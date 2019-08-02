package View;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * @author Ethan Ho
 */
public class UserInput extends HBox {

    private static final Integer BUTTON_WIDTH = 150;
    private static final Integer BUTTON_HEIGHT = 2;
    private static final Integer COMMAND_PROMPT_WIDTH = 500;

    Integer inputState = 0;
    TextField commandLine = new TextField();
    TextArea commandArea = new TextArea();
    VBox buttonBox = new VBox();

    public UserInput(){
        Label title = new Label("Enter command: ");
        commandLine.setFont(Font.font("Monospaced", 14));
        commandLine.setPrefWidth(COMMAND_PROMPT_WIDTH);
        commandArea.setFont(Font.font("Monospaced", 14));
        commandArea.setPrefWidth(COMMAND_PROMPT_WIDTH);
        createButtons();
        getChildren().addAll(title, commandLine, buttonBox);
    }

    public void createTextField(){
        getChildren().add(1, commandLine);
        inputState = 0;
    }

    public void createTextArea(){
        getChildren().add(1, commandArea);
        inputState = 1;
    }

    public void createButtons(){
        Button singleLine = new Button("Single line input");
        Button multiLine = new Button("Multiple line input");
        singleLine.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        multiLine.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        singleLine.setOnAction(e -> {
            getChildren().remove(1);
            createTextField();
        });
        multiLine.setOnAction(e -> {
            getChildren().remove(1);
            createTextArea();
        });
        buttonBox.getChildren().addAll(singleLine, multiLine);
    }

    public String getText(){
        if(inputState == 0){
            return commandLine.getText();
        }else if(inputState == 1){
            return commandArea.getText();
        }
        return "invalid";
    }

    public void setText(String instruction){
        if(inputState == 0){
            commandLine.setText(instruction);
        }else if(inputState == 1){
            commandArea.setText(instruction);
        }
    }

    public void clearText(){
        if(inputState == 0){
            commandLine.clear();
        }else if(inputState == 1){
            commandArea.clear();
        }
    }
}
