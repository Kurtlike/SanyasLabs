import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import File.FileData;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import panels.*;

public class Controller {
    AtomicInteger size = new AtomicInteger();
    AtomicReference<ArrayList<Double>> matrix= new AtomicReference<>(new ArrayList<>());
    @FXML
    private Pane tablesPane=new Pane();
    @FXML
    private  TextField nValue = new TextField();
    @FXML
    private  Button receiver = new Button();
    @FXML
    private  TextArea consoleArea = new TextArea();
    @FXML
    private  TextField fileAddress = new TextField();
    @FXML
    private Button matrixIn = new Button();
    @FXML
    private Button changeIn = new Button();
    @FXML
    private Pane workPlace = new Pane();
    @FXML
    private Button nextIter=new Button();

    @FXML
    private CheckBox iterMode=new CheckBox();

    @FXML
    void initialize() {
        createChooseInput();
        changeInput();
        TablesPane pane = new TablesPane(tablesPane);
        input(pane);
        receiver.setOnAction(event -> {
            matrix.set(TableOperator.readTable((GridPane) tablesPane.getChildren().get(0), size.get()));
            Requestable req = new Test();
            req.setSize(size.get());
            if(iterMode.isSelected()) {
                nextIter.setVisible(true);
                nextIter.setOnAction(event1 -> {
                   GridPane gridPane = pane.createNewTable(size.get());
                   req.setPreparedMatrix(matrix.get());
                   if(req.isEnd()){
                       writeToConsole(req);
                   }else {
                       TableOperator.printTable(req.getNextMatrix(), gridPane, size.get());
                   }
                });
            }
            else {
                req.setPreparedMatrix(matrix.get());
                GridPane gridPane = pane.createNewTable(size.get());
                TableOperator.printTable(req.getFinalMatrix(),gridPane,size.get());
                writeToConsole(req);
            }
        });
    }
    private void createChooseInput(){
        Button manually = new Button("вручную");
        Button fromFile = new Button("Из файла");
        manually.setMinSize(480, 550);
        fromFile.setMinSize(480, 550);
        fromFile.setLayoutX(480);
        workPlace.getChildren().addAll(manually, fromFile);
        manually.setOnAction(event -> {
            fileAddress.setVisible(false);
            workPlace.getChildren().remove(manually);
            workPlace.getChildren().remove(fromFile);
        });
        fromFile.setOnAction(event -> {
            nValue.setVisible(false);
            workPlace.getChildren().remove(manually);
            workPlace.getChildren().remove(fromFile);
        });
    }
    private void changeInput(){
        changeIn.setOnAction(event -> {
            nValue.setVisible(true);
            fileAddress.setVisible(true);
            createChooseInput();
        });
    }
    private void input(TablesPane pane){
        matrixIn.setOnAction(event -> {
            pane.deleteAll();
            if(fileAddress.isVisible()){
                readFromFile();
                pane.createNewTable(size.get());
                TableOperator.createTable((GridPane) tablesPane.getChildren().get(0), size.get());
            }
            else {
                readSize();
                pane.createNewTable(size.get());
            }
        });
    }
    private void readFromFile(){
        try {
            FileReader fw = new FileReader(InputTextFields.getStringFromTextField(fileAddress));
            Scanner scanner = new Scanner(fw);
            size.set(Integer.parseInt(scanner.nextLine()));
            matrix.set(FileData.readFile(scanner,size.get()));
            scanner.close();
            fw.close();

        } catch (Exception e) {
            Console.writeLine(consoleArea,e.getMessage());
        }
    }
    private void readSize(){
        try {
            int a=InputTextFields.getIntFromTextField(nValue);
            if(a>0&&a<21) {
                size.set(a);
            }
            else {
                throw new NumberFormatException();
            }
        }
        catch (NumberFormatException e){
            Console.writeLine(consoleArea,"Размер введен некорректно\n" +
                    "Введите число от 1 до 20 включительно \n");
        }

    }
    private void writeToConsole(Requestable req){
        Console.writeLine(consoleArea,req.getMessage());
        Console.writeXVector(consoleArea,req.getAnswers());
        Console.writeRVector(consoleArea,req.getDiscrepancy());

    }
}
