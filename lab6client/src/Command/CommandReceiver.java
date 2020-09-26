package Command;

import BaseClass.SpaceMarine;
import Client.Receiver;
import Client.Sender;
import Command.SerializedCommands.*;
import Utils.ElementCreator;
import Command.ConcreteCommand.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

public class CommandReceiver {
    private final CommandInvoker commandInvoker;
    private final Sender sender;
    private final SocketChannel socketChannel;
    private final Integer delay;
    private final ElementCreator elementCreator;

    public CommandReceiver(CommandInvoker commandInvoker, Sender sender, SocketChannel socketChannel, Integer delay, ElementCreator elementCreator) {
        this.commandInvoker = commandInvoker;
        this.sender = sender;
        this.socketChannel = socketChannel;
        this.delay = delay;
        this.elementCreator = elementCreator;
    }

    public void help() {
        commandInvoker.getCommandMap().forEach((name, command) -> command.writeInfo());
    }

    public void info() throws IOException, ClassNotFoundException, InterruptedException {
        sender.sendObject(new SerializedSimplyCommand(new Info()));
        Thread.sleep(delay);
        Receiver.receive(socketChannel);
    }

    public void show() throws IOException, ClassNotFoundException, InterruptedException {
        sender.sendObject(new SerializedSimplyCommand(new Show()));
        Thread.sleep(delay);
        Receiver.receive(socketChannel);
    }

    public void add() throws IOException, InterruptedException, ClassNotFoundException {
        sender.sendObject(new SerializedObjectCommand(new Add(), elementCreator.createSpaceMarine()));
        Thread.sleep(delay);
        Receiver.receive(socketChannel);
    }

    public void update(String ID) throws IOException, InterruptedException, ClassNotFoundException {
        sender.sendObject(new SerializedCombinedCommand(new Update(), elementCreator.createSpaceMarine(), ID));
        Thread.sleep(delay);
        Receiver.receive(socketChannel);
    }

    /**
     *
     * @param ID - удаление по ID.
     */
    public void remove_by_id(String ID) throws IOException, InterruptedException, ClassNotFoundException {
        sender.sendObject(new SerializedArgumentCommand(new RemoveByID(), ID));
        Thread.sleep(delay);
        Receiver.receive(socketChannel);
    }

    public void clear() throws IOException, InterruptedException, ClassNotFoundException {
        sender.sendObject(new SerializedSimplyCommand(new Clear()));
        Thread.sleep(delay);
        Receiver.receive(socketChannel);
    }

    public void exit() throws IOException {
        socketChannel.close();
        System.out.println("Завершение работы клиента.");
        System.exit(0);
    }

    public void executeScript(String path) {
        String line;
        String command;
        ArrayList<String> parameters = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            while ((line = bufferedReader.readLine()) != null) {
                if (line.split(" ")[0].matches("add|update|remove_greater")) {
                    parameters.clear();
                    command = line;
                    for (int i = 0; i < 11; i++) {
                        if (line != null) {
                            line = bufferedReader.readLine();
                            parameters.add(line);
                        } else { System.out.println("Не хватает параметров для создания объекта."); break; }
                    }
                    SpaceMarine spaceMarine = elementCreator.createScriptSpaceMarine(parameters);
                    if (spaceMarine != null) {
                        switch (command.split(" ")[0]) {
                            case "add":
                                sender.sendObject(new SerializedObjectCommand(new Add(), spaceMarine));
                                Thread.sleep(delay);
                                Receiver.receive(socketChannel);
                                break;
                            case "update":
                                sender.sendObject(new SerializedCombinedCommand(new Update(), elementCreator.createSpaceMarine(), command.split(" ")[1]));
                                Thread.sleep(delay);
                                Receiver.receive(socketChannel);
                                break;
                            case "remove_greater":
                                sender.sendObject(new SerializedObjectCommand(new RemoveGreater(), spaceMarine));
                                Thread.sleep(delay);
                                Receiver.receive(socketChannel);
                                break;
                        }
                    }
                } else if (line.split(" ")[0].equals("execute_script")
                        && line.split(" ")[1].equals(ExecuteScript.getPath())) { System.out.println("Пресечена попытка рекурсивного вызова скрипта."); }
                else { commandInvoker.executeCommand(line.split(" ")); }
            }
        } catch (IOException | InterruptedException | ClassNotFoundException e) {
            System.out.println("Ошибка! " + e.getMessage());
        }
    }

    public void remove_greater() throws IOException, InterruptedException, ClassNotFoundException {
        sender.sendObject(new SerializedObjectCommand(new RemoveGreater(), elementCreator.createSpaceMarine()));
        Thread.sleep(delay);
        Receiver.receive(socketChannel);
    }

    public void add_if_min() throws IOException, InterruptedException, ClassNotFoundException {
        sender.sendObject(new SerializedObjectCommand(new AddIfMin(), elementCreator.createSpaceMarine()));
        Thread.sleep(delay);
        Receiver.receive(socketChannel);
    }

    public void average_of_health() throws IOException, InterruptedException, ClassNotFoundException {
        sender.sendObject(new SerializedSimplyCommand(new AverageOfHealth()));
        Thread.sleep(delay);
        Receiver.receive(socketChannel);
    }

    public void count_greater_than_weapon_type(String arg) throws IOException, InterruptedException, ClassNotFoundException {
        sender.sendObject(new SerializedArgumentCommand(new CountGreaterThanWeaponType(), arg));
        Thread.sleep(delay);
        Receiver.receive(socketChannel);
    }

    public void filter_by_health(String arg) throws IOException, InterruptedException, ClassNotFoundException {
        sender.sendObject(new SerializedArgumentCommand(new FilterByHealth(), arg));
        Thread.sleep(delay);
        Receiver.receive(socketChannel);;
    }

    public void remove_first() throws IOException, InterruptedException, ClassNotFoundException {
        sender.sendObject(new SerializedSimplyCommand(new RemoveFirst()));
        Thread.sleep(delay);
        Receiver.receive(socketChannel);
    }


}
