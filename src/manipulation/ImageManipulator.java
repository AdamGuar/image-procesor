package manipulation;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;

public class ImageManipulator {
	
	BufferedImage imageToManipulate;
	
	public ImageManipulator(BufferedImage imageToManipulate){
		this.imageToManipulate = imageToManipulate;
	}
	
	
	
	public BufferedImage binarize() {
		System.out.println("Binaryzing image");
		BufferedImage original = toGray(this.imageToManipulate);
		
	    int red;
	    int newPixel;
	 
	    int threshold = otsuTreshold(original);
	 
	    BufferedImage binarized = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());
	 
	    for(int i=0; i<original.getWidth(); i++) {
	        for(int j=0; j<original.getHeight(); j++) {
	 
	            // Get pixels
	            red = new Color(original.getRGB(i, j)).getRed();
	            int alpha = new Color(original.getRGB(i, j)).getAlpha();
	            if(red > threshold) {
	                newPixel = 255;
	            }
	            else {
	                newPixel = 0;
	            }
	            newPixel = colorToRGB(alpha, newPixel, newPixel, newPixel);
	            binarized.setRGB(i, j, newPixel); 
	 
	        }
	    }
	    System.out.println("Image binarized");
	    byte[] imageBytes = extractBytes(binarized);
	    sumBytes(imageBytes);
	    dilate(binarized);
	    return binarized;
	 
	}
	
	private static int colorToRGB(int alpha, int red, int green, int blue) {
		 
        int newPixel = 0;
        newPixel += alpha;
        newPixel = newPixel << 8;
        newPixel += red; newPixel = newPixel << 8;
        newPixel += green; newPixel = newPixel << 8;
        newPixel += blue;
 
        return newPixel;
 
    }
	
	private static BufferedImage toGray(BufferedImage original) {
		System.out.println("Converting image to gray scale"); 
	    int alpha, red, green, blue;
	    int newPixel;
	 
	    BufferedImage lum = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());
	 
	    for(int i=0; i<original.getWidth(); i++) {
	        for(int j=0; j<original.getHeight(); j++) {
	 
	            // Get pixels by R, G, B
	            alpha = new Color(original.getRGB(i, j)).getAlpha();
	            red = new Color(original.getRGB(i, j)).getRed();
	            green = new Color(original.getRGB(i, j)).getGreen();
	            blue = new Color(original.getRGB(i, j)).getBlue();
	          
	            
	            
	            red = (int) (0.21 * red + 0.71 * green + 0.07 * blue);
	            // Return back to original format
	            newPixel = colorToRGB(alpha, red, red, red);
	 
	            // Write pixels into image
	            lum.setRGB(i, j, newPixel);
	 
	        }
	    }
	    System.out.println("Image converted to gray scale");
	    return lum;
	 
	}
	
	private static int otsuTreshold(BufferedImage original) {
		System.out.println("Calculating thershold using otsu alorythm"); 
	    int[] histogram = imageHistogram(original);
	    int total = original.getHeight() * original.getWidth();
	 
	    float sum = 0;
	    for(int i=0; i<256; i++) sum += i * histogram[i];
	 
	    float sumB = 0;
	    int wB = 0;
	    int wF = 0;
	 
	    float varMax = 0;
	    int threshold = 0;
	 
	    for(int i=0 ; i<256 ; i++) {
	        wB += histogram[i];
	        if(wB == 0) continue;
	        wF = total - wB;
	 
	        if(wF == 0) break;
	 
	        sumB += (float) (i * histogram[i]);
	        float mB = sumB / wB;
	        float mF = (sum - sumB) / wF;
	 
	        float varBetween = (float) wB * (float) wF * (mB - mF) * (mB - mF);
	 
	        if(varBetween > varMax) {
	            varMax = varBetween;
	            threshold = i;
	        }
	    }
	    System.out.println("Threshold calculated"); 
	    return threshold;
	 
	}
	
	 public static int[] imageHistogram(BufferedImage input) {
		 
	        int[] histogram = new int[256];
	 
	        for(int i=0; i<histogram.length; i++) histogram[i] = 0;
	 
	        for(int i=0; i<input.getWidth(); i++) {
	            for(int j=0; j<input.getHeight(); j++) {
	                int red = new Color(input.getRGB (i, j)).getRed();
	                histogram[red]++;
	            }
	        }
	 
	        return histogram;
	 
	    }
	 
	 
	 private static byte[] extractBytes(BufferedImage input){
		 
		 WritableRaster raster = input.getRaster();
		 DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();

		 return ( data.getData() );
		 
	 }
	 
	 
	 private static void sumBytes(byte[] imageBytes){
		 int whiteCounter=0;
	
		 
		 for(byte i : imageBytes){
			 if(i==-1)
				 whiteCounter++;
		 }
		 
		 double percentage = whiteCounter/(float)imageBytes.length;
		 
		 System.out.println("Percentage of white pixels: "+percentage);
		
	 }
	 
	 
	 private static BufferedImage dilate(BufferedImage input){
		 BufferedImage result = input;
		 
		 for(int i=0;i<input.getWidth();i++){
			 for(int j=0;j<input.getHeight();j++){
				
			 }
		 }
		 

		 
		 return result;
	 }

	 
	 private static boolean isWhite(int rgb){
		 
		 Color color = new Color(rgb);
		 
		 if(color.getRed()==255&&color.getGreen()==255&&color.getBlue()==255)
			 return true;
		 else
			 return false; 
	 }

}
