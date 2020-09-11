import java.util.Scanner;
public class Emploee {
    String EmploeeNum;
    String EmploeeName;
    Emploee(String ENum, String EName){
        EmploeeNum = ENum;
        EmploeeName = EName;
    }
    void Out(){
        System.out.println("The number of emploee is " + EmploeeNum + ". The name is " + EmploeeName + ".");
    }
}

class Manager extends Emploee{
    String DepartmentName;
    Manager(String MNum, String MName, String DName){
        super(MNum,MName);
        DepartmentName = DName;
    }
    void Out(){
        System.out.println("The number of manager is " + EmploeeNum + ". The name is " + EmploeeName + ". The name of department is " + DepartmentName + ".");
    }
}

class test{
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        Emploee e = new Emploee(scanner.nextLine(),scanner.nextLine());
        Manager m = new Manager(scanner.nextLine(),scanner.nextLine(),scanner.nextLine());
        e.Out();
        m.Out();
    }
}