package utb.homeworks.mathematicalinformatics;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriterSA {

	final String excelFileName = "Schwefel.xlsx";
	final String SA_Sheet = "SW SA";

	double BestCostFunction = 1000000000, x1best, x2best;
	XSSFWorkbook wb;
	Sheet workSheet;

	public ExcelWriterSA() {
		FileInputStream is;
		try {
			// Check if the file "Schwefel.xlsx" already exists
			is = new FileInputStream(excelFileName);
			wb = new XSSFWorkbook(is);
		} catch (IOException e) {
		}
		// If Simulated Annealing sheet doesn't exist, we create it
		if (wb.getSheet(SA_Sheet) == null)
			workSheet = wb.createSheet(SA_Sheet);
		else
			workSheet = wb.getSheet(SA_Sheet);
		workSheet.createRow(0);
		workSheet.createRow(1);
		workSheet.createRow(2);
		workSheet.createRow(3).createCell(0).setCellValue("Iter. #");
		for (int j = 0; j < 30; j++) {
			for (int i = 0; i < 1000; i++) {
				workSheet.getRow(0).createCell(j + 1).setCellValue("BCF #" + (j + 1));
				workSheet.getRow(3).createCell(j + 1).setCellValue("CF #" + (j + 1));
			}
		}
		for (int i = 0; i < 1000; i++) {
			workSheet.createRow(i + 4).createCell(0).setCellValue(i + 1);
		}
		writeInCells();
		FileOutputStream out;
		try {
			out = new FileOutputStream(excelFileName);
			wb.write(out);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void writeInCells() {
		int optimal = 0;
		SimulatedAnnealing saOptimal = null;
		for (int j = 0; j < 30; j++) {
			SimulatedAnnealing sa = new SimulatedAnnealing();
			double data[] = sa.costValueEvolution;
			for (int i = 0; i < 1000; i++) {
				workSheet.getRow(i + 4).createCell(j + 1).setCellValue(data[i]);
			}
			workSheet.getRow(1).createCell(j + 1).setCellValue(sa.bestSolution);
			if (sa.bestSolution < BestCostFunction) {
				BestCostFunction = sa.bestSolution;
				x1best = sa.x1best;
				x2best = sa.x2best;
				optimal = j;
				saOptimal = sa;
			}
		}
		int i = 12;
		wb.getSheetAt(0).createRow(i++).createCell(0).setCellValue("Schwefel SA results:");
		i = 14;
		wb.getSheetAt(0).createRow(i++).createCell(0).setCellValue("X1 best");
		wb.getSheetAt(0).createRow(i++).createCell(0).setCellValue("X2 best");
		wb.getSheetAt(0).createRow(i++).createCell(0).setCellValue("B.C.F.");
		wb.getSheetAt(0).createRow(i++).createCell(0).setCellValue("optimal");
		i = 14;
		wb.getSheetAt(0).getRow(i++).createCell(1).setCellValue(x1best);
		wb.getSheetAt(0).getRow(i++).createCell(1).setCellValue(x2best);
		wb.getSheetAt(0).getRow(i++).createCell(1).setCellValue(BestCostFunction);
		wb.getSheetAt(0).getRow(i++).createCell(1).setCellValue("BCF #" + (optimal + 1));
		writeOptimalSA(saOptimal, optimal);
	}

	private void writeOptimalSA(SimulatedAnnealing saOptimal, int optimal) {
		wb.getSheetAt(3).getRow(0).createCell(32).setCellValue("Optimal BCF #" + (optimal + 1));
		for (int i = 0; i < saOptimal.costValueEvolution.length; i++) {
			wb.getSheetAt(3).getRow(i + 4).createCell(32).setCellValue(saOptimal.costValueEvolution[i]);
		}
	}
}
