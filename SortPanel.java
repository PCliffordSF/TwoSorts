
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SortPanel extends JPanel {

	JButton sortArrayButton;
	int spacer;
	int leftIndent;
	int rInt;
	private static Integer insertionSortIndex;
	Integer[] sortOneArray = new Integer[30];
	Integer[] sortTwoArray = new Integer[30];
	private int index = 0;
	private int changeIndexColor;
	private int changeMinColor;
	private static int whiteUpperBound;
	private static int whiteLowerBound;
	private static int insertionSortKey;

	private boolean changeSelectionColor;
	private boolean changeInsertionColor;
	
	//////////////////////// Constructor /////////////////////////////////////

	public SortPanel(){
		setPreferredSize(new Dimension(800,500));
		setLayout(new FlowLayout(FlowLayout.LEFT));
		sortArrayButton = new JButton("Sort The Arrays");
		sortArrayButton.addActionListener(new ButtonListener());
		add(sortArrayButton);
		buildArrays();
		insertionSortIndex = 1;
		changeSelectionColor = false;
	}
	
	///////////////////////////// Graphics object /////////////////////////////

	public void paintComponent(Graphics g){
		super.paintComponent (g);

		spacer = 10;
		leftIndent = 100;

		g.setColor(Color.BLACK);
		g.fillRect(0,0,getWidth(), getHeight());
		//g.setColor(Color.GREEN);

       	for(int i = 0; i < 30; i++){
       		if((i == changeIndexColor | i == changeMinColor) & changeSelectionColor == true){
       			g.setColor(Color.WHITE);
       		}
       		else{
       			g.setColor(Color.GREEN);
       		}
       		g.fillRect(leftIndent + spacer + 20*i ,190 - sortOneArray[i], 10, sortOneArray[i]);
	      	
 
       		for(int j = 0; j < 30; j++){
       			if(j <= (whiteLowerBound)){
       				g.setColor(Color.MAGENTA);
       			}
       			
       			else if((j > whiteLowerBound & j <= whiteUpperBound +1) | j == insertionSortKey){
           			g.setColor(Color.WHITE);
           		}
           		else{
           			g.setColor(Color.MAGENTA);
           		}
       			g.fillRect(leftIndent + spacer + 20*j ,200 , 10, sortTwoArray[j]);
       		} 
       	}
		
	}
	
	//////////////// Selection Sort method /////////////////////////////////////
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
            changeSelectionColor = true;
            if(min == index -1){
            changeSelectionColor = false;
            }      
	}
	
	///////////////// Insertion Sort method /////////////////////////////////////
    
    public static void insertionSort (Comparable[] sortTwoArray)
    {

    Comparable key = sortTwoArray[insertionSortIndex];
    int position = insertionSortIndex;
    

    //  Shift larger values to the right
    while (position > 0 && key.compareTo(sortTwoArray[position-1]) < 0){
    	whiteLowerBound = position - 1;
    	sortTwoArray[position] = sortTwoArray[position-1];
    	position--;
    }

    sortTwoArray[position] = key;
    insertionSortKey = (int) key;
    insertionSortIndex = insertionSortIndex + 1;
    whiteUpperBound = position + whiteLowerBound;
    System.out.println("whiteUpperBound" + whiteUpperBound);
    System.out.println("whiteLowerBound" + whiteLowerBound);
    System.out.println("insertionSortKey" + insertionSortKey);
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
	    	insertionSort(sortTwoArray);
	    	repaint();
	      }
	   }
}

