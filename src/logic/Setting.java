package logic;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import render.AudioUtility;
import render.ConfigurableOption;
import render.DrawingUtility;
import ui.GameManager;
import ui.GameWindow;

public class Setting extends JPanel {

	private static int soundX = 213;
	private static int bgSoundY = 372;
	private static int fxSoundY = 206;

	private static GameWindow gameWindow;

	public Setting(GameWindow gameWindow) {
		this.gameWindow = gameWindow;
		setDoubleBuffered(true);
		applyResize();
		addListener();

	}

	private void addListener() {
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				int settingX = 212;
				int settingY = 15;
				int settingW = 386;
				int settingH = 523;

				int r = 49;
				int hFx = 331;
				int kFx = 278;
				int hBg = 495;
				int kBg = 442;

				if (e.getButton() == 1) {
					int x = e.getX();
					int y = e.getY();

					if (e.getX() <= settingX || e.getX() >= settingX + settingW
							|| e.getY() <= settingY
							|| e.getY() >= settingY + settingH) {
						AudioUtility.playSoundButton();
						gameWindow.setCurrentScene(GameManager.getGameTitle());
					}

					else if ((int) ((Math.pow(x - hFx, 2) + Math
							.pow(y - kFx, 2))) <= r * r) {
						AudioUtility.playSoundButton();
						PlayerStatus.setOffFxSound(!PlayerStatus.isOffFxSound());
						repaint();
					} else if ((int) ((Math.pow(x - hBg, 2) + Math.pow(y - kBg,
							2))) <= r * r) {

						AudioUtility.playSoundButton();

						PlayerStatus.setOffBgSound(!PlayerStatus.isOffBgSound());

						if (PlayerStatus.isOffBgSound())
							AudioUtility.stopSoundBackground();
						else
							AudioUtility.playSoundBackground();
						repaint();
					}

				}

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	private void applyResize() {
		this.setPreferredSize(new Dimension(ConfigurableOption.screenWidth,
				ConfigurableOption.screenHeight));
		this.validate();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(DrawingUtility.settingBgOn, null, 0, 0);
		if (PlayerStatus.isOffBgSound()) {
			g2d.drawImage(DrawingUtility.bgSoundOff, null, soundX, bgSoundY);
		}
		if (PlayerStatus.isOffFxSound()) {
			g2d.drawImage(DrawingUtility.fxSoundOff, null, soundX, fxSoundY);
		}
	}

}