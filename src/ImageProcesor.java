import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

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
		
		ImageManipulator im = new ImageManipulator(original);
		
		BufferedImage binarized = im.binarize();
		
		
		
		
		
		
		System.out.println("Saving image as output.jpg");
		writeImage("output",binarized);   
		

	}
	
	
	
	 private static void writeImage(String output, BufferedImage imageToSave) throws IOException {
	        File file = new File(output+".jpg");
	        ImageIO.write(imageToSave, "jpg", file);
	    }

}
