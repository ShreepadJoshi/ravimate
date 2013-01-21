package TestJPA;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_person")
public class Person {
	
	private int id=0;
  

	private String firstName = null;
    private String lastName = null;

    public Person() {
        super();
    }
    @Id
    @GeneratedValue
    @Column(name = "id")
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Column(name="first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Column(name="last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}