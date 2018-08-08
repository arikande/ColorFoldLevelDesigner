package game.colorfold.designer.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.util.List;
import java.util.Map;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToggleButton;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;

import game.colorfold.designer.model.Coordinate;

public class LevelDesignerFrame extends JFrame {
	private JMenuBar levelDesignerMenuBar;
	private JMenu fileMenu;
	private JMenuItem openLevelFilesFolderMenuItem;
	private JMenuItem saveLevelToFileMenuItem;
	private JMenuItem saveAllLevelsMenuItem;
	private JMenuItem quitLevelDesignerMenuItem;
	private JMenu levelMenu;
	private JMenuItem newLevelMenuItem;
	private JMenuItem incrementLevelIdMenuItem;
	private JMenuItem decrementLevelIdMenuItem;
	private JMenuItem setLevelIdToMenuItem;
	private JMenuItem levelTypesMenuItem;
	private JMenuItem findLevelSolutionMenuItem;
	private JMenu solutionMenu;
	private JMenuItem testSolutionsMenuItem;
	private LevelDesignerMainPanel levelDesignerMainPanel;

	public LevelDesignerFrame() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setJMenuBar(getLevelDesignerMenuBar());
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(getLevelDesignerMainPanel(), BorderLayout.CENTER);
	}

	public void repaintLevelDesignerMainPanel() {
		getLevelDesignerMainPanel().repaint();
	}

	public void setPuzzleTotalColorValueLabelText(String text) {
		getLevelDesignerMainPanel().setPuzzleTotalColorValueLabelText(text);
	}

	public void setSolutionTotalColorValueLabelText(String text) {
		getLevelDesignerMainPanel().setSolutionTotalColorValueLabelText(text);
	}

	public void setReloadFromFileButtonAction(Action action) {
		getLevelDesignerMainPanel().setReloadFromFileButtonAction(action);
	}

	public void setRecordToggleButtonAction(Action action) {
		getLevelDesignerMainPanel().setRecordToggleButtonAction(action);
	}

	public void addSwapPlayModeToggleButtonActionListener(ActionListener actionListener) {
		getLevelDesignerMainPanel().addSwapPlayModeToggleButtonActionListener(actionListener);
	}

	public void addDividePlayModeToggleButtonActionListener(ActionListener actionListener) {
		getLevelDesignerMainPanel().addDividePlayModeToggleButtonActionListener(actionListener);
	}

	public void addDuplicatePlayModeToggleButtonActionListener(ActionListener actionListener) {
		getLevelDesignerMainPanel().addDuplicatePlayModeToggleButtonActionListener(actionListener);
	}

	public void addDesignModeRadioButtonActionListener(ActionListener actionListener) {
		getLevelDesignerMainPanel().addDesignModeRadioButtonActionListener(actionListener);
	}

	public void addPlayModeRadioButtonActionListener(ActionListener actionListener) {
		getLevelDesignerMainPanel().addPlayModeRadioButtonActionListener(actionListener);
	}

	public void addSwapCountSpinnerChangeListener(ChangeListener listener) {
		getLevelDesignerMainPanel().addSwapCountSpinnerChangeListener(listener);
	}

	public void addDivideCountSpinnerChangeListener(ChangeListener listener) {
		getLevelDesignerMainPanel().addDivideCountSpinnerChangeListener(listener);
	}

	public void addDuplicateCountSpinnerChangeListener(ChangeListener listener) {
		getLevelDesignerMainPanel().addDuplicateCountSpinnerChangeListener(listener);
	}

	public void addMaximumMovesSpinnerChangeListener(ChangeListener listener) {
		getLevelDesignerMainPanel().addMaximumMovesSpinnerChangeListener(listener);
	}

	public void setSwapCountSpinnerValue(int value) {
		getLevelDesignerMainPanel().setSwapCountSpinnerValue(value);
	}

	public void setSwapCountSpinnerEnabled(boolean enabled) {
		getLevelDesignerMainPanel().setSwapCountSpinnerEnabled(enabled);
	}

	public void setSwapInfoLabelEnabled(boolean enabled) {
		getLevelDesignerMainPanel().setSwapInfoLabelEnabled(enabled);
	}

	public void setDivideCountSpinnerEnabled(boolean enabled) {
		getLevelDesignerMainPanel().setDivideCountSpinnerEnabled(enabled);
	}

	public void setDivideInfoLabelEnabled(boolean enabled) {
		getLevelDesignerMainPanel().setDivideInfoLabelEnabled(enabled);
	}

	public void setDuplicateCountSpinnerEnabled(boolean enabled) {
		getLevelDesignerMainPanel().setDuplicateCountSpinnerEnabled(enabled);
	}

	public void setDuplicateInfoLabelEnabled(boolean enabled) {
		getLevelDesignerMainPanel().setDuplicateInfoLabelEnabled(enabled);
	}

	public void setMaximumMovesInfoLabelEnabled(boolean enabled) {
		getLevelDesignerMainPanel().setMaximumMovesInfoLabelEnabled(enabled);
	}

	public void setDivideCountSpinnerValue(int value) {
		getLevelDesignerMainPanel().setDivideCountSpinnerValue(value);
	}

	public void setDuplicateCountSpinnerValue(int value) {
		getLevelDesignerMainPanel().setDuplicateCountSpinnerValue(value);
	}

	public void setMaximumMovesSpinnerValue(int value) {
		getLevelDesignerMainPanel().setMaximumMovesSpinnerValue(value);
	}

	public void setMaximumMovesSpinnerEnabled(boolean enabled) {
		getLevelDesignerMainPanel().setMaximumMovesSpinnerEnabled(enabled);
	}

	public void setNavigationListSelectedIndex(int index) {
		getLevelDesignerMainPanel().setNavigationListSelectedIndex(index);
	}

	public Coordinate getPuzzlePanelCoordinateAtPoint(Point point) {
		return getLevelDesignerMainPanel().getPuzzlePanelCoordinateAtPoint(point);
	}

	public Coordinate getSolutionPanelCoordinateAtPoint(Point point) {
		return getLevelDesignerMainPanel().getSolutionPanelCoordinateAtPoint(point);
	}

	public void setPuzzlePanelMouseAdapter(MouseAdapter mouseAdapter) {
		getLevelDesignerMainPanel().addPuzzlePanelMouseAdapter(mouseAdapter);
	}

	public void setSolutionPanelMouseAdapter(MouseAdapter mouseAdapter) {
		getLevelDesignerMainPanel().addSolutionPanelMouseAdapter(mouseAdapter);
	}

	public void setColorData(List<Coordinate> validCoordinates, Map<Coordinate, Integer> puzzleCoordinateColorIdMap,
			Map<Coordinate, Integer> solutionCoordinateColorIdMap) {
		getLevelDesignerMainPanel().setColorData(validCoordinates, puzzleCoordinateColorIdMap,
				solutionCoordinateColorIdMap);
	}

	public void clearLevelFileToNavigationList() {
		getLevelDesignerMainPanel().clearLevelFileToNavigationList();
	}

	public void addLevelFileToNavigationList(File file) {
		getLevelDesignerMainPanel().addLevelFileToNavigationList(file);
	}

	public void addLevelNavigationListSelectionListener(ListSelectionListener listSelectionListener) {
		getLevelDesignerMainPanel().addLevelNavigationListSelectionListener(listSelectionListener);
	}

	public void setQuitLevelDesignerMenuItemAction(Action action) {
		getQuitLevelDesignerMenuItem().setAction(action);
	}

	public void setNewLevelMenuItemAction(Action action) {
		getNewLevelMenuItem().setAction(action);
	}

	public void setIncrementLevelIdMenuItemAction(Action action) {
		getIncrementLevelIdMenuItem().setAction(action);
	}

	public void setDecrementLevelIdMenuItemAction(Action action) {
		getDecrementLevelIdMenuItem().setAction(action);
	}

	public void setSetLevelIdToMenuItemAction(Action action) {
		getSetLevelIdToMenuItem().setAction(action);
	}

	public void setFindLevelSolutionMenuItemAction(Action action) {
		getFindLevelSolutionMenuItem().setAction(action);
	}

	public void setLevelTypesMenuItemAction(Action action) {
		getLevelTypesMenuItem().setAction(action);
	}

	public void setOpenLevelFilesFolderMenuItemAction(Action action) {
		getOpenLevelFilesFolderMenuItem().setAction(action);
	}

	public void setSaveLevelToFileMenuItemAction(Action action) {
		getSaveLevelToFileMenuItem().setAction(action);
	}

	public void setSaveAllLevelsMenuItemAction(Action action) {
		getSaveAllLevelsMenuItem().setAction(action);
	}

	public void setTestSolutionsMenuItemAction(Action action) {
		getTestSolutionsMenuItem().setAction(action);
	}

	public void addColorButtonToPalette(String text, Color color, ActionListener actionListener, boolean selected) {
		getLevelDesignerMainPanel().addColorButtonToPalette(text, color, actionListener, selected);
	}

	public void setSwapPlayModeToggleButtonEnabled(boolean enabled) {
		getLevelDesignerMainPanel().setSwapPlayModeToggleButtonEnabled(enabled);
	}

	public void setDividePlayModeToggleButtonEnabled(boolean enabled) {
		getLevelDesignerMainPanel().setDividePlayModeToggleButtonEnabled(enabled);
	}

	public void setDuplicatePlayModeToggleButtonEnabled(boolean enabled) {
		getLevelDesignerMainPanel().setDuplicatePlayModeToggleButtonEnabled(enabled);
	}

	private JMenuBar getLevelDesignerMenuBar() {
		if (levelDesignerMenuBar == null) {
			levelDesignerMenuBar = new JMenuBar();
			levelDesignerMenuBar.add(getFileMenu());
			levelDesignerMenuBar.add(getLevelMenu());
			levelDesignerMenuBar.add(getSolutionMenu());
		}
		return levelDesignerMenuBar;
	}

	private JMenu getFileMenu() {
		if (fileMenu == null) {
			fileMenu = new JMenu("File");
			fileMenu.add(getOpenLevelFilesFolderMenuItem());
			fileMenu.add(getSaveLevelToFileMenuItem());
			fileMenu.add(getSaveAllLevelsMenuItem());
			fileMenu.add(getQuitLevelDesignerMenuItem());
		}
		return fileMenu;
	}

	private JMenu getLevelMenu() {
		if (levelMenu == null) {
			levelMenu = new JMenu("Level");
			levelMenu.add(getNewLevelMenuItem());
			levelMenu.add(getIncrementLevelIdMenuItem());
			levelMenu.add(getDecrementLevelIdMenuItem());
			levelMenu.add(getSetLevelIdToMenuItem());
			levelMenu.add(getFindLevelSolutionMenuItem());
			levelMenu.add(getLevelTypesMenuItem());
		}
		return levelMenu;
	}

	private JMenuItem getNewLevelMenuItem() {
		if (newLevelMenuItem == null) {
			newLevelMenuItem = new JMenuItem();
		}
		return newLevelMenuItem;
	}

	private JMenuItem getIncrementLevelIdMenuItem() {
		if (incrementLevelIdMenuItem == null) {
			incrementLevelIdMenuItem = new JMenuItem();
		}
		return incrementLevelIdMenuItem;
	}

	private JMenuItem getDecrementLevelIdMenuItem() {
		if (decrementLevelIdMenuItem == null) {
			decrementLevelIdMenuItem = new JMenuItem();
		}
		return decrementLevelIdMenuItem;
	}

	private JMenuItem getSetLevelIdToMenuItem() {
		if (setLevelIdToMenuItem == null) {
			setLevelIdToMenuItem = new JMenuItem();
		}
		return setLevelIdToMenuItem;
	}

	private JMenuItem getLevelTypesMenuItem() {
		if (levelTypesMenuItem == null) {
			levelTypesMenuItem = new JMenuItem();
		}
		return levelTypesMenuItem;
	}

	private JMenuItem getFindLevelSolutionMenuItem() {
		if (findLevelSolutionMenuItem == null) {
			findLevelSolutionMenuItem = new JMenuItem();
		}
		return findLevelSolutionMenuItem;
	}

	private JMenuItem getOpenLevelFilesFolderMenuItem() {
		if (openLevelFilesFolderMenuItem == null) {
			openLevelFilesFolderMenuItem = new JMenuItem();
		}
		return openLevelFilesFolderMenuItem;
	}

	private JMenuItem getSaveLevelToFileMenuItem() {
		if (saveLevelToFileMenuItem == null) {
			saveLevelToFileMenuItem = new JMenuItem();
		}
		return saveLevelToFileMenuItem;
	}

	private JMenuItem getSaveAllLevelsMenuItem() {
		if (saveAllLevelsMenuItem == null) {
			saveAllLevelsMenuItem = new JMenuItem();
		}
		return saveAllLevelsMenuItem;
	}

	private JMenuItem getQuitLevelDesignerMenuItem() {
		if (quitLevelDesignerMenuItem == null) {
			quitLevelDesignerMenuItem = new JMenuItem();
		}
		return quitLevelDesignerMenuItem;
	}

	private JMenu getSolutionMenu() {
		if (solutionMenu == null) {
			solutionMenu = new JMenu("Solution");
			solutionMenu.add(getTestSolutionsMenuItem());
		}
		return solutionMenu;
	}

	private JMenuItem getTestSolutionsMenuItem() {
		if (testSolutionsMenuItem == null) {
			testSolutionsMenuItem = new JMenuItem();
		}
		return testSolutionsMenuItem;
	}

	private LevelDesignerMainPanel getLevelDesignerMainPanel() {
		if (levelDesignerMainPanel == null) {
			levelDesignerMainPanel = new LevelDesignerMainPanel();
		}
		return levelDesignerMainPanel;
	}
}
