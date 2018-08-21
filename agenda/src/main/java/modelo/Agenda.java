package modelo;

import java.util.List;

import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.TipoContactoDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.LocalidadDAO;
import persistencia.dao.interfaz.PersonaDAO;
import persistencia.dao.interfaz.TipoContactoDAO;


public class Agenda {
	private PersonaDAO persona;	
	private LocalidadDAO localidad;
	private TipoContactoDAO tipo_contacto;
	
	public Agenda(DAOAbstractFactory metodo_persistencia) {
		this.persona = metodo_persistencia.createPersonaDAO();
		this.localidad = metodo_persistencia.createLocalidadDAO();
		this.tipo_contacto = metodo_persistencia.createTipoContactoDAO();
	}
	
	public void agregarPersona(PersonaDTO nuevaPersona) {
		this.persona.insert(nuevaPersona);
	}

	public void borrarPersona(PersonaDTO persona_a_eliminar) {
		
		this.persona.delete(persona_a_eliminar);
	}
	
	public List<PersonaDTO> obtenerPersonas() {
		return this.persona.readAll();		
	}
	
	public PersonaDTO getPersona(int idPersona) {
		return this.persona.get(idPersona);
	}
	
	public void updatePersona(PersonaDTO personaDTO) {
		this.persona.update(personaDTO);
	}
	
	
	public void agregarLocalidad(LocalidadDTO nuevaLocalidad) {
		this.localidad.insert(nuevaLocalidad);
	}
	
	public void borrarLocalidad(LocalidadDTO localidad_a_eliminar){
		this.localidad.delete(localidad_a_eliminar);
	}
	
	public List<LocalidadDTO> obtenerLocalidades() {
		return this.localidad.readAll();
	}
	
	public void agregarTipoContacto(TipoContactoDTO nuevoTipoContacto) {
		this.tipo_contacto.insert(nuevoTipoContacto);
	}
	
	public void borrarTipoContacto(TipoContactoDTO tipo_contacto_a_eliminar){
		this.tipo_contacto.delete(tipo_contacto_a_eliminar);
	}
	
	public List<TipoContactoDTO> obtenerTiposContacto() {
		return this.tipo_contacto.readAll();
	}
}
