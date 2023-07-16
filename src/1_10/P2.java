import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class P2 {
    private long sum(long limit)
    {
        long last = 1;
        long current = 1;
        long next = 1;
        long sum = 0;
        while(next < limit)
        {
            if((next % 2) == 0)
                sum += next;

            next = current + last;
            last = current;
            current = next;
        }
        return sum;
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
        P2 p = new P2();
        ArrayList<Long> inputs = p.read_input();
        for(int index = 0; index < inputs.size(); index++)
        {
            System.out.println(p.sum(inputs.get(index)));
        }

    }
}
