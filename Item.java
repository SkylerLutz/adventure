import java.util.HashMap;

public enum Item {

	ItemShovel  ("shovel", "metal shovel",  new String[]{"shovel"}),
	ItemBrick   ("brick",  "clay brick",    new String[]{"brick"}),
	ItemFood    ("food",   "food",          new String[]{"food"}),
	ItemLadder  ("ladder", "wooden ladder", new String[]{"ladder"}),
	ItemClayPot ("pot",    "clay pot", 	new String[]{"pot", "pottery"}),
	ItemDiamond ("diamond", "white diamond", new String[]{"diamond", "jewel"}),
	ItemKey    ("key",    "silver key",    new String[]{"key"}),
	ItemLock   ("lock",  "silver lock",   new String[]{"lock"}),
	ItemGrafitti("grafitti", "contemporary grafitti. It says: The cake is a lie", new String[]{"grafitti"}),
	ItemLightSwitch("lightswitch", "plastic lightswitch", new String[]{"lightswitch"}),
	ItemFlashLight("flashlight", "battery operated flashlight", new String[]{"flashlight"}),
	ItemUnknown;

	Item(String description, String detailDescription, String[] aliases) {
		this.description = description;
		this.detailDescription = detailDescription;
		this.aliases = aliases;
		this.installedItem = null;
		this.destroyMessage = null;
		setDefaults();
	}
	Item() {
		this("unknown item", "unknown item", new String[]{"unknown"});
	}
	private void setDefaults() {
		switch(this.description) {
			case "shovel":
				this.defaults = genericDefaults();
				break;
			case "brick":
				this.defaults = genericDefaults();
				break;
			case "food":
				this.defaults = genericDefaults();
				break;
			case "ladder":
				this.defaults = genericDefaults();
				break;
			case "pot":
				this.defaults = genericDefaults();
				this.defaults.put("permitsInstalledItems", true);
				this.defaults.put("installedItemsAreVisible", true);
				this.defaults.put("canBeDestroyed", true);
				break;
			case "diamond":
				this.defaults = genericDefaults();
				break;
			case "key":
				this.defaults = genericDefaults();
				break;
			case "lock":  
				this.defaults = genericDefaults();
				this.defaults.put("permitsInstalledItems", true);
				break;
			case "grafitti":
				this.defaults = genericDefaults();
				this.defaults.put("permitsInstalledItems", false);
				this.defaults.put("canBePickedUp", false);
				break;
			case "lightswitch":
				this.defaults = genericDefaults();
				this.defaults.put("permitsInstalledItems", false);
				this.defaults.put("canBePickedUp", false);
				break;
			case "flashlight":
				this.defaults = genericDefaults();
				break;
			case "unknown":
				this.defaults = genericDefaults();
				break;
			default:
				this.defaults = genericDefaults();
				break;
		}
	}
	private static HashMap<String, Boolean> genericDefaults() {
		HashMap<String, Boolean> defaults = new HashMap<String, Boolean>();
		defaults.put("permitsInstalledItems", false);
		defaults.put("canBePickedUp", true);
		defaults.put("canBeDestroyed", false);
		defaults.put("installedItemsAreVisible", false);
		defaults.put("canBePickedUp", true);
		return defaults;
	}
	public HashMap<String, Boolean> defaults() {
		return this.defaults;
	}
	public void updateDefault(String key, Boolean value) {
		this.defaults.put(key, value);
	}
	public String toString() {
		return description;
	}
	public String toDetailString() {
		return this.detailDescription;
	}
	public boolean setInstalledItem(Item item) {
		if(this.defaults.get("permitsInstalledItems")) {
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
		this.defaults.put("installedItemsAreVisible", visible);
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
		return this.defaults.get("permitsInstalledItems");
	}
	public void setCanInstallItems(boolean permission) {
		this.defaults.put("permitsInstalledItems", permission);
	}
	public boolean canBePickedUp() {
		return this.defaults.get("canBePickedUp");
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
		if(this.installedItem != null && this.defaults.get("installedItemsAreVisible")) {
			s = ", with a " + this.installedItem + " installed";
		}
		return description + s;
	}
	private String description;
	private String detailDescription;
	private String[] aliases;
	private Item installedItem;

	// item attributes
	private String destroyMessage;
	private HashMap<String, Boolean> defaults;
}
