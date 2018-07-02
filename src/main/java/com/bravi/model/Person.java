package com.bravi.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="person")
public class Person {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@NotBlank
		private String name;
		
		@Column(name="birth_date")
		private LocalDate birthDate;

		@JsonManagedReference
		@OneToMany(mappedBy="person", cascade=CascadeType.ALL, orphanRemoval=true)
		List<Contact> contacts = new ArrayList<>();
			
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public LocalDate getBirthDate() {
			return birthDate;
		}

		public void setBirthDate(LocalDate birthDate) {
			this.birthDate = birthDate;
		}

		public List<Contact> getContacts() {
			return contacts;
		}

		public void setContacts(List<Contact> contacts) {
			this.contacts = contacts;
		}
		
		public void addContact(Contact contact) {
			this.contacts.add(contact);
			contact.setPerson(this);
		}
		
		public void removeContact(Contact contact) {
			this.contacts.remove(contact);
			contact.setPerson(null);
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Person other = (Person) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Person [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
		}
		
		
 
}
