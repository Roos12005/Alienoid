package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameWindow extends JFrame {

	private JPanel currentScene;

	protected GameWindow() {
		super("Alienoid");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}

	public void addFirstScene(JPanel scene) {
		this.currentScene = scene;
		this.getContentPane().add(currentScene);
		this.setVisible(true);
		this.pack();
		this.currentScene.requestFocus();
	}

	public void switchScene(JPanel scene) {

		scene.repaint();

		this.getContentPane().removeAll();
		this.currentScene = scene;
		this.getContentPane().add(currentScene);

		this.getContentPane().validate();
		this.pack();
		this.currentScene.requestFocus();
	}

	public JPanel getCurrentScene() {
		return currentScene;
	}

	public void setCurrentScene(JPanel currentScene) {
		this.currentScene = currentScene;
	}

}
