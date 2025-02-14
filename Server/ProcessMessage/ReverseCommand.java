package ProcessMessage;

public class ReverseCommand implements MessageCommand {

    @Override
    public String execute(String message) {
        String[] parts = message.split(" ", 2);
        return parts.length > 1
                ? new StringBuilder(parts[1]).reverse().toString()
                : "";
    }
}
