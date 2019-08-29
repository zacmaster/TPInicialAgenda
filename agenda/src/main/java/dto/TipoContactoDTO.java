package dto;

public class TipoContactoDTO {
	private int idTipoContacto;
	private String Tipo_Contacto;
	
	public TipoContactoDTO(int idTipoContacto, String Tipo_Contacto) {
		this.idTipoContacto = idTipoContacto;
		this.Tipo_Contacto = Tipo_Contacto;
	}
	
	public int getIdTipoContacto() {
		return idTipoContacto;
	}

	public void setIdTipoContacto(int idTipoContacto) {
		this.idTipoContacto = idTipoContacto;
	}

	public String getTipoContacto() {
		return Tipo_Contacto;
	}

	public void setTipo_Contacto(String tipo_Contacto) {
		Tipo_Contacto = tipo_Contacto;
	}
}
