package AgendaMgrafico;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;



public class Main2  extends JFrame implements ActionListener,KeyListener,WindowListener{
	static JButton  b;
	static JButton  b2;
	static JFrame ventana;
	private Agenda contacts = new Agenda();
	private JTextField cmd;
	private JTextArea textArea;
	private JToolBar toolBar;
	static  JPanel p;
	private JFileChooser fileChooser = new JFileChooser();
	private JButton saveAs;
	private JButton save;
	private JButton load;
    private String ruta=" ";
public Main2()  throws IOException{
		
		
		JFrame ventana = new JFrame();

		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    p=new JPanel ();
		ventana.setBounds(370,370,370,370);
		ventana.add(p);
	    p.setBounds(370,370,370,370);
		b=new JButton("Buscar/Borrar Contacto");
	    b.setActionCommand("EXEC");
		b.addActionListener(this);


	    b2=new JButton("Ingresar Contacto");
	    b2.setBounds(50,50,50,50);
	    b2.setActionCommand("EXEC");
		b2.addActionListener(this);
		p.add(b);
		p.add(b2);
		
	
		
		ventana.setVisible(true);
		
	}

public static void main(String[] args) {
	try {
		Main2 m=new Main2();
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		int respuesta = JOptionPane.showConfirmDialog(Main2.this, "¿Estás Seguro?", "Cierre de la aplicación", JOptionPane.YES_NO_OPTION);
		if (respuesta == JOptionPane.YES_OPTION)
			System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		cmd.requestFocus();
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		  if(e.getKeyCode()==KeyEvent.VK_ENTER){
            
          }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		 if(e.getKeyCode()==KeyEvent.VK_ENTER){
			 try {
				exec();
			
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		 }
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object objeto=e.getSource();
	
		if(objeto==b2 || objeto==b )  {
			
			  b2.setBackground(Color.lightGray);
			  b.setVisible(false);
			  b2.setVisible(false);
			  textArea = new JTextArea(15,30);
			  cmd=new  JTextField (30) ;
			  cmd.addKeyListener(this);
			  textArea.setEditable(false);
			  textArea.setFocusable(false);
			
			
		    
		         toolBar = new JToolBar();
		    
			     JLabel etiqueta=new JLabel("menu");
			     toolBar.add(etiqueta);
			     load = new JButton(new ImageIcon(getClass().getResource("/img/Open file.png")));
			     load.setActionCommand("LOAD");
				 load.addActionListener(this);
				 toolBar.add(load);
				
				
				save = new JButton(new ImageIcon(getClass().getResource("/img/Save.png")));
				save.setActionCommand("SAVE");
				save.addActionListener(this);
				save.setBounds(5, 5, 5, 5);
				toolBar.add(save);
				
				
				saveAs = new JButton(new ImageIcon(getClass().getResource("/img/Save as.png")));
				saveAs.setActionCommand("SAVEAS");
				saveAs.addActionListener(this);
				toolBar.add(saveAs);
			
				
				p.add(toolBar, BorderLayout.NORTH);
				toolBar.setFloatable(false);
			    p.add(textArea,BorderLayout.LINE_END);
			    p.add(cmd,BorderLayout.LINE_END);
				
				pack();
				setLocationRelativeTo(null);
				addWindowListener(this);
				//
				//http://maki90-blog.blogspot.com/2013/08/jfilechooser-es-una-clase-java-que-nos.html
				
		}
		if(objeto==load) {
			load.setBackground(Color.lightGray);
			
			int seleccion = fileChooser.showOpenDialog(null);
			if (seleccion == JFileChooser.APPROVE_OPTION)
			{
			   File fichero = fileChooser.getSelectedFile();
			 
			try {
				ruta = fichero.getCanonicalPath();
			} catch (IOException e1) {
				e1.printStackTrace();
			} 
			   contacts.leer(ruta);
			  System.out.println(ruta);
			}
			
}
		if(objeto==saveAs) {
			saveAs.setBackground(Color.lightGray);
			fileChooser.showSaveDialog(null);
			fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		    File fichero = fileChooser.getSelectedFile();
		   
			try {
				ruta = fichero.getCanonicalPath();
			} catch (IOException e1) {
				e1.printStackTrace();
			} 
			  contacts.escribir(ruta);
			  System.out.println(ruta);
		
			
		   
		}if(objeto==save) {
			save.setBackground(Color.lightGray);
			
			contacts.escribir(ruta);
		}
	}
	
	private void exec() throws IOException {
		String result = contacts.exec(cmd.getText());
		 textArea.setFocusable(true);
		 if (result != null) {
		textArea.append(result +"\n");
		}
		cmd.setText("");

	}
	
}
