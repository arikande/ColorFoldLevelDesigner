package game.colorfold.designer.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.AbstractAction;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.colorfold.designer.model.ColorFoldLevel;
import game.colorfold.designer.model.Coordinate;
import game.colorfold.designer.model.LevelDesignerModel;
import game.colorfold.designer.view.ColorFoldPanel;
import game.colorfold.designer.view.LevelTypesDialog;

public class DisplayLevelTypesAction extends AbstractAction {

	private LevelDesignerController levelDesignerController;

	public DisplayLevelTypesAction(LevelDesignerController levelDesignerController) {
		super("Level types");
		this.levelDesignerController = levelDesignerController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Map<ColorFoldLevel, Integer> levelTypesMap = new HashMap<ColorFoldLevel, Integer>();

		LevelDesignerModel levelDesignerModel = levelDesignerController.getLevelDesignerModel();
		Iterator<ColorFoldLevel> levelsIterator = levelDesignerModel.getLevelsIterator();
		while (levelsIterator.hasNext()) {
			ColorFoldLevel colorFoldLevel = levelsIterator.next();
			Entry<ColorFoldLevel, Integer> validCoordinateTypesListEntry = getValidCoordinateTypesListEntry(
					colorFoldLevel, levelTypesMap);
			if (validCoordinateTypesListEntry != null) {
				validCoordinateTypesListEntry.setValue(validCoordinateTypesListEntry.getValue() + 1);
			} else {
				levelTypesMap.put(colorFoldLevel, 1);
			}
		}
		LevelTypesDialog levelTypesDialog = new LevelTypesDialog(levelDesignerController.getLevelDesignerFrame());
		JPanel levelTypesMainPanel = new JPanel(new GridLayout(0, 4, 10, 10));
		for (Entry<ColorFoldLevel, Integer> entry : levelTypesMap.entrySet()) {
			ColorFoldLevel colorFoldLevel = entry.getKey();
			List<Coordinate> validCoordinates = new ArrayList<Coordinate>();
			Iterator<Coordinate> validCoordinatesIterator = colorFoldLevel.getValidCoordinatesIterator();
			while (validCoordinatesIterator.hasNext()) {
				Coordinate validCoordinate = validCoordinatesIterator.next();
				validCoordinates.add(validCoordinate);
			}
			ColorFoldPanel colorFoldPanel = new ColorFoldPanel();
			colorFoldPanel.setTriangleSideLength(20);
			colorFoldPanel.setValidCoordinates(validCoordinates);
			JPanel colorFoldInfoPanel = new JPanel(new BorderLayout());
			colorFoldInfoPanel.add(colorFoldPanel, BorderLayout.CENTER);
			colorFoldInfoPanel.add(new JLabel(String.valueOf(entry.getValue())), BorderLayout.PAGE_END);
			levelTypesMainPanel.setBackground(Color.WHITE);
			levelTypesMainPanel.add(colorFoldInfoPanel);
		}
		levelTypesDialog.setContentPane(levelTypesMainPanel);
		levelTypesDialog.display(480, 330);
	}

	private Entry<ColorFoldLevel, Integer> getValidCoordinateTypesListEntry(ColorFoldLevel colorFoldLevel,
			Map<ColorFoldLevel, Integer> levelTypesMap) {
		for (Entry<ColorFoldLevel, Integer> entry : levelTypesMap.entrySet()) {
			if (colorFoldLevel.areValidCoordinatesEqualTo(entry.getKey())) {
				return entry;
			}
		}
		return null;
	}

}
