import javafx.event.*;

public class genderEvent extends chatEvent {

    public static final EventType<chatEvent> CHAT_EVENT_GENDER = new EventType(CHAT_EVENT, "genderEvent");

    private final int param;

    public genderEvent(int param) {
        super(CHAT_EVENT_GENDER);
        this.param = param;
    }

    @Override
    public void invokeHandler(chatEventHandler handler) {
        handler.onEvent1(param);
    }


}