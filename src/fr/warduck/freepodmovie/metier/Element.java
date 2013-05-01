package fr.warduck.freepodmovie.metier;

public class Element {
	private String title ;
	private String description ;
	private String duree;
	private String urlMedia ;
	private String datePublication ;
	private boolean vu ;
	private Flux flux;

	/**
	 * @return the flux
	 */
	public Flux getFlux() {
		return flux;
	}

	/**
	 * @param flux the flux to set
	 */
	public void setFlux(Flux flux) {
		this.flux = flux;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return the duree
	 */
	public String getDuree() {
		return duree;
	}

	/**
	 * @param duree the duree to set
	 */
	public void setDuree(String duree) {
		this.duree = duree;
	}

	/**
	 * @return the urlMedia
	 */
	public String getUrlMedia() {
		return urlMedia;
	}
	
	/**
	 * @param urlMedia the urlMedia to set
	 */
	public void setUrlMedia(String urlMedia) {
		this.urlMedia = urlMedia;
	}
	
	/**
	 * @return the datePublication
	 */
	public String getDatePublication() {
		return datePublication;
	}
	
	/**
	 * @param datePublication the datePublication to set
	 */
	public void setDatePublication(String datePublication) {
		this.datePublication = datePublication;
	}
	
	/**
	 * @return the vu
	 */
	public boolean isVu() {
		return vu;
	}
	
	/**
	 * @param vu the vu to set
	 */
	public void setVu(boolean vu) {
		this.vu = vu;
	}
}
