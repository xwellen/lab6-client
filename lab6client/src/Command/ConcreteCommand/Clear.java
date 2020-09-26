package Command.ConcreteCommand;

import Command.Command;
import Command.CommandReceiver;

import java.io.IOException;

public class Clear extends Command {
    private static final long serialVersionUID = 32L;
    transient private CommandReceiver commandReceiver;

    public Clear(CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    public Clear() {}

    @Override
    protected void execute(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        if (args.length > 1) {
            System.out.println("Введен не нужный аргумент. Команда приведена к базовой команде clear.");
        }
        commandReceiver.clear();
    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда clear – очистить коллекцию.");
    }
}
