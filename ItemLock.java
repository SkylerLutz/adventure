public class ItemLock extends Item implements Hostable {

	public ItemLock(String s, String sd, String[] a) {
		super(s, sd, a);
	}
	public void install(Item i) {
		this.installedItem = i;

		if(this.relatedRoom != null && this.relatedRoom instanceof RoomLockable) {
			((RoomLockable)this.relatedRoom).unlock(this.installedItem);
		}
	}
	public boolean uninstall(Item i) {
		if(this.installedItem == null) {
			return false;
		}
		else if(this.installedItem == i) {
			this.installedItem = null;
			return true;
		}
		else {
			return false;
		}
	}
	public Item installedItem() {
		return this.installedItem;
	}
	protected Item installedItem;
	protected boolean disappears;
}
