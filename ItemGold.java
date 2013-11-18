public class ItemGold extends Item implements Holdable, Valuable {

	public ItemGold(String s, String sd, String[] a) {
		super(s, sd, a);
		this.value = 0;
	}
	
	public int value() {
		return this.value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	protected int value;
}
