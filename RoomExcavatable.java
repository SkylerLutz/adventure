import java.util.HashMap;
import java.util.LinkedList;

public class RoomExcavatable extends Room {

	public RoomExcavatable(String description, String shortDescription, String digMessage) {
		super(description, shortDescription);
		this.digMessage = digMessage;
		this.wasDugUp = false;
		this.revealableItems = new LinkedList<Item>();
	}
	public void setRevealableItems(LinkedList<Item> items) {
		if(items != null) {
			this.revealableItems = items;
		}
	}
	public void dig() {

		if(this.player.hasItem(Item.getInstance("shovel"))) {
			System.out.println(digMessage);
			this.wasDugUp = true;
			this.items.addAll(this.revealableItems);
		}
		else {
			System.out.println("You do not have an item you can use to dig.");
		}
	}
	protected String digMessage;
	protected boolean wasDugUp;
	protected LinkedList<Item> revealableItems;
}
