import java.util.TreeSet;

public class P58 {

    private TreeSet<Integer> primes = new TreeSet<>();
    private TreeSet<Integer> diag = new TreeSet<>();

    //NOTE:     uses slow prime checker bc fast prime checker would require
    //          initializing all primes below 1,000,000,000 which takes longer
    //          than only slowly checking the target values

    public boolean isPrimeFast(int l)
    {
        if(l<2) return false;
        double sqrt = Math.sqrt(l);
        for(int i = 2; i <= sqrt; i++)
        {
            if(l%i==0) return false;
        }
        return true;
    }

    public int min()
    {
        diag.add(1);
        int i = 1;
        boolean ten = false;
        while(!ten)
        {
            int a = (int) Math.pow(2 * i + 1, 2);
            int b = (int) Math.pow(2 * i + 1, 2) - (2 * i);
            int c = (int) Math.pow(2 * i + 1, 2) - (4 * i);
            int d = (int) Math.pow(2 * i + 1, 2) - (6 * i);
            if(isPrimeFast(b))
                primes.add(b);
            if(isPrimeFast(c))
                primes.add(c);
            if(isPrimeFast(d))
                primes.add(d);

            diag.add(a);
            diag.add(b);
            diag.add(c);
            diag.add(d);
            ten = (double) primes.size() / diag.size() <= 0.1;
            i++;
        }
        System.out.println();
        System.out.println(2 * i - 1);
        return i;
    }

    public static void main(String[] args)
    {
        P58 p = new P58();
        p.min();
    }
}
//Upper right:  31, 13, 3, 1    (2n + 1)^2 - 6n
//Upper left:   37, 17, 5, 1    (2n + 1)^2 - 4n
//Bottom left:  43, 21, 7, 1    (2n + 1)^2 - 2n
//Bottom right: 49, 25, 9, 1    (2n + 1)^2