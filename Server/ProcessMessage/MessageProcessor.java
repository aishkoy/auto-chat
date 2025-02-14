package ProcessMessage;

import java.util.HashMap;
import java.util.Map;

public class MessageProcessor {
    private final Map<String, MessageCommand> commands = new HashMap<>();
    private final DefaultCommand defaultCommand = new DefaultCommand();

    public MessageProcessor() {
        commands.put("bye", new ByeCommand());
        commands.put("upper", new UpperCommand());
        commands.put("date", new DateCommand());
        commands.put("time", new TimeCommand());
        commands.put("reverse", new ReverseCommand());
    }

    public String processMessage(String message) {
        String firstWord = message.toLowerCase().split(" ", 2)[0];
        return commands.getOrDefault(firstWord, defaultCommand).execute(message);
    }
}
