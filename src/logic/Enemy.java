package logic;

import java.awt.Graphics2D;

import render.IRenderable;

public abstract class Enemy implements IRenderable {
	private int life;
	private int speed;
	private int incRocket;
	private boolean isDead = false;
	private boolean visible = true;
	private Position post; // post generate
	private int z;
	
	private int wave;
	private int timeToRelease;
	private int randomRocPost;
	private boolean isWalkR;
	private boolean isClimbR;
	private int score;

	


	public synchronized int getScore() {
		return score;
	}

	public synchronized void setScore(int score) {
		this.score = score;
	}

	public Enemy(int life, int speed, int incRocket, int z, int wave,
			int time_release, Position post,boolean isWalkR,boolean isClimbR,int score) {
		this.life = life;
		this.speed = speed;
		this.post = post;
		this.incRocket = incRocket;
		this.z = z;
		this.wave = wave;
		this.timeToRelease = time_release;
		this.isWalkR = isWalkR;
		this.isClimbR = isClimbR;
		this.score = score;

	}

	public int getRandomRocPost() {
		return randomRocPost;
	}

	public void setRandomRocPost(int randomRocPost) {
		this.randomRocPost = randomRocPost;
	}


	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public void setVisible(boolean visible){
		this.visible = visible;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public Position getPost() {
		return post;
	}

	public void setPost(Position post) {
		this.post = post;
	}

	public abstract void animationWalkL(Graphics2D g2d,int postX,int postY);

	public abstract void animationWalkR(Graphics2D g2d,int postX,int postY);

	public abstract void animationClimb(Graphics2D g2d,int postLadder);
	
	public abstract void animationDie(Graphics2D g2d);
	
	public abstract void animationFillRocket(Graphics2D g2d,int x);

	public void walkL() {
		this.post.x = this.post.x - this.speed;

	}

	public void walkR() {
		this.post.x = this.post.x + this.speed;
	}

	public void walkUp() {
		this.post.y = this.post.y - this.speed / 2;
	}

	public int getIncRocket() {
		return incRocket;
	}

	public void setIncRocket(int incRocket) {
		this.incRocket = incRocket;
		
	}

	
	@Override
	public boolean isDead() {
		return isDead;
	}

	@Override
	public boolean isVisible() {
		return visible;
	}


	@Override
	public int getZ() {
		return z;
	}

	public int getTimeToRelease() {
		return timeToRelease;
	}

	public int getWave() {
		return wave;
	}
	
	public boolean isWalkR() {
		return isWalkR;
	}
	
	public boolean isClimbR() {
		return isClimbR;
	}

}
