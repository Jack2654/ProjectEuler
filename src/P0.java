import java.io.*;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class P0 {
    private long answer(long limit)
    {
        return 0;
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
        P0 p = new P0();
        ArrayList<Long> inputs = p.read_input();
        for(int index = 0; index < inputs.size(); index++)
        {
            System.out.println(p.answer(inputs.get(index)));
        }
    }
}
