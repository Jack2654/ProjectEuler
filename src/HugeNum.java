public class HugeNum {
    public String myVal;
    public int[] myInt;
    private int myFirst;
    public HugeNum(String s)
    {
        myVal = s;
        myInt = new int[s.length() * 2];
        myFirst = myInt.length - 1 - s.length();
        String[] temp = s.split("");
        int j = myInt.length - 1;
        for(int i = temp.length - 1; i>=0; i--)
        {
            myInt[j] = Integer.parseInt(temp[i]);
            j--;
        }
    }
    public HugeNum(String s, int length)
    {
        myVal = s;
        myInt = new int[length];
        myFirst = myInt.length - 1 - s.length();
        String[] temp = s.split("");
        int j = myInt.length - 1;
        for(int i = temp.length - 1; i>=0; i--)
        {
            myInt[j] = Integer.parseInt(temp[i]);
            j--;
        }
    }
    public void updateVal()
    {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for(int i = 0; i<myInt.length;i++)
        {
            if(myInt[i]!=0)
            {
                index = i;
                break;
            }
        }
        myFirst = index;
        for(int i = index; i<myInt.length;i++)
        {
            sb.append(myInt[i]);
        }
        myVal = sb.toString();
    }
    public void simplify()
    {
        boolean change = false;
        while(true)
        {
            change = false;
            for (int i = 0; i < myInt.length; i++) {
                if(myInt[i] > 999)
                {
                    myInt[i - 3] += (myInt[i] - myInt[i] % 1000) / 1000;
                    myInt[i - 2] += (myInt[i]%1000 - myInt[i] % 100) / 100;
                    myInt[i - 1] += (myInt[i] % 100 - myInt[i] % 10) / 10;
                    myInt[i] = myInt[i] % 10;
                    change = true;
                }
                else if (myInt[i] > 99)
                {
                    myInt[i - 2] += (myInt[i] - myInt[i] % 100) / 100;
                    myInt[i - 1] += (myInt[i] % 100 - myInt[i] % 10) / 10;
                    myInt[i] = myInt[i] % 10;
                    change = true;
                }
                else if (myInt[i] > 9)
                {
                    myInt[i - 1] += (myInt[i] - myInt[i] % 10) / 10;
                    myInt[i] = myInt[i] % 10;
                    change = true;
                }
            }
            if(!change) break;
        }
    }
    public void multiply(int factor)
    {
        for(int i = 0; i<myInt.length;i++)
        {
            myInt[i] *= factor;
        }
        simplify();
        updateVal();
    }

    //NOTE: division is not implemented

    public void divide(int factor)
    {
        for(int i = myFirst; i<myInt.length; i++)
        {
            if(myInt[i]%factor==0) myInt[i] /= factor;

        }
    }
    public void power(int factor)
    {
        int base = Integer.parseInt(myVal);
        for(int i = 0; i<factor-1; i++)
        {
            multiply(base);
        }
    }
    public void add(HugeNum h)
    {
        int j = this.myInt.length-1;
        for(int i = h.myInt.length-1; i>=0; i--)
        {
            this.myInt[j] += h.myInt[i];
            j--;
        }
        simplify();
        updateVal();
    }
}
