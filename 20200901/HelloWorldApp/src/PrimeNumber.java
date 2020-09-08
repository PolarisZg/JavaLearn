public class PrimeNumber {
    public static void main(String [] args){
        int n = 101 ;
        int m = 200 ;
        for(int i = n; i<= m ; i++){
            int j = 2;
            for(; j < i ; j++ ){
                if(i % j == 0)
                    break;
            }
            if(j == i)
                System.out.println(i);
        }
    }
}
