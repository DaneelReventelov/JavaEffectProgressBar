package Effects.Gradient;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.LinearGradientPaint;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.Paint;
import java.awt.geom.Point2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GradPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	int offset = 0;
	int dim = 0;
	

	private ColorDistList cds = new ColorDistList(new ColorDist[]{
			new ColorDist(ColorDist.FLARE,0.0f),
			new ColorDist(ColorDist.LIGHT,0.3f),
			new ColorDist(ColorDist.DARK,0.7f)
			});
	
	
	public GradPanel(int offset,int dim){
		setPreferredSize(new Dimension(dim,100));
		this.offset = offset;
		this.dim = dim;
	}

	
	protected Point2D getPoint(float x,float y){
		return new Point2D.Double(x,y);
		
	}
	
	
	protected float[] getDists(){
		return cds.getDists(true);
	}	
	
	protected Color[] getColors(Color lightColor,Color darkColor){
		return cds.getColors(true,Color.WHITE,lightColor,darkColor);
	}
	
		public Paint getGradient(int offset,  Color darkColor, Color lightColor) {
				offset = offset%527;
		    	Point2D start = getPoint(0 + offset,0);
		    	Point2D end = getPoint(527 + offset,0);
		    	System.out.println(offset + " "  + (527+offset));
		        return new LinearGradientPaint(start, end,getDists(), getColors(lightColor, darkColor),CycleMethod.REFLECT);
		}
		
		public static void main(String[] args) {
			JFrame f = new JFrame();
			JPanel p = new JPanel(new GridLayout(6,1));
			p.add(new GradPanel(0,1394));
			p.add(new GradPanel(1394,1394));
			p.add(new GradPanel(-139400,1394));
			/*
			int of = -130;
			p.add(new GradPanel(0-of,1394));
			p.add(new GradPanel(1394-of,1394));
			of = -2500;
			p.add(new GradPanel((0-of)%1394,1394));
			p.add(new GradPanel((1394-of)%1394,1394));
			*/			
			f.getContentPane().add(p);
			f.pack();
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setVisible(true);		
			f.setResizable(false);
		}

		
		public void paint(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setClip(0, 0, dim, 100);
	        g2.setPaint(getGradient(offset, Color.BLUE.darker(), Color.BLUE.brighter()));
	        g2.fillRect(0, 0, dim, 100);
		}
			
		
		
}
