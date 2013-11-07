// couldn't think of a better name...
//
interface Hostable {
	
	// marker
	public void install(Item item);
	public boolean uninstall(Item item);
	public Item installedItem();
}
