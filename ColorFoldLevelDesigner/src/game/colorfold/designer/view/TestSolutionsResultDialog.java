package game.colorfold.designer.view;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TestSolutionsResultDialog extends JDialog {

	private JPanel centerPanel;
	private JScrollPane testOutputScrollPane;
	private JTextArea testOutputTextArea;

	public TestSolutionsResultDialog(Frame frame) {
		super(frame);
		setModal(true);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		setLocation(500, 200);
		setSize(420, 500);
		getContentPane().add(getCenterPanel(), BorderLayout.CENTER);
	}

	public void appendTestOutputText(String text) {
		getTestOutputTextArea().append(text);
	}

	private JPanel getCenterPanel() {
		if (centerPanel == null) {
			centerPanel = new JPanel(new BorderLayout());
			centerPanel.add(getTestOutputScrollPane(), BorderLayout.CENTER);
		}
		return centerPanel;
	}

	private JScrollPane getTestOutputScrollPane() {
		if (testOutputScrollPane == null) {
			testOutputScrollPane = new JScrollPane(getTestOutputTextArea());
		}
		return testOutputScrollPane;
	}

	private JTextArea getTestOutputTextArea() {
		if (testOutputTextArea == null) {
			testOutputTextArea = new JTextArea();
			testOutputTextArea.setEditable(false);
		}
		return testOutputTextArea;
	}

}
