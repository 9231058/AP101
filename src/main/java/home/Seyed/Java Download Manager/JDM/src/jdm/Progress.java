package jdm;

import java.awt.Component;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;


/**
 * This is Progress class to update progress bar in download table...
 * @author SMA74/9031806
 */
public class Progress extends JProgressBar implements TableCellRenderer {
    public Progress() {
        super(0,100);
    }
/**
 * TableCellRenderer's implementation
 * @param table Download Table.
 * @param value cell of table's Value.
 * @param isSelected
 * @param hasFocus
 * @param row
 * @param column
 * @return Download Table. 
 */
    @Override
public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected, boolean hasFocus,int row, int column) {
    setValue((int) ((Float) value).floatValue());
    setString(getValue()+"%");
    return this;
}
/**
 * For show change of Progress Bar. 
 * @return progress bar isDisplayable or not.
 */
    @Override
public boolean isDisplayable() {
    return true;
}
}