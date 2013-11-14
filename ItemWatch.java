import java.util.Scanner;
import java.util.Stack;

public class ItemWatch extends Item implements Holdable {
	
	public ItemWatch(String d, String sd, String[] a) {
		super(d, sd, a);
		this.stack = new Stack<ItemWatchMenu>();
	}
// Inspectable
	public void inspect() {

		Scanner s = new Scanner(System.in);
		String input = "";
/*
		System.out.println("\nCIA SmartWatch v3.2.2\n");
		sleep(800);
		System.out.println("Authenticating via retina scan...");
		for(int i=0; i < 3; i++) { 
			sleep(800);
			System.out.println("...");
		}
		sleep(800);
		System.out.println("Retina scan complete. A hologram appears.");
		sleep(800);
*/
		int n = 0;
		out:
		while(true) {
			ItemWatchMenu menu = this.stack.peek();
			System.out.println(menu);
			while(true) {
				System.out.print("$ ");
				input = s.nextLine();
				try {
					n = Integer.parseInt(input);
				}
				catch(Exception e) {
					if(input.toLowerCase().equals("exit")) {
						while(this.stack.size() > 1) stack.pop();
						break out;
					}
					System.out.println("Invalid selection.");
					continue;
				}
				if(n == menu.count()) {
					if(this.stack.size() == 1) {
						break out;
					}
					else {
						this.stack.pop();
					}
				}
				else if (n > menu.count()) {
					System.out.println("Invalid selection.");
					continue;
				}
				else {
					this.stack.push(menu.get(n-1));
				}
				break;
			}
		}
		System.out.println("You turn off the watch and lower your wrist.");
	}
	public void setMenu(ItemWatchMenu main) {
		this.stack.push(main);
	}
	private void sleep(int s) {
		try{
			Thread.sleep(s);
		}
		catch(Exception e1) {
			// pass
		}
	}
	protected Stack<ItemWatchMenu> stack;
}
