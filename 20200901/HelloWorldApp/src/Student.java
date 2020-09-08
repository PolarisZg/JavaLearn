import java.util.Scanner;
public class Student {
    String StudentNum;
    String StudentName;
    int StudentBrithYear;
    int StudentMathGrade;
    int StudentEnglishGrade;
    int StudentComputerGrade;
    Scanner scanner = new Scanner(System.in);

    Student(){
        StudentNum = scanner.nextLine();
        StudentName = scanner.nextLine();
        StudentBrithYear = scanner.nextInt();
        StudentMathGrade = scanner.nextInt();
        StudentEnglishGrade = scanner.nextInt();
        StudentComputerGrade = scanner.nextInt();
    }

    int sum(Student s){
        return s.StudentComputerGrade + s.StudentEnglishGrade + s.StudentMathGrade;
    }

    int age(Student s){
        return 2020 - s.StudentBrithYear;
    }

    void print(Student s){
        System.out.println("学号：" + s.StudentNum);
        System.out.println("姓名：" + s.StudentName);
        System.out.println("出生年份：" + s.StudentBrithYear);
        System.out.println("数学成绩：" + s.StudentMathGrade);
        System.out.println("英语成绩：" + s.StudentEnglishGrade);
        System.out.println("计算机成绩：" + s.StudentComputerGrade);
    }

    public static void main(String [] args){
        Student s = new Student();
        s.sum(s);
        s.age(s);
        s.print(s);
    }

}
