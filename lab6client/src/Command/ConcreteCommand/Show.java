package Command.ConcreteCommand;

import Command.Command;
import Command.CommandReceiver;

import java.io.IOException;


public class Show extends Command{
    private static final long serialVersionUID = 32L;
    transient private CommandReceiver commandReceiver;

    public Show(CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    public Show() {}

    @Override
    protected void execute(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        if (args.length > 1) {
            System.out.println("Введен не нужный аргумент. Команда приведена к базовой команде show.");
        }
        commandReceiver.show();
    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда show – вывести все элементы коллекции в строковом представлении.");
    }
}
