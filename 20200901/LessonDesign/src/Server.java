import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.Vector;

class ServerThread extends Thread {//�̳��߳�
    private Socket socket;//�����׽ӿ�
    private BufferedReader in;//����������
    private PrintWriter out;//���������
    int no;//���������jicq����
    private String UESR = "PolarisZg";
    private String PASSWORD = "8896859ab";

    public ServerThread(Socket s) throws IOException {//�̹߳��캯��
        socket = s;//ȡ�ô��ݲ���
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));//����������
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);//���������
        start();//�����߳�
    }

    public void run() {//�̼߳�������
        try {
            while (true) {
                String str = in.readLine();//ȡ�������ַ���
                if (str.equals("end")) break;//����ǽ����͹ر�����
                else if (str.equals("login")) {//����ǵ�¼
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        String url = "jdbc:mysql://localhost:3306/lessondesign?serverTimezone=Asia/Shanghai";
                        Connection c = DriverManager.getConnection(url,UESR, PASSWORD);
                        String sql = "select nickname,password from icq where icqno=?";
                        //׼�������ݿ�ѡ���سƺ�����
                        PreparedStatement prepare = c.prepareCall(sql);//�趨���ݿ��Ѱ����
                        String icqno = in.readLine();
                        int g = Integer.parseInt(icqno);//ȡ�������jicq����
                        System.out.println(icqno);
                        String passwd = in.readLine().trim();//ȡ�����������
                        System.out.println(passwd);
                        prepare.clearParameters();
                        prepare.setInt(1, g);//�趨����
                        ResultSet r = prepare.executeQuery();//ִ�����ݿ��Ѱ
                        if (r.next()) {//���±Ƚ�����ĺ����������Ƿ���ͬ
                            String pass = r.getString("password").trim();
                            System.out.println(pass);
                            if (passwd.regionMatches(0, pass, 0, pass.length())) {
                                out.println("ok");
//�����ͬ�͸��߿ͻ�ok
//���Ҹ������ݿ��û�Ϊ����
//�Լ�ע���û���ip ��ַ
                                //*************register ipaddress
                                String setip = "update icq set ip=? where icqno=?";
                                PreparedStatement prest = c.prepareCall(setip);
                                prest.clearParameters();
                                prest.setString(1, socket.getInetAddress().getHostAddress());
                                prest.setInt(2, g);
                                int set = prest.executeUpdate();
                                System.out.println(set);
                                //*************ipaddress
                                //set status online
                                String status = "update icq set status=1 where icqno=?";
                                PreparedStatement prest2 = c.prepareCall(status);
                                prest2.clearParameters();
                                prest2.setInt(1, g);
                                int set2 = prest2.executeUpdate();
                                System.out.println(set2);
                                //set online
                            }
//���߸��߿ͻ�ʧ��
                            else out.println("false");
                            r.close();
                            c.close();
                        } else {
                            out.println("false");
                            System.out.println("false");
                            r.close();
                            c.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    socket.close();
                }//end login
                //��¼����
                //����Ϊ����ͻ����½�����
                 else if (str.equals("new")) {
                     try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        String url = "jdbc:mysql://localhost:3306/lessondesign?serverTimezone=Asia/Shanghai";
                        Connection c2 = DriverManager.getConnection(url,UESR, PASSWORD);
                        String newsql = "insert into icq(nickname,password,email,info,place,pic) values(?,?,?,?,?,?)";
//׼�������û����سƣ����룬email���������ϣ����ᣬͷ�����Ϣ
                        PreparedStatement prepare2 = c2.prepareCall(newsql);
                        String nickname = in.readLine().trim();
                        String password = in.readLine().trim();
                        String email = in.readLine().trim();
                        String info = in.readLine().trim();
                        String place = in.readLine().trim();
                        int picindex = Integer.parseInt(in.readLine());
                        prepare2.clearParameters();
                        prepare2.setString(1, nickname);
                        prepare2.setString(2, password);
                        prepare2.setString(3, email);
                        prepare2.setString(4, info);
                        prepare2.setString(5, place);
                        prepare2.setInt(6, picindex);
                        int r3 = prepare2.executeUpdate();//ִ�����ݿ����
                        String sql2 = "select icqno from icq where nickname=?";
//���¸��߿ͻ���ע��ĺ���
                        PreparedStatement prepare3 = c2.prepareCall(sql2);
                        prepare3.clearParameters();
                        prepare3.setString(1, nickname);
                        ResultSet r2 = prepare3.executeQuery();
                        while (r2.next()) {
                            //out.println(r2.getInt(1));
                            no = r2.getInt(1);
                            System.out.println(no);
                        }
                        out.println(no);
                        out.println("ok");
                        c2.close();
//���
                    } catch (Exception e) {
                        e.printStackTrace();
                        out.println("false");
                    }
                    socket.close();
                }//end new
//�½��û�����
//���´����û����Һ���
                else if (str.equals("find")) {
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        String url = "jdbc:mysql://localhost:3306/lessondesign?serverTimezone=Asia/Shanghai";
                        Connection c3 = DriverManager.getConnection(url,UESR, PASSWORD);
                        //�����������ݿ⣬���ҷ��������û����سƣ��Ա𣬼��ᣬ�������ϵ���Ϣ
                        String find = "select nickname,sex,place,ip,email,info from icq";
                        Statement st = c3.createStatement();
                        ResultSet result = st.executeQuery(find);
                        while (result.next()) {
                            out.println(result.getString("nickname"));
                            out.println(result.getString("sex"));
                            out.println(result.getString("place"));
                            out.println(result.getString("ip"));
                            out.println(result.getString("email"));
                            out.println(result.getString("info"));
                        }//while end
                        out.println("over");
                        ////////GET ICQNO
                        int d, x;
                        boolean y;
//���·����û���jicq���룬ͷ��ţ����Ƿ�����
                        ResultSet iset = st.executeQuery("select icqno,pic,status from icq");
                        while (iset.next()) {
                            d = iset.getInt("icqno");
                            out.println(d);
                            x = iset.getInt("pic");//pic info
                            out.println(x);
                            y = iset.getBoolean("status");
                            if (y) {
                                out.println("1");
                            } else {
                                out.println("0");
                            }
                            //System.out.println(d);
                        }
                        // end send jicqno
                        iset.close();
                        /////////icqno end
                        c3.close();
                        result.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("false");
                    }
//socket.close();
                }//end find
//���Һ��ѽ���
//���´����û���¼ʱ��ȡ���������
                else if (str.equals("friend")) {
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        String url = "jdbc:mysql://localhost:3306/lessondesign?serverTimezone=Asia/Shanghai";
                        Connection c4 = DriverManager.getConnection(url,UESR, PASSWORD);
//�������Ӻ��ѱ������û��ĺ�������
                        String friend = "select friend from friend where icqno=?";
                        PreparedStatement prepare4 = c4.prepareCall(friend);
                        prepare4.clearParameters();
                        int icqno = Integer.parseInt(in.readLine());
                        System.out.println(icqno);
                        prepare4.setInt(1, icqno);
                        ResultSet r4 = prepare4.executeQuery();
                        Vector friendno = new Vector();//��ʸ��������Ѻ���
                        while (r4.next()) {
                            friendno.add(new Integer(r4.getInt(1)));
                        }
//read friend info
//���¸��߿ͻ�����ѵ��سƣ����룬ip��ַ��״̬��ͷ�񣬸������ϵ���Ϣ
                        out.println(friendno.size());
                        for (int i = 0; i < friendno.size(); i++) {
                            String friendinfo = "select nickname,icqno,ip,status,pic,email,info from icq where icqno=?";
                            PreparedStatement prepare5 = c4.prepareCall(friendinfo);
                            prepare5.clearParameters();
                            prepare5.setObject(1, friendno.get(i));
                            ResultSet r5 = prepare5.executeQuery();
                            boolean status;
                            while (r5.next()) {
                                out.println(r5.getString("nickname"));
                                out.println(r5.getInt("icqno"));
                                out.println(r5.getString("ip"));
                                status = r5.getBoolean("status");
                                if (status) out.println("1");
                                else {
                                    out.println("0");
                                }
                                out.println(r5.getInt("pic"));
                                out.println(r5.getString("email"));
                                out.println(r5.getString("info"));
                            } //while
                            r5.close();
                        }//for
//�������
                        out.println("over");
                        System.out.println("over");
                        c4.close();
                        r4.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("false");
                    }
//socket.close();
                }//end friend
//��ȡ������Ϣ���
//���´����û���Ӻ���
                else if (str.equals("addfriend")) {
                    System.out.println("add");
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        String url = "jdbc:mysql://localhost:3306/lessondesign?serverTimezone=Asia/Shanghai";
                        Connection c5 = DriverManager.getConnection(url,UESR, PASSWORD);
                        //�������ݿ⣬���ݽ��ܵ��û����뼰���Ѻ�������ѱ���Ӽ�¼
                        int friendicqno = Integer.parseInt(in.readLine());
                        System.out.println(friendicqno);
                        int myicqno = Integer.parseInt(in.readLine());
                        System.out.println(myicqno);
                        String addfriend = "insert into friend values(?,?)";
                        PreparedStatement prepare6 = c5.prepareCall(addfriend);
                        prepare6.clearParameters();
                        prepare6.setInt(1, myicqno);
                        prepare6.setInt(2, friendicqno);
                        int r6 = 0;
                        r6 = prepare6.executeUpdate();
                        if (r6 == 1) System.out.println("ok  addfrien");
                        else System.out.println("false addfriend");

                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("false");
                    }

//socket.close();
                    System.out.println("over addfriend");
                }//end addfriend
//�û���Ӻ��ѽ���
//add new friend who add me
//���´��������û�������ң��Ҿͼ���
                else if (str.equals("addnewfriend")) {
                    System.out.println("add");
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        String url = "jdbc:mysql://localhost:3306/lessondesign?serverTimezone=Asia/Shanghai";
                        Connection c6 = DriverManager.getConnection(url,UESR, PASSWORD);
                        //�������ݿ⣬���ݽ��ܵ��û����뼰���Ѻ�������ѱ���Ӽ�¼
                        int friendicqno = Integer.parseInt(in.readLine());
                        System.out.println(friendicqno);
                        int myicqno = Integer.parseInt(in.readLine());
                        System.out.println(myicqno);
                        String addfriend = "insert into friend values(?,?)";
                        PreparedStatement prepare6 = c6.prepareCall(addfriend);
                        prepare6.clearParameters();
                        prepare6.setInt(1, myicqno);
                        prepare6.setInt(2, friendicqno);
                        int r6 = 0;
                        r6 = prepare6.executeUpdate();
                        if (r6 == 1) System.out.println("ok  addfrien");
                        else System.out.println("false addfriend");

                        String friendinfo = "select nickname,icqno,ip,status,pic,email,info from icq where icqno=?";
//����ɹ��������û����ݺ��ѵĻ�����Ϣ�������سƵ�
                        PreparedStatement prepare5 = c6.prepareCall(friendinfo);
                        prepare5.clearParameters();
                        prepare5.setInt(1, friendicqno);
                        ResultSet r5 = prepare5.executeQuery();
                        boolean status;
                        while (r5.next()) {
                            System.out.println("dsf");
                            out.println(r5.getString("nickname"));
                            out.println(r5.getInt("icqno"));
                            out.println(r5.getString("ip"));
                            status = r5.getBoolean("status");
                            if (status) out.println("1");
                            else {
                                out.println("0");
                            }
                            out.println(r5.getInt("pic"));
                            out.println(r5.getString("email"));
                            out.println(r5.getString("info"));
                        } //while
                        out.println("over");
                        r5.close();
                        c6.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("false");
                    }
                    System.out.println("over addnewfriend");
                }//end addfriend
//�������������û�������ң��Ҿͼ���
//delete friend
//����ִ���û�ɾ������
                else if (str.equals("delfriend")) {
                    System.out.println("del");
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        String url = "jdbc:mysql://localhost:3306/lessondesign?serverTimezone=Asia/Shanghai";
                        Connection c7 = DriverManager.getConnection(url,UESR, PASSWORD);
                        //�������ݿ⣬���ݽ��ܵ��û����뼰���Ѻ�������ѱ�ɾ����¼
                        int friendicqno = Integer.parseInt(in.readLine());
                        System.out.println(friendicqno);
                        int myicqno = Integer.parseInt(in.readLine());
                        System.out.println(myicqno);
                        String addfriend = "delete from friend where icqno=? and friend=?";
                        PreparedStatement prepare7 = c7.prepareCall(addfriend);
                        prepare7.clearParameters();
                        prepare7.setInt(1, myicqno);
                        prepare7.setInt(2, friendicqno);
                        int r7 = 0;
                        r7 = prepare7.executeUpdate();
                        if (r7 == 1) System.out.println("ok  delfrien");//�ɹ�
                        else System.out.println("false delfriend");//ʧ��
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("del false");
                    }
                }//end delete friend
//ִ���û�ɾ�����ѽ���
//���´����û��˳�����
                else if (str.equals("logout")) {
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        String url = "jdbc:mysql://localhost:3306/lessondesign?serverTimezone=Asia/Shanghai";
                        Connection c8 = DriverManager.getConnection(url,UESR, PASSWORD);
                        //�������ݿ⣬���ݽ��ܵ��û����룬����״̬�ֶ���Ϊ0����ip��ַ��Ϊ��
                        int myicqno = Integer.parseInt(in.readLine());
                        System.out.println(myicqno);
                        String status = "update icq set status=0 , ip=' ' where icqno=?";
                        PreparedStatement prest8 = c8.prepareCall(status);
                        prest8.clearParameters();
                        prest8.setInt(1, myicqno);
                        int r8 = prest8.executeUpdate();
                        if (r8 == 1) System.out.println("ok  logout");
                        else System.out.println("false logout");
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("logout false");
                    }
                }//logout end
//�����û��˳��������
//get who add me as friend
//���´�����Щ�˼�����Ϊ���ѣ��Ա�����֪ͨ����
                else if (str.equals("getwhoaddme")) {
                    System.out.println("getwhoaddme");
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        String url = "jdbc:mysql://localhost:3306/lessondesign?serverTimezone=Asia/Shanghai";
                        Connection c9 = DriverManager.getConnection(url,UESR, PASSWORD);
                        //�������ݿ⣬�����ҵĺ��룬�Ӻ��ѱ���ѡ��˭������
                        int myicqno = Integer.parseInt(in.readLine());
                        System.out.println(myicqno);
                        String getwhoaddme = "select icqno from friend where friend=?";
                        PreparedStatement prepare6 = c9.prepareCall(getwhoaddme);
                        prepare6.clearParameters();
                        prepare6.setInt(1, myicqno);
                        ResultSet r6 = prepare6.executeQuery();
                        Vector who = new Vector();
                        while (r6.next()) {
                            who.add(new Integer(r6.getInt(1)));
                        }//end while
//Ȼ�������Щ���ѵ�ip��ַ��Ȼ�󷢸��û��Ա���������ͻ���������
                        for (int i = 0; i < who.size(); i++) {
                            String whoinfo = "select ip from icq where icqno=? and status=1";
                            PreparedStatement prepare = c9.prepareCall(whoinfo);
                            prepare.clearParameters();
                            prepare.setObject(1, who.get(i));
                            ResultSet r = prepare.executeQuery();
                            while (r.next()) {
                                out.println(r.getString("ip"));
                            } //while
                            r.close();
                        }//for
                        out.println("over");
                        System.out.println("over");
                        c9.close();
                        r6.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("false");
                    }
                }//end get who add me as friend
//�������߽���
                System.out.println("Echo ing :" + str);
            }
            System.out.println("Close...");
        } catch (IOException e) {
        }//�����쳣
        finally {
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
    }
}

public class Server {//����������

    public static void main(String args[]) throws IOException {

        ServerSocket s = new ServerSocket(8080);//��8080�˿ڴ����׽ӿ�
        System.out.println("Server start.." + s);
        try {
            while (true) {
                Socket socket = s.accept();//���޼����ͻ�������
                System.out.println("Connecting accept:" + socket);
                try {
                    new ServerThread(socket);//�������߳�
                } catch (IOException e) {
                    socket.close();
                }
            }
        } finally {
            s.close();
        }//�����쳣
    }
}//�������������
