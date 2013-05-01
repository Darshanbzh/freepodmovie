package fr.warduck.freepodmovie.metier;

import org.orman.mapper.EntityList;
import org.orman.mapper.Model;
import org.orman.mapper.annotation.Entity;
import org.orman.mapper.annotation.OneToMany;
import org.orman.mapper.annotation.PrimaryKey;

@Entity
public class Categorie extends Model<Categorie> implements Item{
	@PrimaryKey(autoIncrement=true)
    private long id;
	private String title; 
	@OneToMany(toType = Flux.class, onField = "categorie")
	private EntityList<Categorie,Flux> flux = new EntityList<Categorie, Flux>(Categorie.class, Flux.class, this);
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public EntityList<Categorie, Flux> getFlux() {
		return flux;
	}
	public void setFlux(EntityList<Categorie, Flux> flux) {
		this.flux = flux;
	}
	public boolean isCategory() {
		return true;
	}
}
