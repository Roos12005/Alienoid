package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import render.AudioUtility;
import render.ConfigurableOption;
import render.DrawingUtility;
import logic.PlayerStatus;
import logic.ScoreParsingException;

public class HighScore extends JPanel {

	private static final int highScoreX = 216;
	private static final int highScoreY = 15;
	private static final int highScoreW = 379;
	private static final int highScoreH = 564;

	private static GameWindow gameWindow;

	public static class HighScoreRecord implements Comparable<HighScoreRecord> {
		private String name = "";
		private int score = 0;

		public HighScoreRecord(String name, int score) {
			// Fill code--
			this.name = name;
			this.score = score;
			//
		}

		/*
		 * Parse the given string "record" record format is name:score
		 */
		public HighScoreRecord(String record) throws ScoreParsingException {
			// Fill code--

			if (record.indexOf(":") == -1)
				throw new ScoreParsingException(1);

			this.name = record.substring(0, record.indexOf(":"));

			try {

				score = Integer.parseInt(record.substring(
						record.indexOf(":") + 1, record.length()));
			} catch (IndexOutOfBoundsException e) {
				throw new ScoreParsingException(1);

			} catch (Exception e) {
				throw new ScoreParsingException(0);
			}

			//
		}

		private String getRecord() {
			return name.trim() + ":" + score;
		}

		private static String[] defaultRecord() {

			// ==============EDIT NAME LATER=======================
			return new String[] { "POH:40", "LOVE:35", "ROOS:30" };

		}

		@Override
		public int compareTo(HighScoreRecord o) {
			// Fill code--
			if (o.score < this.score)
				return -1;
			else if (o.score > this.score)
				return 1;
			else
				return 0;
			//
		}
	}

	private static HighScoreRecord[] highScoreList = null;

	private static String readFileName = "highscore";

	public HighScore(GameWindow gameWindow) {
		this.gameWindow = gameWindow;

		// Load from file for updating
		loadHighScore();
		applyResize();
		addListener();
	}

	private void applyResize() {
		this.setPreferredSize(new Dimension(ConfigurableOption.screenWidth,
				ConfigurableOption.screenHeight));
		this.validate();
	}

	private void addListener() {
		// add mouse
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == 1) {
					if (e.getX() <= highScoreX
							|| e.getX() >= highScoreX + highScoreW
							|| e.getY() <= highScoreY
							|| e.getY() >= highScoreY + highScoreH) {
						
							AudioUtility.playSoundButton();
						

						// Update Score
						loadHighScore();

						// gameWindow.switchScene(new GameTitle(gameWindow));
						System.out.println("set game title");
						gameWindow.setCurrentScene(GameManager.getGameTitle());
						// repaint();
					}
				}

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}

	// Aj's Code

	/*
	 * Display player's score and record if the player rank is 3 or higher.
	 */
	public static void recordHighScore(int score) {
		if (!loadHighScore() || highScoreList == null) {
			// Fill code --
			JOptionPane.showMessageDialog(null,
					"Error loading highscore record", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
			//
		}
		int index = highScoreList.length;
		for (int i = 0; i < highScoreList.length; i++) {
			if (score > highScoreList[i].score) {
				index = i;
				break;
			}
		}
		// System.out.println("YY");
		if (index >= highScoreList.length) {
			// Fill code--
			// JOptionPane.showMessageDialog(null, "Game over\nYour score is"
			// + score, "Game over", JOptionPane.INFORMATION_MESSAGE);
			PlayerStatus.setGameOver(true);
			AudioUtility.stopSoundBackground();
			AudioUtility.playGameOverSound();
			gameWindow.setCurrentScene(GameManager.getYourScore());

			//
		} else {
			for (int i = highScoreList.length - 1; i >= index + 1; i--) {
				highScoreList[i] = highScoreList[i - 1];
			}
			// Fill code--

			PlayerStatus.setGameOver(false);
			System.out.println("XX");
			// String name = JOptionPane.showInputDialog(null,
			// "Congratulation, you are ranked" + (index + 1)
			// + "\nPlease enter your name", "High score",
			// JOptionPane.INFORMATION_MESSAGE);
			AudioUtility.playNewHighScoreSound();
			gameWindow.setCurrentScene(GameManager.getYourScore());

		}
	}

	public static void recordHighScoreName(String name, int score) {
		int index = highScoreList.length;
		for (int i = 0; i < highScoreList.length; i++) {
			if (score > highScoreList[i].score) {
				index = i;
				break;
			}
		}
		highScoreList[index] = new HighScoreRecord(name, score);

		//

		try {

			BufferedWriter out = new BufferedWriter(new FileWriter("highscore"));
			// Fill code--
			String highScore = "";
			for (int i = 0; i < highScoreList.length; i++) {
				highScore += highScoreList[i].name + ":"
						+ highScoreList[i].score + "\n";
			}
			String newHigh = getXORed(highScore);
			out.write(newHigh);

			//
			out.close();
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null,
					"Error saving high score record", "Error",
					JOptionPane.ERROR_MESSAGE);
			highScoreList = null;
			return;
		}

	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		// draw background
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.drawImage(DrawingUtility.highscoreBg, null, 0, 0);
		drawTop3(g2d);
	}

	private void drawTop3(Graphics2D g2d) {
		// TODO Auto-generated method stub

		// Draw top 3
		// RANK1
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("junegull", Font.BOLD, 47));
		g2d.drawString(highScoreList[0].name, 387, 275);
		g2d.setFont(new Font("junegull", Font.PLAIN, 32));
		g2d.drawString(highScoreList[0].score + " PT", 387, 300);

		// RANK2
		g2d.setFont(new Font("junegull", Font.BOLD, 35));
		g2d.drawString(highScoreList[1].name, 298, 400);
		g2d.setFont(new Font("junegull", Font.PLAIN, 20));
		g2d.drawString(highScoreList[1].score + " PT", 299, 417);

		// RANK3
		g2d.setFont(new Font("junegull", Font.BOLD, 35));
		g2d.drawString(highScoreList[2].name, 388, 508);
		g2d.setFont(new Font("junegull", Font.PLAIN, 20));
		g2d.drawString(highScoreList[2].score + " PT", 389, 526);
		// drawString
		// TestPixel.test(this);
	}

	public static boolean loadHighScore() {
		File f = new File(readFileName);
		// If no high score, create default
		if (!f.exists()) {
			if (!createDefaultScoreFile())
				return false;
		}
		// Read high score -- if fail: delete the file, create default one and
		// read it again
		if (!readAndParseScoreFile(f)) {
			f.delete();
			if (!createDefaultScoreFile())
				return false;
			return readAndParseScoreFile(f);
		}
		return true;

	}

	private static boolean readAndParseScoreFile(File f) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(f));
			String line;
			highScoreList = new HighScoreRecord[3];
			String str = "";
			int c;
			while ((c = in.read()) != -1) {
				str += (char) c;
			}
			in.close();
			String[] records = getXORed(str).split("\n");
			for (int i = 0; i < highScoreList.length; i++) {
				try {
					highScoreList[i] = new HighScoreRecord(records[i]);
				} catch (ScoreParsingException e) {
					System.err.println("Error parsing line " + (i + 1) + ", "
							+ e.getMessage());
					highScoreList[i] = new HighScoreRecord("ERROR", 0);
				}
			}
			Arrays.sort(highScoreList);
			return true;
		} catch (Exception e) {
			highScoreList = null;
		}
		return false;
	}

	private static boolean createDefaultScoreFile() {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("highscore"));
			String str = "";
			for (String s : HighScoreRecord.defaultRecord()) {
				str += s + "\n";
			}
			str = str.trim();
			out.write(getXORed(str));
			out.close();
			return true;
		} catch (IOException e1) {
			highScoreList = null;
			return false;
		}
	}

	private static final byte[] key = "RmAAq2b5d8fjgu9dhher".getBytes();

	// This method does both encryption and decryption
	private static String getXORed(String in) {
		byte[] inData = in.getBytes();
		// Fill code--
		int temp = inData.length / key.length;
		int count = 0;
		int dataLength = 0;
		while (count <= temp) {
			for (int i = 0; i < key.length; i++) {
				if (dataLength == inData.length)
					break;
				inData[dataLength] = (byte) (inData[dataLength] ^ key[i]);
				dataLength++;
			}
			count++;
		}

		//
		return new String(inData);
	}

	public static void setReadFileName(String name) {
		readFileName = name;
	}

}
