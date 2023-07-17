import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.IntStream;

public class P016 {
    public int[] value;
    public HashMap<Integer, Long> cache;

    private void initialize(long limit) {
        value = new int[50000];
        value[49999] = 2;
        for (int i = 0; i < limit; i++) {
            multiply();
            cache.put(i+2, sum());
        }
    }

    private long answer(long limit)
    {
        return cache.get((int)limit);
    }

    private void multiply()
    {
        for(int i = 0; i < value.length; i++)
            value[i] *= 2;
        for(int i = 0; i < value.length; i++)
        {
            if(value[i] > 9)
            {
                value[i] -= 10;
                value[i-1] += 1;
            }
        }
    }

    private long sum()
    {
        long res = 0;
        for(Integer i: value)
        {
            res += i;
        }
        return res;
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
        P016 p = new P016();
        ArrayList<Long> inputs = p.read_input();
        p.cache = new HashMap<>();
        p.initialize(Collections.max(inputs));
        for(int index = 0; index < inputs.size(); index++)
        {
            System.out.println(p.answer(inputs.get(index)));
        }
    }
}
