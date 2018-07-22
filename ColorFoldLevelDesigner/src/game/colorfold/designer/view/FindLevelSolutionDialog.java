package game.colorfold.designer.view;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class FindLevelSolutionDialog extends JDialog {

    private JPanel centerPanel;
    private JPanel infoPanel;
    private JPanel solutionOutputPanel;
    private JScrollPane solutionOutputScrollPane;
    private JTextArea solutionOutputTextArea;
    private JPanel pageEndPanel;

    private JLabel solutionMovesLabel;
    private JLabel solutionMovesValueLabel;
    private JLabel currentDepthLabel;
    private JLabel currentDepthValueLabel;
    private JLabel numberOfTriesLabel;
    private JLabel numberOfTriesValueLabel;
    private JLabel durationLabel;
    private JLabel durationValueLabel;
    private JButton confirmButton;
    private JButton writeSolutionToFileButton;
    private JButton cancelButton;

    public FindLevelSolutionDialog(Frame frame) {
	super(frame);
	setModal(true);
	setLocationRelativeTo(null);
	getContentPane().setLayout(new BorderLayout());
	setSize(350, 300);
	getContentPane().add(getCenterPanel(), BorderLayout.CENTER);
	getContentPane().add(getPageEndPanel(), BorderLayout.PAGE_END);
    }

    public void setNumberOfTriesValueLabelText(String text) {
	getNumberOfTriesValueLabel().setText(text);
    }

    public void setCurrentDepthValueLabelText(String text) {
	getCurrentDepthValueLabel().setText(text);
    }

    public void setSolutionMovesValueLabelText(String text) {
	getSolutionMovesValueLabel().setText(text);
    }

    public void setDurationValueLabelText(String text) {
	getDurationValueLabel().setText(text);
    }

    public void appendSolutionOutputText(String text) {
	getSolutionOutputTextArea().append(text);
    }

    public void setConfirmButtonAction(Action action) {
	getConfirmButton().setAction(action);
    }

    public void setWriteSolutionToFileButtonAction(Action action) {
	getWriteSolutionToFileButton().setAction(action);
    }

    public void setCancelButtonAction(Action action) {
	getCancelButton().setAction(action);
    }

    public void setConfirmButtonVisible(boolean visible) {
	getConfirmButton().setVisible(visible);
    }

    public void setWriteSolutionToFileButtonVisible(boolean visible) {
	getWriteSolutionToFileButton().setVisible(visible);
    }

    public void setCancelButtonVisible(boolean visible) {
	getCancelButton().setVisible(visible);
    }

    public void setCancelButtonText(String text) {
	getCancelButton().setText(text);
    }

    private JPanel getCenterPanel() {
	if (centerPanel == null) {
	    centerPanel = new JPanel(new GridLayout(0, 1));
	    centerPanel.add(getInfoPanel());
	    centerPanel.add(getSolutionOutputPanel());
	}
	return centerPanel;
    }

    private JPanel getInfoPanel() {
	if (infoPanel == null) {
	    infoPanel = new JPanel(new GridLayout(0, 2));
	    infoPanel.add(getNumberOfTriesLabel());
	    infoPanel.add(getNumberOfTriesValueLabel());
	    infoPanel.add(getCurrentDepthLabel());
	    infoPanel.add(getCurrentDepthValueLabel());
	    infoPanel.add(getSolutionMovesLabel());
	    infoPanel.add(getSolutionMovesValueLabel());
	    infoPanel.add(getDurationLabel());
	    infoPanel.add(getDurationValueLabel());
	}
	return infoPanel;
    }

    private JPanel getSolutionOutputPanel() {
	if (solutionOutputPanel == null) {
	    solutionOutputPanel = new JPanel(new BorderLayout());
	    solutionOutputPanel.add(getSolutionOutputScrollPane());
	}
	return solutionOutputPanel;
    }

    private JScrollPane getSolutionOutputScrollPane() {
	if (solutionOutputScrollPane == null) {
	    solutionOutputScrollPane = new JScrollPane(getSolutionOutputTextArea());
	}
	return solutionOutputScrollPane;
    }

    private JTextArea getSolutionOutputTextArea() {
	if (solutionOutputTextArea == null) {
	    solutionOutputTextArea = new JTextArea();
	    solutionOutputTextArea.setEditable(false);
	}
	return solutionOutputTextArea;
    }

    private JPanel getPageEndPanel() {
	if (pageEndPanel == null) {
	    pageEndPanel = new JPanel();
	    pageEndPanel.add(getConfirmButton());
	    pageEndPanel.add(getWriteSolutionToFileButton());
	    pageEndPanel.add(getCancelButton());
	}
	return pageEndPanel;
    }

    private JLabel getNumberOfTriesLabel() {
	if (numberOfTriesLabel == null) {
	    numberOfTriesLabel = new JLabel("Moves :");
	}
	return numberOfTriesLabel;
    }

    private JLabel getNumberOfTriesValueLabel() {
	if (numberOfTriesValueLabel == null) {
	    numberOfTriesValueLabel = new JLabel();
	}
	return numberOfTriesValueLabel;
    }

    private JLabel getSolutionMovesLabel() {
	if (solutionMovesLabel == null) {
	    solutionMovesLabel = new JLabel("Solution moves :");
	}
	return solutionMovesLabel;
    }

    private JLabel getSolutionMovesValueLabel() {
	if (solutionMovesValueLabel == null) {
	    solutionMovesValueLabel = new JLabel("-");
	}
	return solutionMovesValueLabel;
    }

    private JLabel getCurrentDepthLabel() {
	if (currentDepthLabel == null) {
	    currentDepthLabel = new JLabel("Current depth :");
	}
	return currentDepthLabel;
    }

    private JLabel getCurrentDepthValueLabel() {
	if (currentDepthValueLabel == null) {
	    currentDepthValueLabel = new JLabel("-");
	}
	return currentDepthValueLabel;
    }

    private JLabel getDurationLabel() {
	if (durationLabel == null) {
	    durationLabel = new JLabel("Duration :");
	}
	return durationLabel;
    }

    private JLabel getDurationValueLabel() {
	if (durationValueLabel == null) {
	    durationValueLabel = new JLabel("-");
	}
	return durationValueLabel;
    }

    private JButton getConfirmButton() {
	if (confirmButton == null) {
	    confirmButton = new JButton();
	}
	return confirmButton;
    }

    private JButton getWriteSolutionToFileButton() {
	if (writeSolutionToFileButton == null) {
	    writeSolutionToFileButton = new JButton();
	}
	return writeSolutionToFileButton;
    }

    private JButton getCancelButton() {
	if (cancelButton == null) {
	    cancelButton = new JButton();
	}
	return cancelButton;
    }

}
