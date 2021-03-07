package panels;

import javafx.scene.control.TextField;

public class InputTextFields {
    public static int getIntFromTextField(TextField srt) throws NumberFormatException {
        return Integer.parseInt(srt.getText());
    }

    public static String getStringFromTextField(TextField srt) {
        return srt.getText();
    }
}
