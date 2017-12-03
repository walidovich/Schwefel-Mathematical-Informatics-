package utb.homeworks.mathematicalinformatics;

public class StochasticHillClimber extends VariablesHolder {

	double radius = 50;
	double x1center, x2center;
	private double x1local[] = new double[100], x2local[] = new double[100];

	public StochasticHillClimber() {
		x1center = -500 + 1000 * Math.random();
		x2center = -500 + 1000 * Math.random();
		for (int i = 0; i < 10; i++) {
			x1local = generateNeighboringPoints(x1center);
			x2local = generateNeighboringPoints(x2center);
			for (int j = 0; j < 100; j++) {
				if (costFunctionSchwefel(x1local[j], x2local[j]) < bestSolution) {
					costValueEvolution[100 * i + j] = bestSolution = costFunctionSchwefel(x1local[j], x2local[j]);
					x1center = x1best = x1local[j];
					x2center = x2best = x1local[j];
				}
				costValueEvolution[100 * i + j] = bestSolution;
			}
		}
	}

	double[] generateNeighboringPoints(double xCenter) {
		double min = Math.max(-500, x1center - radius);
		double max = Math.min(500, x1center + radius);
		double xLocal[] = new double[100];
		for (int i = 0; i < 100; i++) {
			xLocal[i] = min + (max - min) * Math.random();
		}
		return xLocal;
	}

	protected double costFunctionSchwefel(double x1, double x2) {
		return -x1 * Math.sin(Math.sqrt(Math.abs(x1))) - x2 * Math.sin(Math.sqrt(Math.abs(x2)));
	}
}
