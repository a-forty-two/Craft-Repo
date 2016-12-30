package java_sandbox;

public class test_object{
	
	private String name;
	private int x;
	
	// constructor, has no return value, allows you to initialize your object
	// with attributes
	public test_object(String enter_name, int enter_x){
		name = enter_name;
		x = enter_x;
	}
	
	
	// method to simple print out the constructor values
	public void getName(){
		System.out.println(name + x);
	}
	
}
