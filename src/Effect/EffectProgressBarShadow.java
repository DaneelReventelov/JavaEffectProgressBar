package Effect;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.geom.Point2D;


public class EffectProgressBarShadow {

	public static final int DEFAULTShadowLightAlpha = 255;
    public static final int DEFAULTShadowDarkAlpha = 0;
    
   	private Color shadowDark = new Color(0,0,0,255);
   	private Color shadowLight = new Color(0,0,0,0);
   	private double shadowStartGapFromMiddlePercent = 0;
   	private double shadowEndFromBottomPercent = 0.02;
   	private EffectProgressBarCoord bar = null;

   	
   	public EffectProgressBarShadow(EffectProgressBarCoord bar){
   		this.bar = bar;
   	}

   	
    protected GradientPaint getShadowGradient() {
       	return new GradientPaint(
       					new Point2D.Double(0,bar.getHeight()/2+(bar.getHeight()/2)*shadowStartGapFromMiddlePercent), shadowLight,
       					new Point2D.Double(0,bar.getHeight() - (bar.getHeight()/2)*shadowEndFromBottomPercent), shadowDark);
    }   


	public Color getShadowDark() {
		return shadowDark;
	}


	public void setShadowDark(Color shadowDark) {
			this.shadowDark = shadowDark;				
	}


	public Color getShadowLight() {
		return shadowLight;
	}


	public void setShadowLight(Color shadowLight) {
		this.shadowLight = shadowLight;
	}


	public double getShadowStartGapFromMiddlePercent() {
		return shadowStartGapFromMiddlePercent;
	}


	public void setShadowStartGapFromMiddlePercent(double percent) {
		if (percent<0 || percent>1) throw new NumberFormatException("Value must be from 0 to 1");
		this.shadowStartGapFromMiddlePercent = percent;
	}


	public double getShadowEndFromBottomPercent() {
		return shadowEndFromBottomPercent;
	}


	public void setShadowEndFromBottomPercent(double percent) {
		if (percent<0 || percent>1) throw new NumberFormatException("Value must be from 0 to 1");
		this.shadowEndFromBottomPercent = percent;
	}
    

}
