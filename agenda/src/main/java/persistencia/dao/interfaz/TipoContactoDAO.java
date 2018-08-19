package persistencia.dao.interfaz;

import java.util.List;

import dto.TipoContactoDTO;

public interface TipoContactoDAO {
	
	public boolean insert(TipoContactoDTO tipo_contacto);

	public boolean delete(TipoContactoDTO tipo_contacto_a_eliminar);
	
	public List<TipoContactoDTO> readAll();
}
