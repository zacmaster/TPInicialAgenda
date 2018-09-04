package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import modelo.Agenda;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.DialogoNuevaLocalidad;
import presentacion.vista.DialogoNuevoTipoContacto;
import presentacion.vista.VentanaConfiguracionDB;
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
		private VentanaConfiguracionDB ventanaConfiguracionDB;
		private VentanaTipoContacto ventanaTipoContacto;
		private DialogoNuevaLocalidad dialogoNuevaLocalidad;
		private DialogoNuevoTipoContacto dialogoNuevoTipoContacto;
		
		
		private PersonaDTO personaSeleccionada;
		private LocalidadDTO localidadSeleccionada;
		private TipoContactoDTO tipoContactoDTOSeleccionado;
		
		private boolean modoEdicionContacto = false;
		private boolean modoEdicionLocalidad = false;
		private boolean modoEdicionTipoContacto = false;
		
		
		private Agenda agenda;
		
		public Controlador(Vista vista, Agenda agenda) {
			this.vista = vista;
			this.vista.getBtnAgregar().addActionListener(this);
			this.vista.getBtnEditar().addActionListener(this);
			this.vista.getBtnBorrar().addActionListener(this);
			this.vista.getBtnReporte().addActionListener(this);
			this.vista.getBtnConfig().addActionListener(this);
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
				Object[] fila = {	this.personas_en_tabla.get(i).getNombre(),
									this.personas_en_tabla.get(i).getApellido(),
									this.personas_en_tabla.get(i).getTelefono(),
									this.personas_en_tabla.get(i).getCorreo(),
									this.personas_en_tabla.get(i).getTipoContacto(),
									this.personas_en_tabla.get(i).getFechaNacimiento(),
									this.personas_en_tabla.get(i).getLocalidad(),
									this.personas_en_tabla.get(i).getCalle(),
									this.personas_en_tabla.get(i).getAltura(),
									this.personas_en_tabla.get(i).getPiso(),
									this.personas_en_tabla.get(i).getDepto()};
				this.vista.getModelPersonas().addRow(fila);
			}			
		}
		
		public void actionPerformed(ActionEvent e)  {
			if(e.getSource() == this.vista.getBtnConfig()) {
				this.ventanaConfiguracionDB = new VentanaConfiguracionDB(this);
				
				
			}
			else if(e.getSource() == this.vista.getBtnAgregar()) {
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
			else if (e.getSource() == this.vista.getBtnEditar()) {
				if(this.vista.getTablaPersonas().getSelectedRow() > -1) {
					this.modoEdicionContacto = true;
					this.ventanaPersona = new VentanaPersona(this);
					this.llenarComboBoxTiposContacto();
					this.llenarComboBoxLocalidades();
					llenarCamposPersona();
				}
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
				if(this.dialogoNuevoTipoContacto.camposInvalidos().size() == 0) {
					TipoContactoDTO nuevoTipoContacto;
					if(this.modoEdicionTipoContacto) {
						nuevoTipoContacto = new TipoContactoDTO(	tipoContactoDTOSeleccionado.getIdTipoContacto(),
								dialogoNuevoTipoContacto.getInput().getText());
						this.agenda.updateTipoContacto(nuevoTipoContacto);
						nuevoTipoContacto = null;
						modoEdicionTipoContacto = false;
					}
					else {
						this.agenda.agregarTipoContacto(new TipoContactoDTO(0, this.dialogoNuevoTipoContacto.getInput().getText()));
						
					}
					this.llenarTablaTiposContacto();
					this.llenarTabla();
					this.llenarComboBoxTiposContacto();
					this.dialogoNuevoTipoContacto.dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, this.dialogoNuevoTipoContacto.mensajeVerificarCampos());
				}
			}
			
			
			
			
			else if(e.getSource() == this.ventanaPersona.getBtnAgregarPersona()) {
				if(ventanaPersona.camposInvalidos().size() == 0) {
					PersonaDTO nuevaPersona;
					Integer piso = (ventanaPersona.getTxtPiso().getText().equals("")) ? null : Integer.parseInt(ventanaPersona.getTxtPiso().getText());
					if(this.modoEdicionContacto) {
						
						nuevaPersona = new PersonaDTO(	personaSeleccionada.getIdPersona(),
														ventanaPersona.getTxtNombre().getText(),
														ventanaPersona.getTxtApellido().getText(),
														ventanaPersona.getTxtTelefono().getText(),
														ventanaPersona.getTxtCalle().getText(),
														Integer.parseInt(ventanaPersona.getTxtAltura().getText()),
														piso,
														ventanaPersona.getTxtDepto().getText(),
														ventanaPersona.getTxtLocalidad(),
														ventanaPersona.getTxtEmail().getText(),
														ventanaPersona.getFechaNac(),
														ventanaPersona.getTipoContacto());
						
						this.agenda.updatePersona(nuevaPersona);
						personaSeleccionada = null;
					}
					else {
						nuevaPersona = new PersonaDTO(	0,
								this.ventanaPersona.getTxtNombre().getText(),
								ventanaPersona.getTxtApellido().getText(),
								ventanaPersona.getTxtTelefono().getText(),
								ventanaPersona.getTxtCalle().getText(),
								Integer.parseInt(ventanaPersona.getTxtAltura().getText()),
								piso,
								ventanaPersona.getTxtDepto().getText(),
								ventanaPersona.getTxtLocalidad(),
								ventanaPersona.getTxtEmail().getText(),
								ventanaPersona.getFechaNac(),
								ventanaPersona.getTipoContacto()
								);
						this.agenda.agregarPersona(nuevaPersona);
					}
					this.llenarTabla();
					this.ventanaPersona.dispose();
					this.modoEdicionContacto = false;
				}
				else{
					JOptionPane.showMessageDialog(null, ventanaPersona.mensajeVerificarCampos());
				}
				
			}
			
			else if(this.ventanaLocalidad != null  && e.getSource() == this.ventanaLocalidad.getBtnEditar()) {
				if(this.ventanaLocalidad.getTablaLocalidades().getSelectedRow() > -1) {
					this.modoEdicionLocalidad = true;
					this.dialogoNuevaLocalidad = new DialogoNuevaLocalidad(this);
					llenarCampoLocalidad();
				}
			}
			
			else if(this.ventanaTipoContacto != null  && e.getSource() == this.ventanaTipoContacto.getBtnEditar()) {
				if(this.ventanaTipoContacto.getTablaTiposContacto().getSelectedRow() > -1) {
					this.modoEdicionTipoContacto = true;
					this.dialogoNuevoTipoContacto= new DialogoNuevoTipoContacto(this);
					llenarCampoTipoContacto();
				}
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
				if(this.dialogoNuevaLocalidad.camposInvalidos().size() == 0) {
					LocalidadDTO nuevaLocalidad;
					if(this.modoEdicionLocalidad) {
						nuevaLocalidad = new LocalidadDTO(	localidadSeleccionada.getIdLocalidad(),
								dialogoNuevaLocalidad.getInput().getText());
						this.agenda.updateLocalidad(nuevaLocalidad);
						localidadSeleccionada = null;
						this.modoEdicionLocalidad = false;
					}
					else {
						this.agenda.agregarLocalidad(new LocalidadDTO(0, this.dialogoNuevaLocalidad.getInput().getText()));
					}
					this.llenarTablaLocalidades();
					this.llenarTabla();
					this.llenarComboBoxLocalidades();
					this.dialogoNuevaLocalidad.dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, this.dialogoNuevaLocalidad.mensajeVerificarCampos());
				}
			}
			
			
			
			
			
		}
		
		private void llenarCamposPersona() {
			int fila = this.vista.getTablaPersonas().getSelectedRow();
			PersonaDTO personaDTO  = this.personas_en_tabla.get(fila);
			
			this.ventanaPersona.getTxtNombre().setText(personaDTO.getNombre());
			this.ventanaPersona.getTxtApellido().setText(personaDTO.getApellido());
			this.ventanaPersona.getTxtTelefono().setText(personaDTO.getTelefono());
			this.ventanaPersona.getTxtEmail().setText(personaDTO.getCorreo());
			this.ventanaPersona.getComboTipoContactos().setSelectedItem(new String(personaDTO.getTipoContacto()));
			
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date dateTemp = sdf.parse(personaDTO.getFechaNacimiento());
				this.ventanaPersona.getDateChooser().setDate(dateTemp);
			} catch (ParseException e) { e.printStackTrace(); }
			
			this.ventanaPersona.getComboLocalidades().setSelectedItem(new String(personaDTO.getLocalidad()));
			this.ventanaPersona.getTxtCalle().setText(personaDTO.getCalle());
			this.ventanaPersona.getTxtAltura().setText(Integer.toString(personaDTO.getAltura()));
			this.ventanaPersona.getTxtPiso().setText(Integer.toString(personaDTO.getPiso()));
			this.ventanaPersona.getTxtDepto().setText(personaDTO.getDepto());
			
			this.personaSeleccionada = personaDTO;
			
			
		}
		
		
		
		
		
		private void llenarCampoLocalidad() {
			
			int fila = this.ventanaLocalidad.getTablaLocalidades().getSelectedRow();
			LocalidadDTO localidadDTO = this.localidades_en_tabla.get(fila);
			this.dialogoNuevaLocalidad.getInput().setText(localidadDTO.getNombreLocalidad());
			this.localidadSeleccionada = localidadDTO;
		}
		private void llenarCampoTipoContacto() {
			
			int fila = this.ventanaTipoContacto.getTablaTiposContacto().getSelectedRow();
			TipoContactoDTO tipoContactoDTO = this.tiposContacto_en_tabla.get(fila);
			this.dialogoNuevoTipoContacto.getInput().setText(tipoContactoDTO.getTipoContacto());
			this.tipoContactoDTOSeleccionado = tipoContactoDTO;
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
