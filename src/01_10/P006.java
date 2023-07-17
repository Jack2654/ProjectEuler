import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class P006 {
    private long answer(long limit)
    {
        long ans1 = sum_of_squares(limit);
        long ans2 = square_of_sums(limit);
        if(ans1 > ans2)
            return ans1-ans2;
        return ans2-ans1;
    }

    private long sum_of_squares(long limit)
    {
        long result = 0;
        for(long i = 1; i <= limit; i++)
        {
            result += i * i;
        }
        return result;
    }

    private long square_of_sums(long limit)
    {
        long result = limit * (limit + 1) / 2;
        result *= result;
        return result;
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
        P006 p = new P006();
        ArrayList<Long> inputs = p.read_input();
        for (Long input : inputs) {
            System.out.println(p.answer(input));
        }
    }
}
