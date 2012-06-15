package Test.Effect;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Effects.Gradient.CometGradient;
import Test.LabeledSlider;
import Test.ProgressPanel;

@SuppressWarnings("serial")
public class CometPanel extends GradientPanel implements ChangeListener {

		private LabeledSlider slide;
		
		public CometPanel(ArrayList<ProgressPanel> list, String title,CometGradient cg) {
			super(list, title, cg);
			slide = new LabeledSlider(100, 1, 50, "Comet", 150, 100, this);
			JPanel p = new JPanel(new FlowLayout(FlowLayout.LEADING));
			p.add(new JLabel("Comet eight"));
			p.add(slide);
			addSpecificPanel(p);
		}

		

		public void setCometEight(int num){
			((CometGradient)gradient).setCometEightPercent(num);
			if (!(list.get(0).getIllusionUI().getGradientFactory().getGradient() instanceof CometGradient)) return;
			for (int i = 0; i < list.size(); i++)
				((CometGradient)(list.get(i).getIllusionUI().getGradientFactory().getGradient())).setCometEightPercent(num);
		}


		@Override
		public void stateChanged(ChangeEvent e) {
			if (e.getSource() == slide) setCometEight(slide.getValue());
			else super.stateChanged(e);
		}	
	
}
