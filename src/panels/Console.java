package panels;

import javafx.scene.control.TextArea;

import java.util.ArrayList;

public class Console {

    public static void writeLine(TextArea console,String str){
        console.setText(str);
    }
    public static void writeXVector(TextArea console, ArrayList<Double> answers){
        StringBuilder line = new StringBuilder();
        line.append("Вектор неизвестных \n");
        for(int i=0; i<answers.size(); i++){
            line.append("X").append(i).append(": ").append(answers.get(i)).append("\n");
        }
        line.append("\n");
        console.appendText(line.toString());
    }
    public static void writeRVector(TextArea console, ArrayList<Double> answers){
        StringBuilder line = new StringBuilder();
        line.append("Вектор невязок \n");
        for(int i=0; i<answers.size(); i++){
            line.append("R").append(i).append(": ").append(answers.get(i)).append("\n");
        }
        line.append("\n");
        console.appendText(line.toString());
    }
}
