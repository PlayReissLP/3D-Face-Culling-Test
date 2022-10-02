package net.pixx.wolfenstein.level;

/* Imports */
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import net.pixx.wolfenstein.mesh.Mesh;
import net.pixx.wolfenstein.texture.Texture;

public class Level {
	/* Lists */
	private List<Mesh> levelWorld;
	
	/* Constructor */
	public Level() {
		// Initializing.
		this.levelWorld = new ArrayList<Mesh>();
	}
	
	public void loadLevel(int level) {
		BufferedImage levelMap = Texture.textures.get("level_0").getImage();
		
		for(int x = 0; x < levelMap.getWidth(); x++) {
			for(int y = 0; y < levelMap.getHeight(); y++) {
				Color color = new Color(levelMap.getRGB(x, y));
				
				if(color.getRed() == 0xff
					&& color.getGreen() == 0xff
					&& color.getBlue() == 0xff) {
					this.levelWorld.add(new Mesh(new float[] {
						x, 0, y,
						x + 1, 0, y,
						x + 1, 0, y + 1,
						x, 0, y + 1
					}, new float[] {
						Texture.getTextureWidthPos(4), Texture.getTextureHeightPos(3),
						Texture.getTextureWidthPos(5), Texture.getTextureHeightPos(3),
						Texture.getTextureWidthPos(5), Texture.getTextureHeightPos(4),
						Texture.getTextureWidthPos(4), Texture.getTextureHeightPos(4)
					}));
					this.levelWorld.add(new Mesh(new float[] {
						x, 1, y,
						x, 1, y + 1,
						x + 1, 1, y + 1,
						x + 1, 1, y
					}, new float[] {
						0f * Texture.TEXTURE_WIDTH, 0f * Texture.TEXTURE_HEIGHT,
						1f * Texture.TEXTURE_WIDTH, 0f * Texture.TEXTURE_HEIGHT,
						1f * Texture.TEXTURE_WIDTH, 1f * Texture.TEXTURE_HEIGHT,
						0f * Texture.TEXTURE_WIDTH, 1f * Texture.TEXTURE_HEIGHT
					}));
					
//					// Checking top of map, to put walls.					
//					Color color_top;
//					if(y + 1 >= levelMap.getHeight()) {
//						color_top = new Color(0, 0, 0);
//					} else {
//						color_top = new Color(levelMap.getRGB(x, y + 1));
//					}
//					
//					if(color_top.getRed() == 0
//						&& color_top.getGreen() == 0
//						&& color_top.getBlue() == 0) {
//						this.levelWorld.add(new Mesh(new float[] {
//							x, 1, y + 1,
//							x, 0, y + 1,
//							x + 1, 0, y + 1,
//							x + 1, 1, y + 1
//						}, (float)Math.random(), (float)Math.random(), (float)Math.random()));
//					}
//					
//					// Checking bottom of map, to put walls.
//					Color color_bottom;
//					if(y - 1 < 0) {
//						color_bottom = new Color(0, 0, 0);
//					} else {
//						color_bottom = new Color(levelMap.getRGB(x, y - 1));
//					}
//					
//					if(color_bottom.getRed() == 0
//						&& color_bottom.getGreen() == 0
//						&& color_bottom.getBlue() == 0) {
//						this.levelWorld.add(new Mesh(new float[] {
//							x + 1, 1, y,
//							x + 1, 0, y,
//							x, 0, y,
//							x, 1, y
//						}, (float)Math.random(), (float)Math.random(), (float)Math.random()));
//					}
//					
//					// Checking right of map, to put walls.
//					Color color_right;
//					if(x + 1 >= levelMap.getWidth()) {
//						color_right = new Color(0, 0, 0);
//					} else {
//						color_right = new Color(levelMap.getRGB(x + 1, y ));
//					}
//					
//					if(color_right.getRed() == 0
//						&& color_right.getGreen() == 0
//						&& color_right.getBlue() == 0) {
//						this.levelWorld.add(new Mesh(new float[] {
//							x + 1, 1, y + 1,
//							x + 1, 0, y + 1,
//							x + 1, 0, y,
//							x + 1, 1, y
//						}, (float)Math.random(), (float)Math.random(), (float)Math.random()));
//					}
//					
//					// Checking left of map, to put walls.
//					Color color_left;
//					if(x - 1 < 0) {
//						color_left = new Color(0, 0, 0);
//					} else {
//						color_left = new Color(levelMap.getRGB(x - 1, y ));
//					}
//					
//					if(color_left.getRed() == 0
//						&& color_left.getGreen() == 0
//						&& color_left.getBlue() == 0) {
//						this.levelWorld.add(new Mesh(new float[] {
//							x, 1, y,
//							x, 0, y,
//							x, 0, y + 1,
//							x, 1, y + 1
//						}, (float)Math.random(), (float)Math.random(), (float)Math.random()));
//					}
				}
			}
		}
	}
	
	public void render() {
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, Texture.textures.get("sheet").getId());
		
		for(int i = 0; i < this.levelWorld.size(); i++) {
			this.levelWorld.get(i).render();
		}
	}
}
