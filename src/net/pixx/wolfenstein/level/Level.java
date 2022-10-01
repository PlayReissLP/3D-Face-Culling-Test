package net.pixx.wolfenstein.level;

/* Imports */
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

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
					}, (float)Math.random(), (float)Math.random(), (float)Math.random()));
					this.levelWorld.add(new Mesh(new float[] {
						x, 2, y,
						x, 2, y + 1,
						x + 1, 2, y + 1,
						x + 1, 2, y
					}, (float)Math.random(), (float)Math.random(), (float)Math.random()));
				}
			}
		}
	}
	
	public void render() {
		for(int i = 0; i < this.levelWorld.size(); i++) {
			this.levelWorld.get(i).render();
		}
	}
}
