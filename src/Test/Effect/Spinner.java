package Test.Effect;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Spinner extends JPanel implements ActionListener {


	private JButton plus;
	private JButton minus;
	private JTextField jtf;
	private int val = 0;
	private int minimum = 1;
	private int maximum = -1;
	private boolean minimumActived = true;
	private boolean maximumActived = false;	
	private ActionListener act = null;
	private String command = "Spinner";
	
	public Spinner(){
		this(0);
	}
	
	
	public Spinner(int num,String actionCommand){
		this(num);
		command = actionCommand;
	}
	
	
	public Spinner(int num){
		val = num;
		FlowLayout f = new FlowLayout();
		f.setHgap(0);		
		setLayout(f);
		jtf = new JTextField(num + "",10);
		jtf.addActionListener(this);
		add(jtf);
		minus = new JButton("-");
		minus.addActionListener(this);
		int h = jtf.getPreferredSize().height - 2;
		add(minus = getButton("-", h));
		add(plus = getButton("+", h));
	}

	
	private JButton getButton(String s,int h){
		JButton b = new JButton(s);
		b.setMargin(new Insets(0, 0, 0, 0));
		b.setPreferredSize(new Dimension(h,h));
		b.addActionListener(this);
		return b;
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == minus) sub();
		else if (e.getSource() == plus) add();	
		else retrive();
	}
	
	
	private void sub(){
		if (val < 2) return;
		val--;
		jtf.setText(val + "");
		notifyVal();
	}
	

	private void add(){
		val++;
		jtf.setText(val + "");
		notifyVal();
	}
	
	private void retrive(){
		int v = getIntVal();
		if (v == val) return;
		if (v < 1) jtf.setText(val + "");
		else {
			val = v;
			notifyVal();
		}
	}

	
	private int getIntVal(){
		String s = jtf.getText();			
		int x = val;
		try {
			x = Integer.parseInt(s);
		} catch (NumberFormatException nfe){jtf.setText(val + "");}		
		return x;
	}
	
	
	private void notifyVal(){
		if (act == null) return;
		act.actionPerformed(new ActionEvent(this, 231, command));
	}
	
	public void setActionListener(ActionListener act){
		this.act = act;
	}
	
	
	public int getVal(){
		return val;
	}
	

	public void setMaximum(int max){
		maximum = max;
		maximumActived = true;
	}
	
	public void setMinimum(int min){
		minimum = min;
		minimumActived = true;
	}

	
	
}
