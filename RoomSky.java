public class RoomSky extends Room {

	public RoomSky(String description, String shortDescription, int duration) {
		super(description, shortDescription);
		this.duration = duration;
	}
	public void freefall() {

		final int dur = this.duration;
		final Player p = this.player;
		final Room r = this.getRoomForDirection(Action.ActionGoDown);
		Thread thread = new Thread(new Runnable() {
			public void run() {
				for(int i=0; i < dur; i++) {

					try {
						Thread.sleep(2000);
						System.out.println("\n" + (dur-i)*1000 + " thousand feet.");
						System.out.print(">>> ");

						for(int j=0; j < 3; j++) {
							//System.out.println("...");
							Thread.sleep(200);
						}
					}
					catch(Exception e1) {
						
					}
				}
				System.out.println("You hit the ground");
				if(RoomSky.shouldDie()) {

					p.die();
				}
				else {
					// move to field
					System.out.println("You land softly in the grass");
					// transition to field
					p.move(r);
					Item i = Item.ItemParachute;
					p.dropItem(i);
					i.setDetailDescription("used parachute");
					i.setCanBePickedUp(false);
					System.out.print(">>> ");
					
				}		
			}
		});
		thread.start();
	}
	public void breakFall() {
		shouldDie = false;
	}
	public static boolean shouldDie() {
		return shouldDie;
	}
	protected int duration; // in seconds
	protected static boolean shouldDie = true;
}
