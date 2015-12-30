import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MatrixDialogs extends JDialog {

    private class MatrixTableModel extends AbstractTableModel {

        private Double[][] data = new Double[1][1];

        @Override
        public int getRowCount() {
            return data.length;
        }

        @Override
        public int getColumnCount() {
            return data[0].length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return data[rowIndex][columnIndex];
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return true;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            try {
                Double value = new Double((String) aValue);
                data[rowIndex][columnIndex] = value;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(MatrixDialogs.this, "Введите число!");
            }
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return String.class;
        }

        void setSize(int width, int height) {
            data = new Double[height][width];
            fireTableStructureChanged();
        }
    }

    private JSpinner height = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));

    private JSpinner width = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));

    private MatrixTableModel model = new MatrixTableModel();

    private JTable table = new JTable(model);

    private double[][] result;

    public MatrixDialogs() {

        setTitle("Ввод матрицы");

        setModalityType(ModalityType.APPLICATION_MODAL);

        ChangeListener listener = new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                changeSize();

            }
        };

        height.addChangeListener(listener);
        width.addChangeListener(listener);

        JPanel up = new JPanel();

        up.add(new JLabel("Количество строк:"));
        up.add(height);
        up.add(new JLabel("Количество столбцов:"));
        up.add(width);

        add(up, BorderLayout.NORTH);
        table.setPreferredScrollableViewportSize(new Dimension(400, 200));
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel down = new JPanel();


        JButton ok = new JButton(new AbstractAction("OK") {
            @Override
            public void actionPerformed(ActionEvent e) {
                result = checkData();

                if (result != null) {
                    dispose();
                }
            }
        });

        down.add(ok);

        add(down, BorderLayout.SOUTH);

        JButton exit = new JButton(new AbstractAction("Вернуться к выбору операции") {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        down.add(exit);

        setLocationRelativeTo(null);

        pack();

        setVisible(true);

    }

    private void changeSize() {

        int h = ((Number) height.getValue()).intValue();
        int w = ((Number) width.getValue()).intValue();
        model.setSize(w, h);

    }

    private double[][] checkData() {

        Double[][] data = model.data;

        double[][] result = new double[data.length][data[0].length];

        for (int i = 0; i < data.length; i++) {
            Double[] row = data[i];
            for (int j = 0; j < row.length; j++) {
                Double value = row[j];
                if (value == null) {
                    JOptionPane.showMessageDialog(this, "Введите данные в строке " + (i + 1) + ", столбце " + (j + 1));
                    return null;
                }
                result[i][j] = value.doubleValue();
            }
        }
        return result;
    }

    public double[][] getData() {
        return result;
    }
}