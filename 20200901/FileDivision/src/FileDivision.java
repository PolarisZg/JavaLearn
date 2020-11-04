import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileDivision {
    static void Division(File file,int Num,String Location) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        FileOutputStream[] fileOutputStreams = new FileOutputStream[Num];
        File[] files = new File[Num];
        for(int i = 0;i < Num;i++){
            files[i] = new File(Location + "/Answer" + i + ".txt");
            fileOutputStreams[i] = new FileOutputStream(files[i]);
        }
        long length = file.length()/Num;
        byte[] b = new byte[1];
        for(long i = 0;fileInputStream.read(b) != -1;i++){
            fileOutputStreams[(int)(i/length)].write(b);
        }
        for(int j = 0;j < Num;j++){
            fileOutputStreams[j].close();
        }
        fileInputStream.close();
    }
    static void Combine(File[] files,String Location) throws IOException {
        FileInputStream[] fileInputStreams = new FileInputStream[files.length];
        for(int i = 0;i < files.length;i++){
            fileInputStreams[i] = new FileInputStream(files[i]);
        }
        File fileOriginal = new File(Location + "/Original" + ".txt");
        FileOutputStream fileOutputStream = new FileOutputStream(fileOriginal);
        for(int i = 0;i < files.length;i++){
            int b;
            while((b = fileInputStreams[i].read()) != -1){
                fileOutputStream.write(b);
            }
            fileInputStreams[i].close();
        }
        fileOutputStream.close();
    }
}
class FileDivisionGui{
    JFrame jFrame;
    JPanel jPanelButtons;
    JButton jButtonDivision;
    JButton jButtonCombine;
    JPanel jPanelWorkSpace;
    CardLayout cardLayout;
    JPanel jPanelDivision;
    JPanel jPanelCombine;
    JButton jButtonDivisionFileLocation;
    File file0;
    JLabel jLabelDivisionFileLocation;
    JButton jButtonCombineFile1;
    JLabel jLabelCombineFile1;
    JButton jButtonCombineFile2;
    File[] files;
    JLabel jLabelCombineFile2;
    JPanel jPanelAnswers;
    JButton jButtonGo;
    JLabel jLabelAnswer;
    int card = 1;

    FileDivisionGui(){
        jFrame = new JFrame("文件分割");
        jFrame.setLayout(new BorderLayout());
        jPanelButtons = new JPanel();
        jPanelButtons.setLayout(new GridLayout(1,2));
        (jButtonDivision = new JButton("分割文件")).addActionListener(e -> {
            cardLayout.show(jPanelWorkSpace,"Division");
            card = 1;
        });
        (jButtonCombine = new JButton("合并文件")).addActionListener(e -> {
            cardLayout.show(jPanelWorkSpace,"Combine");
            card = 2;
        });
        jPanelButtons.add(jButtonDivision);
        jPanelButtons.add(jButtonCombine);
        jFrame.add(jPanelButtons,BorderLayout.NORTH);
        jPanelWorkSpace = new JPanel();
        jPanelWorkSpace.setLayout(cardLayout = new CardLayout());
        jPanelDivision = new JPanel();
        jPanelDivision.setLayout(new GridLayout(1,2));
        (jButtonDivisionFileLocation = new JButton("选择源文件")).addActionListener(e -> {
            JFileChooser jFileChooser = new JFileChooser();
            if(jFileChooser.showOpenDialog(jPanelDivision) == JFileChooser.APPROVE_OPTION){
                file0 = jFileChooser.getSelectedFile();
                jLabelDivisionFileLocation.setText(file0.getAbsolutePath());
            }
        });
        jPanelDivision.add(jButtonDivisionFileLocation);
        jPanelDivision.add(jLabelDivisionFileLocation = new JLabel());
        jPanelWorkSpace.add(jPanelDivision,"Division");
        jPanelCombine = new JPanel();
        jPanelCombine.setLayout(new GridLayout(2,2));
        files = new File[2];
        (jButtonCombineFile1 = new JButton("选择源文件1")).addActionListener(e -> {
            JFileChooser jFileChooser = new JFileChooser();
            if(jFileChooser.showOpenDialog(jPanelDivision) == JFileChooser.APPROVE_OPTION){
                files[0] = jFileChooser.getSelectedFile();
                jLabelCombineFile1.setText(files[0].getAbsolutePath());
            }
        });
        (jButtonCombineFile2 = new JButton("选择源文件2")).addActionListener(e -> {
            JFileChooser jFileChooser = new JFileChooser();
            if(jFileChooser.showOpenDialog(jPanelDivision) == JFileChooser.APPROVE_OPTION){
                files[1] = jFileChooser.getSelectedFile();
                jLabelCombineFile2.setText(files[1].getAbsolutePath());
            }
        });
        jPanelCombine.add(jButtonCombineFile1);
        jPanelCombine.add(jLabelCombineFile1 = new JLabel());
        jPanelCombine.add(jButtonCombineFile2);
        jPanelCombine.add(jLabelCombineFile2 = new JLabel());
        jPanelWorkSpace.add(jPanelCombine,"Combine");
        jFrame.add(jPanelWorkSpace,BorderLayout.CENTER);
        jPanelAnswers = new JPanel();
        jPanelAnswers.setLayout(new GridLayout(1,2));
        (jButtonGo = new JButton("运行")).addActionListener(e -> {
            String Location = null;
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if(jFileChooser.showSaveDialog(jPanelAnswers) == JFileChooser.APPROVE_OPTION){
                Location = jFileChooser.getSelectedFile().getAbsolutePath();
            }
            if(card == 1){
                try {
                    FileDivision.Division(file0,2,Location);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            else if(card == 2){
                try {
                    FileDivision.Combine(files,Location);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        jPanelAnswers.add(jButtonGo);
        jPanelAnswers.add(jLabelAnswer = new JLabel());
        jFrame.add(jPanelAnswers,BorderLayout.SOUTH);

        jFrame.setSize(400,150);
        jFrame.setDefaultCloseOperation(3);
        jFrame.setVisible(true);
    }

    public static void main(String [] args){
        new FileDivisionGui();
    }
}
