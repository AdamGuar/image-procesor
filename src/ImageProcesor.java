import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import calculation.FractionCalculator;
import manipulation.ImageManipulator;

public class ImageProcesor {

	static BufferedImage original;
	private static Scanner scanner; 
	
	public static void main(String[] args) throws IOException {
		
		System.out.println("Starging image manipulation program");
		scanner = new Scanner(System.in);
		System.out.print("Enter a file name: ");
		String filename = scanner.nextLine();
		File file = new File(filename);
		
		original = ImageIO.read(file);
		System.out.println("File readed");
		
		ImageManipulator im = new ImageManipulator();
		
		BufferedImage binarized = im.binarize(original);
		
		FractionCalculator fc = new FractionCalculator();
		
		fc.setBinaryImage(binarized);
		
		double martensiteBinary = fc.getPercentageOfMartenzite();
		
		System.out.println("Saving binary image as binarized.jpg");
		writeImage("binarized",binarized);   
		
		BufferedImage dilatated = im.dilatate(binarized, 1);
		
		System.out.println("Saving binary dilatated image as dilatated.jpg");
		writeImage("dilatated",dilatated);   
		

		fc.setBinaryImage(dilatated);
		
		double martensiteDilatate = fc.getPercentageOfMartenzite();
		
		System.out.println("Fraction of martenstie in binary: "+martensiteBinary);
		System.out.println("Fraction of martenstie in binary dilatated: "+martensiteDilatate);
		

	}
	
	
	
	 private static void writeImage(String output, BufferedImage imageToSave) throws IOException {
	        File file = new File(output+".jpg");
	        ImageIO.write(imageToSave, "jpg", file);
	    }

}
