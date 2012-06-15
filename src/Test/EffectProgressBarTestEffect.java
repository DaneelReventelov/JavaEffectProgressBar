package Test;
import java.util.ArrayList;

import javax.swing.JTabbedPane;

import Test.Effect.EffectsPanel;

@SuppressWarnings("serial")
public class EffectProgressBarTestEffect extends JTabbedPane {
	

	public EffectProgressBarTestEffect(ArrayList<ProgressPanel> list){
		super();
		addTab("Shadow", new ShadowPanel(list));
		addTab("Colors", new ColorsPanel(list));
		addTab("Effects", new EffectsPanel(list));
	}

	
}
