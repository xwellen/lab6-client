package Command.ConcreteCommand;

import Command.Command;
import Command.CommandReceiver;

import java.io.IOException;

public class Add extends Command {
    private static final long serialVersionUID = 32L;
    transient private CommandReceiver commandReceiver;

    public Add (CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    public Add() {}

    @Override
    protected void execute(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        if (args.length > 1) {
            System.out.println("Введен не нужный аргумент. Команда приведена к базовой команде add.");
        }
        commandReceiver.add();
    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда add – добавить новый элемент в коллекцию.");
    }
}
