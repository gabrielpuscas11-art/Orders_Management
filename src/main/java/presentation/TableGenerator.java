package presentation;

import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Clasa se ocupa cu generarea automata a unui tabel Swing.
 * Foloseste reflexia pentru a extrage proprietatile obiectelor din lista.
 */
public class TableGenerator {

    /**
     * Creeaza un model de tabel pe baza unei liste de obiecte primite ca parametru.
     * Numele coloanelor sunt luate automat din numele campurilor clasei respective.
     *
     * @param objects Lista de obiecte care vor fi introduse in tabel
     * @return Modelul de tabel (DefaultTableModel) populat cu date
     */
    public static DefaultTableModel createTable(List<?> objects) {
        if(objects == null || objects.isEmpty()) {
            return new DefaultTableModel();
        }

        Class<?> type = objects.get(0).getClass();
        Field[] fields = type.getDeclaredFields();
        String[] columnNames = new String[fields.length];

        for(int i = 0; i < fields.length; i++) {
            columnNames[i] = fields[i].getName().toUpperCase();
        }
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        objects.stream().forEach(obj -> {
            Object[] row = new Object[fields.length];
            for(int i = 0; i < fields.length; i++) {
                try {
                    fields[i].setAccessible(true);
                    row[i] = fields[i].get(obj);
                }catch(IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            tableModel.addRow(row);
        });
        return tableModel;
    }
}
