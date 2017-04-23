package ui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import render.AudioUtility;
import render.ConfigurableOption;
import render.DrawingUtility;
import logic.PlayerStatus;

public class GameTitle extends JPanel {
	private static GameWindow gameWindow;

	private boolean isPointOverNewGame = false;
	private boolean isPointOverExit = false;
	private boolean isPointOverHighscore = false;
	private boolean isPointOverSetting = false;

	private boolean isPushedHighscoreButton = false;

	private boolean isMute = false;

	public boolean isMute() {
		return isMute;
	}

	public void setMute(boolean isMute) {
		this.isMute = isMute;
	}

	private static final int newGameX = 222;
	private static final int newGameY = 340;
	private static final int newGameW = 361;
	private static final int newGameH = 75;

	private static final int exitX = 282;
	private static final int exitY = 439;
	private static final int exitW = 242;
	private static final int exitH = 75;

	private static final int highscoreX = 717;
	private static final int highscoreY = 13;
	private static final int highscoreW = DrawingUtility.highscorePushed
			.getWidth();
	private static final int highscoreH = DrawingUtility.highscorePushed
			.getHeight();

	private static final int settingX = 631;
	private static final int settingY = 13;
	private static final int settingW = DrawingUtility.settingPushed.getWidth();
	private static final int settingH = DrawingUtility.settingPushed
			.getHeight();

	public GameTitle(GameWindow gameWindow) {

		this.gameWindow = gameWindow;
		addListener();
		applyResize();
		setDoubleBuffered(true);
	}

	private void applyResize() {
		this.setPreferredSize(new Dimension(ConfigurableOption.screenWidth,
				ConfigurableOption.screenHeight));
		this.validate();
	}

	private void addListener() {

		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {

				if (arg0.getButton() == 1) {

					// new game
					if (arg0.getX() > newGameX
							&& arg0.getX() < newGameX + newGameW
							&& arg0.getY() > newGameY
							&& arg0.getY() < newGameY + newGameH) {

						AudioUtility.playSoundButton();
						actionButtonStartGame();

					}

					// exit
					else if (arg0.getX() > exitX && arg0.getX() < exitX + exitW
							&& arg0.getY() > exitY
							&& arg0.getY() < exitY + exitH) {

						AudioUtility.playSoundButton();
						try {
							Thread.sleep(300);
						} catch (InterruptedException e) {

						}
						actionButtonExitGame();

					}

					// high score
					else if (arg0.getX() > highscoreX
							&& arg0.getX() < highscoreX + highscoreW
							&& arg0.getY() > highscoreY
							&& arg0.getY() < highscoreY + highscoreH) {

						AudioUtility.playSoundButton();

						GameManager.getHighScore().loadHighScore();
						gameWindow.setCurrentScene(GameManager.getHighScore());

					}

					// setting
					else if (arg0.getX() > settingX
							&& arg0.getX() < settingX + settingW
							&& arg0.getY() > settingY
							&& arg0.getY() < settingY + settingH) {

						AudioUtility.playSoundButton();
						actionButtonsetting();

					}

				}

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

			}

		});

		this.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent arg0) {
				// new game
				if (arg0.getX() > newGameX && arg0.getX() < newGameX + newGameW
						&& arg0.getY() > newGameY
						&& arg0.getY() < newGameY + newGameH) {
					if (!isPointOverNewGame) {
						isPointOverNewGame = true;
						repaint();
					}
				}
				// exit
				else if (arg0.getX() > exitX && arg0.getX() < exitX + exitW
						&& arg0.getY() > exitY && arg0.getY() < exitY + exitH) {
					if (!isPointOverExit) {
						isPointOverExit = true;
						repaint();
					}

				}

				else if (arg0.getX() > highscoreX
						&& arg0.getX() < highscoreX + highscoreW
						&& arg0.getY() > highscoreY
						&& arg0.getY() < highscoreY + highscoreH) {
					if (!isPointOverHighscore) {
						isPointOverHighscore = true;
						repaint();
					}
				}

				else if (arg0.getX() > settingX
						&& arg0.getX() < settingX + settingW
						&& arg0.getY() > settingY
						&& arg0.getY() < settingY + settingH) {
					if (!isPointOverSetting) {
						isPointOverSetting = true;
						repaint();
					}
				}

				else {
					if (isPointOverExit || isPointOverNewGame
							|| isPointOverHighscore || isPointOverSetting) {
						isPointOverExit = false;
						isPointOverNewGame = false;
						isPointOverHighscore = false;
						isPointOverSetting = false;
						repaint();

					}
				}

			}

			@Override
			public void mouseDragged(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	private void actionButtonHighscore() {
		isPushedHighscoreButton = true;

	}

	private void actionButtonsetting() {
		gameWindow.setCurrentScene(GameManager.getSetting());

	}

	private void actionPointButton(Graphics2D g2d) {
		if (isPointOverNewGame) {
			g2d.drawImage(DrawingUtility.startPushed, null, newGameX, newGameY);
		}

		else if (isPointOverExit) {
			g2d.drawImage(DrawingUtility.exitPushed, null, exitX, exitY);

		}

		else if (isPointOverHighscore) {
			g2d.drawImage(DrawingUtility.highscorePushed, null, highscoreX,
					highscoreY);

		}

		else if (isPointOverSetting) {
			g2d.drawImage(DrawingUtility.settingPushed, null, settingX,
					settingY);

		}

	}

	private void actionButtonStartGame() {
		gameWindow.setCurrentScene(GameManager.getGameScreen());
	}

	private void actionButtonExitGame() {
		System.exit(0);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		drawBackground(g2d);

		actionPointButton(g2d);
		if (isPushedHighscoreButton)
			g2d.drawImage(DrawingUtility.highscoreBg, null, 0, 0);
	}

	private void drawBackground(Graphics2D g2d) {
		g2d.drawImage(DrawingUtility.bgGameTitle, null, 0, 0);
	}

}
