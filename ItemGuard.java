public class ItemGuard extends Item implements Hostable, Killable, Pushable {

	public ItemGuard(String s, String sd, String[] a) {
		super(s, sd, a);
		this.deathMessage = null;
	}
	public void push() {
		
		System.out.println("you've been spotted!");
	}
	public void kill() {
		if(!this.isDead) {
			System.out.println(this.deathMessage);
			System.out.println("It looks like he dropped something");
			this.detailDescription = "dead guard";
			this.relatedRoom.putItem(this.installedItem);
			this.isDead = true;
		}
		else {
			System.out.println("The guard has already perished.");
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
