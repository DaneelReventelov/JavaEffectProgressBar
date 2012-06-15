package Test.Effect;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Effects.Gradient.BoingCometGradient;
import Effects.Gradient.CometGradient;
import Effects.Gradient.CustomizableGradient;
import Effects.Gradient.DoubleSonarGradient;
import Effects.Gradient.ExplosionSonarGradient;
import Effects.Gradient.LinearGradient;
import Effects.Gradient.MultiCometGradient;
import Effects.Gradient.MultiRadialGradient;
import Effects.Gradient.MultipleArrowGradient;
import Effects.Gradient.MultipleBoingCometGradient;
import Effects.Gradient.RadialGradient;
import Effects.Gradient.SonarGradient;
import Test.ProgressPanel;

@SuppressWarnings("serial")
public class EffectsPanel extends JPanel implements ListSelectionListener, MouseListener{
	
	
	private String[] gradients;
	private GradientPanel[] gradientsPanel;
	private JList<String> effects;
	private JPanel cards;
	
	public EffectsPanel(ArrayList<ProgressPanel> list){
		super();	
		setLayout(new BorderLayout());							
		add(getGradientPanels(list),BorderLayout.CENTER);
		add(getGradientList(),BorderLayout.WEST);
	}

	
	public JPanel getGradientPanels(ArrayList<ProgressPanel> list){
		cards = new JPanel(new CardLayout());
		gradients = new String[11];
		gradientsPanel = new GradientPanel[11];
		int i = 0;
		addCustomCards("MultipleArrow",i++, new MultiArrowPanel(list, "MultipleArrow", new MultipleArrowGradient()));
		addCustomCards("BoingComet",i++, new BoingCometPanel(list, "BoingComet", new BoingCometGradient()));
		addCustomCards("Comet",i++, new CometPanel(list, "Comet", new CometGradient()));
		addCards(list, "MultipleComet", new MultiCometGradient(),i++);
		addCards(list, "MultipleBoingComet", new MultipleBoingCometGradient(),i++);
		addCards(list, "Explosion", new ExplosionSonarGradient(),i++);
		addCards(list, "MultiRadial", new MultiRadialGradient(),i++);
		addCards(list, "DoubleSonar", new DoubleSonarGradient(),i++);
		addCards(list, "Linear", new LinearGradient(),i++);
		addCustomCards("Radial",i++, new CometPanel(list, "Radial", new RadialGradient()));
		addCards(list, "Sonar", new SonarGradient(),i++);
		return cards;
	}
	
	
	
	public void addCards(ArrayList<ProgressPanel> list, String type, CustomizableGradient cg, int index){		
		gradients[index] = type;
		gradientsPanel[index] = new GradientPanel(list, type,cg);
		cards.add(gradientsPanel[index], type);
	}

	public void addCustomCards(String type, int index,GradientPanel gp){		
		gradients[index] = type;
		gradientsPanel[index] = gp;
		cards.add(gradientsPanel[index], type);
	}
	
	
	public JComponent getGradientList(){ 
		effects = new JList<String>(gradients);
		effects.addListSelectionListener(this);
		effects.addMouseListener(this);
		effects.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		effects.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		effects.setVisibleRowCount(-1);
		JScrollPane listScroller = new JScrollPane(effects);
		listScroller.setPreferredSize(new Dimension(150, 50));
		listScroller.setBorder(getTitleBorder("Effects"));
		listScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		return listScroller;
	}
	
	
	public Border getTitleBorder(String title){
		Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder titleBorder = BorderFactory.createTitledBorder(loweredetched, title);
		titleBorder.setTitleJustification(TitledBorder.LEFT);
		return titleBorder;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()) return;
	    CardLayout cl = (CardLayout)(cards.getLayout());	    
	    cl.show(cards, gradients[((JList)(e.getSource())).getSelectedIndex()]);
	}


	@SuppressWarnings("rawtypes")
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() > 1)
			gradientsPanel[((JList)(e.getSource())).getSelectedIndex()].setEffect();		
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}



}
