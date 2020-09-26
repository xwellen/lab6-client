package Command.ConcreteCommand;

import Command.Command;
import Command.CommandReceiver;

public class CountGreaterThanWeaponType extends Command {
    private static final long serialVersionUID = 32L;
    transient private CommandReceiver commandReceiver;

    public CountGreaterThanWeaponType(CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    public CountGreaterThanWeaponType() {}


    @Override
    protected void execute(String[] args) {
        if (args.length == 2) { commandReceiver.count_greater_than_weapon_type(args[1]); }
        else { System.out.println("Некорректное количество аргументов. Для справки напишите help."); }
    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда count_greater_than_weapon_type. Синтаксис: count_greater_than_weapon_type weaponType – вывести количество элементов, значение поля weaponType которых больше заданного");
    }
}
