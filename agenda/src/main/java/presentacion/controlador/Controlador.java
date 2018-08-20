package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import modelo.Agenda;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.DialogoNuevaLocalidad;
import presentacion.vista.DialogoNuevoTipoContacto;
import presentacion.vista.VentanaLocalidad;
import presentacion.vista.VentanaPersona;
import presentacion.vista.VentanaTipoContacto;
import presentacion.vista.Vista;
import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.TipoContactoDTO;

public class Controlador implements ActionListener {
		private Vista vista;
		private List<PersonaDTO> personas_en_tabla;
		private List<LocalidadDTO> localidades_en_tabla;
		private List<TipoContactoDTO> tiposContacto_en_tabla;
		
		
		private VentanaPersona ventanaPersona; 
		private VentanaLocalidad ventanaLocalidad;
		private VentanaTipoContacto ventanaTipoContacto;
		private DialogoNuevaLocalidad dialogoNuevaLocalidad;
		private DialogoNuevoTipoContacto dialogoNuevoTipoContacto;
		
		private Agenda agenda;
		
		public Controlador(Vista vista, Agenda agenda) {
			this.vista = vista;
			this.vista.getBtnAgregar().addActionListener(this);
			this.vista.getBtnBorrar().addActionListener(this);
			this.vista.getBtnReporte().addActionListener(this);
			this.agenda = agenda;
			this.personas_en_tabla = null;
			this.localidades_en_tabla = null;
		}
		
		public void inicializar() {
			this.llenarTabla();
			this.vista.show();
		}
		
		private void llenarTabla() {
			this.vista.getModelPersonas().setRowCount(0); //Para vaciar la tabla
			this.vista.getModelPersonas().setColumnCount(0);
			this.vista.getModelPersonas().setColumnIdentifiers(this.vista.getNombreColumnas());
			
			this.personas_en_tabla = agenda.obtenerPersonas();
			for (int i = 0; i < this.personas_en_tabla.size(); i ++) {
				Object[] fila = {this.personas_en_tabla.get(i).getNombre(), this.personas_en_tabla.get(i).getTelefono(), this.personas_en_tabla.get(i)};
				this.vista.getModelPersonas().addRow(fila);
			}			
		}
		
		public void actionPerformed(ActionEvent e)  {
			if(e.getSource() == this.vista.getBtnAgregar()) {
				this.ventanaPersona = new VentanaPersona(this);
				this.llenarComboBoxTiposContacto();
				this.llenarComboBoxLocalidades();
			}
			else if(e.getSource() == this.vista.getBtnBorrar()) {
				int[] filas_seleccionadas = this.vista.getTablaPersonas().getSelectedRows();
				for (int fila:filas_seleccionadas) {
					this.agenda.borrarPersona(this.personas_en_tabla.get(fila));
				}
				this.llenarTabla();
			}
			else if(e.getSource() == this.vista.getBtnReporte()) {				
				ReporteAgenda reporte = new ReporteAgenda(agenda.obtenerPersonas());
				reporte.mostrar();				
			}
			
			
			
			
			else if(this.ventanaPersona != null && e.getSource() == this.ventanaPersona.getBtnABMLocalidad()) {
				this.ventanaLocalidad = new VentanaLocalidad(this);
				this.llenarTablaLocalidades();
			}
			
			else if(this.ventanaPersona != null && e.getSource() == this.ventanaPersona.getABMTipoContacto()) {
				this.ventanaTipoContacto = new VentanaTipoContacto(this);
				this.llenarTablaTiposContacto();
			}
			
			
			
			
			
			else if(this.ventanaTipoContacto != null && e.getSource() == this.ventanaTipoContacto.getBtnBorrar()) {
				int[] filas_seleccionadas = this.ventanaTipoContacto.getTablaTiposContacto().getSelectedRows();
				for (int fila:filas_seleccionadas) {
					this.agenda.borrarTipoContacto(this.tiposContacto_en_tabla.get(fila));
				}
				this.llenarTablaTiposContacto();
				this.llenarComboBoxTiposContacto();
			}
			
			
			else if(this.ventanaTipoContacto != null && e.getSource() == this.ventanaTipoContacto.getBtnNuevo()) {
				this.dialogoNuevoTipoContacto = new  DialogoNuevoTipoContacto(this);
			}
			
			else if(this.dialogoNuevoTipoContacto != null && e.getSource() == this.dialogoNuevoTipoContacto.getBtnAgregar()) {
				this.agenda.agregarTipoContacto(new TipoContactoDTO(0, this.dialogoNuevoTipoContacto.getInput().getText()));
				this.llenarTablaTiposContacto();
				this.llenarComboBoxTiposContacto();
				this.dialogoNuevoTipoContacto.dispose();
			}
			
			
			
			
			else if(e.getSource() == this.ventanaPersona.getBtnAgregarPersona()) {
				PersonaDTO nuevaPersona = new PersonaDTO(	0,
															this.ventanaPersona.getTxtNombre().getText(),
															ventanaPersona.getTxtTelefono().getText(),
															ventanaPersona.getTxtCalle().getText(),
															ventanaPersona.getTxtAltura().getText(),
															ventanaPersona.getTxtPiso().getText(), 
															ventanaPersona.getTxtDepto().getText(),
															ventanaPersona.getTxtLocalidad(),
															ventanaPersona.getTxtEmail().getText(),
															ventanaPersona.getFechaNac(),
															ventanaPersona.getTipoContacto()
															);
				this.agenda.agregarPersona(nuevaPersona);
				this.llenarTabla();
				this.ventanaPersona.dispose();
			}
			
			else if(this.ventanaLocalidad != null && e.getSource() == this.ventanaLocalidad.getBtnBorrar()) {
				int[] filas_seleccionadas = this.ventanaLocalidad.getTablaLocalidades().getSelectedRows();
				for (int fila:filas_seleccionadas) {
					this.agenda.borrarLocalidad(this.localidades_en_tabla.get(fila));
				}
				this.llenarTablaLocalidades();
				this.llenarComboBoxTiposContacto();
			}
			
			else if(this.ventanaLocalidad != null && e.getSource() ==  this.ventanaLocalidad.getBtnNueva()) {
				this.dialogoNuevaLocalidad = new DialogoNuevaLocalidad(this);
			}
			
			else if(this.dialogoNuevaLocalidad != null && e.getSource() == this.dialogoNuevaLocalidad.getBtnAgregar()) {
				
				this.agenda.agregarLocalidad(new LocalidadDTO(0, this.dialogoNuevaLocalidad.getInput().getText()));
				this.llenarTablaLocalidades();
				this.llenarComboBoxLocalidades();
				this.dialogoNuevaLocalidad.dispose();
			}
			
		}
		
		private void llenarTablaLocalidades() {
			
			this.ventanaLocalidad.getModelLocalidad().setRowCount(0);
			this.ventanaLocalidad.getModelLocalidad().setColumnCount(0);
			this.ventanaLocalidad.getModelLocalidad().setColumnIdentifiers(this.ventanaLocalidad.getNombreColumnas());
			
			this.localidades_en_tabla = agenda.obtenerLocalidades();
			for (int i = 0; i < this.localidades_en_tabla.size(); i++) {
				Object[] fila =  { this.localidades_en_tabla.get(i).getNombreLocalidad() };
				this.ventanaLocalidad.getModelLocalidad().addRow(fila);
			}
		}
		
		private void llenarTablaTiposContacto() {
			
			this.ventanaTipoContacto.getModelTipoContacto().setRowCount(0);
			this.ventanaTipoContacto.getModelTipoContacto().setColumnCount(0);
			this.ventanaTipoContacto.getModelTipoContacto().setColumnIdentifiers(this.ventanaTipoContacto.getNombreColumnas());
		
			this.tiposContacto_en_tabla = agenda.obtenerTiposContacto();
			for (int i = 0; i < this.tiposContacto_en_tabla.size(); i++) {
				Object[] fila = { this.tiposContacto_en_tabla.get(i).getTipoContacto()};
				this.ventanaTipoContacto.getModelTipoContacto().addRow(fila);
			}
		}
		private void llenarComboBoxTiposContacto() {
			this.ventanaPersona.getComboTipoContactos().setModel(new DefaultComboBoxModel<>());
			for (TipoContactoDTO tiposDeContactoDTO : agenda.obtenerTiposContacto()) {
				this.ventanaPersona.getComboTipoContactos().addItem(tiposDeContactoDTO.getTipoContacto());
			}
		}
		
		
		private void llenarComboBoxLocalidades() {
			this.ventanaPersona.getComboLocalidades().setModel(new DefaultComboBoxModel<>());
			for(LocalidadDTO localidadDTO: agenda.obtenerLocalidades()){
				this.ventanaPersona.getComboLocalidades().addItem(localidadDTO.getNombreLocalidad());
			}
		}
		
		

}
