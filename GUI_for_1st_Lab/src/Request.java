import java.util.ArrayList;

public class Request implements Requestable {
    MatrixContainer container;
    Matrix m;



    @Override
    public void ebash() {
        container = new MatrixContainer(m);
        Gauss g= new Gauss(container);
        g.solve();

    }
    @Override
    public void setSize(int size) {
        m=new Matrix(size,size+1);
    }

    @Override
    public void setPreparedMatrix(ArrayList<Double> matrix) {
        m.setValues(matrix);
    }

    @Override
    public ArrayList<Double> getFinalMatrix() {
        ArrayList<Double> finalMatrix=new ArrayList<>();
        for(int i=0;i<container.a.getDima();i++){
            for(int j=0;j<container.a.getDima()+1;j++){
                finalMatrix.add(container.a.getValue(container.order[i],j));
            }

        }

        return finalMatrix;
    }

    @Override
    public ArrayList<Double> getAnswers() {
        return container.solution;
    }

    @Override
    public ArrayList<Double> getDiscrepancy() {
        return container.discrepancy;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
