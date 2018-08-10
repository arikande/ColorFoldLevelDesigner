package game.colorfold.designer.controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import game.colorfold.designer.controller.command.SetSelectedLevelCommand;
import game.colorfold.designer.model.ColorFoldLevel;
import game.colorfold.designer.model.ColorFoldMove;
import game.colorfold.designer.model.Coordinate;
import game.colorfold.designer.model.LevelDesignerModel;
import game.colorfold.designer.view.LevelDesignerFrame;

public class LevelDesignerController {
    private LevelDesignerFrame levelDesignerFrame;
    private LevelDesignerModel levelDesignerModel;

    public void displayLevelDesignerFrame() {
	final Toolkit toolkit = Toolkit.getDefaultToolkit();
	final Dimension screenSize = toolkit.getScreenSize();
	getLevelDesignerFrame().setLocation(0, 0);
	getLevelDesignerFrame().setSize(screenSize);
	getLevelDesignerFrame().setNewLevelMenuItemAction(new DisplayNewLevelDialogAction(this));
	getLevelDesignerFrame().setIncrementLevelIdMenuItemAction(new IncrementLevelIdAction(this));
	getLevelDesignerFrame().setDecrementLevelIdMenuItemAction(new DecrementLevelIdAction(this));
	getLevelDesignerFrame().setSetLevelIdToMenuItemAction(new DisplaySetLevelIdToDialogAction(this));
	getLevelDesignerFrame().setLevelTypesMenuItemAction(new DisplayLevelTypesAction(this));
	getLevelDesignerFrame().setFindLevelSolutionMenuItemAction(new FindLevelSolutionAction(this));
	getLevelDesignerFrame()
		.setOpenLevelFilesFolderMenuItemAction(new DisplayOpenLevelFilesFolderDialogAction(this));
	getLevelDesignerFrame().setExportLevelFilesMenuItemAction(new ExportLevelFilesAction(this));
	getLevelDesignerFrame().setSaveLevelToFileMenuItemAction(new SaveLevelToFileAction(this));
	getLevelDesignerFrame().setSaveAllLevelsMenuItemAction(new SaveAllLevelsAction(this));
	getLevelDesignerFrame().setQuitLevelDesignerMenuItemAction(new QuitLevelDesignerAction());
	getLevelDesignerFrame().setTestSolutionsMenuItemAction(new DisplayTestSolutionsDialogAction(this));
	getLevelDesignerFrame().addLevelNavigationListSelectionListener(new LevelNavigationListSelectionListener(this));
	getLevelDesignerFrame().setPuzzlePanelMouseAdapter(new PuzzlePanelMouseAdapter(this));
	getLevelDesignerFrame()
		.setSolutionPanelMouseAdapter(new SolutionPanelMouseAdapter(this, getLevelDesignerFrame()));

	getLevelDesignerFrame().addDesignModeRadioButtonActionListener(new DesignModeRadioButtonActionListener(this));
	getLevelDesignerFrame().addPlayModeRadioButtonActionListener(new PlayModeRadioButtonActionListener(this));
	getLevelDesignerFrame().addSwapCountSpinnerChangeListener(new SwapCountSpinnerChangeListener(this));
	getLevelDesignerFrame().addDivideCountSpinnerChangeListener(new DivideCountSpinnerChangeListener(this));
	getLevelDesignerFrame().addDuplicateCountSpinnerChangeListener(new DuplicateCountSpinnerChangeListener(this));
	getLevelDesignerFrame().addMaximumMovesSpinnerChangeListener(new MaximumMovesSpinnerChangeListener(this));

	getLevelDesignerFrame().addSwapPlayModeToggleButtonActionListener(
		new PlayModeToggleButtonActionListener(this, ColorFoldMove.SWAP_MOVE));
	getLevelDesignerFrame().addDividePlayModeToggleButtonActionListener(
		new PlayModeToggleButtonActionListener(this, ColorFoldMove.DIVIDE_MOVE));
	getLevelDesignerFrame().addDuplicatePlayModeToggleButtonActionListener(
		new PlayModeToggleButtonActionListener(this, ColorFoldMove.DUPLICATE_MOVE));

	getLevelDesignerFrame().setReloadFromFileButtonAction(new ReloadLevelFromFileAction(this));
	getLevelDesignerFrame().setRecordToggleButtonAction(new RecordSolutionAction(this));

	Iterator<Integer> iterator = LevelDesignerModel.colorMap.keySet().iterator();
	while (iterator.hasNext()) {
	    Integer colorId = iterator.next();
	    ActionListener actionListener = new ColorPaletteButtonListener(this, colorId);
	    getLevelDesignerFrame().addColorButtonToPalette(String.valueOf(colorId),
		    LevelDesignerModel.colorMap.get(colorId), actionListener,
		    getLevelDesignerModel().getSelectedColorId() == colorId);
	}
	getLevelDesignerFrame().setVisible(true);
    }

    public void updateLevelNavigationList() {
	getLevelDesignerFrame().clearLevelFileToNavigationList();
	int index = 0;
	boolean found = false;
	Iterator<File> levelFilesIterator = getLevelDesignerModel().getLevelFilesIterator();
	while (levelFilesIterator.hasNext()) {
	    File levelFile = levelFilesIterator.next();
	    getLevelDesignerFrame().addLevelFileToNavigationList(levelFile);
	    ColorFoldLevel colorFoldLevel = getLevelDesignerModel().getLevel(levelFile);
	    if (!found && !getLevelDesignerModel().getSelectedLevel().equals(colorFoldLevel)) {
		index++;
	    } else {
		found = true;
	    }
	}
	getLevelDesignerFrame().setNavigationListSelectedIndex(index);
    }

    public LevelDesignerModel getLevelDesignerModel() {
	if (levelDesignerModel == null) {
	    levelDesignerModel = new LevelDesignerModel();
	}
	return levelDesignerModel;
    }

    public LevelDesignerFrame getLevelDesignerFrame() {
	if (levelDesignerFrame == null) {
	    levelDesignerFrame = new LevelDesignerFrame();
	}
	return levelDesignerFrame;
    }

    public void updateLevelDesignerFrameTitle() {
	if (levelDesignerModel.getSelectedLevel() != null) {
	    String title = levelDesignerModel.getSelectedLevel().getFile().getAbsolutePath();
	    if (levelDesignerModel.getSelectedLevel().isSaveNeeded()) {
		title = title + " *";
	    }
	    levelDesignerFrame.setTitle(title);
	} else {
	    levelDesignerFrame.setTitle("-");
	}
    }

    public void updateLevelDesignerMainPanel() {
	ColorFoldLevel colorFoldLevel = getLevelDesignerModel().getSelectedLevel();
	List<Coordinate> validCoordinates = new ArrayList<Coordinate>();
	Iterator<Coordinate> validCoordinatesIterator = colorFoldLevel.getValidCoordinatesIterator();
	while (validCoordinatesIterator.hasNext()) {
	    Coordinate coordinate = validCoordinatesIterator.next();
	    validCoordinates.add(coordinate);
	}
	getLevelDesignerFrame().setColorData(validCoordinates, colorFoldLevel.getPuzzle(),
		colorFoldLevel.getSolution());
	getLevelDesignerFrame().setSwapCountSpinnerValue(colorFoldLevel.isSwapEnabled() ? 1 : 0);
	getLevelDesignerFrame().setDivideCountSpinnerValue(colorFoldLevel.isDivideEnabled() ? 1 : 0);
	getLevelDesignerFrame().setDuplicateCountSpinnerValue(colorFoldLevel.isDuplicateEnabled() ? 1 : 0);
	getLevelDesignerFrame().setMaximumMovesSpinnerValue(colorFoldLevel.getMaximumMoves());
	getLevelDesignerFrame()
		.setSolutionTotalColorValueLabelText(String.valueOf(colorFoldLevel.getSolutionTotalColorValue()));
	getLevelDesignerFrame()
		.setPuzzleTotalColorValueLabelText(String.valueOf(colorFoldLevel.getPuzzleTotalColorValue()));
    }

    public void repaintAll() {
	levelDesignerFrame.repaint();
	levelDesignerFrame.repaintLevelDesignerMainPanel();
    }

    public void repaintLevelDesignerMainPanel() {
	levelDesignerFrame.repaintLevelDesignerMainPanel();
    }

    public int getMaximumLevelId() {
	int maximumLevelId = 0;
	Iterator<File> levelFilesIterator = getLevelDesignerModel().getLevelFilesIterator();
	while (levelFilesIterator.hasNext()) {
	    File file = levelFilesIterator.next();
	    int id = getLevelIdFromFileName(file.getName());
	    if (id > maximumLevelId) {
		maximumLevelId = id;
	    }
	}
	return maximumLevelId;
    }

    public int getLevelIdFromFileName(String fileName) {
	return Integer.parseInt(fileName.replaceAll("level", "").replaceAll(".xml", ""));
    }

    public String getFileNameFromLevelId(int levelId) {
	String levelFilesFolderPath = getLevelDesignerModel().getLevelFilesFolder().getAbsolutePath();
	String fileName = levelFilesFolderPath + "/level" + String.format("%04d", levelId) + ".xml";
	return fileName;
    }

    public void selectFirstLevel() {
	ColorFoldLevel firstLevel = getLevelDesignerModel().getFirstLevel();
	if (firstLevel != null) {
	    new SetSelectedLevelCommand(this, firstLevel).execute();
	}
    }

}
