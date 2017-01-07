package java_sandbox;

public class case_switch {

	public static void main(String[] args) {
		int x = 3;
		String y = "a";
		
		// with an integer
		switch (x){
		
		case 1: System.out.println("Case 1");
			break;
		case 2: System.out.println("Case 2");
			break;
		case 3: System.out.println("Case 3");
			break;
		default: System.out.println("Default");
		
		}
		
		// with a string
		switch (y){
		
		case "a": System.out.println("Case a");
			break;
		case "b": System.out.println("Case b");
			break;
		case "c": System.out.println("Case c");
			break;
		default: System.out.println("Default");
		
		}
		

	}

}
