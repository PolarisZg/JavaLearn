/*
* 通讯录程序
* 打开按钮打开的文件必须是该使用该程序保存的文件
* 删除行和添加行不会即时保存，需要手动保存
*
* “打开” 按钮
* * 选择使用该程序保存的文件进行打开
*
* “保存按钮”
* * 保存 树根结点 “所有”的表格内容
* * 点击后会弹出对话框，指定保存的文件地址及文件
*
* “添加” 按钮
* * 在程序头部录入数据后点击该按钮，会在表格中添加一行数据，不会去检查是否有重复的数据
*
* “删除” 按钮
* * 选中一行，点击该按钮会在数据库中删除该行数据
*
* “树”
* * 点击某一结点，会在表格中仅显示以该结点名为关系的数据
* * 点击“所有”，会显示所有数据
*
* 删除功能的实现
* * 获取所选行的数据，把所有数据导入一个文件中，写入时剔除所选行的数据，清空所有表格，把刚才文件中的数据再写入程序
*
* 树 分类功能的实现
* * 表格所在区域为卡片布局，点击结点显示相应的卡片*/
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Objects;

public class AddressBook {
    public static void main(String [] args){
        new AddressBookGui();
    }
}
class AddressBookGui{
    JFrame jFrame;
    JTree jTree;
    DefaultMutableTreeNode nodeAll;
    DefaultMutableTreeNode nodeFriend;
    DefaultMutableTreeNode nodeClassmate;
    DefaultMutableTreeNode nodeRelative;
    JPanel jPanelButtons;
    JButton jButtonSave;
    JButton jButtonDel;
    JButton jButtonAdd;
    JButton jButtonOpen;
    JPanel jPanelInput;
    JTextField jTextFieldName;
    JTextField jTextFieldNum;
    JRadioButton jRadioButtonMale;
    JRadioButton jRadioButtonFemale;
    JComboBox<String> jComboBox;
    JPanel jPanelTable;
    JTable jTableAll;
    DefaultTableModel defaultTableModelAll;
    CardLayout cardLayout;
    JTable jTableFriend;
    DefaultTableModel defaultTableModelFriend;
    JTable jTableClassmate;
    DefaultTableModel defaultTableModelClassmate;
    JTable jTableRelative;
    DefaultTableModel defaultTableModelRelative;
    int x = 0;

    AddressBookGui(){
        jFrame = new JFrame("通讯录");
        jFrame.setLayout(new BorderLayout());
        nodeAll = new DefaultMutableTreeNode("所有");
        nodeFriend = new DefaultMutableTreeNode("朋友");
        nodeClassmate = new DefaultMutableTreeNode("同学");
        nodeRelative = new DefaultMutableTreeNode("亲戚");
        nodeAll.add(nodeFriend);
        nodeAll.add(nodeClassmate);
        nodeAll.add(nodeRelative);
        jTree = new JTree(nodeAll);
        jTree.addTreeSelectionListener(e -> {
            if(Objects.equals((jTree.getLastSelectedPathComponent()).toString(), "所有")){
                x = 0;
                cardLayout.show(jPanelTable,"all");
            }
            else if(Objects.equals((jTree.getLastSelectedPathComponent()).toString(), "朋友")){
                x = 1;
                cardLayout.show(jPanelTable,"friend");
            }
            else if(Objects.equals((jTree.getLastSelectedPathComponent()).toString(), "同学")){
                x = 2;
                cardLayout.show(jPanelTable,"classmate");
            }
            else if(Objects.equals((jTree.getLastSelectedPathComponent()).toString(), "亲戚")){
                x = 3;
                cardLayout.show(jPanelTable,"relative");
            }
        });
        jFrame.add(new JScrollPane(jTree), BorderLayout.WEST);
        (jPanelButtons = new JPanel()).setLayout(new GridLayout(1,4));
        jPanelButtons.add(jButtonOpen = new JButton("打开"));
        jButtonOpen.addActionListener(e -> {
            JFileChooser jFileChooser = new JFileChooser();
            if(jFileChooser.showOpenDialog(jFrame) == JFileChooser.APPROVE_OPTION){
                File file = jFileChooser.getSelectedFile();
                AddressBookF.Open(file,defaultTableModelAll,
                                        defaultTableModelFriend,
                                        defaultTableModelClassmate,
                                        defaultTableModelRelative
                                    );
            }
        });
        jPanelButtons.add(jButtonSave = new JButton("保存"));
        jButtonSave.addActionListener(e -> {
            JFileChooser jFileChooser = new JFileChooser();
            if(jFileChooser.showSaveDialog(jFrame) == JFileChooser.APPROVE_OPTION){
                File file = jFileChooser.getSelectedFile();
                AddressBookF.Save(file,defaultTableModelAll,null);
            }
        });
        jPanelButtons.add(jButtonAdd = new JButton("添加"));
        jButtonAdd.addActionListener(e -> {
            String name = jTextFieldName.getText();
            String num = jTextFieldNum.getText();
            String sex = "男";
            if(jRadioButtonFemale.isSelected()) {
                sex = "女";
            }
            String relation = (String) jComboBox.getSelectedItem();
            String[] data = {name + " " , num + " " , sex , relation};
            defaultTableModelAll.addRow(data);
            if(Objects.equals(data[3], "朋友")){
                defaultTableModelFriend.addRow(data);
            }
            else if(Objects.equals(data[3], "同学")){
                defaultTableModelClassmate.addRow(data);
            }
            else if(Objects.equals(data[3], "亲戚")){
                defaultTableModelRelative.addRow(data);
            }
        });
        jPanelButtons.add(jButtonDel = new JButton("删除"));
        jButtonDel.addActionListener(e -> {
            if(JOptionPane.showConfirmDialog(null,"是否删除该行数据") == JOptionPane.OK_OPTION) {
                String s = switch (x) {
                    case 0 -> AddressBookF.returnRowData(jTableAll.getSelectedRow(), defaultTableModelAll);
                    case 1 -> AddressBookF.returnRowData(jTableFriend.getSelectedRow(), defaultTableModelFriend);
                    case 2 -> AddressBookF.returnRowData(jTableClassmate.getSelectedRow(), defaultTableModelClassmate);
                    case 3 -> AddressBookF.returnRowData(jTableRelative.getSelectedRow(), defaultTableModelRelative);
                    default -> null;
                };
                File file = new File("Temporary");
                AddressBookF.Save(file, defaultTableModelAll, s);
                AddressBookF.DelAll(defaultTableModelAll);
                AddressBookF.DelAll(defaultTableModelFriend);
                AddressBookF.DelAll(defaultTableModelClassmate);
                AddressBookF.DelAll(defaultTableModelRelative);
                AddressBookF.Open(file, defaultTableModelAll,
                        defaultTableModelFriend,
                        defaultTableModelClassmate,
                        defaultTableModelRelative);
                file.delete();
            }
        });
        jFrame.add(jPanelButtons,BorderLayout.SOUTH);
        (jPanelInput = new JPanel()).setLayout(new GridLayout(1,8));
        jPanelInput.add(new JLabel("姓名"));
        jPanelInput.add(jTextFieldName = new JTextField());
        jPanelInput.add(new JLabel("性别"));
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(jRadioButtonMale = new JRadioButton("男"));
        buttonGroup.add(jRadioButtonFemale = new JRadioButton("女"));
        JPanel jPanelSex = new JPanel();
        jPanelSex.setLayout(new GridLayout(1,2));
        jPanelSex.add(jRadioButtonMale);
        jPanelSex.add(jRadioButtonFemale);
        jPanelInput.add(jPanelSex);
        jPanelInput.add(new JLabel("电话号码"));
        (jTextFieldNum = new JTextField()).addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyChar() < '0' || e.getKeyChar() > '9'){
                    e.consume();
                }
            }
        });
        jPanelInput.add(jTextFieldNum);
        jPanelInput.add(new JLabel("关系"));
        jComboBox = new JComboBox<>();
        jComboBox.addItem("朋友");
        jComboBox.addItem("同学");
        jComboBox.addItem("亲戚");
        jPanelInput.add(jComboBox);
        jFrame.add(jPanelInput,BorderLayout.NORTH);
        String[] tableHead = {"姓名","性别","电话号码","关系"};
        jPanelTable = new JPanel();
        cardLayout = new CardLayout();
        jPanelTable.setLayout(cardLayout);
        jPanelTable.add("all",new JScrollPane(jTableAll = new JTable(defaultTableModelAll = new DefaultTableModel(tableHead,0))));
        jTableAll.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTableAll.getTableHeader().setReorderingAllowed(false);
        jPanelTable.add("friend",new JScrollPane(jTableFriend = new JTable(defaultTableModelFriend = new DefaultTableModel(tableHead,0))));
        jTableFriend.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTableFriend.getTableHeader().setReorderingAllowed(false);
        jPanelTable.add("classmate",new JScrollPane(jTableClassmate = new JTable(defaultTableModelClassmate = new DefaultTableModel(tableHead,0))));
        jTableClassmate.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTableClassmate.getTableHeader().setReorderingAllowed(false);
        jPanelTable.add("relative",new JScrollPane(jTableRelative = new JTable(defaultTableModelRelative = new DefaultTableModel(tableHead,0))));
        jTableRelative.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTableRelative.getTableHeader().setReorderingAllowed(false);
        jFrame.add(jPanelTable,BorderLayout.CENTER);

        jFrame.setSize(700,500);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}

class AddressBookF{
    static String returnRowData(int i,DefaultTableModel defaultTableModelAll){
        return defaultTableModelAll.getValueAt(i,0).toString()
                + "//" + defaultTableModelAll.getValueAt(i,1).toString()
                + "//" + defaultTableModelAll.getValueAt(i,2).toString()
                + "//" + defaultTableModelAll.getValueAt(i,3).toString();
    }
    static void Save(File file,DefaultTableModel defaultTableModelAll,String no){
        try {
            file.createNewFile();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file,false));
            int row = defaultTableModelAll.getRowCount();
            for(int i = 0;i < row; i++){
                String s = AddressBookF.returnRowData(i,defaultTableModelAll);
                if(!Objects.equals(s, no)) {
                    bufferedWriter.write(s);
                    bufferedWriter.newLine();
                }
            }
            bufferedWriter.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    static void Open(File file, DefaultTableModel defaultTableModelAll,
                                DefaultTableModel defaultTableModelFriend,
                                DefaultTableModel defaultTableModelClassmate,
                                DefaultTableModel defaultTableModelRelative){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while(true) {
                String line = bufferedReader.readLine();
                if(line == null)
                    break;
                String[] data = line.split("//");
                defaultTableModelAll.addRow(data);
                if(Objects.equals(data[3], "朋友")){
                    defaultTableModelFriend.addRow(data);
                }
                else if(Objects.equals(data[3], "同学")){
                    defaultTableModelClassmate.addRow(data);
                }
                else if(Objects.equals(data[3], "亲戚")){
                    defaultTableModelRelative.addRow(data);
                }
            }
        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }
    static void DelAll(DefaultTableModel defaultTableModel){
        for(int i = defaultTableModel.getRowCount() - 1;i >= 0;i--){
            defaultTableModel.removeRow(i);
        }
    }
}