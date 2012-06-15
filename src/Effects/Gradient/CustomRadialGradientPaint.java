package Effects.Gradient;

import java.awt.Color;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;

public class CustomRadialGradientPaint {

	private Point2D center;
	private float radius;
	private float[] dists;
	private Color[] colors;
	private CycleMethod cycle;
	private boolean reverse = false;
	public double x;
	public double y;
	private float incr = -1;
	private double toCollapseDecr = -1;
	private float endRadius;
	private boolean collapse = false;
	
	/*
	public CustomRadialGradientPaint(Point2D center,float radius, float[] dists, Color[] colors,CycleMethod cycle){
		this(center, radius, dists, colors, cycle, false, 0,(int)radius);
	}
	*/
	public CustomRadialGradientPaint(Point2D center,float iniRadius, float[] dists, Color[] colors,CycleMethod cycle,boolean reverse,int total,double gap,float endRadius) {
		this.center = center;
		x = center.getX();
		y = center.getY();
		this.radius = iniRadius;
		this.endRadius = endRadius;
		this.dists = dists;
		this.colors = colors;
		this.cycle = cycle;
		this.reverse = reverse;
		
		incr = (endRadius - iniRadius)/total*2;  
		toCollapseDecr = incr*2;						
	}

	public Paint getPaint(){
		return new RadialGradientPaint(center, radius,dists, colors,cycle);
	}
	
	public Paint getPaint(float radius){
		this.radius = radius;
		return new RadialGradientPaint(center, radius,dists, colors,cycle);
	}

	public Paint getPaintIncr(float r){
		if (collapse) return collapse();		
		if (reverse && radius >= endRadius) collapse = true;
		radius += r;
		return new RadialGradientPaint(center, radius,dists, colors,cycle);
	}


	public Paint getPaintIncr(){
		return getPaintIncr(incr);
	}
	
	
	private Paint collapse(){
		/*
		if (toCollapseDecr < 0) {
			toCollapseDecr = radius/(collapseTime -radius);
			System.out.println(myid + ": " + radius  + " " + collapseTime  + " " + toCollapseDecr);
			}*/
		//System.out.println(myid + ": " + radius  + " " +  " " + toCollapseDecr);
		radius -= toCollapseDecr;
		if (radius <=2) {
			//System.out.println(myid + ": deleted Total: " + id + " diff "  + (id-myid));
			return new Color(0,0,0,0);
		}
		return new RadialGradientPaint(center, radius,dists, colors,cycle);
	}
	
	public Paint getPaintDecr(float r){
		radius -= r;
		if (radius <=2) return new Color(0,0,0,0);
		return new RadialGradientPaint(center, radius,dists, colors,cycle);
	}
	
	public boolean isEmpty(){
		return (radius <= 0); 
	}

	public boolean isReverse() {
		return reverse;
	}

	public void setReverse(boolean active) {
		reverse = active;
	}
	
	
}
