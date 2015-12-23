package main.hangulApp.vcontroller;

import main.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.*;
import model.classes.Hangul;

// TODO: OnActionListener / OnChangeListener romanize Korean text as soon as it gets imported

public class TextHangulViewController {
    private Hangul hangul;

    private MainApp mainApp;

    @FXML
    private TextArea textAreaHangul;

    @FXML
    private TextArea textAreaRomanized;

    @FXML
    private void btn_clear() {
        textAreaHangul.clear();
    }

    @FXML
    private void btn_showHangulOnTextArea() {
        File file = loadFile();

        try {
            String hangulText = readFile(file);
            this.hangul = new Hangul(hangulText);
            textAreaHangul.appendText(hangulText);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void btn_testRomanize() {
        try {
            textAreaRomanized.appendText(hangul.romanize());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void initialize() {

    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    private File loadFile() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getClassLoader().getResource("src/main/java/main/view/RootLayout.fxml"));
        loader.getController();

        FileChooser fileChooser = new FileChooser();

        // TODO add doc/docx file support
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "Text files (*.txt)", "*txt");

        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if(file != null) {
            return file;
        }
        return null;
    }

    private String readFile(File file) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            final String encoding = "UTF8";
            FileInputStream fileInput = new FileInputStream(file);
            InputStreamReader inputStream = new InputStreamReader(fileInput, encoding);
            bufferedReader = new BufferedReader(inputStream);
            String text;
            while((text = bufferedReader.readLine()) != null) {
                sb.append(text);
                sb.append(System.lineSeparator());
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if(bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (NullPointerException npex) {
                npex.printStackTrace();
            }
        }

        return sb.toString();
    }

}
