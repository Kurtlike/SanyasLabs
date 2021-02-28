import java.util.InputMismatchException;
import java.util.Scanner;

public class IO {

    public static int scanDim(Scanner sc) throws InputMismatchException{
            return sc.nextInt();
    }
    public static double[][] scanValues(Scanner sc, int dim) throws InputMismatchException {
        double[][] massivchik = new double[dim][dim+1];//массив с размерностью матрицы
        for(int i=0;i<dim;i++){
            for(int j=0;j<dim+1;j++){
                    massivchik[i][j] =sc.nextDouble();
            }
        }
        return massivchik;
    }
}