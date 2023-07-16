import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class P62 {
    public String num_map(long num) {
        int[] ret = new int[10];
        for (int i = 0; i < Array.getLength(ret); i++) {
            ret[i] = String.valueOf(num).split(String.valueOf(i), -1).length - 1;
        }
        String res = "";
        for(Integer i: ret)
        {
            res = res + "" + i;
        }
        return res;
    }

    public int dig(double number)
    {
        int length = 1;
        if (number >= 100000000) {
            length += 8;
            number /= 100000000;
        }
        if (number >= 10000) {
            length += 4;
            number /= 10000;
        }
        if (number >= 100) {
            length += 2;
            number /= 100;
        }
        if (number >= 10) {
            length += 1;
        }
        return length;
    }

    public static void main(String args[]) {
        P62 p = new P62();
        int digits = 2;
        double num = 1;
        double val = 1;
        boolean found = false;
        String temp;
        HashMap<String, Integer> count = new HashMap<>();
        ArrayList<Long> possible = new ArrayList<>();

        while(!found)
        {
            possible = new ArrayList<>();
            count = new HashMap<>();
            while (p.dig(val) < digits) {
                val = Math.pow(num, 3.0);
                temp = p.num_map((long) val);
                if(count.containsKey(temp))
                {
                    count.put(temp, count.get(temp) + 1);
                }
                else
                {
                    count.put(temp, 1);
                }
                if(temp.equals("1112112111"))
                    System.out.println((long)val);
                if(temp.equals("1112121111"))
                    System.out.println((long)val);
                num++;
            }
            for(String a: count.keySet())
            {
                if(count.get(a) == 5)
                {
                    System.out.println(a + ", " + count.get(a));
                    found = true;
                }
            }
            digits++;
        }
    }
}
