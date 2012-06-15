package Effects.Gradient;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.Paint;
import java.awt.geom.Point2D;

import Effect.DimensionListener;
import Effect.EffectProgressBarCoord;

public class MultipleArrowGradient extends CustomizableGradient implements DimensionListener {


	private float[] interval = {0.0f,1.0f};

	public void init(EffectProgressBarCoord bar){
		super.init(bar);
		bar.addDimensionListener(this);		
	}
		

	public Paint getGradient(float offset, boolean reflect, Color darkColor, Color lightColor, float ini, float end) {
    	Point2D startP = getPoint(offset,bar.getShort()*((ini+end)/2));    	
    	Point2D endP = getPoint(xDegree*getMagnitude() + offset,bar.getShort()*ini);
    	if (reflect) endP = getPoint(xDegree*getMagnitude() + offset,bar.getShort()*end);
        return  new LinearGradientPaint(startP, endP, getDists(), getColors(lightColor, darkColor),CycleMethod.REFLECT);
	}

	public void paintGradientColor(Graphics2D g2, int amountFull,float speed, Color darkColor, Color lightColor){
		g2.setClip(bar.getClipShape(amountFull));
		float offset = (float) incrXcoord(direction*speed);
		for (int i = 0; i < interval.length-1; i++) {
			g2.setPaint(getGradient(offset,false, darkColor, lightColor,interval[i],interval[i+1]));
	        g2.fill(bar.getRectanglePartShort(interval[i],(interval[i]+interval[i+1])/2));
			g2.setPaint(getGradient(offset,true, darkColor, lightColor,interval[i],interval[i+1]));
	        g2.fill(bar.getRectanglePartShort((interval[i]+interval[i+1])/2,interval[i+1]));	        
		}				 
	}

	public void setArrowNum(int num) throws IndexOutOfBoundsException {	
		if (num < 1) throw new IndexOutOfBoundsException("Arrows number must be > 0");
		interval = new float[num + 1];	
		for (int i = 0; i < interval.length; i++) 
			interval[i] = i * 1.0f/num;
	}
	

	private void print(){
		for (int i = 0; i < interval.length; i++) 
			System.out.print(interval[i] + " ");
		System.out.println();
	}

	@Override
	public void dimensionActived() {
		setDefaultMagnitude(bar.getShort()/2);
	}



		

	
}
