package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.PersonaDTO;
import dto.TipoContactoDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.TipoContactoDAO;

public class TipoContactoDAOSQL implements TipoContactoDAO {
	
	private static final String insert = "INSERT INTO tipo_contacto(idTipoContacto,Tipo_Contacto) VALUES(?, ?)";
	private static final String delete = "DELETE FROM tipo_contacto WHERE idTipoContacto = ?";
	private static final String readall = "SELECT * FROM tipo_contacto";
	
	public boolean insert(TipoContactoDTO tipo_contacto) {
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, tipo_contacto.getIdTipoContacto());
			statement.setString(2, tipo_contacto.getTipoContacto());
			if(statement.executeUpdate() > 0) //Si se ejecut� devuelvo true
				return true;
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	public boolean delete(TipoContactoDTO tipo_contacto_a_eliminar) {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setString(1, Integer.toString(tipo_contacto_a_eliminar.getIdTipoContacto()));
			chequeoUpdate = statement.executeUpdate();
			if(chequeoUpdate > 0) //Si se ejecutó devuelvo true
				return true;
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<TipoContactoDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<TipoContactoDTO> tipo_contacto = new ArrayList<TipoContactoDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				tipo_contacto.add(new TipoContactoDTO(resultSet.getInt("idTipoContacto"), resultSet.getString("Tipo_Contacto")));
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return tipo_contacto;
	}

}
