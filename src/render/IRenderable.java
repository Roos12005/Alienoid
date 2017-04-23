package render;

import java.awt.Graphics2D;

public interface IRenderable {
	int getZ();

	void draw(Graphics2D g2d);

	boolean isDead();

	boolean isVisible();

}
