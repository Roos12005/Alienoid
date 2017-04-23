package logic;

import render.AudioUtility;

public class SkillLightUpdate implements Runnable {

	@Override
	public void run() {
		
			
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					
				}
				Skill.isUsedSkill2 = false;
				Skill.setSkillType(Skill.SKILL_CLICK);		
				AudioUtility.stopFreezeBackground();

	}

}
