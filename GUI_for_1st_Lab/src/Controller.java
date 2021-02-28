import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import File.FileData;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import panels.Choice;
import panels.Console;
import panels.InputTextFields;
import panels.TableOperator;

public class Controller {
    private Pane workPlace=new Pane();
    @FXML
    private  GridPane table=new GridPane();
    @FXML
    private  TextField nValue=new TextField();
    @FXML
    private  Button receiver=new Button();
    @FXML
    private  TextArea consoleArea=new TextArea();
    @FXML
    private  TextField fileAddress=new TextField();
    @FXML
    private Button matrixIn=new Button();
    @FXML
    private Button changeIn=new Button();
    AtomicInteger size = new AtomicInteger();
    AtomicReference<ArrayList<Double>> matrix= new AtomicReference<>(new ArrayList<>());
    String address;

    @FXML
    void initialize() {
        selectInput();
        changeInput();
        setData();

        receiver.setOnAction(event -> {
            readMatrix();
            //суй свой класс
            Requestable req=new Request();
            req.setSize(size.get());
            req.setPreparedMatrix(matrix.get());
            req.ebash();
            writeMatrix(req.getFinalMatrix());
            // Думаю, можно тут добавить один метод для всего этого счастья
            Console.writeLine(consoleArea,req.getMessage());
            Console.writeXVector(consoleArea,req.getAnswers());
            Console.writeRVector(consoleArea,req.getDiscrepancy());
            //
        });
    }
    private ArrayList<Node> createHideArr(){
        ArrayList<Node> arr=new ArrayList<>();
        arr.add(fileAddress);
        arr.add(consoleArea);
        arr.add(receiver);
        arr.add(nValue);
        arr.add(matrixIn);
        arr.add(changeIn);
        return arr;
    }
    private void changeInput(){
        changeIn.setOnAction(event -> {
            selectInput();
        });
    }
    private void setData(){
        matrixIn.setOnAction(event -> {
            if(fileAddress.isVisible()) readFromFile();
            if(nValue.isVisible()) readSize();
        });
    }
    private void readFromFile(){
        readAddress();
        try {
            FileReader fw=new FileReader(InputTextFields.getStringFromTextField(fileAddress));
            Scanner scanner=new Scanner(fw);
            size.set(Integer.parseInt(scanner.nextLine()));
            matrix.set(FileData.readFile(scanner,size.get()));
            scanner.close();
            fw.close();
            TableOperator.createTable(table,size.get());
            writeMatrix(matrix.get());
        } catch (Exception e) {
            Console.writeLine(consoleArea,e.getMessage());
        }
    }
    private void selectInput(){
        ArrayList<Node> needToHide=createHideArr();
        Choice.hideInterfaces(needToHide);
        Choice.select(table);
        selectInputMethod((Button) table.getChildren().get(0),(Button) table.getChildren().get(1));
    }
    private void selectInputMethod(Button setManual,Button setFromFile){
        setManual.setOnAction(event -> {
            TableOperator.clearTable(table);
            nValue.setVisible(true);
            matrixIn.setVisible(true);
            consoleArea.setVisible(true);
            receiver.setVisible(true);
            changeIn.setVisible(true);
        });
        setFromFile.setOnAction(event -> {
            TableOperator.clearTable(table);
            fileAddress.setVisible(true);
            consoleArea.setVisible(true);
            receiver.setVisible(true);
            changeIn.setVisible(true);
            matrixIn.setVisible(true);
        });
    }
    private void readSize(){
        try {
            int a=InputTextFields.getIntFromTextField(nValue);
            if(a>0&&a<21) {
                size.set(a);
                TableOperator.createTable(table,size.get());
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
    private void readAddress(){
        address=InputTextFields.getStringFromTextField(fileAddress);
    }
    private void readMatrix(){
        try {
            matrix.set(TableOperator.readTable(table,size.get()));
        }
        catch (Exception e){
            Console.writeLine(consoleArea,e.getMessage()+"\n");
        }
    }

    private void writeMatrix(ArrayList<Double> matrix) {
        try {
            TableOperator.printTable(matrix,table,size.get());
        }
        catch (Exception e){
            Console.writeLine(consoleArea,e.getMessage()+"\n");
        }
    }
}
