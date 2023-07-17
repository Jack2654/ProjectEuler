import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class P014 {
    private HashMap<Long, Long> cache;
    private HashMap<Long, Long> cacheL2;

    public long answer(long limit)
    {
        long maxVal = 1;
        for(Long t: cacheL2.keySet())
        {
            if(t > maxVal && t <= limit)
            {
                maxVal = t;
            }
        }
        return maxVal;
    }

    private void initialize(long limit)
    {
        long max = 0;
        long maxVal;
        for(long i = 1; i<=limit; i++)
        {
            long curr = count(i);
            cache.put(i, curr);
            if(curr >= max)
            {
                max = curr;
                maxVal = i;
                cacheL2.put(maxVal, max);
            }
        }
    }

    public long count(long n)
    {
        long temp = n;
        long counter = 0;
        while(temp > 1)
        {
            if (temp < n)
                return counter + cache.get(temp);
            counter++;
            if(temp%2==0)
                temp /= 2;
            else
                temp = 3 * temp + 1;
        }
        return counter;
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
        P014 p = new P014();
        ArrayList<Long> inputs = p.read_input();
        p.cache = new HashMap<>();
        p.cacheL2 = new HashMap<>();
        p.initialize(Collections.max(inputs));
        for(int index = 0; index < inputs.size(); index++)
        {
            System.out.println(p.answer(inputs.get(index)));
        }
    }
}
