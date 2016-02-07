package imageTest;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Draw {
	
	static int sum = 0;
	
	public static final String IMG = "C:\\asample.png";

	public static void main(String[] args) {

		BufferedImage img;

		try {
			img = ImageIO.read(new File(IMG));

			int[][] pixelData = new int[img.getHeight() * img.getWidth()][3];
			int[] rgb;

			int counter = 0;
			for (int i = 0; i < 120; i++) {
				for (int j = 0; j < 30; j++) {
					rgb = getPixelData(img, i, j);

					for (int k = 0; k < rgb.length; k++) {
						pixelData[counter][k] = rgb[k];
					}

					counter++;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static int[] getPixelData(BufferedImage img, int x, int y) {
		int argb = img.getRGB(x, y);

		int rgb[] = new int[] { (argb >> 16) & 0xff, // red
				(argb >> 8) & 0xff, // green
				(argb) & 0xff // blue
		};
		
		if(rgb[0] == 255 && rgb[1] == 255 && rgb[2] == 255){
			System.out.print("»Ú\t");
		} else if(rgb[0] == 0 && rgb[1] == 0 && rgb[2] == 0){
			System.out.print("∞À\t");
		} else if(rgb[0] == 0 && rgb[1] == 0 && rgb[2] == 255){
			System.out.print("∫Œ\t");
		}
		sum+=1;
		if(sum%30==0){
			System.out.println();
		}
		
		return rgb;
	}

}