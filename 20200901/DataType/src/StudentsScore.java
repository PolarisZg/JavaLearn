import java.util.Scanner;
public class StudentsScore {
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        int[][] Score;
        Score = new int[4][3];
        for(int i = 0;i < 4;i++){
            System.out.printf("输入第 %d 个学生的成绩\n",i + 1);
            for(int j = 0;j < 3;j++){
                System.out.printf("输入第 %d 门课程的成绩：",j + 1);
                Score[i][j] = scanner.nextInt();
            }
        }
        for(int i = 0;i < 4;i++){
            int ZongFen = 0;
            for(int j = 0;j < 3;j++){
                ZongFen = ZongFen + Score[i][j];
            }
            double PingJun = ZongFen * 1.0 / 3.0;
            System.out.printf("第 %d 个学生的总分为 %d ，平均分为 %.3f\n",i + 1,ZongFen,PingJun);
        }
    }
}
