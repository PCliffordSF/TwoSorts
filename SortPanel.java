
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
	JButton buildArraysButton;
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

	private static boolean changeSelectionColor;
	private static boolean changeSelectionColorTwo;
	
	//////////////////////// Constructor /////////////////////////////////////

	public SortPanel(){
		setPreferredSize(new Dimension(800,500));
		setLayout(new FlowLayout(FlowLayout.LEFT));
		sortArrayButton = new JButton("Sort The Arrays");
		buildArraysButton = new JButton("Build The Arrays");
		sortArrayButton.addActionListener(new ButtonListener());
		buildArraysButton.addActionListener(new ButtonListener());
		add(sortArrayButton);
		add(buildArraysButton);
		insertionSortIndex = 1;
		changeSelectionColor = false;
		changeSelectionColorTwo = false;
		sortOneArray = generateArray(30);
		sortTwoArray = generateArray(30);
		
	}
	
	///////////////////////////// Graphics object /////////////////////////////

	public void paintComponent(Graphics g){
		super.paintComponent (g);

		spacer = 10;
		leftIndent = 100;

		g.setColor(Color.BLACK);
		g.fillRect(0,0,getWidth(), getHeight());
		
       	if(isSorted(sortOneArray) == true){
       		g.setColor(Color.YELLOW);
       		g.drawString("Array one is sorted!!!!", 10, 50);
       	}
       	if(isSorted(sortTwoArray) == true){
       		g.setColor(Color.YELLOW);
       		g.drawString("Array two is sorted!!!!", 10, 80);
       	}

       	for(int i = 0; i < 30; i++){
       		if((i == changeIndexColor | i == changeMinColor) & changeSelectionColor == true){
       			g.setColor(Color.WHITE);
       		}
       		else{
       			g.setColor(Color.GREEN);
       		}
       		g.fillRect(leftIndent + spacer + 20*i ,190 - sortOneArray[i], 10, sortOneArray[i]);
	      	
 
       		for(int j = 0; j < 30; j++){
       			if(j < (whiteLowerBound)){
       				g.setColor(Color.MAGENTA);
       			}
       			
       			else if(((j >= whiteLowerBound & j <= whiteUpperBound +1) | j == insertionSortKey) & changeSelectionColorTwo == true){
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
        int sortTest;
        
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
    
    public static void insertionSort (Comparable[] sortTwoArray){

    Comparable key = sortTwoArray[insertionSortIndex];
    int position = insertionSortIndex;
    

    //  Shift larger values to the right
    while (position > 0 && key.compareTo(sortTwoArray[position-1]) < 0){
    	whiteLowerBound = position - 1;
    	sortTwoArray[position] = sortTwoArray[position-1];
    	position--;
    }

    sortTwoArray[position] = key;
    
    if(position <= 29){
    changeSelectionColorTwo = true;
    }
    else{
    	changeSelectionColor = false;
    }
    //insertionSortKey = (int) key;
    insertionSortKey = position + whiteUpperBound;
    
    
    insertionSortIndex = insertionSortIndex + 1;
    whiteUpperBound = position + whiteLowerBound +1;
    //System.out.println("whiteUpperBound" + whiteUpperBound);
    //System.out.println("whiteLowerBound" + whiteLowerBound);
    //System.out.println("lower + upper" + insertionSortKey);
    }
       	 
    public Integer[] generateArray(int numElements){
        
        Integer[] randomInts = new Integer[numElements];
        for(int i = 0; i < numElements ; ++i){
            randomInts[i] = i*5 +5;
        }
        // Do the Knuth shuffle
        for(int i = 0; i < numElements; ++i){
            int randomIndex = (int)Math.floor(Math.random() * (i + 1));
            Integer temp = randomInts[i];
            randomInts[i] = randomInts[randomIndex];
            randomInts[randomIndex] = temp;
        }
        return randomInts;
    }
    
    public boolean isSorted(Comparable[] arrayToBeChecked){
    	int counter = 0;
    	for(int i = 0; i < arrayToBeChecked.length - 1; i++){
    		if(arrayToBeChecked[i].compareTo(arrayToBeChecked[i+1]) <= 0){
    			counter = counter + 1;
    		}
    	}
    	if(counter == arrayToBeChecked.length-1){
    		return true;
    	}
    	else{
    		return false;    	
    	}   
    }
    
	
	   private class ButtonListener implements ActionListener{
	      public void actionPerformed (ActionEvent event){
	    	  
	    	JButton buttonClicked = (JButton)event.getSource();
	    	
	    	if(buttonClicked == buildArraysButton){
	    		sortOneArray = generateArray(30);
	    		sortTwoArray = generateArray(30);
	    		repaint();
	    		index = 0;
	    		insertionSortIndex = 1;
	    		
	    	}
	    	else if(buttonClicked == sortArrayButton){
	    	    selectionSort(sortOneArray);
	    	    insertionSort(sortTwoArray);
	    	    repaint();
	    	}
	      }
	   }
}

