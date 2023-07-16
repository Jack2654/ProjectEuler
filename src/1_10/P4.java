import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class P4 {
    public long answer(long limit)
    {
        long max = 1;
        for(long i = 100; i<1000; i++)
        {
            for(long j = 100; j<1000; j++)
            {
                long product = i * j;
                if(product >= limit)
                    continue;
                if((product > max) && (isPalindrome(product)))
                {
                    max = product;
                }
            }
        }
        return max;
    }

    public boolean isPalindrome(long i)
    {
        StringBuilder s = new StringBuilder(i + "");
        return s.toString().equals(s.reverse().toString());
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
        P4 p = new P4();
        ArrayList<Long> inputs = p.read_input();
        for (Long input : inputs) {
            System.out.println(p.answer(input));
        }
    }
}
