package Client;



import java.io.IOException;

public class Decrypting {
    static void decrypt(Object o) throws IOException {
        if (o instanceof Command.SerializedCommands.SerializedMessage) {
            Command.SerializedCommands.SerializedMessage serializedMessage = (Command.SerializedCommands.SerializedMessage) o;
            System.out.println(((Command.SerializedCommands.SerializedMessage) o).getMessage());
        }
    }
}
