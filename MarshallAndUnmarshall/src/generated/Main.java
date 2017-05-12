package generated;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;
import org.jdesktop.swingx.table.DatePickerCellEditor;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.configuration.DefaultConfigurationBuilder;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.xmlgraphics.util.MimeConstants;

import javax.xml.transform.Transformer;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;

import generated.Leki.Lek;
import javax.swing.LayoutStyle.ComponentPlacement;


public class Main extends JFrame {
		
	/**
	 * autor: MARTA WYSOCKA, 144261
	 * projekt: Dokumenty Cyforwe w Medycynie
	 */
	
	private static final long serialVersionUID = 1L;
	DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");
	Date newDate = new Date();
	Calendar calendar = Calendar.getInstance();
	String filename = " ";
	File fileOut, newFileOut;
	Badanie badanie;
	boolean isValid;
	
	public static boolean XMLValidation (InputStream xml, String xsd) throws SAXException, IOException
	{
	    try
	    {
	        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	        Schema schema = factory.newSchema(new File(xsd));
	        Validator validator = schema.newValidator();
	        validator.validate(new StreamSource(xml));
	        return true;
	    }
	    catch(Exception ex)
	    {
	        return false;
	    }
	}
	
	public boolean validate(InputStream xml) throws ValidationException{
    	SchemaFactory factory = 
    		       SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    		//URL resource = this.getClass().getClassLoader().getResource(XSD_FILE);
    		Source schemaFile = new StreamSource(new File("badaniePodmiotoweOgolne.xsd"));
    		Schema schema;
    		try {
    		        schema = factory.newSchema(schemaFile);
    			//schema=factory.newSchema(new File(XSD_FILE));
    		} catch (Exception e) {
    		         throw new ValidationException(e);
    		}
    		Validator validator = schema.newValidator();
    	       Source source = new StreamSource(xml);
    	       
    	       try {
    	    	   validator.validate(source);
    	    	   System.out.println("XML Validation: OK");
    	    	  } catch (SAXException ex) {
    	    	   System.out.println("XML is not valid "+ex.getMessage());
    	    	   return false;
    	    	  } catch (IOException e) {
    	    	   throw new ValidationException(e);
    	    	  }
    	    	   
    	    	  return true;
   }
	
	
	
	public Main() throws JAXBException {
			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(800,650);
		setTitle("Badanie Podmiotowe Ogólne - autor: Marta Wysocka, 144261");
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.light"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Informacje o oddziale", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 11, 764, 70);
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panel_1.add(panel);
		panel.setLayout(null);
		JLabel lblOddzia = new JLabel("Oddzia\u0142");
		lblOddzia.setBounds(98, 8, 51, 14);
		panel.add(lblOddzia);
		
			
		textField = new JTextField();
		textField.setBounds(146, 5, 86, 20);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!(Character.isDigit(c) || Character.isLetter(c) || c==KeyEvent.VK_SPACE || c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE)){
					getToolkit().beep();
					e.consume();
				}
			}
		});
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNrKsG = new JLabel("Nr Ksi\u0119gi G\u0142\u00F3wnej");
		lblNrKsG.setBounds(242, 8, 103, 14);
		panel.add(lblNrKsG);
		
		textField_1 = new JTextField();
		textField_1.setBounds(352, 5, 86, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNrKsigiOddziau = new JLabel("Nr Ksi\u0119gi Oddzia\u0142u");
		lblNrKsigiOddziau.setBounds(443, 8, 103, 14);
		panel.add(lblNrKsigiOddziau);
		
		textField_2 = new JTextField();
		textField_2.setBounds(554, 5, 96, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Informacje o pacjencie", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(11, 92, 763, 106);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 21, 747, 74);
		panel_3.add(panel_2);
		
		JLabel lblPesel = new JLabel("Pesel");
		lblPesel.setBounds(10, 14, 55, 14);
		
		textField_3 = new JTextField();
		textField_3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!(Character.isDigit(c) || c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE)){
					getToolkit().beep();
					e.consume();
					txtAsaaa.setText("W polu pesel mog¹ znajdowaæ siê tylko cyfry!");
					txtAsaaa.setForeground(Color.RED);
				}
				if (textField_3.getText().length()>=11) {
		            getToolkit().beep();
		            e.consume();
		            txtAsaaa.setText("Pesel sk³ada siê z 11 cyfr");
		            txtAsaaa.setForeground(Color.RED);
		        }
			}
		});
		textField_3.setBounds(75, 11, 134, 20);
		textField_3.setColumns(10);
		
		JLabel lblNazwisko = new JLabel("Nazwisko");
		lblNazwisko.setBounds(10, 46, 74, 14);
		
		textField_4 = new JTextField();
		textField_4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!(Character.isLetter(c) || c==KeyEvent.VK_SPACE ||c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE)){
					getToolkit().beep();
					e.consume();
				}
			}
		});
		textField_4.setBounds(75, 43, 176, 20);
		textField_4.setColumns(10);
		
		JLabel lblImiona = new JLabel("Imiona");
		lblImiona.setBounds(273, 46, 55, 14);
		
		textField_5 = new JTextField();
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!(Character.isLetter(c) || c==KeyEvent.VK_SPACE || c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE)){
					getToolkit().beep();
					e.consume();
				}
			}
		});
		textField_5.setBounds(319, 43, 218, 20);
		textField_5.setColumns(10);
		
		JLabel lblPe = new JLabel("P\u0142e\u0107");
		lblPe.setBounds(547, 46, 43, 14);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(600, 43, 55, 20);
		comboBox.addItem(" ");
		comboBox.addItem("M");
		comboBox.addItem("K");
		
		panel_2.setLayout(null);
		panel_2.add(lblNazwisko);
		panel_2.add(lblPesel);
		panel_2.add(textField_4);
		panel_2.add(lblImiona);
		panel_2.add(textField_5);
		panel_2.add(lblPe);
		panel_2.add(comboBox);
		panel_2.add(textField_3);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 199, 764, 322);
		contentPane.add(tabbedPane);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Wywiad wstêpny", null, panel_4, null);
		panel_4.setLayout(null);
		
		JLabel lblGwneSkargi = new JLabel("G\u0142\u00F3wne skargi");
		lblGwneSkargi.setBounds(10, 11, 89, 14);
		panel_4.add(lblGwneSkargi);
		
		textField_6 = new JTextField();
		textField_6.setBounds(161, 8, 588, 74);
		panel_4.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblDotychczasoweChoroby = new JLabel("Dotychczasowy przebieg");
		lblDotychczasoweChoroby.setBounds(10, 99, 155, 14);
		panel_4.add(lblDotychczasoweChoroby);
		
		textField_7 = new JTextField();
		textField_7.setBounds(161, 98, 588, 74);
		panel_4.add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblPrzebyteChoroby = new JLabel("Przebyte choroby");
		lblPrzebyteChoroby.setBounds(10, 194, 120, 14);
		panel_4.add(lblPrzebyteChoroby);
		
		textField_8 = new JTextField();
		textField_8.setBounds(161, 183, 588, 74);
		panel_4.add(textField_8);
		textField_8.setColumns(10);
				
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("Leki", null, panel_5, null);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnDodajWiersz = new JButton("Dodaj wiersz");
		btnDodajWiersz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] row = { null, null, null, null, null};
				DefaultTableModel modelAdd = (DefaultTableModel) table.getModel();
		    	modelAdd.addRow(row);
			}
		});
		
		JButton btnUsuWiersz = new JButton("Usu\u0144 wiersz");
		btnUsuWiersz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel modelDel = (DefaultTableModel) table.getModel();
				  modelDel.removeRow(table.getSelectedRow());  
			}
		});
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_5.createSequentialGroup()
							.addComponent(btnDodajWiersz)
							.addGap(18)
							.addComponent(btnUsuWiersz))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 739, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnDodajWiersz)
						.addComponent(btnUsuWiersz))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		
                
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"Nazwa", "Dawka", "Data (pocz\u0105tek)", "Data (koniec)", "Uwagi"
			}
		));
		
		DateCellRenderer renderer = new DateCellRenderer();
		
		TableColumn dateStart = table.getColumnModel().getColumn(2);
		dateStart.setCellRenderer(renderer);
		dateStart.setCellEditor(new DatePickerCellEditor());
		
		TableColumn dateStop = this.table.getColumnModel().getColumn(3);
		dateStop.setCellRenderer(renderer);
		dateStop.setCellEditor(new DatePickerCellEditor());
		
		DatePickerCellEditor dataPicker = new DatePickerCellEditor();
		DateFormat formats = new SimpleDateFormat("YYYY-MM-DD");
		dataPicker.setFormats(formats);
		dateStop.setCellEditor(dataPicker);
		
		scrollPane.setViewportView(table);
		panel_5.setLayout(gl_panel_5);
		
		JPanel panel_6 = new JPanel();
		tabbedPane.addTab("Dolegliwoœci", null, panel_6, null);
		panel_6.setLayout(null);
		
		JLabel lblUkadOddechowy = new JLabel("Uk\u0142ad oddechowy");
		lblUkadOddechowy.setBounds(10, 11, 109, 14);
		panel_6.add(lblUkadOddechowy);
		
		textField_9 = new JTextField();
		textField_9.setBounds(151, 8, 598, 60);
		panel_6.add(textField_9);
		textField_9.setColumns(10);
		
		JLabel lblUkadKrenia = new JLabel("Uk\u0142ad kr\u0105\u017Cenia");
		lblUkadKrenia.setBounds(10, 93, 109, 14);
		panel_6.add(lblUkadKrenia);
		
		textField_10 = new JTextField();
		textField_10.setBounds(151, 90, 598, 60);
		panel_6.add(textField_10);
		textField_10.setColumns(10);
		
		JLabel lblUkadTrawienny = new JLabel("Uk\u0142ad trawienny");
		lblUkadTrawienny.setBounds(10, 171, 97, 14);
		panel_6.add(lblUkadTrawienny);
		
		textField_11 = new JTextField();
		textField_11.setBounds(151, 168, 598, 60);
		panel_6.add(textField_11);
		textField_11.setColumns(10);
		
		JPanel panel_7 = new JPanel();
		tabbedPane.addTab("Opis badania", null, panel_7, null);
		panel_7.setLayout(null);
		
		JLabel lblOpis = new JLabel("Opis");
		lblOpis.setBounds(10, 11, 64, 27);
		panel_7.add(lblOpis);
		
		textField_12 = new JTextField();
		textField_12.setBounds(84, 14, 665, 257);
		panel_7.add(textField_12);
		textField_12.setColumns(10);
			
		JButton btnWczytajDaneZ = new JButton("Wczytaj dane z pliku XML");
		btnWczytajDaneZ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter("xml files (*.xml)", "xml");
				fileChooser.setFileFilter(xmlfilter);
				
				if (fileChooser.showOpenDialog(Main.this) == JFileChooser.APPROVE_OPTION) {
					  File file = fileChooser.getSelectedFile();	
					  File newFile = new File(fileChooser.getSelectedFile().toString());
					  txtAsaaa.setText("PLIK: " + file.getName() + "  KATALOG: " + fileChooser.getCurrentDirectory());
					  
					  textField.setText("");
					  textField_1.setText("");
					  textField_2.setText("");
					  textField_3.setText("");
					  textField_4.setText("");
					  textField_5.setText("");
					  textField_6.setText("");
					  textField_7.setText("");
					  textField_8.setText("");
					  textField_9.setText("");
					  textField_10.setText("");
					  textField_11.setText("");
					  textField_12.setText("");
					  
					  DefaultTableModel model = (DefaultTableModel) table.getModel();
					  int rows = model.getRowCount(); 
					  for(int i = rows - 1; i >=0; i--)
					  {
					     model.removeRow(i); 
					  }
					  
					  try {
						  	//SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
					        //Schema schema = factory.newSchema(new File("badniePodmiotoweOgolne.xsd"));
						  
					        JAXBContext jaxbcontext = JAXBContext.newInstance(Badanie.class);
						  	Unmarshaller unmarshaller = jaxbcontext.createUnmarshaller();
							InputStream inputStream = new FileInputStream(newFile);
							
							badanie = (Badanie) unmarshaller.unmarshal(newFile);
							
							//XMLValidation(fileChooser.getSelectedFile().toString(), "badniePodmiotoweOgolne.xsd");
							
							//XMLValidation(inputStream, "badniePodmiotoweOgolne.xsd");
							SchemaValidator sv = new SchemaValidator();
							sv.validate(inputStream, txtAsaaa);
							
							//unmarshaller.setSchema(schema);
							//unmarshaller.setEventHandler(new MyValidator(txtAsaaa));
							//txtAsaaa.setText("Walidacja przebieg³a pomyœlnie");
							//txtAsaaa.setForeground(Color.GREEN);							
							
							if (badanie.oddzial!=null){textField.setText(badanie.getOddzial());}
							if (badanie.ksiegaGl!=null){textField_1.setText(badanie.getKsiegaGl());}
							if (badanie.ksiegaOddzialu!=null){textField_2.setText(badanie.getKsiegaOddzialu());}
							
							if (badanie.pacjent.pesel!=null){textField_3.setText(badanie.pacjent.getPesel());}
							
							if (badanie.pacjent.nazwisko!=null){textField_4.setText(badanie.pacjent.getNazwisko());}

							List<String> imiona = badanie.pacjent.getImie();
							StringBuilder names = new StringBuilder();
							
							for (String i:imiona){
								names.append(i +" ");
							}
							textField_5.setText(names.toString());
							if (badanie.pacjent.plec!=null){comboBox.getModel().setSelectedItem(badanie.pacjent.getPlec());}
											
							if (badanie.wywiadWstepny.glSkargi!=null){textField_6.setText(badanie.wywiadWstepny.getGlSkargi());}
							if (badanie.wywiadWstepny.dotychczasowyPrzebieg!=null){textField_7.setText(badanie.wywiadWstepny.getDotychczasowyPrzebieg());}
							if (badanie.wywiadWstepny.przebyteChoroby!=null){textField_8.setText(badanie.wywiadWstepny.getPrzebyteChoroby());}
								
							if (badanie.leki!=null){
								List<Lek> leki = badanie.leki.getLek();
							    for (Lek l:leki){
							    	Object[] row = { l.getNazwaLeku(), l.getDawka(), l.getOkresPoczatek(), l.getOkresKoniec(), l.getUwagi()};
							    	model = (DefaultTableModel) table.getModel();
							    	model.addRow(row);
							    }
							}
							
							if (badanie.dolegliwosci.ukladOddechowy!=null){textField_9.setText(badanie.dolegliwosci.getUkladOddechowy());}
							if (badanie.dolegliwosci.ukladKrazenia!=null){textField_10.setText(badanie.dolegliwosci.getUkladKrazenia());}
							if (badanie.dolegliwosci.ukladTrawienny!=null){textField_11.setText(badanie.dolegliwosci.getUkladTrawienny());}
							
							if (badanie.opis!=null){textField_12.setText(badanie.getOpis());}  
						
							inputStream.close();
							
							
						} catch (JAXBException e1) {
							txtAsaaa.setText("Nie mo¿na przetworzyæ pliku");
							txtAsaaa.setForeground(Color.RED);
						} catch (FileNotFoundException e1) {
							txtAsaaa.setText("Nie mo¿na otworzyæ pliku");
							txtAsaaa.setForeground(Color.RED);
						} catch (IOException e1) {
							txtAsaaa.setText("Nie mo¿na otworzyæ pliku");
							txtAsaaa.setForeground(Color.RED);
							}
				}else{
					txtAsaaa.setText("Anulowano wybieranie pliku");
			      }
			}
		});
		btnWczytajDaneZ.setBounds(20, 527, 231, 23);
		contentPane.add(btnWczytajDaneZ);
		
		HighlightListener hl = new HighlightListener(textField);
		hl.maybeHighlight();
		HighlightListener hl2 = new HighlightListener(textField_1);
		hl2.maybeHighlight();
		HighlightListener hl3 = new HighlightListener(textField_2);
		
		JLabel lblNewLabel = new JLabel("wz\u00F3r: R17-002");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel.setBounds(362, 33, 76, 14);
		panel.add(lblNewLabel);
		
		JLabel lblWzrOkur = new JLabel("wz\u00F3r: Oku-11/R17");
		lblWzrOkur.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblWzrOkur.setBounds(564, 33, 86, 14);
		panel.add(lblWzrOkur);
		hl3.maybeHighlight();
		HighlightListener hl4 = new HighlightListener(textField_3);
		hl4.maybeHighlight();
		HighlightListener hl5 = new HighlightListener(textField_4);
		hl5.maybeHighlight();
		HighlightListener hl6 = new HighlightListener(textField_5);
		hl6.maybeHighlight();
		HighlightListener hl7 = new HighlightListener(textField_12);
		hl7.maybeHighlight();
		
		
		JButton btnWalidujIZapisz = new JButton("Waliduj i Zapisz");
		btnWalidujIZapisz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				ObjectFactory  factory = new ObjectFactory();
				Badanie bad = factory.createBadanie();
			
				if (textField.getText().trim().length()!=0){bad.setOddzial(textField.getText());}
				else{bad.setOddzial(null);}
			  	
				if (textField_1.getText().trim().length()!=0){bad.setKsiegaGl(textField_1.getText());}
				else{bad.setKsiegaGl(null);}
				if (textField_2.getText().trim().length()!=0){bad.setKsiegaOddzialu(textField_2.getText());}
				else{bad.setKsiegaOddzialu(null);}
				
				Pacjent pac = factory.createPacjent();
				if (textField_3.getText().trim().length()==0 &&
						textField_4.getText().trim().length()==0 &&
						textField_5.getText().trim().length()==0 &&
						comboBox!=null)
				{
					bad.setPacjent(null);
				}
				if (textField_3.getText().trim().length()!=0){pac.setPesel(textField_3.getText());}
				else{pac.setPesel(null);}
				if (textField_4.getText().trim().length()!=0){pac.setNazwisko(textField_4.getText());}
				else{pac.setNazwisko(null);}
				if (textField_5.getText().trim().length()!=0){
					List<String> names = new ArrayList<String>(Arrays.asList(textField_5.getText().split(" ")));
					pac.setImie(names);
					}
				else {pac.setImie(null);}
				if (comboBox!=null){pac.setPlec(Plec.fromValue(comboBox.getSelectedItem().toString()));}
				else{pac.setPlec(null);}
				bad.setPacjent(pac);

				Wywiad wywiad = factory.createWywiad();
				if (textField_6.getText().trim().length()==0 && 
						textField_7.getText().trim().length()==0 && 
						textField.getText().trim().length()==0) 
				{
					bad.setWywiadWstepny(null);
				}
				if (textField_6.getText().trim().length()!=0){wywiad.setGlSkargi(textField_6.getText());}
				else{wywiad.setGlSkargi(null);}
				if (textField_7.getText().trim().length()!=0){wywiad.setDotychczasowyPrzebieg(textField_7.getText());}
				else{wywiad.setDotychczasowyPrzebieg(null);}
				if (textField_8.getText().trim().length()!=0){wywiad.setPrzebyteChoroby(textField_8.getText());}
				else{wywiad.setPrzebyteChoroby(null);}
				bad.setWywiadWstepny(wywiad);
				
				Leki meds = factory.createLeki();
				List<Lek> medList = new ArrayList<>();
				int j=0;
				int rows = table.getRowCount();
				
				while (j<rows){ //rows
					DefaultTableModel tm = (DefaultTableModel) table.getModel();
					@SuppressWarnings("unchecked")
					List<Object> rowData = (List<Object>) tm.getDataVector().elementAt(j);
					
					Lek med = new Lek();
					med.setNazwaLeku(rowData.get(0).toString());
					med.setDawka(new BigDecimal(rowData.get(1).toString()));
					
					GregorianCalendar gcalendar = new GregorianCalendar();
					if (rowData.get(2).toString().length()!=0){
						String sDate = rowData.get(2).toString();
						DateFormat sdfIn = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
						DateFormat sdfOut = new SimpleDateFormat("yyyy-MM-dd");
							try {
									Date date = sdfIn.parse(sDate);
									date = sdfIn.parse(sDate);
									gcalendar.setTime(sdfOut.parse(sdfOut.format(date).toString()));
							} catch (ParseException e2) {e2.printStackTrace();}
					
						XMLGregorianCalendar xmlDate = null;
							try {
									xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendarDate(gcalendar.get(Calendar.YEAR), gcalendar.get(Calendar.MONTH)+1, gcalendar.get(Calendar.DAY_OF_MONTH), DatatypeConstants.FIELD_UNDEFINED);
							} catch (DatatypeConfigurationException e1) {e1.printStackTrace();}
							med.setOkresPoczatek(xmlDate); 
					} else {med.setOkresPoczatek(null);}
					
					if (rowData.get(3).toString().length()!=0){
						String sDate = rowData.get(3).toString();
						DateFormat sdfIn = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
						DateFormat sdfOut = new SimpleDateFormat("yyyy-MM-dd");
							try {
									Date date = sdfIn.parse(sDate);
									date = sdfIn.parse(sDate);
									gcalendar.setTime(sdfOut.parse(sdfOut.format(date).toString()));
							} catch (ParseException e2) {e2.printStackTrace();}
					
						XMLGregorianCalendar xmlDate = null;
							try {
									xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendarDate(gcalendar.get(Calendar.YEAR), gcalendar.get(Calendar.MONTH)+1, gcalendar.get(Calendar.DAY_OF_MONTH), DatatypeConstants.FIELD_UNDEFINED);
							} catch (DatatypeConfigurationException e1) {e1.printStackTrace();}
							med.setOkresKoniec(xmlDate); 
					} else {med.setOkresKoniec(null);}
					
					if (rowData.get(4).toString().length()!=0){med.setUwagi((String) rowData.get(4));}
					else{med.setUwagi(null);}
					medList.add(med);
					j++;
				}
				meds.setLek(medList);
				bad.setLeki(meds);
				
				Dolegliwosci dol = factory.createDolegliwosci();
				if (textField_9.getText().trim().length()==0 &&
						textField_10.getText().trim().length()==0 &&
						textField_11.getText().trim().length()==0) 
				{
					bad.setDolegliwosci(null);
				}
				if (textField_9!=null){dol.setUkladOddechowy(textField_9.getText());} 
				else{dol.setUkladOddechowy(null);}
				if (textField_10!=null){dol.setUkladKrazenia(textField_10.getText());}
				else{dol.setUkladKrazenia(null);}
			    if (textField_11!=null){dol.setUkladTrawienny(textField_11.getText());} 
			    else{dol.setUkladTrawienny(null);}
			    bad.setDolegliwosci(dol);
			    
			    if (textField_12.getText().trim().length()!=0) {bad.setOpis(textField_12.getText());}
			    else{bad.setOpis(null);}
			    
			    JFileChooser fileChooser = new JFileChooser();
			    FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter("xml files (*.xml)", "xml");
				fileChooser.setFileFilter(xmlfilter);
				
			    File fileToSave = null;
					  if (fileChooser.showSaveDialog(Main.this) == JFileChooser.APPROVE_OPTION) {
						  fileOut = fileChooser.getSelectedFile();	
						  newFileOut = new File(fileChooser.getSelectedFile().toString());
					      fileToSave = new File(fileChooser.getSelectedFile()+".xml");
					      
					      String filename = fileChooser.getSelectedFile().toString();
					      if (!filename.endsWith(".xml"))
					      {
					    	 filename += ".xml";
					         newFileOut = new File(filename);
					         }
					      
					      
					      txtAsaaa.setText("Zapisano jako: " + fileToSave.getAbsolutePath());
					      txtAsaaa.setForeground(Color.GREEN);
					  } else {
						  txtAsaaa.setText("Anulowano zapis pliku");
					  }
			    						
				try {
					//SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			        //Schema schema = factory.newSchema(new File("badniePodmiotoweOgolne.xsd"));
					
					JAXBContext jaxbcontext = JAXBContext.newInstance(Badanie.class);
					Marshaller marshaller = jaxbcontext.createMarshaller();
					marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
					marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
					OutputStream outputStream = new FileOutputStream(newFileOut);
					//XMLValidation(outputStream, "badniePodmiotoweOgolne.xsd");
					
					//marshaller.setSchema(schema);
					//marshaller.setEventHandler(new MyValidator(txtAsaaa));
		            marshaller.marshal(bad, outputStream);
		            InputStream xmlIn = new FileInputStream(fileToSave.getAbsolutePath());
					SchemaValidator sv = new SchemaValidator();
					sv.validate(xmlIn, txtAsaaa);
		            outputStream.close();
					
				} catch (JAXBException e1) {
					txtAsaaa.setText("Nie mo¿na przetworzyæ pliku");
					txtAsaaa.setForeground(Color.RED);
				} catch (FileNotFoundException e1) {
					txtAsaaa.setText("Nie mo¿na zapisaæ pliku");
					txtAsaaa.setForeground(Color.RED);
				} catch (IOException e1) {
					txtAsaaa.setText("Nie mo¿na zapisaæ pliku");
					txtAsaaa.setForeground(Color.RED);
				}
			}
		});
		btnWalidujIZapisz.setBounds(279, 527, 143, 23);
		contentPane.add(btnWalidujIZapisz);
		
		txtAsaaa = new JTextField();
		txtAsaaa.setBackground(UIManager.getColor("Button.light"));
		txtAsaaa.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtAsaaa.setBounds(10, 561, 764, 39);
		txtAsaaa.setForeground(Color.DARK_GRAY);
		contentPane.add(txtAsaaa);
		txtAsaaa.setColumns(10);
		
		JButton btnNowyPlik = new JButton("Nowy plik");
		btnNowyPlik.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  textField.setText("");
				  textField_1.setText("");
				  textField_2.setText("");
				  textField_3.setText("");
				  textField_4.setText("");
				  textField_5.setText("");
				  textField_6.setText("");
				  textField_7.setText("");
				  textField_8.setText("");
				  textField_9.setText("");
				  textField_10.setText("");
				  textField_11.setText("");
				  textField_12.setText("");
				  
				  DefaultTableModel model = (DefaultTableModel) table.getModel();
				  int rows = model.getRowCount(); 
				  for(int i = rows - 1; i >=0; i--)
				  {
				     model.removeRow(i); 
				  }
			}
		});
		btnNowyPlik.setBounds(445, 527, 89, 23);
		contentPane.add(btnNowyPlik);
		
		JButton btnPdf = new JButton("Generuj PDF");
		btnPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ObjectFactory  factory = new ObjectFactory();
				Badanie bad = factory.createBadanie();
			
				if (textField.getText().trim().length()!=0){bad.setOddzial(textField.getText());}
				else{bad.setOddzial(null);}
			  	
				if (textField_1.getText().trim().length()!=0){bad.setKsiegaGl(textField_1.getText());}
				else{bad.setKsiegaGl(null);}
				if (textField_2.getText().trim().length()!=0){bad.setKsiegaOddzialu(textField_2.getText());}
				else{bad.setKsiegaOddzialu(null);}
				
				Pacjent pac = factory.createPacjent();
				if (textField_3.getText().trim().length()==0 &&
						textField_4.getText().trim().length()==0 &&
						textField_5.getText().trim().length()==0 &&
						comboBox!=null)
				{
					bad.setPacjent(null);
				}
				if (textField_3.getText().trim().length()!=0){pac.setPesel(textField_3.getText());}
				else{pac.setPesel(null);}
				if (textField_4.getText().trim().length()!=0){pac.setNazwisko(textField_4.getText());}
				else{pac.setNazwisko(null);}
				if (textField_5.getText().trim().length()!=0){
					List<String> names = new ArrayList<String>(Arrays.asList(textField_5.getText().split(" ")));
					pac.setImie(names);
					}
				else {pac.setImie(null);}
				if (comboBox!=null){pac.setPlec(Plec.fromValue(comboBox.getSelectedItem().toString()));}
				else{pac.setPlec(null);}
				bad.setPacjent(pac);

				Wywiad wywiad = factory.createWywiad();
				if (textField_6.getText().trim().length()==0 && 
						textField_7.getText().trim().length()==0 && 
						textField.getText().trim().length()==0) 
				{
					bad.setWywiadWstepny(null);
				}
				if (textField_6.getText().trim().length()!=0){wywiad.setGlSkargi(textField_6.getText());}
				else{wywiad.setGlSkargi(null);}
				if (textField_7.getText().trim().length()!=0){wywiad.setDotychczasowyPrzebieg(textField_7.getText());}
				else{wywiad.setDotychczasowyPrzebieg(null);}
				if (textField_8.getText().trim().length()!=0){wywiad.setPrzebyteChoroby(textField_8.getText());}
				else{wywiad.setPrzebyteChoroby(null);}
				bad.setWywiadWstepny(wywiad);
				
				Leki meds = factory.createLeki();
				List<Lek> medList = new ArrayList<>();
				int j=0;
				int rows = table.getRowCount();
				
				while (j<rows){ //rows
					DefaultTableModel tm = (DefaultTableModel) table.getModel();
					@SuppressWarnings("unchecked")
					List<Object> rowData = (List<Object>) tm.getDataVector().elementAt(j);
					
					Lek med = new Lek();
					med.setNazwaLeku(rowData.get(0).toString());
					med.setDawka(new BigDecimal(rowData.get(1).toString()));
					
					GregorianCalendar gcalendar = new GregorianCalendar();
					if (rowData.get(2).toString().length()!=0){
						String sDate = rowData.get(2).toString();
						DateFormat sdfIn = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
						DateFormat sdfOut = new SimpleDateFormat("yyyy-MM-dd");
							try {
									Date date = sdfIn.parse(sDate);
									date = sdfIn.parse(sDate);
									gcalendar.setTime(sdfOut.parse(sdfOut.format(date).toString()));
							} catch (ParseException e2) {e2.printStackTrace();}
					
						XMLGregorianCalendar xmlDate = null;
							try {
									xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendarDate(gcalendar.get(Calendar.YEAR), gcalendar.get(Calendar.MONTH)+1, gcalendar.get(Calendar.DAY_OF_MONTH), DatatypeConstants.FIELD_UNDEFINED);
							} catch (DatatypeConfigurationException e1) {e1.printStackTrace();}
							med.setOkresPoczatek(xmlDate); 
					} else {med.setOkresPoczatek(null);}
					
					if (rowData.get(3).toString().length()!=0){
						String sDate = rowData.get(3).toString();
						DateFormat sdfIn = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
						DateFormat sdfOut = new SimpleDateFormat("yyyy-MM-dd");
							try {
									Date date = sdfIn.parse(sDate);
									date = sdfIn.parse(sDate);
									gcalendar.setTime(sdfOut.parse(sdfOut.format(date).toString()));
							} catch (ParseException e2) {e2.printStackTrace();}
					
						XMLGregorianCalendar xmlDate = null;
							try {
									xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendarDate(gcalendar.get(Calendar.YEAR), gcalendar.get(Calendar.MONTH)+1, gcalendar.get(Calendar.DAY_OF_MONTH), DatatypeConstants.FIELD_UNDEFINED);
							} catch (DatatypeConfigurationException e1) {e1.printStackTrace();}
							med.setOkresKoniec(xmlDate); 
					} else {med.setOkresKoniec(null);}
					
					//if (rowData.get(4).toString().length()!=0){med.setUwagi((String) rowData.get(4));}
					//else{med.setUwagi("Brak uwag");}
					med.setUwagi(rowData.get(4).toString());

					//med.setUwagi("Brak uwag");
					medList.add(med);
					j++;
				}
				meds.setLek(medList);
				bad.setLeki(meds);
				
				Dolegliwosci dol = factory.createDolegliwosci();
				if (textField_9.getText().trim().length()==0 &&
						textField_10.getText().trim().length()==0 &&
						textField_11.getText().trim().length()==0) 
				{
					bad.setDolegliwosci(null);
				}
				if (textField_9!=null){dol.setUkladOddechowy(textField_9.getText());} 
				else{dol.setUkladOddechowy(null);}
				if (textField_10!=null){dol.setUkladKrazenia(textField_10.getText());}
				else{dol.setUkladKrazenia(null);}
			    if (textField_11!=null){dol.setUkladTrawienny(textField_11.getText());} 
			    else{dol.setUkladTrawienny(null);}
			    bad.setDolegliwosci(dol);
			    
			    if (textField_12.getText().trim().length()!=0) {bad.setOpis(textField_12.getText());}
			    else{bad.setOpis(null);}
			    						
				try {
					//SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			        //Schema schema = factory.newSchema(new File("badniePodmiotoweOgolne.xsd"));					
					
					JAXBContext jaxbcontext = JAXBContext.newInstance(Badanie.class);
					Marshaller marshaller = jaxbcontext.createMarshaller();
					marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
					marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
					//XMLValidation(outputStream, "badniePodmiotoweOgolne.xsd");
					
					//marshaller.setSchema(schema);
					//marshaller.setEventHandler(new MyValidator(txtAsaaa));
		            marshaller.marshal(bad, new File("temp.xml"));
		            SchemaValidator sv = new SchemaValidator();
					sv.validate(new FileInputStream("temp.xml"), txtAsaaa);
		            
					
				} catch (JAXBException e1) {
					txtAsaaa.setText("Nie mo¿na przetworzyæ pliku");
					txtAsaaa.setForeground(Color.RED);
				} catch (FileNotFoundException e1) {
					txtAsaaa.setText("Nie znaleziono pliku xml");
					txtAsaaa.setForeground(Color.RED);
					e1.printStackTrace();
				}
			
				JFileChooser fileChooser = new JFileChooser();
				FileFilter filter = new FileNameExtensionFilter("PDF Documents", "pdf");
				fileChooser.setFileFilter(filter);
					  
					  if (fileChooser.showSaveDialog(Main.this) == JFileChooser.APPROVE_OPTION) {
						  fileOut = fileChooser.getSelectedFile();	
						  newFileOut = new File(fileChooser.getSelectedFile().toString());
						  
					      File fileToSave = new File (fileChooser.getSelectedFile()+".pdf");
						  
					      String filename = fileChooser.getSelectedFile().toString();
					      if (!filename.endsWith(".pdf"))
					      {
					    	 filename += ".pdf";
					         newFileOut = new File(filename);
					         }
					      
					      
					      FopFactory fopFactory = FopFactory.newInstance();
							
						    TransformerFactory tFactory = TransformerFactory.newInstance();
						    
						    DefaultConfigurationBuilder cfgBuilder = new DefaultConfigurationBuilder();
						    Configuration cfg;
							try {
								cfg = cfgBuilder.buildFromFile(new File("fop.xconf"));
								fopFactory.setUserConfig(cfg);
							} catch (ConfigurationException | SAXException | IOException e3) {
								e3.printStackTrace();
							}
							
							OutputStream out;
					        try {
					        	
					            Templates templates = tFactory.newTemplates(new StreamSource(new File("badaniePodmiotoweOgolne.xsl")));

					            out = new org.apache.commons.io.output.NullOutputStream();
					            FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
					            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);
					            Transformer transformer = templates.newTransformer();
					            transformer.setParameter("page-count", "#");
					            transformer.transform(new StreamSource(new File("temp.xml")),
					                new SAXResult(fop.getDefaultHandler()));

					            out = new java.io.FileOutputStream(fileToSave.getAbsolutePath());
					            out = new java.io.BufferedOutputStream(out);
					            try {
					              foUserAgent = fopFactory.newFOUserAgent();
					              fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);
					              transformer = templates.newTransformer();
					              transformer.transform(new StreamSource(new File("temp.xml")),
					                  new SAXResult(fop.getDefaultHandler()));
					            } finally {
					                out.close();
					            }
					        } catch (Exception e1) {
					            e1.printStackTrace();
					        }
					      txtAsaaa.setText("Zapisano jako: " + fileToSave.getAbsolutePath());
					      txtAsaaa.setForeground(Color.GREEN);
					  } else {
						  txtAsaaa.setText("Anulowano zapis pliku");
					  }
			}
		});
		btnPdf.setBounds(554, 527, 143, 23);
		contentPane.add(btnPdf);
		setVisible(true);
	}
	

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTable table;
	private JTextField txtAsaaa;

	public static void main(String[] args) throws JAXBException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
