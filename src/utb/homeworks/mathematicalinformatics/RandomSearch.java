package utb.homeworks.mathematicalinformatics;
public class RandomSearch extends VariablesHolder {

	public RandomSearch() {
		for (int i = 0; i < 1000; i++) {
			x1[i] = -500 + 1000 * Math.random();
			x2[i] = -500 + 1000 * Math.random();
			if (costFunctionSchwefel(x1[i], x2[i]) < bestSolution) {
				x1best = x1[i];
				x2best = x2[i];
				bestSolution = costFunctionSchwefel(x1best, x2best);
			}
			costValueEvolution[i] = costFunctionSchwefel(x1best, x2best);
		}
	}

	double costFunctionSchwefel(double x1, double x2) {
		return -x1 * Math.sin(Math.sqrt(Math.abs(x1))) - x2 * Math.sin(Math.sqrt(Math.abs(x2)));
	}

}
