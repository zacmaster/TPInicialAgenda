package dto;

public class LocalidadDTO {
	private int idLocalidad;
	private String nombreLocalidad;
	
	public LocalidadDTO(int idLocalidad, String Localidad){
		this.idLocalidad = idLocalidad;
		this.nombreLocalidad = Localidad;
	}

	public int getIdLocalidad() {
		return idLocalidad;
	}

	public void setIdLocalidad(int idLocalidad) {
		this.idLocalidad = idLocalidad;
	}

	public String getNombreLocalidad() {
		return nombreLocalidad;
	}

	public void setLocalidad(String localidad) {
		nombreLocalidad = localidad;
	}


}
