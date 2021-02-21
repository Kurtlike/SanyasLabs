package File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class FileData {
    public static ArrayList<Double> readFile(Scanner scanner,int size) throws Exception {System.out.println();
        String str;
        ArrayList<Double> mass= new ArrayList<>();
        for(int i=0;i<size;i++ ){
            str=scanner.nextLine();
            String[] s=str.split(";");
            for(int j=0;j<size+1;j++){
                mass.add(Double.parseDouble(s[j]));
            }
        }
        return mass;

    }


}
