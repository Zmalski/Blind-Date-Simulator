import javafx.event.*;

public abstract class chatEvent extends Event {

    public static final EventType<chatEvent> CHAT_EVENT = new EventType(ANY);

    public chatEvent(EventType<? extends Event> eventType) {
        super(eventType);
    }

    public abstract void invokeHandler(chatEventHandler handler);

}