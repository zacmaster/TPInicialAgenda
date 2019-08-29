package persistencia.dao.mysql;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import persistencia.conexion.Conexion;
import persistencia.conexion.RWProperties;

public class ConfigDAOSQL {
//	private static final String updatePassword = "SET PASSWORD FOR 'tom'@'localhost' = PASSWORD('foobar');";
	private static final String updatePassword = "SET PASSWORD = ? ";
	
	public void updatePasswordDAOSQL() {
		@SuppressWarnings("unused")
		ResultSet resultSet;
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		
		try {
			statement  = conexion.getSQLConexion().prepareStatement(updatePassword);
			statement.setString(1,RWProperties.getValue("dbPassword"));
			resultSet = statement.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
