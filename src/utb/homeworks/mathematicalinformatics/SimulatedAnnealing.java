package utb.homeworks.mathematicalinformatics;

public class SimulatedAnnealing extends StochasticHillClimber {

	final double Tmax = 10000, coolingRate = 0.6;
	double newCF, oldCF, alpha;

	public SimulatedAnnealing() {
		double alpha;
		double T = Tmax;
		x1center = -500 + 1000 * Math.random();
		x2center = -500 + 1000 * Math.random();
		bestSolution = oldCF = costFunctionSchwefel(x1center, x2center);
		for (int i = 0; i < costValueEvolution.length; i++) {
			x1[i] = generateNeighboringPoint(x1center);
			x2[i] = generateNeighboringPoint(x2center);
			newCF = costFunctionSchwefel(x1[i], x2[i]);
			alpha = acceptanceProbability(oldCF, newCF, T);
			if (alpha > Math.random()) {
				x1center = x1[i];
				x2center = x2[i];
				costValueEvolution[i] = newCF;
			} else {
				costValueEvolution[i] = oldCF;
			}
			if (alpha == 1) {
				x1best = x1[i];
				x2best = x2[i];
				bestSolution = newCF;
				oldCF = newCF;
			}
			if (((i + 1) % 100) == 0) {
				T *= coolingRate;
			}
		}
	}

	private double acceptanceProbability(double oldCF, double newCF, double T) {
		if (newCF < oldCF)
			return 1;
		else
			return Math.exp((oldCF - newCF) / T);
	}

	private double generateNeighboringPoint(double xCenter) {
		double min = Math.max(-500, x1center - radius);
		double max = Math.min(500, x1center + radius);
		return min + (max - min) * Math.random();
	}
}
