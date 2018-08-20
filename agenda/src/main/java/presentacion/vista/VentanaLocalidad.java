package presentacion.vista;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.table.DefaultTableModel;

import presentacion.controlador.Controlador;

public class VentanaLocalidad extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JButton btnCancelar;
    private JButton btnAceptar;
    private JButton btnNueva;
    private JButton btnModificar;
    private JButton btnBorrar;
    
    private JPanel contenedorBtnAcepCan;
    private JPanel contenedorBtnABM;
    private JScrollPane jScrollPane1;
    
    private String[] nombreColumna = {"Localidad"};
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
    	this.controlador = controlador;
    	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	setLocation(420, 220);
    	setResizable(false);
        initComponents();
    }

    private void initComponents() {

        contenedorBtnAcepCan = new JPanel();
        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        
        
        
        contenedorBtnABM = new JPanel();
        btnNueva = new JButton("Nueva");
        btnModificar = new JButton("Modificar");
        btnBorrar = new JButton("Borrar");
        
        jScrollPane1 = new JScrollPane();
        
        modelLocalidad = new DefaultTableModel(null, nombreColumna);
        tablaLocalidades = new JTable(modelLocalidad);
        

        

        
        
        

        GroupLayout jPanel1Layout = new GroupLayout(contenedorBtnAcepCan);
        contenedorBtnAcepCan.setLayout(jPanel1Layout);
        
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(btnCancelar)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAceptar)
                .addGap(44, 44, 44))
        );
        
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnAceptar))
                .addGap(0, 2, Short.MAX_VALUE))
        );



        GroupLayout jPanel2Layout = new GroupLayout(contenedorBtnABM);
        contenedorBtnABM.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(100, Short.MAX_VALUE)
                .addComponent(btnBorrar)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnModificar)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnNueva)
                .addGap(86, 86, 86))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNueva)
                    .addComponent(btnModificar)
                    .addComponent(btnBorrar)))
        );

        
        jScrollPane1.setViewportView(tablaLocalidades);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(contenedorBtnABM, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(contenedorBtnAcepCan, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contenedorBtnABM, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(contenedorBtnAcepCan, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        
        this.setVisible(true);
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
    
    

}
