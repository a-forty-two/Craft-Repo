package java_sandbox;

public class multi_method {

	// main method does all the work
	public static void main(String[] args) {
		int num;
		num = 100;
		System.out.println(method_name());
		System.out.println(method_name_two(num));

	}
	
	// access return_type method_name(parameters)
	public static int method_name(){
		int x;
		x = 10;
		return x;
	}
	
	// must define parameters
	public static int method_name_two(int x){
		int y;
		y = x + 100;
		return y;
	}

}
