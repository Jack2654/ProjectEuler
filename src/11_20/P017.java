import java.io.*;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class P017 {
    private String answer(long limit)
    {
        if(limit == 0)
            return "Zero";
        StringBuilder ans = new StringBuilder();
        long remaining = limit;
        int i = 0;
        while(remaining > 0)
        {
            String partial = process(remaining % 1000, i);
            if(!partial.equals(""))
                ans.insert(0, partial);
            remaining /= 1000;
            i++;
        }
        return ans.toString().trim();
    }

    private String process(long value, int num)
    {
        if(value == 0)
            return "";
        if(value < 10)
            return single_digit(value) + num_to_suffix(num);
        if(value < 100)
            return double_digit(value) + num_to_suffix(num);
        return triple_digit(value) + num_to_suffix(num);
    }

    private String single_digit(long value)
    {
        if(value == 9)
            return "Nine ";
        if(value == 8)
            return "Eight ";
        if(value == 7)
            return "Seven ";
        if(value == 6)
            return "Six ";
        if(value == 5)
            return "Five ";
        if(value == 4)
            return "Four ";
        if(value == 3)
            return "Three ";
        if(value == 2)
            return "Two ";
        if(value == 1)
            return "One ";
        return "";
    }

    private String double_digit(long value)
    {
        if(value < 10)
            return single_digit(value);
        if(value == 10)
            return "Ten ";
        if(value == 11)
            return "Eleven ";
        if(value == 12)
            return "Twelve ";
        if(value == 13)
            return "Thirteen ";
        if(value == 14)
            return "Fourteen ";
        if(value == 15)
            return "Fifteen ";
        if(value == 16)
            return "Sixteen ";
        if(value == 17)
            return "Seventeen ";
        if(value == 18)
            return "Eighteen";
        if(value == 19)
            return "Nineteen";
        if(value < 30)
            return "Twenty " + single_digit(value % 10);
        if(value < 40)
            return "Thirty " + single_digit(value % 10);
        if(value < 50)
            return "Forty " + single_digit(value % 10);
        if(value < 60)
            return "Fifty " + single_digit(value % 10);
        if(value < 70)
            return "Sixty " + single_digit(value % 10);
        if(value < 80)
            return "Seventy " + single_digit(value % 10);
        if(value < 90)
            return "Eighty " + single_digit(value % 10);
        if(value < 100)
            return "Ninety " + single_digit(value % 10);
        return "";
    }

    private String triple_digit(long value)
    {
        return single_digit(value / 100) + "Hundred " + double_digit(value % 100);
    }

    private String num_to_suffix(int num)
    {
        if(num == 0)
            return "";
        if(num == 1)
            return "Thousand ";
        if(num == 2)
            return "Million ";
        if(num == 3)
            return "Billion ";
        if(num == 4)
            return "Trillion " ;
        if(num == 5)
            return "Quadrillion ";
        if(num >= 6)
            return "Quintillion ";
        return "";
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
        P017 p = new P017();
        ArrayList<Long> inputs = p.read_input();
        for (Long input : inputs) {
            System.out.println(p.answer(input));
        }
    }
}
