package presentacion.vista;


import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import persistencia.conexion.RWProperties;
import presentacion.controlador.Controlador;

import java.io.IOException;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class Vista {
	private JFrame frame;
	private JTable tablaPersonas;
	private JButton btnAgregar;
	private JButton btnBorrar;
	private JButton btnEditar;
	private JButton btnReporte;
	private JButton btnConfig;
	private DefaultTableModel modelPersonas;
	private  String[] nombreColumnas = {	"Nombre","Apellido","Teléfono","Email",
											"Grupo","Cumpleaños","Localidad",
											"Calle","Altura","Piso","Depto"};
	
	private Controlador controlador;

	public Vista() {
		super();
		initialize();
	}


	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 262);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane spPersonas = new JScrollPane();
		spPersonas.setBounds(10, 11, 414, 182);
		panel.add(spPersonas);
		
		modelPersonas = new DefaultTableModel(null,nombreColumnas);
		tablaPersonas = new JTable(modelPersonas);
		
		tablaPersonas.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaPersonas.getColumnModel().getColumn(0).setResizable(false);
		tablaPersonas.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaPersonas.getColumnModel().getColumn(1).setResizable(false);
		
		
		JScrollPane sPHorizontal = new JScrollPane(tablaPersonas, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tablaPersonas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		panel.add(sPHorizontal);
		
		spPersonas.setViewportView(tablaPersonas);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(10, 228, 89, 23);
		panel.add(btnAgregar);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(109, 228, 89, 23);
		panel.add(btnEditar);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(208, 228, 89, 23);
		panel.add(btnBorrar);
		
		btnReporte = new JButton("Reporte");
		btnReporte.setBounds(307, 228, 89, 23);
		panel.add(btnReporte);
		frame.setResizable(false);
		
		
		URL imgURL = getClass().getResource("../../gear.png");
		Icon icon = new ImageIcon(imgURL);
		btnConfig = new JButton(icon);
		btnConfig.setBounds(405, 228, 25, 23);
		panel.add(btnConfig);
		
	}
	
	public void show() {
//		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
//		this.frame.addWindowListener(new WindowAdapter() {
//			@Override
//		    public void windowClosing(WindowEvent e) {
//		        int confirm = JOptionPane.showOptionDialog(
//		             null, "Estas seguro que quieres salir de la Agenda!?", 
//		             "Confirmacion", JOptionPane.YES_NO_OPTION,
//		             JOptionPane.QUESTION_MESSAGE, null, null, null);
//		        if (confirm == 0) {
//		        	Conexion.getConexion().cerrarConexion();
//		           System.exit(0);
//		        }
//		    }
//		});
		this.frame.setVisible(true);
	}
	
	
	
	
	public JButton getBtnConfig() {
		return btnConfig;
	}


	public JButton getBtnAgregar() {
		return btnAgregar;
	}
	

	public JButton getBtnBorrar() {
		return btnBorrar;
	}
	 
	
	public JButton getBtnEditar() {
		return btnEditar;
	}


	public JButton getBtnReporte() {
		return btnReporte;
	}
	
	public DefaultTableModel getModelPersonas() {
		return modelPersonas;
	}
	
	public JTable getTablaPersonas() {
		return tablaPersonas;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}
}
