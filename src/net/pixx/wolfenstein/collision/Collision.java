package net.pixx.wolfenstein.collision;

public class Collision {
	public static boolean collideRectWithRect(double pos1X, double pos1Y, double width1, double height1,
		double pos2X, double pos2Y, double width2, double height2) {
		return pos1X <= pos2X + width2 && pos2X <= pos1X + width1
			&& pos1Y <= pos2Y + height2 && pos2Y <= pos1Y + height1;
	}
}
