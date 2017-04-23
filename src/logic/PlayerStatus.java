package logic;

public class PlayerStatus {
	private static boolean isPaused = false;
	private static boolean isEnded = false;
	private static boolean isStarted = false;
	private static int score = 0;
	private static boolean isUseHighScore = false;

	
	private static boolean isGameOver;
	private static boolean isEnterYourName;
	private static boolean isOkOnEnterYourName;
	
	private static boolean isOffBgSound = false;
	private static boolean isOffFxSound = false;
	

	public static boolean isUseHighScore() {
		return isUseHighScore;
	}

	public static void setUseHighScore(boolean isUseHighScore) {
		PlayerStatus.isUseHighScore = isUseHighScore;
	}

	
	public static boolean isOkOnEnterYourName() {
		return isOkOnEnterYourName;
	}

	public static void setOkOnEnterYourName(boolean isOkOnEnterYourName) {
		PlayerStatus.isOkOnEnterYourName = isOkOnEnterYourName;
	}

	public static boolean isGameOver() {
		return isGameOver;
	}

	public static void setGameOver(boolean isGameOver) {
		PlayerStatus.isGameOver = isGameOver;
	}

	public static boolean isEnterYourName() {
		return isEnterYourName;
	}

	public static void setEnterYourName(boolean isEnterYourName) {
		PlayerStatus.isEnterYourName = isEnterYourName;
	}

	public static int getScore() {
		return score;
	}
	
	public static void setScore(int score) {
		PlayerStatus.score = score;
	}

	public static boolean isStarted() {
		return isStarted;
	}

	public static void setStarted(boolean isStarted) {
		PlayerStatus.isStarted = isStarted;
	}


	public static boolean isEnded() {
		return isEnded;
	}

	public static void setEnded(boolean isEnded) {
		PlayerStatus.isEnded = isEnded;
	}

	public static synchronized boolean isPaused() {
		return isPaused;
	}

	public static synchronized void setPaused(boolean isPaused) {
		PlayerStatus.isPaused = isPaused;
	}

	public static boolean isOffBgSound() {
		return isOffBgSound;
	}

	public static void setOffBgSound(boolean isOffBgSound) {
		PlayerStatus.isOffBgSound = isOffBgSound;
	}

	public static boolean isOffFxSound() {
		return isOffFxSound;
	}

	public static void setOffFxSound(boolean isOffFxSound) {
		PlayerStatus.isOffFxSound = isOffFxSound;
	}
}
