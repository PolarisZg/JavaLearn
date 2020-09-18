import java.util.Scanner;
public class YangHui {
    int Factorial(int n){
        if(n == 1 || n == 0)
            return 1;
        else
            return n*Factorial(n - 1);
    }
    int C(int n,int m){
        return Factorial(n)/(Factorial(m)*Factorial(n - m));
    }

}
