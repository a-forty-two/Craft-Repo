package java_sandbox;

public class enum_run_test {

	public static void main(String[] args) {
		
		for (names_enum_test item : names_enum_test.values()){
			System.out.printf("%s\t%s\t%s\n", item, item.getAge(), item.getDesc());
		}

	}

}
