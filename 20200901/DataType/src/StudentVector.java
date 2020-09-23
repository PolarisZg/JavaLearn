import java.util.Scanner;
import java.util.Vector;
class Students{
    String Name;
    String Sex;
    String StudentNumber;
    Students(String name,String sex,String studentNumber){
        Name = name;
        Sex = sex;
        StudentNumber = studentNumber;
    }
    Students(){}
}
public class StudentVector {
    public static void main(String [] args){
        Vector studentV = new Vector(4);
        Scanner scanner = new Scanner(System.in);
        for(int i = 0;i < 4;i++){
            System.out.printf("输入第 %d 个学生的姓名：",i + 1);
            String name = scanner.nextLine();
            System.out.printf("输入第 %d 个学生的性别：",i + 1);
            String sex = scanner.nextLine();
            System.out.printf("输入第 %d 个学生的学号：",i + 1);
            String studentNumber = scanner.nextLine();
            Students student = new Students(name,sex,studentNumber);
            studentV.addElement(student);
        }
        for(int i = 0;i < 4;i++){
            Students student =(Students) studentV.elementAt(i);
            System.out.println(student.Name + " " + student.Sex + " " + student.StudentNumber);
        }
    }
}
