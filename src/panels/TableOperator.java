package panels;


import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class TableOperator {
    public static void createTable(GridPane table, int size) {
        clearTable(table);
        for (int i = 0; i < size + 1; i++) {
            for (int j = 0; j < size; j++) {
                table.add(new TextField(), i, j);
            }
        }

    }

    public static void clearTable(GridPane table) {
        table.getColumnConstraints().clear();
        table.getRowConstraints().clear();
        table.getChildren().clear();
    }

    public static ArrayList<Double> readTable(GridPane table, int size) {
        ObservableList<Node> list = table.getChildren();
        ArrayList<Double> inputData = new ArrayList<>();
        ArrayList<Double> data = new ArrayList<>();
        for (Node b : list) {
            TextField textField = (TextField) b;
            inputData.add(Double.parseDouble(textField.getText()));
        }
        normalizeData(inputData, data, size);
        inputData.clear();
        return data;
    }

    public static void printTable(ArrayList<Double> matrix, GridPane table, int size) {
        ObservableList<Node> list = table.getChildren();
        ArrayList<Double> outputData = new ArrayList<>();
        preparationForPrintingData(matrix, outputData, size);
        for (int i = 0; i < list.size(); i++) {
            TextField textField = (TextField) list.get(i);
            textField.setText(outputData.get(i).toString());
        }
    }

    private static void normalizeData(ArrayList<Double> matrix, ArrayList<Double> outputData, int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size + 1; j++) {
                outputData.add(matrix.get(i + j * size));

            }
        }
    }

    private static void preparationForPrintingData(ArrayList<Double> matrix, ArrayList<Double> outputData, int size) {
        for (int i = 0; i < size + 1; i++) {
            for (int j = 0; j < size; j++) {
                outputData.add(matrix.get(i + j * (size + 1)));
            }
        }
    }

}
