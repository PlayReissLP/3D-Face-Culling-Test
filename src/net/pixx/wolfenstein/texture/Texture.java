package net.pixx.wolfenstein.texture;

/* Imports */
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.HashSet;

import javax.imageio.ImageIO;

import org.jcp.xml.dsig.internal.dom.Utils;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import com.sun.corba.se.impl.javax.rmi.CORBA.Util;

import net.pixx.wolfenstein.Wolfenstein;

public class Texture {
	/* Objects */
	private BufferedImage image;
	
	/* Variables */
	private int width;
	private int height;
	private int id;
	
	/* Arrays */
	private int[] atlas_pixels;
	
	/* Hashes */
	public static HashMap<String, Texture> textures = new HashMap<String, Texture>();
	
	/**
	 * Constructor
	 * 
	 * @param path The path to get the texture from.
	 */
	public Texture(String path) {
		try {
			int[] pixels_raw;
			ByteBuffer pixels;
			BufferedImage image;
			
			// Initializing.
			this.image = image = ImageIO.read(Wolfenstein.class.getResourceAsStream(path));
			this.width = image.getWidth();
			this.height = image.getHeight();
			
			this.atlas_pixels = new int[width * height];
			pixels_raw = image.getRGB(0, 0, this.width, this.height, atlas_pixels, 0, this.width);
			
			pixels = BufferUtils.createByteBuffer(this.width * this.height * 4);
			
			for(int i = 0; i < this.width; i++) {
				for(int j = 0; j < this.height; j++) {
					// Creating Variables
					int pixel = pixels_raw[i * this.width + j];
					
					pixels.put((byte)((pixel >> 16) & 0xFF)); 	// RED
					pixels.put((byte)((pixel >> 8) & 0xFF)); 	// GREEN
					pixels.put((byte)(pixel & 0xFF)); 			// BLUE
					pixels.put((byte)((pixel >> 24) & 0xFF)); 	// ALPHA
				}
			}
			
			pixels.flip();
			
			this.id = allocateTexture();
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);
			
			GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
			GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
			
			// Activates transparency of textures.
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			
			GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, this.width, this.height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, pixels);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Methods
	 */
	private int allocateTexture() {
		IntBuffer textureHandle = BufferUtils.createIntBuffer(1);
	    GL11.glGenTextures(textureHandle);
	    return textureHandle.get(0);
	}
	
	public BufferedImage getImage() {
		return this.image;
	}
}
