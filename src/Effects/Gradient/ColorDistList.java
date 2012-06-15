package Effects.Gradient;

import java.awt.Color;
import java.util.ArrayList;

public class ColorDistList {

	private ArrayList<ColorDist> cds;
	private int flareNum = 0;
	
	public ColorDistList(){
		cds = new ArrayList<>();
	}

	public ColorDistList(ColorDist[] cd){
		this();
		add(cd);
	}
	
	
	public void add(ColorDist cd[]){
		for (int i = 0; i < cd.length; i++) {
			cds.add(cd[i]);
			if (cd[i].isFlare()) flareNum ++;
		}
	}
	
	public void add(ColorDist cd){
		cds.add(cd);
		if (cd.isFlare()) flareNum ++;
	}

	
	public float[] getDists(boolean isFlare){
		float[] f;
		if (isFlare) f = new float[cds.size()];
		else f = new float[cds.size() - flareNum];
		int j = 0;
		for (int i = 0; i < cds.size(); i++)
			if (isFlare ||  !cds.get(i).isFlare()){
				f[j] = cds.get(i).getDist();
				j++;
			}
		return f;
	}
	
	public Color[] getColors(boolean isFlare, Color flareColor, Color lightColor, Color darkColor){
		Color[] f;
		if (isFlare) f = new Color[cds.size()];
		else f = new Color[cds.size() - flareNum];
		int j = 0;
		for (int i = 0; i < cds.size(); i++)
			if (isFlare ||  !cds.get(i).isFlare()){
				f[j] = cds.get(i).getColor(flareColor,lightColor,darkColor);
				j++;
			}
		return f;
	}

	public void addTrasparentFinal(){
		 add(new ColorDist(ColorDist.TRASPARENT,1.0f));
	}
	
}
