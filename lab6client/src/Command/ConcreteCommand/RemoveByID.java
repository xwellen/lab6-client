package Command.ConcreteCommand;

import Command.Command;
import Command.CommandReceiver;

import java.io.IOException;

public class RemoveByID extends Command {
    private static final long serialVersionUID = 32L;
    transient private CommandReceiver commandReceiver;

    public RemoveByID(CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    public RemoveByID() {}

    @Override
    protected void execute(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        if (args.length == 2) { commandReceiver.remove_by_id(args[1]); }
        else { System.out.println("Некорректное количество аргументов. Для справки напишите help."); }
    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда remove_by_id. Синтаксис: remove_by_id id – удалить элемент из коллекции по его id.");
    }
}
