public class AnswerTest {
    static Double GetAnswer(String s){
        int Sub = s.lastIndexOf("-");
        int Plus = s.lastIndexOf("+");
        int Dvi = s.lastIndexOf("/");
        int Mul = s.lastIndexOf("*");
        int Dot = s.lastIndexOf(".");

        if(Plus != -1){
            return GetAnswer(s.substring(0,Plus)) + GetAnswer(s.substring(Plus + 1));
        }
        else if(Sub != -1){
            return GetAnswer(s.substring(0,Sub)) - GetAnswer(s.substring(Sub +1));
        }
        else if(Mul != -1){
            return GetAnswer(s.substring(0,Mul)) * GetAnswer(s.substring(Mul + 1));
        }
        else if(Dvi != -1){
            return GetAnswer(s.substring(0,Dvi)) / GetAnswer(s.substring(Dvi + 1));
        }
        else if(Dot != -1){
            return GetAnswer(s.substring(0,Dot)) + GetAnswer(s.substring(Dot + 1)) / (10 * (s.length() - Dot - 1));
        }
        else{
            return Integer.parseInt(s) * 1.0;
        }
    }

    public static void main(String [] args){
        String s = "3.2+3.2";
        //System.out.println(GetAnswer(s));
        System.out.println(Calculate.Answer(s));
    }
}
