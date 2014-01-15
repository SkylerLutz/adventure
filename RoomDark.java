import java.util.HashMap;
import java.util.LinkedList;

// represents a dark room
public class RoomDark extends Room {

	public RoomDark(String description, String shortDescription, String darkDescription, String darkShortDescription) {

		this(description, shortDescription, darkDescription, darkShortDescription, true);
	}
	public RoomDark(String description, String shortDescription, String darkDescription, String darkShortDescription, boolean isDark){
		super(description, shortDescription);

		this.isDark = isDark;
		this.darkDescription = darkDescription;
		this.darkShortDescription = darkShortDescription;
		this.safeDirections = new LinkedList<Action>();
		this.deathMessage = null;
	}
	public boolean isDark() {
		return this.isDark;
	}
	public void setDark(boolean isDark) {
		this.isDark = isDark;
	}
	public void setSafeDirection(Action direction) {
		this.safeDirections.add(direction);
	}
	public boolean willDieInDirection(Action dir) {
		return !this.safeDirections.contains(dir);
	}
	public String deathMessage() {
		return this.deathMessage;
	}	
	public void setDeathMessage(String s) {
		this.deathMessage = s;
	}
	public String toString() {

		if(this.isDark) {
			

			if(this.player.hasItemOfType("Luminous")) {


				return super.toString();
			}
			else {
				return this.darkDescription;
			}
		}
		else {
			return super.toString();
		}
	}
	public String description() {
		if(this.isDark) {
			if(this.player.hasItemOfType("Luminous")) {
				String s = this.roomWasVisited ? this.shortDescription : this.description + "\n" + visibleItems();
				this.roomWasVisited = true;
				return s;
			}
			else {
				String s = this.roomWasVisited ? this.darkShortDescription : this.darkDescription;
				this.roomWasVisited = true;
				return s;
			}
		}
		else {
			String s = this.roomWasVisited ? this.shortDescription : this.description + "\n" + visibleItems();
			this.roomWasVisited = true;
			return s;
		}
	}
	protected LinkedList<Action> safeDirections;
	protected String darkDescription;
	protected String darkShortDescription;
	protected boolean isDark;
	protected String deathMessage;
}
