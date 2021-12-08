package bsuir.wt.lab4.entity;

import java.io.Serializable;
import java.util.StringJoiner;

public class User implements Serializable {
	private int id;
	private String email;
	private String password;
	private Role role;

	public User(int id, String email, String password, Role role) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public User() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof User)) return false;

		User user = (User) o;

		if (id != user.id) return false;
		if (!email.equals(user.email)) return false;
		if (!password.equals(user.password)) return false;
		return role == user.role;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + email.hashCode();
		result = 31 * result + password.hashCode();
		result = 31 * result + role.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
				.add("id=" + id)
				.add("email='" + email + "'")
				.add("password='" + password + "'")
				.add("role=" + role)
				.toString();
	}
}