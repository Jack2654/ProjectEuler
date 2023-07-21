import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class P019 {
    private final boolean debug = false;
    private final String[] myDays = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    private long answer(long[] start, long[] end)
    {
        long pre_years = start[0] - 1900;   // these lines determine what day of the week the starting year begins with
        long leap_years = leap_years_between(start[0]);
        if(debug)
            System.out.println(leap_years);
        long days = (366 * leap_years) + (365 * (pre_years - leap_years));
        long[] current = new long[]{0, ((days % 7) + 1) % 7};
        if(start[0] == 2000)
            current[1] += 1;
        if(debug)
            System.out.println("me thinks " + String.valueOf(start[0]) + " starts on a " + myDays[(int)current[1]]);

        long total;                   // these lines account for the Sundays the starting year before the starting date
        long adj_month = start[1] - 2;
        if (start[2] > 1)
            adj_month++;
        if ((start[0]%400==0) || ((start[0]%4==0) && (start[0]%100!=0)))
            total = -1 * Sundays(current[1], 1, adj_month)[0];
        else
            total = -1 * Sundays(current[1], 0, adj_month)[0];
        if(debug) {
            System.out.println(adj_month);
            System.out.println(String.valueOf(-total) + " Sundays happened the first year before the start date");}


        long full_years = end[0] - start[0];
        for(long i = 0; i < full_years; i++)
        {
            if(debug)
                System.out.println("Year: " + String.valueOf(start[0] + i) + ", " + total);
            if (((start[0] + i)%400==0) || (((start[0] + i)%4==0) && ((start[0] + i)%100!=0)))
                current = Sundays(current[1], 1, 12);
            else
                current = Sundays(current[1], 0, 12);
            total += current[0];
        }
        if(debug)
            System.out.println("Finished full years");
        if ((end[0]%400==0) || ((end[0]%4==0) && (end[0]%100!=0)))
            current = Sundays(current[1], 1, end[1] - 1);
        else
            current = Sundays(current[1], 0, end[1] - 1);
        total += current[0];

        return total;
    }

    public long leap_years_between(long end)
    {
        if(end == 1900)
            return 0;
        long current = end;
        long leap_years = 0;
        while(current >= 40000000000L)
        {
            leap_years += 9700000000L;
            current -= 40000000000L;
        }
        while(current >= 4000000000L)
        {
            leap_years += 970000000L;
            current -= 4000000000L;
        }
        while(current >= 400000000)
        {
            leap_years += 97000000;
            current -= 400000000;
        }
        while(current >= 40000000)
        {
            leap_years += 9700000;
            current -= 40000000;
        }
        while(current >= 4000000)
        {
            leap_years += 970000;
            current -= 4000000;
        }
        while(current >= 400000)
        {
            leap_years += 97000;
            current -= 400000;
        }
        while(current >= 40000)
        {
            leap_years += 9700;
            current -= 40000;
        }
        while(current >= 4000)
        {
            leap_years += 970;
            current -= 4000;
        }
        while(current >= 400)
        {
            leap_years += 97;
            current -= 400;
        }
        if(current == 0)
            leap_years--;

        for(long i = 0; i < current; i++)
        {
            if (((i%4 == 0) && (i%100 != 0)) || i == 0)
            {
                leap_years++;
            }
        }
        return leap_years - (5 * 97 - 24);
    }

    public long[] Sundays(long n, int leap_days, long month)
    {
        long count = 0;
        long day = n;
        String[] months = new String[]{"January", "February", "March", "April", "May", "June", "July", "August",
                "September", "October", "November", "December"};
        int[] month_days = new int[]{31, 28 + leap_days, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if(month == -1)
            return new long[]{count, day};
        if(day == 0)
        {
            count++;
            if(debug)
                System.out.println("Sunday start in January");
        }
        for(long i = 0; i < month; i++)
        {
            day += month_days[(int) i];
            if(i == 11)
                break;
            day %= 7;
            if(day == 0)
            {
                count++;
                if(debug)
                    System.out.println("Sunday start in " + months[(int)i + 1]);
            }
        }
        return new long[]{count, day % 7};
    }

    private ArrayList<String[]> read_input() throws IOException {
        ArrayList<String[]> ret = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bufferedReader.readLine().trim());
        IntStream.range(0, 2*t).forEach(tItr -> {
            try {
                ret.add(bufferedReader.readLine().trim().split(" "));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bufferedReader.close();
        return ret;
    }

    public static void main(String[] args) throws IOException {
        P019 p = new P019();
        ArrayList<String[]> inputs = p.read_input();
        for(int i = 0; i < inputs.size() - 1; i+=2)
        {
            String[] start = inputs.get(i);
            String[] end = inputs.get(i+1);
            if(start.length != end.length || start.length != 3)
                throw new IOException();
            long[] start_int = Arrays.stream(start).mapToLong(Long::parseLong).toArray();
            long[] end_int = Arrays.stream(end).mapToLong(Long::parseLong).toArray();
            System.out.println(p.answer(start_int, end_int));
        }
    }
}
