package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import presentacion.controlador.Controlador;

public class VentanaTipoContacto extends JFrame {
private static final long serialVersionUID = 1L;
	
	
	private int anchoV = 420;
	private int altoV = 250;
	
	
	
    private JButton btnNuevo;
    private JButton btnVolver;
    private JButton btnBorrar;
    
    private JPanel contenedor;
    private JPanel contenedorBotones;
    private JScrollPane contenedorTabla;
    
    private String[] nombreColumnas = {"Tipo de Contacto"};
    private JTable tablaTiposContacto;
    private DefaultTableModel modelTipoContacto;
    
    private Controlador controlador;

    public Controlador getControlador() {
		return controlador;
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public VentanaTipoContacto(Controlador controlador) {
    	super();
    	this.setTitle("Tipo de Contactos");
    	this.controlador = controlador;
    	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	setBounds(420,220,anchoV,altoV);
    	setResizable(false);
        initComponents();
    }

    private void initComponents() {

    	contenedorBotones = new JPanel();
    	contenedorTabla = new JScrollPane();
    	
    	contenedor = new JPanel();
    	contenedor.setLayout(new BorderLayout());
    	contenedor.add(contenedorTabla, BorderLayout.CENTER);
    	contenedor.add(contenedorBotones, BorderLayout.SOUTH);
        
        
        
        btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> dispose());
        
        btnBorrar = new JButton("Borrar");
        btnNuevo = new JButton("Nuevo");
        
        contenedorBotones.setLayout(new FlowLayout());
        contenedorBotones.setBackground(Color.CYAN);
        contenedorBotones.add(btnVolver);
        contenedorBotones.add(btnBorrar);
        contenedorBotones.add(btnNuevo);
        
        
        modelTipoContacto = new DefaultTableModel(null, nombreColumnas);
        
        tablaTiposContacto = new JTable(modelTipoContacto);
        tablaTiposContacto.setFillsViewportHeight(true);
        contenedorTabla.setBounds(10,11, 414, 182);
        contenedorTabla.setViewportView(tablaTiposContacto);
        
        
        
        getContentPane().add(contenedor);

        this.setVisible(true);
        
        addListeners();
    }
    
    
    private void addListeners() {
    	btnNuevo.addActionListener(controlador);
    	btnBorrar.addActionListener(controlador);
    }
   

	public JButton getBtnNuevo() {
		return btnNuevo;
	}


	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public void setBtnBorrar(JButton btnBorrar) {
		this.btnBorrar = btnBorrar;
	}

	public JTable getTablaTiposContacto() {
		return tablaTiposContacto;
	}

	public DefaultTableModel getModelTipoContacto() {
		return modelTipoContacto;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}
    
	


}

