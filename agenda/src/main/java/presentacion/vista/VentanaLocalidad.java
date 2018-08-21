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

public class VentanaLocalidad extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	
	private int anchoV = 420;
	private int altoV = 250;
	
	
	
    private JButton btnNueva;
    private JButton btnVolver;
    private JButton btnBorrar;
    private JButton btnEditar;
    
    private JPanel contenedor;
    private JPanel contenedorBotones;
    private JScrollPane contenedorTabla;
    
    private String[] nombreColumnas = {"Localidad"};
    private JTable tablaLocalidades;
    private DefaultTableModel modelLocalidad;
    
    
    private Controlador controlador;

    public Controlador getControlador() {
		return controlador;
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public VentanaLocalidad(Controlador controlador) {
    	super();
    	this.setTitle("Localidades");
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
        btnNueva = new JButton("Nueva");
        btnEditar = new JButton("Editar");
        
        contenedorBotones.setLayout(new FlowLayout());
        contenedorBotones.add(btnVolver);
        contenedorBotones.add(btnBorrar);
        contenedorBotones.add(btnEditar);
        contenedorBotones.add(btnNueva);
        
        
        modelLocalidad = new DefaultTableModel(null, nombreColumnas);
        
        tablaLocalidades = new JTable(modelLocalidad);
        tablaLocalidades.setFillsViewportHeight(true);
        contenedorTabla.setBounds(10,11, 414, 182);
        contenedorTabla.setViewportView(tablaLocalidades);
        
        
        
        getContentPane().add(contenedor);

        this.setVisible(true);
        
        addListeners();
    }
    
    
    public JButton getBtnEditar() {
		return btnEditar;
	}

	private void addListeners() {
    	btnNueva.addActionListener(controlador);
    	btnBorrar.addActionListener(controlador);
    	btnEditar.addActionListener(controlador);
    }
   

	public JButton getBtnNueva() {
		return btnNueva;
	}

	public void setBtnNueva(JButton btnNueva) {
		this.btnNueva = btnNueva;
	}

	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public void setBtnBorrar(JButton btnBorrar) {
		this.btnBorrar = btnBorrar;
	}

	public JTable getTablaLocalidades() {
		return tablaLocalidades;
	}

	public DefaultTableModel getModelLocalidad() {
		return modelLocalidad;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}
    
	
	

}
