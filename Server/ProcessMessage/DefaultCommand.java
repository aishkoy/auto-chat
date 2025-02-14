package ProcessMessage;

public class DefaultCommand implements MessageCommand {

    @Override
    public String execute(String message) {
        return message;
    }
}
