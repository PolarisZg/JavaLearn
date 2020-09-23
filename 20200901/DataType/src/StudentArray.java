import java.util.Scanner;
class StudentInformation{
    String Name;
    String Sex;
    String StudentNumber;
    StudentInformation(String name,String sex,String studentNumber){
        Name = name;
        Sex = sex;
        StudentNumber = studentNumber;
    }
    StudentInformation(){}
}
public class StudentArray {
    public static void main(String [] args){
        StudentInformation[] student = new StudentInformation[3];
        Scanner scanner = new Scanner(System.in);
        for(int i = 0;i < student.length;i++){
            System.out.printf("输入第 %d 个学生的姓名：",i + 1);
            String name = scanner.nextLine();
            System.out.printf("输入第 %d 个学生的性别：",i + 1);
            String sex = scanner.nextLine();
            System.out.printf("输入第 %d 个学生的学号：",i + 1);
            String studentNumber = scanner.nextLine();
            student[i] = new StudentInformation(name,sex,studentNumber);
        }
        for(int i = 0;i < student.length;i++){
            System.out.println(student[i].Name + " " + student[i].Sex + " " + student[i].StudentNumber);
        }
    }
}
