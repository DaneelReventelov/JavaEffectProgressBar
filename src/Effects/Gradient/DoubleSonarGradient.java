package Effects.Gradient;

import java.awt.MultipleGradientPaint.CycleMethod;

public class DoubleSonarGradient extends SonarGradient{

	
	private CycleMethod cycle = CycleMethod.REFLECT;
	
	
	protected CycleMethod getCycleMethod(){
		return cycle;
	}
	
}
