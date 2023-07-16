import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class P13 {
    private String answer(ArrayList<BigInteger> values)
    {
        BigInteger sum = new BigInteger("0");
        for(BigInteger b: values)
        {
            sum = sum.add(b);
        }
        return sum.toString().substring(0, 10);
    }

    private ArrayList<BigInteger> read_input() throws IOException {
        ArrayList<BigInteger> ret = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bufferedReader.readLine().trim());
        IntStream.range(0, t).forEach(tItr -> {
            try {
                ret.add(new BigInteger(bufferedReader.readLine().trim()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bufferedReader.close();
        return ret;
    }

    public static void main(String[] args) throws IOException {
        P13 p = new P13();
        ArrayList<BigInteger> inputs = p.read_input();
        System.out.println(p.answer(inputs));
    }
}
