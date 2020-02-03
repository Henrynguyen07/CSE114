import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.ImageObserver;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.html.HTMLDocument;
/**
 * DO NOT CHANGE THIS CLASS
 * 
 * This program allows one to load PNG images and
 * place secret messages inside them. This is done
 * by making use of the MacGuffinImageProcessor class,
 * which students must define.
 * 
 * @author McKilla Gorilla
 */
public class MacGuffinImageMaker extends JFrame
{
	// NORTH
	private JPanel northPanel;
	private JLabel fileNameLabel;
	private DefaultComboBoxModel dcbm;
	private JComboBox fileNameComboBox;
	
	// CENTER
	private ImagePanel imagePanel;
	private BufferedImage renderImage;
	
	// SOUTH
	private JPanel southPanel;
	private JLabel messageLabel;
	private JTextField messageTF;
	private JButton setMessageButton;
	
	// WE'LL USE THIS FOR ALL TEXT
	Font font = new Font("Serif", Font.PLAIN, 20);	

	/**
	 * Default and only constructor, this lays out
	 * all of our GUI components.
	 */
	public MacGuffinImageMaker()
	{
		super("MacGuffin Image Maker");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		layoutGUI();
	}

	/**
	 * This sets up the entire GUI.
	 */
	public void layoutGUI()
	{
		// NORTH
		northPanel = new JPanel();
		fileNameLabel = new JLabel("File Name: ");
		fileNameLabel.setFont(font);
		fileNameComboBox = new JComboBox();
		ImageItemListener iil = new ImageItemListener();
		fileNameComboBox.addItemListener(iil);
		northPanel.add(fileNameLabel);
		northPanel.add(fileNameComboBox);
		
		// CENTER
		imagePanel = new ImagePanel();
		renderImage = null;
		
		// SOUTH
		southPanel = new JPanel();
		messageLabel = new JLabel("Message: ");
		messageLabel.setFont(font);
		messageTF = new JTextField(50);
		messageTF.setFont(font);
		setMessageButton = new JButton("Set Message");
		southPanel.add(messageLabel);
		southPanel.add(messageTF);
		southPanel.add(setMessageButton);
		SetMessageListener sml = new SetMessageListener();
		setMessageButton.addActionListener(sml);

		// ARRANGE EVERYTHING INSIDE THIS FRAME
		add(northPanel, BorderLayout.NORTH);
		add(imagePanel, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);
		
		// NOW THAT EVERYTHING'S IN PLACE, LET'S
		// LOAD THE COMBO BOX WITH ALL THE FILE NAMES
		loadComboBox();
	}

	/**
	 * This method finds all the PNG images in the
	 * working directory and places their file names
	 * in the combo box.
	 */
	public void loadComboBox()
	{
		// THIS STORES THE DATA FOR THE COMBO BOX
		dcbm = new DefaultComboBoxModel();
		
		// GET ALL THE FILES IN THIS DIRECTORY
		File f = new File(".");
		File[] files = f.listFiles();
		Vector<String> v = new Vector<String>();
		for (int i = 0; i < files.length; i++)
		{
			String fileName = files[i].getName();
			if (fileName.endsWith(".png"))
			{
				dcbm.addElement(files[i].getName());
			}
		}
		fileNameComboBox.setModel(dcbm);
		
		// LOAD THE FIRST IMAGE
		if (fileNameComboBox.getSelectedItem() != null)
		{
			String fileName = (String)fileNameComboBox.getSelectedItem();
			loadImage(fileName);
		}
	}

	/**
	 * This method checks the fileName image file. If
	 * it is an ARGB-type PNG it will simply load it and
	 * load the secret message, if any. 
	 * 
	 * If it is an RGB-type, it will convert it to an ARGB
	 * type and specify no message.
	 */
	public void loadImage(String fileName)
	{
		try
		{
			// READ IN THE IMAGE
			File imageFile = new File(fileName);
			renderImage = ImageIO.read(imageFile);
			MediaTracker mt = new MediaTracker(this);
			mt.addImage(renderImage, 0);
			try { mt.waitForID(0); }
			catch(InterruptedException ioe) {}
			
			// IS IT AN RGB TYPE?
			if (!renderImage.getColorModel().hasAlpha())
			{
				// LET'S CONVERT THE FILE
				int w = renderImage.getWidth();
				int h = renderImage.getHeight();
				
				// MAKE A NEW BufferedImage
				BufferedImage newImage = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
				Raster oldRaster = renderImage.getRaster();
				WritableRaster newRaster = newImage.getRaster();
				
				// AND COPY OVER ALL TEH DATA TO THE NEW ONE
				for (int i = 0; i < w; i++)
				{
					for (int j = 0; j < h; j++)
					{
						int[] oldPixel = new int[3];
						oldRaster.getPixel(i, j, oldPixel);
						int[] newPixel = new int[4];
						for(int k = 0; k < 3; k++)
							newPixel[k] = oldPixel[k];
						if (j == 0)
							newPixel[3] = 0;
						else
							newPixel[3] = 255;
						newRaster.setPixel(i, j, newPixel);
					}
				}
				// NOW LET'S SAVE IT
				renderImage = newImage;
				saveImageToFile(fileName);
			}
			// IT'S AN ARGB TYPE
			else
			{
				// SO LET'S READ IT IN AND EXTRACT
				// AND THEN DISPLAY THE MESSAGE
				int[] pixels = buildPixelArray();
				String message = MacGuffinImageProcessor.extractSecretMessageFromImage(pixels);
				messageTF.setText(message);
				imagePanel.repaint();
			}
		}
		catch(IOException ioe)
		{
			JOptionPane.showMessageDialog(this, "Error Reading " + fileName);
		}
	}	

	/**
	 * This method builds and returns an array
	 * of integers representing all the pixels
	 * in the image. Note that each int stores
	 * the four bytes for rgba (in that order),
	 * high bits to low bits.
	 */
	public int[] buildPixelArray()
	{
		Raster raster = renderImage.getRaster();
		int w = raster.getWidth();
		int h = raster.getHeight();
		int[] pixels = new int[w * h];
		int[] pixel = new int[4];
		int counter = 0;
		for (int i = 0; i < w; i++)
		{
			for (int j = 0; j < h; j++)
			{
				raster.getPixel(i, j, pixel);
				pixels[counter] = packPixelIntoInt(pixel);
				counter++;
			}
		}
		return pixels;
	}

	/**
	 * This method packs the four integers (all 0 - 255)
	 * in the pixel array into a single int. This int
	 * is then returned.
	 */
	public int packPixelIntoInt(int[] pixel)
	{
		int pixelInt = 0;
		pixelInt = pixel[0] << 24;
		pixelInt = pixelInt | (pixel[1] << 16);
		pixelInt = pixelInt | (pixel[2] << 8);
		pixelInt = pixelInt | pixel[3];
		return pixelInt;
	}
	
	/**
	 * This method provides the architecture for adding
	 * the secret message. Note that the implementation
	 * is done inside the MacGuffinImageProcessor class.
	 */
	public void addSecretMessageToImage(String newFileName,
										String message)
	{
		// IF IT'S A NEW FILE NAME, WE NEED TO
		// ADD IT TO THE COMBO BOX
		if (dcbm.getIndexOf(newFileName) == -1)
		{
			// IS IT THE FIRST ELEMENT?
			if (dcbm.getSize() == 0)
				dcbm.addElement(newFileName);
			// DOES IT GO AT THE END?
			else
			{
				String lastFileName = (String)dcbm.getElementAt(dcbm.getSize()-1);
				if (newFileName.compareTo(lastFileName) > 0)
					dcbm.addElement(newFileName);
				// IT MUST GO SOMEWHERE ELSE
				else
				{
					boolean locationFound = false;
					for (int i = 0; (i < dcbm.getSize()) && !locationFound; i++)
					{
						String testFileName = (String)dcbm.getElementAt(i);
						if (newFileName.compareTo(testFileName) < 0)
						{
							dcbm.insertElementAt(newFileName, i);
							locationFound = true;
						}
					}
				}
			}
		}
		int[] pixels = buildPixelArray();
		MacGuffinImageProcessor.putSecretMessageInImage(pixels, message);
		updatePixels(pixels);
		saveImageToFile(newFileName);		
		fileNameComboBox.setSelectedItem(newFileName);
	}

	/**
	 * This method would be called after a secret
	 * message has been put into a pixels array, which
	 * is the method argument. This method will then
	 * load this data into the image.
	 */
	public void updatePixels(int[] pixels)
	{
		WritableRaster wr = renderImage.getRaster();
		int length = pixels[0] & 0x000000ff;
		for (int i = 0; i <= length; i++)
		{
			int[] pixel = new int[4];
			pixel[0] = (pixels[i] >> 25) & 0x000000ff;
			pixel[1] = (pixels[i] & 0x00ff0000)>>16;
			pixel[2] = (pixels[i] & 0x0000ff00)>>8;
			pixel[3] = (pixels[i] & 0x000000ff);
			wr.setPixel(0, i, pixel);
		}
	}

	/**
	 * This method saves the image, with the secret
	 * message, to its file.
	 */
	public void saveImageToFile(String fileName)
	{
		try
		{
			File f = new File(fileName);
			ImageIO.write(renderImage, "png", f);
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
	}	

	/**
	 * This helper class responds to the user selecting
	 * an image from the combo box. It does so by loading
	 * the selected image, rendering it, and loading and
	 * displaying any secret message found inside.
	 */
	private class ImageItemListener implements ItemListener
	{
		public void itemStateChanged(ItemEvent ie)
		{
			if (ie.getStateChange() == ItemEvent.SELECTED)
			{
				String fileName = (String)ie.getItem();
				loadImage(fileName);
			}
		}
	}

	/**
	 * This helper class provides the response for when
	 * the user clicks on the Set Message button. At that
	 * time, we want to take the text in the text field and
	 * embed it inside the image.
	 */
	private class SetMessageListener implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			// THIS IS THE IMAGE WE'RE USING
			BufferedImage img = MacGuffinImageMaker.this.renderImage;
			if (img != null)
			{
				// FIRST ASK THE USER WHAT TO NAME THE IMAGE
				String fileName = JOptionPane.showInputDialog(
									MacGuffinImageMaker.this,
									"What would you like to name your "
									+ " image with the secret message?",
									MacGuffinImageMaker.this.fileNameComboBox.getSelectedItem());
				if (fileName != null)
				{
					int w = img.getWidth(null);
					int h = img.getHeight(null);

					String secretMessage = MacGuffinImageMaker.this.messageTF.getText();
					if (secretMessage.length() > (h-1))
						JOptionPane.showMessageDialog(MacGuffinImageMaker.this, "For this image, your message is too long. It many not have more than " + w + " characters", "Message too long", JOptionPane.ERROR_MESSAGE);
					else
						MacGuffinImageMaker.this.addSecretMessageToImage(fileName, secretMessage);
				}
			}
		}
	}

	/**
	 * This helper class performs all the image 
	 * rendering inside our application.
	 */
	private class ImagePanel extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			Image img = MacGuffinImageMaker.this.renderImage;
			if (img != null)
			{
				int w = getWidth();
				int h = getHeight();
				if (w > 0)
				{
					int imgW = img.getWidth(null);
					int imgH = img.getHeight(null);
					int x = (w/2) - (imgW/2);
					int y = (h/2) - (imgH/2);
					g.drawImage(img, x, y, null);
				}	
			}
		}
	}

	/**
	 * This is where our program starts. We make a new
	 * window (our MacGuffinImageMaker object), and start
	 * it up. Once started, the program is in event-
	 * handling mode, waiting for the user to do something.
	 */
	public static void main(String[] args)
	{
		MacGuffinImageMaker frame = new MacGuffinImageMaker();
		frame.setVisible(true);
	}
}