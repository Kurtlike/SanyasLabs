import java.util.ArrayList;
import java.util.Arrays;

public class Test implements Requestable {
    ArrayList<Double> matrix;
    int size;
    int iter=0;
    @Override
    public void setSize(int size) {
        iter=3;
        this.size=size;
    }

    @Override
    public void setPreparedMatrix(ArrayList<Double> matrix) {

        this.matrix=matrix;
    }

    @Override
    public boolean isEnd() {
        iter--;
        return iter < 0;
    }

    @Override
    public ArrayList<Double> getFinalMatrix() {
        return matrix;
    }

    @Override
    public ArrayList<Double> getNextMatrix() {
        for(int i=0; i<matrix.size(); i++) matrix.set(i,matrix.get(i)+1);
        return matrix;
    }

    @Override
    public ArrayList<Double> getAnswers() {
        return matrix;
    }

    @Override
    public ArrayList<Double> getDiscrepancy() {
        return matrix;
    }

    @Override
    public String getMessage() {
        return "Ok";
    }
}
