public class RoomLockable extends Room {

	protected boolean locked;
	protected Item key;
	public RoomLockable(String description, String shortDescription, boolean locked, Item key) {
		super(description, shortDescription);
		this.locked = locked;
		this.key = key;
	}
	public RoomLockable(String description, String shortDescription) {
		// unlocked by default
		this(description, shortDescription, false, Item.ItemUnknown);
	}
	public boolean isLocked() {
		return this.locked;
	}
	public boolean unlock(Item key) {
		if(this.key == key) {
			this.locked = false;
			System.out.println("Room unlocked");
			return true;
		}
		else { 
			System.out.println("This key doesn't seem to fit");
			return false;
		}
	}
}
