package render;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import logic.PlayerStatus;

public class AudioUtility {
	private static AudioClip backgroundSound = getSound("res/sound/BGM_JoyfulPixel.wav");
	private static AudioClip pushButtonSound = getSound("res/sound/pushButton.wav");
	private static AudioClip clickSound = getSound("res/sound/click.wav");
	private static AudioClip newHighScoreSound = getSound("res/sound/newhighscore.wav");
	private static AudioClip gameOverSound = getSound("res/sound/gameover.wav");
	private static AudioClip freezeBGSound = getSound("res/sound/BGM_freeze.wav");
	private static AudioClip dieGreenSound = getSound("res/sound/dieGreen.wav");
	private static AudioClip diePinkSound = getSound("res/sound/diePink.wav");
	private static AudioClip alienBuildSound = getSound("res/sound/alienBuild.wav");

	// private static AudioClip die = getSound(directory);;

	private static AudioClip getSound(String directory) {
		ClassLoader loader = AudioUtility.class.getClassLoader();
		AudioClip sound = Applet.newAudioClip(loader.getResource(directory));
		return sound;
	}
	
	public static void playFreezeBackground() {
		stopFreezeBackground();
		if (!PlayerStatus.isOffBgSound())
			freezeBGSound.loop();

	}

	public static void stopFreezeBackground() {
		freezeBGSound.stop();
	}

	public static void playSoundBackground() {
		stopSoundBackground();
		if (!PlayerStatus.isOffBgSound())
			backgroundSound.loop();

	}

	public static void stopSoundBackground() {
		backgroundSound.stop();
	}

	public static void playSoundButton() {
		if (!PlayerStatus.isOffFxSound())
			pushButtonSound.play();
	}

	public static void playClickSound() {
		if (!PlayerStatus.isOffFxSound())
			clickSound.play();
	}
	
	public static void playDieGreenSound() {
		if (!PlayerStatus.isOffFxSound())
			dieGreenSound.play();
	}
	
	public static void playDiePinkSound() {
		if (!PlayerStatus.isOffFxSound())
			diePinkSound.play();
	}

	public static void playAlienBuildSound() {
		if (!PlayerStatus.isOffFxSound())
			alienBuildSound.play();
	}
	
	public static void playNewHighScoreSound() {
		if (!PlayerStatus.isOffBgSound())
			newHighScoreSound.play();
	}

	public static void playGameOverSound() {
		if (!PlayerStatus.isOffBgSound())
			gameOverSound.play();
	}
	
	

}
