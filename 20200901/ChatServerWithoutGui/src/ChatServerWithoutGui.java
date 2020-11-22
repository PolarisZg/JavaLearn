/*
 @PolarisZg
 先启动此程序( 服务器 ) , 再启动客户端
*/

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

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
            outputStream = socket.getOutputStream();
            inputStream = socket.getInputStream();
            outputStream.write("来自服务器的一句话".getBytes());
            int length;
            byte[] b = new byte[1024];
            length = inputStream.read(b);
            System.out.println("客户端 : -->" + new String(b, 0, length));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ChatServerWithoutGui();
    }
}