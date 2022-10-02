package net.pixx.wolfenstein.player;

/* Imports */
import net.pixx.wolfenstein.Wolfenstein;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class Player {
	/* Variables */
	public double posX;
	public double posY;
	public double posZ;
	public double rotX;
	public double rotY;
	private int speed;
	
	/* Constructor */
	public Player(double posX, double posY, double posZ) {
		// Initializing.
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
		this.speed = 5;
	}
	
	/* Methods */
	/**
	 * Updating player stuff.
	 */
	public void update(float dt) {
		float dx = Mouse.getX() - Wolfenstein.getWidth() / 2;
		float dy = Mouse.getY() - Wolfenstein.getHeight() / 2;
		
		this.rotX -= dy / 5f;
		this.rotY += dx / 5f;
		
		if(this.rotX > 90) {
			this.rotX = 90;
		}
		if(this.rotX < -90) {
			this.rotX = -90;
		}
		
		Mouse.setCursorPosition(Wolfenstein.getWidth() / 2, Wolfenstein.getHeight() / 2);
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			this.posX += Math.cos(Math.toRadians(this.rotY - 90)) * this.speed * dt;
			this.posZ += Math.sin(Math.toRadians(this.rotY - 90)) * this.speed * dt;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			this.posX -= Math.cos(Math.toRadians(this.rotY - 90)) * this.speed * dt;
			this.posZ -= Math.sin(Math.toRadians(this.rotY - 90)) * this.speed * dt;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			this.posX += Math.cos(Math.toRadians(this.rotY)) * this.speed * dt;
			this.posZ += Math.sin(Math.toRadians(this.rotY)) * this.speed * dt;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			this.posX -= Math.cos(Math.toRadians(this.rotY)) * this.speed * dt;
			this.posZ -= Math.sin(Math.toRadians(this.rotY)) * this.speed * dt;
		}
	}
	
	/**
	 * Get the players current max speed.
	 * 
	 * @return Player current may speed.
	 */
	public int getSpeed() {
		return this.speed;
	}
	
	/**
	 * Setting the speed the players should have as max.
	 * 
	 * @param speed The speed the player should have as max.
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
