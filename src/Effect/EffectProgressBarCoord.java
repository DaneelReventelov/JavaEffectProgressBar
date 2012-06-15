package Effect;
import java.awt.BasicStroke;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

import javax.swing.JProgressBar;

import Effects.Gradient.CustomizableGradient;


public class EffectProgressBarCoord extends BarConstant implements ComponentListener{

	private ArrayList<DimensionListener> listeners = new ArrayList<>();
	private ArrayList<CustomizableGradient> bases = new ArrayList<>();
	private boolean actived = false;

	public int top = -1;
	public int bottom = -1;
	public int left = -1;
	public int right = -1;
	public int topBorder = -1;
	public int bottomBorder = -1;
	public int leftBorder = -1;
	public int rightBorder = -1;
	
	private int width = -1;
	private int height = -1;
	public int widthBorder = -1;
	public int heightBorder = -1;
	private ClippingShape cs;
    public int borderThickness = 0;
    public int borderOverlap = 2;
    public double roundHeight = 0;
    public double roundWidth =  0;
    public static double[] RoundNOROUND = {0,0};
    public static double[] RoundCIRCLE = {1,1};
    public static double[] RoundOVAL = {4/3.0,1};
    
	public int direction = LEFT_TO_RIGHT;
    
	private JProgressBar bar;
		
	public EffectProgressBarCoord(JProgressBar bar){
		this(bar,0);
	}
	
	public EffectProgressBarCoord(JProgressBar bar, int border){
		this.bar = bar;
		//bar.setBorder(new EmptyBorder(new Insets(10,10,10,10)));
		cs = new ClippingShape(this);
		bar.addComponentListener(this);
		setBorder(border);
	}


	public void init(){
		actived = true;
		Insets in = bar.getInsets();
		topBorder = in.top;
		bottomBorder = in.bottom;
		leftBorder = in.left;
		rightBorder = in.right;
		
		top = in.top + borderThickness;
		bottom = in.bottom + borderThickness;
		left = in.left + borderThickness;
		right = in.right + borderThickness;
		
		width = bar.getWidth() - right - left;
		height = bar.getHeight() - bottom - top;
		
		widthBorder = bar.getWidth() - rightBorder - leftBorder - borderThickness;
		heightBorder = bar.getHeight() - bottomBorder - topBorder  - borderThickness;
		notifyDimension();
	}

	public Insets getInsets(){
		return new Insets(top, left, bottom, right);
	}
	
	@Override
	public void componentHidden(ComponentEvent e) {}
	@Override
	public void componentMoved(ComponentEvent e) {}
	@Override
	public void componentResized(ComponentEvent e) {
		init();
	}
	@Override
	public void componentShown(ComponentEvent e) {
		init();
	}

	
	public int getOrientation(){
		return bar.getOrientation();
	}

	public boolean isDisplayable() {
		return bar.isDisplayable();
	}

	public boolean isStringPainted() {
		return bar.isStringPainted();
	}	
	
	public int horizontalization(int x) {
		return (bar.getOrientation() == JProgressBar.HORIZONTAL) ? x : 0;
	}	

	public int verticalization(int x) {
		return (bar.getOrientation() == JProgressBar.VERTICAL) ? x : 0;
	}	


	public int vertOrHoriz(int vert,int horiz) {
		return (bar.getOrientation() == JProgressBar.VERTICAL) ? vert : horiz;
	}	
	
	public boolean isHorizontal(){
		return (bar.getOrientation() == JProgressBar.HORIZONTAL);
	}

	public int getLong(){
		return vertOrHoriz(height,width);
	}
	
	public int getShort(){
		return vertOrHoriz(width,height);
	}

	public int getHeight(){
		return height;
	}
	
	public int getWidth(){
		return width;
	}
	

	public Rectangle getRectanglePartShort(float ini,float end){
		if (isHorizontal()) return new Rectangle(left,(int) (top + starter(ini) + height * ini),width,(int) (top + height * end)); 
		else return new Rectangle((int) (left + starter(ini) + width * ini),top, (int) (right + width * end),height);					
	}

	private int starter(float ini){
		return (ini == 0) ? 0 : -1; 
	}
	
	
	public Rectangle getRectangle(){
		return new Rectangle(left,top,width,height);					
	}
	
	
	public boolean isVertical(){
		return (bar.getOrientation() == JProgressBar.VERTICAL);
	}
	
	
	public Shape getClipShape(int width){
		return cs.getClipShape(width);
	}
	
	
	public BasicStroke getBorderStroke(){
		return new BasicStroke(borderThickness + borderOverlap);
	}
	
	public void setBorder(int border){
		this.borderThickness = border;
		if (border > 7) borderOverlap = 4;
		else if (border > 5) borderOverlap = 4;
		else if (border > 3) borderOverlap = 3;
		if (border > 0) bar.setBorderPainted(false);
		if (width>0) init();
	}
	
	
    public void setRoundCorner(double[] moltiplier){
    	roundHeight = moltiplier[0];
    	roundWidth =  moltiplier[1];
    }
	
    public static double[] getRoundComponent(double height,double width){
    	return new double[] {height, width};
    }
    
	public Shape getBorderShape(){
		return cs.getBorderShape();
	}

	public boolean isBorderPainted() {
		return (borderThickness > 0);
	}

	public Shape getClipShadow(){
		return cs.getClipShadow();
	}
	
	public Shape getClipShadow(double height){
		return cs.getClipShadow(height);
	}

	public void setBarDirection(int direction) {
		this.direction = direction;	
	}

	
    public void addDimensionListener(DimensionListener dl){
    	listeners.add(dl);
    	if (actived) dl.dimensionActived();
    }
    
    public void notifyDimension(){
    	for (int i = 0; i < listeners.size(); i++)    	
    		listeners.get(i).dimensionActived();
    	for (int i = 0; i < bases.size(); i++){    	
    		bases.get(i).calcExtension();
    		bases.get(i).calcShortExtension();
    	}
    }

    public JProgressBar getProgressBar(){
    	return bar;
    }

	public void addGradientBase(CustomizableGradient cg) {
		bases.add(cg);
    	if (actived) {
    		cg.calcExtension();
    		cg.calcShortExtension();
    	}
	}
	
}
