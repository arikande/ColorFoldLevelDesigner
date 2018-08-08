package game.colorfold.designer.view;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class NewLevelDialog extends JDialog {

	private JLabel levelIdLabel;
	private JSpinner levelIdSpinner;
	private SpinnerNumberModel levelIdSpinnerModel;
	private JPanel centerPanel;
	private JPanel pageEndPanel;
	private JButton confirmButton;
	private JButton cancelButton;

	public NewLevelDialog(Frame frame) {
		super(frame);
		setModal(true);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		setSize(220, 120);
		getContentPane().add(getCenterPanel(), BorderLayout.CENTER);
		getContentPane().add(getPageEndPanel(), BorderLayout.PAGE_END);
	}

	public int getLevelIdSpinnerValue() {
		return (int) getLevelIdSpinner().getValue();
	}

	public void setLevelIdSpinnerValues(int minimum, int value) {
		getLevelIdSpinnerModel().setMinimum(minimum);
		getLevelIdSpinnerModel().setValue(value);
	}

	public void setConfirmButtonAction(Action action) {
		getConfirmButton().setAction(action);
	}

	public void setCancelButtonAction(Action action) {
		getCancelButton().setAction(action);
	}

	private JPanel getCenterPanel() {
		if (centerPanel == null) {
			centerPanel = new JPanel(new GridLayout(0, 2));
			centerPanel.add(getLevelIdLabel());
			centerPanel.add(getLevelIdSpinner());
		}
		return centerPanel;
	}

	private JPanel getPageEndPanel() {
		if (pageEndPanel == null) {
			pageEndPanel = new JPanel();
			pageEndPanel.add(getConfirmButton());
			pageEndPanel.add(getCancelButton());
		}
		return pageEndPanel;
	}

	private JLabel getLevelIdLabel() {
		if (levelIdLabel == null) {
			levelIdLabel = new JLabel("Level id");
		}
		return levelIdLabel;
	}

	private JSpinner getLevelIdSpinner() {
		if (levelIdSpinner == null) {
			levelIdSpinner = new JSpinner(getLevelIdSpinnerModel());
			levelIdSpinner.setEditor(new JSpinner.DefaultEditor(levelIdSpinner));
		}
		return levelIdSpinner;
	}

	private SpinnerNumberModel getLevelIdSpinnerModel() {
		if (levelIdSpinnerModel == null) {
			levelIdSpinnerModel = new SpinnerNumberModel(0, 0, 100, 1);
		}
		return levelIdSpinnerModel;
	}

	private JButton getConfirmButton() {
		if (confirmButton == null) {
			confirmButton = new JButton();
		}
		return confirmButton;
	}

	private JButton getCancelButton() {
		if (cancelButton == null) {
			cancelButton = new JButton();
		}
		return cancelButton;
	}
}
