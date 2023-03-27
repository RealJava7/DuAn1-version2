package view.Contains.cell;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableActionCellRender extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        panelKhoiPhuc khoiPhuc = new panelKhoiPhuc();
        khoiPhuc.setBackground(com.getBackground());
        return khoiPhuc;
    }

}
