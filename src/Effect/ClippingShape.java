package Effect;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.SwingConstants;

public class ClippingShape implements DimensionListener{

	private Area shape;
	private Area borderShape;
	private EffectProgressBarCoord bar;

	
	public ClippingShape(EffectProgressBarCoord bar){
		this.bar = bar;
		bar.addDimensionListener(this);
	}

	public void init(){
		shape = getBarSchape(bar.left,bar.top,bar.getHeight(),bar.getWidth());
		borderShape = initBorderShape(bar.borderThickness,bar.borderOverlap);
	}
	
	public Area initBorderShape(int border,int overlap){
		Area max = getBarSchape(bar.left,bar.top,bar.getHeight(),bar.getWidth(),border);
		Area min = getBarSchape(bar.left,bar.top,bar.getHeight(),bar.getWidth(),-overlap);		
		max.subtract(min);
		return max;
	}	
	
	public Area getBarSchape(int x,int y,int height,int width,int border){
		 return getBarSchape(x-border,y-border,height+border*2,width+border*2);
	}
	
	public Area getBarSchape(int x,int y,int height,int width){
		RoundRectangle2D.Double circle1;
		Rectangle2D.Double rectangle;
		RoundRectangle2D.Double circle2;
		
		if (bar.getOrientation() == SwingConstants.VERTICAL){
			circle1 = new RoundRectangle2D.Double(x,y,width,width,width*bar.roundWidth,width*bar.roundHeight);
			rectangle = new Rectangle2D.Double(x,y + width/2,width,height-width);
			circle2 = new RoundRectangle2D.Double(x,y + height - width,width,width,width*bar.roundWidth,width*bar.roundHeight);		
		} else {
			circle1 = new RoundRectangle2D.Double(x,y,height,height,height*bar.roundWidth,height*bar.roundHeight);
			rectangle = new Rectangle2D.Double(x + height/2,y,width-height,height);
			circle2 = new RoundRectangle2D.Double(x + width - height ,y,height,height,height*bar.roundWidth,height*bar.roundHeight);			
		}
		
		Area c1 = new Area(circle1);
		Area c2 = new Area(circle2);
		Area r = new Area(rectangle);
		Area s = new Area(c1);
		s.add(r);
		s.add(c2);
		return s;
	}

	public Shape getClipShape(int ammount){
		Rectangle2D.Double rec = null;
		if (bar.direction == BarConstant.LEFT_TO_RIGHT)
			rec = new Rectangle2D.Double(bar.left + horizontalization(ammount),bar.top  + verticalization(ammount),bar.getWidth(),bar.getHeight());
		else rec = new Rectangle2D.Double(bar.left,bar.top,bar.getWidth() - horizontalization(ammount),bar.getHeight() - verticalization(ammount));		
		Area r = new Area(rec);
		Area clip = new Area();
		clip.add(shape);
		clip.subtract(r);
		return clip;
	}
	
	public Shape getClipShadow(){
		return getClipShadow(bar.getHeight()/2);
	}
	
	
	public Shape getClipShadow(double ammount){
		Rectangle2D.Double rec = new Rectangle2D.Double(bar.left + horizontalization(ammount),bar.top  + verticalization(ammount),bar.getWidth(),bar.getHeight());		
		Area r = new Area(rec);
		Area clip = new Area();
		clip.add(shape);
		clip.subtract(r);
		return clip;
	}
	
	
	public Shape getBorderShape(){
		return borderShape;
	}
	

	public int horizontalization(int x) {
		return (bar.getOrientation() == SwingConstants.HORIZONTAL) ? x : 0;
	}	

	public int verticalization(int x) {
		return (bar.getOrientation() == SwingConstants.VERTICAL) ? x : 0;
	}	

	public double horizontalization(double x) {
		return (bar.getOrientation() == SwingConstants.HORIZONTAL) ? x : 0;
	}	

	public double verticalization(double x) {
		return (bar.getOrientation() == SwingConstants.VERTICAL) ? x : 0;
	}



	@Override
	public void dimensionActived() {
		init();
	}	
	
}
