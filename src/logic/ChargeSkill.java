package logic;


public class ChargeSkill implements Runnable {

	private static final int FILL_DELAY_CLICK = 1;
	private static final int FILL_DELAY_LIGHT = 20;
	private static int fillClickCount = 0;
	private static int fillLightCount = 0;

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {

			}
			
			//PAUSE
			if(PlayerStatus.isPaused() || !PlayerStatus.isStarted())
				continue;
			
			// SKILL1
			if (fillClickCount < FILL_DELAY_CLICK)
				fillClickCount++;
			else {
				fillClickCount = 0;
				Skill.setFillSkillClick(Skill.getFillSkillClick() + 2);
			}

			// SKILL2
			if (fillLightCount < FILL_DELAY_LIGHT)
				fillLightCount++;
			else {
				fillLightCount = 0;
				Skill.setFillSkillLight(Skill.getFillSkillLight() + 1);
			}
		}

	}

	public static void setFillClickCount(int fillClickCount) {
		ChargeSkill.fillClickCount = fillClickCount;
	}

	public static void setFillLightCount(int fillLightCount) {
		ChargeSkill.fillLightCount = fillLightCount;
	}

}
