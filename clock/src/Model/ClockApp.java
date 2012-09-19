package Model;
//Doom Comment of the End of World !!!
import java.awt.*;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.event.*;
import java.util.GregorianCalendar;

public class ClockApp
{
	@SuppressWarnings("deprecation")
	ClockApp()
	{
		fenetre=new JFrame("JClock");
		fenetre.addWindowListener( new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		calend=new GregorianCalendar();
		h=calend.get(GregorianCalendar.HOUR_OF_DAY);
		min=calend.get(GregorianCalendar.MINUTE);
		sec=calend.get(GregorianCalendar.SECOND);
		calend.get(GregorianCalendar.MILLISECOND);
		d=calend.get(GregorianCalendar.DAY_OF_MONTH);
		mon=calend.get(GregorianCalendar.MONTH);
		year=calend.get(GregorianCalendar.YEAR);
		clockPanel=new ClockPanel();
		fenetre.getContentPane().setLayout(new BorderLayout());
		JPanel chronoPanel = new JPanel();
		GridLayout gl = new GridLayout();
		gl.setColumns(1);
		gl.setRows(2);
		chronoPanel.setLayout(gl);
		fenetre.getContentPane().add(clockPanel, BorderLayout.CENTER);
		fenetre.getContentPane().add(chronoPanel, BorderLayout.EAST);
		
		panel = new JPanel();
		fenetre.getContentPane().add(panel, BorderLayout.SOUTH);
		ChronoPlus = new JButton("Chrono");
		panel.add(ChronoPlus);
		rebourtPlus = new JButton("Timer");
		panel.add(rebourtPlus);
		rebourtPlus.addActionListener(new ActionListener() {
			Rebourt reb = new Rebourt();
			Rebourt2 reb2 = new Rebourt2();
			Rebourt3 reb3 = new Rebourt3();
			Rebourt4 reb4 = new Rebourt4();
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (reb.isExiste()==false){
					reb.show();
					reb.setExiste(true);
				} 
				else if(reb2.isExiste()==false){
					reb2.show();
					reb2.setExiste(true);
				} 
				else if(reb3.isExiste()==false){
					reb3.show();
					reb3.setExiste(true);
				} 
				else if(reb4.isExiste()==false){
					reb4.show();
					reb4.setExiste(true);
				}
			}
		});
		
				ChronoPlus.addActionListener(new ActionListener() {
					Chrono chro = new Chrono();
					Chrono2 chro2 = new Chrono2();
					Chrono3 chro3 = new Chrono3();
					Chrono4 chro4 = new Chrono4();
					@Override
					public void actionPerformed(ActionEvent e) {
						if (chro.isExiste()==false){
							chro.show();
							chro.setExiste(true);
						} 
						else if(chro2.isExiste()==false){
							chro2.show();
							chro2.setExiste(true);
						} 
						else if(chro3.isExiste()==false){
							chro3.show();
							chro3.setExiste(true);
						} 
						else if(chro4.isExiste()==false){
							chro4.show();
							chro4.setExiste(true);
						}
					}
				});

		fenetre.setSize(298,387);
		fenetre.setResizable(false);
		fenetre.show();
		clockThread=new ClockThread(this);
		clockThread.start();
	}/* Fin constructeur*/
	class ClockPanel extends JPanel
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void paintComponent (Graphics g)
		{


			super.paintComponent(g);
			//fenetre.getContentPane().add(B);


			xc=100;
			yc=100;
			int rayon=Math.min(xc,yc)*80/100;
			font= new Font("Times New Roman",0,15);
			g.setFont(font);
			for(int i=1;i<=12;i++)
			{
				double angle=i*Math.PI/6.0-Math.PI/2.0;
				double x=xc+rayon*Math.cos(angle);
				double y=yc+rayon*Math.sin(angle);
				g.drawString(" "+i,(int)x,(int)y);
				String heure_courante = String.valueOf(h)+ ":" + String.valueOf(min)+ ":" + String.valueOf(sec) ;
				String date_courante = String.valueOf(d)+ "/" + String.valueOf(mon)+ "/" + String.valueOf(year) ;
				g.drawString(heure_courante,30,230);
				g.drawString(date_courante,30,250);
			}
			/* gestion et affichage de aiguilles*/
			double anglesec=(sec*((Math.PI)/30.0)-(Math.PI/2.0));
			int xsf=xc+(int)(0.7*rayon*Math.cos(anglesec));
			int ysf=yc+(int)(0.7*rayon*Math.sin(anglesec));
			g.setColor(Color.red);
			g.drawLine(xc,yc,xsf,ysf);
			double anglemin=(min*((Math.PI)/30.0)-(Math.PI/2.0));
			int xmf=xc+(int)(0.6*rayon*Math.cos(anglemin));
			int ymf=yc+(int)(0.6*rayon*Math.sin(anglemin));
			g.setColor(Color.yellow);
			g.drawLine(xc,yc,xmf,ymf);
			double angleheure=(h*((2*Math.PI)/12.0)-(Math.PI/2.0));
			int xhf=xc+(int)(0.4*rayon*Math.cos(angleheure));
			int yhf=yc+(int)(0.4*rayon*Math.sin(angleheure));
			g.setColor(Color.green);
			g.drawLine(xc,yc,xhf,yhf);
			//clockPanel.add(ChronoPlus);
			//clockPanel.add(ChronoMoins);


		}/* fin de paintComponent */
	}/* fin de classe ClockPanel */
	public void increment()
	{
		sec=sec+1;
		if(sec>60)
		{
			min=min+1;
			sec=1;
			if(min>60)
			{
				min=1;
				h=h+1;
				if(h>12)
				{
					h=1;
				}
			}
		}
		clockPanel.repaint();
	}



	public static void main (String args[])
	{    

		new ClockApp();

	}





	private JFrame fenetre;
	private GregorianCalendar calend;
	private int h,min,sec,xc,yc;
	private int d,mon,year;
	private JButton ChronoPlus;
	private JButton rebourtPlus;
	private ClockPanel clockPanel;
	private ClockThread clockThread;
	private Font font;
	private JPanel panel;
}/* fin de classe ClockApp*/
class ClockThread extends Thread
{
	ClockThread(ClockApp horloge)
	{
		this.horloge=horloge;
	}
	public void run()
	{
		while(true)
		{
			horloge.increment();
			try
			{
				sleep(1000);
			}
			catch(InterruptedException e)
			{
			}
		}
	}
	private ClockApp horloge;
}