package Effects.Gradient;

import java.awt.Color;


 
public class ColorDist {

	public static final int FLARE = 1;
	public static final int LIGHT = 2;
	public static final int DARK = 3;
	public static final int TRASPARENT = 4;	

	
	private int alpha;
	private int type;
	private float dist;
			
	
	public ColorDist(int type, float dist){
			this(type,dist,255);
	}

	public ColorDist(int type, float dist, int alpha){
		this.type = type;
		this.dist = dist;
		this.alpha = alpha;
	}
	
	
	public void setDist(float f){
		dist = f;
	}
	
	public float getDist(){
		return dist;
	}		
	
	public int getType(){
		return type;
	}		
	
	public boolean isType(int t){
		return (type == t);
	}		

	public boolean isFlare(){
		return (type == FLARE);
	}
	
	public boolean isLight(){
		return (type == LIGHT);
	}
	
	public boolean isDark(){
		return (type == DARK);
	}
	

	public boolean isTrasparent(){
		return (type == TRASPARENT);
	}

	public Color getColor(Color flareColor,Color lightColor,Color darkColor){
		if (isLight()) return getAlphaColor(lightColor);
		else if (isDark()) return getAlphaColor(darkColor);
		else if (isFlare()) return getAlphaColor(flareColor);
		else if (isTrasparent()) return new Color(0,0,0,0);
		return null;		
	}
	
	private Color getAlphaColor(Color c){
		if (alpha == 255) return c;
		return new Color(c.getRed(), c.getGreen(), c.getBlue(), alpha);
	}
	
}

