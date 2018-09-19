import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;

import java.io.FileNotFoundException;
import java.lang.Object;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import java.io.File;
import java.util.Scanner;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.*;
import javafx.event.*;

public class Main extends Application {
    private Pane pane;
    private Labyrint l = null;
    private  Button filKnapp;
    private Stage stage;
    private BorderPane borderPane;
    private GridPane gridPane;
    static String filnavn = "tekstFil.txt";
    private Labyrint lab;
    Text startinfo;
    Button stoppknapp;
    Button ruteKnapp;
    Image img1;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Labrint");

        stoppknapp = new Button("Stopp");
        stoppknapp.setLayoutX(150); stoppknapp.setLayoutY(10);
        Stoppbehandler stopp = new Stoppbehandler();
        stoppknapp.setOnAction(stopp);

        FileChooser fileChooser = new FileChooser();
        filKnapp = new Button("Velg Labyrint");
        filKnapp.setLayoutX(10);
        filKnapp.setLayoutY(10);

        filKnapp.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        File file = fileChooser.showOpenDialog(stage);
                        if (file != null) {
                            openFile(file);
                            System.out.println("fil er ikke null");
                            gridPane = new GridPane();

                        int teller = 0;
                            for (int i=0; i<l.hoyde;i++) {
                                for (int j = 0; j < l.bredde; j++) {
                                    Rute r = l.ruter[i][j];
                                    finnUtvei klikk = new finnUtvei(r);
                                    boolean hvitRute = r.tilTegn()=='.'||r.tilTegn()=='A';
                                    String farge = hvitRute ? "FFFFFF" : "000000";
                                    r.setStyle("-fx-background-color:#farge;".replace("farge", farge));
                                    if(hvitRute)r.setOnAction(klikk);
                                    else{r.getStylesheets().add("addBobOk.css");};

                                    gridPane.add(r, j, i);

                                }
                            }

                            System.out.println(teller);
                            gridPane.setGridLinesVisible(true);
                            gridPane.setLayoutX(l.hoyde);
                            gridPane.setLayoutY(l.bredde);




                            pane = new Pane();
                            pane.setPrefSize(700, 900);
                            pane.getChildren().addAll(gridPane);
                            Scene tmpScene = new Scene(pane);
                            tmpScene.getStylesheets().add("addBobOk.css");
                            primaryStage.setScene(tmpScene);
                            primaryStage.show();
                        }
                    }
                });


        Pane startPane = new Pane();
        startPane.setPrefSize(300, 60);
        startPane.getChildren().add(stoppknapp);
        startPane.getChildren().add(filKnapp);
        Scene tmpScene = new Scene(startPane);
        tmpScene.getStylesheets().add("addBobOk.css");
        primaryStage.setScene(tmpScene);
        primaryStage.show();

    }

    public void openFile(File fil){


        try {
            l = Labyrint.lesFraFil(fil);
        } catch (FileNotFoundException e) {
            System.out.printf("FEIL: Kunne ikke lese fra '%s'\n", filnavn);
            System.exit(1);
        }

    class finnUtvei implements EventHandler<ActionEvent> {
        Rute r = null;

        public finnUtvei(Rute r) {
            this.r = r;
        }

        @Override
        public void handle(ActionEvent e) {

            l.finnUtveiFra(r.kol, r.rad);
            Text tellerInfo = new Text();



            l.utveier.size();
            if(l.utveier.size() == 0){
                tellerInfo.setText("sorry du er innelåst fra denne posisjonen, ingenting å gjøre med det.");
                tellerInfo.setFont(new Font(20));
                tellerInfo.setX(10);
                tellerInfo.setY(550);
                pane.getChildren().addAll(tellerInfo);
            }else{

                tellerInfo.setFont(new Font(20));
                tellerInfo.setX(10);
                tellerInfo.setY(500);
                tellerInfo.setText("Antall mulige utganger: " + Integer.toString(l.utveier.size()));
                pane.getChildren().addAll(tellerInfo);

                boolean[][] losning = null;

                for (String s : l.utveier) {
                    losning = losningStringTilTabell(s, l.bredde, l.hoyde);
                }

                for (int i = 0; i < losning.length; i++) {
                    for (int j = 0; j < losning[i].length; j++) {
                        Rute r = l.ruter[i][j];
                        if (losning[i][j]) {
                            r.setStyle("-fx-background-color:#FFFF00");
                        }
                    }
                }
            }
        }
    }

    class Stoppbehandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            Platform.exit();
        }
    }

    static boolean[][] losningStringTilTabell(String losningString, int bredde, int hoyde) {
        boolean[][] losning = new boolean[hoyde][bredde];
        java.util.regex.Pattern p = java.util.regex.Pattern.compile("\\(([0-9]+),([0-9]+)\\)");
        java.util.regex.Matcher m = p.matcher(losningString.replaceAll("\\s",""));
        while (m.find()) {
            int x = Integer.parseInt(m.group(1));
            int y = Integer.parseInt(m.group(2));
            losning[y][x] = true;
        }
        return losning;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
