package net.pixx.wolfenstein.entities;

public class Entity {
	/* Variables */
	public double posX, posY, posZ;
	public double prevPosX, prevPosY, prevPosZ;
	public float width, height;
	
	/* Constructors */
	public Entity() {
		// Initializing.
		this.prevPosX = 0;
		this.prevPosY = 0;
		this.prevPosZ = 0;
	}
	
	public Entity(float width, float height) {
		// Initializing.
		this.width = width;
		this.height = height;
	}
	
	/* Methods */
	public double getPosX() {
		return posX;
	}
	
	public void setPosX(double posX) {
		this.posX = posX;
	}
	
	public double getPosY() {
		return posY;
	}
	
	public void setPosY(double posY) {
		this.posY = posY;
	}
	
	public double getPosZ() {
		return posZ;
	}
	
	public void setPosZ(double posZ) {
		this.posZ = posZ;
	}
	
	public double getPrevPosX() {
		return prevPosX;
	}
	
	public void setPrevPosX(double prevPosX) {
		this.prevPosX = prevPosX;
	}
	
	public double getPrevPosY() {
		return prevPosY;
	}
	
	public void setPrevPosY(double prevPosY) {
		this.prevPosY = prevPosY;
	}
	
	public double getPrevPosZ() {
		return prevPosZ;
	}
	
	public void setPrevPosZ(double prevPosZ) {
		this.prevPosZ = prevPosZ;
	}
}
