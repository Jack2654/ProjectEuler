public class P63 {
    public int digits(double number)
    {
        int length = 1;
        if (number >= 100000000)
        {
            length += 8;
            number /= 100000000;
        }
        if (number >= 100000000) {
            length += 8;
            number /= 100000000;
        }
        if (number >= 10000) {
            length += 4;
            number /= 10000;
        }
        if (number >= 100) {
            length += 2;
            number /= 100;
        }
        if (number >= 10) {
            length += 1;
            number /= 10;
        }
        if (number >= 10)
        {
            length += 1;
            number /= 10;
        }
        if (number >= 10)
        {
            length += 1;
            number /=10;
        }
        if (number >= 10)
        {
            length += 1;
            number /= 10;
        }
        if (number >= 10)
        {
            length += 1;
            number /= 10;
        }
        if (number >= 10)
        {
            length += 1;
            number /= 10;
        }
        return length;
    }

    public static void main(String args[])
    {
        P63 p = new P63();
        int num = 0;
        int pow;
        long val;
        for(int i = 1; i < 10; i++)
        {
            pow = 1;
            while(pow < 23)
            {
                val = (long) Math.pow(i, pow);
                if(pow == p.digits(val))
                {
                    System.out.println(i + " " + pow + " " + val);
                    num++;
                }
                pow++;
            }
        }
        System.out.println(num);
    }
    // answer is 49
}
