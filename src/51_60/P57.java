import java.util.Arrays;
import java.util.HashMap;

public class P57 {
    private HashMap<Integer, String> last = new HashMap<>();

    public int count(int limit)
    {
        int count = 0;
        last.put(1, "1/2");
        for(int i = 2; i <= limit; i++)
        {
            last.put(i, adder(last.get(i-1)));
        }
        for(int i = 1; i <= limit; i++)
        {
            last.put(i, addOne(last.get(i)));
            if(bigger(last.get(i)))
                count++;
        }
        return count;
    }

    public String addOne(String s)
    {
        String[] fraction = s.split("/");
        HugeNum numerator = new HugeNum(fraction[0], 1000);
        HugeNum demoninator = new HugeNum(fraction[1], 1000);
        numerator.add(demoninator);
        return numerator.myVal + "/" + demoninator.myVal;
    }

    public String adder(String s)
    {
        String[] fraction = s.split("/");
        HugeNum numerator = new HugeNum(fraction[0], 1000);
        HugeNum demoninator = new HugeNum(fraction[1], 1000);
        numerator.add(demoninator);
        numerator.add(demoninator);
        return demoninator.myVal + "/" + numerator.myVal;
    }

    public boolean bigger(String s)
    {
        String[] fraction = s.split("/");
        return fraction[0].length() > fraction[1].length();
    }

    public static void main(String[] args)
    {
        P57 p = new P57();
        System.out.println(p.count(1000));
    }
}