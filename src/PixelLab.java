import processing.core.*;
//  PROJECT: PIXEL LAB
//  Created 2016 by TEALS APCS team at Bellevue International High School.
//  Do not modify this file, though you are encouraged to examine it to see how
//  it implements the functionality to load, save and display images, using the
//  underlying support in the Processing library.
//
// Class PixelLab provides methods to load, save and display images.
// Images are represented by 2D arrays of pixels.
// Grayscale pixels are integers between [0,255], with
// 0 representing black and 255 representing white.
// Each row in the 2D array represents a row in the image.

public class PixelLab {
	private static PApplet pa;

	// Set's up the static pa object.
	public static void setPApplet(PApplet pa) {
		PixelLab.pa = pa;
	}

	// Loads the specified image file and convert it into a grayscale image.
	// Each pixel ranges between 0 (black) and 255(white).
	public static int[][]loadGrayscaleImage(String fileName) {
		assert(pa!=null);
		PImage img = pa.loadImage(fileName);
		int[][] arr = new int[img.height][img.width];
		img.loadPixels(); // must be called before accessing img.pixels.
		for (int i=0; i<arr.length; i++) {
			int[] row = arr[i];
			for (int j=0; j<row.length; j++) {
				// Pixel at (i,j) is given by pixels[i*width + j].
				int c = img.pixels[i*img.width+j];
				row[j] = (int)pa.brightness(c); // Convert from (r,g,b) to grayscale.
			}
		}
		return arr;
	}

	// Saves the pixels in array as a grayscale image with the specified
	// filename. values <= 0 are interpreted as black. Values >= 255 are are
	// interpreted as as white. In-between values represent a range of grays.
	// The filename must be just a file name (no path) and
	// the file will be saved in the "images" subdirectory (which will be
	// created if needed).
	public static void saveGrayscaleImage(int[][] arr, String fileName) {
		assert(pa!=null);
		if (!validOutputFilename(fileName)) {
			System.err.println("Filename has invalid characters: " + fileName);
			return; // ****** early return *********
		}
		fileName = "images\\" + fileName;
		PImage img = convertToPImage(arr);
		img.save(fileName);
	}

	// Display a grayscale image with upper left corner at the
	// specified (x,y) location. 0 (or -ve) represents black. A value of
	// 255 of greater is interpreted as white. Values between
	// 1 and 254 represent a range of grays.
	public static  void displayGrayscaleImage(int[][] array, int x, int y) {
		assert(pa!=null);
		PImage img = convertToPImage(array);
		pa.image(img, x, y);
	}

	static PImage convertToPImage(int[][] arr) {
		int imgHeight = arr.length;
		int imgWidth = (arr.length==0) ? 0 : arr[0].length;
		PImage img = pa.createImage(imgWidth, imgHeight, PConstants.RGB);
		img.loadPixels(); // must be done first.
		for (int i=0; i<arr.length; i++) {
			int[] row = arr[i];
			for (int j=0; j<row.length; j++) {
				// Pixel at (i,j) is given by pixels[i*width + j].
				float b = Math.min(255, Math.max(row[j], 0));
				int c = pa.color(b);
				img.pixels[i*img.width+j] = c;
			}
		}
		img.updatePixels(); // must be done after all pixel updating.
		return img;
	}

	// Checks that the name has no path chars and has only a valid
	// set of extensions representing image files.
	static boolean validOutputFilename(String name) {
		final char[] invalidChars = {'\\', '/', ':', '$'};
		for (char c : invalidChars) {
			if (name.indexOf(c)>=0) return false;
		}    
		name = name.toLowerCase()+"$"; // $ is a trick to match the end.
		final String[] ext = {".jpg$", ".tif$", ".png$"};
		for (String s : ext) {
			if (name.indexOf(s)>0) return true;
		}
		return false;
	}
}