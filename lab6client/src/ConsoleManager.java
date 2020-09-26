import Client.Sender;
import Client.Session;
import Command.CommandReceiver;
import Command.ConcreteCommand.*;
import Utils.ElementCreator;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleManager {
    void startInteractiveMode(String hostName, String port, String delayArg) throws IOException {
        Session session = null;
        int delay = 0;

        try {
            session = new Session(hostName, Integer.parseInt(port));
            session.startSession();
            delay = Integer.parseInt(delayArg);
            if (delay < 80) delay = 80;  // Минимальная задержка 80
        } catch (NumberFormatException ex) {
            System.out.println("Один из аргументов не соответствует требованием.\n" +
                    "Имя хоста должно быть текстовым значением, а порта и задержки(в мс) - целочисленным!");
            System.exit(0);
        } catch (InterruptedException ex){
            System.out.println(ex.getMessage());
        }

        Sender sender = new Sender(session);

        ElementCreator elementCreator = new ElementCreator();
        Command.CommandInvoker commandInvoker = new Command.CommandInvoker();
        CommandReceiver commandReceiver = new CommandReceiver(commandInvoker, sender, session.getSocketChannel(), delay, elementCreator);

        commandInvoker.register("exit", new Exit(commandReceiver));
        commandInvoker.register("info", new Info(commandReceiver));
        commandInvoker.register("show", new Show(commandReceiver));
        commandInvoker.register("help", new Help(commandReceiver));
        commandInvoker.register("add", new Add(commandReceiver));
        commandInvoker.register("clear", new Clear(commandReceiver));
        commandInvoker.register("remove_by_id", new RemoveByID(commandReceiver));
        commandInvoker.register("update", new Update(commandReceiver));
        commandInvoker.register("remove_first", new RemoveFirst(commandReceiver));
        commandInvoker.register("average_of_health", new AverageOfHealth(commandReceiver));
        commandInvoker.register("filter_by_health", new FilterByHealth(commandReceiver));
        commandInvoker.register("remove_greater", new RemoveGreater(commandReceiver));
        commandInvoker.register("count_greater_than_weapon_type", new CountGreaterThanWeaponType(commandReceiver));
        commandInvoker.register("add_if_min", new AddIfMin(commandReceiver));
        commandInvoker.register("execute_script", new ExecuteScript(commandReceiver));

        try(Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                commandInvoker.executeCommand(scanner.nextLine().trim().split(" "));
            }
        }

        session.closeSession();
    }


}
