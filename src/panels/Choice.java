package panels;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class Choice {
    public static void select(GridPane table) {
        table.getColumnConstraints().clear();
        table.getRowConstraints().clear();
        table.getChildren().clear();
        Button manual = createButton("Ввести вручную");
        Button file = createButton("Ввести из файла");
        table.add(manual, 0, 0);
        table.add(file, 1, 0);
    }

    private static Button createButton(String text) {
        Button button = new Button(text);
        button.setMinSize(445, 500);
        return button;
    }

    public static void hideInterfaces(ArrayList<Node> nodes) {
        for (Node n : nodes) n.setVisible(false);
    }
}
