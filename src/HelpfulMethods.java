import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.stream.IntStream;
import java.util.stream.*;

public class HelpfulMethods {
    //private TreeSet<Integer> primes = new TreeSet<>();
    private ArrayList<Integer> primes = new ArrayList<>();

    public void initializeFast(int n)
    {
        for(int i = 2; i<=n; i++)
        {
            if (isPrimeFast(i))
            {
                primes.add(i);
            }
        }
    }

    public ArrayList<Long> read_input() throws IOException {
        ArrayList<Long> ret = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bufferedReader.readLine().trim());
        IntStream.range(0, t).forEach(tItr -> {
            try {
                ret.add(Long.parseLong(bufferedReader.readLine().trim()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bufferedReader.close();
        return ret;
    }

    public boolean isPrimeFast(int l)
    {
        if(l<2) return false;
        int sqrt = (int) Math.sqrt(l);
        for(Integer p : primes)
        {
            if(l % p == 0) return false;
            if(p > sqrt + 1)
            {
                return true;
            }
        }
        return true;
    }

    public void initializeFaster(int n)
    {
        primes.add(2);
        primes.add(3);
        for(int i = 6; i<=n; i+=6)
        {
            if (isPrimeFast(i-1))
            {
                primes.add(i-1);
            }
            if (isPrimeFast(i+1))
            {
                primes.add(i+1);
            }
        }
    }

    public static void main(String[] args)
    {
        HelpfulMethods h = new HelpfulMethods();
        /*long start = System.nanoTime();
        h.initializeFast(10000000);
        System.out.println(h.primes.size());
        long end = System.nanoTime();
        System.out.println("Elapsed time: "+(end-start)+" nanoseconds");
        System.out.println("Elapsed time: "+(double)(end-start)/1000000000 + " seconds");
        System.out.println();
        h.primes.clear();*/

        long start = System.nanoTime();

        h.initializeFaster(100000000);
        System.out.println(h.primes.size());
        //System.out.println(h.primes.toString());
        long end = System.nanoTime();
        System.out.println("Elapsed time: "+(end-start)+" nanoseconds");
        System.out.println("Elapsed time: "+(double)(end-start)/1000000000 + " seconds");
        System.out.println();
    }
}
