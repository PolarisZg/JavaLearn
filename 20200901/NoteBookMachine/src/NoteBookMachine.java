/************************************************
 * 一个文本编辑器
 * 打开文件的时候会覆盖掉输入区域的所有文字
 * 保存文件时不能仅指定保存路径
 * 按❌退出时不会有对话框弹出
*************************************************/
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class NoteBookMachine {
    JFrame jFrame;
    JMenu jMenu;
    JMenuBar jMenuBar;
    JMenuItem jMenuItemOpen;
    JMenuItem jMenuItemSave;
    JMenuItem jMenuItemExit;
    JMenuItem jMenuItemClose;
    JTextArea jTextArea;

    NoteBookMachine(){
        jFrame = new JFrame("文本编辑器");
        jFrame.setJMenuBar(jMenuBar = new JMenuBar());
        jMenuBar.add(jMenu = new JMenu("文件"));
        (jMenuItemOpen = new JMenuItem("打开")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                if(jFileChooser.showOpenDialog(jFrame) == JFileChooser.APPROVE_OPTION){
                    File file = jFileChooser.getSelectedFile();
                    try {
                        Reader reader = new FileReader(file);
                        BufferedReader bufferedReader = new BufferedReader(reader);
                        jTextArea.setText("");
                        while(true){
                            try {
                                String s = bufferedReader.readLine();
                                if(s == null){
                                    bufferedReader.close();
                                    reader.close();
                                    break;
                                }
                                jTextArea.append(s + System.lineSeparator());
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }
                }
            }
        });
        (jMenuItemSave = new JMenuItem("保存")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NoteBookMachine.Save(jTextArea);
            }
        });
        (jMenuItemExit = new JMenuItem("退出")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selection = JOptionPane.showConfirmDialog(jFrame,"是否保存更改？");
                if(selection == JOptionPane.OK_OPTION){
                    NoteBookMachine.Save(jTextArea);
                }
                System.exit(0);
            }
        });
        (jMenuItemClose = new JMenuItem("关闭")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        jMenu.add(jMenuItemOpen);
        jMenu.add(jMenuItemSave);
        jMenu.add(jMenuItemExit);
        jMenu.add(jMenuItemClose);
        JScrollPane jScrollPane = new JScrollPane(jTextArea = new JTextArea());
        jFrame.add(jScrollPane);

        jFrame.setDefaultCloseOperation(3);
        jFrame.setSize(500,500);
        jFrame.setVisible(true);
    }

    static void Save(JTextArea jTextArea){
        JFileChooser jFileChooser = new JFileChooser();
        if(jFileChooser.showSaveDialog(jTextArea) == JFileChooser.APPROVE_OPTION){
            File file = jFileChooser.getSelectedFile();
            try {
                file.createNewFile();
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                String[] s = jTextArea.getText().split(System.lineSeparator());
                for(int i = 0;i < s.length; i++){
                    bufferedWriter.write(s[i]);
                    bufferedWriter.newLine();
                }
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String [] args){
        new NoteBookMachine();
    }
}
