import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class P5 {
    public long answer(long limit)
    {
        long min = 1;
        for(long i = 1; i <= limit; i++)
        {
            if(min % i == 0)
                continue;
            for(long j = 2; j <= i; j++)
            {
                if((min * j) % i == 0)
                {
                    min *= j;
                    break;
                }
            }
        }
        return min;
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
        P5 p = new P5();
        ArrayList<Long> inputs = p.read_input();
        for (Long input : inputs) {
            System.out.println(p.answer(input));
        }
    }
}
