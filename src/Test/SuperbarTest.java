package Test;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class SuperbarTest extends JPanel{

	private ArrayList<ProgressPanel> list = null;

	/* da eliminare*/
	private boolean vertical = true;
	private boolean horizontal = true;
	/**/
	
	
	public SuperbarTest(){
		setLayout(new BorderLayout());
		list = new ArrayList<>();
		JTabbedPane jtp = new JTabbedPane();

//		jtp.addTab("Horizontal",add(getHorizontalPanel()));
		//jtp.addTab("Vertical",add(getVerticalPanel()));
		jtp.addTab("Super Horizontal",add(getSuperHorizontalPanel()));
		//jtp.addTab("Super Vertical",add(getSuperVerticalPanel()));
		
		jtp.addTab("Example 1",new CodeTextArea("Text/Example1.html","Example 1"));
		jtp.addTab("Example 2",new CodeTextArea("Example 2"));
		jtp.addTab("Self-generated Code",new CodeTextArea("Self-generated Code"));
		jtp.addTab("License",new CodeTextArea("Text/epl-v10.html","License",true));

		add(jtp,BorderLayout.CENTER);
		
		add(new PlayerPanel(list),BorderLayout.NORTH);
		add(new EffectProgressBarTestEffect(list),BorderLayout.SOUTH);
    }

	
	private JPanel getSuperHorizontalPanel(){
		JPanel p = new JPanel();
		if (!horizontal) return p;
		p.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		p.add(getProgressBarPanel(1000,300),c);
		return p;
	}

	
	
	private JPanel getSuperVerticalPanel(){
		JPanel p = new JPanel();
		//p.setBorder(new EmptyBorder(new Insets(0, 0, 0, 0)));
		p.add(getProgressBarPanel(600,300,JProgressBar.VERTICAL));
		if (!vertical) return p;
		return p;	
	}
	
	int barsHeight = 50;
	private JPanel getHorizontalPanel(){
		JPanel p = new JPanel();
		if (!horizontal) return p;/*
		p.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		p.add(getProgressBarPanel(1400,70),c);
		c.gridy = 2;
		p.add(getProgressBarPanel(1400,50),c);
		c.gridy = 3;*/
		//p.add(getProgressBarPanel(1400,30),c);
		p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
		p.add(getProgressBarPanel(100,barsHeight));
		p.add(getProgressBarPanel(200,barsHeight));
		p.add(getProgressBarPanel(300,barsHeight));
		p.add(getProgressBarPanel(400,barsHeight));
		p.add(getProgressBarPanel(500,barsHeight));
		p.add(getProgressBarPanel(600,barsHeight));
		p.add(getProgressBarPanel(600,barsHeight+10));
		p.add(getProgressBarPanel(600,barsHeight+20));
		return p;
	}

	
	
	private JPanel getVerticalPanel(){
		JPanel p = new JPanel();
		//p.setBorder(new EmptyBorder(new Insets(0, 0, 0, 0)));
		if (!vertical) return p;
		//p.setLayout(new BoxLayout(p, BoxLayout.LINE_AXIS));
		
		p.setLayout(new GridLayout(1, 8));
		p.add(getProgressBarPanel(100,barsHeight,JProgressBar.VERTICAL));
		p.add(getProgressBarPanel(150,barsHeight,JProgressBar.VERTICAL));
		p.add(getProgressBarPanel(200,barsHeight,JProgressBar.VERTICAL));
		p.add(getProgressBarPanel(300,barsHeight,JProgressBar.VERTICAL));
		p.add(getProgressBarPanel(400,barsHeight,JProgressBar.VERTICAL));
		p.add(getProgressBarPanel(400,barsHeight+10,JProgressBar.VERTICAL));
		p.add(getProgressBarPanel(400,barsHeight+20,JProgressBar.VERTICAL));
		p.add(getProgressBarPanel(400,barsHeight+30,JProgressBar.VERTICAL));
	
		return p;
		
	}


	private Component getProgressBarPanel(int width,int height, int orientation) {
		ProgressPanel pp = new ProgressPanel(width, height,orientation);
		list.add(pp);
		return pp;
	}


	public JPanel getProgressBarPanel(int width,int height){
		ProgressPanel pp = new ProgressPanel(width, height);
		list.add(pp);
		return pp;
	}


	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new SuperbarTest());
		frame.pack();
		frame.setVisible(true);			
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("Super Illusion Progress Bar");
	}

	
	


	

}
