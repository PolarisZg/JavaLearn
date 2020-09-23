import java.util.Scanner;
public class NumSort {
    public static void main(String [] args){
        int length;
        Scanner scanner = new Scanner(System.in);
        System.out.printf("输入数字个数：");
        length = scanner.nextInt();
        int[] Num;
        Num = new int[length];
        System.out.println("输入数字：");
        for(int i = 0;i < Num.length;i++){
            Num[i] = scanner.nextInt();
        }
        for(int i = 0;i < Num.length - 1;i++){
            for(int j = i + 1;j < Num.length;j++){
                if(Num[i] > Num[j]){
                    int min = Num[j];
                    Num[j] = Num[i];
                    Num[i] = min;
                }
            }
        }
        for(int i = 0;i < Num.length;i++){
            System.out.printf("%d ",Num[i]);
        }
    }
}
