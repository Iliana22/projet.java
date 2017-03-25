package Play;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Start extends JFrame{
	
	public static void Start (String[] args){
		EventQueue.invokeLater(new Runnable(){
			@Override
			public void run(){
				Game game = new Game(); //Establecemos un nuevo juego
				
				JFrame window = new JFrame("2048"); //Nombre de la ventana
				window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cerrar proceso cuando se de click a x
				window.setResizable(false); //Negar modificacion del tamaño de la ventana
				window.add(game); 
				window.pack();
				window.setLocationRelativeTo(null); //Aparece la ventana en medio de la pantalla
				window.setVisible(true); //Volver nuestra ventana visible
				
				game.start();
			}
		});
	}
}