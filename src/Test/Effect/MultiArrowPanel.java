package Test.Effect;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Effects.Gradient.MultipleArrowGradient;
import Test.ProgressPanel;

@SuppressWarnings("serial")
public class MultiArrowPanel extends GradientPanel{

	private Spinner spin;
	
	public MultiArrowPanel(ArrayList<ProgressPanel> list, String title,MultipleArrowGradient cg) {
		super(list, title, cg);
		spin = new Spinner(1);
		spin.setActionListener(this);		
		JPanel p = new JPanel(new FlowLayout(FlowLayout.LEADING));
		p.add(new JLabel("Arrow numbers"));
		p.add(spin);
		addSpecificPanel(p);
	}

	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == spin) setArrowNum(spin.getVal());
		else super.actionPerformed(e);
	}		

	public void setArrowNum(int num){
		((MultipleArrowGradient)gradient).setArrowNum(num);
		if (!(list.get(0).getIllusionUI().getGradientFactory().getGradient() instanceof MultipleArrowGradient)) return;
		for (int i = 0; i < list.size(); i++)
			((MultipleArrowGradient)(list.get(i).getIllusionUI().getGradientFactory().getGradient())).setArrowNum(num);
	}	
	

}
