import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.IntStream;

public class P12 {

    private long answer(long limit)
    {
        long i = 1;
        long num;
        while(true)
        {
            if(i%2==0)
                num = divisors(i/2) * divisors(i+1);
            else
                num = divisors(i) * divisors((i+1)/2);
            if(num > limit)
                return i * (i+1) / 2;
            i++;
        }
    }

    long divisors(long limit) {
        long count = 0;
        for (long i = 1; i <= Math.sqrt(limit); i++)
        {
            if (limit % i == 0)
            {
                count += 2;
                if (limit / i == i)
                    count--;
            }
        }
        return count;
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
        P12 p = new P12();
        ArrayList<Long> inputs = p.read_input();
        for(int index = 0; index < inputs.size(); index++)
        {
            System.out.println(p.answer(inputs.get(index)));
        }
    }
}
