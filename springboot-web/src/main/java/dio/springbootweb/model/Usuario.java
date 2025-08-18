package dio.springbootweb.model;

public class Usuario {
    private String login;
    private String password;
    private String username;

    public Usuario() {
    }

    public Usuario(String login, String password, String username) {
        this.login = login;
        this.password = password;
        this.username = username;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }    

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Usuario [login=" + login + ", password=" + password + ", username=" + username + "]";
	}
}
