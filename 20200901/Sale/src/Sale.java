public class Sale {
    public static void main(String[] arg) {
        Goods goods = new Goods(0);
        Consumer consumer = new Consumer(goods);
        Producer producer = new Producer(goods);
        Thread threadConsumer = new Thread(consumer);
        Thread threadProducer = new Thread(producer);
        threadConsumer.start();
        threadProducer.start();
        byte[] b;
        String s = "abc";
        b = s.getBytes();
    }
}

class Goods {
    int Num;

    Goods(int num) {
        Num = num;
    }
}

class Consumer implements Runnable {
    private final Goods goods;

    Consumer(Goods g) {
        goods = g;
    }

    @Override
    public void run() {
        while (true) {
            if (goods.Num == 0) {
                System.out.println("库存为 0 ");
                synchronized (goods) {
                    try {
                        goods.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                while (goods.Num > 0) {
                    try {
                        Thread.sleep(1000);                     // 销售时间，用来控制程序执行速度
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    goods.Num--;
                    System.out.println("销售 1 个产品，库存 " + goods.Num + " 个");
                }
                synchronized (goods) {
                    goods.notifyAll();
                }
            }
        }
    }
}

class Producer implements Runnable {
    private final Goods goods;

    Producer(Goods g) {
        goods = g;
    }

    @Override
    public void run() {
        while (true) {
            if (goods.Num == 0) {
                int num = 100;                               // 每次的进货量
                goods.Num = goods.Num + num;
                System.out.println("进货 " + num + " 件商品，库存 " + goods.Num + " 个");
                synchronized (goods) {
                    goods.notifyAll();
                }
            } else {
                synchronized (goods) {
                    try {
                        goods.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}