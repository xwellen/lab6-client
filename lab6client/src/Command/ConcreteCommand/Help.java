package Command.ConcreteCommand;

import Command.Command;
import Command.CommandReceiver;

public class Help extends Command {
    private static final long serialVersionUID = 32L;
    transient private CommandReceiver commandReceiver;

    public Help(CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    public Help() {}

    @Override
    protected void execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Введен не нужный аргумент. Команда приведена к базовой команде help.");
        }
        commandReceiver.help();
    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда help – получить справку по доступным командам.");
    }

}
