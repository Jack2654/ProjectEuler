import java.math.BigInteger;
import java.util.ArrayList;

public class P65 {
    private ArrayList<Integer> values = new ArrayList<>();
    public void initialize(int limit)
    {
        int j = 1;
        for(int i = 0; i < limit; i++)
        {
            if((i-1)%3==0)
            {
                values.add(2*j);
                j++;
            }
            else
            {
                values.add(1);
            }
        }
    }

    public void frac(int num)
    {
        int count = num;
        BigInteger numer = new BigInteger("1");
        BigInteger denom;
        BigInteger carry;
        denom = new BigInteger(String.valueOf(values.get(count)));
        while(count > 0)
        {
            numer = (new BigInteger(String.valueOf(values.get(count - 1))).multiply(denom)).add(numer);
            carry = numer;
            numer = denom;
            denom = carry;
            count--;
        }
        numer = (new BigInteger("2").multiply(denom)).add(numer);
        System.out.println("num: " + numer);
        int sum = 0;
        for(String s: numer.toString().split(""))
        {
            sum += Integer.parseInt(s);
        }
        System.out.println(sum);
        System.out.println("denom: " + denom);
    }

    public static void main(String args[])
    {
        P65 p = new P65();
        p.initialize(99);
        p.frac(98);
    }
}
