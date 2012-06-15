package Effects.Gradient;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.Paint;
import java.awt.geom.Point2D;

public class LinearGradient extends CustomizableGradient {


	public Paint getGradient(int offset,  Color darkColor, Color lightColor) {
			//System.out.println(offset + " "  + bar.getWidth());
	    	Point2D start = getPoint(0 + offset,0);
	    	Point2D end = getPoint(xDegree*getMagnitude() + offset,yDegree*getMagnitude());
	        return new LinearGradientPaint(start, end,getDists(), getColors(lightColor, darkColor),CycleMethod.REFLECT);
	}

	@Override
	public void paintGradientColor(Graphics2D g2, int amountFull,float speed, Color darkColor, Color lightColor){
		double offset = incrXcoord(direction*speed);
        g2.setClip(bar.getClipShape(amountFull));
        g2.setPaint(getGradient((int) offset, darkColor, lightColor));
        g2.fill(bar.getRectangle());
	}

	
	
}
