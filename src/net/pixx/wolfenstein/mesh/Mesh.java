package net.pixx.wolfenstein.mesh;

/* Imports */
import org.lwjgl.opengl.GL11;

/**
 * Creating a mesh of vertices.
 * 
 * @author Pixx CEO
 */
public class Mesh {
	/* Arrays */
	private float[] vertices;
	private float[] texCoords;
	
	/**
	 * Constructor
	 * 
	 * @param vertices Vertices array to use.
	 */
	public Mesh(float[] vertices, float[] texCoords) {
		// Initializing.
		this.vertices = vertices;
		this.texCoords = texCoords;
	}
	
	public void render() {
		int texCount = 0;
		for(int i = 0; i < this.vertices.length; i += 3) {
			GL11.glTexCoord2f(this.texCoords[texCount], this.texCoords[texCount + 1]);
			GL11.glVertex3f(vertices[i], vertices[i + 1], this.vertices[i + 2]);
			
			texCount += 2;
		}
	}
}
