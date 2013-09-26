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
		return this.isDark ? this.darkDescription : super.toString();
		/*
 *			if(this.isDark) {
 *				return player has flashlight ? super.toString : this.darkDescription;
 *			}
 *			else { 
 *				return this.darkDescription;
 *			}
 * 		*/
	}
	public String description() {

		return this.isDark ? this.darkShortDescription : super.description();
		/*
 *			if(this.isDark) {
 *				return player has flashlight ? super.description: this.darkShortDescription;
 *			}
 *			else { 
 *				return this.darkShortDescription;
 *			}
 * 		*/
	}
	protected String darkDescription;
	protected String darkShortDescription;
	protected boolean isDark;
}
