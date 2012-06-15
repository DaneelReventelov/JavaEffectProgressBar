package Effects.Gradient;

import java.awt.geom.Point2D;

import Effect.DimensionListener;

public class RadialGradient extends CometGradient implements DimensionListener{

	
	protected Point2D getFocusPoint(double x){			
		return getPoint(x, yCoord);	
	}
}













	