public class ItemRMS extends Item implements Installable, Holdable, Meltable {

	public ItemRMS(String s, String sd, String[] a) {
		super(s, sd, a);
		this.meltItem = null;
	}
	public void setMeltItem(Item item) {
		this.meltItem = item;
	}	
	public Item meltItem() {
		return this.meltItem;
	}
	protected Item meltItem;
}
