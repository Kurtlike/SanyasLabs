import java.util.ArrayList;

public interface Requestable {
    public int setSize(int size);
    public void setPreparedMatrix(ArrayList<Double> matrix);
    public ArrayList<Double> getFinalMatrix();
    public ArrayList<Double> getAnswers();
    public ArrayList<Double> getDiscrepancy();
    public String getMessage();
}
