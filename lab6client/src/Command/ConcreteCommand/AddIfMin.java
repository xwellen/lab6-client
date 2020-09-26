package Command.ConcreteCommand;

import Command.Command;
import Command.CommandReceiver;

import java.io.IOException;

public class AddIfMin extends Command {
    private static final long serialVersionUID = 32L;
    transient private CommandReceiver commandReceiver;

    public AddIfMin(CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    public AddIfMin() {}

    @Override
    protected void execute(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        if (args.length > 1) {
            System.out.println("Введен не нужный аргумент. Команда приведена к базовой команде add_if_min.");
        }
        commandReceiver.add_if_min();
    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда add_if_min {element} – добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
    }
}
