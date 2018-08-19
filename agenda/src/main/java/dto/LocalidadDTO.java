package dto;

public class LocalidadDTO {
	private int idLocalidad;
	private String Localidad;
	
	public LocalidadDTO(int idLocalidad, String Localidad){
		this.idLocalidad = idLocalidad;
		this.Localidad = Localidad;
	}

	public int getIdLocalidad() {
		return idLocalidad;
	}

	public void setIdLocalidad(int idLocalidad) {
		this.idLocalidad = idLocalidad;
	}

	public String getLocalidad() {
		return Localidad;
	}

	public void setLocalidad(String localidad) {
		Localidad = localidad;
	}

}
