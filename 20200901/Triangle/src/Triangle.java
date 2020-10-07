import java.util.Scanner;
class TriangleException extends Exception{
    public TriangleException(String s){
        super(s);
    }
}
public class Triangle {
    public static void main(String [] args){
        while(true){
            try{
                System.out.println("输入三边长 : ");
                int a = new Scanner(System.in).nextInt();
                int b = new Scanner(System.in).nextInt();
                int c = new Scanner(System.in).nextInt();
                if(a + b <= c || a + c <= b || c + b <= a){
                    throw new TriangleException("无法构成三角形 ！ , 请继续输入");
                }
                else{
                    double p = (a + b + c) *1.0 / 2.0;
                    double s = Math.sqrt(p * (p - a) * (p - b) * (p - c));
                    System.out.printf("面积为 %.3f",s);
                    break;
                }
            }
            catch (TriangleException t){
                System.out.println(t.getMessage());
            }
        }
    }
}
