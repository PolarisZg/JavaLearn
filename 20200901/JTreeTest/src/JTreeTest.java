import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

public class JTreeTest {
    JFrame jFrame;
    JTree jTree;
    DefaultMutableTreeNode root;
    JTreeTest(){
        jFrame = new JFrame("树");
        jTree = new JTree(root = new DefaultMutableTreeNode("字符"));
        root.add(new DefaultMutableTreeNode("字母"));
        root.add(new DefaultMutableTreeNode("数字"));
        jFrame.add(jTree);
        jFrame.setSize(100,100);
        jFrame.setDefaultCloseOperation(3);
        jFrame.setVisible(true);
    }
     public static void main(String [] args){
        new JTreeTest();
     }
}
