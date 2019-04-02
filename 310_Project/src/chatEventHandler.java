import javafx.event.*;

public abstract class chatEventHandler implements EventHandler<chatEvent> {

    public abstract void onEvent1(int param0);

    public abstract void onEvent2(String param0);

    @Override
    public void handle(chatEvent event) {
        event.invokeHandler(this);
    }
}