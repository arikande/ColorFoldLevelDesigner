package game.colorfold.designer.model;

import java.awt.Color;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class LevelDesignerModel {

    public static final int DESIGN_MODE = 0;
    public static final int PLAY_MODE = 1;

    public static Map<Integer, Color> colorMap = new HashMap<Integer, Color>();
    private File levelFilesFolder;
    private File solutionFilesFolder;
    private int selectedColorId = 0;
    private ColorFoldLevel selectedLevel;
    private TreeMap<File, ColorFoldLevel> levelsMap;
    private TreeMap<String, ColorFoldLevelSolution> solutionsMap;
    private int mode = DESIGN_MODE;
    private Coordinate lastMousePressedCoordinate;
    private int foldColorMode;
    private ColorFoldLevelSolution recordedSolution;

    static {
	colorMap.put(0, Color.WHITE);
	colorMap.put(1, Color.YELLOW);
	colorMap.put(2, Color.GREEN);
	colorMap.put(3, Color.CYAN);
	colorMap.put(4, Color.BLUE);
	colorMap.put(5, Color.ORANGE);
	colorMap.put(6, Color.RED);
	colorMap.put(7, new Color(148, 0, 211));
	colorMap.put(8, Color.BLACK);
    }

    public LevelDesignerModel() {
	levelsMap = new TreeMap<File, ColorFoldLevel>();
	solutionsMap = new TreeMap<String, ColorFoldLevelSolution>();
	recordedSolution = new ColorFoldLevelSolution();
    }

    public void clearLevels() {
	levelsMap.clear();
    }

    public void addLevel(ColorFoldLevel colorFoldLevel) {
	levelsMap.put(colorFoldLevel.getFile(), colorFoldLevel);
    }

    public void removeLevel(ColorFoldLevel colorFoldLevel) {
	levelsMap.remove(colorFoldLevel.getFile());
    }

    public ColorFoldLevel getLevel(File file) {
	return levelsMap.get(file);
    }

    public ColorFoldLevel getFirstLevel() {
	if (levelsMap.size() > 0) {
	    return levelsMap.get(levelsMap.firstKey());
	} else {
	    return null;
	}
    }

    public Iterator<File> getLevelFilesIterator() {
	return levelsMap.keySet().iterator();
    }

    public Iterator<ColorFoldLevel> getLevelsIterator() {
	return levelsMap.values().iterator();
    }

    public int getLevelCount() {
	return levelsMap.size();
    }

    public void addLevelSolution(ColorFoldLevelSolution colorFoldLevelSolution) {
	solutionsMap.put(colorFoldLevelSolution.getFile().getName(), colorFoldLevelSolution);
    }

    public ColorFoldLevelSolution getLevelSolution(String fileName) {
	return solutionsMap.get(fileName);
    }

    public int getSelectedColorId() {
	return selectedColorId;
    }

    public void setSelectedColorId(int selectedColorId) {
	this.selectedColorId = selectedColorId;
    }

    public ColorFoldLevel getSelectedLevel() {
	return selectedLevel;
    }

    public void setSelectedLevel(ColorFoldLevel selectedLevel) {
	this.selectedLevel = selectedLevel;
    }

    public File getLevelFilesFolder() {
	return levelFilesFolder;
    }

    public void setLevelFilesFolder(File levelFilesFolder) {
	this.levelFilesFolder = levelFilesFolder;
    }

    public File getSolutionFilesFolder() {
	return solutionFilesFolder;
    }

    public void setSolutionFilesFolder(File solutionFilesFolder) {
	this.solutionFilesFolder = solutionFilesFolder;
    }

    public int getMode() {
	return mode;
    }

    public void setMode(int mode) {
	this.mode = mode;
    }

    public Coordinate getLastMousePressedCoordinate() {
	return lastMousePressedCoordinate;
    }

    public void setLastMousePressedCoordinate(Coordinate lastMousePressedCoordinate) {
	this.lastMousePressedCoordinate = lastMousePressedCoordinate;
    }

    public int getFoldColorMode() {
	return foldColorMode;
    }

    public void setFoldColorMode(int foldColorMode) {
	this.foldColorMode = foldColorMode;
    }

    public ColorFoldLevelSolution getRecordedSolution() {
        return recordedSolution;
    }

    
    
}