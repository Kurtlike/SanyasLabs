package panels;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class TablesPane {
    int shift=0;
    Pane tablesPane;
    public TablesPane(Pane tablesPane){
        this.tablesPane = tablesPane;
    }
    ArrayList<GridPane> tables = new ArrayList<>();
    public GridPane createNewTable(int size){
        GridPane table = new GridPane();
        TableOperator.createTable(table,size);
        table.setLayoutY(shift);
        table.setMaxWidth(535);
        table.setLayoutX(30);
        shift += size*30+10;
        tables.add(table);
        tablesPane.setPrefHeight(shift);
        tablesPane.getChildren().add(table);
        return table;
    }
    public void deleteAll(){
        tables.clear();
        tablesPane.getChildren().removeAll(tables);
    }
}
