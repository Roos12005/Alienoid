package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import logic.PlayerStatus;
import render.AudioUtility;
import render.ConfigurableOption;
import render.DrawingUtility;

public class YourScore extends JPanel {
	private boolean isPointOverOkGameOver = false;
	private boolean isPointOverOkEnter = false;

	private GameWindow gameWindow;
	public static JTextField textField = new JTextField();

	public static String name = "";

	private static final int okGameOverX = 319;
	private static final int okGameOverY = 390;
	private static final int okGameOverW = 162;
	private static final int okGameOverH = 68;

	private static final int okEnterX = 319;
	private static final int okEnterY = 419;
	private static final int okEnterW = 162;
	private static final int okEnterH = 68;

	private static boolean showTextField = false;

	public YourScore(GameWindow gameWindow) {
		this.gameWindow = gameWindow;
		applyResize();
		addListener();
	}

	public void reset() {
		this.removeAll();
	}

	private void addListener() {
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				if (arg0.getButton() == 1) {
					if (arg0.getX() >= okGameOverX
							&& arg0.getX() <= okGameOverX + okGameOverW
							&& arg0.getY() >= okGameOverY
							&& arg0.getY() <= okGameOverY + okGameOverH
							&& PlayerStatus.isGameOver()) {
						// NOT IN RANK
						gameWindow.setCurrentScene(GameManager.getGameTitle());
						AudioUtility.playSoundButton();
						AudioUtility.playSoundBackground();
					}
					if (!PlayerStatus.isGameOver() && arg0.getX() >= okEnterX
							&& arg0.getX() <= okEnterX + okEnterW
							&& arg0.getY() >= okEnterY
							&& arg0.getY() <= okEnterY + okEnterH) { // IN RANK

						if (!PlayerStatus.isEnterYourName()) { // SHOWTOP3 ,
																// GOTO
																// INPUT
																// NAME
							PlayerStatus.setEnterYourName(true);
							// isPointOverOkEnter = false;

							AudioUtility.playSoundButton();
							repaint();

						} else { // TOP3
							name = textField.getText().toString();

							HighScore.recordHighScoreName(name,
									PlayerStatus.getScore());

							reset();
							gameWindow.setCurrentScene(GameManager
									.getGameTitle());
							
							AudioUtility.playSoundButton();
							AudioUtility.playSoundBackground();
						}

					}
				}

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

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
				// ok
				if (arg0.getX() > okGameOverX
						&& arg0.getX() < okGameOverX + okGameOverW
						&& arg0.getY() > okGameOverY
						&& arg0.getY() < okGameOverY + okGameOverH
						&& PlayerStatus.isGameOver()) {
					if (!isPointOverOkGameOver) {
						isPointOverOkGameOver = true;
						repaint();
					}
				}

				else if (arg0.getX() > okEnterX
						&& arg0.getX() < okEnterX + okEnterW
						&& arg0.getY() > okEnterY
						&& arg0.getY() < okEnterY + okEnterH
						&& !PlayerStatus.isGameOver()) {
					if (!isPointOverOkEnter) {
						isPointOverOkEnter = true;
						repaint();
					}
				}

				else {
					if (isPointOverOkEnter || isPointOverOkGameOver) {
						isPointOverOkEnter = false;
						isPointOverOkGameOver = false;
						repaint();

					}
				}

			}

			@Override
			public void mouseDragged(MouseEvent arg0) {

			}
		});

	}

	public void showEnterYourName() {

		class MaxLengthTextDocument extends PlainDocument {
			// Store maximum characters permitted
			private int maxChars;

			@Override
			public void insertString(int offs, String str, AttributeSet a)
					throws BadLocationException {
				if (str != null && (getLength() + str.length() <= maxChars)) {
					super.insertString(offs, str, a);
				}
			}

			public void setMaxChars(int maxChars) {
				this.maxChars = maxChars;
			}
		}

		MaxLengthTextDocument maxLength = new MaxLengthTextDocument();
		maxLength.setMaxChars(5);// 50 is a maximum number of character

		textField.setDocument(maxLength);

		textField.setText("");
		textField.setBorder(null);
		textField.setCaretColor(Color.WHITE);
		textField.setFont(new Font("junegull", Font.PLAIN, 120));
		textField.setForeground(Color.WHITE);
		textField.setOpaque(false);
		textField.setHorizontalAlignment(JTextField.CENTER);
		this.setLayout(null);
		textField.setBounds(134, 245, 533, 131);
		this.add(textField);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) (g);
		if (PlayerStatus.isGameOver()) {
			g2d.drawImage(DrawingUtility.gameOver, null, 0, 0);
			g2d.setFont(new Font("junegull", Font.PLAIN, 120));
			g2d.setColor(Color.WHITE);
			g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
					RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			int stringLen = (int) g2d.getFontMetrics()
					.getStringBounds(PlayerStatus.getScore() + "", g2d)
					.getWidth();
			int start = ConfigurableOption.screenWidth / 2 - stringLen / 2;
			g2d.drawString(PlayerStatus.getScore() + "", start, 345);

		} else {
			if (!PlayerStatus.isEnterYourName()) {
				g2d.drawImage(DrawingUtility.newHighScore, null, 0, 0);
				g2d.setFont(new Font("junegull", Font.PLAIN, 120));
				g2d.setColor(Color.WHITE);
				g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
						RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
				int stringLen = (int) g2d.getFontMetrics()
						.getStringBounds(PlayerStatus.getScore() + "", g2d)
						.getWidth();
				int start = ConfigurableOption.screenWidth / 2 - stringLen / 2;
				g2d.drawString(PlayerStatus.getScore() + "", start, 345);
				showTextField = false;

			} else {
				PlayerStatus.setUseHighScore(true);
				g2d.drawImage(DrawingUtility.newHighScoreEnterName, null, 0, 0);

				if (!showTextField)
					showEnterYourName();
				showTextField = true;
			}
		}

		if (isPointOverOkEnter) {
			g2d.drawImage(DrawingUtility.okPushed, null, okEnterX, okEnterY);
		}
		if (isPointOverOkGameOver) {
			g2d.drawImage(DrawingUtility.okPushed, null, okGameOverX,
					okGameOverY);
		}

	}

	private void applyResize() {
		this.setPreferredSize(new Dimension(ConfigurableOption.screenWidth,
				ConfigurableOption.screenHeight));
		this.validate();
	}

}
