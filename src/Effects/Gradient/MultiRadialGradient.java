package Effects.Gradient;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import Effect.DimensionListener;
import Effect.EffectProgressBarCoord;

public class MultiRadialGradient extends CustomizableGradient implements DimensionListener {

	
	private boolean reset = true;
	private boolean background = true;
	private int gap = 100;
	private int newGap = 0;
	private boolean gapSet = false;
	private ArrayList<CustomRadialGradientPaint> ps = new ArrayList<>();
	

	public void init(EffectProgressBarCoord bar){
		super.init(bar);
		bar.addDimensionListener(this);
		setColorDistList(new ColorDist[]{
				new ColorDist(ColorDist.DARK,0.0f,100),
				new ColorDist(ColorDist.LIGHT,0.6f,100),
				new ColorDist(ColorDist.FLARE,0.8f,255),
				new ColorDist(ColorDist.DARK,0.9f,100),
				new ColorDist(ColorDist.TRASPARENT,1.0f)
				});		
	}
	
	public void addGradient(int offset,  Color darkColor, Color lightColor) {	
		//Point2D center = getPoint((float) ((bar.getLong() + direction*offset)%bar.getLong()), (float) (bar.getShort()*Math.random()));
		Point2D center = getPoint(offset, (float) (bar.getShort()*Math.random()));				
		ps.add(new CustomRadialGradientPaint(center, 1,getDists(), getColors(lightColor, darkColor),CycleMethod.NO_CYCLE,reset, bar.getLong(),gap,bar.getShort()+10));
	}

	
	
	
	@Override
	public synchronized void paintGradientColor(Graphics2D g2, int amountFull,float speed, Color darkColor, Color lightColor){
	 double offset = incrXcoord(direction*speed);
     g2.setClip(bar.getClipShape(amountFull));
      if (background) {
      	g2.setPaint(lightColor);
      	g2.fill(bar.getRectangle());    	  
      }

  	  newGap--;  	  
  	  if (newGap < 0){
  		  newGap = gap;
  		  addGradient((int) offset, darkColor, lightColor);
  	  }


      for (int i = 0; i < ps.size(); i++) {
  		//g2.setPaint(ps.get(i).getPaintIncr(0.3f));
    	  g2.setPaint(ps.get(i).getPaintIncr());
  	  	  g2.fill(bar.getRectangle());
      }
      
      int j = 0;
      while (j < ps.size()) 
  	  	if (ps.get(j).isEmpty()) ps.remove(j);
  	  	else j++;
      
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
      //g2.setXORMode(darkColor);
	  //g2.setColor(addAlpha(lightColor, 125));
      //g2.setColor(addAlpha(lightColor, 150));
      //g2.setColor(addAlpha(darkColor, 150));
      g2.setColor(lightColor);
	  g2.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f));      
      for (int i = 0; i < ps.size()-1; i++)     	  
    		g2.drawLine((int)ps.get(i).x, (int)ps.get(i).y, (int)ps.get(i+1).x,(int)ps.get(i+1).y);
      //g2.setPaintMode();
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_OFF);
	}



	@Override
	public void dimensionActived() {
		if (gapSet) return;		
		gap = (int) (bar.getLong()/bar.getShort()*20);
		
	}
	
	
	public void setGap(int gap) {
		if (gap < 1) gap = 1;
		this.gap = gap;
		gapSet = true;
	}
}













	