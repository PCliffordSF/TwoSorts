//********************************************************************
//  TwoSortsFrame.java       Authors: Paul Clifford/ Patrick Kelly / Amy Dos Santos
//
//  Contains sorting algorithms/builds arrays/iterates and draws arrays as they sort
//  Assigment number 7, Due November 24th, CS 111B, 1:00pm section
//********************************************************************

// Algorithm
// 1. Builds random arrays of 30 unique integers
// 2. Draws arrays with Sort button and Build array button
// 3. Iterates through the selection and insertion sorts algorithms with each button generated action event object.
// 4. Counts the number of iterations of the algorithm and displays their total when the array is sorted.
// 5. Checks to see if the arrays are sorted after each iteration, and colors the arrays light grey when sorted.



import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class TwoSortsPanel extends JPanel {

	JButton sortArrayButton;
	JButton buildArraysButton;
	int spacer;
	int leftIndent;
	int rInt;
	Integer[] sortOneArray = new Integer[30];
	Integer[] sortTwoArray = new Integer[30];
	private int index = 0;
	private static int insertionSortIndex = 1;

	//////////////////////// Constructor /////////////////////////////////////

	public TwoSortsPanel(){
		setPreferredSize(new Dimension(1000,500));
		setLayout(new FlowLayout(FlowLayout.LEFT));
		sortArrayButton = new JButton("Sort The Arrays");
		buildArraysButton = new JButton("Build The Arrays");
		sortArrayButton.addActionListener(new ButtonListener());
		buildArraysButton.addActionListener(new ButtonListener());
		add(sortArrayButton);
		add(buildArraysButton);
		sortOneArray = generateArray(30);
		sortTwoArray = generateArray(30);

	}

	///////////////////////////// Graphics object to draw arrays and counters /////////////////////////////

	public void paintComponent(Graphics g){
		super.paintComponent (g);

		spacer = 10;
		leftIndent = 300;

		g.setColor(Color.BLACK);
		g.fillRect(0,0,getWidth(), getHeight());

////////////////////////////////////// Print the labels, sort count and sort confirmation //////////////////

       	if(isSorted(sortOneArray) == true){
       		g.setColor(Color.GREEN);
       		g.drawString("Selection Sort array is sorted after " + index + " iterations", 10, 150);
       	}
       	else{
       		g.setColor(Color.GREEN);
       		g.drawString("Selection Sort iteration number " + index, 10, 150);
       	}

       	if(isSorted(sortTwoArray) == true){
       		g.setColor(Color.MAGENTA);
       		g.drawString("Insertion Sort array is sorted after " + (insertionSortIndex -1) + " iterations", 10, 250);
       	}
       	else{
       		g.setColor(Color.MAGENTA);
       		g.drawString("Insertion Sort iteration number " + (insertionSortIndex -1), 10, 250);
       	}

  ///////////////////////  Draw the Selecton sort bars ////////////////////////////////////////////////////

       	for(int i = 0; i < 30; i++){
       		if(isSorted(sortOneArray) == true){
       			g.setColor(Color.LIGHT_GRAY);
       		}
       		else{
       			g.setColor(Color.GREEN);
       		}
       		g.fillRect(leftIndent + spacer + 20*i ,190 - sortOneArray[i], 10, sortOneArray[i]);
       	}

   ////////////////////// Draw the insertion sort bars ////////////////////////////////////////////////////

       		for(int j = 0; j < 30; j++){
       			if(isSorted(sortTwoArray) == true){
       				g.setColor(Color.LIGHT_GRAY);
       			}
       			else{
       			g.setColor(Color.MAGENTA);
       			}
       			g.fillRect(leftIndent + spacer + 20*j ,200 , 10, sortTwoArray[j]);
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
	}

	///////////////// Insertion Sort method /////////////////////////////////////

    public static void insertionSort (Comparable[] sortTwoArray){

        Comparable key = sortTwoArray[insertionSortIndex];
        int position = insertionSortIndex;


        //  Shift larger values to the right
        while (position > 0 && key.compareTo(sortTwoArray[position-1]) < 0){
    	    sortTwoArray[position] = sortTwoArray[position-1];
    	    position--;
        }
        sortTwoArray[position] = key;
        insertionSortIndex = insertionSortIndex + 1;
    }

///////////////////   Method to generate random arrays. /////////////////////////////////////////////////

    public Integer[] generateArray(int numElements){

        Integer[] randomInts = new Integer[numElements];
        for(int i = 0; i < numElements ; ++i){
            randomInts[i] = i*5 +5;
        }
        // Using the Fisher/Yates shuffle AKA the Knuth shuffle.
        // This randomizes the arrays which have been generated.
        for(int i = 0; i < numElements; ++i){
            int randomIndex = (int)Math.floor(Math.random() * (i + 1));
            Integer temp = randomInts[i];
            randomInts[i] = randomInts[randomIndex];
            randomInts[randomIndex] = temp;
        }
        return randomInts;
    }

  ////////////////////////// Method to check if arrays are sorted ///////////////////////////////////

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

  /////////////////////// Inner class to handle action events ///////////////////////////////////////////////////

	   private class ButtonListener implements ActionListener{
	      public void actionPerformed (ActionEvent event){



       ///////////// assigns a new button object to the button clicked to be used in logic /////////////////
	    	JButton buttonClicked = (JButton)event.getSource();

	    	if(buttonClicked == buildArraysButton){
	    		sortOneArray = generateArray(30);
	    		sortTwoArray = generateArray(30);
	    		repaint();
	    		index = 0;
	    		insertionSortIndex = 1;
	    	}

	    	else if(buttonClicked == sortArrayButton){
	    		if(isSorted(sortOneArray) == false){
	    	        selectionSort(sortOneArray);
	    		}
	    		if(isSorted(sortTwoArray) ==  false){
	    	        insertionSort(sortTwoArray);
	    	    }
	    	    repaint();
	    	}
	      }
	   }
}

