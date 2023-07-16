import java.util.HashSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class P51 {
    private TreeSet<Integer> primes1 = new TreeSet<>();
    private TreeSet<Integer> primes2 = new TreeSet<>();
    private TreeSet<Integer> primes3 = new TreeSet<>();
    private TreeSet<Integer> primes4 = new TreeSet<>();
    private TreeSet<Integer> primes5 = new TreeSet<>();
    private TreeSet<Integer> primes6 = new TreeSet<>();
    private TreeSet<Integer> primes7 = new TreeSet<>();
    private TreeSet<Integer> primes8 = new TreeSet<>();
    private HashSet<Integer> processed = new HashSet<>();
    private class sharedNumbers
    {
        TreeMap<Integer, Integer> myVals;
        public sharedNumbers()
        {
            myVals = new TreeMap<Integer, Integer>();
        }
        public void add(int index, int val)
        {
            myVals.put(index, val);
        }
        public boolean equals(sharedNumbers s)
        {
            return myVals.equals(s.myVals);
        }
        public int size()
        {
            return myVals.size();
        }
    }
    public void initialize(int n)
    {
        for(int i = 2; i<=n; i++)
        {
            if (isPrime(i) && repeating(i)){
                primes5.add(i);
                /*double digits = Math.ceil(Math.log10(i));
                if(digits == 8.0)
                {
                    primes8.add(i);
                }
                else if(digits == 7.0)
                {
                    primes7.add(i);
                }
                else if(digits == 6.0)
                {
                    primes6.add(i);
                }
                else if(digits == 5.0)
                {
                    primes5.add(i);
                }
                else if(digits == 4.0)
                {
                    primes4.add(i);
                }
                else if(digits == 3.0)
                {
                    primes3.add(i);
                }
                else if(digits == 2.0)
                {
                    primes2.add(i);
                }
                else if(digits == 1.0)
                {
                    primes1.add(i);
                }
                else
                {
                    System.out.println("idk bro");
                }*/
            }
        }
    }
    public boolean isPrime(int l)
    {
        if(l<0) return false;
        for(int i = 2; i<=Math.sqrt(l);i++)
        {
            if(l%i==0) return false;
        }
        return true;
    }
    public boolean repeating(int n)
    {
        String s = Integer.toString(n);
        String[] t = s.split("");
        HashSet<String> set = new HashSet<>();
        for(String x : t)
        {
            set.add(x);
        }
        return set.size()!=s.length();
    }

    /*public int maxPrimes(int n)
    {
        int max = 0;
        int current = 0;
        String t = Integer.toString(n);
        String[] s = t.split("");
        for(int k = 0; k<=9; k++) {
            for (int i = 0; i < s.length - 1; i++) {
                current = 0;
                for (int j = i + 1; j < s.length; j++) {
                    s = t.split("");
                    s[i] = Integer.toString(k);
                    s[j] = Integer.toString(k);
                    String b = String.join("",s);
                    if(primes5.contains(Integer.parseInt(b)))
                    {
                        //System.out.println(b);
                        current = 1;
                        for(int l = k+1; l<=9; l++)
                        {
                            s[i] = Integer.toString(l);
                            s[j] = Integer.toString(l);
                            b = String.join("", s);
                            if(primes5.contains(Integer.parseInt(b)))
                            {
                                //System.out.println(b);
                                current++;
                            }
                        }
                        if(current > max)
                            max = current;
                    }
                }

            }
        }
        return max;
    }
    public int idk()
    {
        int max = 0;
        int current = 0;
        for(Integer i : primes5)
        {
            current = maxPrimes(i);
            if(current > max) {
                max = current;
                System.out.println(i);
            }
        }
        return max;
    }*/

    public sharedNumbers sharedDigits(int a, int b)
    {
        sharedNumbers s = new sharedNumbers();
        if(a==b) return s;
        String sa = Integer.toString(a);
        String sb = Integer.toString(b);
        String[] x = sa.split("");
        String[] y = sb.split("");
        HashSet<String> alpha = new HashSet<>();
        HashSet<String> beta = new HashSet<>();
        for(int i = 0; i<x.length; i++)
        {
            if(x[i].equals(y[i]))
                s.add(i,Integer.parseInt(x[i]));
            else
            {
                alpha.add(x[i]);
                beta.add(y[i]);
            }
        }
        if(alpha.size() == 1 && alpha.size() == beta.size())
            return s;
        return new sharedNumbers();
    }
    public int count()
    {
        int max = 0;
        int current = 0;
        boolean first = true;
        sharedNumbers s = new sharedNumbers();
        for(int f = 3; f<=5; f++) {
            System.out.println(f);
            processed.clear();
            for (Integer i : primes5) {
                processed.add(i);
                first = true;
                for (Integer j : primes5) {
                    if(Math.ceil(Math.log10((double)i))!=Math.ceil(Math.log10((double)j)) || processed.contains(j))
                        continue;
                    sharedNumbers n = sharedDigits(i, j);
                    if (first && n.size() == f)
                    {
                        s = n;
                        current++;
                        first = false;
                    }
                    else if (n.size() == f)
                    {
                        if (n.equals(s))
                        {
                            current++;
                        }
                    }
                }
                if (current > max)
                {
                    max = current;
                    System.out.println("new max of "+max+" at " + i);
                    if(max==7) return 7;
                }
                else if(current == max)
                {
                    System.out.println("possible same of "+current+" at "+i);
                }
                current = 0;
            }
        }
        return max;
    }

    //RUNTIME O(n^4), actually so shit but whatever lmao

    public static void main(String[] args)
    {
        long start = System.nanoTime();
        P51 p = new P51();
        p.initialize(1000000);
        System.out.println(p.count());

        long end = System.nanoTime();
        System.out.println("Elapsed time: "+(end-start)+" nanoseconds");
        System.out.println("Elapsed time: "+(double)(end-start)/1000000000 + " seconds");
    }
}
