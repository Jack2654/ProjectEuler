import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.IntStream;

public class P015 {
    private Long[][] paths;
    private long answer(long m, long n)
    {
        return path_search(m, n);
    }

    private long path_search(long m, long n) {
        for (int i = (int) m; i >= 0; i--)
            paths[(int) n][i] = 1L;
        for (int i = (int) n; i >= 0; i--)
            paths[i][(int) m] = 1L;

        for (int i = (int)m - 1; i >= 0; i--)
        {
            for(int j = (int)n - 1; j>=0; j--)
            {
                paths[j][i] = (paths[j][i+1] + paths[j+1][i]) % (long)(Math.pow(10, 9) + 7);
            }
        }
        return paths[0][0] % (long)(Math.pow(10, 9) + 7);
    }


    private ArrayList<String> read_input() throws IOException {
        ArrayList<String> ret = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bufferedReader.readLine().trim());
        IntStream.range(0, t).forEach(tItr -> {
            try {
                ret.add(bufferedReader.readLine().trim());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bufferedReader.close();
        return ret;
    }

    public static void main(String[] args) throws IOException {
        P015 p = new P015();
        ArrayList<String> inputs = p.read_input();
        for (String input : inputs) {
            String[] grid = input.split(" ");
            long m = Long.parseLong(grid[0]);
            long n = Long.parseLong(grid[1]);
            p.paths = new Long[(int) n + 1][(int) m + 1];
            System.out.println(p.answer(m, n));
        }
    }
}
