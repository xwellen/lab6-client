package Command.ConcreteCommand;

import Command.Command;
import Command.CommandReceiver;

import java.io.IOException;

public class RemoveGreater extends Command {
    private static final long serialVersionUID = 32L;
    transient private CommandReceiver commandReceiver;

    public RemoveGreater(CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    public RemoveGreater() {}

    @Override
    protected void execute(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        if (args.length > 1) {
            System.out.println("Введен не нужный аргумент. Команда приведена к базовой команде remove_greater.");
        }
        commandReceiver.remove_greater();
    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда remove_greater – удалить из коллекции все элементы, превышающие заданный.");
    }
}
