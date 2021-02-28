import java.util.ArrayList;

import static java.lang.Math.abs;

public class MatrixContainer {
    public MatrixContainer(Matrix a){
        this.a=a;
        int amount=a.getDimb();
        this.order = new int[amount];
        this.altAmount=0;
        for(int i=0;i<a.getDimb();i++){
            this.order[i]=i;
        }
    }
    private int findMax(int column, int start, Matrix m){
        double max=abs(m.getValue(order[start],column));
        int index=start;
        for(int i=start;i<m.getDima();i++){
            if(abs(m.getValue(order[i],column))>max){
                max=abs(m.getValue(order[i],column));
                index=order[i];
            }
        }
        return index;
    }
//    public int findMaxA(int column, int start){
//        return findMax(column,start,a);
//    }
    private void alterOrder(int first, int second){
        int buf=order[first];
        order[first]=order[second];
        order[second]=buf;
        altAmount++;
    }
    public double getDet(Matrix m){
        double det=1;
        for(int i=0;i<m.getDima();i++){
            det*=m.getValue(order[i],i);
        }
        return det;
    }
    public void triangulare(Matrix m){
        for(int i=0;i<m.getDima()-1;i++){//цикл для прохода по всем строкам кроме последней
            int maxEl = findMax(order[i],i,m);
            //смена порядка строк в случае необходимости
            if(maxEl!=order[i]){
                alterOrder(order[i],order[maxEl]);
            }

            double c;
            for(int j=i+1;j<m.getDima();j++){
                //считаем коэффициент для каждой новой строки
                c=m.getValue(order[i+1],i)/m.getValue(order[i],i);
                //цикл для выбора элементов матриц
                for(int n=i;n<m.getDimb();n++){
                    //Устанавливаем новое значение элемента матрицы
                    m.alterValue(order[j],n,m.getValue(order[j],n)-c*m.getValue(order[i],n));
                }
            }
        }
    }
    public void  countSolution(Matrix m){
        solution=new ArrayList<>();
        double sum=0;
        System.out.println(m.getDima()+" "+m.getDimb());
        solution.add(m.getValue(order[m.getDima()-1],m.getDimb()-1)/m.getValue(order[m.getDima()-1],m.getDimb()-2));
        for(int i=m.getDima()-1;i>=0;i--){
            for(int j=m.getDimb()-2, n=0;j>i;j--,n++){
                sum+=m.getValue(order[i],j)*solution.get(n);
            }
            solution.add((m.getValue(order[i],m.getDimb()-1)-sum)/m.getValue(order[i],i));
            sum=0;
        }
    }
    public void countDiscrepancy(Matrix m){
        discrepancy=new ArrayList<Double>();
        double sum=0;
        for(int i=0;i<m.getDima();i++){
            for(int j=m.getDimb()-2, n=0;n<m.getDimb()-1;n++, j--) {
                sum+=m.getValue(i,n)*solution.get(j);
            }
            discrepancy.add(sum-m.getValue(i,m.getDimb()-1));
            sum=0;
        }
    }
    ArrayList<Double> discrepancy;
    ArrayList<Double> solution;
    int altAmount;
    int[] order;
    Matrix a;
}
