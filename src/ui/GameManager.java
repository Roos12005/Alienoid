package ui;

import render.AudioUtility;
import render.DrawingUtility;
import render.InputUtility;
import render.RenderManager;
import logic.ChargeSkill;
import logic.Enemy;
import logic.GreenAlien;
import logic.PinkAlien;
import logic.PlayerStatus;
import logic.ReleaseEnemy;
import logic.Rocket;
import logic.Setting;
import logic.Skill;
import logic.Time;

public class GameManager {

	private static final boolean _DEBUG = false;

	private static GameWindow gameWindow;
	private static GameTitle gameTitle;
	private static GameScreen gameScreen;
	private static YourScore yourScore;
	private static Setting setting;
	private static HighScore highScore;
	private static RenderManager renderManager;
	private static ReleaseEnemy releaseEnemy;

	private static Thread timer;
	private static Thread click;

	public static void runGame() {

		renderManager = new RenderManager();

		gameWindow = new GameWindow();
		gameTitle = new GameTitle(gameWindow);
		highScore = new HighScore(gameWindow);
		gameScreen = new GameScreen(renderManager);
		releaseEnemy = new ReleaseEnemy();
		yourScore = new YourScore(gameWindow);
		setting = new Setting(gameWindow);

		gameWindow.addFirstScene(gameTitle);
		AudioUtility.playSoundBackground();

		// Timer
		timer = new Thread(new Time());
		timer.start();

		// Clicker
		click = new Thread(new ChargeSkill());
		click.start();

		while (true) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (gameWindow.getCurrentScene() instanceof GameTitle) {
				gameWindow.switchScene(gameTitle);
			} else if (gameWindow.getCurrentScene() instanceof GameScreen) {
				gameWindow.switchScene(gameScreen);
				newGame();
			} else if (gameWindow.getCurrentScene() instanceof HighScore) {
				gameWindow.switchScene(highScore);
			} else if (gameWindow.getCurrentScene() instanceof YourScore
					&& !PlayerStatus.isUseHighScore()) {
				gameWindow.switchScene(yourScore);
			} else if (gameWindow.getCurrentScene() instanceof Setting) {
				gameWindow.switchScene(setting);
			}
		}

	}

	public static void newGame() {
		// Init for new game
		gameInit();
		PlayerStatus.setStarted(true);
		runGameLoop();
		endGame();
	}

	private static void runGameLoop() {
		int idx = 0;

		while (!PlayerStatus.isEnded()) {

			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (idx < releaseEnemy.getGenEnemies().size()
					&& Time.getTime() >= ((Enemy)releaseEnemy.getGenEnemies().get(idx)
							).getTimeToRelease()) {

				renderManager.enemies
						.add(releaseEnemy.getGenEnemies().get(idx));
				idx++;
				if (_DEBUG
						&& idx != 0
						&& ((Enemy)releaseEnemy.getGenEnemies().get(idx)
								).getWave() != ((Enemy)releaseEnemy.getGenEnemies().get(idx-1)
										).getWave())
					System.out.println("wave "
							+ ((Enemy)releaseEnemy.getGenEnemies().get(idx)
									).getWave());

			}

			if (!PlayerStatus.isPaused())
				gameScreen.repaint();

			updateLogic();
			InputUtility.postUpdate();
			renderManager.update();
		}
	}

	public static void endGame() {
		AudioUtility.stopSoundBackground();
		HighScore.recordHighScore(PlayerStatus.getScore());
		PlayerStatus.setEnded(true);
	}

	private static void gameInit() {
		PlayerStatus.setScore(0);
		PlayerStatus.setEnded(false);
		PlayerStatus.setUseHighScore(false);
		PlayerStatus.setEnterYourName(false);

		// Reset Time
		Time.setTime(0);

		// Reset Skill Cooldown
		Skill.setFillSkillClick(0);
		Skill.setFillSkillLight(0);

		// Clear All Enemies
		renderManager.enemies.clear();

		// Generate New Enemies Troop
		releaseEnemy.generateRandomEnemies();

		// Reset Rocket
		Rocket.reset();

	}

	private static void updateLogic() {

		// Attack Alien
		int i;
		for (i = renderManager.enemies.size() - 1; i >= 0; i--) {
			Enemy alien = (Enemy) (renderManager.enemies.get(i));
			boolean hit = false;
			if (alien instanceof GreenAlien)
				hit = Skill.checkSkill(alien, alien.getPost().x, 30,
						alien.getPost().y, DrawingUtility.greenHeight);
			else if (alien instanceof PinkAlien) {
				hit = Skill.checkSkill(alien, alien.getPost().x, 40,
						alien.getPost().y, DrawingUtility.pinkHeight);
				if (_DEBUG && hit)
					System.out.println(hit);
			}
			if (hit) {
				alien.setLife(alien.getLife() - Skill.damagePerClick);
				if (alien.getLife() <= 0) {
					alien.setDead(true); // KILL !!!
					PlayerStatus.setScore(PlayerStatus.getScore()
							+ alien.getScore());
					if (_DEBUG)
						System.out.println("DEAD");
					if (alien instanceof GreenAlien)
						AudioUtility.playDieGreenSound();
					else if (alien instanceof PinkAlien)
						AudioUtility.playDiePinkSound();
				}
				break;
			}
		}

		if (Skill.resetNextTickClick) {
			Skill.setFillSkillClick(0);
			Skill.resetNextTickClick = false;
		}
		if (Skill.resetNextTickLight) {
			Skill.setFillSkillLight(0);
			Skill.resetNextTickLight = false;
		}

	}

	public static GameScreen getGameScreen() {
		return gameScreen;
	}

	public static HighScore getHighScore() {
		return highScore;
	}

	public static GameTitle getGameTitle() {
		return gameTitle;
	}

	public static YourScore getYourScore() {
		return yourScore;
	}

	public static Setting getSetting() {
		return setting;
	}

}
