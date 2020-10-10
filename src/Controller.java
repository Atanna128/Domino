import java.io.*;
import java.net.URL;
import java.util.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Controller implements Initializable{
    public ListView<String> dominoList;
    public Button choosenTrain;
    public TextArea train0,train1,train2,train3,train4;
    public void mexicanTrain(ActionEvent event) throws IOException {
        choosenTrain.setText("Mexican Train");
    }

    public void player1Train(ActionEvent event) throws IOException {
        choosenTrain.setText("Train 1");
    }

    public void player2Train(ActionEvent event) throws IOException {
        choosenTrain.setText("Train 2");
    }

    public void player3Train(ActionEvent event) throws IOException {
        choosenTrain.setText("Train 3");
    }

    public void player4Train(ActionEvent event) throws IOException {
        choosenTrain.setText("Train 4");
    }

    public void select(ActionEvent event)throws IOException {
        String name = dominoList.getSelectionModel().getSelectedItem();
        boolean success = false;
        String current = choosenTrain.getText();
        if (current.contains("Mexican")){
            train0.setEditable(true);
            train0.setText(train0.getText()+ "  [" + name + "] ");
            train0.setEditable(false);
            dominoList.getItems().remove(name);
        }else if (current.contains("1")){
            train1.setEditable(true);
            train1.setText(train1.getText()+ "  [" + name + "] ");
            train1.setEditable(false);
            dominoList.getItems().remove(name);
        }else if (current.contains("2")){
            train2.setEditable(true);
            train2.setText(train2.getText()+ "  [" + name + "] ");
            train2.setEditable(false);
            dominoList.getItems().remove(name);
        }else if (current.contains("3")){
            train3.setEditable(true);
            train3.setText(train3.getText()+ "  [" + name + "] ");
            train3.setEditable(false);
            dominoList.getItems().remove(name);
        }else if (current.contains("4")){
            train4.setEditable(true);
            train4.setText(train4.getText()+ "  [" + name + "] ");
            train4.setEditable(false);
            dominoList.getItems().remove(name);
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        domino[] dominos = new domino[] {
                new domino(2, 4),
                new domino(4, 2),
                new domino(2, 5),
                new domino(5, 2),
                new domino(4, 2),
                new domino(2, 5),
                new domino(5, 2),
                new domino(2, 5),
                new domino(5, 2),
                new domino(2, 5),
                new domino(5, 2),
        };
        for (domino domino: dominos) {
            dominoList.getItems().add(domino.getLeftValue()+" | "+domino.getRightValue());
        }
        train0.setEditable(false);
        train1.setEditable(false);
        train2.setEditable(false);
        train3.setEditable(false);
        train4.setEditable(false);
    }
}
