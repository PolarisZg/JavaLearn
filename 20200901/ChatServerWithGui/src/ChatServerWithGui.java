//需要先启动服务器程序( 此程序 ) ， 再启动客户端
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServerWithGui {
    public static void main(String[] args) {
        new ChatServerWithGui_Gui();
    }
}

class ChatServerWithGui_Gui {
    JFrame jFrame;
    JTextArea jTextAreaScreen;
    JTextArea jTextAreaInput;
    JButton jButtonSend;
    JPanel jPanelInput;

    ServerSocket serverSocket;
    Socket socket;
    private static final int SERVER_PORT = 9001;

    ChatServerWithGui_Gui() {
        jFrame = new JFrame("聊天_服务器");
        jFrame.add(new JScrollPane(jTextAreaScreen = new JTextArea()));
        jTextAreaScreen.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {

                if (e.getKeyChar() != KeyEvent.CHAR_UNDEFINED) {
                    e.consume();
                }
            }
        });
        jPanelInput = new JPanel(new BorderLayout());
        jPanelInput.add(new JScrollPane(jTextAreaInput = new JTextArea()));
        jPanelInput.add(jButtonSend = new JButton("发送"), BorderLayout.EAST);
        jButtonSend.addActionListener(e -> {
            jTextAreaScreen.append("服务器 : -->" + jTextAreaInput.getText() + "\r\n");
            try {
                socket.getOutputStream().write(jTextAreaInput.getText().getBytes());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } finally {
                jTextAreaInput.setText("");
            }
        });
        jFrame.add(jPanelInput, BorderLayout.SOUTH);

        jFrame.setSize(400, 400);
        jFrame.setDefaultCloseOperation(3);
        jFrame.setVisible(true);

        try {
            serverSocket = new ServerSocket(SERVER_PORT);
            jTextAreaScreen.append("服务器已启动，等待客户端" + "\r\n");
            socket = serverSocket.accept();
            jTextAreaScreen.append("已连接" + "\r\n");
            byte[] b = new byte[1024];
            int length;
            while ((length = socket.getInputStream().read(b)) != -1){
                jTextAreaScreen.append("客户端 : -->" + new String(b,0,length) + "\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
