package poo.valutazioneEspressione.Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;



import static poo.valutazioneEspressione.EspressioneUtil.*;


@SuppressWarnings("serial")
class FrameGUI extends JFrame implements ActionListener{
	
	private TastieraVirtuale tv = null;

	
	private JPanel panel1, panel2;
	private JScrollPane scrollPane1, scrollPane2;
	private JTextArea  testo1, console, areaInserimento;
	private JButton valutaButton, tastieraButton;
	private JLabel label1, risultato;
	
	private String espressione;

	
	private final String FONT1 = "Century Schoolbook";

	
	private final int COLOR_BACKGROUND = 0XE6E6FA;
	private final int COLOR_BUTTON = 0xF5FFFA;
	private final int COLOR_PANEL = 0xFFF5EE;
	private final int COLOR_TEXT = 0xFFF8DC;
	private String titolo = "ValutazioneEspressione";
	

	
	public FrameGUI(){
		
		setTitle(titolo);
		setBounds(400, 200, 648, 445);
		
		
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		
		panel1 = new JPanel();
		getContentPane().add(panel1, BorderLayout.CENTER);
		panel1.setLayout(null);
		panel1.setBackground(new Color(COLOR_BACKGROUND));
		scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(75, 275, 470, 42);
		panel1.add(scrollPane1);
		
		console = new JTextArea();
		console.setEditable(false);
		scrollPane1.setViewportView(console);
		
		scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(61, 209, 505, 42);
		panel1.add(scrollPane2);
		
		areaInserimento = new JTextArea();
		scrollPane2.setViewportView(areaInserimento);
		areaInserimento.setColumns(10);
		areaInserimento.setFont(new Font(FONT1, Font.PLAIN, 20));
		
		testo1 = new JTextArea();
		testo1.setEditable(false);
		testo1.setFont(new Font(FONT1, Font.PLAIN, 23));
		testo1.setText("VALUTATORE DI ESPRESSIONI");
		testo1.setBounds(145, 86, 374, 42);
		testo1.setBackground(new Color(COLOR_TEXT));
		testo1.setBorder(border);
		panel1.add(testo1);
		
		valutaButton = new JButton("VALUTA");
		valutaButton.setFont(new Font(FONT1, Font.PLAIN, 10));
		valutaButton.setBackground(new Color(COLOR_BUTTON));
		valutaButton.setBounds(268, 339, 89, 23);
		panel1.add(valutaButton);
		
		tastieraButton = new JButton("TASTIERA VIRTUALE");
		tastieraButton.setBackground(new Color(COLOR_BUTTON));
		tastieraButton.setFont(new Font(FONT1, Font.PLAIN, 10));
		tastieraButton.setBounds(35, 352, 157, 30);
		panel1.add(tastieraButton);
		
		panel2 = new JPanel();
		panel2.setBackground(new Color(COLOR_PANEL));
		panel2.setBounds(61, 174, 505, 24);
		panel1.add(panel2);
		
		label1 = new JLabel("Inserire l'espressione da valutare");
		label1.setFont(new Font(FONT1, Font.PLAIN, 13));
		panel2.setBorder(border);
		panel2.add(label1);
		
		risultato = new JLabel("Risultato");
		risultato.setHorizontalAlignment(SwingConstants.CENTER);
		risultato.setBounds(280, 260, 69, 14);
		panel1.add(risultato);
		
		
		valutaButton.addActionListener(this);
		tastieraButton.addActionListener(this);
		
		
	}//FrameGUI



	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == valutaButton) {
			
			espressione = areaInserimento.getText().replaceAll("\\s+", "");
			
			if(espressioneValida(espressione)) {
				
				try {
					int risultato = valuta(espressione);
					console.setText("" + risultato);
				}catch(RuntimeException re){
					JOptionPane.showMessageDialog(null, "Espressione costruita in modo errato");	
				}
				
				
			}else {
				
				JOptionPane.showMessageDialog(null, "Espressione inserita non valida");	
			}
		}
		
		else if(e.getSource() == tastieraButton) {
			
			if(tv == null) {
				tv = new TastieraVirtuale();
				tv.setVisible(true);
			}
			
			else {
				tv.setVisible(true);
			}
		return;
		}
	}//actionPerformed
	
	

	private class TastieraVirtuale extends JFrame implements ActionListener{
		
		private JPanel contentPane;
		JButton uno, due, tre, quattro, cinque, sei, sette, otto, nove, zero, add, sottr, mul, div, resto, eleva, chiudi, leftBracket, rightBracket, delete;

		private final int COLOR_BUTTON = 0xFFF8DC;
		
		public TastieraVirtuale(){
			
			setTitle("Tastiera Virtuale");
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			
			addWindowListener(new WindowAdapter(){
			 				
			 				public void windowClosing(WindowEvent e) {
			 					
			 					setVisible(false);
				 				
			 				}
	
			 			});
			
			setBounds(100, 100, 450, 300);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			setContentPane(contentPane);
					
			JPanel panelButton=new JPanel();
			panelButton.setBackground(new Color(255, 245, 238));
			panelButton.setLayout( new GridLayout(4,5,3,3) );
		    sette=new JButton("7");
			sette.setBackground(new Color(COLOR_BUTTON));
			otto=new JButton("8");
			otto.setBackground(new Color(COLOR_BUTTON));
			nove=new JButton("9");
			nove.setBackground(new Color(COLOR_BUTTON));
			div=new JButton("/");
			div.setBackground(new Color(COLOR_BUTTON));
			quattro=new JButton("4");
			quattro.setBackground(new Color(COLOR_BUTTON));
			cinque=new JButton("5");
			cinque.setBackground(new Color(COLOR_BUTTON));
			sei=new JButton("6");
			sei.setBackground(new Color(COLOR_BUTTON));
			mul=new JButton("*");
			mul.setBackground(new Color(COLOR_BUTTON));
			uno=new JButton("1");
			uno.setBackground(new Color(COLOR_BUTTON));
			due=new JButton("2");
			due.setBackground(new Color(COLOR_BUTTON));
			tre=new JButton("3");
			tre.setBackground(new Color(COLOR_BUTTON));
			sottr=new JButton("-");	
			sottr.setBackground(new Color(COLOR_BUTTON));
			zero=new JButton("0");
			zero.setBackground(new Color(COLOR_BUTTON));
			add=new JButton("+");
			add.setBackground(new Color(COLOR_BUTTON));
			//...
			panelButton.add(sette); 
			panelButton.add(otto); 
			panelButton.add(nove); 
			panelButton.add(div);
			
			resto = new JButton("%");
			resto.setBackground(new Color(COLOR_BUTTON));
			panelButton.add(resto);
			panelButton.add(quattro); 
			panelButton.add(cinque); 
			panelButton.add(sei); 
			panelButton.add(mul);
			
			leftBracket = new JButton("(");
			leftBracket.setBackground(new Color(COLOR_BUTTON));
			panelButton.add(leftBracket);
			panelButton.add(uno); 
			panelButton.add(due); 
			panelButton.add(tre); 
			panelButton.add(sottr);	
			
			rightBracket = new JButton(")");
			rightBracket.setBackground(new Color(COLOR_BUTTON));
			panelButton.add(rightBracket);
			panelButton.add(zero);
			panelButton.add(add);
			
			getContentPane().add( panelButton, BorderLayout.CENTER);
			
			eleva = new JButton("^");
			eleva.setBackground(new Color(COLOR_BUTTON));
			
			panelButton.add(eleva);
			chiudi = new JButton("CHIUDI");
			chiudi.setFont(new Font("Century Schoolbook", Font.PLAIN, 11));
			chiudi.setBackground(new Color(COLOR_BUTTON));
			panelButton.add(chiudi);
			
			delete = new JButton("DELETE");
			delete.setFont(new Font("Century Schoolbook", Font.PLAIN, 11));
			delete.setBackground(new Color(COLOR_BUTTON));
			panelButton.add(delete);
			
			chiudi.addActionListener(this);
			delete.addActionListener(this);
			uno.addActionListener(this);
			due.addActionListener(this);
			tre.addActionListener(this);
			quattro.addActionListener(this);
			cinque.addActionListener(this);
			sei.addActionListener(this);
			sette.addActionListener(this);
			otto.addActionListener(this);
			nove.addActionListener(this);
			zero.addActionListener(this);
			add.addActionListener(this);
			sottr.addActionListener(this);
			mul.addActionListener(this);
			div.addActionListener(this);
			resto.addActionListener(this);
			eleva.addActionListener(this);
			leftBracket.addActionListener(this);
			rightBracket.addActionListener(this);
			
		}//TastieraVirtuale
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == chiudi) {
				setVisible(false);
			}
			
			else if(e.getSource() == uno) {
				areaInserimento.setText(areaInserimento.getText() + "1");
			}
			
			else if(e.getSource() == due) {
				areaInserimento.setText(areaInserimento.getText() + "2");
			}
			
			else if(e.getSource() == tre) {
				areaInserimento.setText(areaInserimento.getText() + "3");
			}
				
			else if(e.getSource() == quattro) {
				areaInserimento.setText(areaInserimento.getText() + "4");
			}
				
			else if(e.getSource() == cinque) {
				areaInserimento.setText(areaInserimento.getText() + "5");
			}
				
			else if(e.getSource() == sei) {
				areaInserimento.setText(areaInserimento.getText() + "6");
			}
			
			else if(e.getSource() == sette) {
				areaInserimento.setText(areaInserimento.getText() + "7");
			}
			if(e.getSource() == otto) {
				areaInserimento.setText(areaInserimento.getText() + "8");
			}
			
			else if(e.getSource() == nove) {
				areaInserimento.setText(areaInserimento.getText() + "9");
			}
			
			else if(e.getSource() == zero) {
				areaInserimento.setText(areaInserimento.getText() + "0");
			}
			
			else if(e.getSource() == add) {
				areaInserimento.setText(areaInserimento.getText() + "+");
			}
				
			else if(e.getSource() == sottr) {
				areaInserimento.setText(areaInserimento.getText() + "-");
			}
				
			else if(e.getSource() == mul) {
				areaInserimento.setText(areaInserimento.getText() + "*");
			}
				
			else if(e.getSource() == div) {
				areaInserimento.setText(areaInserimento.getText() + "/");
			}
			
			else if(e.getSource() == resto) {
				areaInserimento.setText(areaInserimento.getText() + "%");
			}
			else if(e.getSource() == eleva) {
				areaInserimento.setText(areaInserimento.getText() + "^");
			}
			else if(e.getSource() == leftBracket) {
				areaInserimento.setText(areaInserimento.getText() + "(");
			}
			else if(e.getSource() == rightBracket) {
				areaInserimento.setText(areaInserimento.getText() + ")");
			}
			else if(e.getSource() == delete) {
				
				StringBuilder tmp = new StringBuilder(areaInserimento.getText());
				
				if(tmp.length()!=0)
					tmp.deleteCharAt(tmp.length()-1);
				
				areaInserimento.setText(tmp.toString());
			}
			
		}//actionPerformed
		
	}//TastieraVirtuale

	
}//FrameGUI



public class ValutazioneEspressioneGUI {

	
	
	public static void main(String[] args) {
		
		 EventQueue.invokeLater( new Runnable(){
			 
			  public void run(){
				  
				  try {
			      
				  JFrame f=new FrameGUI();  
				  f.setVisible(true);
			      
				  }catch(Exception e) {
					  e.printStackTrace();
				  }
				  
			  }
		  });	
	}//main
	
}//ValutazioneEspressione
