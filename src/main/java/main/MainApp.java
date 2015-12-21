package main;

import main.hangulApp.vcontroller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
//
import model.classes.Symbol;
import model.classes.Hangul;

public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        Symbol s = new Symbol("a", 2);
        Hangul h = new Hangul("test", 3);

        this.primaryStage.setTitle(h.romanize(21,12,1));

        initRootLayout();

        showHangulView();
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getClassLoader().getResource("main/hangulApp/view/RootLayout.fxml"));
            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            primaryStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void showHangulView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getClassLoader().getResource("main/hangulApp/view/TextHangulView.fxml"));
            AnchorPane hangulView = loader.load();

            rootLayout.setCenter(hangulView);

            // give controller access to main app
            TextHangulViewController textHangulVC = loader.getController();
            textHangulVC.setMainApp(this);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
