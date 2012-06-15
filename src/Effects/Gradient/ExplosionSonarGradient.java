package Effects.Gradient;

import java.awt.MultipleGradientPaint.CycleMethod;

public class ExplosionSonarGradient extends SonarGradient{

	protected CycleMethod cycle = CycleMethod.REPEAT;
	
	protected CycleMethod getCycleMethod(){
		return cycle;
	}
	
	
}
