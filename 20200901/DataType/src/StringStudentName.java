import java.util.Scanner;
class Student{
    String Name;
    String Sex;
    int Age;
    Student(String name,String sex,int age){
        Name = name;
        Sex = sex;
        Age = age;
    }
}
public class StringStudentName {
    public  static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        System.out.println(s);
//        System.out.println(s.substring(0,s.indexOf(" ")) + "||" + s.substring(s.indexOf(" ") + 1,s.lastIndexOf(" ")) + "||" + s.substring(s.lastIndexOf(" "),s.length() - 1));
        Student student = new Student(s.substring(0,s.indexOf(" ")),s.substring(s.indexOf(" ") + 1,s.lastIndexOf(" ")),Integer.parseInt(s.substring(s.lastIndexOf(" ") + 1,s.length())));
        System.out.println("学生姓名：" + student.Name);
        System.out.println("学生性别：" + student.Sex);
        System.out.println("学生年龄：" + student.Age);
    }
}
