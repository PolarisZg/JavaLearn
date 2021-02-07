package user2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;

public class New extends JFrame {//��¼������
    JPanel contentPane;
    //*****************
    String server;//��������
    int serport;//�˿�
    private Socket socket;
    private BufferedReader in;//���������
    private PrintWriter out;
    //***********//�������
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
        jLabel1.setText("�����������Ϣ��ӭʹ��!");
        jLabel1.setBounds(new Rectangle(5, 7, 403, 8));
        jLabel2.setText("���QQ:");
        jLabel2.setBounds(new Rectangle(7, 66, 58, 18));
        jicq.setBounds(new Rectangle(68, 65, 97, 22));
        jLabel3.setText("�������:");
        jLabel3.setBounds(new Rectangle(173, 66, 67, 18));
        password.setBounds(new Rectangle(237, 63, 94, 22));
        jPanel2.setBounds(new Rectangle(8, 154, 347, 151));
        jPanel2.setLayout(null);
        login.setText("��¼");
        login.setBounds(new Rectangle(5, 27, 79, 29));
        login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                login_mouseClicked(e);
            }
        });
        newuser.setText("ע��");
        newuser.setBounds(new Rectangle(118, 28, 79, 29));
        newuser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                newuser_mouseClicked(e);
            }
        });
        quit.setText("�˳�");
        quit.setBounds(new Rectangle(228, 26, 79, 29));
        quit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                quit_mouseClicked(e);
            }
        });
        jLabel6.setText("������");
        jLabel6.setBounds(new Rectangle(20, 132, 41, 18));
        servername.setText("localhost");
        servername.setBounds(new Rectangle(73, 135, 102, 22));
        jLabel7.setText("�˿�");
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

    public static void main(String[] args) {//������
        New f = new New();
        f.setVisible(true);

    }

    void login_mouseClicked(MouseEvent e) {//��¼��Ť

        try {
            Socket socket = new Socket(InetAddress.getByName(server), serport);//���ӷ�����

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())), true);
            out.println("login");//���߷�������Ҫ��¼
            out.println(jicq.getText());
            out.println(password.getPassword());

            String str = " ";
            //do{
            str = in.readLine().trim();//�ӷ�������ȡ��Ϣ
            //���ʧ�ܾ͸��߳���
            if (str.equals("false"))
                JOptionPane.showMessageDialog(this, "�Բ��𣬳�����:-(", "ok", JOptionPane.INFORMATION_MESSAGE);
            else {//����ɹ��ʹ�������
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

    void newuser_mouseClicked(MouseEvent e) {//�½��û���Ŧ
        this.dispose();
        JDialog d = new Register(server, serport);//���½�����
        d.pack();
        d.setLocationRelativeTo(this);
        d.setSize(400, 400);
        d.show();
    }

    void quit_mouseClicked(MouseEvent e) {//�رհ�Ť
        this.dispose();
        System.exit(0);
    }


}