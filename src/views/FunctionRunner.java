package views;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

public class FunctionRunner {

    public static void runInFXThread(Runnable function, Duration duration) {
        PauseTransition pause = new PauseTransition(duration);
        pause.setOnFinished(event -> {
            Platform.runLater(function);
        });
        pause.play();
    }
}
