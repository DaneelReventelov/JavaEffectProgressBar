package Effects.Gradient;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;

import Effect.DimensionListener;
import Effect.EffectProgressBarCoord;

public class CometGradient extends CustomizableGradient implements DimensionListener{

	private float high = 0.5f;

	
	public void init(EffectProgressBarCoord bar){
		super.init(bar);
		setMagnitudeExtension(true);
		bar.addDimensionListener(this);
	}

	
	protected Point2D getFocusPoint(double x){			
		return getPoint((float)(x + xDegree * bar.getShort()),yCoord); 				
	}
	

	protected Point2D getCenterPoint(double x){
		return getPoint(x, yCoord);	
	}		

		
	public Paint getGradient(float speed,  Color darkColor, Color lightColor) {
		double x = incrXcoord(direction*speed);
		///System.out.println("CG64 " + xCoord + " " + (direction*speed));
		return new RadialGradientPaint(getCenterPoint(x), magnitude,getFocusPoint(x),
				getDists(), getColors(lightColor, darkColor),CycleMethod.NO_CYCLE);				
	}
	
	@Override
	public void paintGradientColor(Graphics2D g2, int amountFull,float speed, Color darkColor, Color lightColor){
        g2.setClip(bar.getClipShape(amountFull));
        g2.setPaint(getGradient(speed, darkColor, lightColor));
        g2.fill(bar.getRectangle());        
	}
	
	
    public void setGrad(int grads){}

	@Override
	public void dimensionActived() {
		setDefaultMagnitude(bar.getShort());
		setDegree();
		yCoord = bar.getShort()*high;
		//Coord = bar.getShort()2;
		setOffsetCoord(bar.getShort()/2);
		
	}
	

	
	private void setDegree(){
		int d = 0;
		if (bar.isVertical()) d = 90;
		if (direction == CometGradient.RIGHT_TO_LEFT) d += 180;
		super.setGrad(d);		
	}
    
	
	public void setIllusionDirection(int direction) {
		super.setIllusionDirection(direction);
		if (bar != null) setDegree();
	}
	
	
	public void setCometEightPercent(int percent){
		high = (float) (percent/100.0);
		yCoord = bar.getShort()*high;
	}


	public void setRandomCometEight(){
		setCometEightPercent((int) (100*Math.random()));
	}	
	
	public int getCometEight(){
		return (int) (high*bar.getShort());
	}	
	 
		
	
	
}













	