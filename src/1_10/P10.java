import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class P10 {
    private ArrayList<Integer> primes = new ArrayList<>();

    private long answer(long limit)
    {
        long sum = 0;
        for(Integer p: primes)
        {
            if(p > limit)
                break;
            sum += p;
        }
        return sum;
    }

    public void initializeFaster(long n)
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

    private ArrayList<Long> read_input() throws IOException {
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

    public static void main(String[] args) throws IOException {
        P10 p = new P10();
        ArrayList<Long> inputs = p.read_input();

        p.initializeFaster(Collections.max(inputs));
        for (Long input : inputs) {
            System.out.println(p.answer(input));
        }
    }
}
