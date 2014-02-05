public class RoomSky extends Room {

	public RoomSky(String description, String shortDescription, int duration) {
		super(description, shortDescription);
		this.duration = duration;
		this.landMessage = null;
		this.fallBrokenMessage = null;
	}
	public void freefall() {

		final int dur = this.duration;
		final Player p = this.player;
		final Room r = this.getRoomForDirection(Action.ActionGoDown);
		final String landMessage = this.landMessage;
		Thread thread = new Thread(new Runnable() {
			public void run() {
				for(int i=0; i < dur; i++) {

					System.out.println("\n");
					try {
						System.out.println((dur-i)*1000 + " thousand feet.");
						System.out.print("> ");
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
					if(landMessage != null) {
						System.out.println(landMessage);
					}
					// transition to field
					p.move(Action.ActionGoDown);
					Item i = Item.getInstance("parachute");
					//p.dropItem(i);
					i.setDetailDescription("used parachute");
					//i.setCanBePickedUp(false);
					System.out.print("> ");
					
				}		
			}
		});
		thread.start();
	}
	public void breakFall() {
		shouldDie = false;
		System.out.println(this.fallBrokenMessage);
	}
	public static boolean shouldDie() {
		return shouldDie;
	}
	public void setLandMessage(String s) {
		this.landMessage = s;
	}
	public void setFallBrokenMessage(String s) {
		this.fallBrokenMessage = s;
	}
	protected int duration; // in seconds
	protected static boolean shouldDie = true;
	protected String landMessage;
	protected String fallBrokenMessage;
}
