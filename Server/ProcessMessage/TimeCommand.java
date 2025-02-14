package ProcessMessage;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeCommand implements MessageCommand{

    @Override
    public String execute(String message) {
        return LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) ;
    }
}
