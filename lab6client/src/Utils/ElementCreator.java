package Utils;

import BaseClass.*;
import Utils.EnumReaders.AstartesCategoryReader;
import Utils.EnumReaders.MeleeWeaponReader;
import Utils.EnumReaders.WeaponReader;
import Utils.PrimitiveAndReferenceReaders.PrimitiveDoubleReader;
import Utils.PrimitiveAndReferenceReaders.RefLongReader;
import Utils.PrimitiveAndReferenceReaders.StringReader;

import java.util.ArrayList;

public class ElementCreator {
    public static SpaceMarine createSpaceMarine() {
        String name = StringReader.read("Введите имя бойца: ", false);
        Long x = RefLongReader.read("Введите X: ", false, 878, "MAX");
        double y = PrimitiveDoubleReader.read("Введите Y: ", Long.MAX_VALUE, "MAX");
        long health = RefLongReader.read("Введите показатель здоровья: ", false, 0, "MIN");
        AstartesCategory astartesCategory = AstartesCategoryReader.read("",true);
        Weapon weapon = WeaponReader.read("",true);
        MeleeWeapon meleeWeapon = MeleeWeaponReader.read("", false);
        String chapterName = StringReader.read("Введите название главы: ", true);
        String chapterWorld = StringReader.read("Ввеите название мира:", true);

        return new SpaceMarine(name, new Coordinates(x, y), health, astartesCategory, weapon, meleeWeapon, new Chapter(chapterName, chapterWorld));
    }


    public static SpaceMarine createScriptSpaceMarine(ArrayList<String> parameters) {
        if (validateArray(parameters)) {
            AstartesCategory astartesCategory = null;
            Weapon weapon = null;
            Chapter chapter = new Chapter(null, null);
            if (!parameters.get(4).isEmpty()) { astartesCategory = AstartesCategory.valueOf(parameters.get(4)); }
            if (!parameters.get(5).isEmpty()) { weapon = Weapon.valueOf(parameters.get(5)); }
            if (!parameters.get(7).isEmpty()) { chapter.setName(chapter.getName().valueOf(parameters.get(7))); }
            if (!parameters.get(8).isEmpty()) { chapter.setWorld(chapter.getWorld().valueOf(parameters.get(8))); }
            return new SpaceMarine(parameters.get(0),
                    new Coordinates(Long.parseLong(parameters.get(1)), Double.parseDouble(parameters.get(2))),
                    Long.parseLong(parameters.get(3)),
                    astartesCategory,
                    weapon,
                    MeleeWeapon.valueOf(parameters.get(6)),
                    chapter);
        } else { System.out.println("Один из параметров не соответствует требованиям."); }

        return null;
    }

    private static boolean validateArray(ArrayList<String> parameters) {
        try {
            return !parameters.get(0).isEmpty()
                    && Integer.parseInt(parameters.get(1)) < 878
                    && Long.parseLong(parameters.get(2)) < Long.MAX_VALUE
                    && Integer.parseInt(parameters.get(3)) > 0
                    && (AstartesCategoryReader.checkExist(parameters.get(4)) || parameters.get(4).isEmpty())
                    && (WeaponReader.checkExist(parameters.get(5)) || parameters.get(5).isEmpty())
                    && !parameters.get(6).isEmpty();

        } catch (NumberFormatException ex) { return false; }
    }
}
