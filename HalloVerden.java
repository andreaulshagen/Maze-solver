import javafx.application.Application;
import javafx.stage.Stage;

public class HalloVerden extends Application {
  @Override public void start(Stage teater) { // Sett opp scene og kulisser
    teater.setTitle("Overskrift"); // teater.setScene(scene);
    teater.show();
}

public static void main(String[] args) {
  launch(args);
  }
}
