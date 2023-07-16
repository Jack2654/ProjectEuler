import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class P72 {
    private ArrayList<Integer> starting_primes;
    private HashSet<Integer> primes;
    private HashMap<Integer, HashSet<Integer>> factorizations;

    public boolean isPrimeFast(int l)
    {
        if(l<2) return false;
        int sqrt = (int) Math.sqrt(l);
        for(Integer p : starting_primes)
        {
            if(l % p == 0) return false;
            if(p > sqrt + 1)
            {
                return true;
            }
        }
        return true;
    }

    public void initializeFaster(int n)
    {
        starting_primes.add(2);
        starting_primes.add(3);
        for(int i = 6; i<=n; i+=6)
        {
            if (isPrimeFast(i-1))
            {
                starting_primes.add(i-1);
            }
            if (isPrimeFast(i+1))
            {
                starting_primes.add(i+1);
            }
        }
        primes.addAll(starting_primes);
    }

    public void initializeFactorizations(int n) {
        for (int i = 2; i <= n; i++) {
            factorizations.put(i, prime_factorization(i));
        }
    }

    public HashSet<Integer> prime_factorization(int n) {
        if (factorizations.containsKey(n))
            return new HashSet<>(factorizations.get(n));

        HashSet<Integer> ret = new HashSet<>();
        if (primes.contains(n)) {
            ret.add(n);
            return ret;
        }

        for (Integer p : starting_primes) {
            if (p > n)
                break;
            if (n % p == 0) {
                ret = prime_factorization(n / p);
                ret.add(p);
                return ret;
            }
        }
        ret = new HashSet<>();
        return ret;
    }

    public long proper_fractions(int n)
    {
        long ret = 0;
        long overlap_size;
        HashSet<Integer> factors;
        for(long i = 2; i <= n; i++)
        {
            factors = factorizations.get((int) i);
            overlap_size = overlap_2(factors, i);
            //System.out.println(i + ": " + (i - overlap_size) + ",\t\t" + factors.toString());
            ret += overlap_size;
        }
        return ret;
    }


    // this method is wrong. See example of 60. 30 is removed 3 times when it should only be removed twice
    public long overlap(HashSet<Integer> factors, long cur) // returns the # of numbers less than cur which are not mutually coprime with cur
    {
        if(primes.contains((int) cur))
        {
            return 0;
        }
        ArrayList<Integer> temp = new ArrayList<>(factors);
        long retur = 0;
        long tem;
        for(int i = 0; i < temp.size(); i++)
        {
            retur += (cur / temp.get(i)) - 1;
            for(int j = i + 1; j < temp.size(); j++)
            {
                //retur -= (cur / (((long) temp.get(i)) * ((long) temp.get(j)))) - 1;
                tem = (cur / (((long) temp.get(i)) * ((long) temp.get(j)))) - 1;
                System.out.println(tem);
                retur -= tem;
            }
        }
        return retur;
    }

    public long overlap_2(HashSet<Integer> factors, long cur)
    {
        double ret = cur;
        for(Integer i: factors)
        {
            ret *= (1 - (1/(double)i));
        }
        return Math.round(ret);
    }

    public static void main(String[] args)
    {
        P72 p = new P72();
        int n;
        n = 1000000;
        p.starting_primes = new ArrayList<>();
        p.primes = new HashSet<>();
        p.factorizations = new HashMap<>();

        p.initializeFaster(n);
        System.out.println("Initialized Primes");
        p.initializeFactorizations(n);
        System.out.println("Initialized Factors");
        System.out.println(p.proper_fractions(n));

    }
    // 303,963,552,391
}
