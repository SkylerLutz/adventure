import java.util.LinkedList;

public class ItemWatchMenu {

	public ItemWatchMenu(String title) {

		this.title = title;
		this.text = null;
		this.menus = new LinkedList<ItemWatchMenu>();

		String prev = "Go Back.";
		if(!this.title.equals(prev)) {
			ItemWatchMenu exit = new ItemWatchMenu("Go Back.");
			this.menus.add(exit);
		}
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return this.title;
	}
	public void setText(String text) {
		this.text = text;
	}
	public void add(ItemWatchMenu menu) {
		this.menus.add(this.menus.size()-1, menu);
	}
	public ItemWatchMenu get(int i) {
		return this.menus.get(i);
	}
	public int count() {
		return this.menus.size();
	}
	public String toString() {
		String top = this.title + "\n\n" + this.text + "\n\n";
		String list = "";
		for(int i = 0; i < this.menus.size(); i++) {
			ItemWatchMenu menu = this.menus.get(i);
			list+=(i+1) + "  " + menu.getTitle() + "\n";
		}
		return top + list;
	}
	protected String title;
	protected String text;
	protected LinkedList<ItemWatchMenu> menus;
}
