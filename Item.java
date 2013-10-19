import java.util.HashMap;

public enum Item {

	ItemShovel  ("shovel", "metal shovel",  new String[]{"shovel"}),
	ItemBrick   ("brick",  "clay brick",    new String[]{"brick"}),
	ItemFood    ("food",   "food",          new String[]{"food"}),
	ItemLadder  ("ladder", "wooden ladder", new String[]{"ladder"}),
	ItemClayPot ("pot",    "clay pot", 	new String[]{"pot", "pottery"}),
	ItemDiamond ("diamond", "white diamond", new String[]{"diamond", "jewel"}),
	ItemGold    ("gold", "shiny gold bar", new String[]{"gold", "bar"}),
	ItemStatue  ("statue", "wax statuette of Richard M Stallman", new String[]{"statue", "statuette", "rms"}),
	ItemMicrowave ("microwave", "microwave that stinks of month old popcorn", new String[]{"microwave", "appliance"}),
	ItemFridge ("fridge", "white refrigerator", new String[]{"fridge", "refrigerator"}),

	ItemElevatorButton("elevator button", "elevator button", new String[]{"elevator"}),
	ItemElevatorFloor1Button("Elevator Button", "Floor 1 Button", new String[]{"1"}),
	ItemElevatorFloor2Button("Elevator Button", "Floor 2 Button", new String[]{"2"}),
	ItemElevatorFloor3Button("Elevator Button", "Floor 3 Button", new String[]{"3"}),
	ItemElevatorFloor4Button("Elevator Button", "Floor 4 Button", new String[]{"4"}),

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
		this.wasPushed = false;
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
			case "statue":
				this.defaults = genericDefaults();
				break;
			case "microwave":
				this.defaults = genericDefaults();
				this.defaults.put("canBePickedUp", false);
				this.defaults.put("permitsInstalledItems", true);
				this.defaults.put("installedItemsAreVisible", true);
				defaults.put("canBeEnabled", true);
				break;
			case "fridge": 
				this.defaults = genericDefaults();
				this.defaults.put("canBePushed", true);
				break;
			case "Elevator Button": 
				this.defaults = genericDefaults();
				this.defaults.put("isVisible", false);
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
				defaults.put("canBeEnabled", true);
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
		defaults.put("isVisible", true);
		defaults.put("permitsInstalledItems", false);
		defaults.put("canBePickedUp", true);
		defaults.put("canBeDestroyed", false);
		defaults.put("installedItemsAreVisible", false);
		defaults.put("canBeEnabled", false);
		defaults.put("canBePushed", false);
		defaults.put("isEdible", false);
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
	public void push() {
		if(this.defaults.get("canBePushed")) {
			this.wasPushed = true;
		}
		switch(this) {
			case ItemFridge: 
				System.out.println("You revealed a secret passage to the east!");
				break;
			default:
				System.out.println("You cannot push this item");
		}
	}
	public void start() {
		if(this.defaults.get("canBeEnabled")) {
			switch(this) {
				case ItemLightSwitch:
					System.out.println("The room is lit");
					break;
				case ItemMicrowave:
					for(int i=0; i < 3; i++) {
						System.out.println("...");
						try {
							Thread.sleep(1000);
						} catch(Exception e1) {
							e1.printStackTrace();
						}
					}
					System.out.println("Beep beep beep");
					if(this.installedItem == Item.ItemStatue) {
						System.out.println("You melted Richard Stallman!");
						this.installedItem = Item.ItemDiamond;
					}
						
					break;
				default:
					System.out.println("I don't know how to start that item");
					break;	
			}
		}
		else {
			System.out.println("I don't understand what that means");
		}
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
	public void setElevator(RoomElevator elevator) {
		this.elevator = elevator;
	}
	public Room getElevator() {
		return this.elevator;
	}
	public void setPassage(RoomObscured room) {
		this.passage = room;
	}
	public RoomObscured getPassage() {
		return this.passage;
	}
	public boolean isVisible() {
		return this.defaults.get("isVisible");
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
			s = ", with a " + this.installedItem.detailDescription() + " inside it";
		}
		return this.detailDescription + s;
	}
	private String description;
	private String detailDescription;
	private String[] aliases;
	private Item installedItem;
	private Room elevator; // to be associated with a button
	private RoomObscured passage; // to be associated with a passage

	// item attributes
	private String destroyMessage;
	private boolean wasPushed;
	private HashMap<String, Boolean> defaults;
}
