import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class P020 {
    private long answer(long limit)
    {
        BigInteger temp = new BigInteger("1");
        for(long i = limit; i > 1; i--)
            temp = temp.multiply(new BigInteger(String.valueOf(i)));
        return sum_digits(temp);
    }

    private long sum_digits(BigInteger input)
    {
        String[] values = input.toString().split("");
        long res = 0;
        for(String s: values)
            res += Long.parseLong(s);
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
        P020 p = new P020();
        ArrayList<Long> inputs = p.read_input();
        for (Long input : inputs) {
            System.out.println(p.answer(input));
        }
    }
}
