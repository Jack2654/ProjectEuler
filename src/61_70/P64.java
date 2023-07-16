import java.lang.reflect.Array;
import java.util.ArrayList;

public class P64 {
    private ArrayList<Integer> squares = new ArrayList<>();

    public void initialize_squares(int limit)
    {
        for(int i = 1; i < limit; i++)
        {
            squares.add(i*i);
        }
    }

    public int largest_square_less_than(int val)
    {
        int max = 0;
        for(Integer s: squares)
        {
            if(s>max && s<val)
            {
                max = s;
            }
        }
        return max;
    }

    public int ret_period(int base, int max)
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
            return 0;
        }
        if(sz == 0)
        {
            return ret_period(base, max*2);
        }
        System.out.println(period.toString());
        return sz;
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

    public static void main(String args[])
    {
        P64 p = new P64();
        p.initialize_squares(100);
        int count = 0;
        int cur;

        for(int i = 1; i < 10000; i++)
        {
            if(!p.squares.contains(i))
            {
                cur = p.ret_period(i, 32);
                if(cur%2==1)
                {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
    // Answer: 1322 odds
}
