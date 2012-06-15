package Effects.Gradient;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.Paint;
import java.awt.RadialGradientPaint;

import Effect.EffectProgressBarCoord;

public class SonarGradient extends CustomizableGradient {

	
	public CycleMethod cycle = CycleMethod.NO_CYCLE;


	public void init(EffectProgressBarCoord bar){
		super.init(bar);
		setColorDistList(new ColorDist[]{
				new ColorDist(ColorDist.DARK,0.0f),
				new ColorDist(ColorDist.LIGHT,0.6f),
				new ColorDist(ColorDist.FLARE,0.8f),
				new ColorDist(ColorDist.DARK,1.0f)
				});	
	}
	
	
	
	public Paint getGradient(int offset,  Color darkColor, Color lightColor) {
		if (direction == 1) 	
			return new RadialGradientPaint(getPoint(0, bar.getShort()/2), offset*1.3f + 1 , getDists(), getColors(lightColor, darkColor),getCycleMethod());
		return new RadialGradientPaint(getPoint(bar.getLong(), bar.getShort()/2), (bar.getLong()-offset)*1.3f + 1 , getDists(), getColors(lightColor, darkColor),getCycleMethod());				
	}

	
	@Override
	public void paintGradientColor(Graphics2D g2, int amountFull,float speed, Color darkColor, Color lightColor){
		double offset = incrXcoord(direction*speed);
        g2.setClip(bar.getClipShape(amountFull));
        g2.setPaint(getGradient((int) offset, darkColor, lightColor));
        g2.fill(bar.getRectangle());        
	}
	
	
	protected CycleMethod getCycleMethod(){
		return cycle;
	}
	

}













	