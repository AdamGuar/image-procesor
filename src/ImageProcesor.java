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
		
		System.out.println("Saving binary image as binarized+file-name");
		writeImage("binarized-"+filename,binarized);   
		
		BufferedImage output = binarized;
		
		//Otwarcie
		output = im.erose(output, 1);
		output = im.dilatate(output, 1);
		/*
		//Zamkniêcie
		output = im.dilatate(output, 1);
		output = im.erose(output, 1);
		
		//tylko erose
		output = im.erose(output, 1);*/
		

		
		
		
		System.out.println("Saving binary processed image as output.jpg");
		writeImage("output-"+filename,output);   
		

		fc.setBinaryImage(output);
		
		double martensiteDilatate = fc.getPercentageOfMartenzite();
		
		System.out.println("Fraction of martenstie in binary: "+martensiteBinary);
		System.out.println("Fraction of martenstie in binary processed with opening: "+martensiteDilatate);
		

	}
	
	
	
	 private static void writeImage(String output, BufferedImage imageToSave) throws IOException {
	        File file = new File(output);
	        ImageIO.write(imageToSave, "jpg", file);
	    }

}
