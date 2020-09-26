package Utils.EnumReaders;

import BaseClass.AstartesCategory;

import java.util.Arrays;
import java.util.Scanner;

public class AstartesCategoryReader {
    public static boolean checkExist(String toContains) {
        return Arrays.stream(AstartesCategory.values()).anyMatch((astartesCategory) -> astartesCategory.name().equals(toContains));
    }

    public static AstartesCategory read(String messageForConsole, boolean canBeNull) {
        Scanner in = new Scanner(System.in);
        System.out.print(messageForConsole + " Выберите должность из представленных(" + Arrays.asList(AstartesCategory.values()) + "): ");
        String toContains = in.nextLine().trim();

        if ((!checkExist(toContains)) && !canBeNull && !toContains.equals("") || !canBeNull && toContains.equals("") || canBeNull && !toContains.equals("")) {
            while (!checkExist(toContains)) {
                System.out.print("Вы ввели несуществующее значение из представленных. Попробуйте снова: ");
                toContains = in.nextLine().trim();
                checkExist(toContains);
            }
        }

        if (canBeNull && toContains.equals("")) { return null; }

        return Enum.valueOf(AstartesCategory.class, toContains);
    }
}
