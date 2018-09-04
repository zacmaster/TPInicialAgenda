package presentacion.vista;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import persistencia.conexion.RWProperties;
import presentacion.controlador.Controlador;

public class VentanaConfiguracionDB extends JFrame{
	private static final long serialVersionUID = 1L;
	private Controlador controlador;
	private JPanel contenedor;
	private JLabel labelDescripcion;
	
	
	private JTextField inputIP;
	private JTextField inputPuerto;
	private JTextField inputUsuario;
	private JTextField inputPassword;
	
	private JButton btnCancelar;
	private JButton btnGuardar;
	
	
	
	private final int MARGEN_IZQ = 100;
	public VentanaConfiguracionDB(Controlador controlador) {
		super();
		this.controlador = controlador;
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400, 200, 400, 300);
		setResizable(false);
		
		this.setTitle("Configuracion de la Base de Datos");
		
		
		contenedor = new JPanel();
		contenedor.setLayout(null);
		labelDescripcion = new JLabel(
				"<html>Aquí puede modificar la configuración  de la <br> conexión  a la Base de datos...</html>");
		labelDescripcion.setBounds(20, 0, 300, 50);
		contenedor.add(labelDescripcion);
		
		getContentPane().add(contenedor);
		
		
		inputIP = new JTextField();
		inputPuerto = new JTextField();
		inputUsuario = new JTextField();
		inputPassword = new JTextField();
		
		
		JLabel ipLabel = new JLabel("IP");
		JLabel puertoLabel = new JLabel("Puerto");
		JLabel usuarioLabel = new JLabel("Usuario");
		JLabel passwordLabel = new JLabel("Password");
		
		btnGuardar = new JButton("Guardar");
		btnCancelar = new JButton("Cancelar");
		
		
		ipLabel.setBounds(MARGEN_IZQ, 50, 100, 20);
		puertoLabel.setBounds(MARGEN_IZQ,80,100,20);
		usuarioLabel.setBounds(MARGEN_IZQ,110,100,20);
		passwordLabel.setBounds(MARGEN_IZQ, 140, 100, 20);
		
		inputIP.setBounds(MARGEN_IZQ + 100, 50, 120, 20);
		inputPuerto.setBounds(MARGEN_IZQ + 100, 80, 120, 20);
		inputUsuario.setBounds(MARGEN_IZQ + 100, 110, 120, 20);
		inputPassword.setBounds(MARGEN_IZQ + 100, 140, 120, 20);
		
		btnCancelar.setBounds(MARGEN_IZQ - 20, 200, 100, 20);
		btnGuardar.setBounds(MARGEN_IZQ + 150, 200, 100, 20);

		
		contenedor.add(ipLabel);
		contenedor.add(puertoLabel);
		contenedor.add(usuarioLabel);
		contenedor.add(passwordLabel);
		
		contenedor.add(btnGuardar);
		contenedor.add(btnCancelar);
	
		contenedor.add(inputIP);
		contenedor.add(inputPuerto);
		contenedor.add(inputUsuario);
		contenedor.add(inputPassword);
		
		btnCancelar.addActionListener(this.controlador);
		btnGuardar.addActionListener(this.controlador);
		
		leerProperties();
		
		this.setVisible(true);
	}
	
	private void leerProperties(){
		try {
			inputIP.setText(RWProperties.getValue("dbIP"));
			inputPuerto.setText(RWProperties.getValue("dbPort"));
			inputUsuario.setText(RWProperties.getValue("dbUser"));
			inputPassword.setText(RWProperties.getValue("dbPassword"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void guardar() throws IOException {
		RWProperties.writeValue("dbIP", inputIP.getText());
		RWProperties.writeValue("dbPort", inputPuerto.getText());
		RWProperties.writeValue("dbUser", inputUsuario.getText());
		RWProperties.writeValue("dbPassword", inputPassword.getText());
	}
	
	public JButton getBtnGuardar() {
		return btnGuardar;
	}
	public JButton getBtnCancelar() {
		return btnCancelar;
	}
	
}	
