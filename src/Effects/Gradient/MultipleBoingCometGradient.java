package Effects.Gradient;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.util.ArrayList;

import Effect.DimensionListener;
import Effect.EffectProgressBarCoord;

public class MultipleBoingCometGradient extends CustomizableGradient implements DimensionListener{


	protected ArrayList<CustomBoingCometGradientPaint> ps = new ArrayList<>();
	protected int gap = 60;
	protected int newGap = 0;
	protected int row = 3;
	private boolean gapSet = false;

	protected boolean background = true;		

	/*
			private int radius = 100;
			private boolean radiusSet = false;				
	 */

	public void init(EffectProgressBarCoord bar){
		super.init(bar);
		bar.addDimensionListener(this);
		addTrasparentFinal();
	}





	boolean uno = true;
	public synchronized void paintGradientColor(Graphics2D g2, int amountFull,float speed, Color darkColor, Color lightColor){
		g2.setClip(bar.getClipShape(amountFull));
		if (background) {
			g2.setPaint(lightColor);
			g2.fill(bar.getRectangle());    	  
		}


		newGap--;
		if ((newGap<0) && uno){
	//		uno = false;
			newGap = (int) (gap*(0.5 + Math.random()));	    	  
			BoingCometGradient com = new BoingCometGradient();
			com.init(bar);
			com.setMagnitude(magnitude);
			com.setIllusionDirection(direction);
			int r = (int) (1 + Math.rint((row-1)*Math.random()));

			//System.out.println("MCG57 " + animationIndex + " " + next + " " + newGap + " " + gap + " " + bar.getShort()+ " "+ amountFull);
			//System.out.println("MCG60 " + amountFull + " " + bar.getLong());
			//  ps.add(new CustomCometGradientPaint(com,0,bar.getLong(),amountFull, darkColor,lightColor,speed,(float)((r-1) * 1.0/row),(float)(r*1.0/row)));
			//ps.add(new CustomCometGradientPaint(com,amountFull, darkColor,lightColor,(float)((r-1) * 1.0/row),(float)(r*1.0/row)));
			ps.add(new CustomBoingCometGradientPaint(com,0, darkColor,lightColor,(float)((r-1) * 1.0/row),(float)(r*1.0/row)));
		}


		int j = 0;
		while (j < ps.size()){
			Paint p = ps.get(j).getPaint(speed);
			if (p == null) ps.remove(j);
			else {
				g2.setPaint(p);
				g2.fill(bar.getRectangle());  
				j++;
			}
		}


		/*
 g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

 g2.setColor(lightColor);
 g2.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f));      
 for (int i = 0; i < ps.size()-1; i++){
	   	int x1 = (int)ps.get(i).getX();
	   	int x2 = (int)ps.get(i+1).getX();
	   	if (Math.abs(x2-x1)<gap*2)
	   		g2.drawLine(x1, (int)ps.get(i).getY(),x2 ,(int)ps.get(i+1).getY());
 }

 //g2.setPaintMode();
     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_OFF);
		 */  
	}




	@Override
	public void dimensionActived() {
		setDefaultMagnitude(bar.getShort()/7);
		if (!gapSet) gap = magnitude;

		//if (!radiusSet) radius = (int) (bar.getShort()/10.0);			
	}

	public void setGap(int gap) {
		if (gap < 1) gap = 1;
		this.gap = gap;
		gapSet = true;

	}
	/*
			public void setRadius(int rad) {
				radiusSet = true;
				radius = rad;
			}

			protected float getRadius() {
				return radius;
			}
	 */

	public void setIllusionDirection(int direction) {
		super.setIllusionDirection(direction);
		for (int i = 0; i < ps.size(); i++) {
			ps.get(i).setIllusionDirection(direction);
		}

	}


	public void setMagnitude(int m){
		super.setMagnitude(m);
		for (int i = 0; i < ps.size(); i++) {
			ps.get(i).setMagnitude(m);
		}	    	
	}


	public boolean setDefaultMagnitude(int m){
		if (super.setDefaultMagnitude(m)){ 
			for (int i = 0; i < ps.size(); i++) 
				ps.get(i).setMagnitude(m);
			return true;
		}		   
		return false;
	}

}








