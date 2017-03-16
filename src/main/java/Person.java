import java.util.Comparator;

public class Person implements Comparable<Person> {

	private int sorce;

	// @Override
	// public int compare(Person o1, Person o2) {
	// if(o1.getSorce()>o2.getSorce()){
	// return -1;
	// }else if(o1.getSorce()<o2.getSorce()){
	// return 1;
	// }
	// return 0;
	// }

	public int getSorce() {
		return sorce;
	}

	public void setSorce(int sorce) {
		this.sorce = sorce;
	}

	@Override
	public int compareTo(Person o) {
		if (this.getSorce() > o.getSorce()) {
			return -1;
		} else if (this.getSorce() < o.getSorce()) {
			return 1;
		}
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}
}
