import java.util.Scanner;
abstract class Shape{
    Shape(){}
    abstract Double Area();
    abstract Double Perimeter();
}

class Circle extends Shape{
    Double Radius;
    Circle(Double r){
        Radius = r;
    }
    Double Area(){
        return 3.14*Radius*Radius;
    }
    Double Perimeter(){
        return 2*3.14*Radius;
    }
}

class Rectangle extends Shape{
    Double Length;
    Double Width;
    Rectangle(Double L,Double W){
        Length = L;
        Width = W;
    }
    Double Area(){
        return Length*Width;
    }
    Double Perimeter(){
        return 2*(Length+Width);
    }
}

class Square extends Rectangle{
    Square(Double L){
        super(L,L);
    }
}

public class GraphCalculate {
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入1为圆,2为长方形,3为正方形:");
        int Choice = scanner.nextInt();
        switch (Choice){
            case 1 :
                System.out.print("输入圆的半径:");
                Circle c = new Circle(scanner.nextDouble());
                System.out.println("圆的周长为" + c.Perimeter() + " 圆的面积为" + c.Area());
                break;
            case 2 :
                System.out.print("输入长方形的长:");
                Double L = scanner.nextDouble();
                System.out.print("输入长方形的宽:");
                Double W = scanner.nextDouble();
                Rectangle r = new Rectangle(L,W);
                System.out.println("长方形的周长为" + r.Perimeter() + " 长方形的面积为" + r.Area());
                break;
            case 3 :
                System.out.print("输入正方形的边长:");
                Square s = new Square(scanner.nextDouble());
                System.out.println("正方形的周长为" + s.Perimeter() + " 正方形的面积为" + s.Area());
                break;
            default:break;
        }
    }
}
