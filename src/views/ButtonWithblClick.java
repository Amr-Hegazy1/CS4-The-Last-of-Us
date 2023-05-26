package views;

import java.util.concurrent.CompletableFuture;

import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.event.*;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.*;

public class ButtonWithblClick extends Button {

    private long        singleClickDelayMillis  = 250;
    private ClickRunner latestClickRunner       = null;

    private ObjectProperty<EventHandler<MouseEvent>>    onMouseSingleClickedProperty    = new SimpleObjectProperty<>();
    private ObjectProperty<EventHandler<MouseEvent>>    onMouseDoubleClickedProperty    = new SimpleObjectProperty<>();

    // CONSTRUCTORS
    public ButtonWithblClick() {
        super();
        addClickedEventHandler();
    }

    public ButtonWithblClick(String text) {
        super(text);
        addClickedEventHandler();
    }

    public ButtonWithblClick(String text, Node graphic) {
        super(text, graphic);
        addClickedEventHandler();
    }

    private class ClickRunner implements Runnable {

        private final Runnable  onClick;
        private boolean         aborted = false;

        public ClickRunner(Runnable onClick) {
            this.onClick = onClick;
        }

        public void abort() {
            this.aborted = true;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(singleClickDelayMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!aborted) {
                Platform.runLater(onClick::run);
            }
        }
    }

    private void addClickedEventHandler() {
        //Handling the mouse clicked event (not using 'onMouseClicked' so it can still be used by developer).
        EventHandler<MouseEvent> eventHandler = me -> {
            switch (me.getButton()) {
                case PRIMARY:
                    if (me.getClickCount() == 1) {
                        latestClickRunner = new ClickRunner(() -> {
//                            System.out.println("ButtonWithDblClick : SINGLE Click fired");
                            onMouseSingleClickedProperty.get().handle(me);
                        });
                        CompletableFuture.runAsync(latestClickRunner);
                    }
                    if (me.getClickCount() == 2) {
                        if (latestClickRunner != null) {
                            latestClickRunner.abort();
                        }
//                        System.out.println("ButtonWithDblClick : DOUBLE Click fired");
                        onMouseDoubleClickedProperty.get().handle(me);
                    }
                    break;
                case SECONDARY:
                    // Right-click operation. Not implemented since usually no double RIGHT click needs to be caught.
                    break;
                default:
                    break;
            }
        };
        //Adding the event handler
        addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
    }

    public void setOnMouseSingleClicked(EventHandler<MouseEvent> eventHandler) {
        this.onMouseSingleClickedProperty.set(eventHandler);
    }

    public void setOnMouseDoubleClicked(EventHandler<MouseEvent> eventHandler) {
        this.onMouseDoubleClickedProperty.set(eventHandler);
    }
    public long getSingleClickDelayMillis() {
        return singleClickDelayMillis;
    }

    public void setSingleClickDelayMillis(long singleClickDelayMillis) {
        this.singleClickDelayMillis = singleClickDelayMillis;
    }

}
