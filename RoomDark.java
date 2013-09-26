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
	public boolean canMoveToRoomInDirection(Action a) {

		if(super.canMoveToRoomInDirection(a)) {
			return !this.isDark();
		}
		return false;
	}
	public String toString() {
		return this.isDark ? this.darkDescription : super.toString();
	}
	public String description() {

		return this.isDark ? this.darkShortDescription : super.description();
	}
	protected String darkDescription;
	protected String darkShortDescription;
	protected boolean isDark;
}
