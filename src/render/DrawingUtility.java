package render;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class DrawingUtility {

	public static final BufferedImage bgGameTitle = getImage("res/img/bgTitle.png");
	public static final BufferedImage bgGameScreen = getImage("res/img/bgBlank.png");
	public static final BufferedImage emptyRocket = getImage("res/img/rocketEmpty.png");
	public static final BufferedImage fullRocket = getImage("res/img/rocketFull.png");
	public static final BufferedImage greenSprite = getImage("res/character/SpriteSheetGreen.png");
	public static final BufferedImage pinkSprite = getImage("res/character/SpriteSheetPink.png");
	public static final BufferedImage startPushed = getImage("res/button/startPushed.png");
	public static final BufferedImage exitPushed = getImage("res/button/exitPushed.png");
	public static final BufferedImage highscorePushed = getImage("res/button/highscorePushed.png");
	public static final BufferedImage settingPushed = getImage("res/button/settingPushed.png");
	public static final BufferedImage pausePushed = getImage("res/button/pausePushed.png");
	public static final BufferedImage okPushed = getImage("res/button/okPushed.png");
	public static final BufferedImage skillClick = getImage("res/skill/skill1.png");
	public static final BufferedImage skillLight = getImage("res/skill/skill2.png");
	public static final BufferedImage skillFreezeBg = getImage("res/img/bgFreeze.png");
	public static final BufferedImage highscoreBg = getImage("res/img/highScore.png");
	public static final BufferedImage greenFill = getImage(
			"res/character/SpriteSheetGreen.png").getSubimage(0, 0,
			greenSprite.getWidth() / 7, greenSprite.getHeight());
	public static final BufferedImage pinkFill = getImage(
			"res/character/SpriteSheetPink.png").getSubimage(0, 0,
			pinkSprite.getWidth() / 7, pinkSprite.getHeight());
	public static final BufferedImage gameOver = getImage("res/img/GameOver.png");
	public static final BufferedImage newHighScore = getImage("res/img/newHighScore.png");
	public static final BufferedImage newHighScoreEnterName = getImage("res/img/newHighScoreEnterName.png");
	public static final BufferedImage settingBgOn = getImage("res/img/settingON.png");
	public static final BufferedImage settingBgOff = getImage("res/img/SettingOff.png");

	public static final BufferedImage bgSoundOff = settingBgOff.getSubimage(
			213, 372, 384, 149);
	public static final BufferedImage fxSoundOff = settingBgOff.getSubimage(
			213, 206, 384, 149);

	public static final int greenWidth = greenSprite.getWidth() / 7;
	public static final int greenHeight = greenSprite.getHeight();
	public static final BufferedImage greenWalkR1 = greenSprite.getSubimage(
			greenWidth * 5, 0, greenWidth, greenHeight);
	public static final BufferedImage greenWalkR2 = greenSprite.getSubimage(
			greenWidth * 6, 0, greenWidth, greenHeight);
	public static final BufferedImage greenWalkL1 = greenSprite.getSubimage(
			greenWidth * 3, 0, greenWidth, greenHeight);
	public static final BufferedImage greenWalkL2 = greenSprite.getSubimage(
			greenWidth * 4, 0, greenWidth, greenHeight);
	public static final BufferedImage greenClimb1 = greenSprite.getSubimage(
			greenWidth, 0, greenWidth, greenHeight);
	public static final BufferedImage greenClimb2 = greenSprite.getSubimage(
			greenWidth * 2, 0, greenWidth, greenHeight);


	public static final int pinkWidth = pinkSprite.getWidth() / 7;
	public static final int pinkHeight = pinkSprite.getHeight();
	public static final BufferedImage pinkWalkR1 = pinkSprite.getSubimage(
			pinkWidth * 5, 0, pinkWidth, pinkHeight);
	public static final BufferedImage pinkWalkR2 = pinkSprite.getSubimage(
			pinkWidth * 6, 0, pinkWidth, pinkHeight);
	public static final BufferedImage pinkWalkL1 = pinkSprite.getSubimage(
			pinkWidth * 3, 0, pinkWidth, pinkHeight);
	public static final BufferedImage pinkWalkL2 = pinkSprite.getSubimage(
			pinkWidth * 4, 0, pinkWidth, pinkHeight);
	public static final BufferedImage pinkClimb1 = pinkSprite.getSubimage(
			pinkWidth, 0, pinkWidth, pinkHeight);
	public static final BufferedImage pinkClimb2 = pinkSprite.getSubimage(
			pinkWidth * 2, 0, pinkWidth, pinkHeight);
	
	private static BufferedImage getImage(String directory) {
		ClassLoader loader = DrawingUtility.class.getClassLoader();
		BufferedImage bf;
		try {
			bf = ImageIO.read(loader.getResource(directory));
		} catch (Exception e) {
			bf = null;
		}
		return bf;

	}
}
