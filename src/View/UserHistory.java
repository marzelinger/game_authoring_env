package View;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;

import java.util.LinkedList;

public class UserHistory extends ScrollPane {

    private static final Integer HISTORY_BOX_WIDTH = 200;
    private static final Integer HISTORY_BOX_HEIGHT = 100;

    private VBox commandLineHistory = new VBox();
    private String instruction;
    private LinkedList<String> instructionHistory = new LinkedList<>();
    private Integer instructionIndex;
    private boolean prevDirection = true;

    public UserHistory(){
        super();
        setPrefSize(HISTORY_BOX_WIDTH, HISTORY_BOX_HEIGHT);
        setContent(commandLineHistory);
    }

    public void updateUserHistory(Text newInstruction){
        commandLineHistory.getChildren().add(newInstruction);
        instructionHistory.add(newInstruction.getText());
        instructionIndex = instructionHistory.size()-1;

        vvalueProperty().bind(commandLineHistory.heightProperty());
        setContent(commandLineHistory);
    }

    public String cycleHistory(boolean direction) {
        if(direction != prevDirection){
            if(prevDirection){
                instructionIndex += 2;
            }else{
                instructionIndex -= 2;
            }
        }
        if(direction){
            try{
                instruction = instructionHistory.get(instructionIndex);
                if(instructionIndex >= 0){
                    instructionIndex--;
                }
            }catch(IndexOutOfBoundsException e){
                if(instructionIndex > -2){
                    instructionIndex--;
                }
                return "";
            }
        }else{
            try{
                instruction = instructionHistory.get(instructionIndex);
                if(instructionIndex <= instructionHistory.size() - 1) {
                    instructionIndex++;
                }
            }catch(IndexOutOfBoundsException e){
                if(instructionIndex < instructionHistory.size() + 1)
                    instructionIndex++;
                return "";
            }
        }
        prevDirection = direction;
        return instruction;
    }
}
