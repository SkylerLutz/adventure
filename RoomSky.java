public class RoomSky extends Room {

	public RoomSky(String description, String shortDescription, int duration) {
		super(description, shortDescription);
	}
	public void freefall() {

		for(int i=0; i < this.duration; i++) {

			Thread.sleep(400);
			System.out.println((this.duration-1)*1000 + " thousand feet.");

			for(int j=0; j < 3; j++) {
				System.out.println("...");
				Thread.sleep(200);
			}
		}
	}
	public void breakFall() {
		// transition to field
	}
	protected int duration; // in seconds
}
