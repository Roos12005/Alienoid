package logic;

import java.util.ArrayList;

import render.ConfigurableOption;
import render.IRenderable;

public class ReleaseEnemy {

	private ArrayList<IRenderable> genEnemies;
	private static final int postRX = 800;
	private static final int postLX = 0;
	private static final int postY = ConfigurableOption.screenHeight - 120;
	private static final int postUL = 130;
	public static final int WAVE = 1000;
	private static final int WAIT_TIME_PER_WAVE = 50;
	private static final int WAIT_BEFORE_START_GAME = 10;

	private Position right = new Position(postRX, postY);
	private Position left = new Position(postLX, postY);

	public ReleaseEnemy() {
		genEnemies = new ArrayList<IRenderable>();
		generateRandomEnemies();
	}

	// random = [min, max]
	public static int random(int min, int max) {
		return (min + (int) (Math.random() * (max - min + 1)));
	}

	public void generateRandomEnemies() {
		int wave = 1;
		int z = 0;
		int alien = 0;

		genEnemies.clear();

		boolean lastTimeIsPink = false;
		boolean justEndWave = false;
		int time_release = WAIT_BEFORE_START_GAME;
		int alien_per_wave= 30;

		while (wave <= WAVE) {
			Position post = random(0, 1) == 0 ? left : right;

			// EDIT HERE TIME PER WAVE

			int delay_time = random(3, 10) + (lastTimeIsPink ? 3 : 0);
			
			if(justEndWave)
				time_release += WAIT_TIME_PER_WAVE;
			else
				time_release += delay_time;
			
			justEndWave = lastTimeIsPink = false;

			boolean isWalkR = random(0, 1) == 0 ? false : true;
			boolean isClimbR = random(0, 1) == 0 ? false : true;

			int alienType = random(1, 100);
			if (alienType < 80)
				genEnemies.add(new GreenAlien(post, z, time_release, wave,
						isWalkR, isClimbR));
			else {
				genEnemies.add(new PinkAlien(post, z, time_release, wave,
						isWalkR, isClimbR));
				lastTimeIsPink = true;
			}
			
			z++;
			alien++;
			if (alien == alien_per_wave - 1) {
				alien = 0;
				wave++;
				justEndWave = true;
				if (wave % 2 == 0)	//Every 5 wave 
					alien_per_wave += 5;
			}

		}
	}

	public ArrayList<IRenderable> getGenEnemies() {
		return genEnemies;
	}

	public void setGenEnemies(ArrayList<IRenderable> genEnemies) {
		this.genEnemies = genEnemies;
	}
}