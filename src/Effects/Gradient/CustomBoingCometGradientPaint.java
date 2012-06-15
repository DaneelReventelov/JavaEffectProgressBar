package Effects.Gradient;

import java.awt.Color;
import java.awt.Paint;


public class CustomBoingCometGradientPaint {
	
	private Color dark;
	private Color light;
//	private int ini;
//	private int end;
	public CometGradient cg;
	
	//public CustomCometGradientPaint(CometGradient cg, int ini, int end,int pin,Color dark,Color light,int animationIndex,float intervalIniPerc,float intervalEndPerc) {

	public CustomBoingCometGradientPaint(BoingCometGradient cg,int pin,Color dark,Color light,float intervalIniPerc,float intervalEndPerc) {
		this.cg = cg;
		cg.setOffsetCoord(pin);
		//cg.setOffsetCoord(0);
		this.dark = dark;
		this.light = light;

		setHeight(intervalIniPerc, intervalEndPerc);
		
		cg.setShortDirection((int)((Math.random()-0.5)*10));
		cg.addTrasparentFinal();
		cg.setModularRepetion(false);
		
	}
	
	
	public void setHeight(float ini,float end){
		cg.setCometEightPercent((int) ((ini + (end-ini)*Math.random())*100));	
	}
	
	public Paint getPaint(float speed){		
		if (cg.isOutOfRange()) return null;			
		return cg.getGradient(speed, dark, light);
	}

	

	public void setIllusionDirection(int direction) {
		cg.setIllusionDirection(direction);
	}



	
    public void setMagnitude(int m){
    	cg.setMagnitude(m);
    }
	
}
