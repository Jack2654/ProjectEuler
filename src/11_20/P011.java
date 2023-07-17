import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

public class P011 {
    private long answer(ArrayList<ArrayList<String>> full) {
        ArrayList<ArrayList<Long>> formatted = new ArrayList<>();
        for(ArrayList<String> row: full)
        {
            ArrayList<Long> running = new ArrayList<>();
            for(String s: row)
            {
                running.add(Long.parseLong(s));
            }
            formatted.add(running);
        }

        long temp;
        long max = 0;
        temp = maxHorizontal(formatted);
        if(temp > max)
            max = temp;
        temp = maxVertical(formatted);
        if(temp > max)
            max = temp;
        temp = maxDiagDown(formatted);
        if(temp > max)
            max = temp;
        temp = maxDiagUp(formatted);
        if(temp > max)
            max = temp;
        return max;
    }

    private long maxHorizontal(ArrayList<ArrayList<Long>> full)
    {
        long max = 0;
        for(ArrayList<Long> row: full)
        {
            for(int i = 0; i < row.size() - 3; i++)
            {
                long temp = row.get(i) * row.get(i+1) * row.get(i+2) * row.get(i+3);
                if(temp > max)
                    max = temp;
            }
        }
        return max;
    }

    private long maxVertical(ArrayList<ArrayList<Long>> full)
    {
        long max = 0;
        for(int i = 0; i < full.get(0).size(); i++)
        {
            for(int j = 0; j < full.size() - 3; j++)
            {
                long temp = full.get(j).get(i) * full.get(j+1).get(i) * full.get(j+2).get(i) * full.get(j+3).get(i);
                if(temp > max)
                    max = temp;
            }
        }
        return max;
    }

    private long maxDiagDown(ArrayList<ArrayList<Long>> full)
    {
        long max = 0;
        for(int i = 0; i < full.get(0).size() - 3; i++)
        {
            for(int j = 0; j < full.size() - 3; j++)
            {
                long temp = full.get(j).get(i) * full.get(j+1).get(i+1) * full.get(j+2).get(i+2) * full.get(j+3).get(i+3);
                if(temp > max)
                    max = temp;
            }
        }
        return max;
    }

    private long maxDiagUp(ArrayList<ArrayList<Long>> full)
    {
        long max = 0;
        for(int i = 3; i < full.get(0).size(); i++)
        {
            for(int j = 0; j < full.size() - 3; j++)
            {
                long temp = full.get(j).get(i) * full.get(j+1).get(i-1) * full.get(j+2).get(i-2) * full.get(j+3).get(i-3);
                if(temp > max)
                    max = temp;
            }
        }
        return max;
    }

    private ArrayList<ArrayList<String>> read_input() throws IOException {
        ArrayList<ArrayList<String>> ret = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        IntStream.range(0, 20).forEach(tItr -> {
            try {
                ArrayList<String> temp = new ArrayList<>();
                temp.addAll(List.of(bufferedReader.readLine().trim().split(" ")));
                ret.add(temp);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bufferedReader.close();
        return ret;
    }

    public static void main(String[] args) throws IOException {
        P011 p = new P011();
        ArrayList<ArrayList<String>> inputs = p.read_input();
        System.out.println(p.answer(inputs));
    }
}
