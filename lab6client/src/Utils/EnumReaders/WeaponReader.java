package Utils.EnumReaders;

import BaseClass.Weapon;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class WeaponReader {
    public static boolean checkExist(String toContains) {
        return Arrays.stream(Weapon.values()).anyMatch((weapon) -> weapon.name().equals(toContains));
    }

    public static Weapon parseWeapon(String stringWeapon) throws IOException {
        switch (stringWeapon) {
            case "BOLTGUN":
                return Weapon.BOLTGUN;
            case "MELTAGUN":
                return Weapon.MELTAGUN;
            case "COMBI_FLAMER":
                return Weapon.COMBI_FLAMER;
            case "FLAMER":
                return Weapon.FLAMER;
            case "INFERNO_PISTOL":
                return Weapon.INFERNO_PISTOL;
            default:
                throw new IOException("Несуществующее оружие");
        }
    }

    public static Weapon read(String messageForConsole, boolean canBeNull) {
        Scanner in = new Scanner(System.in);
        System.out.print(messageForConsole + " Выберите оружие из представленных(" + Arrays.asList(Weapon.values()) + "): ");
        String toContains = in.nextLine().trim();

        if ((!checkExist(toContains)) && !canBeNull && !toContains.equals("") || !canBeNull && toContains.equals("") || canBeNull && !toContains.equals("")) {
            while (!checkExist(toContains)) {
                System.out.print("Вы ввели несуществующее значение из представленных. Попробуйте снова: ");
                toContains = in.nextLine().trim();
                checkExist(toContains);
            }
        }

        if (canBeNull && toContains.equals("")) { return null; }

        return Enum.valueOf(Weapon.class, toContains);
    }
}
