import javax.swing.JFrame;

public class SortFrame extends JFrame {

	SortPanel sortPanel;

	public SortFrame(SortPanel sortPanel){
		super("Two Sorts");
		this.sortPanel = sortPanel;
		setVisible(true);
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		add(sortPanel);
		pack();
	}

	public static void main(String[] args) {
		new SortFrame(new SortPanel());
	}
}
