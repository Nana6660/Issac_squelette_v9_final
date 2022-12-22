package resources;

import libraries.Keybinding;

public class Controls
{
	//Touche déplacement
	public static int goUp = Keybinding.keycodeOf('z');
	public static int goDown = Keybinding.keycodeOf('s');
	public static int goRight = Keybinding.keycodeOf('d');
	public static int goLeft = Keybinding.keycodeOf('q');

	// Touches directionnelles
	public static int tirUp = Keybinding.keycodeOf(Keybinding.SpecialKeys.UP);
	public static int tirDown = Keybinding.keycodeOf(Keybinding.SpecialKeys.DOWN);
	public static int tirRight = Keybinding.keycodeOf(Keybinding.SpecialKeys.RIGHT);
	public static int tirLeft = Keybinding.keycodeOf(Keybinding.SpecialKeys.LEFT);

	// Touches de triche
	public static int invincible = Keybinding.keycodeOf('i');
	public static int rapide = Keybinding.keycodeOf('l');
	public static int lent = Keybinding.keycodeOf('m');
	public static int tuerTousLesMonstres = Keybinding.keycodeOf('k');
	public static int puissanceTotal = Keybinding.keycodeOf('p');
	public static int donnePiece = Keybinding.keycodeOf('o');
}
