package Effect;

import java.util.ArrayList;

public class FactorSupport implements DimensionListener{

	private ArrayList<Double> divisor;
	private ArrayList<Double> moltiplier;
	
	private double selection = 1;
	private EffectProgressBarCoord bar;
	
	
	public FactorSupport(EffectProgressBarCoord bar){
		this.bar = bar;
		bar.addDimensionListener(this);
	}
			
	@Override
	public void dimensionActived() {
		init();
	}   	
		

	public void init(){
    	divisor = getDivisorList(bar.getLong());
    	moltiplier = getMoltiplierList(bar.getLong());
    	moltiplier.add(1.0*bar.getLong());
    }

    private ArrayList<Double> getDivisorList(int n){
    	ArrayList<Double> divs = new ArrayList<>();    	
    	int max = (int) Math.pow(10,new Integer(n).toString().length()-1);    	    	
    	for (int i = 1; i < max; i++) 
    		if ((n/(1.0*i/max))%1 == 0) divs.add(1.0*i/max);    		
    	return divs;
    }
    

    private ArrayList<Double> getMoltiplierList(int n){
    	ArrayList<Double> mult = new ArrayList<>();
    	for (int i = 1; i <= n/2; i++) 
    		if (n%i == 0) mult.add(1.0*i);
    	return mult;
    }

    
    public ArrayList<Double> getFactorList(){
    	ArrayList<Double> factors = new ArrayList<Double>();
    	factors.addAll(divisor);
    	factors.addAll(moltiplier);
    	return factors;
    }   
        
    
    public double getNearestFactor(double n){
    	if (n <= divisor.get(0)) selection = divisor.get(0);
    	else if (n >= moltiplier.get(moltiplier.size()-1)) selection = moltiplier.get(moltiplier.size()-1);
    	else if (n > 1) selection = getNearestFactor(n, moltiplier);
    	else if (n < 1) selection = getNearestFactor(n, divisor);
    	else selection = 1;
    	return selection;
    }
    
    
    private double getNearestFactor(double n, ArrayList<Double> factor){    	
    	int i = 0;
    	while (i < factor.size() &&  n > factor.get(i)) i++;
    	if ((n - factor.get(i-1)) < (factor.get(i) - n))
    		return factor.get(i);
    	return factor.get(i+1);
    }    

    
    public void setPercentualFactor(int percent){
    	getPercentualFactor(percent);
    }

    public double getPercentualFactor(int percent){
    	if (percent == 50) selection = 1;
    	else if (percent < 50) selection = getPercentualFactor(percent,divisor);
    	else selection = getPercentualFactor(percent - 50,moltiplier);
    	return selection;
    }
    
    private double getPercentualFactor(double n, ArrayList<Double> factor){
    	return factor.get((int) ((n*(factor.size()-1))/50));
    }
    
    
    
    public double getSelectedFactor(){
    	return selection;
    }
    
    public double getDoubledSelectedFactor(){
    	return getNearestFactor(selection*2);
    }    

    public double getHalvedSelectedFactor(){
    	return getNearestFactor(selection/2);
    }    

    
    
    
    /*
    private static double round(double d, int max) {
    	int decimalPlace = new Integer(max).toString().length()-1;
        BigDecimal bd = new BigDecimal(d);
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }    
    */
    
   
    public  void printFactorListPercent(){
    	double p = -1;
    	for (int i = 0; i < 100; i++){
    		double x = getPercentualFactor(i);
    		if (p != x) {
    			p = x;
    			System.out.print(p + " ");	
    		}
    	}
		System.out.println();
    }    

        
    public  void printFactorList(){
    	for (int i = 0; i < divisor.size(); i++) 
    		System.out.print(divisor.get(i) + " ");   	
    	for (int i = 0; i < moltiplier.size(); i++) 
    		System.out.print(moltiplier.get(i) + " ");
		System.out.println();
    }

    /*
    public static void main(String[] args) {
    	FactorSupport fl = new FactorSupport(200);
    	fl.printFactorList();
    	fl.printFactorListPercent();
    	System.out.println(fl.getNearestFactor(10));
    	System.out.println(fl.getNearestFactor(0.3));
	}
   */

    
    
    
	
}
