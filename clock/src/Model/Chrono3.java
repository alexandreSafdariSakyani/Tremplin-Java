package Model;


import java.lang.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
public class Chrono3
{
	private int heure=0,minute=0,seconde=0, miliSeconds=0;
	private boolean existe;
	private JFrame fenetre;
	public JButton split;
	private JList liste;
	private String [] splits = {"00:00:00.0","00:00:00.0","00:00:00.0","00:00:00.0"};
	private Timer timer1;
	private ActionListener tache_timer;
	private JLabel Label1;
	public JButton debut;
	public JButton fin;
	//private JLabel LabelSplit = new JLabel(heure+":"+minute+":"+seconde);
	private Date date;
	private SimpleDateFormat format;
	public Chrono3()
	{
		/* Le timer */
		int delais=100;
		ActionListener tache_timer;
		/* création des composants */
		Label1 = new JLabel(heure+":"+minute+":"+seconde+":"+miliSeconds); /* déclarer final car une classe interne va acceder à ce composant */
		debut = new JButton("Start");
		fin = new JButton("Remise à zéro");
		split = new JButton("Diviser");
		liste = new JList(getAbstractListModel());
		liste.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		fenetre = new JFrame("Chronomètre 3");
		//setExiste(false);
		fenetre.setVisible(true);
		fenetre.setResizable(false);
		split.addActionListener(this.getSplitActionListener());
		debut.addActionListener(this.getStartActionListener());
		fin.addActionListener(this.getResetActionListener());
		JPanel Panel1 = new JPanel();
		/* Action réalisé par le timer */
		tache_timer= new ActionListener()
		{
			public void actionPerformed(ActionEvent e1)
			{
			miliSeconds++;
			if(miliSeconds == 10){
			    miliSeconds = 0;
			    seconde++;
			}
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
			Label1.setText(heure+":"+minute+":"+seconde+":"+miliSeconds);/* rafraichir le label */
			}
			};
		/* instanciation du timer */
		timer1= new Timer(delais,tache_timer);

		/* Ajout des composants aux conteneurs avec formatage */
		Panel1.add(debut);
		Panel1.add(fin);
		Panel1.add(split);
		Panel1.add(liste);
		Label1.setBorder(new EmptyBorder(10,135,10,10));
		fenetre.getContentPane().add(Label1,"Center");
		fenetre.getContentPane().add(Panel1,"South");
		
		/* Afficher l'ihm */
		fenetre.pack();
		fenetre.setLocation(350,200); /* Déplacer la fenetre à ce nouvel emplacement */
		fenetre.setSize(500,300); /* dimension de la fenetre */
		fenetre.setVisible(false);
	}
		public void start(){
		timer1.start();
		}
		public void stop(){
		timer1.stop();
		}


		public boolean isRunning(){
		    return timer1.isRunning();
		}

		public String afficher(){
		return this.getTime();
		}

		public String getTime(){
		date = new Date();
		date.setHours(heure);
		date.setMinutes(minute);
		date.setSeconds(seconde);
		format = new SimpleDateFormat("HH:mm:ss");
		return format.format(date) + "." + miliSeconds;
		}

		public ActionListener getStartActionListener(){
		return new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
		       getStartAction();
		   }
		};
		}

		public boolean getStartAction(){
		if(debut.getText().compareTo("Start") == 0) {
		    debut.setText("Stop"); 
		    start();
		} else if(debut.getText().compareTo("Stop") == 0) {
		        debut.setText("Start");
		        stop();
		}
		return true;
		}


		public ActionListener getResetActionListener(){

		return new ActionListener() {
		    
		    public void actionPerformed(ActionEvent e) {
		        resetAction();
		    }
		};
		}

		public boolean resetAction(){
		if(debut.getText().equals("Start")) {
		    heure = 0;
		    minute = 0;
		    seconde = 0;
		    miliSeconds = 0;
		        debut.setText("Start");
		    splits[0] = "00:00:00.0";
		    splits[1] = "00:00:00.0";
		    splits[2] = "00:00:00.0";
		    splits[3] = "00:00:00.0";
		    liste.setModel(getAbstractListModel());
		        
		}
		return true;
		}


		public ListModel getAbstractListModel() {
		return new javax.swing.AbstractListModel() {
		String[] strings = splits;
		public int getSize() {
		    return strings.length; 
		}
		public Object getElementAt(int i) {     
		return strings[i]; 
		}
		};
		}

		public void diviser(String time){
		for(int x = 0; x<4; x++){
		  if(splits[x].equals("00:00:00.0")){
		          splits[x] = time;
//		          System.out.println(time);
		          break;
		  }
		}
		}

		public int nbSplits(){
		int nbS = 0;
		for(int x = 0; x<4; x++){
		    if(!splits[x].equals("00:00:00.0"))
		        nbS++;
		}
		return nbS;
		}

		public ActionListener getSplitActionListener(){
		return new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        splitAction();
		    }
		};
		}

		public boolean splitAction(){
		    
		    //System.out.println(Label1.getText());
		        if(split.getText().equals("Diviser") && debut.getText().equals("Stop"))
		            {
		        diviser(Label1.getText());
		        liste.setModel(getAbstractListModel());
		            }else{
		        splits[0] = "00:00:00.0";
		        splits[1] = "00:00:00.0";
		        splits[2] = "00:00:00.0";
		        splits[3] = "00:00:00.0";
		        split.setText("Diviser");
		    }
		    
		    if(nbSplits()==4){
		        split.setText("Reset Splits");
		    }else
		        split.setText("Diviser");
		    
		    return true;
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