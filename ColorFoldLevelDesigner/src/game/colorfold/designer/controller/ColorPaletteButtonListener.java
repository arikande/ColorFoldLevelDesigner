package game.colorfold.designer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorPaletteButtonListener implements ActionListener {

	private LevelDesignerController levelDesignerController;
	private int colorId;
	
	public ColorPaletteButtonListener(LevelDesignerController levelDesignerController, int colorId) {
		this.levelDesignerController = levelDesignerController;
		this.colorId = colorId;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		levelDesignerController.getLevelDesignerModel().setSelectedColorId(colorId);
	}

}
