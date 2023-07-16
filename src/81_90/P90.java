import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

public class P90 {
    private class dice implements Collection
    {
        private TreeSet<Integer> myVal;
        public dice()
        {
            myVal = new TreeSet<>();
        }

        @Override
        public String toString()
        {
            return myVal.toString();
        }

        @Override
        public int size() {
            return myVal.size();
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return myVal.contains(o);
        }

        @Override
        public Iterator iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public boolean add(Object o) {
            return myVal.add((Integer) o);
        }

        @Override
        public boolean remove(Object o) {
            return myVal.remove(o);
        }

        @Override
        public boolean addAll(Collection c) {
            return myVal.addAll(c);
        }

        @Override
        public void clear() {}

        @Override
        public boolean retainAll(Collection c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection c) {
            return false;
        }

        @Override
        public boolean containsAll(Collection c) {
            return myVal.equals(c);
        }

        @Override
        public Object[] toArray(Object[] a) {
            return new Object[0];
        }

        @Override
        public int hashCode()
        {
            return myVal.hashCode();
        }
    }

    public int myCount = 0;
    private dice aDie = new dice();
    private HashSet<dice> allDice = new HashSet<>();
    private HashSet<String> combos = new HashSet<>();

    public void cubeA(dice base, TreeSet<Integer> dont)
    {
        dice current = new dice();
        current.addAll(base.myVal);
        TreeSet<Integer> cd = new TreeSet<>();
        cd.addAll(dont);
        for(int i = 0; i<= 9; i++)
        {
            if(cd.contains(i)||current.contains(i)) continue;
            cd.add(i);
            current.add(i);
            cubeA(current, cd);
            current.remove(i);
        }
        if(current.myVal.size()==6)
        {
            myCount++;
            allDice.add(current);
        }
    }

    public boolean valid(dice a, dice b)
    {
        boolean one = (a.contains(0) && b.contains(1)) || (a.contains(1) && b.contains(0));
        boolean four = (a.contains(0) && b.contains(4)) || (a.contains(4) && b.contains(0));
        boolean nine = (a.contains(0) && (b.contains(6) || b.contains(9))) || (b.contains(0) && (a.contains(6) || a.contains(9)));
        boolean sixteen = (a.contains(1) && (b.contains(6) || b.contains(9))) || (b.contains(1) && (a.contains(6) || a.contains(9)));
        boolean twentyfive = (a.contains(2) && b.contains(5)) || (a.contains(5) && b.contains(2));
        boolean thirtysix = (a.contains(3) && (b.contains(6) || b.contains(9))) || (b.contains(3) && (a.contains(6) || a.contains(9)));
        boolean fourtynine = (a.contains(4) && (b.contains(6) || b.contains(9))) || (b.contains(4) && (a.contains(6) || a.contains(9)));
        boolean eightyone = (a.contains(8) && b.contains(1)) || (a.contains(1) && b.contains(8));
        return one && four && nine && sixteen && twentyfive && thirtysix && fourtynine && eightyone;
    }

    public String toget(dice a, dice b)
    {
        dice first = new dice();
        dice second = new dice();
        for(int i = 0; i<=9; i++)
        {
            if(a.contains(i)&&!b.contains(i))
            {
                first = a;
                second = b;
                break;
            }
            else if(b.contains(i)&&!a.contains(i))
            {
                first = b;
                second = a;
                break;
            }
            if(i==9)
            {
                System.out.println("same");
                first = a;
                second = b;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(Integer i : first.myVal)
        {
            sb.append(i);
        }
        for(Integer i : second.myVal)
        {
            sb.append(i);
        }
        return sb.toString();
    }

    public int count()
    {
        TreeSet<Integer> dont = new TreeSet<>();
        cubeA(aDie, dont);
        System.out.println(allDice.size());
        for(dice d : allDice)
        {
            for (dice x : allDice)
            {
                if(valid(d,x))
                {
                    combos.add(toget(d,x));
                }
            }
        }
        return combos.size();
    }

    public static void main(String[] args)
    {
        P90 c = new P90();
        System.out.println(c.count());
    }
}