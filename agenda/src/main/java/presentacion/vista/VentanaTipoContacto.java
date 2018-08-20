package presentacion.vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import presentacion.controlador.Controlador;

public class VentanaTipoContacto extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTipoContacto;
	private JButton btnAgregarTipoContacto;
	private Controlador controlador;

	public VentanaTipoContacto(Controlador controlador) {
		super();
		this.controlador = controlador;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 343, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 307, 385);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblTipoContacto = new JLabel("TipoContacto");
		lblTipoContacto.setBounds(10, 11, 113, 14);
		panel.add(lblTipoContacto);
				
		txtTipoContacto = new JTextField();
		txtTipoContacto.setBounds(133, 8, 164, 20);
		panel.add(txtTipoContacto);
		txtTipoContacto.setColumns(10);
				
		btnAgregarTipoContacto = new JButton("Agregar");
		btnAgregarTipoContacto.addActionListener(this.controlador);
		btnAgregarTipoContacto.setBounds(208, 295, 89, 23);
		panel.add(btnAgregarTipoContacto);
		
		this.setVisible(true);
	}
	
	public JTextField getTxtLocalidad() {
		return txtTipoContacto;
	}

	public JButton getBtnAgregarLocalidad() {
		return btnAgregarTipoContacto;
	}
	
}

