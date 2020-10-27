import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class DrawBoardMenu {
    JFrame jFrame;
    JMenuBar jMenuBar;
    JMenu jMenu;
    JMenuItem jMenuItemCircle;
    JMenuItem jMenuItemRec;
    JMenuItem jMenuItemLine;
    JMenuItem jMenuItemDel;
    Boolean aBooleanCircle;
    Boolean aBooleanRec;
    Boolean aBooleanLine;
    JPanel jPanelDrawBoard;
    JLabel jLabelNote;
    Boolean aBooleanNote = false;
    Graphics graphics;
    int X0 = 0;
    int Y0 = 0;
    int X1 = 0;
    int Y1 = 0;
    int n = 0;

    DrawBoardMenu(){
        jFrame = new JFrame("DrawBoard");
        jMenuBar = new JMenuBar();
        jFrame.setJMenuBar(jMenuBar);
        jMenu = new JMenu("绘图");
        jMenu.add(jMenuItemCircle = new JMenuItem("圆"));
        jMenu.add(jMenuItemRec = new JMenuItem("矩形"));
        jMenu.add(jMenuItemLine = new JMenuItem("线段"));
        jMenu.add(jMenuItemDel = new JMenuItem("清除绘画区域"));
        jMenuBar.add(jMenu);
        jPanelDrawBoard = new JPanel();
        jFrame.add(jPanelDrawBoard,BorderLayout.CENTER);
        jFrame.add(jLabelNote = new JLabel("空白绘图区"),BorderLayout.SOUTH);

        jFrame.setSize(500,500);
        jFrame.setDefaultCloseOperation(3);
        jFrame.setVisible(true);

        jMenuItemCircle.addActionListener(new actionPerformed());
        jMenuItemLine.addActionListener(new actionPerformed());
        jMenuItemRec.addActionListener(new actionPerformed());
        jMenuItemDel.addActionListener(new actionPerformed());
        jPanelDrawBoard.addMouseListener(new mousePerformed());
    }

    class actionPerformed implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(Objects.equals(e.getActionCommand(), "圆")){
                aBooleanCircle = true;
                n = 1;
                aBooleanNote = aBooleanLine = aBooleanRec = false;
                jLabelNote.setText("点击以确定圆心");
            }
            if(Objects.equals(e.getActionCommand(), "矩形")){
                aBooleanRec = true;
                n = 1;
                aBooleanNote = aBooleanLine = aBooleanCircle = false;
                jLabelNote.setText("点击以确定矩形一角");
            }
            if(Objects.equals(e.getActionCommand(), "线段")){
                aBooleanLine = true;
                n = 1;
                aBooleanNote = aBooleanRec = aBooleanCircle = false;
                jLabelNote.setText("点击以确定线段一端");
            }
            if(Objects.equals(e.getActionCommand(), "清除绘画区域")){
                if(!aBooleanNote) {
                    aBooleanLine = aBooleanRec = aBooleanCircle = false;
                    X0 = Y0 = X1 = Y1 = 0;
                    n = 0;
                    int w = jPanelDrawBoard.getWidth();
                    int h = jPanelDrawBoard.getHeight();
                    graphics = jPanelDrawBoard.getGraphics();
                    graphics.setColor(Color.white);
                    graphics.fillRect(0, 0, w, h);
                    jLabelNote.setText("空白绘图区");
                    aBooleanNote = true;
                }
            }
        }
    }

    class mousePerformed extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            graphics = jPanelDrawBoard.getGraphics();
            super.mouseClicked(e);
            if(n == 1) {
                X0 = e.getX();
                Y0 = e.getY();
                n = 2;
                if(aBooleanCircle) {
                    graphics.drawString(".o", X0, Y0);
                    jLabelNote.setText("点击以确定圆周上一点");
                }
                if(aBooleanRec){
                    graphics.drawString("A",X0,Y0);
                    jLabelNote.setText("点击以确定矩形对角线另一点");
                }
                if(aBooleanLine){
                    graphics.drawString("A",X0,Y0);
                    jLabelNote.setText("点击以确定线段另一端点");
                }
            }
            else if(n == 2){
                X1 = e.getX();
                Y1 = e.getY();
                if(aBooleanCircle){
                    double r = Math.sqrt((X1 - X0) * (X1 - X0) + (Y1 - Y0) * (Y1 - Y0));
                    int x = (int)(X0 - r);
                    int y = (int)(Y0 - r);
                    graphics.drawOval(x,y,(int)(2 * r),(int)(2 * r));
                    jLabelNote.setText("点击以确定同心圆圆周上一点");
                }
                if(aBooleanRec){
                    int w = Math.abs(X1 - X0);
                    int h = Math.abs(Y1 - Y0);
                    graphics.drawRect(Math.min(X0,X1),Math.min(Y0,Y1),w,h);
                }
                if(aBooleanLine){
                    graphics.drawLine(X0,Y0,X1,Y1);
                    graphics.drawString("B",X1,Y1);
                }
            }
        }
    }
    public static void main(String [] args){
        new DrawBoardMenu();
    }
}
