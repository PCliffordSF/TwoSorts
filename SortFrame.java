//********************************************************************
//  SortFrame.java       Authors: Paul Clifford/ Patrick Kelly / Amy Dos Santos
//
//  Constructs the frame and panel for Two Sorts
//  Assigment number 7, Due November 24th, CS 111B, 1:00pm section
//********************************************************************

// Algorithm
// 1. Instantiate new Frame and new Panel


import javax.swing.JFrame;

public class SortFrame extends JFrame {

	TwoSortsPanel twoSortsPanel;

	public SortFrame(TwoSortsPanel twoSortsPanel){
		super("Two Sorts");
		this.twoSortsPanel = twoSortsPanel;
		setVisible(true);
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		add(twoSortsPanel);
		pack();
	}

	public static void main(String[] args) {
		new SortFrame(new TwoSortsPanel());
	}
}
