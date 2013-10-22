public class RoomSky extends Room {

	public RoomSky(String description, String shortDescription, int duration) {
		super(description, shortDescription);
		this.duration = duration;
	}
	public void freefall() {

		for(int i=0; i < this.duration; i++) {

			try {
				Thread.sleep(400);
				System.out.println((this.duration-i)*1000 + " thousand feet.");

				for(int j=0; j < 3; j++) {
					System.out.println("...");
					Thread.sleep(200);
				}
			}
			catch(Exception e1) {
				
			}
		}
		System.out.println("You hit the ground");
		this.player.die();
	}
	public void breakFall() {
		// transition to field
	}
	protected int duration; // in seconds
}
