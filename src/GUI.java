import javax.swing.*;

public class GUI {
// Главный метод.


    public static void main(String[] args) {
        Procedures method = new Procedures();

        JOptionPane.showMessageDialog(null, "Здравствуйте! Вы работаете с калькулятором матриц (Ver1.0)." + "\n" + "\n" + "Выберете нужную Вам операцию");

        while (true) {
            String text = "Введите соответствующую цифру:" + "\n" + "\n" + "1 - Определитель" + "\n" + "2 - Сумма" + "\n" + "3 - Разность" + "\n" + "4 - Произведение" + "\n" + "5 - Транспонирование" + "\n" + "\n" + "0 - для выхода из программы";

            // JOptionPane.showInputDialog(1);

            int h = Integer.parseInt(JOptionPane.showInputDialog(text));

            switch (h) {

// Вычисление определителя

                case 1:
                    method.determinant();
                    break;

// Сумма

                case 2:
                    method.summation();
                    break;

// Разность

                case 3:
                    method.subtraction();
                    break;

// Произведение

                case 4:
                    method.multiplication();
                    break;

// Транспонирование


                case 5:
                    method.transposition();
                    break;

// Выход из программы

                case 0:
                    JOptionPane.showMessageDialog(null, "Спасибо за использование программы!" + "\n" + "\n" );
                    return;


                default:
                    JOptionPane.showMessageDialog(null, "Такого пункта не существует! Введите заново!");
            }
        }
    }
}