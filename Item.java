public enum Item {

	ItemShovel  ("shovel", "metal shovel",  new String[]{"shovel"}, false, true),
	ItemBrick   ("brick",  "clay brick",    new String[]{"brick"}, false, true),
	ItemFood    ("food",   "food",          new String[]{"food"}, false, true),
	ItemLadder  ("ladder", "wooden ladder", new String[]{"ladder"}, false, true),
	ItemClayPot ("pot",    "clay pot", 	new String[]{"pot", "pottery"}, true, true),
	ItemDiamond ("diamond", "white diamond", new String[]{"diamond", "jewel"}, false, true),
	ItemKey1    ("key1",    "silver key",    new String[]{"key1"}, false, true),
	ItemKey2    ("key2",    "brass key",     new String[]{"key2"}, false, true),
	ItemLock1   ("lock1",  "silver lock",   new String[]{"lock"}, true, true),
	ItemLock2   ("lock2",  "brass lock",    new String[]{"lock"}, true, true),
	ItemGrafitti("grafitti", "contemporary grafitti. It says: The cake is a lie", new String[]{"grafitti"}, false, false),
	ItemLightSwitch("lightswitch", "plastic lightswitch", new String[]{"lightswitch"}, false, false),
	ItemFlashLight("flashlight", "battery operated flashlight", new String[]{"flashlight"}, false, true),
	ItemUnknown;

	Item(String description, String detailDescription, String[] aliases, boolean permitsInstalledItems, boolean canBePickedUp) {
		this.description = description;
		this.detailDescription = detailDescription;
		this.aliases = aliases;
		this.installedItem = null;
		this.permitsInstalledItems = permitsInstalledItems;
		this.installedItemsAreVisible = true;
		this.canBePickedUp = canBePickedUp;

		this.destroyMessage = null;
	}
	Item() {
		this("unknown item", "unknown item", new String[]{"unknown"}, false, true);
	}
	public String toString() {
		return description;
	}
	public String toDetailString() {
		return this.detailDescription;
	}
	public boolean setInstalledItem(Item item) {
		if(this.permitsInstalledItems) {
			this.installedItem = item;
			return true;
		}
		else {
			System.out.println("You cannot put an item into this " + this);
		}
		return false;
	}
	public Item removeInstalledItem() {
		Item i = this.installedItem;
		this.installedItem = null;
		return i;
	}
	public void setInstalledItemVisible(boolean visible) {
		this.installedItemsAreVisible = visible;
	}
	public Item destroy() {
		// Todo if object is breakable
		Item i = removeInstalledItem();
		showDestroyMessage();

		return i;
		// else {
		//	System.out.println("You cannot break this item.");
		// }
	}
	public boolean hasItemInstalled() {
		return this.installedItem != null;
	}
	public boolean canInstallItems() {
		return this.permitsInstalledItems;
	}
	public void setCanInstallItems(boolean permission) {
		this.permitsInstalledItems = permission;
	}
	public boolean canBePickedUp() {
		return this.canBePickedUp;
	}
	public void setDestroyMessage(String message) {
		this.destroyMessage = message;
	}
	public void showDestroyMessage() {
		if(destroyMessage != null) {
			System.out.println(destroyMessage);
		}
	}
	public String[] getAliases() {
		return this.aliases;
	}
	public String detailDescription() {
		String s = "";
		if(this.installedItem != null && this.installedItemsAreVisible) {
			s = ", with a " + this.installedItem + " installed";
		}
		return description + s;
	}
	private String description;
	private String detailDescription;
	private String[] aliases;
	private Item installedItem;

	// item attributes
	private boolean permitsInstalledItems;
	private boolean installedItemsAreVisible;
	private boolean canBePickedUp;

	private boolean canBeDestroyed;
	private String destroyMessage;
}
