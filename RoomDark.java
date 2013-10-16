import java.util.HashMap;
import java.util.LinkedList;

// represents a dark room
public class RoomDark extends Room {

	public RoomDark(String description, String shortDescription, String darkDescription, String darkShortDescription) {

		this(description, shortDescription, darkDescription, darkShortDescription, true);
	}
	public RoomDark(String description, String shortDescription, String darkDescription, String darkShortDescription, boolean isDark){
		super(description, shortDescription);

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
				String s = this.roomWasVisited ? this.shortDescription : this.description;
				this.roomWasVisited = true;
				return s;
			}
			else {
				String s = this.roomWasVisited ? this.darkShortDescription : this.darkDescription;
				this.roomWasVisited = true;
				return s;
			}
		}
		else {
			String s = this.roomWasVisited ? this.shortDescription : this.description;
			this.roomWasVisited = true;
			return s;
		}
	}
	protected String darkDescription;
	protected String darkShortDescription;
	protected boolean isDark;
}
