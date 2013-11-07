public class ItemShovel extends Item implements Acquirable, Destroyable, Droppable, Edible, Inspectable, Visible {
	
	public ItemShovel(String d, String sd, String[] a) {
		super(d, sd, a);
	}

	public void destroy() {
		System.out.println("Shattered into a million pieces");
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
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean b) {
		this.visible = b;
	}
	protected boolean visible = true;
}
