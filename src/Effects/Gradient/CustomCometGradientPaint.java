package Effects.Gradient;

import java.awt.Color;
import java.awt.Paint;


public class CustomCometGradientPaint {
	
	private Color dark;
	private Color light;
//	private int ini;
//	private int end;
	public CometGradient cg;
	
	//public CustomCometGradientPaint(CometGradient cg, int ini, int end,int pin,Color dark,Color light,int animationIndex,float intervalIniPerc,float intervalEndPerc) {

	public CustomCometGradientPaint(CometGradient cg,int pin,Color dark,Color light,float intervalIniPerc,float intervalEndPerc) {
		this.cg = cg;
		cg.setOffsetCoord(pin);
//		if (cg.direction == CustomizableGradient.RIGHT_TO_LEFT) offset = end-pin-cg.getMagnitude();
// 		else offset = end-10;
		 
		//System.out.println("ccgp22 " + offset + " " + ini + " " + cg.getMagnitude() + " " + cg.getXCoord(offset) +  " " + cg.coordToOffset(pin));
//		index = animationIndex;
		this.dark = dark;
		this.light = light;
//		this.ini = ini - cg.getMagnitude();
//		this.end = end + cg.getMagnitude();
		setHeight(intervalIniPerc, intervalEndPerc);
		cg.addTrasparentFinal();
		cg.setModularRepetion(false);
		//y = cg.getCometEight();
		
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
