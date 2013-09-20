public enum Item {

	ItemShovel("shovel"),
	ItemBrick("brick"),
	ItemFood("food"),
	ItemLadder("ladder"),
	ItemUnknown;

	Item(String description) {
		this.description = description;
		this.installedItem = null;
	}
	Item() {
		this("unknown item");
	}
	public String toString() {
		return description;
	}
	public void setInstalledItem(Item item) {
		this.installedItem = item;
	}
	public Item removeInstalledItem() {
		Item i = this.installedItem;
		this.installedItem = null;
		return i;
	}
	public boolean hasItemInstalled() {
		return this.installedItem != null;
	}
	public String detailDescription() {
		String s = "";
		if(this.installedItem != null) {
			s = ", with a " + this.installedItem + " installed";
		}
		return description + s;
	}
	protected String description;
	protected Item installedItem;
}
