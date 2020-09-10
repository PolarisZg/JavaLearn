import java.util.Scanner;
public class Emploee {
    String EmploeeNum;
    String EmploeeName;
    Scanner scanner = new Scanner(System.in);
    Emploee(){
        EmploeeNum = scanner.nextLine();
        EmploeeName = scanner.nextLine();
    }
    void EmploeeOut(){
        System.out.println("The number of emploee is " + EmploeeNum + ". The name is " + EmploeeName + ".");
    }
}

class Manager extends Emploee{
    String DepartmentName;
    Scanner scanner = new Scanner(System.in);
    Manager(){
        DepartmentName = scanner.nextLine();
    }
    void ManagerOut(){
        System.out.println("The number of manager is " + EmploeeNum + ". The name is " + EmploeeName + ". The name of department is " + DepartmentName + ".");
    }
}

class test{
    public static void main(String [] args){
        Emploee e = new Emploee();
        Manager m = new Manager();
        e.EmploeeOut();
        m.ManagerOut();
    }
}