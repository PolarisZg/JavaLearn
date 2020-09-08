import java.util.Scanner;

public class Common {
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int GreatestCommonMeasure = 1;
        int LowestCommonMultiple;
        for(int i = (n<m?n:m) ; i >= 1 ; i--){
            if(n%i == 0 && m%i == 0){
                GreatestCommonMeasure = i ;
                break;
            }
        }
        for(int i = (n>m?n:m) ; ; i++){
            if(i%n == 0 && i%m == 0){
                LowestCommonMultiple = i;
                break;
            }
        }
        System.out.println("最大公约数" + GreatestCommonMeasure);
        System.out.println("最小公倍数" + LowestCommonMultiple);
    }
}
