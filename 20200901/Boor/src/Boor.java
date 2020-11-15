import java.util.Scanner;

public class Boor {
    public static void main(String [] args){
        while(true){
            double x = (new Scanner(System.in)).nextDouble();
            double y = (new Scanner(System.in)).nextDouble();
            double answer = Math.atan(( -0.062 * y * y * x ) / (Math.PI * (x * x - y * y)));
            System.out.println("answer = " + Math.toDegrees(answer) );
        }
    }
}
