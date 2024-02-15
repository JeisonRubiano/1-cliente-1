package model;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImageConverter {
	public static byte[] toBinary(String path) {
	    int len = path.split("\\.").length;
	    String ext = path.split("\\.")[len - 1];
	    try {
	        BufferedImage img = ImageIO.read(new File(path));
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        ImageIO.write(img, ext, baos);
	        return baos.toByteArray();
	    } catch(IOException e) {
			System.out.println(e.getMessage());
	        return new byte[0];
	    }
	}
	public static File toImage(byte[] imageBytes, String imageName ) {

		try (InputStream inputStream = new ByteArrayInputStream(imageBytes)) {
		    BufferedImage imagen = ImageIO.read(inputStream);
		    File archivoImagen = new File(imageName);
			System.out.println("error posble --"+imageName);
		    return archivoImagen;
		} catch (IOException e) {
		    return null;
		}
	}
}
