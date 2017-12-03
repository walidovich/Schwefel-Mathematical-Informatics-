package utb.homeworks.mathematicalinformatics;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class Main_Schwefel {
	public static void main(String[] args) {

		new ExcelWriterRS();
		System.out.println("Schwefel Random Search is done.");
		new ExcelWriterSHC();
		System.out.println("Schwefel Stochastic Hill Climbing is done.");
		new ExcelWriterSA();
		System.out.println("Schwefel Simulated Annealing is done.");

		try {
			Thread.sleep(1000);
			Desktop.getDesktop().open(new File("Schwefel.xlsx"));
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
