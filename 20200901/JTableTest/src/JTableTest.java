import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class JTableTest {
    JFrame jFrame;
    JTable jTable;
    DefaultTableModel defaultTableModel;
    JButton jButtonDelRows;
    JButton jButtonInsertRows;
    JButton jButtonAddRows;
    JButton jButtonAddCols;
    JPanel jPanelButtons;

    JTableTest(){
        jFrame = new JFrame("表格");
        jTable = new JTable(defaultTableModel = new DefaultTableModel(3,3));
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.add(jTable);
        jFrame.add(scrollPane,BorderLayout.CENTER);
        (jPanelButtons = new JPanel()).setLayout(new GridLayout(1,4));
        (jButtonAddRows = new JButton("表格最下方添加行")).addActionListener(e -> defaultTableModel.addRow((Object[]) null));
        (jButtonInsertRows = new JButton("选中行后添加行")).addActionListener(e -> {
            int row = jTable.getSelectedRow();
            defaultTableModel.insertRow(row + 1,(Object[]) null);
        });
        (jButtonDelRows = new JButton(" 删除选中行")).addActionListener(e -> {
            int[] row = jTable.getSelectedRows();
            //System.out.println(row);
            for(int i = row.length - 1;i >= 0;i--){
                defaultTableModel.removeRow(row[i]);
            }
        });
        (jButtonAddCols = new JButton("表格最右侧添加列")).addActionListener(e -> defaultTableModel.addColumn(null));
        jPanelButtons.add(jButtonInsertRows);
        jPanelButtons.add(jButtonAddRows);
        jPanelButtons.add(jButtonDelRows);
        jPanelButtons.add(jButtonAddCols);
        jFrame.add(jPanelButtons,BorderLayout.SOUTH);

        jFrame.setSize(600,500);
        jFrame.setDefaultCloseOperation(3);
        jFrame.setVisible(true);
    }

    public static void main(String [] args){
        new JTableTest();
    }
}
