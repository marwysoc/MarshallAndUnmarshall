package generated;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JTextField;
import javax.xml.XMLConstants;
import javax.xml.bind.ValidationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class SchemaValidator {
    public boolean validate(InputStream xml, JTextField txt) throws ValidationException{
    	SchemaFactory factory = 
    		       SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    		Source schemaFile = new StreamSource(new File("badaniePodmiotoweOgolne.xsd"));
    		Schema schema;
    		try {
    		        schema = factory.newSchema(schemaFile);
    		} catch (Exception e) {
    		         throw new ValidationException(e);
    		}
    		Validator validator = schema.newValidator();
    	       Source source = new StreamSource(xml);
    	       
    	       try {
    	    	   validator.validate(source);
    	    	   System.out.println("XML Validation: OK");
    	    	   txt.setText("Walidacja przebieg³a pomyœlnie");
    	    	   txt.setForeground(Color.GREEN);
    	    	  } catch (SAXException ex) {
    	    	   System.out.println("XML is not valid "+ex.getMessage());
    	    	   txt.setText("B³¹d walidacji");
    	    	   txt.setForeground(Color.RED);
    	    	   return false;
    	    	  } catch (IOException e) {
    	    	   throw new ValidationException(e);
    	    	  }
    	    	   
    	    	  return true;
    }

}
