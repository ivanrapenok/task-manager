package ivanchik.task.manager.api.model;

import javax.swing.table.DefaultTableModel;

public class NotEditableTableModel extends DefaultTableModel {

    public NotEditableTableModel(Object[] columnNames, int rowCount) {
        super(convertToVector(columnNames), rowCount);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
