package logic;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import render.AudioUtility;
import render.DrawingUtility;
import render.InputUtility;

public class Skill {
	public static final int damagePerClick = 1;

	public static boolean isUsedSkill2 = false;

	public static final int SKILL_CLICK = 1;
	public static final int SKILL_LIGHT = 2;

	public static final int skillHeight = DrawingUtility.skillClick.getHeight();
	public static final int skillWidth = DrawingUtility.skillClick.getWidth();
	
	private static final int skillClickX = 37;
	private static final int skillLightX = 139;
	private static final int skillY = 32;

	private static int fillSkillClick = 0;
	private static int fillSkillLight = 0;

	public static boolean resetNextTickClick = false;
	public static boolean resetNextTickLight = false;

	public static int getFillSkillClick() {
		return fillSkillClick;
	}

	private static int skillType = SKILL_CLICK;

	// public static Enemy enemy;
	// RETURN: Hit enemy (Y/N)
	public static boolean checkSkill(Enemy alien, int x, int width, int y,
			int height) {

		// CastSkill
		if (InputUtility.isLeftClickTriggered()) {

			int mouseX = InputUtility.getPosClicked().x;
			int mouseY = InputUtility.getPosClicked().y;

			if (!PlayerStatus.isPaused() && skillType == SKILL_CLICK
					&& fillSkillClick == skillHeight
					|| (skillType == SKILL_LIGHT && isUsedSkill2)) {

				AudioUtility.playClickSound();

				if (mouseX >= x && mouseX <= x + width && mouseY >= y
						&& mouseY <= y + height) { // Hit Alien
					return true;
				}
			}
		}

		return false;
	}

	public static int getSkillType() {
		return skillType;
	}

	public static void setSkillType(int skillType) {
		Skill.skillType = skillType;
	}

	public static void animationChooseSkill(Graphics2D g2d) {
		g2d.setStroke(new BasicStroke(6));
		g2d.setColor(new Color(214, 0, 0));
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		if (skillType == SKILL_CLICK)
			g2d.drawOval(37 - 1, 32 - 1, 82 + 2, 82 + 2);
		else if (skillType == SKILL_LIGHT)
			g2d.drawOval(139 - 1, 32 - 1, 82 + 1, 82 + 1);
	}

	public static void animationFillSkillClick(Graphics2D g2d) {
		if (fillSkillClick != 0 && fillSkillClick < skillHeight) {
			g2d.drawImage(
					DrawingUtility.skillClick.getSubimage(0, skillHeight
							- fillSkillClick, skillWidth, fillSkillClick),
					null, skillClickX, skillHeight + skillY - fillSkillClick);
		} else if (fillSkillClick >= skillHeight)
			g2d.drawImage(DrawingUtility.skillClick, null, skillClickX, skillY);
	}

	public static void animationFillSkillLight(Graphics2D g2d) {
		if (fillSkillLight != 0 && fillSkillLight < skillHeight) {
			g2d.drawImage(
					DrawingUtility.skillLight.getSubimage(0, skillHeight
							- fillSkillLight, skillWidth, fillSkillLight),
					null, skillLightX, skillHeight + skillY - fillSkillLight);
		} else if (fillSkillLight >= skillHeight)
			g2d.drawImage(DrawingUtility.skillLight, null, skillLightX, skillY);
	}

	public static void setFillSkillClick(int fillSkillClick) {
		if (fillSkillClick >= skillHeight)
			Skill.fillSkillClick = skillHeight;
		else
			Skill.fillSkillClick = fillSkillClick;
	}

	public static int getFillSkillLight() {
		return fillSkillLight;
	}

	public static void setFillSkillLight(int fillSkillLight) {
		if (fillSkillLight >= skillHeight)
			Skill.fillSkillLight = skillHeight;
		else
			Skill.fillSkillLight = fillSkillLight;
	}

	public static void changeSkill() {
		if (skillType == SKILL_CLICK)
			skillType = SKILL_LIGHT;
		else if (skillType == SKILL_LIGHT)
			skillType = SKILL_CLICK;

	}

	public static void castSkill2() {

		if (skillType == SKILL_LIGHT && fillSkillLight == skillHeight) {
			AudioUtility.playFreezeBackground();
			isUsedSkill2 = true;
			Thread skill2 = new Thread(new SkillLightUpdate());
			skill2.start();

		}
	}
}
