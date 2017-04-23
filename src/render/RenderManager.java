package render;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import javax.imageio.ImageIO;

import logic.Enemy;
import logic.GreenAlien;
import logic.Position;
import logic.Skill;
import logic.Time;

public class RenderManager {

	public ArrayList<IRenderable> enemies;

	public RenderManager() {
		enemies = new ArrayList<IRenderable>();

	}

	public void add(IRenderable enemy) {
		enemies.add(enemy);

		// ASC Order
		Collections.sort(enemies, new Comparator<IRenderable>() {
			@Override
			public int compare(IRenderable o1, IRenderable o2) {
				if (o1.getZ() > o2.getZ())
					return 1;
				return -1;
			}
		});

	}

	public void drawAll(Graphics2D g2d) {
		for (int index = 0; index < enemies.size(); index++) {
			IRenderable enemy = enemies.get(index);
			if (enemy.isVisible())
				enemy.draw(g2d);
		}
	}

	public void update() {
		for (int i = enemies.size() - 1; i >= 0; i--) {
			if (enemies.get(i).isDead())
				enemies.remove(i);
		}
	}
}