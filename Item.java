public enum Item {

	ItemShovel("shovel"),
	ItemBrick("brick"),
	ItemFood("food"),
	ItemLadder("ladder"),
	ItemUnknown;

	Item(String description) {
		this.description = description;
	}
	Item() {
		this("unknown item");
	}
	public String toString() {
		return description;
	}
	protected String description;
}
