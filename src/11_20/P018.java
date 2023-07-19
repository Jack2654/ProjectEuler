import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.IntStream;

public class P018 {
    private long answer(ArrayList<String[]> pyramid) {
        ArrayList<int[]> pyr_int = new ArrayList<>();
        for (String[] s : pyramid) {
            int[] temp = Arrays.stream(s).mapToInt(Integer::parseInt).toArray();
            pyr_int.add(temp);
        }
        int[][] formatted_data = new int[pyr_int.size()][pyr_int.get(pyr_int.size() - 1).length];
        int j = 0;
        for (int[] i : pyr_int) {
            formatted_data[j] = i;
            j++;
        }

        for(int x = formatted_data.length - 2; x >= 0; x--)
        {
            for(int y = 0; y < formatted_data[x].length; y++)
            {
                formatted_data[x][y] = formatted_data[x][y] + Math.max(formatted_data[x+1][y], formatted_data[x+1][y+1]);
            }
        }
        return formatted_data[0][0];
    }

    private ArrayList<String[]> read_input() throws IOException {
        ArrayList<String[]> ret = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bufferedReader.readLine().trim());
        IntStream.range(0, t).forEach(tItr -> {
            try {
                int c = Integer.parseInt(bufferedReader.readLine().trim());
                IntStream.range(0, c).forEach(cItr -> {
                    try {
                        ret.add(bufferedReader.readLine().trim().split(" "));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bufferedReader.close();
        return ret;
    }

    public static void main(String[] args) throws IOException {
        P018 p = new P018();
        ArrayList<String[]> inputs = p.read_input();
        ArrayList<String[]> pyramid = new ArrayList<>();
        for (String[] input : inputs) {
            if(input.length == 1)
            {
                if(pyramid.size() > 0)
                    System.out.println(p.answer(pyramid));
                pyramid = new ArrayList<>();
            }
            pyramid.add(input);
        }
        System.out.println(p.answer(pyramid));
    }
}
