import java.util.*;

// solution is painfully slow but works
// better is to concatenate primes rather than look at big numbers to see if it is a concatenation itself

public class P60 {
    private ArrayList<Integer> primes = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> links = new ArrayList<>();
    private HashMap<Integer, ArrayList<Integer>> full = new HashMap<>();
    private ArrayList<ArrayList<Integer>> results = new ArrayList<>();

    public boolean isPrimeFast(int l)
    {
        if(l<2) return false;
        int sqrt = (int) Math.sqrt(l);
        for(Integer p : primes)
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
        primes.add(2);
        primes.add(3);
        for(int i = 6; i<=n; i+=6)
        {
            if (isPrimeFast(i-1))
            {
                primes.add(i-1);
            }
            if (isPrimeFast(i+1))
            {
                primes.add(i+1);
            }
        }
    }

    public void initializeArrays()
    {
        for(Integer p: primes)
        {
            links.add(possible_pairs(p));
            System.out.println(p);
        }
    }

    public ArrayList<Integer> possible_pairs(int start)
    {
        String temp;
        String temp_c;
        String temp_n;
        HashSet<Integer> possible = new HashSet<>();

        for(Integer p: primes) {
            if(p != start) {
                temp = String.valueOf(p);
                temp_c = String.valueOf(start);
                if (temp.startsWith(temp_c)) {
                    temp_n = temp.substring(temp_c.length());
                    if (primes.contains(Integer.parseInt(temp_n)) && primes.contains(Integer.parseInt(temp_n + "" + temp_c)))
                    {
                        possible.add(Integer.parseInt(temp_n));
                    }
                }
                if (temp.endsWith(temp_c)) {
                    temp_n = temp.substring(0, temp.length() - temp_c.length());
                    if (primes.contains(Integer.parseInt(temp_n)) && primes.contains(Integer.parseInt(temp_c + "" + temp_n)))
                    {
                        possible.add(Integer.parseInt(temp_n));
                    }
                }
            }
        }
        ArrayList<Integer> ret = new ArrayList<>(possible);
        ret.add(start);
        return ret;
    }

    public void find_five(int start)
    {
        ArrayList<Integer> possible;
        ArrayList<Integer> possible_a;
        ArrayList<Integer> possible_b;
        ArrayList<Integer> possible_c;
        ArrayList<Integer> possible_d;
        ArrayList<Integer> temp;
        ArrayList<Integer> temp_2;
        ArrayList<Integer> temp_3;
        ArrayList<Integer> temp_4;

        int digits = 5;

        if (!full.containsKey(start)) {
            full.put(start, possible_pairs(start));
        }
        possible = full.get(start);
        int len = possible.size();
        for(Integer a: possible)
        {
            if(a <= start)
            {
                continue;
            }
            if (!full.containsKey(a)) {
                full.put(a, possible_pairs(a));
            }
            possible_a = full.get(a);
            temp = new ArrayList<>(possible);
            temp.retainAll(possible_a);
            if(temp.size() < digits)
            {
                continue;
            }
            else if(!temp.contains(start))
            {
                continue;
            }
            else if(!temp.contains(a))
            {
                continue;
            }
            for(Integer b: temp)
            {
                if(b <= start || b == a)
                {
                    continue;
                }
                if (!full.containsKey(b)) {
                    full.put(b, possible_pairs(b));
                }
                possible_b = full.get(b);
                temp_2 = new ArrayList<>(possible);
                temp_2.retainAll(possible_a);
                temp_2.retainAll(possible_b);
                if(temp_2.size() < digits)
                {
                    continue;
                }
                else if(!temp_2.contains(start))
                {
                    continue;
                }
                else if(!temp_2.contains(a))
                {
                    continue;
                }
                else if(!temp_2.contains(b))
                {
                    continue;
                }
                // System.out.println(start + " " + a + " " + b);
                // System.out.println(temp_2);
                for(Integer c: temp_2)
                {
                    if(c <= start || c == b || c == a)
                    {
                        continue;
                    }
                    if (!full.containsKey(c)) {
                        full.put(c, possible_pairs(c));
                    }
                    possible_c = full.get(c);
                    temp_3 = new ArrayList<>(possible);
                    temp_3.retainAll(possible_a);
                    temp_3.retainAll(possible_b);
                    temp_3.retainAll(possible_c);
                    if(temp_3.size() < digits)
                    {
                        continue;
                    }
                    System.out.println(start + " " + a + " " + b + " " + c);
                    System.out.println(temp_3);
                    for(Integer d: temp_3)
                    {
                        if(d <= start || d == a || d == b || d == c)
                        {
                            continue;
                        }
                        if (!full.containsKey(d)) {
                            full.put(d, possible_pairs(d));
                        }
                        possible_d = full.get(d);
                        temp_4 = new ArrayList<>(possible);
                        temp_4.retainAll(possible_a);
                        temp_4.retainAll(possible_b);
                        temp_4.retainAll(possible_c);
                        temp_4.retainAll(possible_d);
                        if(temp_4.size() < digits)
                        {
                            continue;
                        }
                        System.out.println(start + " " + a + " " + b + " " + c + " " + d);
                        System.out.println(temp_4);
                        results.add(temp_4);
                    }
                }
            }
        }
    }

    public void min() {
        HashSet<Integer> possible;

        initializeFaster(100000000);
        for (int i = 0; i < primes.size(); i++) {
            System.out.println(primes.get(i));
            find_five(primes.get(i));
        }
        for (ArrayList<Integer> a : results)
        {
            System.out.println(a.toString());
        }
    }

    public static void main(String[] args)
    {
        P60 p = new P60();
        p.min();
    }
}
