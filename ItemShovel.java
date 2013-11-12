public class ItemShovel extends Item implements Chuckable, Destroyable, Edible, Holdable, Inspectable {
	
	public ItemShovel(String d, String sd, String[] a) {
		super(d, sd, a);
	}
	public void chuck() {

	}
	public void setDestroyMessage(String s) {
		this.destroyMessage = s;
	}	
	public void destroy() {
		System.out.println("Shattered into a million pieces");
	}
	public void setDisappears(boolean b) {
		this.disappears = b;
	}
	public boolean disappears() {
		return this.disappears;
	}
	public void take() {
		System.out.println("Taken");
	}
	public void eat() {
		System.out.println("death");
	}
	public void inspect() {
		System.out.println("There looks like there is something on the bottom.");
	}
	public void drop() {
		System.out.println("plop");
	}
	protected String destroyMessage = null;
	protected boolean disappears;
}
