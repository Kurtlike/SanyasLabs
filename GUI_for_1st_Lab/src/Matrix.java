import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Matrix {


    public Matrix(Scanner sc){
        try {
            this.dimb = sc.nextInt();
            this.dima = this.dimb+1;
            this.values = new double[dima][dimb];
        }
        catch(Exception e) {
            System.out.println("Ошибка ввода размерности") ;
        }
    }
    public Matrix(int dima, int dimb){
        this.dima = dima;
        this.dimb = dimb;
        this.values = new double[dima][dimb];
    }

    public double getValue(int a, int b){
        return values[a][b];
    }
    public double[][] getValues() {
        return values;
    }

    public void setValues(ArrayList<Double> list){
        for(int i=0, n=0;i<dima;i++){
            for(int j=0;j<dimb;j++,n++){
                try {
                    values[i][j] =list.get(n);
                }
                catch (InputMismatchException e){
                    System.out.println("Ошибка считывания данных");
                }
            }
        }
    }
    public void alterValue(int a, int b, double value){
        values[a][b]=value;
    }
    public int getDimb() {
        return dimb;
    }

    public int getDima() {
        return dima;
    }
    private int dima;
    private int dimb;
    private double[][] values;
}
