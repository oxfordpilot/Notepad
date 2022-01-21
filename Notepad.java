package notepadproject;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.font.GraphicAttribute;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.text.AttributeSet.FontAttribute;

public class Notepad implements ActionListener {
	


	JFrame jf;
	JMenuBar jmb;
	JMenu file, edit;
	JMenuItem jmi, jmi1, jmi2, jmi3, jmi4, jmi5, jmi6;
	JTextArea jta;
	JScrollPane jsp;
	String title = "Untitled File";
	JMenuItem cut;
	JMenuItem copy;
	JMenuItem paste;
	JMenuItem replace;
	JMenuItem datetime;
	JMenu format,help;
	JFileChooser jfc, jfc1;
	FileOutputStream fos, fos1;
	JFrame replaceframe;
	JLabel jl,jl1,jl2,jl3;
	JButton jb,jb1,jb2,jb3;
	JCheckBox jcb,jcb1;
	JTextField jtf1,jtf2;
	JMenuItem font, fontcolor,textareacolor;
	JComboBox CB_Font_Style,CB_Font_Style1, CB_Font_Style2;
	JLabel jlf,jlf1,jlf2;
	JButton okay;
	JFrame font_frame;
	JCheckBoxMenuItem word_wrap;
	JFrame open_help;
	
	public Notepad() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); // For auto System Look and Feel
		} catch (Exception e) {
			System.out.println(e);
		}

		jf = new JFrame(title); // JFrame
		jf.setSize(600, 500);
		
		Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Office\\eclipse-workspace\\Notepadproject\\src\\notepadproject\\Notepad.png");    
		jf.setIconImage(icon);

		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jmb = new JMenuBar(); // Main Menu Bar

		file = new JMenu("File"); // File Menu

		jmi = new JMenuItem("New"); // Sub Menu New
		jmi.setAccelerator(KeyStroke.getKeyStroke('N', InputEvent.CTRL_DOWN_MASK));
		jmi.addActionListener(this);
		file.add(jmi);

		jmi1 = new JMenuItem("Open"); // Sub Menu Open
		jmi1.setAccelerator(KeyStroke.getKeyStroke('O', InputEvent.CTRL_DOWN_MASK));
		jmi1.addActionListener(this);
		file.add(jmi1);
		jmi2 = new JMenuItem("Save"); // Sub Menu Save
		jmi2.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_DOWN_MASK));
		jmi2.addActionListener(this);
		file.add(jmi2);
		
		jmi3 = new JMenuItem("Save As"); // Sub Menu Save As
		jmi3.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.SHIFT_DOWN_MASK));
		jmi3.addActionListener(this);
		file.add(jmi3);
		file.addSeparator();

		jmi5 = new JMenuItem("Page Setup"); //Sub Menu Page Setup
		jmi5.setAccelerator(KeyStroke.getKeyStroke('P', InputEvent.SHIFT_DOWN_MASK));
		jmi5.addActionListener(this);
		file.add(jmi5);

		jmi6 = new JMenuItem("Print ");  //Sub Menu Print Page
		jmi6.setAccelerator(KeyStroke.getKeyStroke('P', InputEvent.CTRL_DOWN_MASK));
		jmi6.addActionListener(this);
		file.add(jmi6);

		file.addSeparator();

		jmi4 = new JMenuItem("Exit"); // Sub Menu Exit
		jmi4.setAccelerator(KeyStroke.getKeyStroke('E', InputEvent.CTRL_DOWN_MASK));
		jmi4.addActionListener(this);
		file.add(jmi4);

		jmb.add(file);

		edit = new JMenu("Edit"); // Edit Menu
		
		cut = new JMenuItem("Cut"); // Sub Menu Cut
		cut.setAccelerator(KeyStroke.getKeyStroke('X', InputEvent.CTRL_DOWN_MASK));
		cut.addActionListener(this);
		edit.add(cut);

		copy = new JMenuItem("Copy");  // Sub Menu Copy
		copy.setAccelerator(KeyStroke.getKeyStroke('C', InputEvent.CTRL_DOWN_MASK));
		copy.addActionListener(this);
		edit.add(copy);

		paste = new JMenuItem("Paste");  //Sub Menu Past
		paste.setAccelerator(KeyStroke.getKeyStroke('V', InputEvent.CTRL_DOWN_MASK));
		paste.addActionListener(this);
		edit.add(paste);
		edit.addSeparator();
		
		replace = new JMenuItem("Replace"); //Sub Menu Replace
		replace.setAccelerator(KeyStroke.getKeyStroke('H', InputEvent.CTRL_DOWN_MASK));
		replace.addActionListener(this);
		edit.add(replace);
		
		edit.addSeparator();
		datetime = new JMenuItem("Date/Time");  // Sub Menu Date and Time
		edit.add(datetime);

		jmb.add(edit);

		format = new JMenu("Format"); //Format Menu

		font = new JMenuItem("Font"); //Sub Menu Font
		font.addActionListener(this);
		format.add(font);

		format.addSeparator();	
		
		fontcolor = new JMenuItem("Font Color");  //Sub Menu Font Color
		fontcolor.addActionListener(this);
		format.add(fontcolor);

		textareacolor = new JMenuItem("Text Area Color");
		textareacolor.addActionListener(this);
		format.add(textareacolor);
		
		
		word_wrap=new JCheckBoxMenuItem("Word wrap"); //Word Wrapper
		word_wrap.addActionListener(this);
		format.add(word_wrap);
		

		jmb.add(format);

		help = new JMenu("Help");
		help.addActionListener(this);
		jmb.add(help);

		jf.setJMenuBar(jmb); // Setting JMenu on JFrame

		jta = new JTextArea(); // Added Text Area.

		jsp = new JScrollPane(jta); // ScrollPane on Text Area
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // added Vertical Scrollbar Always On
																				// Policy
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); // added Horizontal Scrollbar Always
																					// On Policy
		jf.add(jsp); // Scroll Pane added to JFrame.

		jf.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jmi4) // Exit
		{
			System.exit(0);
		}
		if (e.getSource() == jmi2) // save
		{
			save();
		}

		if (e.getSource() == jmi) // New
		{
			newnotepad();
		}
		if (e.getSource() == jmi1) { //open
			openfile();
		}
		if(e.getSource() ==jmi3) //saveas
		{
			saveas();
		}
		if(e.getSource() ==jmi5) //Page Setup
		{
			pagesetup();
		}
		if(e.getSource()==jmi6) //Print Page
		{
			printpage();
		}
		if(e.getSource()==cut) //Cut
		{
			jta.cut();
		}
		if(e.getSource()==copy) //Copy
		{
			jta.copy();
		}
		if(e.getSource()==paste) //past
		{
			jta.paste();
		}
		if(e.getSource()==replace) //Replace
		{
			replaceFrame();
		}
		if(e.getSource()==jb1)
		{
			replacea();
		}
		if(e.getSource()==jb2)
		{
			replacea1();
		}
		if(e.getSource()==jb3)
		{
			replaceframe.setVisible(false);
		}
		if(e.getSource()==datetime)
		{
			datentime();
		}
		if(e.getSource()== fontcolor)
		{
			fontcolor();
		}
		if(e.getSource()==textareacolor)
		{
			settextareacolor();
		}
		if(e.getSource()==font)
		{
			openfontframe();
		}
		if(e.getSource()==okay)
		{
			getokay();
		}
		if(e.getSource()==help)
		{
			openhelp();
			
		}
		if(e.getSource()==word_wrap)
		{
			boolean b=word_wrap.getState();
			jta.setLineWrap(b);
		}
	}
	
	
	
	
	public void openhelp()
	{
		open_help=new JFrame("Help");
		open_help.setSize(500,500);
		open_help.setLayout(null);
		open_help.setVisible(true);
	}
		
	
	
	
	public void fontcolor()
	{
		Color c=JColorChooser.showDialog(jf, "Select Color", Color.black);
		jta.setForeground(c);
	}
	
	public void settextareacolor()
	{
		Color c=JColorChooser.showDialog(jf, "Select Text Area", Color.white);
		jta.setBackground(c);
	}
	
	public void datentime()
	{
		LocalDateTime ldt= LocalDateTime.now();
		String dt=ldt.format(DateTimeFormatter.ISO_DATE);
		jta.append(dt);
	}
	
	
	
	
	public void replaceFrame()
	{
		replaceframe=new JFrame();
		replaceframe.setSize(400,200);
		replaceframe.setLayout(null);
		
		Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Office\\eclipse-workspace\\Notepadproject\\src\\notepadproject\\Notepad.png");    
		replaceframe.setIconImage(icon);
		
		
		jl=new JLabel("Find what:");
		jl.setBounds(35,10, 70,30);
		replaceframe.add(jl);
		
		jl1=new JLabel("Replace with:");
		jl1.setBounds(35,40, 70,30);
		replaceframe.add(jl1);
		
		jtf1=new JTextField();
		jtf1.setBounds(120, 15, 150, 20);
		replaceframe.add(jtf1);
		
		jtf2=new JTextField();
		jtf2.setBounds(120, 45, 150, 20);
		replaceframe.add(jtf2);
		
		
		
		jb=new JButton("Find Next");
		jb.setBounds(280,11,84,25);
		replaceframe.add(jb);
		
		//Buttons Starts Here
		jb1=new JButton("Replace");
		jb1.setBounds(280,41,84,25);
		jb1.addActionListener(this);
		replaceframe.add(jb1);
		
		
		jb2=new JButton("Replace All");
		jb2.setBounds(280,71,84,25);
		jb2.addActionListener(this);
		replaceframe.add(jb2);
		
		jb3=new JButton("Cancel");
		jb3.setBounds(280,101,84,25);
		jb3.addActionListener(this);
		replaceframe.add(jb3);
		//Buttons Starts Here
		
		//Check Box Added
		jcb=new JCheckBox();
		jcb.setBounds(5,105,20,20);
		replaceframe.add(jcb);
		
		jcb1=new JCheckBox();
		jcb1.setBounds(5,130,20,20);
		replaceframe.add(jcb1);
		
		//Check-Box Ends here
		
		//label for Check Box
		
		jl2=new JLabel("Match case");
		jl2.setBounds(28,100, 70,30);
		replaceframe.add(jl2);
		
		jl3=new JLabel("Wrap around");
		jl3.setBounds(28,125, 70,30);
		replaceframe.add(jl3);
		
		//label for Check Box ends
		
		
		
		replaceframe.setVisible(true);
	}
	
	
	public void replacea()
	{
		String find_what=jtf1.getText();
		String replace_with=jtf2.getText();
		String text=jta.getText();
		String new_text=text.replace(find_what,replace_with);
		jta.setText(new_text);
		
		replaceframe.setVisible(false);
	}
	
	
	public void replacea1()
	{
		String find_what=jtf1.getText();
		String replace_with=jtf2.getText();
		String text=jta.getText();
		String new_text=text.replaceAll(find_what,replace_with);
		jta.setText(new_text);
		
		replaceframe.setVisible(false);
	}
	
	
	
	public void printpage()
	{
		PrinterJob pj = PrinterJob.getPrinterJob();
	
		    if (pj.printDialog()) {
		        try {
		        	pj.print();
		        	}
		        catch (PrinterException exc) {
		            System.out.println(exc);
		         }
		     }  
	}
	
	
	
	public void pagesetup()
	{
		PrinterJob pj = PrinterJob.getPrinterJob();
		PageFormat pf = pj.pageDialog(pj.defaultPage());
	
	}
	

	
	public void save()
	{
		if(jf.getTitle().equals(title))
		{
			saveas();
		}
		else 
		{
			try {
				String text = jta.getText();
				byte[] b = text.getBytes();
				File f = jfc.getSelectedFile();
				FileOutputStream fos = new FileOutputStream(f);
				fos.write(b);
				settitle(jfc.getSelectedFile().getName());
				fos.close();
			}catch (Exception e)
			{
				System.out.println(e);
			}
		}
	}
	
	
	
	
	public void openfile() {
		FileInputStream fis;
		String s=jta.getText();
		if(s.equals("")) {
			JFileChooser jfc=new JFileChooser();
			
			int i=jfc.showOpenDialog(jf);
			if(i==0)
			{
				try {
					
					File f=jfc.getSelectedFile();
					fis=new FileInputStream(f);
					int j;
					while((j=fis.read())!= -1)
					{
						jta.append(String.valueOf( (char)j));
					}
					settitle(jfc.getSelectedFile().getName()); ///getting File name on Tab.
				}catch(Exception e)
				{
					System.out.println(e);
				}
				finally {
						try {
							
						}catch(Exception e)
						{
							System.out.println(e);
						}
				}		
			
			}
		}else {
			int j=JOptionPane.showConfirmDialog(jf, "Do you want to save this file?");
			if(j==0)
			{	
				 
				saveas();
				
				try {
					jfc.showOpenDialog(jf);
					JFileChooser jfc=new JFileChooser();
					File f=jfc.getSelectedFile();
					
					FileInputStream fis1=new FileInputStream(f);
					int l;
					
					while((l=fis1.read()) !=-1)
					{
						jta.append(String.valueOf( (char)l));
					}
					fis1.close();
					
				}catch(Exception e)
				{
					System.out.println(e);
				}
				finally {
					try {
						
						settitle(jfc.getSelectedFile().getName());
					}catch(Exception e)
					{
						System.out.println(e);
					}
				}
				
				
				
			}
			else {
				
				
			}
		}
		
		}
				
				
			

	

	public void newnotepad() {
		String text = jta.getText();
		if (!text.equals("")) {
			int i = JOptionPane.showConfirmDialog(jf, "Do you want to save this file?");

			if (i == 0) {

				int j = saveas();

				if (j != 0) {
					settitle(title);
					jta.setText("");
				}

			} else if (i == 1) {
				jta.setText("");
			}
		}

	}

	public int saveas() {
		fos = null;
		File f;
		String text;
		jfc = new JFileChooser();
		int i = jfc.showSaveDialog(jf);

		if (i == 0) {
			try {
				text = jta.getText();
				byte[] b = text.getBytes();
				f = jfc.getSelectedFile();
				fos = new FileOutputStream(f);
				fos.write(b);

				settitle(f.getName()); // Setting Title.

			} catch (Exception e) {
				System.out.println(e);
			} finally {
				try {
					fos.close();
				} catch (Exception e) {
					System.out.println(e);
				}
			}

			JOptionPane.showMessageDialog(jfc, "File Saved.");
		} else {
			JOptionPane.showMessageDialog(jfc, "File not saved.", "File not saved.", JOptionPane.WARNING_MESSAGE);
		}
		return 0;
	}

	void settitle(String title) {
		jf.setTitle(title);
	}
	
	
	
	public void openfontframe()
	{
		font_frame=new JFrame("Fonts...");
		font_frame.setSize(500,180);
		
		font_frame.setLayout(null);
		Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Office\\eclipse-workspace\\Notepadproject\\src\\notepadproject\\Notepad.png");    
		font_frame.setIconImage(icon);
		
		
		//------ComboBox Starts Here.....
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment(); //added from Oracle
		String[] fontFamilies = ge.getAvailableFontFamilyNames();
		
		CB_Font_Style=new JComboBox(fontFamilies);
		CB_Font_Style.setBounds(10, 40, 150, 25);
		font_frame.add(CB_Font_Style);
		
		String [] s= {"Simple", "Bold", "Italic"};
		CB_Font_Style1=new JComboBox(s);
		CB_Font_Style1.setBounds(180, 40, 150, 25);
		font_frame.add(CB_Font_Style1);
		
		Integer [] font_size= {8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50};
		CB_Font_Style2=new JComboBox(font_size);
		CB_Font_Style2.setBounds(360, 40, 60, 25);
		font_frame.add(CB_Font_Style2);
		//------ComboBox Ends Here.....
		
		//--------JLable Starts here....
		
		jlf=new JLabel("Fonts");
		jlf.setBounds(10, 5, 50, 50);
		font_frame.add(jlf);
		
		jlf1=new JLabel("Font Style");
		jlf1.setBounds(182, 5, 50, 50);
		font_frame.add(jlf1);
		
		
		jlf2=new JLabel("Size");
		jlf2.setBounds(362, 5, 50, 50);
		font_frame.add(jlf2);
		
		//--------JLable Ends here....
		
		///---------JButton Starts Here-----
		okay=new JButton("Okay");
		okay.setBounds(360,100,80,30);
		okay.addActionListener(this);
		font_frame.add(okay);
		
		font_frame.setVisible(true);
	}
	
	
	public void getokay()
	{
		String font_family=(String)CB_Font_Style.getSelectedItem();
		String font_style=(String) CB_Font_Style1.getSelectedItem();
		Integer font_size1=(Integer)CB_Font_Style2.getSelectedItem();
		
		
		int style1=0;
		if(font_style.equals("Simple"))
		{
			style1=Font.PLAIN;
		}
		
		if(font_style.equals("Bold"))
		{
			style1=Font.BOLD;
		}
		if(font_style.equals("Italic"))
		{
			style1=Font.ITALIC;
		}
		
		Font f=new Font(font_family,style1,font_size1);
		
		jta.setFont(f);
		
		font_frame.setVisible(false);
		
	}
	
	

	
	
	

}
