public class ItemFood extends Item implements Edible, Holdable {

	public ItemFood(String s, String sd, String[] a) {
		super(s, sd, a);
	}
	public void eat() {
		System.out.println("Yummy");
	}		
}
