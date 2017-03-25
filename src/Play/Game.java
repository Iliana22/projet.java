package Play;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Game extends JPanel implements KeyListener, Runnable {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 500; //Modificar ancho de la ventana
	public static final int HEIGHT = 600; //Modificar largo de la pantalla
	public static final Font main = new Font("Bebas Neue Regular",Font.PLAIN,28); //Tipo de fuente para la letra
	private Thread game; //Se crea un hilo
	private boolean running; 
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private GameBoard board;

	
	private long startTime;
	private long elapsed;
	private boolean set;
	
	public Game(){ //Estructura de Game
		setFocusable(true);
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		addKeyListener(this); //Agregamos los eventos del teclado a esta clase "this"
	
		board = new GameBoard(WIDTH / 2 - GameBoard.BOARD_WIDTH / 2, HEIGHT - GameBoard.BOARD_HEIGHT - 10);
	}
	
	private void update(){ //Actualizar
		board.update();
		Keyboard.update();
		
	}
	
	private void render(){ //Hacer
		//En esta parte hacemos el boseto de lo que quueremos conlocar en la pantalla
		Graphics2D g = (Graphics2D) image.getGraphics(); //Iniciamos un grafico y lo guardamos en g
		g.setColor(Color.white); //Limpia la pantalla
		g.fillRect(0, 0, WIDTH, HEIGHT); //Dibujamos un rectangulo
		board.render(g); //Agregamos nuestro boseto a board
		g.dispose(); //Desplegar g
		
		Graphics2D g2d = (Graphics2D) getGraphics(); //Creamos otro grafico
		g2d.drawImage(image, 0, 0, null); //dibujamos el segundo grafico
		g2d.dispose(); //Desplegamos el segundo grafico para que aparesca
	}
	
	@Override
	public void run() { //Cuando la aplicacion se corra se ejecutara esto
	int fps = 0, updates = 0;
	long fpsTimer = System.currentTimeMillis();
	double nsPerUpdate =  1000000000.0 / 60;
	
	//last update time in nanoseconds
	double then = System.nanoTime();
	double unprocessed = 0;
	
	while(running){
		
		boolean shouldRender = false;
		double now = System.nanoTime();
		unprocessed += (now - then) / nsPerUpdate;
		then = now; 
		
	//update queue 
	while(unprocessed >= 1){
		updates++;
		update();
		unprocessed--;
		shouldRender = true;
	}
	
	//render
	if(shouldRender){
		fps++;
		render();
		shouldRender = false;
	}
	else{
		try{
			Thread.sleep(1);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	}
	//FPS Timer 
	if(System.currentTimeMillis() - fpsTimer > 1000){
		System.out.printf("%d fps %d updates", fps, updates);
		System.out.println();
		fps = 0;
		updates = 0;
		fpsTimer += 1000;
	}
	}
	
	public synchronized void start(){
		if(running) return;
		running = true;
		game = new Thread(this, "game");
		game.start();
	}
	
	public synchronized void stop(){
		if(!running)return;
		running = false;
		System.exit(0);
	}

	@Override
	public void keyPressed(KeyEvent e) {
	
		Keyboard.KeyPressed(e);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Keyboard.keyReleased(e);
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}
	

}
