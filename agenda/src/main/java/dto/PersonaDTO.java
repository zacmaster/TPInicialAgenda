package dto;

public class PersonaDTO {
	private int idPersona;
	private String nombre;
	private String telefono;
	private String calle;
	private int altura;
	private int piso;
	private String depto;
	private String localidad;
	private String correo;
	private String fecha_nacimiento;
	private String tipo_contacto;

	public PersonaDTO(int idPersona, String nombre, String telefono, String calle, int altura, int piso, String depto, String localidad, String correo, String fecha_nacimiento, String tipo_contacto) {
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.telefono = telefono;
		this.calle = calle;
		this.altura = altura;
		this.piso = piso;
		this.depto = depto;
		this.localidad = localidad;
		this.correo = correo;
		this.fecha_nacimiento = fecha_nacimiento;
		this.tipo_contacto = tipo_contacto;
	}
	
	public int getIdPersona() {
		return this.idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getPiso() {
		return piso;
	}

	public void setPiso(int piso) {
		this.piso = piso;
	}

	public String getDepto() {
		return depto;
	}

	public void setDepto(String depto) {
		this.depto = depto;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getFechaNacimiento() {
		return fecha_nacimiento;
	}

	public void setFechaNacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public String getTipoContacto() {
		return tipo_contacto;
	}

	public void setTipoContacto(String tipo_contacto) {
		this.tipo_contacto = tipo_contacto;
	}
}
