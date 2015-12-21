package main.hangulApp.vcontroller;

import main.MainApp;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * Created by Hien Nguyen on 09.12.2015.
 */
public class RootLayoutController {
    private MainApp mainApp;

    private void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void handleImport() {
        FileChooser fileChooser = new FileChooser();

        // TODO add doc/docx file support
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "Text files (*.txt)", "*txt");

        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }
}
