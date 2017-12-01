import processing.core.*;
//
// PROJECT: PIXEL LAB
// Created in 2016 by the TEALS APCS team at Bellevue International
//
// (Main) Program class
// Add all your code to this class (leave the PixelLab class unmodified).
// Start with method introActivity(). For more details consult the
// Pixel Lab project document.
//
public class Program extends PApplet {
	private static final int HEIGHT = 720;
	private static final int WIDTH = 720;

	/**
	 * Launch the Processing Application.
	 *  DO NOT MODIFY
	 *  Calls settings() once, then setup() once, then draw() 30 times per second.
	 * @param args - Program arguments are ignored.
	 */
	public static void main(String args[]) {
		String packageFilename = "Program";
		PApplet.main(new String[] { packageFilename });
	}

	/**
	 * Sets the Application Properties.
	 * DO NOT MODIFY - Except to change the size of the display window.
	 */
	public void settings() {
		size(WIDTH,HEIGHT); // Set size of screen
	}

	/**
	 * Program initialiation.
	 * DO NOT MODIFY
	 */
	public void setup() {
		PixelLab.setPApplet(this);
		noLoop();
	}

	/**
	 * Main part of the program. 
	 * Calls the methods for the various Lab activityes.
	 * DO NOT MODIFY - unless you want to add additional activities.
	 */
	public void draw() {
		introActivity();
		stripeActivity();
		blendActivity();
		blurActivity();
	}

	/**
	 * START HERE - this is the introductory activity (Activity 1 in the project documentation).
	 */
	void introActivity() {

		final int imgWidth = 300;
		final int imgHeight = 400;

		// Create a complex checkerboard pattern represented as a 2D array of ints
		int[][] img = makeTestImage(imgWidth, imgHeight);

		// Display the image
		PixelLab.displayGrayscaleImage(img, 10, 10); // Top right of image is at location (10, 10).

		// Save the image
		PixelLab.saveGrayscaleImage(img, "foo.png");

		// Load the image that you just saved
		int[][] img2 = PixelLab.loadGrayscaleImage("images\\foo.png");

		// WHAT IS THIS CODE DOING?
		for (int i=0; i< img2.length; i++) {
			for (int j=0; j<img2[i].length; j++) {
				img2[i][j] /= 2;
			}
		}

		// Display the 2nd image next to the first.
		int xOffset = img[0].length + 20; // push 2nd image to the right of the first.
		PixelLab.displayGrayscaleImage(img2, xOffset, 10);
	}

	// Makes a complex "checkerboard-like" image
	// This method is called from introActivity()
	public int[][] makeTestImage(int w, int h) {
		int CHECKER = 10;
		int[][] arr = new int[h][];
		for (int i=0; i<h; i++) {
			int[] row = new int[w];
			arr[i]=row;
			for (int j=0; j<w; j++) {
				row[j] = (i+j)%256;
				if ((i/CHECKER + j/CHECKER) % 2 == 0) {
					row[j] = 255;
				}
			}
		}

		return arr;
	}

	/**
	 * Add code for the Stripe Activity (Activity 2 in the project documentation) here. 
	 * You care encouraged to write helper methods to structure your code. Call method
	 * stripeImage() from here.
	 */
	void stripeActivity() {
		// Your code goes here
	}

	/**
	 * Add code for the Blend Activity (Activity 3 in the project documentation) here. 
	 * You are encouraged to) write helper methods to structure your code. In particular, call
	 * methods uniformlyBlend and leftToRightBlend from here.
	 */
	void blendActivity() {
		// Your code goes here 
	}

	/**
	 * Add code for the Posterize Activity (Activity 4 in the project documentation) here. 
	 * You are encouraged to) write helper methods to structure your code.
	 */
	void posterizeActivity() {
		// Your code goes here
	}
	
	/**
	 * Add code for the Extra Credit Blur Activity here. 
	 * You are encouraged to) write helper methods to structure your code. In particular, call
	 * method blendImage from here.
	 */
	void blurActivity() {
		// Your code goes here
	}
}
