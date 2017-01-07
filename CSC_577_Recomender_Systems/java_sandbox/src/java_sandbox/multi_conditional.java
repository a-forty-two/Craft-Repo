package java_sandbox;

public class multi_conditional {

	public static void main(String[] args) {
		int x = 100;
		int y = 50;
		int z = 25;
		
		// multiple and conditional
		if (x == 100 && y == 50 && z == 25){
			System.out.println("Conditions met");
		}else{
			System.out.println("Conditions not met");
		}
		
		// multiple or condition
		if (x == 100 || y == 55 || z == 20){
			System.out.println("One Condition met");
		}else{
			System.out.println("No Conditions not met");
		}
	}

}
