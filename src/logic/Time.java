package logic;

import ui.GameManager;

public class Time implements Runnable {

	private static int time = 0;

	@Override
	public void run() {
		// set time
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}

			if (PlayerStatus.isPaused() || !PlayerStatus.isStarted())
				continue;

			time++;
		}

	}

	public static int getTime() {
		return time;
	}

	public static void setTime(int time) {
		Time.time = time;
	}

}
