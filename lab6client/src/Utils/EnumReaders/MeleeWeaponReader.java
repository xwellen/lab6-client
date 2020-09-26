package Utils.EnumReaders;

import BaseClass.MeleeWeapon;

import java.util.Arrays;
import java.util.Scanner;

public class MeleeWeaponReader {
    public static boolean checkExist(String toContains) {
        return Arrays.stream(MeleeWeapon.values()).anyMatch((meleeWeapon) -> meleeWeapon.name().equals(toContains));
    }

    public static MeleeWeapon read(String messageForConsole, boolean canBeNull) {
        Scanner in = new Scanner(System.in);
        System.out.print(messageForConsole + " Выберите оружие ближнего боя из представленных(" + Arrays.asList(MeleeWeapon.values()) + "): ");
        String toContains = in.nextLine().trim();

        if ((!checkExist(toContains)) && !canBeNull && !toContains.equals("") || !canBeNull && toContains.equals("") || canBeNull && !toContains.equals("")) {
            while (!checkExist(toContains)) {
                System.out.print("Вы ввели несуществующее значение из представленных. Попробуйте снова: ");
                toContains = in.nextLine().trim();
                checkExist(toContains);
            }
        }

        if (canBeNull && toContains.equals("")) { return null; }

        return Enum.valueOf(MeleeWeapon.class, toContains);
    }
}
