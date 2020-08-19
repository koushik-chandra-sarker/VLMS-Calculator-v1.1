package VLSM;

public class powerCalculator {
    int n;

    public powerCalculator(int n) {
        this.n = n;
    }

    public int Calculator(){

            int count = -1;

            while(n!=0){
                n=n/2;
                count++;
            }
            return count;

    }
}
