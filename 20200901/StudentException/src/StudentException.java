import java.util.*;
public class StudentException {
    public static void main(String [] args){
        int student[][];
        double studentAverage[];
        int m = 0;
        int n = 0;
        System.out.print("m =");
        try {
            m = new Scanner(System.in).nextInt();
        }
        catch (InputMismatchException e){
            System.out.print("请输入整数!");
            System.exit(0);
        }
        System.out.print("n =");
        try {
            n = new Scanner(System.in).nextInt();
        }
        catch (InputMismatchException e){
            System.out.print("请输入整数!");
            System.exit(0);
        }
        student = new int[m][n];
        studentAverage = new double[m];
        for(int i = 0;i < m;i++){
            studentAverage[i] = 0;
            for(int j = 0;j < n;j++){
                System.out.printf("第 %d 个学生的第 %d 个成绩是：",i + 1,j + 1);
                try {
                    student[i][j] = new Scanner(System.in).nextInt();
                    if(student[i][j] > 100 || student[i][j] < 0){
                        throw new Exception();
                    }
                }
                catch (InputMismatchException e){
                    System.out.print("请输入整数!");
                    System.exit(0);
                }
                catch (Exception e){
                    System.out.print("成绩应大于 0 小于 100!");
                    System.exit(0);
                }
                studentAverage[i] += student[i][j] * 1.0;
            }
            studentAverage[i] = studentAverage[i] / n;
        }
        for(int i = 0;i < m;i++){
            System.out.printf("第 %d 个学生的平均成绩为 %.3f\n",i + 1,studentAverage[i]);
        }
    }
}
