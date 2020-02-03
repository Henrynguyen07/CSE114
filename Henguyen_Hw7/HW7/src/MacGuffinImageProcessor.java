/**
 * YOU MUST DEFINE THE METHODS IN THIS CLASS
 * 
 * This class performs the act of putting secret
 * messages into pixel data. The putSecretMessageInImage
 * puts them in and the extractSecretMessageFromimage
 * takes them out.
 * 
 * @author Richard McKenna
 * @author _______________
 */
public class MacGuffinImageProcessor 
{
	/**
	 * This method should put the secret message
	 * into the pixels array. Note that the pixels
	 * array is an array of pixel values where the
	 * rgba values are all stored within singular
	 * integers. In other words, each int is really
	 * four bytes, one for each of the rgba components.
	 * 
	 * Note that the message data should be placed in
	 * the alpha channel along the left-edge of the image,
	 * top to bottom.
	 */
	public static void putSecretMessageInImage(int[] pixels, String message) 
	{
		// ADD YOUR CODE HERE
            int character;
            int pixel;
            int mess_len = Integer.parseInt(Integer.toBinaryString(message.length()),2);
            pixels[0] = (pixels[0] & 0xFFFFFF00) | mess_len;
            
            for (int i = 0; i < message.length(); i++) {
                character = Integer.parseUnsignedInt(Integer.toBinaryString(message.charAt(i)), 2);

                pixel = Integer.parseUnsignedInt(Integer.toBinaryString(pixels[i+1]), 2);
                pixels[i+1] = ((pixel & 0xFFFFFF00) | character);
            }
	}

	/**
	 * This method works in cooperation with the
	 * putSecretMessageInImage method. It must know
	 * how that one works in order to extract the data
	 * properly.
	 * 
	 * This one will look at the pixel data and extract
	 * the text hidden inside, returning this as a String.
	 */
	public static String extractSecretMessageFromImage(int[] pixels)
	{
		// ADD YOUR CODE HERE
            String result = "";
            int pixel;
            int alpha;
            char character;
            int length = (pixels[0] & 0x000000FF) + 1;
            for (int j = 1; j < length; j++) {
                pixel = pixels[j];
                alpha = (pixel & 0x000000FF);
                character = (char) alpha;
                result += character;
            }
            return result;
	}
}