/*
 @PolarisZg
 先启动服务器 , 再启动此程序( 客户端 )
*/

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ChatClientWithoutGui {

    Socket socket;
    private static final int SERVER_PORT = 9001;

    ChatClientWithoutGui() {
        try {
            InetAddress inetAddress = InetAddress.getByName("localhost");
            socket = new Socket(inetAddress, SERVER_PORT);
            System.out.println("客户端已启动");
            System.out.println("输入文字 , 按回车发送");
            Thread threadIn = new Thread(() -> {
                byte[] b = new byte[1024];
                int length = 0;
                while (true) {
                    try {
                        if ((length = socket.getInputStream().read(b)) == -1) break;
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("服务器停止运行 , 请先启动服务器 \n 本程序已停止");
                        System.exit(0);
                    }
                    System.out.println("服务器 : -->" + new String(b, 0, length));
                }
            });
            Thread threadOut = new Thread(() -> {
                while(true){
                    try {
                        socket.getOutputStream().write(new Scanner(System.in).nextLine().getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            threadOut.start();
            threadIn.start();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务器停止运行 , 请先启动服务器 \n 本程序已停止");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new ChatClientWithoutGui();
    }
}
