import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class P8 {
    private ArrayList<String> values;
    private long answer(long n, long k, String val) {
        long max = 0;
        long temp;
        StringBuilder value = new StringBuilder(val);
        for(int i = 0; i <= (n-k); i++)
        {
            temp = product(value.substring(i, (int) (i+k)));
            if(temp > max)
                max = temp;
        }
        return max;
    }

    private long product(String s)
    {
        long res = 1;
        for(String c: s.split(""))
        {
            res *= Long.parseLong(c);
        }
        return res;
    }

    private ArrayList<Long> read_input() throws IOException {
        ArrayList<Long> ret = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bufferedReader.readLine().trim());
        IntStream.range(0, t*2).forEach(tItr -> {
            try {
                String[] line = bufferedReader.readLine().trim().split(" ");
                if(line.length == 1)
                {
                    values.add(line[0]);
                }
                else
                {
                    for(String s: line)
                    {
                        ret.add(Long.parseLong(s));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bufferedReader.close();
        return ret;
    }

    public static void main(String[] args) throws IOException {
        P8 p = new P8();
        p.values = new ArrayList<>();
        ArrayList<Long> inputs = p.read_input();
        for(int i = 0; i < inputs.size(); i += 2)
        {
            System.out.println(p.answer(inputs.get(i), inputs.get(i+1), p.values.get(i/2)));
        }
    }
}
