package logic;

import java.awt.Graphics2D;

import render.AudioUtility;
import render.ConfigurableOption;
import render.DrawingUtility;

public class GreenAlien extends Enemy {

	private static final int LIFE = 1;
	private static final int SPEED = 2;
	private static final int INCROCKET = 100;
	private static final int SCORE = 2;

	private static final int delayWalk = 6;
	private int countDelayWalk = 0;
	private int countFrameWalk = 0;

	private static final int delayClimb = 8;
	private int countDelayClimb = 0;
	private int countFrameClimb = 0;

	private boolean isDown = true;
	private static final int delayFill = 30;
	private int countDelayFill = 0;

	private static final int postYD = ConfigurableOption.screenHeight - 120;
	private static final int postYU = ConfigurableOption.screenHeight - 238;
	private static final int postLadderL = 127;
	private static final int postLadderR = 548;

	private static final int minX_Rocket = Rocket.rocketX + 7;
	private static final int maxX_Rocket = Rocket.rocketX + Rocket.rocketWidth
			- 40;

	private int speedWalk = 0;
	private int speedWalk2 = 0;
	private int speedClimb = 0;
	private boolean resetSpeedWalk = false;

	// Green(pos, z, time, wave, fromR, ladderR)
	public GreenAlien(Position post, int z, int time_release, int wave,
			boolean isWalkR, boolean isClimbR) {
		super(LIFE, SPEED, INCROCKET, z, wave, time_release, post, isWalkR,
				isClimbR, SCORE);
		setRandomRocPost((int) (Math.random() * (maxX_Rocket - minX_Rocket) + minX_Rocket));
	}

	@Override
	public void animationWalkL(Graphics2D g2d, int postX, int postY) {

		// Skill.checkSkill(this, postX - speedWalk, 30, postY, greenHeight,
		// g2d);

		if (countDelayWalk == delayWalk) {
			if (!Skill.isUsedSkill2) { // ----------------------
				countFrameWalk++;
				countDelayWalk = 0;
				if (countFrameWalk > 1)
					countFrameWalk = 0;
			}
		} else if (!Skill.isUsedSkill2)// --------------------------
			countDelayWalk++;

		setPost(new Position(postX - speedWalk, postY)); // identify position

		if (countFrameWalk == 0) {
			g2d.drawImage(DrawingUtility.greenWalkL1, null, postX - speedWalk,
					postY);
			if (!Skill.isUsedSkill2) // -----------------------------
				speedWalk += SPEED;
		} else if (countFrameWalk == 1) {
			g2d.drawImage(DrawingUtility.greenWalkL2, null, postX - speedWalk,
					postY);
			if (!Skill.isUsedSkill2)// ---------------------------------
				speedWalk += SPEED;
		}

	}

	@Override
	public void animationWalkR(Graphics2D g2d, int postX, int postY) {

		// Skill.checkSkill(this, postX + speedWalk, 30, postY, greenHeight,
		// g2d);

		if (countDelayWalk == delayWalk) {
			if (!Skill.isUsedSkill2) {// ---------------
				countFrameWalk++;
				countDelayWalk = 0;
				if (countFrameWalk > 1)
					countFrameWalk = 0;
			}
		} else if (!Skill.isUsedSkill2)// -----------------
			countDelayWalk++;

		setPost(new Position(postX + speedWalk, postY)); // identify position

		if (countFrameWalk == 0) {
			g2d.drawImage(DrawingUtility.greenWalkR1, null, postX + speedWalk,
					postY);
			if (!Skill.isUsedSkill2)// -----------------
				speedWalk += SPEED;
		} else if (countFrameWalk == 1) {
			g2d.drawImage(DrawingUtility.greenWalkR2, null, postX + speedWalk,
					postY);
			if (!Skill.isUsedSkill2)// -----------------
				speedWalk += SPEED;
		}

	}

	@Override
	public void draw(Graphics2D g2d) {
		if (isDead())
			animationDie(g2d);

		else {

			if (isDown) {
				if (isWalkR()) {
					if (isClimbR()) {
						if (speedWalk >= postLadderR
								&& speedWalk <= postLadderR + 3)
							animationClimb(g2d, speedWalk);
						else
							animationWalkR(g2d, 0, postYD);
					}

					else {
						if (speedWalk >= postLadderL
								&& speedWalk <= postLadderL + 3)
							animationClimb(g2d, speedWalk);
						else
							animationWalkR(g2d, 0, postYD);
					}

				} else {
					if (isClimbR()) {
						if (ConfigurableOption.screenWidth - speedWalk >= postLadderR
								&& ConfigurableOption.screenWidth - speedWalk <= postLadderR + 3)
							animationClimb(g2d, ConfigurableOption.screenWidth
									- speedWalk);
						else
							animationWalkL(g2d, ConfigurableOption.screenWidth,
									postYD);
					}

					else {
						if (ConfigurableOption.screenWidth - speedWalk >= postLadderL
								&& ConfigurableOption.screenWidth - speedWalk <= postLadderL + 3)
							animationClimb(g2d, ConfigurableOption.screenWidth
									- speedWalk);
						else
							animationWalkL(g2d, ConfigurableOption.screenWidth,
									postYD);
					}

				}
			}
			// stair2
			else {
				if (speedWalk != 0 && !resetSpeedWalk) {

					speedWalk2 = speedWalk;
					speedWalk = 0;
					resetSpeedWalk = true;
				}

				if (isClimbR()) {
					if (postLadderR - speedWalk <= getRandomRocPost())
						animationFillRocket(g2d, postLadderR - speedWalk);

					else if (isWalkR())
						animationWalkL(g2d, speedWalk2, postYU);
					else
						animationWalkL(g2d, ConfigurableOption.screenWidth
								- speedWalk2, postYU);

				}

				else {
					if (postLadderL + speedWalk >= getRandomRocPost())
						animationFillRocket(g2d, postLadderL + speedWalk);

					else if (isWalkR())
						animationWalkR(g2d, speedWalk2, postYU);
					else
						animationWalkR(g2d, ConfigurableOption.screenWidth
								- speedWalk2, postYU);

				}

			}
		}

	}

	@Override
	public void animationClimb(Graphics2D g2d, int postLadder) {

		// Skill.checkSkill(this, postLadder, 30, postYD - speedClimb,
		// greenHeight, g2d);

		if (postYD - speedClimb > postYU) {
			if (countDelayClimb == delayClimb) {
				if (!Skill.isUsedSkill2) {// -----------------
					countFrameClimb++;
					countDelayClimb = 0;
					if (countFrameClimb > 1)
						countFrameClimb = 0;
				}
			} else if (!Skill.isUsedSkill2)// -----------------
				countDelayClimb++;

			setPost(new Position(postLadder, postYD - speedClimb)); // identify
																	// position

			if (countFrameClimb == 0) {
				g2d.drawImage(DrawingUtility.greenClimb1, null, postLadder,
						postYD - speedClimb);
				if (!Skill.isUsedSkill2)// -----------------
					speedClimb += SPEED;
			} else if (countFrameClimb == 1) {
				g2d.drawImage(DrawingUtility.greenClimb2, null, postLadder,
						postYD - speedClimb);
				if (!Skill.isUsedSkill2)// -----------------
					speedClimb += SPEED;
			}
		}

		else {
			isDown = false;
			g2d.drawImage(DrawingUtility.greenClimb1, null, postLadder, postYD
					- speedClimb);
		}

	}

	@Override
	public void animationDie(Graphics2D g2d) {
		this.setDead(true);
	}

	@Override
	public void animationFillRocket(Graphics2D g2d, int x) {

		if (countDelayFill < delayFill) {
			g2d.drawImage(DrawingUtility.greenFill, null, x, postYU);
			if (!Skill.isUsedSkill2)
				countDelayFill++;
		} else {
			AudioUtility.playAlienBuildSound();
			this.setDead(true);
			Rocket.fillAndDrawRocket(g2d, INCROCKET);
		}

	}

}
