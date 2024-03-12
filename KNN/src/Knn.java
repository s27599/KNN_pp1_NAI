import java.io.File;
import java.io.FileNotFoundException;
import java.text.CollationElementIterator;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.Math.pow;

public class Knn {


  private   String trainSetName;
    private List<String[]> testArray;


    public Knn(String trainSetName) {

        this.trainSetName = trainSetName;
    }

    public void filetoClasify(String testSetName) {
        try(Scanner testSetScanner = new Scanner(new File(testSetName))){
            testArray = ReadData(testSetScanner);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void arrayToClasify(String[] testData) {
        this.testArray = new ArrayList<>();
        this.testArray.add(testData);

    }

    public String clasify(int k) {

        try (Scanner trainSetScanner = new Scanner(new File(trainSetName));
             ) {
            int goodAns = 0;
            int allAns = 0;
            List<String[]> dataArray = ReadData(trainSetScanner);


            List<Results> distanceArray = new ArrayList<>();


            for (String[] testing : testArray) {
                for (String[] trained : dataArray) {
                    double distance = pow((Float.parseFloat(testing[0]) - Float.parseFloat(trained[0])), 2) +
                            pow((Float.parseFloat(testing[1]) - Float.parseFloat(trained[1])), 2) +
                            pow((Float.parseFloat(testing[2]) - Float.parseFloat(trained[2])), 2) +
                            pow((Float.parseFloat(testing[3]) - Float.parseFloat(trained[3])), 2);
                    distanceArray.add(new Results(distance, trained[4]));
                }
                Collections.sort(distanceArray);

                String answ = maxCount(k, distanceArray);


                System.out.println("Kwiat to: " + answ);
                if(testing.length>4) {
                    if (Objects.equals(testing[4], answ)) {
                        goodAns++;
                    }
                    allAns++;
                    double accuracy = ((double) goodAns / allAns) * 100;
                    System.out.println("accuracy "+accuracy+"%");
//                    System.out.println(testing[4]);

                }

                System.out.println();

                distanceArray.clear();
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    private static String maxCount(int k, List<Results> distanceArray) {
        Map<String, Integer> occurrences = new HashMap<>();


        for (int i = 0; i < k; i++) {
            Results curr = distanceArray.get(i);
            if (occurrences.containsKey(curr.getName())) {
                occurrences.put(curr.getName(), occurrences.get(curr.getName()) + 1);
            } else {
                occurrences.put(curr.getName(), 1);
            }
        }
        String answ = null;
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : occurrences.entrySet()) {
            if (entry.getValue() > maxCount) {
                answ = entry.getKey();
                maxCount = entry.getValue();
            }
        }
        return answ;
    }

    private ArrayList<String[]> ReadData(Scanner scanner) {
        ArrayList<String[]> tmp = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String[] data = scanner.nextLine().split(",");
            tmp.add(data);
        }
        return tmp;
    }

}
