package user2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.Vector;
import java.net.*;
import java.io.*;

class FindFriend2 extends JFrame {//查找好友类
    JLabel jLabel1 = new JLabel();
    JButton find2 = new JButton();
    JButton jButton1 = new JButton();
    JButton jButton2 = new JButton();
    JButton jButton3 = new JButton();
    JList list2;
    ///////////////////以下是好友的呢称，性别等信息
    Vector nickname = new Vector();
    Vector sex = new Vector();
    Vector place = new Vector();
    Vector jicq = new Vector();
    Vector ip = new Vector();
    Vector pic = new Vector();
    Vector status = new Vector();
    Vector emails = new Vector();
    Vector infos = new Vector();
    //以下临时保存好友的呢称，性别等信息
    Vector tmpjicq = new Vector();//jicqid
    Vector tmpname = new Vector();//jicqname
    Vector tmpip = new Vector();//ip
    Vector tmppic = new Vector();//pic info
    Vector tmpstatus = new Vector();//status
    Vector tmpemail = new Vector();
    Vector tmpinfo = new Vector();
    //以下创建网络相关变量
    Socket socket;
    BufferedReader in;
    PrintWriter out;
    int myid;
    String serverhost;
    int servport;
    DatagramPacket sendPacket;
    DatagramSocket sendSocket;
    int sendPort = 5000;
    //////////////////
    JPopupMenu findmenu = new JPopupMenu();
    JMenuItem look = new JMenuItem();
    JMenuItem add = new JMenuItem();

    public FindFriend2(int whoami, String host, int port) {//查找好友类构造函数
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        try {
            serverhost = host;
            servport = port;
            myid = whoami;
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }//以下与服务器连接
        try {
            socket = new Socket(InetAddress.getByName(serverhost), servport);

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())), true);
            sendSocket = new DatagramSocket();
        } catch (IOException e1) {
        }
    }

    private void jbInit() throws Exception {//以下是程序界面
        jLabel1.setText("下面是在线的朋友");
        jLabel1.setBounds(new Rectangle(11, 11, 211, 18));
        this.getContentPane().setLayout(new FlowLayout());
        find2.setText("查找");
        find2.setBounds(new Rectangle(8, 289, 79, 29));
        find2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                find2_mouseClicked(e);
            }
        });
        jButton1.setText("next");
        jButton1.setBounds(new Rectangle(110, 288, 79, 29));
        jButton2.setText("up");
        jButton2.setBounds(new Rectangle(211, 285, 79, 29));
        jButton3.setText("cancel");
        jButton3.setBounds(new Rectangle(317, 289, 79, 29));
        // nickname=new Vector();
        // sex=new Vector();
        // place=new Vector();
        ListModel model = new FindListModel(nickname, sex, place);//列表模型
        ListCellRenderer renderer = new FindListCellRenderer();
        list2 = new JList(model);
        list2.setSize(200, 200);
        list2.setBackground(new Color(255, 255, 210));
        list2.setAlignmentX((float) 1.0);
        list2.setAlignmentY((float) 1.0);
        list2.setCellRenderer(renderer);
        list2.setVisibleRowCount(7);
        list2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                list2_mousePressed(e);
            }
        });
        look.setText("查看资料");
        add.setText("加为好友");
        add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                add_mousePressed(e);
            }
        });
        this.getContentPane().add(jLabel1, null);
        this.getContentPane().add(new JScrollPane(list2));
        this.getContentPane().add(find2, null);
        this.getContentPane().add(jButton1, null);
        this.getContentPane().add(jButton2, null);
        this.getContentPane().add(jButton3, null);
        findmenu.add(look);
        findmenu.add(add);
    }//以下是关闭本窗口

    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            //this.dispose();
            this.hide();
        }
    }

    //以下向服务器发送查找好友请求
    void find2_mouseClicked(MouseEvent e) {
        out.println("find");
        DefaultListModel mm = (DefaultListModel) list2.getModel();
/////////////////find friend info
        try {
            String s = " ";
//从服务器读取好友信息
            do {
                s = in.readLine();
                if (s.equals("over")) break;
                nickname.add(s);
                sex.add(in.readLine());
                place.add(in.readLine());
                ip.add(in.readLine());
                emails.add(in.readLine());
                infos.add(in.readLine());
            } while (!s.equals("over"));
/////////////end  find  info
//read their jicqno
            int theirjicq, picinfo, sta;
            for (int x = 0; x < nickname.size(); x++) {
                theirjicq = Integer.parseInt(in.readLine());
//System.out.println(theirjicq);
                jicq.add(new Integer(theirjicq));
                picinfo = Integer.parseInt(in.readLine());
                pic.add(new Integer(picinfo));
                sta = Integer.parseInt(in.readLine());
//System.out.println(sta);
                status.add(new Integer(sta));
//System.out.println(jicq.get(x));
            }
//在列表中显示
            for (int i = 0; i < nickname.size(); i++) {
                mm.addElement(new Object[]{nickname.get(i), sex.get(i), place.get(i)});
            }//for

        } catch (IOException e4) {
            System.out.println("false");
        }
    }

    //显示查找好友菜单
    void list2_mousePressed(MouseEvent e) {
        findmenu.show(this, e.getX() + 20, e.getY() + 50);

    }

    /////////////add frined
//以下将添加的好友存储在临时矢量
    void add_mousePressed(MouseEvent e) {
//add friend to database
        int dd;
        dd = list2.getSelectedIndex();
        tmpjicq.add(jicq.get(dd));
        tmpname.add(nickname.get(dd));
        tmpip.add(ip.get(dd));
        tmppic.add(pic.get(dd));
        tmpstatus.add(status.get(dd));
        tmpemail.add(emails.get(dd));
        tmpinfo.add(infos.get(dd));
//以下向服务器发送添加好友请求
        out.println("addfriend");
        out.println(jicq.get(dd));
        out.println(myid);
        try { //以下告诉客户将其加为好友
            String whoips;
            String s = "oneaddyou" + myid;
            s.trim();
            System.out.println(s);
            byte[] data = s.getBytes();
            whoips = ip.get(dd).toString().trim();
            sendPacket = new
                    DatagramPacket(data, s.length(), InetAddress.getByName(whoips), sendPort);
            sendSocket.send(sendPacket);

        } catch (IOException e2) {
            e2.printStackTrace();
        }
//}catch(IOException df){};

    }
/////////////add friend end
}

//以下扩展DefaultListModel类建立列表
class FindListModel extends DefaultListModel {
    public FindListModel(Vector nickname, Vector sex, Vector place) {
        for (int i = 0; i < nickname.size(); ++i) {
            addElement(new Object[]{nickname.get(i), sex.get(i), place.get(i)});
        }
    }

    public String getName(Object object) {
        Object[] array = (Object[]) object;
        return (String) array[0];
    }

    public String getSex(Object object) {
        Object[] array = (Object[]) object;
        return (String) array[1];
    }

    public String getPlace(Object object) {
        Object[] array = (Object[]) object;
        return (String) array[2];

    }
}

class FindListCellRenderer extends JLabel implements ListCellRenderer//以下是处理列表渲染
{
    private Border lineBorder = BorderFactory.createLineBorder(Color.red, 2),
            emptyBorder = BorderFactory.createEmptyBorder(2, 2, 2, 2);

    public FindListCellRenderer() {
        setOpaque(true);
    }

    public Component getListCellRendererComponent(JList list, Object value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        FindListModel model = (FindListModel) list.getModel();
        setText(model.getName(value) + "  " + model.getSex(value) + "  " + model.getPlace(value));
        if (isSelected) {
            setForeground(list.getSelectionForeground());
            setBackground(list.getSelectionBackground());
        } else {
            setForeground(list.getForeground());
            setBackground(list.getBackground());
        }
        if (cellHasFocus) setBorder(lineBorder);
        else setBorder(emptyBorder);
        return this;
    }
}

//以下是主程序
public class MainWin extends JFrame implements Runnable {
    JPanel contentPane;
    ///////////////////////////friendinfo
//以下是好友基本信息变量，比如呢称，ip地址等
    Vector friendnames = new Vector();
    int friendnum;//friend number
    private String[] picsonline = new String[]{
            "1.jpg", "3.jpg", "5.jpg", "7.jpg"};
    private String[] picsoffline = new String[]{
            "2.jpg", "4.jpg", "6.jpg", "8.jpg"};
    Vector friendjicq = new Vector();
    Vector udpport = new Vector();
    Vector friendips = new Vector();
    Vector friendemail = new Vector();
    Vector friendinfo = new Vector();
    Vector picno = new Vector();
    Vector status = new Vector();
    // Vector pic=new Vector();
//以下是临时变量保存临时的好友
    Vector tempname = new Vector();
    Vector tempjicq = new Vector();
    Vector tempip = new Vector();
    Vector temppic = new Vector();
    Vector tempstatus = new Vector();
    Vector whoaddmesip = new Vector();//get whoaddme as friend
    Vector tempemail = new Vector();
    Vector tempinfo = new Vector();
    int index;//get list index
    int index3;//get firiend onlineinfo
    int index4;//get message from info
    boolean fromunknow = false;
    //以下建立窗口类
    FindFriend2 findf;
    JDialog hello = new JDialog();
    JDialog OneAddyou = new JDialog();
    JDialog DirectAdd = new JDialog();
    int tempgetjicq;//get the tempgetjicq
    /////////////////////////////friend info
//以下是程序界面的变量
    ImageIcon icon1 = new ImageIcon("");
    ImageIcon icon6 = new ImageIcon("");
    JButton jButton1 = new JButton();
    JButton direct = new JButton();
    JLabel info = new JLabel();
    JDialog about = new JDialog();
    JDialog senddata = new JDialog();
    JDialog getdata = new JDialog();
    JButton ok = new JButton();
    JPopupMenu jPopupMenu1 = new JPopupMenu();
    JMenuItem sendmessage = new JMenuItem();
    JMenuItem getmessage = new JMenuItem();
    JMenuItem lookinfo = new JMenuItem();
    JMenuItem chatrecord = new JMenuItem();
    JLabel name = new JLabel();
    JTextField nametext = new JTextField();
    JLabel icq = new JLabel();
    JTextField icqno = new JTextField();
    JButton send = new JButton();
    JButton cancel = new JButton();
    JTextArea sendtext = new JTextArea();
    JList list;
    JLabel jLabel2 = new JLabel();
    JButton find = new JButton();
    FlowLayout flowLayout1 = new FlowLayout();
    //*************net
//以下是网络相关变量
    Socket socket;
    BufferedReader in;
    PrintWriter out;
    DatagramPacket sendPacket, receivePacket;
    DatagramSocket sendSocket, receiveSocket;
    int udpPORT = 5001;//
    int sendPort = 5001;//单机调试请改动这里，谢谢！！！！！
    String server;
    int serverport;
    byte array[] = new byte[512];
    Thread thread;
    int myjicq;
    String received;
    ////以下是程序界面的变量
    JLabel jLabel3 = new JLabel();
    JTextField getfromname = new JTextField();
    JLabel jLabel4 = new JLabel();
    JTextField getfromjicq = new JTextField();
    JTextArea getinfo = new JTextArea();
    JButton getok = new JButton();
    String theip;
    JButton update = new JButton();
    JMenuItem delfriend = new JMenuItem();
    JButton myinfo = new JButton();
    JButton online = new JButton();
    JLabel jLabel1 = new JLabel();
    JTextField helloname = new JTextField();
    JLabel jLabel5 = new JLabel();
    JTextField hellojicq = new JTextField();
    JLabel jLabel6 = new JLabel();
    JTextField helloemail = new JTextField();
    JLabel jLabel7 = new JLabel();
    JTextArea helloinfo = new JTextArea();
    JButton jButton3 = new JButton();
    JButton hellook = new JButton();
    JLabel jLabel8 = new JLabel();
    JLabel jLabel9 = new JLabel();
    JLabel jLabel10 = new JLabel();
    JLabel oneaddme = new JLabel();
    JButton addit = new JButton();
    JButton iknow = new JButton();
    JLabel jLabel11 = new JLabel();
    JLabel jLabel12 = new JLabel();
    JTextField friendid = new JTextField();
    JButton directaddok = new JButton();
//***************net

    //以下该函数连接服务器
    public void ConnectServer(int myid) {
        try {
            socket = new Socket(InetAddress.getByName(server), serverport);

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())), true);

            //this is call my friend info
            //以下读取好友信息
            out.println("friend");
            out.println(myid);
            friendnum = Integer.parseInt(in.readLine());
            String friendname = " ";

            String friendjicqno, friendip, friendstatus, picinfo, email, infos;
            do {
                friendname = in.readLine();
                if (friendname.equals("over")) break;
                friendnames.add(friendname);
                friendjicqno = in.readLine();
                friendjicq.add(new Integer(friendjicqno));
                friendip = in.readLine();
                friendips.add(friendip);
                friendstatus = in.readLine();
                status.add(friendstatus);
                picinfo = in.readLine();
                picno.add(new Integer(picinfo));
                email = in.readLine();
                friendemail.add(email);
                infos = in.readLine();
                friendinfo.add(infos);
            } while (!friendname.equals("over"));
        } catch (IOException e1) {
            System.out.println("false");
        }
//以下在列表中显示好友
        DefaultListModel mm = (DefaultListModel) list.getModel();
        int picid;
        for (int p = 0; p < friendnames.size(); p++) {
            picid = Integer.parseInt(picno.get(p).toString());
            if (status.get(p).equals("1")) {
                mm.addElement(new Object[]{friendnames.get(p), new ImageIcon(picsonline[picid])});
            } else {
                mm.addElement(new Object[]{friendnames.get(p), new ImageIcon(picsoffline[picid])});
            }
        }//for
    }//connectto server

    //*****************************
//以下函数无限监听好友的消息
    public void run() {

        while (true) {
            try {
                for (int x = 0; x < 512; x++) array[x] = ' ';
//创建数据报
                receivePacket = new DatagramPacket(array, array.length);
                receiveSocket.receive(receivePacket);
                byte[] data = receivePacket.getData();
                String infofromip = receivePacket.getAddress().getHostAddress().toString().trim();
                index3 = 0;
                received = new String(data, 0, data.length);
                received.trim();
                //  System.out.println("get"+received.substring(0,6));
                String tempstr;
                int tx;
                //friend online
                if (received.substring(0, 6).equals("online")) {//如果有好友上线就变彩色
                    tempstr = received.substring(6).trim();
                    // System.out.println("str"+tempstr);
                    tempgetjicq = Integer.parseInt(tempstr);
                    // System.out.println("id"+tempgetjicq);
                    do {
                        tx = Integer.parseInt(friendjicq.get(index3).toString());
                        //System.out.println("tx"+tx);
                        if (tempgetjicq == tx) break;
                        index3++;
                    } while (index3 < friendjicq.size());
                    friendips.setElementAt(infofromip, index3);
                    // status.setElementAt(,index3);
                    //System.out.println(index3);
                    DefaultListModel mm3 = (DefaultListModel) list.getModel();
                    int picid = Integer.parseInt(picno.get(index3).toString());
                    mm3.setElementAt(new Object[]{friendnames.get(index3), new ImageIcon(picsonline[picid])}, index3);
                }//end online
                //friend offline
                else if (received.substring(0, 7).equals("offline")) {//如果有好友下线就变灰色
                    tempstr = received.substring(7).trim();
                    System.out.println("str" + tempstr);
                    tempgetjicq = Integer.parseInt(tempstr);
                    System.out.println("id" + tempgetjicq);
                    do {
                        tx = Integer.parseInt(friendjicq.get(index3).toString());
                        System.out.println("tx" + tx);
                        if (tempgetjicq == tx) break;
                        index3++;
                    } while (index3 < friendjicq.size());
                    infofromip = "null";
                    friendips.setElementAt(infofromip, index3);
                    // status.setElementAt(,index3);
                    System.out.println(index3);
                    DefaultListModel mm3 = (DefaultListModel) list.getModel();
                    int picid = Integer.parseInt(picno.get(index3).toString());
                    mm3.setElementAt(new Object[]{friendnames.get(index3), new ImageIcon(picsoffline[picid])}, index3);

                }//end friend offline
                //someone add me as friend
                else if (received.substring(0, 9).equals("oneaddyou")) {
                    //如果有人加我为好有，选择加还是不
                    tempstr = received.substring(9).trim();
                    System.out.println("str" + tempstr);
                    tempgetjicq = Integer.parseInt(tempstr);
                    System.out.println("id" + tempgetjicq);
                    //JOptionPane.showMessageDialog(this,"收到"+tempgetjicq+"addyou","ok",JOptionPane.INFORMATION_MESSAGE);
                    oneaddme.setText(tempgetjicq + "把你加为好友");
                    OneAddyou.setBounds(400, 300, 250, 200);
                    OneAddyou.show();


                } //endsomeone add me as friend
                else {//否则就显示收到消息
                    index4 = 0;
                    //  String infofromip=receivePacket.getAddress().getHostAddress().toString().trim();
                    do {
                        String friendip = friendips.get(index4).toString().trim();
                        if (infofromip.equals(friendip)) {
                            String nameinfo = friendnames.get(index4).toString().trim();
                            JOptionPane.showMessageDialog(this, "收到" + nameinfo + "的消息", "ok", JOptionPane.INFORMATION_MESSAGE);
                            fromunknow = false;
                            break;
                        }//if
                        index4++;
                        if (index4 >= friendnames.size()) {
                            fromunknow = true;//收到陌生人的消息
                            JOptionPane.showMessageDialog(this, "收到陌生人" + infofromip + "的消息", "ok", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } while (index4 < friendnames.size());//while
                    System.out.println(index4);

                }
                ;

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }


    }//run end

    //**********************
//以下创建数据报
    public void CreatUDP() {
        try {
            sendSocket = new DatagramSocket();
            receiveSocket = new DatagramSocket(udpPORT);
            // System.out.println("udp ok");
        } catch (SocketException se) {
            se.printStackTrace();
            System.out.println("false udp");
        }
    }// creat udp end

    //main ****************
    public MainWin(int s, String sername, int serport) {//主程序构造函数
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        try {
            myjicq = s;
            server = sername;
            serverport = serport;
            jbInit();
            ConnectServer(myjicq);
            CreatUDP();
            findf = new FindFriend2(myjicq, server, serverport);
            findf.setBounds(200, 150, 300, 300);
            thread = new Thread(this);
            thread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//end main*****

    /**
     * Component initialization
     */
    private void jbInit() throws Exception {//主程序界面
        contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(flowLayout1);

        this.getContentPane().setBackground(new Color(132, 158, 203));
        this.setResizable(false);
        this.setSize(new Dimension(206, 420));
        this.setTitle("Frame Title");
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                this_mousePressed(e);
            }
        });

        ListModel model = new NameAndPicListModel(friendnames, picsonline);
        ListCellRenderer renderer = new NameAndPicListCellRenderer();
        list = new JList(model);
        list.setBackground(new Color(255, 255, 210));
        list.setAlignmentX((float) 1.0);
        list.setAlignmentY((float) 1.0);
        list.setCellRenderer(renderer);
        list.setVisibleRowCount(7);
        list.addMouseListener(new MainWin_list_mouseAdapter(this));
        list.setSize(380, 200);
        jButton1.setText("我的好友");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setIcon(icon1);
        jButton1.setPressedIcon(icon6);
        direct.setText("直接加友");
        direct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                direct_mouseClicked(e);
            }
        });
        direct.addMouseListener(new MainWin_direct_mouseAdapter(this));
        direct.setToolTipText("about");
        direct.setPressedIcon(icon6);
        ok.setText("OK");
        ok.setBounds(new Rectangle(111, 89, 97, 29));
        ok.addMouseListener(new MainWin_ok_mouseAdapter(this));
        info.setMaximumSize(new Dimension(200, 100));
        info.setMinimumSize(new Dimension(200, 100));
        info.setText("");
        info.setBounds(new Rectangle(-31, 21, 353, 66));
        sendmessage.setText("发送消息");
        sendmessage.addMouseListener(new MainWin_sendmessage_mouseAdapter(this));
        getmessage.setText("接收消息");
        getmessage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                getmessage_mousePressed(e);
            }
        });
        lookinfo.setText("查看资料");
        lookinfo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                lookinfo_mousePressed(e);
            }
        });
        chatrecord.setText("聊天记录");
        Container dialogcon = about.getContentPane();
        about.setSize(200, 200);
        Container senddiapane = senddata.getContentPane();
        dialogcon.setLayout(null);
        dialogcon.setSize(100, 100);
        //senddiapane.setLayout(null);
        name.setForeground(SystemColor.activeCaption);
        name.setText("呢称");
        name.setBounds(new Rectangle(9, 44, 41, 18));
        nametext.setBounds(new Rectangle(52, 38, 90, 22));
        icq.setForeground(SystemColor.activeCaption);
        icq.setText("JAVA_ICQ");
        icq.setBounds(new Rectangle(163, 39, 64, 18));
        icqno.setBounds(new Rectangle(257, 37, 96, 22));
        send.setIcon(icon1);
        send.setText("发送");
        send.setBounds(new Rectangle(39, 219, 136, 29));
        send.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                send_mouseClicked(e);
            }
        });
        senddiapane.setLayout(null);
        cancel.setIcon(icon1);
        cancel.setText("取消");
        cancel.setBounds(new Rectangle(220, 216, 110, 29));
        cancel.addMouseListener(new MainWin_cancel_mouseAdapter(this));
        contentPane.setAlignmentX((float) 200.0);
        contentPane.setAlignmentY((float) 200.0);
        senddata.setResizable(false);
        senddata.getContentPane().setBackground(Color.lightGray);
        sendtext.setRows(10);
        sendtext.setMinimumSize(new Dimension(20, 10));
        sendtext.setMaximumSize(new Dimension(20, 10));
        sendtext.setBounds(new Rectangle(7, 71, 384, 141));
        jLabel2.setText("This is me");
        jLabel2.setBounds(new Rectangle(20, 82, 89, 18));
        //senddiapane.setBackground(new Color(58, 112, 165));
        find.setToolTipText("");
        find.setText("查找");
        find.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                find_mouseClicked(e);
            }
        });
        getdata.getContentPane().setLayout(null);
        getdata.setSize(400, 300);
        jLabel3.setText("昵称");
        jLabel3.setBounds(new Rectangle(14, 37, 41, 18));
        getfromname.setBounds(new Rectangle(56, 37, 90, 22));
        jLabel4.setText("JiCQ");
        jLabel4.setBounds(new Rectangle(164, 39, 41, 18));
        getfromjicq.setBounds(new Rectangle(224, 37, 104, 22));
        getinfo.setBounds(new Rectangle(18, 68, 325, 153));
        getok.setText("ok");
        getok.setBounds(new Rectangle(136, 240, 79, 29));
        getok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                getok_mouseClicked(e);
            }
        });
        update.setText("更新");
        update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                update_mouseClicked(e);
            }
        });
        delfriend.setText("删除好友");
        delfriend.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                delfriend_mousePressed(e);
            }
        });
        myinfo.setMaximumSize(new Dimension(70, 29));
        myinfo.setMinimumSize(new Dimension(70, 29));
        myinfo.setText("陌生人");
        myinfo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                myinfo_mouseClicked(e);
            }
        });
        online.setText("上线");
        online.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                online_mouseClicked(e);
            }
        });
        hello.getContentPane().setLayout(null);
        jLabel1.setText("呢称");
        jLabel1.setBounds(new Rectangle(11, 29, 41, 18));
        helloname.setBounds(new Rectangle(52, 27, 78, 22));
        jLabel5.setText("Jicq#");
        jLabel5.setBounds(new Rectangle(148, 30, 41, 18));
        hellojicq.setBounds(new Rectangle(198, 28, 106, 22));
        jLabel6.setText("电子邮件");
        jLabel6.setBounds(new Rectangle(11, 71, 66, 18));
        helloemail.setBounds(new Rectangle(64, 69, 138, 22));
        jLabel7.setText("个人资料");
        jLabel7.setBounds(new Rectangle(14, 106, 75, 18));
        helloinfo.setBounds(new Rectangle(13, 136, 301, 101));
        jButton3.setBounds(new Rectangle(218, 65, 79, 29));
        hellook.setText("ok");
        hellook.setBounds(new Rectangle(124, 245, 79, 29));
        hellook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                hellook_mouseClicked(e);
            }
        });
        jLabel8.setText("发送消息");
        jLabel8.setBounds(new Rectangle(14, 19, 196, 18));
        jLabel9.setText("接收消息");
        jLabel9.setBounds(new Rectangle(12, 13, 186, 18));
        OneAddyou.getContentPane().setLayout(null);
        jLabel10.setText("收到消息");
        jLabel10.setBounds(new Rectangle(7, 13, 143, 18));
        oneaddme.setBounds(new Rectangle(7, 57, 247, 18));
        addit.setText("加为好友");
        addit.setBounds(new Rectangle(19, 124, 93, 29));
        addit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                addit_mouseClicked(e);
            }
        });
        iknow.setText("知道了");
        iknow.setBounds(new Rectangle(164, 124, 79, 29));
        iknow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                iknow_mouseClicked(e);
            }
        });
        DirectAdd.getContentPane().setLayout(null);
        jLabel11.setText("直接添加好友");
        jLabel11.setBounds(new Rectangle(7, 19, 220, 18));
        jLabel12.setText("好友号码");
        jLabel12.setBounds(new Rectangle(11, 58, 72, 18));
        friendid.setBounds(new Rectangle(83, 53, 118, 22));
        directaddok.setText("确定");
        directaddok.setBounds(new Rectangle(89, 109, 79, 29));
        directaddok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                directaddok_mouseClicked(e);
            }
        });
        DirectAdd.setResizable(false);
        dialogcon.add(info, null);
        dialogcon.add(ok, null);
        dialogcon.add(jLabel2, null);
        about.setSize(100, 100);
        about.pack();
        contentPane.add(jButton1, null);
        contentPane.add(new JScrollPane(list));


        contentPane.add(find, null);
        contentPane.add(update, null);
        contentPane.add(direct, null);
        contentPane.add(myinfo, null);
        contentPane.add(online, null);
        jPopupMenu1.add(sendmessage);
        jPopupMenu1.add(getmessage);
        jPopupMenu1.add(lookinfo);
        jPopupMenu1.add(chatrecord);
        jPopupMenu1.add(delfriend);


        senddiapane.add(send, null);
        senddiapane.add(cancel, null);
        senddiapane.add(sendtext, null);
        senddiapane.add(name, null);
        senddiapane.add(nametext, null);
        senddiapane.add(icq, null);
        senddiapane.add(icqno, null);
        senddiapane.add(jLabel8, null);
        getdata.getContentPane().add(getinfo, null);
        getdata.getContentPane().add(getok, null);
        getdata.getContentPane().add(jLabel3, null);
        getdata.getContentPane().add(getfromname, null);
        getdata.getContentPane().add(jLabel4, null);
        getdata.getContentPane().add(getfromjicq, null);
        getdata.getContentPane().add(jLabel9, null);
        hello.getContentPane().add(jLabel1, null);
        hello.getContentPane().add(helloname, null);
        hello.getContentPane().add(jLabel5, null);
        hello.getContentPane().add(hellojicq, null);
        hello.getContentPane().add(jLabel6, null);
        hello.getContentPane().add(helloemail, null);
        hello.getContentPane().add(jLabel7, null);
        hello.getContentPane().add(helloinfo, null);
        hello.getContentPane().add(jButton3, null);
        hello.getContentPane().add(hellook, null);
        OneAddyou.getContentPane().add(jLabel10, null);
        OneAddyou.getContentPane().add(oneaddme, null);
        OneAddyou.getContentPane().add(addit, null);
        OneAddyou.getContentPane().add(iknow, null);
        DirectAdd.getContentPane().add(jLabel11, null);
        DirectAdd.getContentPane().add(jLabel12, null);
        DirectAdd.getContentPane().add(friendid, null);
        DirectAdd.getContentPane().add(directaddok, null);
        senddata.pack();

    }

    /**
     * Overridden so we can exit when window is closed
     */
    protected void processWindowEvent(WindowEvent e) {//关闭程序
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            //tell who add me as friend offline
            try {
                String whoips;
                String s = "offline" + myjicq;
                s.trim();
                System.out.println(s);
                byte[] data = s.getBytes();
                for (int i = 0; i < whoaddmesip.size(); i++) {
                    whoips = whoaddmesip.get(i).toString().trim();
                    sendPacket = new
                            DatagramPacket(data, s.length(), InetAddress.getByName(whoips), sendPort);
                    sendSocket.send(sendPacket);//通知好友我下线了
                }//for
            } catch (IOException e2) {
                sendtext.append(sendtext.getText());
                e2.printStackTrace();
            }
//end offline

//告诉服务器我下线了
            out.println("logout");
            out.println(myjicq);
            //socket.close();
            System.exit(0);

        }
    }

    void this_mousePressed(MouseEvent e) {
        jButton1.setIcon(icon1);
    }

    void list_mouseClicked(MouseEvent e) {
        jPopupMenu1.show(this, e.getX() + 20, e.getY() + 20);
    }

    void direct_mouseClicked(MouseEvent e) {//直接添加好友
        DirectAdd.setLocationRelativeTo(MainWin.this);
        DirectAdd.setSize(260, 160);
        DirectAdd.show();
// JOptionPane.showMessageDialog(this,"：-（对不起，还有好多没做出来.hg","ok",JOptionPane.INFORMATION_MESSAGE);

    }

    void ok_mouseClicked(MouseEvent e) {
        about.dispose();
    }


    void cancel_mouseClicked(MouseEvent e) {
        senddata.dispose();
    }

    void sendmessage_mousePressed(MouseEvent e) {//发消息菜单
        senddata.setLocationRelativeTo(MainWin.this);
        senddata.setBounds(e.getX() + 50, e.getY() + 50, 400, 280);
        index = list.getSelectedIndex();
        System.out.println(index);
        nametext.setText(friendnames.get(index).toString());
        icqno.setText(friendjicq.get(index).toString());
        theip = friendips.get(index).toString();//ip address
        System.out.println(theip);
        senddata.show();

    }

    void find_mouseClicked(MouseEvent e) {//显示查找好友窗口

        findf.show();
    }//find

    void send_mouseClicked(MouseEvent e) {//发送消息
//*********send message
        try {
            String s = sendtext.getText().trim();
            // System.out.println(s);
            byte[] data = s.getBytes();
            System.out.println(theip);
            theip.trim();
            if (theip.equals("null") || theip.equals(" ") || theip.equals("0")) {
                JOptionPane.showMessageDialog(this, "：-（对不起,不在线", "ok", JOptionPane.INFORMATION_MESSAGE);
            } else {
                sendPacket = new
                        DatagramPacket(data, s.length(), InetAddress.getByName(theip), sendPort);
                sendSocket.send(sendPacket);
            }

        } catch (IOException e2) {
            sendtext.append(sendtext.getText());
            e2.printStackTrace();
        }
        senddata.dispose();
//*******end send message
    }

    void getmessage_mousePressed(MouseEvent e) {//接受消息菜单
        String message = received.trim();
        index = list.getSelectedIndex();
        if (index == index4) getinfo.append(message);
        else getinfo.append(" ");
        getfromname.setText(friendnames.get(index).toString().trim());
        getfromjicq.setText(friendjicq.get(index).toString().trim());
        getdata.show();
    }

    void getok_mouseClicked(MouseEvent e) {//接受消息
        getinfo.setText(" ");
        getdata.dispose();
        received = " ";
    }

    //update friend info;
    void update_mouseClicked(MouseEvent e) {//更新好友列表
        tempname = findf.tmpname;
        tempjicq = findf.tmpjicq;
        tempip = findf.tmpip;
        temppic = findf.tmppic;
        tempstatus = findf.tmpstatus;
        tempemail = findf.tmpemail;
        tempinfo = findf.tmpinfo;
        DefaultListModel mm2 = (DefaultListModel) list.getModel();
        int picid = 0;
        for (int p = 0; p < tempname.size(); p++) {
            picid = Integer.parseInt(temppic.get(p).toString());
            if (status.get(p).equals("1")) {
                mm2.addElement(new Object[]{tempname.get(p), new ImageIcon(picsonline[picid])});
            } else {
                mm2.addElement(new Object[]{tempname.get(p), new ImageIcon(picsonline[picid])});
            }
//picid=Integer.parseInt(temppic.get(p).toString());
//mm2.addElement(new Object[]{tempname.get(p),new ImageIcon(picsonline[picid])});

        }//for
//add to friendlist
        for (int k = 0; k < tempname.size(); k++) {
            friendnames.add(tempname.get(k));
            friendjicq.add(tempjicq.get(k));
            friendips.add(tempip.get(k));
            picno.add(temppic.get(k));
            status.add(tempstatus.get(k));
            friendemail.add(tempemail.get(k));
            friendinfo.add(tempinfo.get(k));
        }//for
//clean tmp
        for (int p = 0; p < tempname.size(); p++) {
            findf.tmpip.removeAllElements();
            findf.tmpjicq.removeAllElements();
            findf.tmpname.removeAllElements();
            findf.tmppic.removeAllElements();
            findf.tmpstatus.removeAllElements();
            findf.tmpemail.removeAllElements();
            findf.tmpinfo.removeAllElements();
        }
    }

    //delete freind
    void delfriend_mousePressed(MouseEvent e) {//删除好友
        out.println("delfriend");
        int index2;
        index2 = list.getSelectedIndex();

        out.println(friendjicq.get(index2));//the friendjicq to del
        out.println(myjicq);//my jicqno
        DefaultListModel mm = (DefaultListModel) list.getModel();
        mm.removeElementAt(index2);
        friendnames.removeElementAt(index2);
        friendips.removeElementAt(index2);
        friendjicq.removeElementAt(index2);
        picno.removeElementAt(index2);
        status.removeElementAt(index2);
        friendemail.removeElementAt(index2);
        friendinfo.removeElementAt(index2);
    }//////////////delfriend

    //tell friend i am online
    void online_mouseClicked(MouseEvent e) {
        out.println("getwhoaddme");
        out.println(myjicq);

        String whoip = " ";
        do {
            try {
                whoip = in.readLine().trim();
                if (whoip.equals("over")) break;
                whoaddmesip.add(whoip);
            } catch (IOException s) {
                System.out.println("false getwhoaddme");
            }
        } while (!whoip.equals("over"));
        for (int i = 0; i < whoaddmesip.size(); i++) {
            System.out.println(whoaddmesip.get(i));
        }
        try {
            String whoips;
            String s = "online" + myjicq;
            s.trim();
            System.out.println(s);
            byte[] data = s.getBytes();
            for (int i = 0; i < whoaddmesip.size(); i++) {
                whoips = whoaddmesip.get(i).toString().trim();
                sendPacket = new
                        DatagramPacket(data, s.length(), InetAddress.getByName(whoips), sendPort);
                sendSocket.send(sendPacket);
            }//for
        } catch (IOException e2) {
            sendtext.append(sendtext.getText());
            e2.printStackTrace();
            System.exit(1);
        }

    }/////end tellfrienonline

    void myinfo_mouseClicked(MouseEvent e) {//陌生人消息
        if (fromunknow) {
            String message = received.trim();
            getinfo.append(message);
            getdata.show();
        }

    }

    void lookinfo_mousePressed(MouseEvent e) {//查看好友资料菜单
        hello.setLocationRelativeTo(MainWin.this);
        hello.setBounds(e.getX() + 50, e.getY() + 50, 380, 300);
        index = list.getSelectedIndex();
        helloname.setText(friendnames.get(index).toString());
        hellojicq.setText(friendjicq.get(index).toString());
        helloemail.setText(friendemail.get(index).toString());
        helloinfo.setText(friendinfo.get(index).toString().trim());
        hello.show();
    }

    void hellook_mouseClicked(MouseEvent e) {//查看好友资料关闭
        hello.dispose();
    }

    //add the one who add me as friend
    void addit_mouseClicked(MouseEvent e) {
//如果有人加我，我就加它
        out.println("addnewfriend");
        out.println(tempgetjicq);
        out.println(myjicq);
        String thename = " ";
        try {
            String thejicqno, theip, thestatus, picinfo, email, infos;
            do {
                thename = in.readLine();
                if (thename.equals("over")) break;
                friendnames.add(thename);
                thejicqno = in.readLine();
                friendjicq.add(new Integer(thejicqno));
                theip = in.readLine();
                friendips.add(theip);
                thestatus = in.readLine();
                status.add(thestatus);
                picinfo = in.readLine();
                picno.add(new Integer(picinfo));
                email = in.readLine();
                friendemail.add(email);
                infos = in.readLine();
                friendinfo.add(infos);
            } while (!thename.equals("over"));
        } catch (IOException e1) {
            System.out.println("false");
        }
        int dddd = friendnames.size() - 1;
        DefaultListModel mm2 = (DefaultListModel) list.getModel();
        int picid;
        picid = Integer.parseInt(picno.get(dddd).toString());
        mm2.addElement(new Object[]{friendnames.get(dddd), new ImageIcon(picsonline[picid])});
    }

    void iknow_mouseClicked(MouseEvent e) {
        OneAddyou.dispose();
    }

    void directaddok_mouseClicked(MouseEvent e) {//直接添加好友
        out.println("addnewfriend");
        out.println(friendid.getText().trim());
        out.println(myjicq);
        String thename = " ";
        try {
            String thejicqno, theip, thestatus, picinfo, email, infos;
            do {
                thename = in.readLine();
                if (thename.equals("over")) break;
                friendnames.add(thename);
                thejicqno = in.readLine();
                friendjicq.add(new Integer(thejicqno));
                theip = in.readLine();
                friendips.add(theip);
                thestatus = in.readLine();
                status.add(thestatus);
                picinfo = in.readLine();
                picno.add(new Integer(picinfo));
                email = in.readLine();
                friendemail.add(email);
                infos = in.readLine();
                friendinfo.add(infos);
            } while (!thename.equals("over"));
        } catch (IOException e1) {
            System.out.println("false");
        }
        int dddd = friendnames.size() - 1;
        DefaultListModel mm2 = (DefaultListModel) list.getModel();
        int picid;
        picid = Integer.parseInt(picno.get(dddd).toString());
        mm2.addElement(new Object[]{friendnames.get(dddd), new ImageIcon(picsonline[picid])});
        DirectAdd.dispose();
    }

    ;//end directadd friend

}//end class MainWin

//以下继承DefaultListModel类以创建好友列表
class NameAndPicListModel extends DefaultListModel {
    public NameAndPicListModel(Vector friendnames, String[] pics) {
        for (int i = 0; i < friendnames.size(); ++i) {
            addElement(new Object[]{friendnames.get(i), new ImageIcon(pics[i])});
        }
    }

    public String getName(Object object) {
        Object[] array = (Object[]) object;
        return (String) array[0];
    }

    public Icon getIcon(Object object) {
        Object[] array = (Object[]) object;
        return (Icon) array[1];
    }
}

class NameAndPicListCellRenderer extends JLabel implements ListCellRenderer {
    private Border lineBorder = BorderFactory.createLineBorder(Color.red, 2),
            emptyBorder = BorderFactory.createEmptyBorder(2, 2, 2, 2);

    public NameAndPicListCellRenderer() {
        setOpaque(true);
    }

    public Component getListCellRendererComponent(JList list, Object value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        NameAndPicListModel model = (NameAndPicListModel) list.getModel();
        setText(model.getName(value));
        setIcon(model.getIcon(value));
        if (isSelected) {
            setForeground(list.getSelectionForeground());
            setBackground(list.getSelectionBackground());
        } else {
            setForeground(list.getForeground());
            setBackground(list.getBackground());
        }
        if (cellHasFocus) setBorder(lineBorder);
        else setBorder(emptyBorder);
        return this;
    }
}

class MainWin_list_mouseAdapter extends java.awt.event.MouseAdapter {
    MainWin adaptee;

    MainWin_list_mouseAdapter(MainWin adaptee) {
        this.adaptee = adaptee;
    }

    public void mouseClicked(MouseEvent e) {
        adaptee.list_mouseClicked(e);
    }
}

class MainWin_direct_mouseAdapter extends java.awt.event.MouseAdapter {
    MainWin adaptee;

    MainWin_direct_mouseAdapter(MainWin adaptee) {
        this.adaptee = adaptee;
    }

    public void mouseClicked(MouseEvent e) {
        adaptee.direct_mouseClicked(e);
    }
}

class MainWin_ok_mouseAdapter extends java.awt.event.MouseAdapter {
    MainWin adaptee;

    MainWin_ok_mouseAdapter(MainWin adaptee) {
        this.adaptee = adaptee;
    }

    public void mouseClicked(MouseEvent e) {
        adaptee.ok_mouseClicked(e);
    }
}

class MainWin_sendmessage_mouseAdapter extends java.awt.event.MouseAdapter {
    MainWin adaptee;

    MainWin_sendmessage_mouseAdapter(MainWin adaptee) {
        this.adaptee = adaptee;
    }

    public void mousePressed(MouseEvent e) {
        adaptee.sendmessage_mousePressed(e);
    }
}

class MainWin_cancel_mouseAdapter extends java.awt.event.MouseAdapter {
    MainWin adaptee;

    MainWin_cancel_mouseAdapter(MainWin adaptee) {
        this.adaptee = adaptee;
    }

    public void mouseClicked(MouseEvent e) {
        adaptee.cancel_mouseClicked(e);
    }
}
