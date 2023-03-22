public class Perceptron {
    double[] weights = new double[]{0,0,0};
    double[] xValues;
    double[][] data;
    double learningRate = 1;
    int nbErrors = 0;

    public Perceptron(double[][] data) {
        if (data == null || data.length == 0 || data[0].length == 0) {
            throw new IllegalArgumentException("Invalid data");
        }

        this.data = data;
        this.xValues = new double[data[0].length+1];
        this.xValues[0] = 1;
    }

    public void train(){
        int it = 1;
        GraphDrawer perceptronSimple = new GraphDrawer("Perceptron simple");
        perceptronSimple.addPoint("My point 1", 0, 0);
        perceptronSimple.addPoint("My point 2", 0, 1);
        perceptronSimple.addPoint("My point 3", 1, 0);
        perceptronSimple.addPoint("My point 4", 1, 1);
        
        do {
            nbErrors = 0;

            for (int i = 0; i < data.length; i++) {
                double[] line = data[i];
                System.arraycopy(line, 0, xValues, 1, 3);
                double potential = (weights[0]*xValues[0])+(weights[1]*xValues[1])+(weights[2]*xValues[2]);
                double y = getY(potential);

                if (i == data.length-1) {
                    perceptronSimple.addLine(weights[0], weights[1], weights[2], "Line " + it);
                }

                if (y != line[2]) {
                    nbErrors++;
                    updateWeights(line[2], y);
                }
            }
            it++;
            System.out.println("In this iteration " + "" + " I made " + nbErrors + " mistakes");
        } while (nbErrors > 0);

        perceptronSimple.draw();
    }

    private double getY(double potential) {
        return potential < 0 ? 0 : 1;
    }
    private void updateWeights(double expected, double y) {
        for (int i = 0; i < weights.length; i++) {
            double weight = weights[i];
            weight = weight + learningRate *(expected - y)*xValues[i];
            weights[i] = weight;
        }
    }

}
