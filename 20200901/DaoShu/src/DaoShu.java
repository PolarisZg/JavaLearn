import java.util.Scanner;
public class DaoShu {
    public static void main(String [] args){
        System.out.printf("输入规则：\n系数 未知数x ^ 指数\n空格不影响，x大小写不影响\n输入示例\n3 * x ^ 5 +x^2-6*X\n");
        String s = new Scanner(System.in).nextLine();
//        System.out.println(s.replace(" ",""));
//        System.out.println(s.length());
        QiuDao(s.replace(" ","").toLowerCase());
    }
    public static void QiuDao(String DanXiangShi){
        int locationPlus = Math.max(DanXiangShi.indexOf('+'),DanXiangShi.indexOf('-'));
//        System.out.println("locationPlus:" + locationPlus);
//        System.out.println("DanXiangShi" + DanXiangShi);
        if(locationPlus == -1 || locationPlus == 0){
            locationPlus = DanXiangShi.length();
            int locationX = DanXiangShi.indexOf('x');
            if(locationX == -1){
                System.out.printf("+ 0");
            }
            else{
                int xiShu;
                if(locationX > 0 && DanXiangShi.charAt(locationX - 1) != '+' && DanXiangShi.charAt(locationX - 1) != '-') {
                    xiShu = Integer.parseInt(DanXiangShi, 0, locationX - 1, 10);
                }
                else
                    xiShu = 1;
//                System.out.println(xiShu);
                int zhiShu = 1;
                if(locationX < DanXiangShi.length() - 1) {
//                    System.out.println(DanXiangShi.substring(locationX + 2,DanXiangShi.length()));
                    zhiShu = Integer.parseInt(DanXiangShi, locationX + 2, DanXiangShi.length(), 10);
                }
                System.out.printf("+ ( %d * x ^ %d ) ",xiShu*zhiShu,zhiShu - 1);
            }
        }
        else{
            QiuDao(DanXiangShi.substring(0,locationPlus));
            QiuDao(DanXiangShi.substring(locationPlus));
        }
    }
}