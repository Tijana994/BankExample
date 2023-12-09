package bank.models;

import java.util.Date;
import java.util.List;

import com.security.model.validation.annotations.PrincipalAnnotation;

@PrincipalAnnotation(id = "username", birthday = "rodjendan", childrensIds = "lista")
public class User {

	private String username;
	private List<String> lista;
	public User(String username){
		this.setUsername(username);
	}
	public User(String username, Date rodjedan, List<String> lista){
		this.setUsername(username);
		this.setRodjedan(rodjedan);
		this.setLista(lista);
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
	public List<String> getLista() {
		return lista;
	}
	public void setLista(List<String> lista) {
		this.lista = lista;
	}
}