package generated;


import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class HighlightListener implements DocumentListener {

	JTextField comp = null; 
	Border defaultBorder;
	Border highlightBorder = BorderFactory.createLineBorder(java.awt.Color.RED);
	
	 public HighlightListener(JTextField jtc) {
	        comp = jtc;
	        defaultBorder = comp.getBorder();
	        comp.getDocument().addDocumentListener(this);
	        this.maybeHighlight();
	    }
	
	
	@Override
	public void insertUpdate(DocumentEvent e) {
		 maybeHighlight();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		 maybeHighlight();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		 maybeHighlight();
	}
	
	void maybeHighlight() { 
		 if (comp.getText().trim().length()==0)
			 comp.setBorder(highlightBorder);
		 else
			 comp.setBorder(defaultBorder);
	}
}
