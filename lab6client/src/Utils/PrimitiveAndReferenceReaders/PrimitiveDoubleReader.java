package Utils.PrimitiveAndReferenceReaders;

import java.util.Scanner;

public class PrimitiveDoubleReader {
    public static double read(String messageForConsole, double limit, String type) {
        System.out.print(messageForConsole);
        Scanner sc = new Scanner(System.in);
        double result = 0;
        boolean end = false;
        while (!end) {
            try {
                result = Double.parseDouble(sc.nextLine().trim());
                switch (type) {
                    case ("MIN"):
                        if (result > limit) { end = true; }
                        else { System.out.print("Вы ввели не подходящее значение. " + "Оно должно быть больше " + limit + ". Попробуйте снова: ");}
                        break;
                    case ("MAX"):
                        if (result < limit) { end = true; }
                        else { System.out.print("Вы ввели не подходящее значение. " + "Оно должно быть меньше " + limit + ". Попробуйте снова: ");}
                        break;
                }
            } catch (NumberFormatException ex) {
                System.out.print("Вы должны ввести число, попробуйте снова: ");
            }
        }

        return result;
    }
}
