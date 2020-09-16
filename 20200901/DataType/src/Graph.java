import java.util.Scanner;
interface shape {
    double Area();
    double Perimeter();
}

class Circle implements shape{
    double radius;
    public Circle(double r) {
        radius = r;
    }
    public double Area() {
        return 3.1415926*radius*radius;
    }
    public double Perimeter() {
        return 2*3.1415926*radius;
    }
}

class Rectangle implements shape{
    double width;
    double heigth;
    public Rectangle(double w,double h) {
        width = w;
        heigth = h;
    }
    public double Area() {
        return width * heigth;
    }
    public double Perimeter() {
        return 2 *(width + heigth);
    }
}

class Square extends Rectangle{
    public Square(double width) {
        super(width,width);
    }
}

public class Graph {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int i;
        System.out.println("1：圆；2：长方形；3：正方形");
        i = scanner.nextInt();
        switch (i) {
            case 1:
                System.out.print("半径：");
                Circle cricle = new Circle(scanner.nextDouble());
                System.out.printf("周长 = %.3f, 面积 = %.3f",cricle.Perimeter(),cricle.Area());
                break;
            case 2:
                System.out.print("长：");
                double h = scanner.nextDouble();
                System.out.print("宽：");
                double w = scanner.nextDouble();
                Rectangle rectangle = new Rectangle(w, h);
                System.out.printf("周长 = %.3f, 面积 = %.3f",rectangle.Perimeter(),rectangle.Area());
                break;
            case 3:
                System.out.print("边长：");
                Square square = new Square(scanner.nextDouble());
                System.out.printf("周长 = %.3f, 面积 = %.3f",square.Perimeter(),square.Area());
                break;
            default:
                break;
        }
    }
}