import java.util.ArrayList;
import java.util.HashMap;

public class RoomElevator extends Room {

	public RoomElevator(String description, String shortDescription) {
		super(description, shortDescription);
		this.restrictedFloors = new ArrayList<Integer>();
	}
	public void setFloors(ArrayList<String> descriptions, ArrayList<Room> floors, Action directionOfFloors, int initial) {
		this.descriptions = descriptions;
		this.floors = floors;
		this.directionOfFloors = directionOfFloors;

		setFloor(initial);
	}
	public void setRestrictedFloors(ArrayList<Integer> restrictedFloors) {
		this.restrictedFloors = restrictedFloors;
	}
	public void call(int index) { // call to a specific floor. Will set adjacent room
		if(this.restrictedFloors.contains(index)) {
			System.out.println("You push the button, but nothing happens. Perhaps this floor is off-limits.");
			return;
		}
		else if(index == currentFloor) {
			System.out.println("The elevator is already on this floor -- the doors are open.");
			return;
		}
		for(int i=0; i < 3; i++) {
			System.out.println("...");
			try {
				Thread.sleep(1000);
			} catch(Exception e1) {
				e1.printStackTrace();
			}
		}
		System.out.println("Ding");
		System.out.println("The doors open");
		setFloor(index);
	}
	protected void setFloor(int index) {
		this.currentFloor = index;
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
	protected ArrayList<Integer> restrictedFloors;
	protected Action directionOfFloors; // should be a single direction, that points to every floor. 
					    // This class does not support elevators 
					    // with doors on either side of it. 
	
}
