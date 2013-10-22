public class RoomSky extends Room {

	public RoomSky(String description, String shortDescription, int duration) {
		super(description, shortDescription);
		this.duration = duration;
	}
	public void freefall() {

		final int dur = this.duration;
		final Player p = this.player;
		Thread thread = new Thread(new Runnable() {
			public void run() {
				for(int i=0; i < dur; i++) {

					try {
						Thread.sleep(400);
						System.out.println((dur-i)*1000 + " thousand feet.");

						for(int j=0; j < 3; j++) {
							System.out.println("...");
							Thread.sleep(200);
						}
					}
					catch(Exception e1) {
						
					}
				}
				System.out.println("You hit the ground");
				p.die();
			}
		});
		thread.start();
	}
	public void breakFall() {
		// transition to field
	}
	protected int duration; // in seconds
}
