package Effects.Gradient;

import java.awt.geom.Point2D;

import Effect.EffectProgressBarCoord;

public class BoingCometGradient extends CometGradient {
		
	//private int hdirection = 1;
	private float multiplier = 1;		          
	//private float frontier = 1.05f;
	//private float h = 0;
	//private int distance = 50;
	private int distance = 200;
	private double[] prevY;
	private int index = 0;
	private double speedEffectPercent = 0.5;
	private double boingFactorPercent = 0.05;

	public void init(EffectProgressBarCoord bar){
		setMagnitudeExtension(true);
		//setMagnitudeShortExtension(4f);
		setShortSpecular(true);
		super.init(bar);
		prevY = new double[distance];		
//		setModularRepetion(false);
	}
	
    /* 
	// with extent
	protected Point2D getCenterPoint(double x){					
		incrIndex();
		

					
		double mod = boingCollapse;
		
		if ((h > magnitude) || h < 0) {
			double bd = h - magnitude;
			if (h < 0) bd = - h; 		
			double ab = - shortIni;	
			double ad = ab - bd;
			double bc = boingCollapse;
			double be = ad*bc/ab;					
			mod = be;
		}
		
		return getPoint(x - getDirection() * mod, h - getShortDirection() * mod);
	}
	*/

	/*
	private boolean urto = false;
	protected Point2D getCenterPoint(double x){					
		incrIndex();
				
		double mod = magnitude * speedEffectPercent;
		double boingFactor = bar.getShort() * boingFactorPercent;
		
		if ((magnitude - h < boingFactor) || h < boingFactor) urto = true;
				
		if (urto) { 		
				double ab = magnitude-h;
				if (h < boingFactor) ab = h;
				double ac = magnitude * speedEffectPercent;
				double ae = ab*ac/boingFactor;					
				mod = ae;		
		}
		
		
		if ((magnitude - h >= magnitude * speedEffectPercent - 1) ||
			(h >= magnitude * speedEffectPercent - 1))
					urto = false;
		
		return getPoint(x - getDirection() * mod, h - getShortDirection() * mod);
	}
	*/

	
	
	private boolean urto = false;
	protected Point2D getCenterPoint(double x){					
		incrIndex();
				
		double mod = magnitude * speedEffectPercent;
		double boingFactor = bar.getShort() * boingFactorPercent;
		
		if ((shortEnd - h < boingFactor) || h < boingFactor) urto = true;
				
		if (urto) { 		
				double ab = shortEnd-h;
				if (h < boingFactor) ab = h;
				double ac = magnitude * speedEffectPercent;
				double ae = ab*ac/boingFactor;					
				mod = ae;		
		}
		
		
		if ((shortEnd - h >= shortEnd * speedEffectPercent - 1) ||
			(h >= shortEnd * speedEffectPercent - 1))
					urto = false;
		
		return getPoint(x - getDirection() * mod, h - getShortDirection() * mod);
	}
	
	
	double h = 0;
	protected Point2D getFocusPoint(double x){
		//h += hdirection * multiplier;		
		//if ((h > bar.getShort()*frontier) || (h < bar.getShort()*(1-frontier))) hdirection = -hdirection;
		h = incrYcoord(multiplier);
		prevY[getPreviusIndex()] = h;
		return getPoint(x, h);
	}


	public void setDistance(int d){
		distance = d;
		prevY = new double[distance];	
	}
	
	
	private int getPreviusIndex(){
		int p = index -1;
		if (p < 0) return distance - 1 ;
		return p;		
	}

	
	private int getnextIndex(){
		return (index+1)%distance;
	}
	

	private int incrIndex(){
		index = getnextIndex();
		return index;
	}

	
	public void setBoingSpeedPercent(int perc){
		multiplier = (float) (perc/100.0);
	}
	
	
	public void setBoingCollapsePercent(int perc){
		boingFactorPercent = (float) (perc/100.0);
	}	

	public void setSpeedEffectPercent(int perc){
		speedEffectPercent = (float) (perc/100.0);
	}	
	
	
}
