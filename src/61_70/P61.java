import java.lang.reflect.Array;
import java.util.ArrayList;

public class P61 {

    public ArrayList<Integer> generate_triangles()
    {
        ArrayList<Integer> tri = new ArrayList<>();
        int temp;
        int i = 1;
        while(i*(i+1)/2 < 10000)
        {
            temp = i*(i+1)/2;
            if(temp>1000)
            {
                tri.add(temp);
            }
            i++;
        }
        return tri;
    }

    public ArrayList<Integer> generate_squares()
    {
        ArrayList<Integer> squ = new ArrayList<>();
        int temp;
        int i = 1;
        while(i*i < 10000)
        {
            temp = i*i;
            if(temp>1000)
            {
                squ.add(temp);
            }
            i++;
        }
        return squ;
    }

    public ArrayList<Integer> generate_pent()
    {
        ArrayList<Integer> pent = new ArrayList<>();
        int temp;
        int i = 1;
        while(i*(3*i-1)/2 < 10000)
        {
            temp = i*(3*i-1)/2;
            if(temp>1000)
            {
                pent.add(temp);
            }
            i++;
        }
        return pent;
    }

    public ArrayList<Integer> generate_hex()
    {
        ArrayList<Integer> hex = new ArrayList<>();
        int temp;
        int i = 1;
        while(i*(2*i-1) < 10000)
        {
            temp = i*(2*i-1);
            if(temp>1000)
            {
                hex.add(temp);
            }
            i++;
        }
        return hex;
    }

    public ArrayList<Integer> generate_hept()
    {
        ArrayList<Integer> hept = new ArrayList<>();
        int temp;
        int i = 1;
        while(i*(5*i-3)/2 < 10000)
        {
            temp = i*(5*i-3)/2;
            if(temp>1000)
            {
                hept.add(temp);
            }
            i++;
        }
        return hept;
    }

    public ArrayList<Integer> generate_oct()
    {
        ArrayList<Integer> oct = new ArrayList<>();
        int temp;
        int i = 1;
        while(i*(3*i-2) < 10000)
        {
            temp = i*(3*i-2);
            if(temp>1000)
            {
                oct.add(temp);
            }
            i++;
        }
        return oct;
    }

    public static void main(String[] args) {
        P61 p = new P61();
        ArrayList<Integer> tria = p.generate_triangles();
        ArrayList<Integer> squa = p.generate_squares();
        ArrayList<Integer> pent = p.generate_pent();
        ArrayList<Integer> hexa = p.generate_hex();
        ArrayList<Integer> hept = p.generate_hept();
        ArrayList<Integer> octa = p.generate_oct();
        ArrayList<ArrayList<Integer>> options = new ArrayList<>();
        options.add(squa);
        options.add(pent);
        options.add(hexa);
        options.add(hept);
        options.add(octa);

        String temp_a;
        String temp_b;
        String temp_c;
        String temp_d;
        String temp_e;
        String temp_f;

        for (Integer a : tria) {
            temp_a = String.valueOf(a);
            for (int j = 0; j < options.size(); j++) {
                for (int k = 0; k < options.size(); k++) {
                    if (j == k) {
                        continue;
                    }
                    for (int x = 0; x < options.size(); x++) {
                        if (x == j || x == k) {
                            continue;
                        }
                        for (int y = 0; y < options.size(); y++) {
                            if (y == j || y == k || y == x) {
                                continue;
                            }
                            for (int z = 0; z < options.size(); z++) {
                                if (z == j || z == k || z == x || z == y) {
                                    continue;
                                }
                                for (Integer b : options.get(j)) {
                                    temp_b = String.valueOf(b);
                                    if (temp_a.substring(2).equals(temp_b.substring(0, 2))) {
                                        for (Integer c : options.get(k)) {
                                            temp_c = String.valueOf(c);
                                            if (temp_b.substring(2).equals(temp_c.substring(0, 2))) {
                                                for(Integer d: options.get(x))
                                                {
                                                    temp_d = String.valueOf(d);
                                                    if(temp_c.substring(2).equals(temp_d.substring(0,2)))
                                                    {
                                                        for(Integer e: options.get(y))
                                                        {
                                                            temp_e = String.valueOf(e);
                                                            if(temp_d.substring(2).equals(temp_e.substring(0,2)))
                                                            {
                                                                for(Integer f: options.get(z))
                                                                {
                                                                    temp_f = String.valueOf(f);
                                                                    if(temp_e.substring(2).equals(temp_f.substring(0,2)) && temp_f.substring(2).equals(temp_a.substring(0,2)))
                                                                    {
                                                                        System.out.println(temp_a + " " + temp_b + " " + temp_c + " " + temp_d + " " + temp_e + " " + temp_f + " ");
                                                                        System.out.println(a + b + c + d + e + f);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
