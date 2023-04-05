package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class HelloController {
    @FXML private Label welcomeText;
    @FXML private VBox myVbox;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
        // create a FileChooser instance
        FileChooser fileChooser = new FileChooser();

        // set the title of the dialog box
        fileChooser.setTitle("Open File");

        // add filters to restrict the type of files that can be selected
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        // show the file chooser dialog box
        Stage stage = (Stage) myVbox.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        // check if a file was selected and do something with it
        if (selectedFile != null) {
            System.out.println(selectedFile.getAbsolutePath());
            String content = null;
            try {
                content = readFile(new File(selectedFile.getAbsolutePath()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(content);
        }
    }

    public static String readFile(File file) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStream in =new LowerCaseInputStream(new FileInputStream(file));
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append(System.lineSeparator());
        }

        return sb.toString();
    }
}