interface Acquirable {
	// marker
}

interface Burnable {
	public void burn();
}

// I would call it throwable, but we all know I can't do that...
interface Chuckable {
	public void chuck();
}

interface Destroyable {
	public void destroy();
	public void setDestroyMessage(String s);
	public void setDisappears(boolean b);
	public boolean disappears();
}

interface Droppable {
	public void drop();
}

interface Edible {
	public void eat();
}
interface Explodable {
	public void explode();
}
interface Holdable {
	//marker
}

// couldn't think of a better name...
interface Hostable {
	public void install(Item item);
	public boolean uninstall(Item item);
	public Item installedItem();
}

interface Inspectable {
	public void inspect();
	public void setInspectMessage(String s);
}

interface Installable {
	// marker
}

interface Killable {
	public void kill();
}

interface Luminous {

}

interface Meltable {
	public void setMeltItem(Item item);
	public Item meltItem();
}

interface Openable {
	public void open();
}
interface Pushable {
	public void push();
}

interface Shakeable {
	public void shake();
	public boolean deadly();
}

interface Startable {
	public void start();
}

interface Valuable {
	public int value();
	public void setValue(int value);
}

interface Visible {
	public boolean isVisible();
	public void setVisible(boolean b);
}


interface Wearable {
	// marker
}
