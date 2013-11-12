public class ItemMicrowave extends Item implements Startable {

	public ItemMicrowave(String s, String sd, String[] a) {
		super(s, sd, a);
	}

	public void start() {
		System.out.println("Beep beep beep");
	}
}
