package presentacion.vista;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import presentacion.controlador.Controlador;

public class DialogoNuevaLocalidad extends JFrame{
 
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	@SuppressWarnings("unused")
	private Controlador controlador;
	
//	Bounds
	private final int x = 550;
	private final int  y = 250;
	private final int width = 300;
	private final int height = 100;
	
	
	private JButton btnAgregar;
	private JButton btnCancelar;
	private JTextField input;
	private JLabel label;
	
	public DialogoNuevaLocalidad(Controlador controlador) {
		super();
		this.setBounds(x,y,width,height);
		this.setVisible(true);
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.controlador = controlador;
		this.setTitle("Nueva localidad");
		this.setLayout(null);
		
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(x,y,width,height);
		
		setContentPane(panel);
		
		
		
		label = new JLabel("Nombre");
		label.setBounds(25, 5, 100, 20);
		
		
		input = new JTextField();
		input.setBounds(110, 5, 150, 20);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(controlador);
		btnCancelar.setBounds(10, 40, 100, 23);
		btnCancelar.addActionListener(e -> dispose());
		
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(controlador);
		btnAgregar.setBounds(180, 40, 100, 23);
		
		panel.add(btnAgregar);
		panel.add(btnCancelar);
		panel.add(input);
		panel.add(label);
		
		
		
		
		
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public JTextField getInput() {
		return input;
	}
	
	
    
}