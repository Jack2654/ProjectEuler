import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class P69 {
    private ArrayList<Integer> starting_primes;
    private HashSet<Integer> primes;
    private HashMap<Integer, HashSet<Integer>> factorizations;
    private HashSet<Integer> over_temp;

    public boolean isPrimeFast(int l) {
        if (l < 2) return false;
        int sqrt = (int) Math.sqrt(l);
        for (Integer p : starting_primes) {
            if (l % p == 0) return false;
            if (p > sqrt + 1) {
                return true;
            }
        }
        return true;
    }

    public void initializeFaster(int n) {
        starting_primes.add(2);
        starting_primes.add(3);
        for (int i = 6; i <= n; i += 6) {
            if (isPrimeFast(i - 1)) {
                starting_primes.add(i - 1);
            }
            if (isPrimeFast(i + 1)) {
                starting_primes.add(i + 1);
            }
        }
        primes.addAll(starting_primes);
    }

    public void initializeFactorizations(int n) {
        for (int i = 2; i < n; i++) {
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

    public void initializeOverlap()
    {
        HashSet<Integer> factors;
        float cur;
        float max = 0;
        float max_n = 0;
        float max_factors = 0;
        for (int i = 2; i < factorizations.keySet().size() + 2; i++)
        {
            if(primes.contains(i) || factorizations.get(i).size() < max_factors)
            {
                continue;
            }

            max_factors = factorizations.get(i).size();

            over_temp = new HashSet<>();
            factors = factorizations.get(i);
            overlap(factors, i);
            cur = i - over_temp.size() - 1;
            if(i / cur > max)
            {
                max = i / cur;
                max_n = i;
            }
        }
        System.out.println(max);
        System.out.println(max_n);
    }

    public void overlap(HashSet<Integer> factors, int cur) // bc visiting same point, maybe some way for caching?
    {
        int temp;
        for(Integer f: factors)
        {
            temp = cur;
            temp -= f;
            while(temp > 1)
            {
                over_temp.add(temp);
                temp -= f;
            }
        }
    }

    public int min_not(HashSet<Integer> f, int c)
    {
        int min = 2;
        while(true)
        {
            if(f.contains(min) && min != c)
            {
                min++;
                continue;
            }
            break;
        }
        return min;
    }

    public int factorial(int n)
    {
        int temp = n;
        for(int i = n - 1; i > 1; i--)
        {
            temp *= i;
        }
        return temp;
    }

    public static void main(String[] args)
    {
        P69 p = new P69();
        p.primes = new HashSet<>();
        p.starting_primes = new ArrayList<>();
        p.factorizations = new HashMap<>();

        int n = 1000000;

        p.initializeFaster(n);
        System.out.println("Primes Initialized");

        p.initializeFactorizations(n);
        System.out.println("Factorizations Initialized");

        long start_time = System.nanoTime();
        p.initializeOverlap();
        long end_time = System.nanoTime();
        System.out.println("Overlap Initialized in " + ((end_time-start_time)/(Math.pow(10, 9))) + " seconds");

        //for(Integer a: p.overlap_map.keySet())
        //    System.out.println(a + ": " + p.overlap_map.get(a).toString());

        // System.out.println(p.max_totient());
        }
}
