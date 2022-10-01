package net.pixx.wolfenstein.mesh;

/* Imports */
import org.lwjgl.opengl.GL11;

/**
 * Creating a mesh of vertices.
 * 
 * @author Pixx CEO
 */
public class Mesh {
	/* Variables */
	private float r, g, b;
	
	/* Arrays */
	private float[] vertices;
	
	/**
	 * Constructor
	 * 
	 * @param vertices Vertices array to use.
	 * @param r Red color.
	 * @param g Green color.
	 * @param b Blue color.
	 */
	public Mesh(float[] vertices, float r, float g, float b) {
		// Initializing.
		this.vertices = vertices;
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public void render() {
		GL11.glColor3f(this.r, this.g, this.b);
		
		for(int i = 0; i < this.vertices.length; i+=3) {
			GL11.glVertex3f(vertices[i], vertices[i + 1], this.vertices[i + 2]);
		}
	}
}
