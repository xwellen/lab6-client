package Command.ConcreteCommand;

import Command.CommandReceiver;
import Command.Command;

import java.io.IOException;

public class Exit extends Command {
    private static final long serialVersionUID = 32L;
    transient private CommandReceiver commandReceiver;

    public Exit(CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    public Exit() {}


    @Override
    protected void execute(String[] args) throws IOException {
        if (args.length > 1) {
            System.out.println("Введен не нужный аргумент. Команда приведена к базовой команде exit.");
        }
        commandReceiver.exit();
    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда exit – завершить программу (без сохранения в файл).");
    }
}
