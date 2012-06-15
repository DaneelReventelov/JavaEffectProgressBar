import javax.swing.JFrame;

import Test.EffectProgressBarTest;

@SuppressWarnings("serial")
public class Loader extends JFrame {


	public Loader(){
		super();
		getContentPane().add(new EffectProgressBarTest());
		pack();
		setVisible(true);			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Java Effect Progress Bar");
		
	}
	
	public static void main(String[] args) {
		new Loader();
	}

}
