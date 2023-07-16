public class P71 {
    public void max(int n)
    {
        int num_max = 0;
        int denom_max = 1;
        double max = num_max / denom_max;
        double temp;
        for(int i = 2; i <= n; i++)
        {
            for(int j = i / 3; j < i; j++)
            {
                temp = (double) j / (double) i;
                if(temp >= 3.0 / 7.0)
                    break;
                if(temp > max)
                {
                    max = temp;
                    num_max = j;
                    denom_max = i;
                    System.out.println(num_max + " / " + denom_max);
                }
            }
        }
        System.out.println(num_max + " / " + denom_max);
    }

    public static void main(String[] args)
    {
        P71 p = new P71();
        int n = 1000000;
        p.max(n);
    }
    // answer is 428570 / 999997
}
