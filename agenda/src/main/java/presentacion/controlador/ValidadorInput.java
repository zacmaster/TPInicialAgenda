package presentacion.controlador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;


public class ValidadorInput {
	private Pattern pattern;
	private Matcher matcher;
	

    private static final String EMAIL_PATTERN =	"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
    		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    private static final String ONLY_TEXT_WITH_SPACES_PATTERN = "^[a-zA-Z ]*$";
    private static final String ONLY_TEXT_WITHOUT_SPACES_PATTERN = "^[a-zA-Z]*$";
    private static final String ONLY_NUMBERS = "^[0-9]*$";
    private static final String ONLY_NUMBERS_AND_LETTERS = "[a-z,A-Z,0-9]*";
    
    
    public boolean validarEmail(String str) {
    	if(str != null && ! str.equals("")) {
    		this.pattern = Pattern.compile(EMAIL_PATTERN);
    		matcher = pattern.matcher(str);
    		return matcher.matches();
    	}
    	return false;
    }
    
    
    public boolean validarTelefono(String str) {
    	if(str != null && ! str.equals("")) {
    		this.pattern = Pattern.compile(ONLY_NUMBERS);
    		matcher = pattern.matcher(str);
    		return matcher.matches();
    	}
    	return false;
    }
    public boolean validarTextoConEspacios(String str) {
    	if(str != null && ! str.equals("")) {
    		this.pattern = Pattern.compile(ONLY_TEXT_WITH_SPACES_PATTERN);
    		matcher = pattern.matcher(str);
    		return matcher.matches();
    	}
    	return false;
    }


	public boolean validarTextoSinEspacios(String str) {
		if(str != null && ! str.equals("")) {
    		this.pattern = Pattern.compile(ONLY_TEXT_WITHOUT_SPACES_PATTERN);
    		matcher = pattern.matcher(str);
    		return matcher.matches();
    	}
    	return false;
	}


	public boolean validarNumerico(String str) {
		if(str != null && ! str.equals("")) {
    		this.pattern = Pattern.compile(ONLY_NUMBERS);
    		matcher = pattern.matcher(str);
    		return matcher.matches();
    	}
    	return false;
	}


	public boolean validarAlfaNumerico(String str) {
		if(str != null && ! str.equals("")) {
    		this.pattern = Pattern.compile(ONLY_NUMBERS_AND_LETTERS);
    		matcher = pattern.matcher(str);
    		return matcher.matches();
    	}
    	return false;
	}
	
	public boolean validarNumericoVacio(String str) {
		if(str != null) {
			if(str.equals("")) return true;
    		this.pattern = Pattern.compile(ONLY_NUMBERS);
    		matcher = pattern.matcher(str);
    		return matcher.matches();
    	}
    	return false;
	}


	public boolean validarAlfaNumericoVacio(String str) {
		if(str != null) {
			if(str.equals("")) return true;
    		this.pattern = Pattern.compile(ONLY_NUMBERS_AND_LETTERS);
    		matcher = pattern.matcher(str);
    		return matcher.matches();
    	}
    	return false;
	}
    
}
