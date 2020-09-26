package Command.ConcreteCommand;

import Command.Command;
import Command.CommandReceiver;

import java.io.IOException;


public class RemoveFirst extends Command{
    private static final long serialVersionUID = 32L;
    transient private CommandReceiver commandReceiver;

    public RemoveFirst(CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    public RemoveFirst() {}

    @Override
    protected void execute(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        if (args.length > 1) {
            System.out.println("Введен не нужный аргумент. Команда приведена к базовой команде remove_first.");
        }
        commandReceiver.remove_first();
    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда remove_first – удалить первый элемент из коллекции.");
    }
}