public class vcbTest {
    static double[] LowerFrame(double[] encodeList){
        double sum = 0;
        int length = encodeList.length;
        for (int i = 0 ; i < length ; i++){
            sum = sum + encodeList[i];
        }
        double average = sum / length;
        double sD = 0;
        for (int i = 0 ; i < length ; i++){
            sD = sD + (encodeList[i] - average) * (encodeList[i] - average);
        }
        sD = Math.sqrt(sD / length);

    }
}
