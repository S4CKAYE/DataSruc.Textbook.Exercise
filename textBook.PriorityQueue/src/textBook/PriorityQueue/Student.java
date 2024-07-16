package textBook.PriorityQueue;

public class Student implements Comparable<Student> {
	String name;
	int mark; //score
	
	public Student(String name, int mark) {
		this.name = name;
		this.mark = mark;
	}
	
	@Override
	//students with more marked are more important
	public int compareTo(Student other) {
		// TODO Auto-generated method stub
		if(this.mark > other.mark) {
			return -1;
		}else if(this.mark == other.mark) {
			return 0;
		}
		return 1;
	}
	
	
}
