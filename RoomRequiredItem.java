import java.util.LinkedList;

public class RoomRequiredItem extends Room {

	public RoomRequiredItem(String d, String dd, String w, String ws, Item requiredItem) {

		super(d, dd);
		this.warningDescription = w;
		this.warningShortDescription = ws;
		this.requiredItem = requiredItem;
		this.diesOnExit = true;
	}
	public void setDiesOnExit(boolean b) {
		this.diesOnExit = b;
	}
	public void setDeathMessage(String s) {
		this.deathMessage = s;
	}
	public String deathMessage() {
		return this.deathMessage;
	}
	public Item requiredItem() {
		return this.requiredItem;
	}
	public boolean willDieInDirection(Action dir) {
		return !this.safeDirections.contains(dir) && this.diesOnExit;
	}
	public void setSafeDirection(Action direction) {
		this.safeDirections.add(direction);
	}
	public String toString() {

		if(this.player.hasItem(this.requiredItem)) {
			return super.toString();
		}
		else {
			return this.warningDescription;
		}
	}
	public String description() {
		if(this.player.hasItem(this.requiredItem)) {
			String s = this.roomWasVisited ? this.shortDescription : this.description;
			this.roomWasVisited = true;
			return s;
		}
		else {
			String s = this.roomWasVisited ? this.warningShortDescription : this.warningDescription;
			this.roomWasVisited = true;
			return s;
		}
	}
	protected Item requiredItem;
	protected boolean diesOnExit;
	protected String deathMessage;
	protected LinkedList<Action> safeDirections;
	String warningDescription;
	String warningShortDescription;
/*
	protected String[] warnings;
	protected int warning;
*/
}
