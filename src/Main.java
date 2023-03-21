import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        int numRows = 4;
        int numCols = 3;
        double[][] data = new double[numRows][numCols];

        extractDataset(data);

        Perceptron myPerceptron = new Perceptron(data);
        myPerceptron.train();

    }

    private static void extractDataset(double[][] data) {
        try (BufferedReader br = new BufferedReader(new FileReader("datasetPorteET.csv"))) {
            String line;
            int row = 0;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                for (int col = 0; col < 3; col++) {
                    data[row][col] = Integer.parseInt(values[col]);
                }
                row++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}