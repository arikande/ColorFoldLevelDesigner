package game.colorfold.designer;

import game.colorfold.designer.controller.LevelDesignerController;

public class LevelDesignerLauncher {

	private static LevelDesignerController levelDesignerController;

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				getLevelDesignerController().displayLevelDesignerFrame();
			}
		});
	}

	private static LevelDesignerController getLevelDesignerController() {
		if (levelDesignerController == null) {
			levelDesignerController = new LevelDesignerController();
		}
		return levelDesignerController;
	}

}
