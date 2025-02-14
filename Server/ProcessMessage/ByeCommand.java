package ProcessMessage;

public class ByeCommand implements MessageCommand {
    @Override
    public String execute(String message) {
        return "Bye bye!";
    }
}
