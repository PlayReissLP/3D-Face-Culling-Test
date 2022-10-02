package net.pixx.wolfenstein;

/* Imports */
import net.pixx.wolfenstein.player.Player;
import net.pixx.wolfenstein.exceptions.FatalExceptions;
import net.pixx.wolfenstein.exceptions.WolfException;
import net.pixx.wolfenstein.level.Level;
import net.pixx.wolfenstein.texture.Texture;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;


public class Wolfenstein implements Runnable {
	/* Objects */
	private Player player;
	private Level level;
	
	/* Variables */
	private static final int WINDOW_WIDTH = 1280;
	private static final int WINDOW_HEIGHT = 720;
	private boolean running;
	
	/**
	 * Constructor
	 */
	public Wolfenstein() {
		// Initializing.
		this.running = true;
		this.player = new Player(5, 0.5f, 5);
		this.level = new Level();
	}

	/**
	 * Thread
	 * 
	 * Running the game initializations.
	 */
	public void run() {
		this.initDisplay();
		this.initPeripherals();
		this.initGL();
		this.initGame();
		this.runGame();
	}
	
	/**
	 * The main game loop.
	 */
	private void runGame() {
		long lastFrame = System.currentTimeMillis();
		while(this.running) {
			this.running = !Display.isCloseRequested();
			
			long currFrame = System.currentTimeMillis();
			float dt = (currFrame - lastFrame) / 1000f;
			lastFrame = currFrame;
			
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			
			this.getInput();
			this.update(dt);
			this.render();
			
			Display.update();
		}
		
		Display.destroy();
	}
	
	/**
	 * Start
	 * 
	 * Starts the main game thread.
	 */
	public void start() {
		Thread thread = new Thread(this, "Wolfenstein main thread");
		thread.setPriority(Thread.MAX_PRIORITY);
		thread.start();
	}
	
	/**
	 * Input
	 */
	private void getInput() {
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			this.close();
		}
	}
	
	/**
	 * Update
	 * 
	 * @param dt Current delta time.
	 */
	private void update(float dt) {
		this.player.update(dt);
	}
	
	/**
	 * Render
	 */
	private void render() {
		GL11.glPushMatrix();
		{
			GL11.glRotatef((float)this.player.rotX, 1, 0, 0);
			GL11.glRotatef((float)this.player.rotY, 0, 1, 0);
			GL11.glTranslatef(-(float)this.player.posX, -(float)this.player.posY, -(float)this.player.posZ);
			
			GL11.glColor3f(1, 1, 1);
			GL11.glBegin(GL11.GL_QUADS);
			{
				this.level.render();
			}
			GL11.glEnd();
		}
		GL11.glPopMatrix();
	}
	
	/**
	 * Close / Dispose
	 */
	private void close() {
		this.running = false;
	}
	
	/**
	 Gets the current window width.
	 * @return Current window width.
	 */
	public static int getWidth() {
		return Wolfenstein.WINDOW_WIDTH;
	}
	
	/**
	 * Gets the current window height.
	 * @return Current window height.
	 */
	public static int getHeight() {
		return Wolfenstein.WINDOW_HEIGHT;
	}
	
	/* Private Methods */
	private void initGame() {
		Texture.textures.put("level_0", new Texture("/levels/level_0.png"));
		Texture.textures.put("sheet", new Texture("/tiles/sheet.png"));
		
		this.grabMouse();
		
		this.level.loadLevel(0);
	}
	
	/**
	 * Initializing OpenGL stuff.
	 */
	private void initGL() {
		GL11.glClearColor(0, 0, 0, 0);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GLU.gluPerspective(45, Wolfenstein.WINDOW_WIDTH / Wolfenstein.WINDOW_HEIGHT, 0.3f, 1000f);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
		GL11.glFrontFace(GL11.GL_CW);
		GL11.glCullFace(GL11.GL_BACK);
		GL11.glEnable(GL11.GL_CULL_FACE);
		
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}
	
	/**
	 * Creates and initializes the display.
	 */
	private void initDisplay() {
		Display.setTitle("Wolfenstein 3D");
		try {
			Display.setDisplayMode(new DisplayMode(Wolfenstein.WINDOW_WIDTH, Wolfenstein.WINDOW_HEIGHT));
		} catch (LWJGLException e) {
			WolfException.createMessage(FatalExceptions.create(FatalExceptions.EX_DISPLAY_MODE));
		}
		
		try {
			Display.create();
		} catch (LWJGLException e) {
			WolfException.createMessage(FatalExceptions.create(FatalExceptions.EX_DISPLAY_CREATE));
		}
	}
	
	/**
	 * Initializing mouse, keyboard etc.
	 */
	private void initPeripherals() {
		if(!Keyboard.isCreated()) {
			try {
				Keyboard.create();
			} catch (LWJGLException e) {
				WolfException.createMessage(FatalExceptions.create(FatalExceptions.EX_KEYBOARD_CREATE));
			}
		}
		if(!Mouse.isCreated()) {
			try {
				Mouse.create();
			} catch (LWJGLException e) {
				WolfException.createMessage(FatalExceptions.create(FatalExceptions.EX_MOUSE_CREATE));
			}
		}
	}
	
	/**
	 * Grabs the mouse and changes the mouse position to the middle of the display.
	 */
	private void grabMouse() {
		Mouse.setCursorPosition(Wolfenstein.WINDOW_WIDTH / 2, Wolfenstein.WINDOW_HEIGHT / 2);
		Mouse.setGrabbed(true);
	}
}
