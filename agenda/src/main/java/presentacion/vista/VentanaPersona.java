package presentacion.vista;

import java.awt.Font;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import Atxy2k.CustomTextField.RestrictedTextField;
import presentacion.controlador.Controlador;
import presentacion.controlador.ValidadorInput;

public class VentanaPersona extends JFrame implements CamposValidables{
	private static final long serialVersionUID = 1L;
	
	private final int altoVentana = 450;
	private final int anchoVentana = 380;
	private int y_calle = 0;
	private int y_localidad = 0;
	private int y_tipoDeContacto = 0;
	private int y_cumple = 0;
	private JDateChooser dateChooser;
	private ValidadorInput validador;
	
	
	
	private String[] labels = {	"Nombre", "Apellido", "Teléfono", "Email",
								"Tipo de contacto", "Fecha cumpleaños", "Dirección",
								"Localidad","Calle","Altura", "Piso", "Depto" };
	
	
	private final int x_start = 10;
	private final int y_start = 10;
	private final int marginBottomFields = 30;
	
	
	private ArrayList<JTextField> textFields = new ArrayList<>();
	private JComboBox<String> comboTipoContactos;
	private JComboBox<String> comboLocalidades;
	
	
	private JButton btnAgregarTipoContacto;
	private JButton btnABMLocalidad;
	private JButton btnAgregarPersona;
	private JButton btnCancelar;
	
	private ArrayList<String> campos;
	
	private JPanel panel;
	
	private Controlador controlador;
	

	public VentanaPersona(Controlador controlador) {
		super();
		this.controlador = controlador;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400, 200, anchoVentana, altoVentana);
		setResizable(false);
		
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
		
		limiteCaracteres();
		this.setTitle("Complete los datos");
		this.setVisible(true);
		
	}
			

	private void dibujarControlesLocalidad() {
		comboLocalidades = new JComboBox<>();
		comboLocalidades.setBounds(180, y_localidad, 150, 20);
		
		btnABMLocalidad = new JButton("+");
		btnABMLocalidad.setMargin(new Insets(0,0,0,0));
		btnABMLocalidad.addActionListener(this.controlador);
		btnABMLocalidad.setBounds(340, y_localidad, 20,20);
		
		panel.add(comboLocalidades);
		panel.add(btnABMLocalidad);
	}
	
	private void dibujarControlesTipoContacto() {
		comboTipoContactos = new JComboBox<>();
		comboTipoContactos.setBounds(180, y_tipoDeContacto, 150, 20);
		
		btnAgregarTipoContacto = new JButton("+");
		btnAgregarTipoContacto.addActionListener(this.controlador);
		btnAgregarTipoContacto.setMargin(new Insets(0,0,0,0));
		btnAgregarTipoContacto.setBounds(340, y_tipoDeContacto, 20,20);
		
		panel.add(comboTipoContactos);
		panel.add(btnAgregarTipoContacto);
	}
	
	
	
	public int getY_tipoDeContacto() {
		return y_tipoDeContacto;
	}


	public void setY_tipoDeContacto(int y_tipoDeContacto) {
		this.y_tipoDeContacto = y_tipoDeContacto;
	}


	public JComboBox<String> getComboTipoContactos() {
		return comboTipoContactos;
	}


	public void setComboTipoContactos(JComboBox<String> comboTipoContactos) {
		this.comboTipoContactos = comboTipoContactos;
		this.comboTipoContactos.repaint();
	}





	private void dibujarCumple() {

		dateChooser = new JDateChooser();
		dateChooser.setBounds(180, y_cumple, 180, 20);
		dateChooser.setLocale(new Locale("es"));
		dateChooser.setDate(Calendar.getInstance().getTime());
		panel.add(dateChooser);
		
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
		for (int i = 0; i < 8; i++) {
			JTextField temp = new JTextField();
			if(i == 4) {
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
		btnAgregarPersona.setBounds(220, this.altoVentana - 70, 89, 23);
		panel.add(btnAgregarPersona);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(	this.anchoVentana / 8, this.altoVentana - 70, 100, 23);
		btnCancelar.addActionListener( e -> dispose());
		panel.add(btnCancelar);
	}
	
	private void limiteCaracteres() {
		textFields.get(0).setDocument(new JTextFieldCharLimit(11)); //Nombre:11 char
		textFields.get(1).setDocument(new JTextFieldCharLimit(11)); //Apellido: 11 char
		textFields.get(2).setDocument(new JTextFieldCharLimit(20)); //Telefono: 20 char
		textFields.get(3).setDocument(new JTextFieldCharLimit(45)); //Email: 20 char
		textFields.get(4).setDocument(new JTextFieldCharLimit(30)); //Calle: 30 char
		textFields.get(5).setDocument(new JTextFieldCharLimit(4)); //Altura: 4 char
		textFields.get(6).setDocument(new JTextFieldCharLimit(2)); //Piso: 2 char
		textFields.get(7).setDocument(new JTextFieldCharLimit(2)); //Depto: 2 char
		
	}

	public ArrayList<String> camposInvalidos() {
		campos = new ArrayList<>();
		validador = new ValidadorInput();
		if(! validador.validarTextoSinEspacios(textFields.get(0).getText())) campos.add("Nombre");
		if(! validador.validarTextoSinEspacios(textFields.get(1).getText())) campos.add("Apellido");
		if(! validador.validarTelefono(textFields.get(2).getText())) campos.add("Teléfono");
		if(! validador.validarEmail(textFields.get(3).getText())) campos.add("Email");
		if(! validador.validarTextoConEspacios(textFields.get(4).getText())) campos.add("Calle");
		if(! validador.validarNumerico(textFields.get(5).getText())) campos.add("Altura");
		if(! validador.validarNumericoVacio(textFields.get(6).getText())) campos.add("Piso");//Acepta que el campo esté vacío
		if(! validador.validarAlfaNumericoVacio(textFields.get(7).getText())) campos.add("Depto"); //Acepta que el campo esté vacío
		return campos;
	}
	
	public String mensajeVerificarCampos() {
		String texto = "Por favor revise los campos: ";
		for (int i = 0; i < campos.size(); i++) {
			if(campos.size() == 1) texto = "Por favor revise el campo " + campos.get(i);
			else {
				if(i == campos.size()-1) texto += campos.get(i) + ".";
				else texto += campos.get(i) + ", ";
			}
		}
		return texto;
	}
	
	
	
	public JDateChooser getDateChooser() {
		return dateChooser;
	}


	//---------------------Getters--------------------
	public JTextField getTxtNombre() {
		return textFields.get(0);
	}

	public JTextField getTxtApellido() {
		return textFields.get(1);
	}

	public JTextField getTxtTelefono() {
		return textFields.get(2);
	}
	
	public JTextField getTxtEmail() {
		return textFields.get(3);
	}
	
	public JTextField getTxtCalle() {
		return textFields.get(4);
	}
	
	public JTextField getTxtAltura() {
		return textFields.get(5);
	}
	public JTextField getTxtPiso() {
		return textFields.get(6);
	}
	public JTextField getTxtDepto() {
		return textFields.get(7);
	}
	
	
	public JButton getBtnAgregarPersona() {
		return btnAgregarPersona;
	}
	
	public JButton getBtnABMLocalidad() {
		return btnABMLocalidad;
	}


	public String getTxtLocalidad() {
		return String.valueOf(comboLocalidades.getSelectedItem());
	}

	public JTextField getTxtCorreo() {
		return textFields.get(2);
	}
	
	public String getFechaNac() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(dateChooser.getDate());
		
	}
	
	public String getTipoContacto() {
		return comboTipoContactos.getSelectedItem().toString();
	}
	
	public JButton getABMTipoContacto() {
		return btnAgregarTipoContacto;
	}


	public JComboBox<String> getComboLocalidades() {
		return comboLocalidades;
	}
	
}

