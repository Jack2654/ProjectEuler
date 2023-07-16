import java.math.BigInteger;
import java.util.*;
public class P53 {
    public int find()
    {
        BigInteger limit = new BigInteger("1000000");
        BigInteger current = new BigInteger("0");
        int count = 0;
        for(int i = 1; i<=100; i++)
        {
            for(int j = 1; j<=i; j++)
            {
                current = combination(i,j);
                if(current.compareTo(limit)>0) count++;
            }
        }
        return count;
    }
    public BigInteger combination(int n, int r)
    {
        BigInteger num = new BigInteger(factorial(n));
        BigInteger den = new BigInteger(factorial(r));
        num = num.divide(den);
        den = new BigInteger(factorial(n-r));
        num = num.divide(den);
        return num;
    }
    public String factorial(int n)
    {
        if(n==0) return Integer.toString(1);
        if(n==1) return Integer.toString(1);
        HugeNum val = new HugeNum(Integer.toString(1), 1000);
        for(int i = n; i>=1; i--)
        {
            val.multiply(i);
        }
        return val.myVal;
    }
    public static void main(String[] args)
    {
        P53 p = new P53();
        System.out.println(p.find());
    }
}
