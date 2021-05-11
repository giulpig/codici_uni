public class ES<T extends Comparable<T>> extends S<T> {
    @Override
    public boolean contains(T x) {
        Comparable[] set = new Comparable[size()];
        boolean ret = search2(set, 0, x);
        for(Comparable i: set){
            add((T)i);
        }
        return ret;
    }

    private boolean search2(Comparable[] s, int idx, T x) {
        
        if(isEmpty()){
            return false;
        }

        if(min().equals(x)){
            return true;
        }
        
        s[idx] = min();
        remove(min());

        return search2(s, idx+1, x);
    }
}
