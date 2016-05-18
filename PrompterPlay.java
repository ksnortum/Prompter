package net.snortum.prompter;

import java.util.Arrays;
import java.util.List;

public class PrompterPlay {
	public static void main(String[] args) {
		new PrompterPlay().start();
	}
	
	private void start() {
		Prompter p = new Prompter();
		String name;
		int age;
		String address;
		String gender;
		double salary;
		
		do {
    		name = p.getString("Enter your name");
    		age = p.getInt("Enter your age");
    		address = p.getString("Enter your street address");
    		salary = p.getDouble("Enter you annual salary");
    		List<String> options = Arrays.asList("m", "f", "t");
    		gender = p.getFromValidList("Enter gender (m/f/t)", options);
		} while (p.getYN("Is this correct?").equals("n")); 
		
		p.pause();
		System.out.printf("Name: %s, age: %d, gender: %s%n", name, age, gender);
		System.out.println(address);
		System.out.printf("Salary: %,10.2f", salary );
	}
}
