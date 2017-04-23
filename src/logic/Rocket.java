package logic;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import render.DrawingUtility;

public class Rocket {

	private static int lifeOuterLevel = 0;
	private static int lifeInnerLevel = 0;
	
	private static boolean draftFull = false;
	
	public static final int rocketWidth = DrawingUtility.fullRocket.getWidth();
	private static final int fullRocketLife = DrawingUtility.fullRocket.getHeight();
	
	public static final int rocketX = 248;
	public static final int rocketY = 85;

	public static void fillAndDrawRocket(Graphics2D g2d, int fillCount) {

		if (!draftFull) {
			lifeInnerLevel += fillCount;
			if (lifeInnerLevel < fullRocketLife)
				g2d.drawImage(
						DrawingUtility.emptyRocket.getSubimage(0, fullRocketLife
								- lifeInnerLevel, rocketWidth, lifeInnerLevel),
						null, rocketX, rocketY + fullRocketLife - lifeInnerLevel);
			else if (lifeInnerLevel >= fullRocketLife) {
				draftFull = true;
				g2d.drawImage(DrawingUtility.emptyRocket, null, rocketX,
						rocketY);
			}
		}

		else {
			g2d.drawImage(DrawingUtility.emptyRocket, null, rocketX, rocketY);
			lifeOuterLevel += fillCount;
			if (lifeOuterLevel < fullRocketLife)
				g2d.drawImage(DrawingUtility.fullRocket.getSubimage(0, fullRocketLife - lifeOuterLevel,
						rocketWidth, lifeOuterLevel), null, rocketX, rocketY
						+ fullRocketLife - lifeOuterLevel);
			else if (lifeOuterLevel >= fullRocketLife) {
				g2d.drawImage(DrawingUtility.fullRocket, null, rocketX, rocketY);
			}
		}

	}

	public static void drawRocket(Graphics2D g2d) {
		if (!draftFull) {
			if (lifeInnerLevel != 0 && lifeInnerLevel < fullRocketLife)
				g2d.drawImage(
						DrawingUtility.emptyRocket.getSubimage(0, fullRocketLife
								- lifeInnerLevel, rocketWidth, lifeInnerLevel),
						null, rocketX, rocketY + fullRocketLife - lifeInnerLevel);
			else if (lifeOuterLevel >= fullRocketLife) {
				g2d.drawImage(DrawingUtility.emptyRocket, null, rocketX,
						rocketY);
			}
		}

		else {
			g2d.drawImage(DrawingUtility.emptyRocket, null, rocketX, rocketY);
			if (lifeOuterLevel != 0 && lifeOuterLevel < fullRocketLife) {
				g2d.drawImage(DrawingUtility.fullRocket.getSubimage(0, fullRocketLife - lifeOuterLevel,
						rocketWidth, lifeOuterLevel), null, rocketX, rocketY
						+ fullRocketLife - lifeOuterLevel);
			} else if (lifeOuterLevel >= fullRocketLife) {

				g2d.drawImage(DrawingUtility.fullRocket, null, rocketX, rocketY);
				PlayerStatus.setEnded(true);
			}
		}

	}

	public static void reset() {
		lifeOuterLevel = 0;
		lifeInnerLevel = 0;
		draftFull = false;
	}
}
