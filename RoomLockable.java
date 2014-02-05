public class RoomLockable extends Room {

	public RoomLockable(String description, String shortDescription, boolean locked, Item key) {
		super(description, shortDescription);
		this.locked = locked;
		this.key = key;
		this.causesDeath = false;
		this.deathMessage = "";
		this.unlockMessage = "Room unlocked.";
	}
	public RoomLockable(String description, String shortDescription) {
		// unlocked by default
		this(description, shortDescription, false, Item.getInstance("unknown"));
	}
	public boolean isLocked() {
		return this.locked;
	}
	public void setCausesDeath(boolean causesDeath, String message) {
		this.causesDeath = causesDeath;
		this.deathMessage = message;
	}
	public boolean causesDeath() {
		return this.causesDeath;
	}
	public String deathMessage() {
		return this.deathMessage;
	}
	public void setUnlockMessage(String s){
		this.unlockMessage = s;
	}
	public boolean unlock(Item key) {
		if(this.key.compareTo(key) == 0) {
			this.locked = false;
			System.out.println(this.unlockMessage);
			return true;
		}
		else { 
			if(!causesDeath()) {
				System.out.println("This key doesn't seem to fit");
			}
			return false;
		}
	}
	protected boolean locked;
	protected Item key;
	protected boolean causesDeath;
	protected String deathMessage;
	protected String unlockMessage;
}
