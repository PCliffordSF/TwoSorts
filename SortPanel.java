
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SortPanel extends JPanel {

	JButton buildArrayButton;
	JButton sortArrayButton;
	int spacer;
	int leftIndent;
	int rInt;
	int[] sortOneArray = new int[30];
	int[] sortTwoArray = new int[30];

	public SortPanel(){
		setPreferredSize(new Dimension(800,500));
		setLayout(new FlowLayout(FlowLayout.LEFT));
		buildArrayButton = new JButton("Build New Arrays");
		sortArrayButton = new JButton("Sort The Arrays");
		buildArrayButton.addActionListener(new ButtonListener());
		add(buildArrayButton);
		add(sortArrayButton);
		buildArrays();
	}

	public void paintComponent(Graphics g){
		super.paintComponent (g);

		spacer = 10;
		leftIndent = 100;

		g.setColor(Color.BLACK);
		g.fillRect(0,0,getWidth(), getHeight());
		g.setColor(Color.GREEN);

		for(int i = 0; i < 30; i++){
			g.fillRect(leftIndent + spacer + 20*i ,190 - sortOneArray[i], 10, sortOneArray[i]);
		}
		    g.setColor(Color.MAGENTA);
		for(int j = 0; j < 30; j++){
			g.fillRect(leftIndent + spacer + 20*j ,200 , 10, sortTwoArray[j]);
		}

	}
	public void buildArrays(){
		for(int x = 0; x < 30; x++){
			rInt = (int)(Math.random()*7) + 1;
			sortOneArray[x] = rInt*10;
			sortTwoArray[x] = rInt*10;
		}
	}

	   private class ButtonListener implements ActionListener{
	      public void actionPerformed (ActionEvent event){
	    	buildArrays();
	    	repaint();
	      }
	   }
}
