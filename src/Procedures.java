import javax.swing.*;

public class Procedures {

// МАТЕМАТИЧЕСКИЕ ОПЕРАЦИИ

    // Метод qualifier вычисляет определитель матрицы с помощью разложения по первой строке.
    static double qualifier(double[][] d) {
        double det = 0;

        if (d.length <= 1) {
            det = d[0][0];
        } else {
            for (int k = 0; k < d.length; k++) {
                double[][] subAr = new double[d.length - 1][d.length - 1];
                for (int i = 0; i < subAr.length; i++) {
                    for (int j = 0; j < k; j++) {
                        subAr[i][j] = d[i + 1][j];
                    }
                    for (int j = k; j < subAr.length; j++) {
                        subAr[i][j] = d[i + 1][j + 1];
                    }
                }
                det += d[0][k] * qualifier(subAr) * ((int) Math.pow(-1, k + 2));
            }
        }

        return det;
    }

    // Метод sum вычисляет сумму двух матриц.
    static double[][] sum(double[][] s1, double[][] s2) {
        double[][] total = new double[s1.length][s1[0].length];

        for (int i = 0; i < s1.length; i++) {
            for (int j = 0; j < s1[0].length; j++) {
                total[i][j] = s1[i][j] + s2[i][j];
            }
        }

        return total;
    }

    // Метод difference вычисляет разность двух матриц.
    static double[][] difference(double[][] s1, double[][] s2) {
        double[][] dif = new double[s1.length][s1[0].length];

        for (int i = 0; i < s1.length; i++) {
            for (int j = 0; j < s1[0].length; j++) {
                dif[i][j] = s1[i][j] - s2[i][j];
            }
        }

        return dif;
    }

    // Метод product вычисляет произведение двух матриц.
    static double[][] product(double[][] s1, double[][] s2) {
        double[][] multi = new double[s1.length][s2[0].length];

        for (int i = 0; i < s1.length; i++) {
            for (int j = 0; j < s2[0].length; j++) {
                multi[i][j] = multiply(i, j, s1, s2);
            }
        }

        return multi;
    }

    // Вспомогательный метод multiply "перемножает" i-ю строку на j-й столбец матрицы.
    static double multiply(int i, int j, double[][] s1, double[][] s2) {
        double ss = 0;

        for (int k = 0; k < s1[0].length; k++) {
            ss += s1[i][k] * s2[k][j];
        }

        return ss;
    }

    // Метод overturning выполняет транспонирование матрицы.
    static double[][] overturning(double[][] s) {
        double[][] trans = new double[s[0].length][s.length];

        for (int i = 0; i < s[0].length; i++) {
            for (int j = 0; j < s.length; j++) {
                trans[i][j] = s[j][i];
            }
        }

        return trans;
    }

// Методы, соединяющие программный код с данными. Они вызываются из главного класса.

    //Дополнительный метод заполнения матриц
    private double[][][] readMatrices(int s) {

        double[][][] matrix = new double[s][][]; // задали количество матриц

        for (int i = 0; i < s; i++) {  // заполняем каждую матрицу
            MatrixDialogs matrixDialog = new MatrixDialogs();
            double[][] data = matrixDialog.getData();
            if (data == null)
                return null;
            matrix[i] = data;
            output(matrix[i]); //выводим заполненную i матрицу на экран
        }

        return matrix;
    }

    //Дополнительный метод для ввода количества матриц
    public double[][][] number() {
        int s = Integer.parseInt(JOptionPane.showInputDialog("Введите количество матриц = '...'"));

        return readMatrices(s);
    }

    //Дополнительный метод при вычислении определителя/транспонировании матрицы
    public double[][][] one() {
        return readMatrices(1);
    }

    // Вычисление определителя:
    public void determinant() {
        double[][][] matrix = one();

        if (matrix == null) return;

        //проверка корректности введенной матрицы
        if (matrix[0].length != matrix[0][0].length) {
            JOptionPane.showMessageDialog(null, "Ошибка! Для вычисления определителя матрица должна быть квадратной!");
            return;
        }
        JOptionPane.showMessageDialog(null, "Определитель:" + "\n" + qualifier(matrix[0]));
    }

    // Сумма:
    public void summation() {

        double[][][] matrix = number();

        if (matrix == null) return;
        //проверка корректности введенных матриц
        for (int i = 0; i < matrix.length - 1; i++) {
            if ((matrix[i].length != matrix[i + 1].length) | (matrix[i][0].length != matrix[i + 1][0].length)) {
                JOptionPane.showMessageDialog(null, "Ошибка! Для выполнения операции сложения матрицы должны быть одинаковой размерности!");
                return;
            }
        }

        double[][] sum = matrix[0];

        for (int i = 1; i < matrix.length; i++) {
            sum = sum(sum, matrix[i]);
        }
        result(sum); //выводим полученную матрицу на экран
    }

    // Разность:
    public void subtraction() {

        double[][][] matrix = number();

        if (matrix == null) return;
        //проверка корректности введенных матриц
        for (int i = 0; i < matrix.length - 1; i++) {
            if ((matrix[i].length != matrix[i + 1].length) | (matrix[i][0].length != matrix[i + 1][0].length)) {
                JOptionPane.showMessageDialog(null, "Ошибка! Для выполнения операции вычитания матрицы должны быть одинаковой размерности!");
                return;
            }
        }

        double[][] diff = matrix[0];

        for (int i = 1; i < matrix.length; i++) {
            diff = difference(diff, matrix[i]);
        }
        result(diff); //выводим полученную матрицу на экран
    }

    // Произведение:
    public void multiplication() {

        double[][][] matrix = number();

        if (matrix == null) return;
        //проверка корректности введенных матриц

        for (int i = 0; i < matrix.length - 1; i++) {
            if (matrix[i][0].length != matrix[i + 1].length) {
                JOptionPane.showMessageDialog(null, "Ошибка! Для умножения матриц количество столбцов первой матрицы должно быть равно количеству строк следующей матрицы!");
                return;
            }
        }

        double[][] prod = matrix[0];
        for (int i = 1; i < matrix.length; i++) {
            prod = product(prod, matrix[i]);
        }


        result(prod); //выводим полученную матрицу на экран
    }

    // Транспонирование:
    public void transposition() {

        double[][][] matrix = one();

        if (matrix == null) return;
        result(overturning(matrix[0])); //выводим полученную матрицу на экран
    }

    // Метод output выводит входную матрицу на экран в диалоговом окне.
    void output(double[][] s_out) {

        String out = "Введенная матрица " + s_out.length + "x" + s_out[0].length + ":";
        for (int i = 0; i < s_out.length; i++) {
            out += "\n";
            for (int j = 0; j < s_out[0].length; j++) {
                out += s_out[i][j] + " ";
            }
        }

        JOptionPane.showMessageDialog(null, out);
    }

    // Метод result выводит итоговую матрицу на экран в диалоговом окне.
    void result(double[][] s) {

        String got = "Итоговая матрица " + s.length + "x" + s[0].length + ":";

        for (int i = 0; i < s.length; i++) {
            got += "\n";
            for (int j = 0; j < s[0].length; j++) {
                got += s[i][j] + " ";
            }
        }

        JOptionPane.showMessageDialog(null, got);
    }
}