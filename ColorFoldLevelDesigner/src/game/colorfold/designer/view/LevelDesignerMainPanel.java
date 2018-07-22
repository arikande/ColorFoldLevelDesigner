package game.colorfold.designer.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.util.List;
import java.util.Map;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;

import game.colorfold.designer.model.Coordinate;

public class LevelDesignerMainPanel extends JPanel {

    private JPanel levelNavigationPanel;
    private JScrollPane levelNavigationScrollPane;
    private JList<File> levelNavigationList;
    private DefaultListModel<File> levelNavigationListModel;
    private JPanel selectedLevelPanel;
    private JPanel selectedLevelInfoPanel;
    private JPanel designModePanel;
    private JPanel swapSelectionPanel;
    private JPanel divideSelectionPanel;
    private JPanel duplicateSelectionPanel;
    private JPanel maximumMovesSelectionPanel;
    private ButtonGroup designModeButtonGroup;
    private JRadioButton designModeRadioButton;
    private JRadioButton playModeRadioButton;
    private JToggleButton recordToggleButton;
    private JLabel swapInfoLabel;
    private JSpinner swapCountSpinner;
    private SpinnerModel swapCountSpinnerModel;
    private JLabel divideInfoLabel;
    private JSpinner divideCountSpinner;
    private SpinnerModel divideCountSpinnerModel;
    private JLabel duplicateInfoLabel;
    private JSpinner duplicateCountSpinner;
    private SpinnerModel duplicateCountSpinnerModel;
    private JLabel maximumMovesInfoLabel;
    private JSpinner maximumMovesSpinner;
    private SpinnerModel maximumMovesSpinnerModel;
    private JPanel selectedLevelMainPanel;
    private JPanel puzzleContainerPanel;
    private JPanel puzzleContainerPageEndPanel;
    private JPanel puzzleTotalColorValuePanel;
    private JLabel puzzleTotalColorValueInfoLabel;
    private JLabel puzzleTotalColorValueLabel;
    private ColorFoldPanel puzzlePanel;
    private JPanel playFoldColorModePanel;
    private JToggleButton swapPlayModeToggleButton;
    private JToggleButton dividePlayModeToggleButton;
    private JToggleButton duplicatePlayModeToggleButton;
    private JButton reloadFromFileButton;
    private ButtonGroup playFoldColorModeButtonGroup;
    private JPanel solutionContainerPanel;
    private JPanel solutionContainerPageEndPanel;
    private ColorFoldPanel solutionPanel;
    private JPanel solutionTotalColorValuePanel;
    private JLabel solutionTotalColorValueInfoLabel;
    private JLabel solutionTotalColorValueLabel;
    private JPanel palettePanel;
    private ButtonGroup paletteButtonGroup;

    public LevelDesignerMainPanel() {
	super(new BorderLayout(30, 0));
	add(getLevelNavigationPanel(), BorderLayout.LINE_START);
	add(getSelectedLevelPanel(), BorderLayout.CENTER);
    }

    public void setPuzzleTotalColorValueLabelText(String text) {
	getPuzzleTotalColorValueLabel().setText(text);
    }

    public void setSolutionTotalColorValueLabelText(String text) {
	getSolutionTotalColorValueLabel().setText(text);
    }

    public void setReloadFromFileButtonAction(Action action) {
	getReloadFromFileButton().setAction(action);
    }

    public void setRecordToggleButtonAction(Action action) {
	getRecordToggleButton().setAction(action);
    }

    public void addSwapPlayModeToggleButtonActionListener(ActionListener actionListener) {
	getSwapPlayModeToggleButton().addActionListener(actionListener);
    }

    public void addDividePlayModeToggleButtonActionListener(ActionListener actionListener) {
	getDividePlayModeToggleButton().addActionListener(actionListener);
    }

    public void addDuplicatePlayModeToggleButtonActionListener(ActionListener actionListener) {
	getDuplicatePlayModeToggleButton().addActionListener(actionListener);
    }

    public void addDesignModeRadioButtonActionListener(ActionListener actionListener) {
	getDesignModeRadioButton().addActionListener(actionListener);
    }

    public void addPlayModeRadioButtonActionListener(ActionListener actionListener) {
	getPlayModeRadioButton().addActionListener(actionListener);
    }

    public void addSwapCountSpinnerChangeListener(ChangeListener listener) {
	getSwapCountSpinner().addChangeListener(listener);
    }

    public void addDivideCountSpinnerChangeListener(ChangeListener listener) {
	getDivideCountSpinner().addChangeListener(listener);
    }

    public void addDuplicateCountSpinnerChangeListener(ChangeListener listener) {
	getDuplicateCountSpinner().addChangeListener(listener);
    }

    public void addMaximumMovesSpinnerChangeListener(ChangeListener listener) {
	getMaximumMovesSpinner().addChangeListener(listener);
    }

    public void setSwapCountSpinnerValue(int value) {
	getSwapCountSpinner().setValue(value);
    }

    public void setSwapCountSpinnerEnabled(boolean enabled) {
	getSwapCountSpinner().setEnabled(enabled);
    }

    public void setSwapInfoLabelEnabled(boolean enabled) {
	getSwapInfoLabel().setEnabled(enabled);
    }

    public void setDivideCountSpinnerValue(int value) {
	getDivideCountSpinner().setValue(value);
    }

    public void setDivideCountSpinnerEnabled(boolean enabled) {
	getDivideCountSpinner().setEnabled(enabled);
    }

    public void setDivideInfoLabelEnabled(boolean enabled) {
	getDivideInfoLabel().setEnabled(enabled);
    }

    public void setDuplicateCountSpinnerValue(int value) {
	getDuplicateCountSpinner().setValue(value);
    }

    public void setDuplicateCountSpinnerEnabled(boolean enabled) {
	getDuplicateCountSpinner().setEnabled(enabled);
    }

    public void setDuplicateInfoLabelEnabled(boolean enabled) {
	getDuplicateInfoLabel().setEnabled(enabled);
    }

    public void setMaximumMovesSpinnerValue(int value) {
	getMaximumMovesSpinner().setValue(value);
    }

    public void setMaximumMovesInfoLabelEnabled(boolean enabled) {
	getMaximumMovesInfoLabel().setEnabled(enabled);
    }

    public void setMaximumMovesSpinnerEnabled(boolean enabled) {
	getMaximumMovesSpinner().setEnabled(enabled);
    }

    public void setColorData(List<Coordinate> validCoordinates, Map<Coordinate, Integer> puzzleCoordinateColorIdMap,
	    Map<Coordinate, Integer> solutionCoordinateColorIdMap) {
	getPuzzlePanel().setValidCoordinates(validCoordinates);
	getPuzzlePanel().setCoordinateColorIdMap(puzzleCoordinateColorIdMap);
	getSolutionPanel().setValidCoordinates(validCoordinates);
	getSolutionPanel().setCoordinateColorIdMap(solutionCoordinateColorIdMap);
	repaint();
    }

    public Coordinate getPuzzlePanelCoordinateAtPoint(Point point) {
	return getPuzzlePanel().getCoordinateAtPoint(point);
    }

    public Coordinate getSolutionPanelCoordinateAtPoint(Point point) {
	return getSolutionPanel().getCoordinateAtPoint(point);
    }

    public void addPuzzlePanelMouseAdapter(MouseAdapter mouseAdapter) {
	getPuzzlePanel().addMouseListener(mouseAdapter);
	getPuzzlePanel().addMouseMotionListener(mouseAdapter);
    }

    public void addSolutionPanelMouseAdapter(MouseAdapter mouseAdapter) {
	getSolutionPanel().addMouseListener(mouseAdapter);
	getSolutionPanel().addMouseMotionListener(mouseAdapter);
    }

    public void clearLevelFileToNavigationList() {
	getLevelNavigationList().removeAll();
	getLevelNavigationListModel().removeAllElements();
	getLevelNavigationListModel().clear();
    }

    public void addLevelFileToNavigationList(File file) {
	getLevelNavigationListModel().addElement(file);
    }

    public void setNavigationListSelectedIndex(int index) {
	getLevelNavigationList().setSelectedIndex(index);
    }

    public void addLevelNavigationListSelectionListener(ListSelectionListener listSelectionListener) {
	getLevelNavigationList().addListSelectionListener(listSelectionListener);
    }

    public void addColorButtonToPalette(String text, Color color, ActionListener actionListener, boolean selected) {
	JToggleButton colorButton = new JToggleButton();
	colorButton.setText(text);
	colorButton.setBackground(color);
	colorButton.addActionListener(actionListener);
	colorButton.setSelected(selected);
	getPaletteButtonGroup().add(colorButton);
	getPalettePanel().add(colorButton);
    }

    public void setSwapPlayModeToggleButtonEnabled(boolean enabled) {
	getSwapPlayModeToggleButton().setEnabled(enabled);
    }

    public void setDividePlayModeToggleButtonEnabled(boolean enabled) {
	getDividePlayModeToggleButton().setEnabled(enabled);
    }

    public void setDuplicatePlayModeToggleButtonEnabled(boolean enabled) {
	getDuplicatePlayModeToggleButton().setEnabled(enabled);
    }

    private JPanel getLevelNavigationPanel() {
	if (levelNavigationPanel == null) {
	    levelNavigationPanel = new JPanel(new BorderLayout());
	    levelNavigationPanel.setPreferredSize(new Dimension(350, 0));
	    levelNavigationPanel.add(getLevelNavigationScrollPane());
	}
	return levelNavigationPanel;
    }

    private JPanel getSelectedLevelPanel() {
	if (selectedLevelPanel == null) {
	    selectedLevelPanel = new JPanel(new BorderLayout(0, 30));
	    selectedLevelPanel.add(getSelectedLevelInfoPanel(), BorderLayout.PAGE_START);
	    selectedLevelPanel.add(getSelectedLevelMainPanel(), BorderLayout.CENTER);
	    selectedLevelPanel.add(getPalettePanel(), BorderLayout.PAGE_END);
	}
	return selectedLevelPanel;
    }

    private JScrollPane getLevelNavigationScrollPane() {
	if (levelNavigationScrollPane == null) {
	    levelNavigationScrollPane = new JScrollPane(getLevelNavigationList());
	}
	return levelNavigationScrollPane;
    }

    private JList<File> getLevelNavigationList() {
	if (levelNavigationList == null) {
	    levelNavigationList = new JList<File>(getLevelNavigationListModel());
	    levelNavigationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	return levelNavigationList;
    }

    private DefaultListModel<File> getLevelNavigationListModel() {
	if (levelNavigationListModel == null) {
	    levelNavigationListModel = new DefaultListModel<File>();
	}
	return levelNavigationListModel;
    }

    private JPanel getSelectedLevelInfoPanel() {
	if (selectedLevelInfoPanel == null) {
	    selectedLevelInfoPanel = new JPanel(new GridLayout(1, 0, 30, 0));
	    selectedLevelInfoPanel.add(getDesignModePanel());
	    selectedLevelInfoPanel.add(getRecordToggleButton());
	    selectedLevelInfoPanel.add(getSwapSelectionPanel());
	    selectedLevelInfoPanel.add(getDivideSelectionPanel());
	    selectedLevelInfoPanel.add(getDuplicateSelectionPanel());
	    selectedLevelInfoPanel.add(getMaximumMovesSelectionPanel());
	}
	return selectedLevelInfoPanel;
    }

    private JPanel getDesignModePanel() {
	if (designModePanel == null) {
	    designModePanel = new JPanel(new GridLayout(1, 0));
	    designModePanel.add(new JLabel("Mode"));
	    designModePanel.add(getDesignModeRadioButton());
	    designModePanel.add(getPlayModeRadioButton());
	    designModePanel.setBackground(Color.LIGHT_GRAY);
	    getDesignModeButtonGroup().add(getDesignModeRadioButton());
	    getDesignModeButtonGroup().add(getPlayModeRadioButton());
	}
	return designModePanel;
    }

    private ButtonGroup getDesignModeButtonGroup() {
	if (designModeButtonGroup == null) {
	    designModeButtonGroup = new ButtonGroup();
	}
	return designModeButtonGroup;
    }

    private JRadioButton getDesignModeRadioButton() {
	if (designModeRadioButton == null) {
	    designModeRadioButton = new JRadioButton("Design");
	    designModeRadioButton.setOpaque(false);
	    designModeRadioButton.setSelected(true);
	}
	return designModeRadioButton;
    }

    private JRadioButton getPlayModeRadioButton() {
	if (playModeRadioButton == null) {
	    playModeRadioButton = new JRadioButton("Play");
	    playModeRadioButton.setOpaque(false);
	}
	return playModeRadioButton;
    }

    private JToggleButton getRecordToggleButton() {
	if (recordToggleButton == null) {
	    recordToggleButton = new JToggleButton("Record");
	    playModeRadioButton.setOpaque(false);
	}
	return recordToggleButton;
    }

    private JPanel getSwapSelectionPanel() {
	if (swapSelectionPanel == null) {
	    swapSelectionPanel = new JPanel(new GridLayout(1, 0));
	    swapSelectionPanel.add(getSwapInfoLabel());
	    swapSelectionPanel.add(getSwapCountSpinner());
	}
	return swapSelectionPanel;
    }

    private JPanel getDivideSelectionPanel() {
	if (divideSelectionPanel == null) {
	    divideSelectionPanel = new JPanel(new GridLayout(1, 0));
	    divideSelectionPanel.add(getDivideInfoLabel());
	    divideSelectionPanel.add(getDivideCountSpinner());
	}
	return divideSelectionPanel;
    }

    private JPanel getDuplicateSelectionPanel() {
	if (duplicateSelectionPanel == null) {
	    duplicateSelectionPanel = new JPanel(new GridLayout(1, 0));
	    duplicateSelectionPanel.add(getDuplicateInfoLabel());
	    duplicateSelectionPanel.add(getDuplicateCountSpinner());
	}
	return duplicateSelectionPanel;
    }

    private JPanel getMaximumMovesSelectionPanel() {
	if (maximumMovesSelectionPanel == null) {
	    maximumMovesSelectionPanel = new JPanel(new GridLayout(1, 0));
	    maximumMovesSelectionPanel.add(getMaximumMovesInfoLabel());
	    maximumMovesSelectionPanel.add(getMaximumMovesSpinner());
	}
	return maximumMovesSelectionPanel;
    }

    private JLabel getSwapInfoLabel() {
	if (swapInfoLabel == null) {
	    swapInfoLabel = new JLabel("Swap");
	}
	return swapInfoLabel;
    }

    private JSpinner getSwapCountSpinner() {
	if (swapCountSpinner == null) {
	    swapCountSpinner = new JSpinner(getSwapCountSpinnerModel());
	    swapCountSpinner.setEditor(new JSpinner.DefaultEditor(swapCountSpinner));
	}
	return swapCountSpinner;
    }

    private SpinnerModel getSwapCountSpinnerModel() {
	if (swapCountSpinnerModel == null) {
	    swapCountSpinnerModel = new SpinnerNumberModel(0, 0, 1, 1);
	}
	return swapCountSpinnerModel;
    }

    private JLabel getDivideInfoLabel() {
	if (divideInfoLabel == null) {
	    divideInfoLabel = new JLabel("Divide");
	}
	return divideInfoLabel;
    }

    private JSpinner getDivideCountSpinner() {
	if (divideCountSpinner == null) {
	    divideCountSpinner = new JSpinner(getDivideCountSpinnerModel());
	    divideCountSpinner.setEditor(new JSpinner.DefaultEditor(divideCountSpinner));
	}
	return divideCountSpinner;
    }

    private SpinnerModel getDivideCountSpinnerModel() {
	if (divideCountSpinnerModel == null) {
	    divideCountSpinnerModel = new SpinnerNumberModel(0, 0, 1, 1);
	}
	return divideCountSpinnerModel;
    }

    private JLabel getDuplicateInfoLabel() {
	if (duplicateInfoLabel == null) {
	    duplicateInfoLabel = new JLabel("Duplicate");
	}
	return duplicateInfoLabel;
    }

    private JSpinner getDuplicateCountSpinner() {
	if (duplicateCountSpinner == null) {
	    duplicateCountSpinner = new JSpinner(getDuplicateCountSpinnerModel());
	    duplicateCountSpinner.setEditor(new JSpinner.DefaultEditor(duplicateCountSpinner));
	}
	return duplicateCountSpinner;
    }

    private SpinnerModel getDuplicateCountSpinnerModel() {
	if (duplicateCountSpinnerModel == null) {
	    duplicateCountSpinnerModel = new SpinnerNumberModel(0, 0, 1, 1);
	}
	return duplicateCountSpinnerModel;
    }

    private JLabel getMaximumMovesInfoLabel() {
	if (maximumMovesInfoLabel == null) {
	    maximumMovesInfoLabel = new JLabel("Max moves");
	}
	return maximumMovesInfoLabel;
    }

    private JSpinner getMaximumMovesSpinner() {
	if (maximumMovesSpinner == null) {
	    maximumMovesSpinner = new JSpinner(getMaximumMovesSpinnerModel());
	    maximumMovesSpinner.setEditor(new JSpinner.DefaultEditor(maximumMovesSpinner));
	}
	return maximumMovesSpinner;
    }

    private SpinnerModel getMaximumMovesSpinnerModel() {
	if (maximumMovesSpinnerModel == null) {
	    maximumMovesSpinnerModel = new SpinnerNumberModel(1, 1, 50, 1);
	}
	return maximumMovesSpinnerModel;
    }

    private JPanel getSelectedLevelMainPanel() {
	if (selectedLevelMainPanel == null) {
	    selectedLevelMainPanel = new JPanel(new GridLayout(1, 0));
	    selectedLevelMainPanel.add(getPuzzleContainerPanel());
	    selectedLevelMainPanel.add(getSolutionContainerPanel());
	}
	return selectedLevelMainPanel;
    }

    private JPanel getPuzzleContainerPanel() {
	if (puzzleContainerPanel == null) {
	    puzzleContainerPanel = new JPanel(new BorderLayout());
	    puzzleContainerPanel.setBackground(new Color(243, 255, 255));
	    puzzleContainerPanel.add(getPuzzlePanel(), BorderLayout.CENTER);
	    puzzleContainerPanel.add(getPuzzleContainerPageEndPanel(), BorderLayout.PAGE_END);
	}
	return puzzleContainerPanel;
    }

    private JPanel getPuzzleContainerPageEndPanel() {
	if (puzzleContainerPageEndPanel == null) {
	    puzzleContainerPageEndPanel = new JPanel(new BorderLayout());
	    puzzleContainerPageEndPanel.setOpaque(false);
	    puzzleContainerPageEndPanel.add(getPlayFoldColorModePanel(), BorderLayout.CENTER);
	    puzzleContainerPageEndPanel.add(getPuzzleTotalColorValuePanel(), BorderLayout.PAGE_END);
	}
	return puzzleContainerPageEndPanel;
    }

    private JPanel getPuzzleTotalColorValuePanel() {
	if (puzzleTotalColorValuePanel == null) {
	    puzzleTotalColorValuePanel = new JPanel();
	    puzzleTotalColorValuePanel.setOpaque(false);
	    puzzleTotalColorValuePanel.add(getPuzzleTotalColorValueInfoLabel());
	    puzzleTotalColorValuePanel.add(getPuzzleTotalColorValueLabel());
	}
	return puzzleTotalColorValuePanel;
    }

    private JLabel getPuzzleTotalColorValueInfoLabel() {
	if (puzzleTotalColorValueInfoLabel == null) {
	    puzzleTotalColorValueInfoLabel = new JLabel("Total color value :");
	}
	return puzzleTotalColorValueInfoLabel;
    }

    private JLabel getPuzzleTotalColorValueLabel() {
	if (puzzleTotalColorValueLabel == null) {
	    puzzleTotalColorValueLabel = new JLabel("-");
	}
	return puzzleTotalColorValueLabel;
    }

    private ColorFoldPanel getPuzzlePanel() {
	if (puzzlePanel == null) {
	    puzzlePanel = new ColorFoldPanel();
	    puzzlePanel.setOpaque(false);
	}
	return puzzlePanel;
    }

    private JPanel getPlayFoldColorModePanel() {
	if (playFoldColorModePanel == null) {
	    playFoldColorModePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    playFoldColorModePanel.setOpaque(false);
	    playFoldColorModePanel.add(getSwapPlayModeToggleButton());
	    playFoldColorModePanel.add(getDividePlayModeToggleButton());
	    playFoldColorModePanel.add(getDuplicatePlayModeToggleButton());
	    playFoldColorModePanel.add(getReloadFromFileButton());
	    getPlayFoldColorModeButtonGroup().add(getSwapPlayModeToggleButton());
	    getPlayFoldColorModeButtonGroup().add(getDividePlayModeToggleButton());
	    getPlayFoldColorModeButtonGroup().add(getDuplicatePlayModeToggleButton());
	}
	return playFoldColorModePanel;
    }

    private JToggleButton getSwapPlayModeToggleButton() {
	if (swapPlayModeToggleButton == null) {
	    swapPlayModeToggleButton = new JToggleButton("Swap");
	    swapPlayModeToggleButton.setSelected(true);
	    swapPlayModeToggleButton.setEnabled(false);
	}
	return swapPlayModeToggleButton;
    }

    private JToggleButton getDividePlayModeToggleButton() {
	if (dividePlayModeToggleButton == null) {
	    dividePlayModeToggleButton = new JToggleButton("Divide");
	    dividePlayModeToggleButton.setEnabled(false);
	}
	return dividePlayModeToggleButton;
    }

    private JToggleButton getDuplicatePlayModeToggleButton() {
	if (duplicatePlayModeToggleButton == null) {
	    duplicatePlayModeToggleButton = new JToggleButton("Duplicate");
	    duplicatePlayModeToggleButton.setEnabled(false);
	}
	return duplicatePlayModeToggleButton;
    }

    private ButtonGroup getPlayFoldColorModeButtonGroup() {
	if (playFoldColorModeButtonGroup == null) {
	    playFoldColorModeButtonGroup = new ButtonGroup();
	}
	return playFoldColorModeButtonGroup;
    }

    private JButton getReloadFromFileButton() {
	if (reloadFromFileButton == null) {
	    reloadFromFileButton = new JButton();
	}
	return reloadFromFileButton;
    }

    private JPanel getSolutionContainerPanel() {
	if (solutionContainerPanel == null) {
	    solutionContainerPanel = new JPanel(new BorderLayout());
	    solutionContainerPanel.setBackground(new Color(240, 255, 240));
	    solutionContainerPanel.add(getSolutionPanel(), BorderLayout.CENTER);
	    solutionContainerPanel.add(getSolutionContainerPageEndPanel(), BorderLayout.PAGE_END);
	}
	return solutionContainerPanel;
    }

    private ColorFoldPanel getSolutionPanel() {
	if (solutionPanel == null) {
	    solutionPanel = new ColorFoldPanel();
	    solutionPanel.setOpaque(false);
	}
	return solutionPanel;
    }

    private JPanel getSolutionContainerPageEndPanel() {
	if (solutionContainerPageEndPanel == null) {
	    solutionContainerPageEndPanel = new JPanel(new GridLayout(0, 1));
	    solutionContainerPageEndPanel.setOpaque(false);
	    solutionContainerPageEndPanel.add(getSolutionTotalColorValuePanel());
	}
	return solutionContainerPageEndPanel;
    }

    private JPanel getSolutionTotalColorValuePanel() {
	if (solutionTotalColorValuePanel == null) {
	    solutionTotalColorValuePanel = new JPanel();
	    solutionTotalColorValuePanel.setOpaque(false);
	    solutionTotalColorValuePanel.add(getSolutionTotalColorValueInfoLabel());
	    solutionTotalColorValuePanel.add(getSolutionTotalColorValueLabel());
	}
	return solutionTotalColorValuePanel;
    }

    private JLabel getSolutionTotalColorValueInfoLabel() {
	if (solutionTotalColorValueInfoLabel == null) {
	    solutionTotalColorValueInfoLabel = new JLabel("Total color value :");
	}
	return solutionTotalColorValueInfoLabel;
    }

    private JLabel getSolutionTotalColorValueLabel() {
	if (solutionTotalColorValueLabel == null) {
	    solutionTotalColorValueLabel = new JLabel("-");
	}
	return solutionTotalColorValueLabel;
    }

    private JPanel getPalettePanel() {
	if (palettePanel == null) {
	    palettePanel = new JPanel(new GridLayout(2, 5));
	}
	return palettePanel;
    }

    private ButtonGroup getPaletteButtonGroup() {
	if (paletteButtonGroup == null) {
	    paletteButtonGroup = new ButtonGroup();
	}
	return paletteButtonGroup;
    }
}
