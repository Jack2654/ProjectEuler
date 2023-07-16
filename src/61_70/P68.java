import java.util.ArrayList;
import java.util.Arrays;

public class P68 {
    private ArrayList<int[]> possible;
    public void possible_combinations(int[] cur, int next)
    {
        if(next == 0)
        {
            if(combination_valid(cur))
                possible.add(Arrays.copyOf(cur, cur.length));
        }
        else if(next == 10)
        {
            for(int i: new int[]{0, 2, 4, 6, 8})
            {
                cur[i] = 10;
                possible_combinations(cur, next - 1);
                cur[i] = 0;
            }
        }
        else
        {
            for (int i = 0; i < 10; i++) {
                if (cur[i] != 0)
                    continue;
                cur[i] = next;
                possible_combinations(cur, next - 1);
                cur[i] = 0;
            }
        }
    }

    public boolean combination_valid(int[] cur)
    {
        if(cur[2] < cur[0] || cur[4] < cur[0] || cur[6] < cur[0] || cur[8] < cur[0])
            return false;
        int s1 = cur[0] + cur[1] + cur[3];
        int s2 = cur[2] + cur[3] + cur[5];
        int s3 = cur[4] + cur[5] + cur[7];
        int s4 = cur[6] + cur[7] + cur[9];
        int s5 = cur[8] + cur[9] + cur[1];
        if(s1 - s2 != 0)
            return false;
        if(s1 - s3 != 0)
            return false;
        if(s1 - s4 != 0)
            return false;
        if(s1 - s5 != 0)
            return false;
        return true;
    }

    public long ret_max(ArrayList<int[]> poss)
    {
        long max = 0;
        long temp_l;
        String temp;
        for(int[] i: poss)
        {
            temp = "" + i[0] + i[1] + i[3] + i[2] + i[3] + i[5] + i[4] + i[5] + i[7] + i[6] + i[7] + i[9] + i[8] + i[9] + i[1];
            temp_l = Long.parseLong(temp);
            if(temp_l > max)
            {
                max = temp_l;
            }
        }
        return max;
    }

    public static void main(String[] args)
    {
        P68 p = new P68();
        p.possible = new ArrayList<>();
        int[] base = new int[10];
        p.possible_combinations(base, 10);
        System.out.println(p.ret_max(p.possible));
    }
    // answer is 6531031914842725
    // could optimize substantially by decreasing combinations checked but not needed here
}
