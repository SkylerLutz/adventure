import java.util.LinkedList;

public class RoomRequiredItem extends Room {

	public RoomRequiredItem(String d, String dd, Item requiredItem) {
		this(d, dd, null, null, requiredItem);
	}
	public RoomRequiredItem(String d, String dd, String w, String ws, Item requiredItem) {
		super(d, dd);
		this.warningDescription = w;
		this.warningShortDescription = ws;
		this.requiredItem = requiredItem;
		this.safeDirections = new LinkedList<Action>();
		this.roomWasVisitedInDanger = false;
		this.diesOnItemDiscard = false;
		this.diesOnEntry = false;
		this.deathMessage = null;
	}
	public void playerDidDropRequiredItem() {
		if(this.diesOnItemDiscard) {
			System.out.println(this.deathMessage);
			this.player.die();
		}
		else {
			this.player.look();
		}
	}
	public void setPlayerDiesOnItemDiscard(boolean b) {
		this.diesOnItemDiscard = b;
	}
	public void setPlayerDiesOnEntry(boolean b) {
		this.diesOnEntry = b;
	}
	public boolean diesOnEntry() {
		return this.diesOnEntry;
	}
	public boolean shouldDieForAction(Action a) {
		return !this.safeDirections.contains(a) && !this.player.hasItem(this.requiredItem) && !(this.player.disguise == this.requiredItem);
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
	public void setSafeDirection(Action direction) {
		this.safeDirections.add(direction);
	}
	public String toString() {

		if(this.player.hasItem(this.requiredItem) || this.player.disguise == this.requiredItem) {
			return super.toString();
		}
		else {
			return this.warningDescription;
		}
	}
	public String description() {
		if(this.player.hasItem(this.requiredItem) || this.player.disguise == this.requiredItem) {
			String s = this.roomWasVisited ? this.shortDescription : this.description + visibleItems();
			this.roomWasVisited = true;
			return s;
		}
		else {
			String s = this.roomWasVisitedInDanger ? this.warningShortDescription : this.warningDescription + visibleItems();
			this.roomWasVisitedInDanger = true;
			return s;
		}
	}
	protected Item requiredItem;
	protected boolean diesOnItemDiscard;
	protected boolean diesOnEntry;
	protected String deathMessage;
	protected LinkedList<Action> safeDirections;
	String warningDescription;
	String warningShortDescription;
	protected boolean roomWasVisitedInDanger;
}
