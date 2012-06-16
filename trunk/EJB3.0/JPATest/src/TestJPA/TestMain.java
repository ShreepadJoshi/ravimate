package TestJPA;

public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JPACreate create = new JPACreate();

		int id = create.createPerson();

		Person person = create.readPerson4mDB(id);
		System.out.println("person 4m DB " + person);

		create.updatePerson(person);

		 person = create.readPerson4mDB(id);
		System.out.println("person 4m DB " + person);

		create.deletePerson(person);

		person = create.readPerson4mDB(id);
		System.out.println("after delete person in DB " + person);

	}

}
