import java.util.ArrayList;

public interface Requestable {
    public void setSize(int size);
    public void setPreparedMatrix(ArrayList<Double> matrix);
    public boolean isEnd();
    public ArrayList<Double> getFinalMatrix();
    public ArrayList<Double> getNextMatrix();
    public ArrayList<Double> getAnswers();
    public ArrayList<Double> getDiscrepancy();
    public String getMessage();
}
