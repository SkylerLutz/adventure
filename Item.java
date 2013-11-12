import java.util.LinkedList;

public class Item implements Visible {

	public Item(String d, String sd, String[] a) {
		this.description = d;
		this.detailDescription = sd;
		this.aliases = a;
		this.relatedRoom = null;
		this.relatedItem = null;
	}
	private static void initSharedInstances() {
		
		sharedInstances = new LinkedList<Item>();
		sharedInstances.add(new ItemShovel("shovel", "metal shovel",  new String[]{"shovel"}));
		sharedInstances.add(new ItemBrick   ("brick",  "clay brick",    new String[]{"brick"}));
		sharedInstances.add(new ItemFood    ("food",   "food",          new String[]{"food"}));
		sharedInstances.add(new ItemLadder  ("ladder", "wooden ladder", new String[]{"ladder"}));
		sharedInstances.add(new ItemKey ("key", "gold key", new String[]{"key"}));
		sharedInstances.add(new ItemLock  ("lock", "gold lock", new String[]{"lock"}));
		sharedInstances.add(new ItemKeycard ("keycard", "plastic keycard", new String[]{"keycard", "card"}));
		sharedInstances.add(new ItemKeycardReader ("keycard reader", "metal keycard reader", new String[]{"reader", "slot"}));
		sharedInstances.add(new ItemClayPot ("pot",    "clay pot", 	new String[]{"pot", "pottery"}));
		sharedInstances.add(new ItemDiamond ("diamond", "white diamond", new String[]{"diamond", "jewel"}));
		sharedInstances.add(new ItemGold    ("gold", "shiny gold bar", new String[]{"gold", "bar"}));
		sharedInstances.add(new ItemRMS("statue", "wax statuette of Richard M Stallman", new String[]{"statue", "statuette", "rms"}));
		sharedInstances.add(new ItemMicrowave ("microwave", "microwave that stinks of month old popcorn", new String[]{"microwave", "appliance"}));
		sharedInstances.add(new ItemFridge ("fridge", "white refrigerator", new String[]{"fridge", "refrigerator"}));
		sharedInstances.add(new ItemFlashlight ("flashlight", "battery operated flashlight", new String[]{"flashlight", "light"}));
		sharedInstances.add(new ItemGuard ("guard", "sleeping guard", new String[]{"guard", "henchman"}));
		sharedInstances.add(new ItemParachute ("parachute", "packed parachute", new String[]{"chute", "parachute"}));
		sharedInstances.add(new ItemGhillieSuit("camouflage", "Ghillie Suit", new String[]{"suit", "disguise", "ghillie", "camo", "camouflage"}));
		sharedInstances.add(new ItemJunctionBox("box", "junction box", new String[]{"box", "junction", "meter", "electric", "electricity", "power"}));

		sharedInstances.add(new ItemButton ("Button", "Elevator Button", new String[]{"button"}));
		sharedInstances.add(new ItemButton ("Floor 1 Button", "Elevator Floor 1 Button", new String[]{"1"}));
		sharedInstances.add(new ItemButton ("Floor 2 Button", "Elevator Floor 2 Button", new String[]{"2"}));
		sharedInstances.add(new ItemButton ("Floor 3 Button", "Elevator Floor 3 Button", new String[]{"3"}));
		sharedInstances.add(new ItemButton ("Floor 4 Button", "Elevator Floor 4 Button", new String[]{"4"}));

		sharedInstances.add(new ItemUnknown ("unknown", "unknown", new String[]{"unknown"}));
	}
	public static Item getInstance(String s) {
		if(sharedInstances == null) {
			initSharedInstances();
		}
		for(Item i : sharedInstances) {
			for(String a : i.getAliases()) {
				if(s.equals(a)) {
					return i;
				}
			}
		}
		return null;
	}
	public Item relatedItem() {
		return this.relatedItem;
	}	
	public void setRelatedItem(Item i) {
		this.relatedItem = i;
	}
	public Room relatedRoom() {
		return this.relatedRoom;
	}	
	public void setRelatedRoom(Room r) {
		this.relatedRoom = r;
	}
	public String[] getAliases() {
		return this.aliases;
	}
	public String toString() {
		return this.description;
	}
	public String detailDescription() {
		return this.detailDescription;
	}
	public void setDescription(String s) {
		this.description = s;
	}
	public void setDetailDescription(String s) {
		this.detailDescription = s;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean b) {
		this.visible = b;
	}
	protected boolean visible = true;
	protected String description;
	protected String detailDescription;
	protected String[] aliases;
	protected static LinkedList<Item> sharedInstances;
	protected Room relatedRoom; // items can open rooms, call elevators, etc (e.g., an ItemButton instance)
	protected Item relatedItem; // items can also affect other items, like setting other items breakable (like a junction box);
}
/*
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
	ItemKeyCard("keycard","keycard",       new String[]{"keycard", "smartcard", "card"}),
	ItemLock   ("lock",  "silver lock",   new String[]{"lock"}),
	ItemCardReader("reader",  "keycard reader",   new String[]{"reader", "slider"}),
	ItemGrafitti("grafitti", "contemporary grafitti. It says: The cake is a lie", new String[]{"grafitti"}),
	ItemLightSwitch("lightswitch", "plastic lightswitch", new String[]{"lightswitch"}),
	ItemFlashLight("flashlight", "battery operated flashlight", new String[]{"flashlight"}),
	ItemParachute("parachute", "red and blue parachute", new String[]{"parachute", "chute"}),
	ItemGhillieSuit("camouflage", "Ghillie Suit", new String[]{"suit", "disguise", "ghillie", "camo", "camouflage"}),
	ItemArmor("armor", "body armor", new String[]{"armor"}),
	ItemGuard1("guard", "sleeping guard", new String[]{"guard", "henchman"}),
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
			case "pot":
				this.defaults = genericDefaults();
				this.defaults.put("permitsInstalledItems", true);
				this.defaults.put("installedItemsAreVisible", true);
				this.defaults.put("canBeDestroyed", true);
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
			case "lock":  
				this.defaults = genericDefaults();
				this.defaults.put("permitsInstalledItems", true);
				break;
			case "parachute":  
				this.defaults = genericDefaults();
				this.defaults.put("canBeEnabled", true);
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
			case "camouflage":
				this.defaults = genericDefaults();
				defaults.put("isDisguise", true);
				break;
			case "guard":
				this.defaults = genericDefaults();
				defaults.put("isKillable", true);
				defaults.put("canBePickedUp", false);
				defaults.put("permitsInstalledItems", true);
				break;
			case "reader":
				this.defaults = genericDefaults();
				defaults.put("permitsInstalledItems", true);
				defaults.put("canBePickedUp", false);
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
		defaults.put("isDisguise", false);
		defaults.put("isKillable", false);
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
			switch(this) {
				case ItemCardReader: {
					if(item == Item.ItemKeyCard) {
						System.out.println("Authenticating.");
						for(int i=0; i < 3; i++) {
							System.out.println("...");
							try {
								Thread.sleep(1000);
							} catch(Exception e1) {
								e1.printStackTrace();
							}
						}
						System.out.println("Authentication Complete.");
						this.installedItem = item;
					}	
					break;
				}
				case ItemGuard1: {
					if(item == Item.ItemKeyCard) {
						this.installedItem = item;
					}		
					else {
						System.out.println("You cannot put an item into this " + this);
					}
				}	
				default:
					this.installedItem = item;
					break;
			}
			return true;
		}
		System.out.println("You cannot put an item into this " + this);
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
	public void eat() {
		switch(this) {
			case ItemGuard1:
				System.out.println("You kneel down on the ground, and move your open mouth toward the guard's dead body, but then remember you are not a cannibal.");
				break;
			default:
				System.out.println("yummy");
				break;
		}
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
				case ItemParachute:

					System.out.println("You deploy your parachute, and your feelings of fear immediately turn into bliss. You admire the view from here, as you gracefully decend toward the soft brush field below.");
					((RoomSky)this.relatedRoom).breakFall();
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
	public void kill() {
		switch(this) {
			case ItemGuard1:
				System.out.println("The guard is now dead.");
				this.detailDescription = "dead guard";
				this.defaults.put("isEdible", true);
				if(this.installedItem != null) {
					this.relatedRoom.putItem(this.installedItem);
					System.out.println("It looks like the guard dropped something");
				}
				break;
			default:
				System.out.println("This item cannot be killed");
				break;
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
	public void setCanBePickedUp(boolean permission) {
		this.defaults.put("canBePickedUp", permission);
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
	public void setSky(RoomSky sky) {
		this.sky = sky;
	}
	public RoomSky getSky() {
		return this.sky;
	}
	public RoomObscured getPassage() {
		return this.passage;
	}
	public void setRelatedRoom(Room room) {
		this.relatedRoom = room;
	}
	public Room getRelatedRoom() {
		return this.relatedRoom;
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
	public void setDescription(String d) {
		this.description = d;
	}
	public void setDetailDescription(String dd) {
		this.detailDescription = dd;
	}
	public String detailDescription() {
		String s = "";
		if(this.installedItem != null && this.defaults.get("installedItemsAreVisible")) {
			s = ", with a " + this.installedItem.detailDescription() + " inside it";
		}
		return this.detailDescription + s;
	}
	private Item installedItem;
	private Room elevator; // to be associated with a button
	private RoomObscured passage; // to be associated with a passage
	private RoomSky sky;
	private Room relatedRoom;

	// item attributes
	private String destroyMessage;
	private boolean wasPushed;
	private HashMap<String, Boolean> defaults = null;
}
*/
