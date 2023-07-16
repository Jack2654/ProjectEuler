import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class P66 {
    private HashMap<Long, Long> squares = new HashMap<>();
    public long num_of_squares;

    public void initialize_squares(long limit)
    {
        for(long i = 1; i < limit; i++)
        {
            squares.put(i*i, 0L);
        }
    }

    public int largest_square_less_than(int val)
    {
        int max = 0;
        for(Long s: squares.keySet())
        {
            if(s>max && s<val)
            {
                max = s.intValue();
            }
        }
        return max;
    }

    public ArrayList<Integer> ret_period(int base, int max)
    {
        ArrayList<Integer> values = new ArrayList<>();
        ArrayList<Integer> val_copy;
        ArrayList<Integer> period = new ArrayList<>();
        int cutoff = largest_square_less_than(base);
        int num = - (int) Math.sqrt(cutoff);
        int denom = 1;
        int carry;
        int sz;

        for(int i = 0; i < max; i++)
        {
            denom = (base - (num * num)) / denom;
            num = -num;
            carry = (int) Math.round(Math.floor((Math.sqrt(base) + num)/denom));
            values.add(carry);
            num = num - denom * carry;
        }

        val_copy = new ArrayList<>(values);
        sz = 0;
        while(val_copy.size() > 1)
        {
            if(is_repeating(val_copy) && minimal_period(val_copy, values))
            {
                sz = val_copy.size()/2;
                period = new ArrayList<>(val_copy.subList(0, val_copy.size()/2));
            }
            val_copy.remove(val_copy.size() - 1);
            val_copy.remove(val_copy.size() - 1);
        }
        if(max > 100000)
        {
            System.out.println("problem at " + base);
            return null;
        }
        if(sz == 0)
        {
            return ret_period(base, max*2);
        }
        return period;
    }

    public boolean minimal_period(ArrayList<Integer> p, ArrayList<Integer> full)
    {
        int temp = p.size();
        while(temp < full.size())
        {
            if(!is_repeating(new ArrayList<>(full.subList(0, temp))))
                return false;
            temp *= 2;
        }
        return true;
    }

    public boolean is_repeating(ArrayList<Integer> input)
    {
        int a;
        int b;
        for(int i = 0; i < input.size()/2; i++)
        {
            a = input.get(i);
            b = input.get(i + input.size()/2);
            if(a != b)
            {
                return false;
            }
        }
        return true;
    }

    public ArrayList<BigInteger> frac(int base, int num, ArrayList<Integer> per)
    {
        ArrayList<Integer> values = per;
        int count = num;
        BigInteger numer = new BigInteger("1");
        BigInteger denom;
        BigInteger carry;
        denom = new BigInteger(String.valueOf(values.get(count % values.size())));
        while(count > 0)
        {
            numer = (new BigInteger(String.valueOf(values.get((count - 1) % values.size()))).multiply(denom)).add(numer);
            carry = numer;
            numer = denom;
            denom = carry;
            count--;
        }
        numer = (new BigInteger(String.valueOf(Math.round(Math.sqrt(largest_square_less_than(base))))).multiply(denom)).add(numer);

        ArrayList<BigInteger> ret = new ArrayList<>();
        ret.add(numer);
        ret.add(denom);
        return ret;
    }

    public long diophantine(long D)
    {
        long y_squared;
        for(Long s: squares.keySet())
        {
            y_squared = (1 - (s));
            if(y_squared % D == 0)
            {
                y_squared /= -D;
                if (squares.containsKey(y_squared))
                {
                    return (long) Math.sqrt(s);
                }
            }
        }
        return 0;
    }

    public long diophantine_2(long D)
    {
        long temp = 0;
        long i = 1;
        long max = num_of_squares * num_of_squares;
        while(temp < max)
        {
            temp = 1 + (D * (i * i));
            if(Math.pow(Math.floor(Math.sqrt(temp)),2) == temp)
                return (long) Math.sqrt(temp);
            i++;
        }
        return 0;
    }

    public BigInteger diophantine_3(long D)
    {
        boolean found = false;
        int i = 0;
        ArrayList<Integer> period = ret_period((int) D, 32);
        ArrayList<BigInteger> ans;
        while(i < 100000)
        {
            ans = frac((int) D, i, period);
            if(is_solution(ans.get(0), ans.get(1), D))
            {
                found = true;
                return ans.get(0);
            }

            i++;
        }
        return BigInteger.valueOf(0);
    }

    public boolean is_solution(BigInteger x, BigInteger y, long base)
    {
        BigInteger temp_x = new BigInteger("1").multiply(x).multiply(x);
        BigInteger temp_y = new BigInteger("1").multiply(y).multiply(y).multiply(BigInteger.valueOf(base));
        BigInteger temp_3 = new BigInteger("1").multiply(temp_x).subtract(temp_y);
        if(temp_3.equals(new BigInteger("1")))
            return true;
        return false;
    }

    public int zeroes(HashMap<Long, Long> input)
    {
        int ret = 0;
        for(Long i: input.keySet())
        {
            if(input.get(i) == 0)
                ret++;
        }
        return ret;
    }

    public static void main(String[] args)
    {
        P66 p = new P66();
        p.num_of_squares = 10000000L;
        p.initialize_squares(p.num_of_squares);
        System.out.println("Initialized Squares");
        BigInteger dio;
        BigInteger max = BigInteger.valueOf(0);
        long max_val = 0;
        int problems = 0;
        for(long i = 270; i <= 1000; i++)
        {
            if(i%10==0)
            {
                System.out.println(i);
            }
            if(i%100==0)
            {
                System.out.println(max + " found at " + max_val);
            }
            if(Math.sqrt(i) - Math.floor(Math.sqrt(i)) == 0)
            {
                continue;
            }
            dio = p.diophantine_3(i);
            if(dio.compareTo(max) > 0)
            {
                System.out.println("max set to " + dio + " at " + i);
                max = dio;
                max_val = i;
            }
            if(dio.compareTo(BigInteger.valueOf(0)) == 0)
            {
                System.out.println("Problem: " + i);
                problems++;
            }

        }
        System.out.println(problems + " problems found");
    }
}
