package presentacion.vista;

import java.awt.Font;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import presentacion.controlador.Controlador;

public class VentanaPersona extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private final int altoVentana = 420;
	private final int anchoVentana = 380;
	private int y_calle = 0;
	private int y_localidad = 0;
	private int y_tipoDeContacto = 0;
	private int y_cumple = 0;
	
	
	private String[] labels = {	"Nombre", "Teléfono", "Email",
								"Tipo de contacto", "Fecha cumpleaños", "Dirección",
								"Localidad","Calle","Altura", "Piso", "Depto" };
	
	private String[] listaLocalidades = {"Polvorines", "Rosario"};
	
	private final int x_start = 10;
	private final int y_start = 10;
	private final int marginBottomFields = 30;
	
	
	private ArrayList<JTextField> textFields = new ArrayList<>();
	private JComboBox<String> comboTipoContactos;
	private JComboBox<String> comboLocalidades;
	private JComboBox<String> comboDiaCumple;
	private JComboBox<String> comboMesCumple;
	
	
	private JButton btnAgregarTipoContacto;
	private JButton btnAgregarLocalidad;
	private JButton btnAgregarPersona;
	private JButton btnCancelar;
	
	
	private JPanel panel;
	
	private Controlador controlador;
	

	public VentanaPersona(Controlador controlador) {
		super();
		this.controlador = controlador;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(600, 200, anchoVentana, altoVentana);
		
		panel = new JPanel();
		panel.setBounds(10, 11, anchoVentana, altoVentana);
		panel.setLayout(null);
		setContentPane(panel);
		
		dibujarLabels();
		dibujarTextFields();
		dibujarControlesLocalidad();
		dibujarControlesTipoContacto();
		dibujarBotones();
		dibujarCumple();
		
		this.setTitle("Complete los datos");
		this.setVisible(true);
	}
			

	private void dibujarControlesLocalidad() {
		comboLocalidades = new JComboBox<>(listaLocalidades);
		comboLocalidades.setSelectedIndex(0);
		comboLocalidades.setBounds(180, y_localidad, 150, 20);
		
		btnAgregarLocalidad = new JButton("+");
		btnAgregarLocalidad.setMargin(new Insets(0,0,0,0));
		btnAgregarLocalidad.setBounds(340, y_localidad, 20,20);
		
		panel.add(comboLocalidades);
		panel.add(btnAgregarLocalidad);
	}
	
	private void dibujarControlesTipoContacto() {
		String[] listaTiposContacto = {"Familia", "Amigos"};
		comboTipoContactos = new JComboBox<>(listaTiposContacto);
		comboTipoContactos.setSelectedIndex(0);
		comboTipoContactos.setBounds(180, y_tipoDeContacto, 150, 20);
		
		btnAgregarTipoContacto = new JButton("+");
		btnAgregarTipoContacto.setMargin(new Insets(0,0,0,0));
		btnAgregarTipoContacto.setBounds(340, y_tipoDeContacto, 20,20);
		
		panel.add(comboTipoContactos);
		panel.add(btnAgregarTipoContacto);
	}
	
	
	
	private void dibujarCumple() {
		String[] meses = {	"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
				"Julio", "Agosto", "Septiembre", "Octubre", "Noviembre",
				"Diciembre" };
		comboMesCumple = new JComboBox<>(meses);
		comboMesCumple.setBounds(260, y_cumple, 100, 20);
		
		String[] dias = new String[31];
		for (int i = 0; i < 31; i++) {
			dias[i] = String.valueOf(i+1);
		}
		comboDiaCumple = new JComboBox<String>(dias);
		comboDiaCumple.setBounds(180, y_cumple, 70, 20);
		
		
		panel.add(comboMesCumple);
		panel.add(comboDiaCumple);
		
		
	}
	


	public JButton getBtnAgregarPersona() {
		return btnAgregarPersona;
	}
	
	
	private void dibujarLabels() {
		int ancho = 200;
		int alto = 20;
		int x = x_start;
		int y = 11;
		
		int margintBottom = 0;
		
		for (int i = 0; i < labels.length; i++) {
			
			JLabel temp = new JLabel(labels[i]);
			temp.setBounds(	x,
							y + margintBottom,
							ancho, alto);
			
			if(temp.getText().equals("Dirección")) {
				temp.setFont(new Font("Arial", 2, 12));
			}
			
			if(labels[i].equals("Tipo de contacto"))
				y_tipoDeContacto = y + margintBottom;
			else if(labels[i].equals("Calle"))
				y_calle = y + margintBottom;
			else if (labels[i].equals("Localidad")) {
				y_localidad = y + margintBottom;
			}
			else if(labels[i].equals("Fecha cumpleaños"))
				y_cumple = y + margintBottom;
			
			panel.add(temp);
			margintBottom += marginBottomFields;
		}
	}
	
	private void dibujarTextFields() {
		int x = 180;
		int y = y_start;
		int ancho = 180;
		int alto = 20;
		for (int i = 0; i < 7; i++) {
			JTextField temp = new JTextField();
			if(i == 3) {
				y = y_calle;
			}
			temp.setBounds(	x,
							y,
							ancho, alto);
			
			textFields.add(temp);
			panel.add(textFields.get(i));
			y += marginBottomFields;
		}
	}
	
	private void dibujarBotones() {
		
		btnAgregarPersona = new JButton("Agregar");
		btnAgregarPersona.addActionListener(this.controlador);
		btnAgregarPersona.setBounds(	220,
										this.altoVentana - 80,
										89, 23);
		panel.add(btnAgregarPersona);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(	this.anchoVentana / 8,
								this.altoVentana - 80,
								100, 23);
		btnCancelar.addActionListener( e -> dispose());
		panel.add(btnCancelar);
	}
	
	
	
	//---------------------Getters--------------------
	public JTextField getTxtNombre() {
		return textFields.get(0);
	}

	public JTextField getTxtTelefono() {
		return textFields.get(1);
	}
	
	public JTextField getTxtEmail() {
		return textFields.get(2);
	}
	
	public JTextField getTxtPiso() {
		return textFields.get(3);
	}
	
	public JTextField getTxtDepto() {
		return textFields.get(4);
	}
	
	
}

