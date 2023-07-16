public class P55 {
    public int count(int limit)
    {
        int count = 0;
        HugeNum current;
        boolean lychrel = false;
        for(int i = 1; i<=limit; i++)
        {
            current = new HugeNum(Integer.toString(i), 100);
            for(int j = 0; j<50; j++)
            {
                current.add(reverse(current));
                if(isPalindrome(current)) break;
                if(j==49) lychrel = true;
            }
            if(lychrel) count++;
            lychrel = false;
        }
        return count;
    }

    public boolean isPalindrome(HugeNum h)
    {
        StringBuilder s = new StringBuilder(h.myVal);
        return s.toString().equals(s.reverse().toString());
    }

    public HugeNum reverse(HugeNum h)
    {
        StringBuilder s = new StringBuilder(h.myVal);
        s.reverse();
        HugeNum ret = new HugeNum(s.toString(), 100);
        return ret;
    }

    public static void main(String[] args)
    {
        P55 p = new P55();
        System.out.println(p.count(10000));
    }
}