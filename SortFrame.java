import javax.swing.JFrame;

public class SortFrame extends JFrame {
// git update
	public SortFrame(){
		super("Two Sorts");
		setVisible(true);
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		add(new SortPanel());
		pack();
	}

	public static void main(String[] args) {
		new SortFrame();
	}
}
