public class ClubRegistration {
    public static void main(String [] args){
        Registration registration = new Registration();
        Thread A = new Thread(registration);
        A.setName("A");
        A.start();
        Thread B = new Thread(registration);
        B.setName("B");
        B.start();
        Thread C = new Thread(registration);
        C.setName("C");
        C.start();
    }
}
class Registration implements Runnable{

    @Override
    public void run() {
        int i;
        while(( i = Queue.queueOut() ) != -1 ) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String name = Thread.currentThread().getName();
            System.out.println( i + " 同学在 " + name + " 处报名");
        }
    }
}
class Queue{
    static int Head = 1;
    static int Tail = 50;
    synchronized static int queueOut(){
        if(Head <= Tail){
            return Head++;
        }
        return -1;
    }
}