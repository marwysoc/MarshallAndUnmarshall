package generated;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;

import javax.swing.JTextField;

public class MyValidator implements ValidationEventHandler {

	
	
	MyValidator(JTextField jtf){
		jtf.setText("hello");
	}
	
	@Override
	public boolean handleEvent(ValidationEvent event) {
		System.out.println("WALIDACJA " + event.getMessage());
		return false;
	}

}
