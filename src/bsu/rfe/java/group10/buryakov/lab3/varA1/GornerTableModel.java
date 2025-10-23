package bsu.rfe.java.group10.buryakov.lab3.varA1;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class GornerTableModel extends AbstractTableModel {
    private Double[] coefficients;
    private Double from;
    private Double to;
    private Double step;

    public GornerTableModel(Double from, Double to, Double step, Double[] coefficients) {
        this.from = from;
        this.to = to;
        this.step = step;
        this.coefficients = coefficients;
    }

    public Double getFrom() {
        return from;
    }

    public Double getTo() {
        return to;
    }

    public Double getStep() {
        return step;
    }

    public int getColumnCount() {
        // Теперь три столбца
        return 3;
    }

    public int getRowCount() {
        return (int) Math.ceil((to - from) / step) + 1;
    }

    public Object getValueAt(int row, int col) {
        double x = from + step * row;

        if (col == 0) {
            // Первый столбец - значение X
            return x;
        } else if (col == 1) {
            // Второй столбец - значение многочлена
            Double result = 0.0;

            if (coefficients.length > 0) {
                result = coefficients[0];
                for (int i = 1; i < coefficients.length; i++) {
                    result = result * x + coefficients[i];
                }
            }
            return result;
        } else {
            // Третий столбец - больше ли нуля многочлен
            Double result = 0.0;

            if (coefficients.length > 0) {
                result = coefficients[0];
                for (int i = 1; i < coefficients.length; i++) {
                    result = result * x + coefficients[i];
                }
            }
            // Возвращаем Boolean значение: true если результат > 0, иначе false
            return result > 0;
        }
    }

    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Значение X";
            case 1:
                return "Значение многочлена";
            case 2:
                return "Больше нуля?";
            default:
                return "";
        }
    }

    public Class<?> getColumnClass(int col) {
        switch (col) {
            case 0:
                return Double.class;
            case 1:
                return Double.class;
            case 2:
                return Boolean.class;
            default:
                return Object.class;
        }
    }
}