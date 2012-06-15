package Test.Effect;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Effects.Gradient.BoingCometGradient;
import Test.LabeledSlider;
import Test.ProgressPanel;

@SuppressWarnings("serial")
public class BoingCometPanel extends GradientPanel implements ChangeListener {

		private LabeledSlider slide;
		
		public BoingCometPanel(ArrayList<ProgressPanel> list, String title,BoingCometGradient cg) {
			super(list, title, cg);
			slide = new LabeledSlider(1000, 1, 100, "Boing", 150, 100, this);
			JPanel p = new JPanel(new FlowLayout(FlowLayout.LEADING));
			p.add(new JLabel("Comet speed"));
			p.add(slide);
			addSpecificPanel(p);
		}

		

		public void setArrowNum(int num){
			((BoingCometGradient)gradient).setBoingSpeedPercent(num);
			if (!(list.get(0).getIllusionUI().getGradientFactory().getGradient() instanceof BoingCometGradient)) return;
			for (int i = 0; i < list.size(); i++)
				((BoingCometGradient)(list.get(i).getIllusionUI().getGradientFactory().getGradient())).setBoingSpeedPercent(num);
		}


		@Override
		public void stateChanged(ChangeEvent e) {
			if (e.getSource() == slide) setArrowNum(slide.getValue());
			else super.stateChanged(e);
		}	
	
}
