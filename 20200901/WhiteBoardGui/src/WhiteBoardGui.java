import javax.swing.*;

public class WhiteBoardGui {
    JFrame jFrame;
    JPanel jPanelWhiteBoard;

    WhiteBoardGui(){
        jFrame = new JFrame("WhiteBoard");
        jFrame.add(jPanelWhiteBoard = new JPanel());

        jFrame.setSize(500,500);
        jFrame.setDefaultCloseOperation(3);
        jFrame.setVisible(true);
    }

    public static void main(String [] args){
        new WhiteBoardGui();
    }
}
