package Command.ConcreteCommand;

import Command.Command;
import Command.CommandReceiver;

public class FilterByHealth extends Command {
    private static final long serialVersionUID = 32L;
    transient private CommandReceiver commandReceiver;

    public FilterByHealth(CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    public FilterByHealth() {}

    @Override
    protected void execute(String[] args) {
        if (args.length == 2) { commandReceiver.filter_by_health(args[1]); }
        else { System.out.println("Некорректное количество аргументов. Для справки напишите help."); }
    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда filter_by_health. Синтаксис: filter_by_health health – вывести элементы, значение поля health которых равно заданному");
    }
}
