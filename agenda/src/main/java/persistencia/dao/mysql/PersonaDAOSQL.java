package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.PersonaDAO;
import dto.PersonaDTO;

public class PersonaDAOSQL implements PersonaDAO {
	private static final String insert = "INSERT INTO personas(idpersona,nombre,telefono,calle,altura,piso,departamento,idlocalidad,correo,fecha_nacimiento,idTipoContacto,apellido) VALUES (?,?,?,?,?,?,?,(select idLocalidad from localidad where localidad = ?),?,?,(select idtipocontacto from tipo_contacto where tipo_contacto = ?),?)";
	private static final String delete = "DELETE FROM personas WHERE idPersona = ?";
	private static final String readall = "SELECT * FROM personas p INNER JOIN localidad l ON p.idLocalidad = l.idLocalidad INNER JOIN tipo_contacto t ON p.idTipoContacto = t.idTipoContacto";
	private static final String update = "UPDATE personas SET nombre=?,telefono=?,calle=?,altura=?,piso=?,departamento=?,idlocalidad=(select idLocalidad from localidad where localidad = ?),correo=?,fecha_nacimiento=?,idTipoContacto=(select idtipocontacto from tipo_contacto where tipo_contacto = ?),apellido=? WHERE idPersona = ?";	
	private static final String get = "SELECT * FROM personas p WHERE idPersona = ?";
	
	public PersonaDTO get(int id) {
		ResultSet resultSet;
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		PersonaDTO personaDTO = null;
		try {
			statement = conexion.getSQLConexion().prepareStatement(get);
			statement.setInt(1,id);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				personaDTO = new PersonaDTO(	resultSet.getInt("idPersona"),
												resultSet.getString("Nombre"),
												resultSet.getString("Apellido"),
												resultSet.getString("Telefono"),
												resultSet.getString("Calle"),
												resultSet.getInt("Altura"),
												resultSet.getInt("Piso"),
												resultSet.getString("Departamento"),
												resultSet.getString("Localidad"), 
												resultSet.getString("Correo"),
												resultSet.getString("Fecha_Nacimiento"),
												resultSet.getString("Tipo_Contacto"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return personaDTO;
	}
	
	
	public boolean insert(PersonaDTO persona) {
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, persona.getIdPersona());
			statement.setString(2, persona.getNombre());
			statement.setString(3, persona.getTelefono());
			statement.setString(4, persona.getCalle());
			statement.setInt(5, persona.getAltura());
			if(persona.getPiso() == null) {
				statement.setNull(6, java.sql.Types.INTEGER);
			}
			else {
				statement.setInt(6, persona.getPiso());
			}
			statement.setString(7, persona.getDepto());
			statement.setString(8, persona.getLocalidad());
			statement.setString(9, persona.getCorreo());
			statement.setString(10, persona.getFechaNacimiento());
			statement.setString(11, persona.getTipoContacto());
			statement.setString(12, persona.getApellido());
			if(statement.executeUpdate() > 0) //Si se ejecut� devuelvo true
				return true;
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean delete(PersonaDTO persona_a_eliminar) {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setString(1, Integer.toString(persona_a_eliminar.getIdPersona()));
			chequeoUpdate = statement.executeUpdate();
			if(chequeoUpdate > 0) //Si se ejecutó devuelvo true
				return true;
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<PersonaDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<PersonaDTO> personas = new ArrayList<PersonaDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				personas.add(new PersonaDTO(	resultSet.getInt("idPersona"),
												resultSet.getString("Nombre"),
												resultSet.getString("Apellido"),
												resultSet.getString("Telefono"),
												resultSet.getString("Calle"),
												resultSet.getInt("Altura"),
												resultSet.getInt("Piso"),
												resultSet.getString("Departamento"),
												resultSet.getString("Localidad"), 
												resultSet.getString("Correo"),
												resultSet.getString("Fecha_Nacimiento"),
												resultSet.getString("Tipo_Contacto")));
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		personas.size();
		return personas;
	}
	
	public boolean update(PersonaDTO persona) {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(update);
			statement.setString(1, persona.getNombre());
			statement.setString(2, persona.getTelefono());
			statement.setString(3, persona.getCalle());
			statement.setInt(4, persona.getAltura());
			if(persona.getPiso() == null) {
				statement.setNull(5, java.sql.Types.INTEGER);
			}
			else {
				statement.setInt(5, persona.getPiso());
			}
			statement.setString(6, persona.getDepto());
			statement.setString(7, persona.getLocalidad());
			statement.setString(8, persona.getCorreo());
			statement.setString(9, persona.getFechaNacimiento());
			statement.setString(10, persona.getTipoContacto());
			statement.setString(11, persona.getApellido());
			statement.setInt(12, persona.getIdPersona());
			
			chequeoUpdate = statement.executeUpdate();
			if(chequeoUpdate > 0) //Si se ejecutó devuelvo true
				return true;
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
