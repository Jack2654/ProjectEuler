public class P56 {
    public int max(int limit)
    {
        int max = 0;
        HugeNum current;
        for(int i = 1; i<limit; i++)
        {
            for(int j = 1; j<limit; j++)
            {
                current = new HugeNum(Integer.toString(i), 200);
                current.power(j);
                int sum = digitSum(current);
                if(sum > max)
                    max = sum;
            }
        }
        return max;
    }
    public int digitSum(HugeNum h)
    {
        int ret = 0;
        for(int i : h.myInt)
        {
            ret += i;
        }
        return ret;
    }
    public static void main(String[] args)
    {
        P56 p = new P56();
        System.out.println(p.max(100));
    }
}