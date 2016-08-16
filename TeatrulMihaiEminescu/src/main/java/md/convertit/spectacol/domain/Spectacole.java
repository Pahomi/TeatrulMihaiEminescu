package md.convertit.spectacol.domain;

import java.util.Date;

public class Spectacole {
	/*
	 * This class represents a Course object
	 * @author 
	 */
	private Long id;
	private String name;
	private int seatsAvailable;
	private boolean premiere;
	private Date data;
	public Spectacole() {
		super();
	}
	public Spectacole(String name, int seatsAvailable, boolean premiere, Date data) {
		super();
		this.name = name;
		this.seatsAvailable = seatsAvailable;
		this.premiere = premiere;
		this.data = data;
	}
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
	public int getSeatsAvailable() {
		return seatsAvailable;
	}
	public void setSeatsAvailable(int seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}
	public boolean isPremiere() {
		return premiere;
	}
	public void setPremiere(boolean premiere) {
		this.premiere = premiere;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "Spectacol [id=" + id + ", name=" + name + ", seatsAvailable=" + seatsAvailable + ", premiere="
				+ premiere + ", data=" + data + "]";
	}
	
}
// this text was added by other 

	
