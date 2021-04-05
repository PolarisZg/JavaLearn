public class Solution {
    public static void main(String [] args){
        BenJin bj = new BenJin( 20,20 , 0.0035);
        bj.reply();
    }
}

abstract class Loan{
    double principal;
    int yearNum;
    double[] repayment;
    public double Repay(int i){
        return repayment[i];
    }
    public void reply(){
        for(int i = 0 ; i < yearNum ; i++){
            System.out.println(repayment[i]);
        }
    }
}

class BenJin extends Loan{
    BenJin(double Principal , int Num , double rate){
        principal = Principal;
        yearNum = Num;
        repayment = new double[Num];
        double average = principal / yearNum;
        double already = 0.0;
        for(int i = 0 ; i < Num ; i++){
            repayment[i] = (principal - already) * rate + average;
            already++;
        }
    }

    @Override
    public double Repay(int i) {
        return super.Repay(i);
    }

    @Override
    public void reply() {
        super.reply();
    }
}

class BenXi extends Loan{
    BenXi(double Principal , int Num , double rate){
        principal = Principal;
        yearNum = Num;
        repayment = new double[Num];

    }
}

