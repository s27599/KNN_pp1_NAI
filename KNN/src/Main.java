import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);



        System.out.println("Podaj K");
        int k = scanner.nextInt();


        scanner.nextLine();
        String com;
        do {
            System.out.println("Menu:");
            System.out.println("Uruchomienie na przykładowych danych wpisz: 1 ");
            System.out.println("lub podaj swoje dane oddzielone przecinkiem ");
            System.out.println("aby wyjść wprowadź \"q\"");
            com = scanner.nextLine();

            if(com.equals("1")){
                Knn knn = new Knn("TrainSet.csv");
                knn.filetoClasify("TestSet.csv");
                knn.clasify(k);
            } else if (!com.equals("q")&& !com.isEmpty()) {
                if(com.matches("[0-9]*\\.[0-9]+,[0-9]*\\.[0-9]+,[0-9]*\\.[0-9]+,[0-9]*\\.[0-9]+")) {
                    Knn knn = new Knn("TrainSet.csv");
                    knn.arrayToClasify(com.split(","));
                    knn.clasify(k);
                }else{
                    System.out.println("Wprowadz liczby dziesiętne z kropkami, oddzielone przecinkiem lub opcję z menu");
                }

            }


        }while (!com.equals("q"));





    }
}