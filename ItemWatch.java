import java.util.Scanner;
public class ItemWatch extends Item implements Holdable {
	
	public ItemWatch(String d, String sd, String[] a) {
		super(d, sd, a);
	}
// Inspectable
	public void inspect() {

		Scanner s = new Scanner(System.in);
		String input = "";
		System.out.println("\nCIA SmartWatch v3.2.2\n");
		sleep(1000);
		System.out.println("Authenticating via retina scan...");
		for(int i=0; i < 3; i++) { 
			System.out.println("...");
			sleep(1000);
		}
		System.out.println("Retina scan complete.");
		
		while(!input.equals("exit")) {
			System.out.print("$ ");
			input = s.nextLine();
			System.out.println(input);
		}
	}
	private void sleep(int s) {
		try{
			Thread.sleep(s);
		}
		catch(Exception e1) {
			// pass
		}
	}
}
