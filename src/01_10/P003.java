import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class P003 {
    private long answer(long limit)
    {
        if(new BigInteger(String.valueOf(limit)).isProbablePrime(1))
            return limit;
        long remaining = limit;
        long max = 0;

        if(remaining % 2 == 0)
        {
            max = 2;
            while(remaining % 2 == 0)
            {
                remaining /= 2;
            }
        }

        if(remaining % 3 == 0)
        {
            max = 3;
            while(remaining % 3 == 0)
            {
                remaining /= 3;
            }
        }
        for(long i = 6; i <= 1 + limit / 2; i+=6)
        {
            if(remaining < max)
                break;
            if(remaining % (i-1) == 0)
            {
                if((i-1) > max)
                    max = (i-1);
                while(remaining % (i-1) == 0)
                {
                    remaining /= (i-1);
                }
            }
            if(remaining < max)
                break;
            if(remaining % (i+1) == 0)
            {
                if((i+1) > max)
                    max = (i+1);
                while(remaining % (i+1) == 0)
                {
                    remaining /= (i+1);
                }
            }
        }
        return max;
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
        P003 p = new P003();
        ArrayList<Long> inputs = p.read_input();
        for (Long input : inputs) {
            System.out.println(p.answer(input));
        }
    }
}
