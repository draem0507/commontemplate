package org.commontemplate.util.swing;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ImageFactory {

	public static Image getImage(String url) {
		try {
			return ImageIO.read(ImageFactory.class.getClassLoader().getResourceAsStream(url));
		} catch (Exception e) {
			// return new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB);
		}
		return new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB);
	}

	public static Icon getIcon(String url) {
		return new ImageIcon(getImage(url), url);
	}

}
