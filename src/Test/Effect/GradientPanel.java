package Test.Effect;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Effect.BarConstant;
import Effects.Gradient.CustomizableGradient;
import Test.LabeledSlider;
import Test.ProgressPanel;

@SuppressWarnings("serial")
public class GradientPanel extends JPanel implements ChangeListener, ActionListener{
	
	protected ArrayList<ProgressPanel> list;
	private JButton apply;
	protected CustomizableGradient gradient;
	private JToggleButton flare;
	private int magnitude;
	private int degree;
	private int direction;
	private boolean flared = true;
	
	public GradientPanel(ArrayList<ProgressPanel> list, String title, CustomizableGradient cg){
		this.gradient = cg;
		magnitude = cg.getMagnitude();
		degree = cg.getDegree();
		direction = cg.getDirection();				
		setLayout(new BorderLayout());
		setBorder(getTitleBorder(title));
		this.list = list;
		JPanel south = new JPanel(new FlowLayout(FlowLayout.LEADING));		
		apply = new JButton("Apply");
		apply.addActionListener(this);	
		south.add(apply);
		add(south, BorderLayout.SOUTH);
		add(getGeneralPanel(),BorderLayout.WEST);
	}
	
	
	
	public void addSpecificPanel(JPanel p){
		add(p,BorderLayout.CENTER);
	}
	
	public void addDefaultValuesPanel(JPanel p){
		add(p,BorderLayout.WEST);
	}
	
	public JPanel getGeneralPanel(){
		int width = 200;
		JPanel p = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();		
		p.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));		
		c.fill = GridBagConstraints.HORIZONTAL;		
		addGrid(0, 0, 1  , 1, new JLabel("Degrees"), p, c);
		addGrid(1, 0, 100, 1, new LabeledSlider(360,1,degree,"Degrees",width,30,this), p, c);
		addGrid(0, 1, 1  , 1, new JLabel("Magnitude"), p, c);
		addGrid(1, 1, 100, 1, new LabeledSlider(1,1000,1,magnitude,"Magnitude",width,30,this), p, c);
		addGrid(0, 2, 1  , 1, new JLabel("Direction"), p, c);
		addGrid(1, 2, 100, 1, getDirectionsPanel("Effect"), p, c);		
		addGrid(0, 3, 1  , 1, new JLabel("Flare"), p, c);
		addGrid(1, 3, 100, 1, flare = getToggle("Actived","on"), p, c);
		flare.setSelected(true);
       	return p;
	}
	
	private JToggleButton getToggle(String command, String title){
		JToggleButton t = new JToggleButton(title);
		t.setPreferredSize(new Dimension(15,15));
		t.setActionCommand(command);		
		t.setMargin(new Insets(0, 0, 0, 0));
		t.addActionListener(this);
		return t;
	}
	
	
	public JPanel getDirectionsPanel(String type){
		JPanel p = new JPanel();
		p.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JToggleButton toSelect = getToggle(type + " right", ">"); 
		JToggleButton other  = getToggle(type + " left", "<");
       	addGrid(0, 0, 1 , -1, other, p, c);
       	addGrid(1, 0, 50, -1, new JLabel(type, SwingConstants.CENTER), p, c);
       	addGrid(2, 0, 1 , -1, toSelect, p, c);
        ButtonGroup mutuallyExclusiveButtons = new ButtonGroup();        
        mutuallyExclusiveButtons.add(toSelect);
        mutuallyExclusiveButtons.add(other);
        //if (direction == BarConstant.RIGHT_TO_LEFT) toSelect.setSelected(true);        
        //else other.setSelected(true);
        toSelect.setSelected(true);       
        return p;
	}

	

	private void addGrid(int x,int y, int w, int h,JComponent comp, JPanel p, GridBagConstraints c){
		c.gridx = x;
		c.gridy = y;		
		if (w>=0) c.weightx = w;
		if (h>=0) c.weighty = h;
		p.add(comp,c);
	}
	
	public Border getTitleBorder(String title){
		Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder titleBorder = BorderFactory.createTitledBorder(loweredetched, title);
		titleBorder.setTitleJustification(TitledBorder.LEFT);
		return titleBorder;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
		if (source.getName().equals("Degrees"))
			setDegree((int)source.getValue());
		else if (source.getName().equals("Magnitude"))
			setMagnitude((int)source.getValue());		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == apply) setEffect();
		else if (e.getSource() == flare) setFlared();
		else
			switch (e.getActionCommand()) { 		
				case "Effect up": setDirection(BarConstant.BOTTOM_TO_TOP); break;
				case "Effect down": setDirection(BarConstant.TOP_TO_BOTTOM); break;
				case "Effect left": setDirection(BarConstant.RIGHT_TO_LEFT); break;
				case "Effect right": setDirection(BarConstant.LEFT_TO_RIGHT); break;
				default:			break;
			}
	}

	private void setDegree(int d){
		degree = d;
		for (int i = 0; i < list.size(); i++) 	
			list.get(i).getIllusionUI().getGradientFactory().setGrad(d);
	}

	private void setMagnitude(int d){
		magnitude = d;
		for (int i = 0; i < list.size(); i++) 	
			list.get(i).getIllusionUI().getGradientFactory().setMagnitude(d);
	}	
	
	private void setDirection(int d){
		direction = d;
		for (int i = 0; i < list.size(); i++) 	
			list.get(i).getIllusionUI().setIllusionDirection(d);
	}	

	
	private void setFlared(){		
		if (flare.isSelected()) flare.setText("ON");
		else flare.setText("OFF");
		
		//gradient.setFlareLightPoint();
		flared = flare.isSelected();
		for (int i = 0; i < list.size(); i++) 	
			list.get(i).getIllusionUI().getGradientFactory().getGradient().setFlareLightPoint(flare.isSelected());
	}
	
	public void setEffect(){
		for (int i = 0; i < list.size(); i++){
			CustomizableGradient newcg = gradient.getInstance();
			list.get(i).getIllusionUI().getGradientFactory().setGradient(newcg);
			//newcg.setMagnitude(magnitude);
			newcg.setFlareLightPoint(flared);
			newcg.setGrad(degree);
			newcg.setIllusionDirection(direction);			
		}
	}

}
