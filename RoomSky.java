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
						System.out.println("\n" + (dur-i)*1000 + " thousand feet.");
						System.out.print(">>> ");
						Thread.sleep(2500 + (RoomSky.shouldDie() ? 0 : 1000));
					}
					catch(Exception e1) {
						
					}
				}
				if(RoomSky.shouldDie()) {
					System.out.println("Splat.");
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
