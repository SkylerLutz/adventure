public class ItemGuard extends Item implements Hostable, Killable, Pushable {

	public ItemGuard(String s, String sd, String[] a) {
		super(s, sd, a);
		this.deathMessage = null;
	}
	public void push() {
		
		Game.print("you've been spotted!");
	}
	public void kill() {
		if(!this.isDead) {
			Game.print(this.deathMessage);
			Game.print("It looks like he dropped something");
			this.detailDescription = "dead guard";
			this.relatedRoom.putItem(this.installedItem);
			this.isDead = true;
		}
		else {
			Game.print("The guard has already perished.");
		}
	}
	public void install(Item i) {
		if(this.canInstall) {
			this.installedItem = i;
			this.canInstall = false;  // now the player cannot put the card back "in" the guard
		}
	}
	public boolean uninstall(Item i) {
		if(this.installedItem == null) {
			return false;
		}
		else if(this.installedItem == i) {
			this.installedItem = null;
			return true;
		}
		else {
			return false;
		}
	}
	public Item installedItem() {
		return this.installedItem;
	}
	public String deathMessage() {
		return this.deathMessage;
	}	
	public void setDeathMessage(String s) {
		this.deathMessage = s;
	}
	protected Item installedItem;
	protected boolean disappears;
	protected boolean canInstall = true;
	protected boolean isDead = false;
	protected String deathMessage;
}
