/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * Author Henry Nguyen
 * SBU ID: 111484010
 * Net ID: Henguyen
 * CSE 114
 */
public class test {
    public static void main (String[] args) {
    // HERE ARE OUR RGBA VALUES
        int red = 255;
        int green = 255;
        int blue = 0;
        int alpha = 255;

// LET'S BUILD THE PIXEL
// FIRST PUT RED IN THE LEFT MOST BYTE
// BY SHIFTING IT DOWN 24 BITS (3 BYTES)
        int pixel = red << 24;

// NOW PUT GREEN INTO THE SAME INT
        pixel = pixel | (green << 16);

// NOW PUT BLUE INTO THE SAME INT
        pixel = pixel | (blue << 8);

// AND FINALLY ALPHA
        pixel = pixel | 0x0000000F;

// WE NOW HAVE ALL 4 COMPONENTS IN THE pixel INT
// LET'S SEE HOW IT LOOKS IN BINARY
        String text = Integer.toBinaryString(pixel);
        System.out.println("pixel in binary: " + text);

// AND IN HEX
        text = Integer.toHexString(pixel);
        System.out.println("pixel in hex: " + text);

// AND IN DECIMAL
        System.out.println("pixel in decimal: " + pixel);

// NOW LET'S EXTRACT THE RGBA COMPONENTS
        red = (pixel >> 24) & 0x000000FF;
        green = (pixel & 0x00FF0000) >> 16;
        blue = (pixel & 0x0000FF00) >> 8;
        alpha = (pixel & 0x000000FF);

// AND LET'S DISPLAY THE RESULTS
        System.out.println("Red: " + red);
        System.out.println("Green: " + green);
        System.out.println("Blue: " + blue);
        System.out.println("Alpha: " + alpha);
        
    }
}
