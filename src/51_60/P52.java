import java.util.Arrays;
import java.util.HashSet;

public class P52 {
    public int smallest()
    {
        for(int i = 1; i <= Integer.MAX_VALUE; i++)
        {
            if(i%100000==0) System.out.println(i);
            if(sameDigits(i, 2*i) && sameDigits(i, 3*i) && sameDigits(i, 4*i) && sameDigits(i, 5*i) && sameDigits(i, 6*i))
            {
                return i;
            }
        }
        return -1;
    }
    public boolean sameDigits(int a, int b)
    {
        if(Math.ceil(Math.log10(a))!=Math.ceil(Math.log10(b)))
            return false;
        HashSet<String> aSet = new HashSet<>();
        HashSet<String> bSet = new HashSet<>();
        aSet.addAll(Arrays.asList(Integer.toString(a).split("")));
        bSet.addAll(Arrays.asList(Integer.toString(b).split("")));
        return aSet.equals(bSet);
    }
    public static void main(String[] args)
    {
        P52 p = new P52();
        System.out.println(p.smallest());
    }
}