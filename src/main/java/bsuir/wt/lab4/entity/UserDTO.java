package bsuir.wt.lab4.entity;

import java.io.Serializable;
import java.util.StringJoiner;

public class UserDTO implements Serializable {
	private int id;
	private String email;
	private Role role;

	public UserDTO(int id, String email, Role role) {
		this.id = id;
		this.email = email;
		this.role = role;
	}

	public UserDTO() {
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof UserDTO)) return false;

		UserDTO userDTO = (UserDTO) o;

		if (id != userDTO.id) return false;
		if (!email.equals(userDTO.email)) return false;
		return role == userDTO.role;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + email.hashCode();
		result = 31 * result + role.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", UserDTO.class.getSimpleName() + "[", "]")
				.add("id=" + id)
				.add("email='" + email + "'")
				.add("role=" + role)
				.toString();
	}
}
