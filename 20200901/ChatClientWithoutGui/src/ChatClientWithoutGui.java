/*
 @PolarisZg
 先启动服务器 , 再启动此程序( 客户端 )
*/

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ChatClientWithoutGui {

    Socket socket;
    private static final int SERVER_PORT = 9001;

    ChatClientWithoutGui() {
        try {
            InetAddress inetAddress = InetAddress.getByName("localhost");
            socket = new Socket(inetAddress, SERVER_PORT);
            System.out.println("客户端已启动");
            byte[] b = new byte[1024];
            int length;
            length = socket.getInputStream().read(b);
            System.out.println("服务器 : -->" + new String(b, 0, length));
            socket.getOutputStream().write("这是来自客户端的信息".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ChatClientWithoutGui();
    }
}
