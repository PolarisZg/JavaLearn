class Odd extends Thread{
    @Override
    public void run() {
        for(int i = 0; i < 100 ; i++){
            if(i % 2 == 1){
                System.out.println("ODD : " + i);
            }
        }
    }
}

class Prime implements Runnable{

    @Override
    public void run() {
        for(int i = 0 ; i < 100 ; i++){
            if(isPrime(i)){
                System.out.println("Prime : " + i);
            }
        }
    }
    private boolean isPrime(int n){
        for(int i = 2 ; i < n ; i++){
            if(n % i == 0){
                return false;
            }
        }
        return true;
    }
}
public class ThreadPractice {
    public static void main(String [] args){
        Odd odd = new Odd();
        Prime prime = new Prime();
        Thread thread = new Thread(prime);
        odd.start();
        thread.start();
    }
}
