import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class P009 {

    private long answer(long limit)
    {
        long max = 0;
        for(long i = 1; i < limit - 1; i++)
        {
            for(long j = i+1; j < limit - i; j++)
            {
                long k = limit - i - j;
                if(k < j)
                    break;
                if(i*i + j*j - k*k == 0)
                {
                    if(i*j*k > max)
                        max = i*j*k;
                }
            }
        }
        if(max > 0)
            return max;
        return -1;
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
        P009 p = new P009();
        ArrayList<Long> inputs = p.read_input();
        for (Long input : inputs) {
            System.out.println(p.answer(input));
        }
    }
}
