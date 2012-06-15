package Effects.Gradient;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import javax.swing.JProgressBar;

import Effect.BarConstant;
import Effect.EffectProgressBarCoord;

public abstract class CustomizableGradient extends BarConstant {

	private boolean extension = false;
	private int extent;
	protected int ini;
	protected int end;
	protected boolean modular = true;
	
	private boolean shortExtension = false;
	private float shortExtensionValue = 1;
	private int shortExtent;
	private int shortIncrDirection = 1;
	protected int shortIni;
	protected int shortEnd;
	protected boolean shortModular = false;
	protected boolean shortSpecular = false;
	
	

	
	protected int magnitude  = 10;
	protected boolean magnitudeSet = false;
	
	private double xCoord;
	protected double yCoord;	
	
	protected boolean degreeSet = false;
	protected double xDegree = 1;
	protected double yDegree = 1;
	private int grad = 45;
	
	
	private ColorDistList cds = new ColorDistList(new ColorDist[]{
			new ColorDist(ColorDist.FLARE,0.0f),
			new ColorDist(ColorDist.LIGHT,0.3f),
			new ColorDist(ColorDist.DARK,0.7f)
			});
	
	private boolean flare = true;
	
	//private boolean valueChanged = false;

	
    //protected int direction = RIGHT_TO_LEFT;
	protected int direction = LEFT_TO_RIGHT;
	
    public int BARHEIGHT = -1;
    public int BARWIDTH = -1;
    
	protected EffectProgressBarCoord bar;
	
	
	public CustomizableGradient(){}
	
    public CustomizableGradient(EffectProgressBarCoord bar){
    	init(bar);
    }
    
    public void init(EffectProgressBarCoord bar){
    	this.bar = bar;    	
    	bar.addGradientBase(this);
    }
        
    public void calcExtension(){    	
   		extent = bar.getLong();
   		ini = 0;
   		end = bar.getLong();
   		xCoord = bar.getLong()/2;
   		if (extension){
   			extent += magnitude*2;
   			ini = - magnitude;
   			end += magnitude; 
   		}
    }    

	public void setMagnitudeShortExtension(float f){
		shortExtension = true;
		shortExtensionValue = f;
	}
    

	public void setShortModular(boolean b){
		shortModular = b;
	}

	
	public void setShortSpecular(boolean b){
		shortSpecular = b;
	}
	
    public void calcShortExtension(){
   		shortExtent = bar.getShort();
   		shortIni = 0;
   		shortEnd = bar.getShort();
   		//xCoord = bar.getLong()/2;
   		if (shortExtension){
   			shortExtent += magnitude*shortExtensionValue*2;
   			shortIni = (int) (- magnitude*shortExtensionValue);
   			shortEnd += magnitude*shortExtensionValue;
   		}
    }   
    
	//public abstract Paint getGradient(int offset,int center);

	public abstract void paintGradientColor(Graphics2D g2, int amountFull,float speed, Color darkColor, Color lightColor);

	public void setOffsetCoord(int coord){				
		xCoord = (extent - magnitude) * (coord/bar.getLong());
	}	
	
	public void setMagnitudeExtension(boolean b){
		extension = true;
	}
	
    public void setModularRepetion(boolean m){
    	modular = m;    
    }	
	
    public void setMagnitude(int m){
    	if (m == 0) throw new IndexOutOfBoundsException("Magnitude must be > 0");
    	magnitude = m;
    	magnitudeSet = true;
    	calcExtension();
    	calcShortExtension();   	    
    }
	    
    public void setGrad(int grads){
    	grad = grads;
    	xDegree = Math.cos((grad * 3.14159)/180);
    	yDegree = Math.sin((grad * 3.14159)/180);
    	degreeSet = true;
    }

    
    protected boolean setDefaultMagnitude(int m){
    	if (magnitudeSet) return false;
    	magnitude = m;
    	return true;
    }    
    
    protected boolean setDefaultGrad(int grads){ 	
    	if (degreeSet) return false;
    	grad = grads;
    	return true;
    }
    


    protected double incrXcoord(double incr){
    	xCoord += incr;
    	if (modular) {
    		if (xCoord < ini) xCoord = end;
    		else if (xCoord > end) xCoord = ini;
    	}
    	return xCoord;
    }

    
    protected double incrYcoord(double incr){
    	yCoord += shortIncrDirection * incr;
    	if (shortModular) {
    		if (yCoord < shortIni) yCoord = shortEnd;
    		else if (yCoord > shortEnd) yCoord = shortIni;
    	}
    	else if ((shortSpecular) && ((yCoord < shortIni) || (yCoord > shortEnd))) 
    		shortIncrDirection *=-1;
    	return yCoord;
    }
    
    
    
    
	public boolean isOutOfRange(){
		if (modular) return false;
		return ((xCoord < ini) || xCoord> end);
	}

    
    public void setFlareLightPoint(boolean active){
    	flare = active;
    }

    public boolean isFlare(){
    	return flare;
    }


	public void setIllusionDirection(int direction) {
		this.direction = direction;  
	}
    
	protected float[] getDists(){
		return cds.getDists(isFlare());
	}	
	
	protected Color[] getColors(Color lightColor,Color darkColor){
		return cds.getColors(isFlare(),Color.WHITE,lightColor,darkColor);
	}

	protected void setColorDistList(ColorDist[] cd){
		cds = new ColorDistList(cd);
	}

	protected void addColorDistList(ColorDist cd){
		cds.add(cd);
	}
	
	protected int ov(double horizontal, double vertical){
		return (int)((bar.isHorizontal()) ? horizontal : vertical);
	}
	
	protected Point2D getPoint(double x,double y){
		if (bar.getOrientation() == JProgressBar.HORIZONTAL) 
			return new Point2D.Double(x,y);
		else return new Point2D.Double(y,x);
		
	}
	
	protected Point2D getPoint(float x,float y){
		if (bar.getOrientation() == JProgressBar.HORIZONTAL) 
			return new Point2D.Double(x,y);
		else return new Point2D.Double(y,x);
		
	}
	
	protected double getExtension(int offset){
		return direction * bar.getShort() * (2.0 * offset/bar.getLong() - 1);
	}

	protected double getAdvance(int offset,float f){
		return direction * (bar.getLong()/offset)*f; 
	}
	
	protected Color addAlpha(Color c,int alpha){ 
		return new Color (c.getRed(), c.getGreen(), c.getBlue(),alpha);		
	}
			
	
	public CustomizableGradient getInstance() {
		try {
			CustomizableGradient cg = (CustomizableGradient)(this.getClass().newInstance());
//			cg.reset(xDegree,yDegree,grad,degreeSet,magnitude,magnitudeSet,cds,flare,direction,BARHEIGHT,BARWIDTH,bar,extension);
			return cg;
		} catch (InstantiationException | IllegalAccessException e) {return null;}
	}

	
	
	public void addTrasparentFinal(){ 
		cds.addTrasparentFinal(); 
	}

	
	/*
	protected void reset(double arg1, double arg2, int arg3,boolean arg4, int arg5, boolean arg6, ColorDistList arg7,
						boolean arg8,int arg9,int arg10, int arg11,IllusionProgressBarCoord arg12,boolean arg13){
		xDegree = arg1;
		yDegree = arg2;
		grad = arg3;
		degreeSet = arg4;
		magnitude  = arg5;
		magnitudeSet = arg6;
		cds = arg7;
		flare = arg8;
		direction = arg9;
		BARHEIGHT = arg10;
	    BARWIDTH = arg11;
	    bar = arg12;
	    extension = arg13;
	}
	
	*/
	
	
	public int getMagnitude(){
		return magnitude;
	}
	
	public int getDegree(){
		return grad; 
	}
	
	public int getDirection(){
		return direction;
	}
	
	
	public int getShortDirection(){
		return shortIncrDirection;
	}

	public void setShortDirection(int dir){		
		if (dir <= 0) shortIncrDirection = -1;
		else shortIncrDirection = 1;
	}
	
}

