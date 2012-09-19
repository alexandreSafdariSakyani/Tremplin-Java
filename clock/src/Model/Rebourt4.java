package Model;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
public class Rebourt4
{
	private static int heure,minute,seconde;
	private JFormattedTextField h=new JFormattedTextField("0000");
	private JFormattedTextField min=new JFormattedTextField("00");
	private JFormattedTextField sec=new JFormattedTextField("00");
	private Audio son = new Audio();
	private boolean existe;
	private JFrame fenetre;

	public Rebourt4(){
		//init des variable horaires
		/* Le timer */
		int delais=1000;
		ActionListener tache_timer;
		/* création des composants */
		final JPanel Panel2 = new JPanel();
		final JButton debut = new JButton("Start");
		JButton fin = new JButton("Remise à zéro");
		fenetre = new JFrame("Compte à Rebourt 4");
		fenetre.setResizable(false);
		JPanel Panel1 = new JPanel();
		h.setPreferredSize(new Dimension(40,20));
		h.setHorizontalAlignment(JTextField.CENTER);
		min.setPreferredSize(new Dimension(20,20));
		min.setHorizontalAlignment(JTextField.CENTER);
		sec.setPreferredSize(new Dimension(20,20));
		sec.setHorizontalAlignment(JTextField.CENTER);
		Panel2.add(h);
		Panel2.add(min);
		Panel2.add(sec);
		/* Action réalisé par le timer */
		tache_timer= new ActionListener()
		{
			public void actionPerformed(ActionEvent e1)
			{
				seconde--;
				if(seconde==-1)
				{
					seconde=59;
					minute--;
				}
				if(minute==-1)
				{
					minute=59;
					heure--;
				}
				if (heure<0){
					heure=0;
					minute=0;
					seconde=0;
					h.setText(String.valueOf(heure));
					min.setText(String.valueOf(minute));
					sec.setText(String.valueOf(seconde));
					son.run();
					debut.doClick();
				}
				h.setText(String.valueOf(heure));
				min.setText(String.valueOf(minute));
				sec.setText(String.valueOf(seconde));/* rafraichir le label */
			}
		};
		/* instanciation du timer */
		final Timer timer1= new Timer(delais,tache_timer);

		/* Ajout des composants aux conteneurs avec formatage */
		Panel1.add(debut);
		Panel1.add(fin);
		Panel2.setBorder(new EmptyBorder(10,115,10,10));
		fenetre.getContentPane().add(Panel2,"West");
		fenetre.getContentPane().add(Panel1,"South");
		/* Action provoqué par l'utilisateur */
		/* Lors du clic sur le bouton debut */
		debut.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String texte;
				texte=debut.getText();
				if(texte.compareTo("Start")==0)
				{
					debut.setText("Stop ");
					timer1.start();
					h.setEditable(false);
					min.setEditable(false);
					sec.setEditable(false);
					heure=Integer.parseInt(h.getText());
					minute=Integer.parseInt(min.getText());
					seconde=Integer.parseInt(sec.getText());
				}
				else if(texte.compareTo("Stop ")==0)
				{
					debut.setText("Start");
					timer1.stop();
					h.setEditable(true);
					min.setEditable(true);
					sec.setEditable(true);
				}
			}
		});
		//label1
		/* Lors du clic sur le bouton fin */
		fin.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String texte;
				texte=debut.getText();
				if(texte.compareTo("Start")==0)
				{
					debut.setText("Start");
					h.setText("0000");
					min.setText("00");
					sec.setText("00");
				}
			}
		});
		/* Afficher l'ihm */
		fenetre.pack();
		fenetre.setLocation(350,200); /* Déplacer la fenetre à ce nouvel emplacement */
		fenetre.setSize(300,100); /* dimension de la fenetre */
		fenetre.setVisible(false);
		fenetre.addWindowListener(new WindowListener(){

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				setExiste(false);
			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				setExiste(false);
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				setExiste(true);
				
			}
		});
		}
	
	public void show(){
		fenetre.setVisible(true);
	}

	public boolean isExiste() {
		return existe;
	}

	public void setExiste(boolean existe) {
		this.existe = existe;
	}
	

} 