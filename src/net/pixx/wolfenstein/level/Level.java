package net.pixx.wolfenstein.level;

/* Imports */
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import net.pixx.wolfenstein.collision.Collision;
import net.pixx.wolfenstein.entities.Entity;
import net.pixx.wolfenstein.mesh.Mesh;
import net.pixx.wolfenstein.texture.Texture;

public class Level {
	/* Objects */
	BufferedImage levelMap;
	
	/* Variables */
	private int r_levelList;
	
	/* Lists */
	private List<Mesh> levelWorld;
	
	/* Constructor */
	public Level() {
		// Initializing.
		this.levelWorld = new ArrayList<Mesh>();
		this.levelMap = null;
	}
	
	public void loadLevel(int level) {
		this.levelMap = Texture.textures.get("level_0").getImage();
		
		for(int x = 0; x < levelMap.getWidth(); x++) {
			for(int y = 0; y < levelMap.getHeight(); y++) {
				Color color = new Color(levelMap.getRGB(x, y));
				
				if(color.getRed() == 0xff
					&& color.getGreen() == 0xff
					&& color.getBlue() == 0xff) {
					this.levelWorld.add(new Mesh(new float[] {
						x - 0.1f, 0, y - 0.1f,
						x + 0.9f, 0, y - 0.1f,
						x + 0.9f, 0, y + 0.9f,
						x - 0.1f, 0, y + 0.9f
					}, new float[] {
						14f / 16f, 6f / 16f,
						15f / 16f, 6f / 16f,
						15f / 16f, 7f / 16f,
						14f / 16f, 7f / 16f
					}));
					this.levelWorld.add(new Mesh(new float[] {
						x - 0.1f, 1, y - 0.1f,
						x - 0.1f, 1, y + 0.9f,
						x + 0.9f, 1, y + 0.9f,
						x + 0.9f, 1, y - 0.1f
					}, new float[] {
						12f / 16f, 6f / 16f,
						13f / 16f, 6f / 16f,
						13f / 16f, 7f / 16f,
						12f / 16f, 7f / 16f
					}));
					
					// Checking top of map, to put walls.					
					Color color_top;
					if(y + 1 >= levelMap.getHeight()) {
						color_top = new Color(0, 0, 0);
					} else {
						color_top = new Color(levelMap.getRGB(x, y + 1));
					}
					
					if(color_top.getRed() == 0
						&& color_top.getGreen() == 0
						&& color_top.getBlue() == 0) {
						this.levelWorld.add(new Mesh(new float[] {
							x - 0.1f, 1, y + 0.9f,
							x - 0.1f, 0, y + 0.9f,
							x + 0.9f, 0, y + 0.9f,
							x + 0.9f, 1, y + 0.9f
						}, new float[] {
							1f / 16f, 0f,
							1f / 16f, 1f / 16f,
							0f, 1f / 16f,
							0f, 0f
						}));
					}
					
					// Checking bottom of map, to put walls.
					Color color_bottom;
					if(y - 1 < 0) {
						color_bottom = new Color(0, 0, 0);
					} else {
						color_bottom = new Color(levelMap.getRGB(x, y - 1));
					}
					
					if(color_bottom.getRed() == 0
						&& color_bottom.getGreen() == 0
						&& color_bottom.getBlue() == 0) {
						this.levelWorld.add(new Mesh(new float[] {
							x + 0.9f, 1, y - 0.1f,
							x + 0.9f, 0, y - 0.1f,
							x - 0.1f, 0, y - 0.1f,
							x - 0.1f, 1, y - 0.1f
						}, new float[] {
							1f / 16f, 0f,
							1f / 16f, 1f / 16f,
							0f, 1f / 16f,
							0f, 0f
						}));
					}
					
					// Checking right of map, to put walls.
					Color color_right;
					if(x + 1 >= levelMap.getWidth()) {
						color_right = new Color(0, 0, 0);
					} else {
						color_right = new Color(levelMap.getRGB(x + 1, y ));
					}
					
					if(color_right.getRed() == 0
						&& color_right.getGreen() == 0
						&& color_right.getBlue() == 0) {
						this.levelWorld.add(new Mesh(new float[] {
							x + 0.9f, 1, y + 0.9f,
							x + 0.9f, 0, y + 0.9f,
							x + 0.9f, 0, y - 0.1f,
							x + 0.9f, 1, y - 0.1f
						}, new float[] {
							1f / 16f, 0f,
							1f / 16f, 1f / 16f,
							0f, 1f / 16f,
							0f, 0f
						}));
					}
					
					// Checking left of map, to put walls.
					Color color_left;
					if(x - 1 < 0) {
						color_left = new Color(0, 0, 0);
					} else {
						color_left = new Color(levelMap.getRGB(x - 1, y ));
					}
					
					if(color_left.getRed() == 0
						&& color_left.getGreen() == 0
						&& color_left.getBlue() == 0) {
						this.levelWorld.add(new Mesh(new float[] {
							x - 0.1f, 1, y - 0.1f,
							x - 0.1f, 0, y - 0.1f,
							x - 0.1f, 0, y + 0.9f,
							x - 0.1f, 1, y + 0.9f
						}, new float[] {
							1f / 16f, 0f,
							1f / 16f, 1f / 16f,
							0f, 1f / 16f,
							0f, 0f
						}));
					}
				}
			}
		}
		
		this.r_levelList = GL11.glGenLists(1);
		GL11.glNewList(this.r_levelList, GL11.GL_COMPILE);
		GL11.glBegin(GL11.GL_QUADS);
		{
			for(int i = 0; i < this.levelWorld.size(); i++) {
				this.levelWorld.get(i).render();
			}
		}
		GL11.glEnd();
		GL11.glEndList();
	}
	
	public void render() {
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, Texture.textures.get("sheet").getId());
		GL11.glCallList(this.r_levelList);
	}
	
	public void checkCollision(Entity e) {
		int posTileX = (int)(e.getPosX() - e.width / 2);
		int posTileY = (int)(e.getPosZ() - e.height / 2);
		
		for(int x = 0; x < 2; x++) {
			for(int y = 0; y < 2; y++) {
				int newPosTileX = posTileX + x;
				int newPosTileY = posTileY + y;
				
				Color color = new Color(this.levelMap.getRGB(newPosTileX, newPosTileY));
				if(color.getRed() == 0
					&& color.getGreen() == 0
					&& color.getBlue() == 0) {
					if(Collision.collideRectWithRect(e.prevPosZ - e.width / 2, e.posZ - e.height / 2, e.width, e.height,
						newPosTileX, newPosTileY, 1, 1)) {
						if(e.prevPosZ < newPosTileY + 0.5f) {
							e.posZ = newPosTileY - e.height / 2;
						} else {
							e.posZ = newPosTileY + 1 + e.height / 2;
						}
					}
					
					if(Collision.collideRectWithRect(e.posX - e.width / 2, e.prevPosZ - e.height / 2, e.width, e.height,
						newPosTileX, newPosTileY, 1, 1)) {
						if(e.prevPosX < newPosTileX + 0.5f) {
							e.posX = newPosTileX - e.width / 2;
						} else {
							e.posX = newPosTileX + 1 + e.width / 2;
						}
					}
				}
			}
		}
	}
}
