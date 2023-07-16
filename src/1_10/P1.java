import java.io.*;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class P1 {
    private long multiple_sum(long limit)
    {
        long num_of_threes = (limit - 1) / 3;
        long num_of_fives = (limit - 1) / 5;
        long num_of_overlap = (limit - 1) / 15;
        long sum_of_threes = 3 * (num_of_threes * (num_of_threes + 1) / 2);
        long sum_of_fives = 5 * (num_of_fives * (num_of_fives + 1) / 2);
        long sum_of_overlap = 15 * (num_of_overlap * (num_of_overlap + 1) / 2);
        return sum_of_threes + sum_of_fives - sum_of_overlap;
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
        P1 p = new P1();
        ArrayList<Long> inputs = p.read_input();
        for(int index = 0; index < inputs.size(); index++)
        {
            System.out.println(p.multiple_sum(inputs.get(index)));
        }
    }
}
