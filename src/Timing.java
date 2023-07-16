import javax.swing.plaf.synth.SynthDesktopIconUI;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.*;

public class Timing {
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

        public String num_map(long num) {
            int[] ret = new int[10];
            String[] num_s = String.valueOf(num).split("");
            int temp;
            for(String s: num_s)
            {
                temp = Integer.parseInt(s);
                ret[temp]++;
            }
            StringBuilder res = new StringBuilder();
            for(Integer i: ret)
            {
                res.append(i);
            }
            return res.toString();
        }

        public boolean permutation(int a, int b)
        {
            String alpha = num_map(a);
            String beta = num_map(b);
            return alpha.equals(beta);
        }

        public int sum(HashSet<Integer> values)
        {
            int ret = 0;
            for(Integer i: values)
            {
                ret += i;
            }
            return ret;
        }

        public void initializeOverlap()
        {
            HashSet<Integer> factors;
            float cur;
            float min = 100;
            float min_n = 0;
            int fact = 2;
            // int sum_min = 0;
            int temp = 0;
            int overlap_size;
            long start;
            long tot_time = 0;
            while(true)
            {
                System.out.println(fact + "f");
                temp =0;
                for (int i = factorizations.keySet().size() + 1; i > 1; i--)
                {
                    if(factorizations.get(i).size() != fact/* || i%2==0 || sum(factorizations.get(i)) < sum_min*/) // reverse this back?
                    {
                        continue;
                    }

                    start = System.nanoTime();
                    temp++;
                    factors = factorizations.get(i);
                    overlap_size = overlap(factors, i);

                    cur = i - overlap_size - 1;
                    tot_time += System.nanoTime() - start;

                    if(i / cur < min && permutation(i, (int)cur))
                    {
                        System.out.println(i + ": " + cur);
                        // sum_min = sum(factorizations.get(i));
                        min = i / cur;
                        min_n = i;
                    }
                }
                System.out.println("temp: " + temp);
                System.out.println("time: " + (tot_time/Math.pow(10,9)) + " seconds");
                if(min_n != 0)
                {
                    break;
                }
                fact++;
            }

            System.out.println(min);
            System.out.println(min_n);
        }

        public int overlap(HashSet<Integer> factors, int cur)
        {
            ArrayList<Integer> temp = new ArrayList<>(factors);
            int ret = 0;
            for(int i = 0; i < temp.size(); i++)
            {
                ret += (cur / temp.get(i)) - 1;
                for(int j = i + 1; j < temp.size(); j++)
                {
                    ret -= (cur / (temp.get(i)*temp.get(j))) - 1;
                }
            }
            return ret;
        }

        public static void main(String[] args)
        {
            Timing p = new Timing();
            p.primes = new HashSet<>();
            p.starting_primes = new ArrayList<>();
            p.factorizations = new HashMap<>();

            int n = 10000000;

            p.initializeFaster(n);
            System.out.println("Primes Initialized");

            p.initializeFactorizations(n);
            System.out.println("Factorizations Initialized");

            long start_time = System.nanoTime();
            p.initializeOverlap();
            long end_time = System.nanoTime();
            System.out.println("Overlap Initialized in " + ((end_time-start_time)/(Math.pow(10, 9))) + " seconds");
        }
}
