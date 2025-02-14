package ProcessMessage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateCommand implements MessageCommand {
    @Override
    public String execute(String message) {
        return LocalDate.now().format(DateTimeFormatter.ISO_DATE);
    }
}
