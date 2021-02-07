package user2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;

public class New extends JFrame {//登录窗口类
    JPanel contentPane;
    //*****************
    String server;//服务器名
    int serport;//端口
    private Socket socket;
    private BufferedReader in;//输入输出流
    private PrintWriter out;
    //***********//程序界面
    JPanel jPanel1 = new JPanel();
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    JTextField jicq = new JTextField();
    JLabel jLabel3 = new JLabel();
    JPasswordField password = new JPasswordField();
    JPanel jPanel2 = new JPanel();
    JButton login = new JButton();
    JButton newuser = new JButton();
    JButton quit = new JButton();
    JLabel jLabel6 = new JLabel();
    JTextField servername = new JTextField();
    JLabel jLabel7 = new JLabel();
    JTextField serverport = new JTextField();

    public New() {
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        try {
            jbInit();
            server = servername.getText().toString().trim();
            serport = Integer.parseInt(serverport.getText().trim());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void jbInit() throws Exception {
        contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(null);
        this.setResizable(false);
        this.setSize(new Dimension(344, 245));
        this.setTitle("New JICQ");
        //contentPane.add(text, null);
        jPanel1.setBounds(new Rectangle(2, 3, 348, 110));
        jPanel1.setLayout(null);
        jLabel1.setText("请输入你的信息欢迎使用!");
        jLabel1.setBounds(new Rectangle(5, 7, 403, 8));
        jLabel2.setText("你的QQ:");
        jLabel2.setBounds(new Rectangle(7, 66, 58, 18));
        jicq.setBounds(new Rectangle(68, 65, 97, 22));
        jLabel3.setText("你的密码:");
        jLabel3.setBounds(new Rectangle(173, 66, 67, 18));
        password.setBounds(new Rectangle(237, 63, 94, 22));
        jPanel2.setBounds(new Rectangle(8, 154, 347, 151));
        jPanel2.setLayout(null);
        login.setText("登录");
        login.setBounds(new Rectangle(5, 27, 79, 29));
        login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                login_mouseClicked(e);
            }
        });
        newuser.setText("注册");
        newuser.setBounds(new Rectangle(118, 28, 79, 29));
        newuser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                newuser_mouseClicked(e);
            }
        });
        quit.setText("退出");
        quit.setBounds(new Rectangle(228, 26, 79, 29));
        quit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                quit_mouseClicked(e);
            }
        });
        jLabel6.setText("服务器");
        jLabel6.setBounds(new Rectangle(20, 132, 41, 18));
        servername.setText("localhost");
        servername.setBounds(new Rectangle(73, 135, 102, 22));
        jLabel7.setText("端口");
        jLabel7.setBounds(new Rectangle(191, 137, 41, 18));
        serverport.setText("8080");
        serverport.setBounds(new Rectangle(241, 131, 90, 30));
        contentPane.add(jPanel1, null);
        jPanel1.add(jLabel1, null);
        jPanel1.add(jLabel2, null);
        jPanel1.add(jicq, null);
        jPanel1.add(jLabel3, null);
        jPanel1.add(password, null);
        contentPane.add(jPanel2, null);
        jPanel2.add(login, null);
        jPanel2.add(quit, null);
        jPanel2.add(newuser, null);
        contentPane.add(jLabel6, null);
        contentPane.add(servername, null);
        contentPane.add(jLabel7, null);
        contentPane.add(serverport, null);
    }

    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {//主程序
        New f = new New();
        f.setVisible(true);

    }

    void login_mouseClicked(MouseEvent e) {//登录按扭

        try {
            Socket socket = new Socket(InetAddress.getByName(server), serport);//连接服务器

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())), true);
            out.println("login");//告诉服务器我要登录
            out.println(jicq.getText());
            out.println(password.getPassword());

            String str = " ";
            //do{
            str = in.readLine().trim();//从服务器读取消息
            //如果失败就告诉出错
            if (str.equals("false"))
                JOptionPane.showMessageDialog(this, "对不起，出错了:-(", "ok", JOptionPane.INFORMATION_MESSAGE);
            else {//如果成功就打开主程序
                this.dispose();
                int g = Integer.parseInt(jicq.getText());
                MainWin f2 = new MainWin(g, server, serport);
                f2.setVisible(true);
            }

            //System.out.println("\n");
            //}while(!str.equals("ok"));
        } catch (IOException e1) {
        }
    }

    void newuser_mouseClicked(MouseEvent e) {//新建用户按纽
        this.dispose();
        JDialog d = new Register(server, serport);//打开新建窗口
        d.pack();
        d.setLocationRelativeTo(this);
        d.setSize(400, 400);
        d.show();
    }

    void quit_mouseClicked(MouseEvent e) {//关闭按扭
        this.dispose();
        System.exit(0);
    }


}