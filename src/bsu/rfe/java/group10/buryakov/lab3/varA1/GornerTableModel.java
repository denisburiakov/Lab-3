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
        // В данной модели два столбца
        return 2;
    }

    public int getRowCount() {
        // Вычислить количество точек между началом и концом отрезка исходя из шага табулирования
        return (int) Math.ceil((to - from) / step) + 1;
    }

    public Object getValueAt(int row, int col) {
        // Вычислить значение X как НАЧАЛО_ОТРЕЗКА + ШАГ*НОМЕР_СТРОКИ
        double x = from + step * row;

        if (col == 0) {
            // Если запрашивается значение 1-го столбца, то это X
            return x;
        } else {
            // Если запрашивается значение 2-го столбца, то это значение многочлена
            Double result = 0.0;

            // Вычисление значения в точке по схеме Горнера
            if (coefficients.length > 0) {
                result = coefficients[0];
                for (int i = 1; i < coefficients.length; i++) {
                    result = result * x + coefficients[i];
                }
            }

            return result;
        }
    }

    public String getColumnName(int col) {
        switch (col) {
            case 0:
                // Название 1-го столбца
                return "Значение X";
            case 1:
                // Название 2-го столбца
                return "Значение многочлена";
            default:
                return "";
        }
    }

    public Class<?> getColumnClass(int col) {
        // И в 1-ом и во 2-ом столбце находятся значения типа Double
        return Double.class;
    }
}
