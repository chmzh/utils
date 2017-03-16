import java.util.Random;
import java.util.concurrent.ConcurrentSkipListSet;

public class ConTest {
	private static ConcurrentSkipListSet<Person> persons = new ConcurrentSkipListSet<Person>();
	public static void main(String[] args) {
		for(int i=0;i<500;i++){
			Random random = new Random(i);
			int sorce = random.nextInt(100);
			Person person = new Person();
			person.setSorce(sorce);
			persons.add(person);
			System.out.print(sorce+",");
		}
		System.out.println();
		for(Person person: persons){
			System.out.print(person.getSorce()+",");
		}
		System.out.println();
		
		System.out.println(persons.first().getSorce());
		System.out.println(persons.last().getSorce());
	}
}
