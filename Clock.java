import javax.swing.*;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.Calendar;
import java.lang.Math;


public class Clock extends JFrame{
	
	private Calendar calendar;
	private int x0;
	private int y0;	
	private ClockPanel clockPanel;
	private Timer timer;
	
	class ClockPanel extends JPanel{
		
		public ClockPanel(){
			this.setBackground(Color.WHITE);
		}
		
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(200, 200);
		}
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			update(g);
		}
		
		public void update(Graphics g) {
			calendar = Calendar.getInstance();
			int hour = calendar.get(Calendar.HOUR);
			int minute = calendar.get(Calendar.MINUTE);
			int second = calendar.get(Calendar.SECOND);
			
			g.drawOval(0, 0, 200, 200);
			drawLine(g, (hour+minute/60.0)*360/12*Math.PI/180, 50);
			drawLine(g, (minute+second/60.0)*360/60*Math.PI/180, 70);
			drawLine(g, second*360/60*Math.PI/180, 90);
			
		}
		
		public void drawLine(Graphics g, double radians, int length) {
			int x1 = (int) (x0 + length * Math.sin(radians));
			int y1 = (int) (y0 - length * Math.cos(radians));
			Graphics2D thick = (Graphics2D)g;
			
			if(length == 50) {
				thick.setStroke(new BasicStroke(5));
				thick.draw(new Line2D.Float(x0, y0, x1, y1));
			}
			else if(length == 70) {
				thick.setStroke(new BasicStroke(3));
				thick.draw(new Line2D.Float(x0, y0, x1, y1));
			}
			else {
				thick.setStroke(new BasicStroke(1));
				thick.draw(new Line2D.Float(x0, y0, x1, y1));
			}	
		}
	}
		
	public Clock() {
		x0 = 100;
		y0 = 100;
		ClockPanel clockPanel = new ClockPanel();
		
		this.setLayout(new BorderLayout());
		this.getContentPane().add(clockPanel, BorderLayout.CENTER);
		clockPanel.setBounds(this.getBounds());
		this.setTitle("Clock");
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);	
		
		timer = new Timer(500, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Clock.this.repaint();
			}
			
		});
		timer.start();
		
	}
	
	
	public static void main(String[] args) {
		Clock clock = new Clock();
		Calendar c = Calendar.getInstance();
		System.out.println(c.getTime());
	}


}
