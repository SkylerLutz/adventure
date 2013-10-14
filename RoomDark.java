import java.util.HashMap;
import java.util.LinkedList;

// represents a dark room
public class RoomDark extends Room {

	public RoomDark(String description, String shortDescription, String darkDescription, String darkShortDescription) {

		this(description, shortDescription, darkDescription, darkShortDescription, null, null, true);
	}
	public RoomDark(String description, String shortDescription, String darkDescription, String darkShortDescription, HashMap<Action, Room> adjacentRooms, LinkedList<Item> items, boolean isDark){
		super(description, shortDescription, adjacentRooms, items);

		this.isDark = isDark;
		this.darkDescription = darkDescription;
		this.darkShortDescription = darkShortDescription;
	}
	public boolean isDark() {
		return this.isDark;
	}
	public void setDark(boolean isDark) {
		this.isDark = isDark;
	}
		
	@Override
	/* I would also like to test if the player has a flashlight
	public boolean canMoveToRoomInDirection(Action a) {

		if(super.canMoveToRoomInDirection(a)) {
			return !this.isDark();
		}
		return false;
	}
	*/

	public String toString() {

		if(this.isDark) {
			if(this.player.hasItem(Item.ItemFlashLight)) {
				return super.toString();
			}
			else {
				return this.darkDescription;
			}
		}
		else {
			return super.toString();
		}
	}
	public String description() {
		if(this.isDark) {
			if(this.player.hasItem(Item.ItemFlashLight)) {
				String s = this.roomWasVisited ? this.description : this.shortDescription;
				this.roomWasVisited = true;
				return s;
			}
			else {
				String s = this.roomWasVisited ? this.darkDescription : this.darkShortDescription;
				this.roomWasVisited = true;
				return s;
			}
		}
		else {
			String s = this.roomWasVisited ? this.description : this.shortDescription;
			this.roomWasVisited = true;
			return s;
		}
	}
	protected String darkDescription;
	protected String darkShortDescription;
	protected boolean isDark;
}
