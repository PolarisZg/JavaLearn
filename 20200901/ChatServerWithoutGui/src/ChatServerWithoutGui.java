/*
 @PolarisZg
 先启动此程序( 服务器 ) , 再启动客户端
*/

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatServerWithoutGui {

    ServerSocket serverSocket;
    Socket socket;
    InputStream inputStream;
    OutputStream outputStream;
    private static final int SERVER_PORT = 9001;

    ChatServerWithoutGui() {
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("服务器已启动，等待客户端");
            socket = serverSocket.accept();
            System.out.println("已连接");
            System.out.println("输入文字 , 按回车发送");
            outputStream = socket.getOutputStream();
            inputStream = socket.getInputStream();
            Thread threadIn = new Thread(() -> {
                int length = 0;
                byte[] b = new byte[1024];
                while (true) {
                    try {
                        if ((length = inputStream.read(b)) == -1) break;
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("客户端停止运行 , 请启动客户端 \n 本程序已停止");
                        System.exit(0);
                    }
                    System.out.println("客户端 : -->" + new String(b, 0, length));
                }
            });
            Thread threadOut = new Thread(() -> {
                while (true){
                    try {
                        outputStream.write(new Scanner(System.in).nextLine().getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            threadIn.start();
            threadOut.start();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("客户端停止运行 , 请启动客户端");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new ChatServerWithoutGui();
    }
}