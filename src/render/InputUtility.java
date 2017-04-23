package render;

import logic.Position;

public class InputUtility {
	private static boolean isLeftDown = false;
	private static boolean isLeftClickedLastTick =false;
	private static boolean isLeftReleased= false;
	
	private static boolean isRightDown = false;
	private static boolean isRightClickedLastTick =false;
	private static boolean isRightReleased= false;
	
	private static Position posClicked = new Position(0, 0);
	private static Position posReleased = new Position(0, 0);

	

	public static Position getPosClicked() {
		return posClicked;
	}

	public static Position getPosReleased() {
		return posReleased;
	}

	public static void setPosClicked(Position posClicked) {
		InputUtility.posClicked = posClicked;
	}

	public static void setPosReleased(Position posReleased) {
		InputUtility.posReleased = posReleased;
	}

	public static void mouseLeftDown(){
		isLeftDown = true;
		isLeftClickedLastTick = true;
	}
	
	public static void mouseLeftRelease(){
		isLeftDown = false;
	}
	
	public static boolean isLeftClickTriggered(){
		return isLeftClickedLastTick;
	}

	public static void mouseRightDown(){
		isRightDown = true;
		isRightClickedLastTick = true;
	}
	
	public static void mouseRightRelease(){
		isRightDown = false;
	}
	
	public static boolean isRightClickTriggered(){
		return isRightClickedLastTick;
	}
	

	public static void postUpdate() {
		isLeftClickedLastTick = false;
		isRightClickedLastTick = false;
	}
	
}
