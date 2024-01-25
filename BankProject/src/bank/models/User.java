package bank.models;

import java.util.Date;
import java.util.List;

import com.security.model.validation.annotations.PrincipalAnnotation;

@PrincipalAnnotation(id = "username", birthday = "rodjendan", childrens = "lista")
public class User {

	private String username;
	private List<User> lista;
	public User(String username){
		this.setUsername(username);
	}
	public User(String username, Date rodjedan, List<User> lista){
		this(username, rodjedan);
		this.setLista(lista);
	}
	public User(String username, Date rodjedan){
		this.setUsername(username);
		this.setRodjedan(rodjedan);
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	private Date rodjendan;
	public Date getRodjendan() {
		return rodjendan;
	}
	public void setRodjedan(Date rodjendan) {
		this.rodjendan = rodjendan;
	}
	public List<User> getLista() {
		return lista;
	}
	public void setLista(List<User> lista) {
		this.lista = lista;
	}
}