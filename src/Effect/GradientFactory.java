package Effect;

import java.awt.Color;
import java.awt.Graphics2D;

import Effects.Gradient.CometGradient;
import Effects.Gradient.CustomizableGradient;

public class GradientFactory {

	
    public static final Color DARKDEFAULT = new Color(48,143,252,100);
    public static final Color LIGHTDEFAULT = new Color(145,217,255,100);
    private Color[] darkColors = { DARKDEFAULT };
    private Color[] lightColors = { LIGHTDEFAULT };
    
    private Color darkColor = DARKDEFAULT;
    private Color lightColor = LIGHTDEFAULT;
    
    private int colorIndex = 0;
	private double colorAcceleration = 0.5;
    
    
	private double pass = 0;    
    private EffectProgressBarCoord bar;
    
    private CustomizableGradient gradient;
    
    
	public GradientFactory(EffectProgressBarCoord bar){
		this.bar = bar;
	//	gradient = new ArrowGradient();
//		gradient = new LinearGradient();
	//	gradient = new RadialGradient();
		gradient = new CometGradient();
	//	gradient = new BoingCometGradient();
	//	gradient = new SonarGradient();
	//	gradient = new ExplosionSonarGradient();
	//	gradient = new DoubleSonarGradient();
	//	gradient = new MultiRadialGradient();
		gradient.init(bar);
	}

	protected  void paintGradient(Graphics2D g2, int amountFull,float speed) {
		selectColor();		
		gradient.paintGradientColor(g2, amountFull, speed,darkColor,lightColor);
	}
	
	private void selectColor(){
		if (darkColors.length != lightColors.length) return;

		darkColor = darkColors[colorIndex];
		lightColor = lightColors[colorIndex];
		if (darkColors.length == 1) return;
		
		int next = (colorIndex + 1)%lightColors.length; 
		darkColor = getMediumColor(darkColors[colorIndex], darkColors[next],bar.getLong(),pass);
		lightColor = getMediumColor(lightColors[colorIndex], lightColors[next],bar.getLong(),pass);
		pass += (colorAcceleration*bar.getLong())/100;    		
		if (pass > bar.getLong()) {
			pass = 0;
			colorIndex = (colorIndex + 1)%(lightColors.length);
		}		
	}
	
    
    public static Color getMediumColor(Color c1, Color c2, int totalPass,double incr){
    	double p = incr * (1.0/totalPass);
    	double p1 = 1-p;
    	double p2 = p;
    	int r = (int) (c1.getRed() * p1 + c2.getRed() * p2);
    	int g = (int) (c1.getGreen() * p1 + c2.getGreen() * p2);
    	int b = (int) (c1.getBlue() * p1 + c2.getBlue() * p2);
    	int a = (int) (c1.getAlpha() * p1 + c2.getAlpha() * p2);
    	return new Color(r,g,b,a);
    }

    
    public int getColorAccelerationPercent(){
    	return (int) (colorAcceleration*100);
    }    

    public void setColorAccelerationPercent(int percent){
    	colorAcceleration = percent/100.0;
    }    
    
       
    public void setLightAndDarkColor(Color light,Color dark){
    	this.lightColors = new Color[]{light};
    	this.darkColors = new Color[]{dark};
    	colorIndex = 0;
    }
        

    public void setLightAndDarkColors(Color[] lights,Color[] darks){
    	if (lights.length != darks.length) throw new ArrayIndexOutOfBoundsException("Lights.lenght != Darks.length " + lights.length + "!=" + darks.length);    	
    	lightColors = lights;
    	darkColors = darks;
    	colorIndex = 0;
    }

    public void setDarkLightColors(Color[] c){    
    	Color[] dark = new Color[c.length];
    	Color[] light = new Color[c.length];
    	for (int i = 0; i < c.length; i++) {
    		dark[i] = c[i].darker();
    		light[i] = c[i].brighter();
		}
    	this.darkColors = dark;
    	this.lightColors = light;
    }   

    
    
    public Color[] getDarkColors(){
    	return darkColors;
    }

    public Color[] getLightColors(){
    	return lightColors;
    }
    

    public void setMagnitude (int m){
    	gradient.setMagnitude(m);
    }

    public void setGrad(int grad){
    	gradient.setGrad(grad);
    }

	public void setIllusionDirection(int direction) {
		gradient.setIllusionDirection(direction);
		
	}
	
    public void setGradient(CustomizableGradient cg){
    	cg.init(bar);    	
    	gradient = cg;
    }
    
    public CustomizableGradient getGradient(){
    	return gradient;
    }
    
    /*
    public void setDefaultGradient(int[] gradient){
    	setGrad(gradient[0]);
    	setMagnitude(gradient[1]);
    }
    
  */  
		
    
}
