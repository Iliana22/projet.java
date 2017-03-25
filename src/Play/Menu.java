package Play;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 498, 338);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton = new JButton("Comenzar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
				dispose();
			}
		});
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\zonia\\.eclipse\\workspace\\2048\\bin\\Image\\icon.png"));
		contentPane.add(btnNewButton, BorderLayout.CENTER);
	}

}
