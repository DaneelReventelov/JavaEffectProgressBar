package Test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Effect.BarConstant;
import Effect.EffectProgressBarCoord;
import Effect.EffectProgressBarShadow;
import Effect.EffectProgressBarUI;
import Effects.Gradient.CustomizableGradient;


@SuppressWarnings("serial")
public class ProgressPanel extends JPanel implements ChangeListener, ActionListener{

	private JProgressBar bar = new JProgressBar();	
	private EffectProgressBarUI ipbi;
	private Color[] col = {Color.BLUE,Color.CYAN,Color.GRAY,Color.MAGENTA,Color.ORANGE,Color.PINK,Color.RED,Color.YELLOW};

	private int lateralHeight = 70;
	

	
	public  ProgressPanel(int width,int height){
		this(width,height,JProgressBar.HORIZONTAL);
	}
	
	public ProgressPanel(int width,int height, int orientation){		
		this(width,height,orientation,orientation);
	}

	public ProgressPanel(int width,int height, int orientation,int onlyBarOrientation){		
		super();
		setLayout(new BorderLayout());
		add(getBar(width,height,onlyBarOrientation),BorderLayout.CENTER);
		add(getDirectionsButtonPanel(orientation),(orientation == JProgressBar.HORIZONTAL) ? BorderLayout.WEST : BorderLayout.NORTH);		        
       	add(getSlidersPanel(width),(orientation == JProgressBar.HORIZONTAL) ? BorderLayout.EAST : BorderLayout.SOUTH);
	}

	
	public JPanel getBar(int width, int height, int orientation){
		JPanel p = new JPanel();
		bar = new JProgressBar();
		bar.setOrientation(orientation);
		if (orientation == JProgressBar.VERTICAL)
			bar.setPreferredSize(new Dimension(height, width));	
		else bar.setPreferredSize(new Dimension(width, height));
		bar.setStringPainted(true);
		bar.setBorder(BorderFactory.createEtchedBorder());
		bar.setUI(ipbi = new EffectProgressBarUI(bar));
		ipbi.setRoundCorner(EffectProgressBarCoord.RoundOVAL);
		ipbi.setDarkLightColors(col);
		ipbi.setBorder(1);
		ipbi.setIllusionDirection(CustomizableGradient.LEFT_TO_RIGHT);
		//ipbi.setShadowPainted(true);
		//ipbi.setHighQuality(true);
		if (orientation == JProgressBar.HORIZONTAL)
			p.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx=0;
		c.gridy=2;

		p.add(bar,c);
		return p;
	}
	


	private JPanel getSlidersPanel(int width){
		JPanel p = new JPanel();
		int lateralWidth = 120;
		p.setPreferredSize(new Dimension(lateralWidth,lateralHeight));
		p.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		p.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));		
		JPanel dim = new JPanel();
		dim.setPreferredSize(new Dimension(300,1));
		
		c.fill = GridBagConstraints.HORIZONTAL;
		addGrid(0, 0, 1  , -1, new JLabel("Color"), p, c);
		addGrid(1, 0, 100, -1, new LabeledSlider(100,1,50,"Color",lateralWidth,lateralHeight/4,this), p, c);
		addGrid(0, 1, 1  , -1, new JLabel("Bar"), p, c);
		addGrid(1, 1, 100, -1, new LabeledSlider(100,1,50,"Bar",lateralWidth,lateralHeight/4,this), p, c);
		addGrid(0, 2, 1  , -1, new JLabel("Grad"), p, c);
		addGrid(1, 2, 100, -1, new LabeledSlider(360,1,45,"Grad",lateralWidth,lateralHeight/4,this), p, c);
		addGrid(0, 3, 1  , -1, new JLabel("Magn"), p, c);
		addGrid(1, 3, 100, -1, new LabeledSlider(width,1,20,"Magnitude",lateralWidth,lateralHeight/4,this), p, c);
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
		titleBorder.setTitleJustification(TitledBorder.CENTER);
		return titleBorder;
	}

	
	private JToggleButton getToggle(String command, String title){
		JToggleButton t = new JToggleButton(title);
		t.setPreferredSize(new Dimension(15,15));
		t.setActionCommand(command);		
		t.setMargin(new Insets(0, 0, 0, 0));
		t.addActionListener(this);
		return t;
	}
	
	public JPanel getDirectionsPanel(String type,int orientation){
		JPanel p = new JPanel();
		p.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JToggleButton toSelect; 
		JToggleButton other;
				
        if (orientation == JProgressBar.VERTICAL){
    		c.fill = GridBagConstraints.HORIZONTAL;
        	addGrid(0, 0, 1, -1, toSelect = getToggle(type + " up", "˄"), p, c);
        	addGrid(0, 1, 1, -1, new JLabel(type, SwingConstants.CENTER), p, c);
        	addGrid(0, 2, 1, -1, other = getToggle(type + " down", "˅"), p, c);
        }else {
        	addGrid(0, 0, 1 , -1, other = getToggle(type + " left", "<"), p, c);
        	addGrid(1, 0, 50, -1, new JLabel(type, SwingConstants.CENTER), p, c);
        	addGrid(2, 0, 1 , -1, toSelect = getToggle(type + " right", ">"), p, c);
        }
        ButtonGroup mutuallyExclusiveButtons = new ButtonGroup();        
        mutuallyExclusiveButtons.add(toSelect);
        mutuallyExclusiveButtons.add(other);
        toSelect.doClick();
        return p;
	}

	
	
	public JPanel getDirectionsButtonPanel(int orientation){
		JPanel p = new JPanel(new GridBagLayout());
		p.setPreferredSize(new Dimension(75,60));
		
		//p.setBorder(getTitleBorder(""));
		//p.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

		
		GridBagConstraints c = new GridBagConstraints();
		JSeparator separate = new JSeparator(orientation);             
		//if (orientation == JProgressBar.VERTICAL) separate.setPreferredSize(new Dimension(1,lateralHeight));        
        //else separate.setPreferredSize(new Dimension(75,2));
        JPanel p1  = getDirectionsPanel("Effect",orientation);       	
    	JPanel p2 = getDirectionsPanel("Bar",orientation);
    
    	
    	if (orientation == SwingConstants.HORIZONTAL){
       		c.fill = GridBagConstraints.HORIZONTAL;
       		addGrid(0,0, 1,1,new JPanel(),p,c);
       		addGrid(0,1, 1,1, p1,p,c);
       		//addGrid(0,2, -1,1 , new JPanel(),p,c);
       		addGrid(0,2, 1,1 , separate,p,c);
       		//addGrid(0,4, -1,1 , new JPanel(),p,c);
       		addGrid(0,3, 1,1, p2,p,c);
       		addGrid(0,4, 1,1,new JPanel(),p,c);
       	}
       	else{
       		p1.setPreferredSize(new Dimension(35,1));
       		p2.setPreferredSize(new Dimension(35,1));
       		c.fill = GridBagConstraints.VERTICAL;
       		addGrid(0,0,1, 1,new JPanel(),p,c);
       		addGrid(1,0,1, 1,p1,p,c);
       		//addGrid(2,0,1, 1,new JPanel(),p,c);
       		addGrid(2,0,0, 1,separate,p,c);
       		//addGrid(4,0,1, 1,new JPanel(),p,c);
       		addGrid(3,0,1, 1,p2,p,c);
       		addGrid(4,0,1, 1,new JPanel(),p,c);
       	}
    	
        return p;
	}

	
		
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) { 		
		case "Effect up": ipbi.setIllusionDirection(BarConstant.BOTTOM_TO_TOP); break;
		case "Effect down": ipbi.setIllusionDirection(BarConstant.TOP_TO_BOTTOM); break;
		case "Effect left": ipbi.setIllusionDirection(BarConstant.RIGHT_TO_LEFT); break;
		case "Effect right": ipbi.setIllusionDirection(BarConstant.LEFT_TO_RIGHT); break;
		case "Bar up": ipbi.setBarDirection(CustomizableGradient.RIGHT_TO_LEFT); break;
		case "Bar down": ipbi.setBarDirection(CustomizableGradient.TOP_TO_BOTTOM); break;
		case "Bar left": ipbi.setBarDirection(CustomizableGradient.RIGHT_TO_LEFT); break;
		case "Bar right": ipbi.setBarDirection(CustomizableGradient.LEFT_TO_RIGHT); break;
		default:			break;
		}
	
			

	}
	
	public void setValue(int value){
		bar.setValue(value);
	}
	
	

	@Override
    public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
		if (source.getName().equals("Color"))
			ipbi.setColorAccelerationPercent((int)source.getValue());
		else if (source.getName().equals("Bar"))
			ipbi.setEffectAcceleration((int)source.getValue());		
		else if (source.getName().equals("Grad"))
			ipbi.getGradientFactory().setGrad((int)source.getValue());
		else if (source.getName().equals("Magnitude"))
			ipbi.getGradientFactory().setMagnitude((int)source.getValue());		
    }

	
	public void setDarkAndLightColor(Color dark, Color light) {
		ipbi.setLightAndDarkColor(light, dark);
	}

	
	public void setDarkAndLightColor(Color[] dark, Color[] light) {
		ipbi.setLightAndDarkColors(light, dark);
	}

    public Color[] getDarkColors(){
    	return ipbi.getGradientFactory().getDarkColors();
    }

    public Color[] getLightColors(){
    	return ipbi.getGradientFactory().getLightColors();
    }
	
	public EffectProgressBarShadow getShadowFactory(){
		return ipbi.getShadowFactory();
	}
	
	public Color[] getDefaultColor(){
		return col;
	}


    public EffectProgressBarUI getIllusionUI(){
    	return ipbi;
    }
	
}	
