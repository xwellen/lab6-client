package Command.ConcreteCommand;

import Command.Command;
import Command.CommandReceiver;

import java.io.IOException;

public class Info extends Command {
    private static final long serialVersionUID = 32L;
    transient private CommandReceiver commandReceiver;

    public Info(CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    public Info() {}

    @Override
    protected void execute(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        if (args.length > 1) {
            System.out.println("Введен не нужный аргумент. Команда приведена к базовой команде info.");
        }
        commandReceiver.info();
    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда info – вывести в стандартный поток вывода информацию о коллекции.");
    }
}
