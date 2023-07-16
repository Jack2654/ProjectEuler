public class P97 {
    public long reduce(long num)
    {
        String temp = Long.toString(num);
        if(temp.length() < 11)
        {
            temp = "0000000000" + temp;
        }
        return Long.parseLong(temp.substring(temp.length() - 10));
    }
    public static void main(String[] args)
    {
        P97 p = new P97();
        long temp = 1;
        for(long i = 0; i < 7830457L; i++)
        {
            temp *= 2L;
            temp = p.reduce(temp);
        }
        temp *= 28433L;
        temp++;
        System.out.println(temp);
    }
}
