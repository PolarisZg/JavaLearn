// 需要先启动服务器程序，再启动客户端( 此程序 )
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;


public class ChatClient {
    public static void main(String[] args) {
        new ChatClientGui();
    }
}

class ChatClientGui {
    JFrame jFrame;
    JTextArea jTextAreaScreen;
    JTextArea jTextAreaInput;
    JButton jButtonSend;
    JPanel jPanelInput;

    Socket socket;
    private static final int SERVER_PORT = 9001;

    ChatClientGui() {
        jFrame = new JFrame("聊天_客户端");
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
            jTextAreaScreen.append("客户端 : -->" + jTextAreaInput.getText() + "\r\n");
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
            InetAddress inetAddress = InetAddress.getByName("localhost");
            socket = new Socket(inetAddress, SERVER_PORT);
            jTextAreaInput.append("客户端已启动" + "\r\n");
            byte[] b = new byte[1024];
            int length;
            while ((length = socket.getInputStream().read(b)) != -1) {
                jTextAreaScreen.append("服务器 : -->" + new String(b, 0, length) + "\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
