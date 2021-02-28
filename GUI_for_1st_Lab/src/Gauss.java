public class Gauss {
    public Gauss(MatrixContainer container){
        this.container=container;
    }
    MatrixContainer container;
    public void solve(){
        container.triangulare(container.a);
        container.countSolution(container.a);
        container.countDiscrepancy(container.a);
        if (container.getDet(container.a)!=0){
            //TODO Написать обработку множественного решения или его отсутствия
        }
        else{

        }
    }
}
