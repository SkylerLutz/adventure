import java.util.ArrayList;
import java.util.HashMap;

public class RoomElevator extends Room {

	public RoomElevator(String description, String shortDescription) {
		super(description, shortDescription);
	}
	public void setFloors(ArrayList<String> descriptions, ArrayList<Room> floors, Action directionOfFloors) {
		this.descriptions = descriptions;
		this.floors = floors;
		this.directionOfFloors = directionOfFloors;
	}
	public void call(int index) { // call to a specific floor. Will set adjacent room
		Room adjacentFloor = this.floors.get(index);
		setAdjacentRoom(this.directionOfFloors, adjacentFloor);
		this.description = this.descriptions.get(index);
	}
	public void call(Room floor) { // call to the floor the player is on
		int index = this.floors.indexOf(floor);
		call(index);
	}

	protected int currentFloor;
	protected ArrayList<String> descriptions;
	protected ArrayList<Room> floors;
	protected Action directionOfFloors; // should be a single direction, that points to every floor. 
					    // This class does not support elevators 
					    // with doors on either side of it. 
	
}
