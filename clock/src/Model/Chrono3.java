package Model;


import java.lang.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
public class Chrono3
{
	private static int heure=0,minute=0,seconde=0;
	private boolean existe;
	private JFrame fenetre;
	
	public Chrono3()
	{
		/* Le timer */
		int delais=1000;
		ActionListener tache_timer;
		/* création des composants */
		final JLabel Label1 = new JLabel(heure+":"+minute+":"+seconde); /* déclarer final car une classe interne va acceder à ce composant */
		final JButton debut = new JButton("Start");
		JButton fin = new JButton("Remise à zéro");
		fenetre = new JFrame("Chronomètre 3");
		fenetre.setResizable(false);
		setExiste(false);
		JPanel Panel1 = new JPanel();
		/* Action réalisé par le timer */
		tache_timer= new ActionListener()
		{
			public void actionPerformed(ActionEvent e1)
			{
				seconde++;
				if(seconde==60)
				{
					seconde=0;
					minute++;
				}
				if(minute==60)
				{
					minute=0;
					heure++;
				}
				Label1.setText(heure+":"+minute+":"+seconde);/* rafraichir le label */
			}
		};
		/* instanciation du timer */
		final Timer timer1= new Timer(delais,tache_timer);

		/* Ajout des composants aux conteneurs avec formatage */
		Panel1.add(debut);
		Panel1.add(fin);
		Label1.setBorder(new EmptyBorder(10,135,10,10));
		fenetre.getContentPane().add(Label1,"Center");
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
				}
				else if(texte.compareTo("Stop ")==0)
				{
					debut.setText("Start");
					timer1.stop();
				}
			}
		});
		/* Lors du clic sur le bouton fin */
		fin.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String texte;
				texte=debut.getText();
				if(texte.compareTo("Start")==0)
				{
					heure=0;
					minute=0;
					seconde=0;
					debut.setText("Start");
					Label1.setText(heure+":"+minute+":"+seconde);
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