package java_sandbox;

public enum names_enum_test {
	
	
	// enum is like a python dictionary, we have a key
	// some value or array of information
	//then we take each item and put it in a variable
	// in this case we have the first item be age and description
	// then we define methods to grab these values
	// look at enum_run_test.java class to see how we get he values
	
	BRIAN("22", "This is a man"), JAMES("23", "This is a boy");
	private final String age;
	private final String desc;
	
	names_enum_test(String desc_en, String age_en){
		age = age_en;
		desc = desc_en;	
	}
	
	public String getDesc(){
		return desc;
	}
	
	public String getAge(){
		return age;
	}
	
}