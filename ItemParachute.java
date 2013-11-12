public class ItemParachute extends Item implements Startable, Wearable {

	public ItemParachute(String s, String sd, String[] a) {
		super(s, sd, a);
	}
	public void start() {
		((RoomSky)this.relatedRoom).breakFall();
	}
}
