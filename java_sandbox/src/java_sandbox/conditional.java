package java_sandbox;

public class conditional {

	public static void main(String[] args) {
		int x = 50;
		int max;
		
		// normal conditional
		if (x < 50){
			System.out.println("this number is less than 50");
		} 
		
		else {
			System.out.println("This number is not less than 50.");
		}
		
		
		// conditional for value assignment
		max = (x < 50) ? x : 100;
		
		System.out.println(max);
		

	}

}
