package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import logic.PlayerStatus;
import logic.Position;
import logic.Rocket;
import logic.Skill;
import render.AudioUtility;
import render.ConfigurableOption;
import render.DrawingUtility;
import render.InputUtility;
import render.RenderManager;

public class GameScreen extends JPanel {
	private static final int pauseX = 711;
	private static final int pauseY = 19;
	private static boolean isPointOverPause = false;

	private RenderManager renderManager;

	public GameScreen(RenderManager renderMager) {
		this.renderManager = renderMager;
		setDoubleBuffered(true);
		addListener();
		applyResize();

		// render.TestPixel.test(this);
	}

	private void applyResize() {
		this.setPreferredSize(new Dimension(ConfigurableOption.screenWidth,
				ConfigurableOption.screenHeight));
		this.validate();
	}

	private void addListener() {
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				if (arg0.getButton() == MouseEvent.BUTTON1) // Left
					InputUtility.mouseLeftRelease();
				else if (arg0.getButton() == MouseEvent.BUTTON3) // Right
					InputUtility.mouseRightRelease();

				InputUtility.setPosReleased(new Position(arg0.getX(), arg0
						.getY()));
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				if (arg0.getButton() == MouseEvent.BUTTON1) // Left
				{
					InputUtility.mouseLeftDown();

					// PAUSE BUTTON
					if (arg0.getX() > 711
							&& arg0.getX() < pauseX
									+ DrawingUtility.pausePushed.getWidth()
							&& arg0.getY() > pauseY
							&& arg0.getY() < pauseY
									+ DrawingUtility.pausePushed.getHeight()) {

						synchronized (this) {
							PlayerStatus.setPaused(!PlayerStatus.isPaused());
						}

						AudioUtility.playSoundButton();
					} else {

						// USE & RESET SKILL
						if (Skill.getSkillType() == Skill.SKILL_CLICK
								&& Skill.getFillSkillClick() == Skill.skillHeight)
							Skill.resetNextTickClick = true;
						if (Skill.getSkillType() == Skill.SKILL_LIGHT
								&& Skill.getFillSkillLight() == Skill.skillHeight)
							Skill.resetNextTickLight = true;

						// update skill
						Skill.castSkill2();
					}

				} else if (arg0.getButton() == MouseEvent.BUTTON3) { // Right
					InputUtility.mouseRightDown();
				}

				InputUtility.setPosClicked(new Position(arg0.getX(), arg0
						.getY()));

				if (arg0.getButton() == MouseEvent.BUTTON3) {// RIGHT CLICK
					Skill.changeSkill();
				}

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		this.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent arg0) {
				if (arg0.getX() > 711
						&& arg0.getX() < pauseX
								+ DrawingUtility.pausePushed.getWidth()
						&& arg0.getY() > pauseY
						&& arg0.getY() < pauseY
								+ DrawingUtility.pausePushed.getHeight()) {
					if (!isPointOverPause) {
						isPointOverPause = true;

					}
				} else {
					isPointOverPause = false;

				}

			}

			@Override
			public void mouseDragged(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		drawBackground(g2d);
		Rocket.drawRocket(g2d);
		renderManager.drawAll(g2d);

		Skill.animationFillSkillClick(g2d);
		Skill.animationFillSkillLight(g2d);
		Skill.animationChooseSkill(g2d);

		drawScore(g2d);
		if (isPointOverPause)
			g2d.drawImage(DrawingUtility.pausePushed, null, 711, 19);

	}

	private void drawScore(Graphics2D g2d) {

		g2d.setColor(Color.DARK_GRAY);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		g2d.setFont(new Font("junegull", Font.BOLD, 30));
		g2d.drawString("SCORE", 527, 42);

		g2d.setFont(new Font("junegull", Font.BOLD, 60));
		g2d.drawString(PlayerStatus.getScore() + "", 527, 82);

	}

	private void drawBackground(Graphics2D g2d) {
		if (!Skill.isUsedSkill2)
			g2d.drawImage(DrawingUtility.bgGameScreen, null, 0, 0);
		else
			g2d.drawImage(DrawingUtility.skillFreezeBg, null, 0, 0);
		// drawEmptyRocket(g2d);

	}
}
