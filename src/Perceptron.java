public class Perceptron {
    double[] weights = new double[]{0,0,0};
    double[] xValues;
    double[][] data;
    double learningRate = 1;
    int nbErrors = 0;

    public Perceptron(double[][] data) {
        this.data = data;
        this.xValues = new double[data[0].length+1];
        this.xValues[0] = 1;
    }

    public void train(){
        do {
            nbErrors = 0;
            for (int i = 0; i < 4; i++) {
                double[] line = data[i];
                System.arraycopy(line, 0, xValues, 1, 3);
                double potential = (weights[0]*xValues[0])+(weights[1]*xValues[1])+(weights[2]*xValues[2]);
                double y = getY(potential);

                if (y != line[2]) {
                    nbErrors++;
                    recalculateWeights(line[2], y, line);
                }
                System.out.println(y);
            }

            System.out.println("In this iteration " + "" + " I made " + nbErrors + " mistakes");
        } while (nbErrors > 0);
    }

    private double getY(double potential) {
        return potential < 0 ? 0 : 1;
    }
    private void recalculateWeights(double expected, double y, double[] line) {
        for (int i = 0; i < weights.length; i++) {
            double weight = weights[i];
            weight = weight + learningRate *(expected - y)*xValues[i];
            weights[i] = weight;
        }
    }

}
