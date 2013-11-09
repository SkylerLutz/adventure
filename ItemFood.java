public class ItemFood extends Item implements Edible, Holdable, Visible{

	public ItemFood(String s, String sd, String[] a) {
		super(s, sd, a);
	}
	public void eat() {
		System.out.println("Yummy");
	}		
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean b) {
		this.visible = b;
	}
	protected boolean visible = true;
}
