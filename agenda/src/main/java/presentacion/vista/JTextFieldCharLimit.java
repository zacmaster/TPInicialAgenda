package presentacion.vista;


import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class JTextFieldCharLimit extends PlainDocument{
	private static final long serialVersionUID = 1L;
	private int limite;
	
	public JTextFieldCharLimit(int limitacion) {
		super();
		this.limite = limitacion;
	}
	public void insertString(int offset, String str, AttributeSet set) throws BadLocationException{
		if(str == null) {
			return;
		}else if((getLength() + str.length()) <= limite) {
			super.insertString(offset, str, set);
		}
	}
}
