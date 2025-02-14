package ProcessMessage;

public class UpperCommand implements MessageCommand {

    @Override
    public String execute(String message) {
        String[] parts = message.split(" ", 2);
        return parts.length > 1 ? parts[1].toUpperCase() : "";
    }

}
