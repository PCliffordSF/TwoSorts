
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SortPanel extends JPanel {

	private JButton sortArrayButton;
	private int spacer;
	private int leftIndent;
	private int rInt;
	private Integer[] sortOneArray = new Integer[30];
	private Integer[] sortTwoArray = new Integer[30];
	private int index = 0;
	private int changeIndexColor;
	private int changeMinColor;
	private boolean changeColor;

	public SortPanel(){
		setPreferredSize(new Dimension(800,500));
		setLayout(new FlowLayout(FlowLayout.LEFT));
		sortArrayButton = new JButton("Sort The Arrays");
		sortArrayButton.addActionListener(new ButtonListener());
		add(sortArrayButton);
		buildArrays();
		changeColor = false;
	}

	public void paintComponent(Graphics g){
		super.paintComponent (g);

		spacer = 10;
		leftIndent = 100;

		g.setColor(Color.BLACK);
		g.fillRect(0,0,getWidth(), getHeight());
		g.setColor(Color.GREEN);

       	for(int i = 0; i < 30; i++){
       		if((i == changeIndexColor | i == changeMinColor) & changeColor == true){
       			g.setColor(Color.WHITE);
       		}
			g.fillRect(leftIndent + spacer + 20*i ,190 - sortOneArray[i], 10, sortOneArray[i]);
			g.setColor(Color.GREEN);
		}

		g.setColor(Color.MAGENTA);
		for(int j = 0; j < 30; j++){
			g.fillRect(leftIndent + spacer + 20*j ,200 , 10, sortTwoArray[j]);
		}
}
	public void selectionSort (Comparable[] sortOneArray){
        int min;
        Integer tempArray;
        min = index;

        for (int scan = index + 1; scan < sortOneArray.length; scan++){
            if (sortOneArray[scan].compareTo(sortOneArray[min]) < 0){
            min = scan;
            }

        }
            // Swap the values
            tempArray = (Integer) sortOneArray[min];
            sortOneArray[min] = sortOneArray[index];
            sortOneArray[index] = tempArray;
            index = index + 1;
            changeIndexColor = index;
            changeMinColor = min;
            changeColor = true;
            if(min == index -1){
            	changeColor = false;
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
	    	selectionSort(sortOneArray);
	    	repaint();
	      }
	   }
}

//////////////// INSERTION SORT ALGORITHM ////////////////////////////////////////
/*
public static void insertionSort (Comparable[] list)
{
   for (int index = 1; index < list.length; index++)
   {
      Comparable key = list[index];
      int position = index;

      //  Shift larger values to the right
      while (position > 0 && key.compareTo(list[position-1]) < 0)
      {
         list[position] = list[position-1];
         position--;
      }

      list[position] = key;
   }
}
*/