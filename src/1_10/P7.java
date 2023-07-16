import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class P7 {
    private ArrayList<Integer> primes = new ArrayList<>();

    private long answer(long limit)
    {
        return primes.get((int) limit - 1);
    }

    public void initializeFaster(long count)
    {
        primes.add(2);
        primes.add(3);
        long counter = 2;
        int i = 0;
        while(count > counter)
        {
            i+=6;
            if (isPrimeFast(i-1))
            {
                primes.add(i-1);
                counter++;
            }
            if (isPrimeFast(i+1))
            {
                primes.add(i+1);
                counter++;
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
        P7 p = new P7();
        ArrayList<Long> inputs = p.read_input();

        p.initializeFaster(Collections.max(inputs));
        for (Long input : inputs) {
            System.out.println(p.answer(input));
        }
    }
}
