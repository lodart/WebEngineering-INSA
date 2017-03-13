package fr.insa.rennes.web.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Player {
	@Id
	@GeneratedValue
	private int id; // Should factorise this code in a super abstract class. See: the with inheritance example

	@Basic(optional = false) // Not mandatory
	@Column(name = "P_NAME", nullable = false) // Optional. Put here in an illustrative purpose.
	private String name; // Idem

	@Transient
	private boolean anAttributeNotToMakePersistent;

	public Player() {
		super();
	}

	public Player(final String name) {
		super();
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}
}