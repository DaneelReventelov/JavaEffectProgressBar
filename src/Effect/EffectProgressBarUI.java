package Effect;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;

import javax.swing.JComponent;
import javax.swing.JProgressBar;
import javax.swing.plaf.basic.BasicProgressBarUI;

import Effects.Gradient.CustomizableGradient;

public class EffectProgressBarUI extends BasicProgressBarUI implements HierarchyListener{

	private FactorSupport effectF;
    private Color borderColor = Color.BLACK;

    private boolean highQuality = false;
    
    private GradientFactory gradient;
    private EffectProgressBarCoord bar;
    private EffectProgressBarShadow shadow = null;
   	private boolean shadowPainted = false;
   	private Color foregroundColor = Color.WHITE;
   	private Color backgroundColor = Color.BLACK;
   	
    
    public EffectProgressBarUI(JProgressBar progressBar) {
    	super();    	
    	super.progressBar = progressBar;
    	bar = new EffectProgressBarCoord(progressBar);    	  	    	
    	progressBar.addHierarchyListener(this);
    	shadow = new EffectProgressBarShadow(bar);
    	gradient = new GradientFactory(bar);
       	if (progressBar.getOrientation() == JProgressBar.HORIZONTAL)
       		effectF = new FactorSupport(bar);
       	else effectF = new FactorSupport(bar);

    }
    
    
    public void setIllusionDirection(int direction) {
    	gradient.setIllusionDirection(direction);
    }

    public void setBarDirection(int direction) {
    	bar.setBarDirection(direction);
    }    
    
    
    
    @Override
    protected void incrementAnimationIndex() {
    	//int newValue = (int)(((getAnimationIndex()/100.0 + effectF.getSelectedFactor())%bar.getLong())*100);
    	int newValue = (int)(((getAnimationIndex()/100.0 + effectF.getSelectedFactor()))*100);
    	//TODO find a better way
    	if (newValue > 1000000000) newValue = 0;
        setAnimationIndex(newValue);
    }

         
    
    protected void paintDeterminate(Graphics g, JComponent c) {
        if (!(g instanceof  Graphics2D)) 
            return;
        if (bar.getLong() <= 0 || bar.getShort() <= 0) {
            return;
        }        
               
        Graphics2D g2 = (Graphics2D) g;
        
        if (highQuality)
        	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
       
        paintDeterminateUni(g, bar.getLong(),bar.getShort(), bar.roundHeight,bar.roundWidth);

        
        
        
        if (bar.isBorderPainted()){
        	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        	g2.setColor(borderColor);
        	//g2.setClip(bar.getBorderShape());
        	g2.setClip(0,0,progressBar.getWidth(),progressBar.getHeight());
        	g2.fill(bar.getBorderShape());
        	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_OFF);
        }
        
    }    
  
    
    protected void paintDeterminateUni(Graphics g, int barlong, int barShort,double roundShort, double roundLong) {    
        int amountFull = getAmountFull(bar.getInsets(), bar.getWidth(), bar.getHeight());    
        
        Graphics2D g2 = (Graphics2D) g;
        //gradient.paintGradient(g2, amountFull, (int)getAnimationIndex()/100);
        
        gradient.paintGradient(g2, amountFull, (float) effectF.getSelectedFactor());
       	if (shadowPainted){ 
            g2.setPaint(shadow.getShadowGradient());
            g2.fill(bar.getRectangle());
       	}
      
       	//g2.setClip(bar.left,bar.top,bar.width,bar.height);
       	g2.setClip(bar.getRectangle());
        if (bar.isStringPainted()) 
        	paintString(g, bar.left, bar.top, bar.getLong(), bar.getShort(),amountFull, bar.getInsets());
            //paintString(g, bar.left, bar.top, bar.width, bar.height,amountFull, bar.getInsets());
    }
   


    public void setSelectionBackground(Color c) { 
    	backgroundColor = c;
    }
    
    public void setSelectionForeground(Color c) { 
    	foregroundColor = c;     
    }
    
    protected Color getSelectionBackground() { 
    	return backgroundColor;
    }
    
    protected Color getSelectionForeground() {
    	return foregroundColor;
    }

    
 
	public void setEffectAcceleration(int percent) {
		effectF.setPercentualFactor(percent);
	}
    
 
    public void setColorAccelerationPercent(int percent){
    	gradient.setColorAccelerationPercent(percent);
    }    

    
	
	public FactorSupport getEffectFactorSupport() {
		return effectF;
	}
    
 

    public GradientFactory getGradientFactory(){
    	return gradient;
    }
    
	
	/*
    public void hierarchyChanged(HierarchyEvent he) {
        if ((he.getChangeFlags() & HierarchyEvent.DISPLAYABILITY_CHANGED) != 0)
                if (bar.isDisplayable()) startAnimationTimer();
                else stopAnimationTimer();
    }
    */
	
    public void hierarchyChanged(HierarchyEvent he) {
    	if (he.getChangeFlags() == HierarchyEvent.SHOWING_CHANGED)
            if (progressBar.isShowing()) {
            	startAnimationTimer();
            }
            else if (!progressBar.isShowing()) stopAnimationTimer(); 
    }


    
    public void setLightAndDarkColor(Color light,Color dark){
    	gradient.setLightAndDarkColor(light,dark);
    }
        

    public void setLightAndDarkColors(Color[] lights,Color[] darks){
    	gradient.setLightAndDarkColors(lights,darks);
    }

    public void setDarkLightColors(Color[] c){    
    	gradient.setDarkLightColors(c);
    }    
    

    public void setRoundCorner(double[] moltiplier){
    	bar.setRoundCorner(moltiplier);
    }
    
    public void setBorder(int border){
    	bar.setBorder(border);
    }
    
    public void setBorderColor(Color c){
    	borderColor = c;
    }    
    
    public void setHighQuality(boolean b){
    	highQuality = true;
    }
    

    public EffectProgressBarShadow getShadowFactory(){
    	return shadow;
    }


    public void setShadowPainted(boolean active){
    	this.shadowPainted = active;
    	//if (shadowPainted) shadow = new IllusionProgressBarShadow(bar);  
    }
	
    public boolean isShadowPainted(){
    	return shadowPainted;
    }
    
    public void installGradient(CustomizableGradient cg){
    	cg.init(bar);
    	getGradientFactory().setGradient(cg);
    }

    
}
