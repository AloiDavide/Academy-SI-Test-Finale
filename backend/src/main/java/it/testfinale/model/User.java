package it.testfinale.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "username", nullable = false, length = 50)
	private String username;

	@Column(name = "email", nullable = false, unique = true, length = 100)
	private String mail;

	@Column(name = "password", nullable = false, length = 255)
	private String password;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<WeatherDaily> savedWeathers;

	// Getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<WeatherDaily> getSavedWeathers() {
		return savedWeathers;
	}

	public void setSavedWeathers(List<WeatherDaily> savedWeathers) {
		this.savedWeathers = savedWeathers;
	}

	@Override
	public String toString() {
		return "User{" +
				"username='" + username + '\'' +
				", mail='" + mail + '\'' +
				", password='" + password + '\'' +
				", savedWeathers=" + savedWeathers +
				'}';
	}
}