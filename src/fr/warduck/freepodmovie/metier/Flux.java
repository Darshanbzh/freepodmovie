package fr.warduck.freepodmovie.metier;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.orman.mapper.Model;
import org.orman.mapper.annotation.Entity;
import org.orman.mapper.annotation.ManyToOne;
import org.orman.mapper.annotation.PrimaryKey;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.os.AsyncTask;

@Entity
public class Flux extends Model<Flux> implements Item{
	@PrimaryKey(autoIncrement=true)
    private long id;
	private String title ;
	private String link ;
	private String description ;
	private transient List<Element> elements = new ArrayList<Element>();
	@ManyToOne
	private Categorie categorie;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
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
	 * @return the link
	 */
	public String getLink() {
		return link;
	}
	
	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
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
	 * @return the elements
	 */
	public List<Element> getElements() {
		return elements;
	}

	/**
	 * @param elements the elements to set
	 */
	public void setElements(List<Element> elements) {
		this.elements = elements;
	}

	/**
	 * @return the categorie
	 */
	public Categorie getCategorie() {
		return categorie;
	}

	/**
	 * @param categorie the categorie to set
	 */
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public boolean isCategory() {
		return false;
	}
	
	public void loadElement() {
		new LoadElementTask().execute(this.link);
	}
	
	class LoadElementTask extends AsyncTask<String, Void, List<Element>> {
        @Override
        protected List<Element> doInBackground(String... params) {
        	DateFormat dateFormat;
        	Date date;
        	List<Element> elements = new ArrayList<Element>();
            String link = params[0]; 
            try {
    			URL url = new URL(link);
    			URLConnection urlc = url.openConnection();
    			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    			DocumentBuilder db = dbf.newDocumentBuilder();
    			Document doc = db.parse(urlc.getInputStream());
    			doc.getDocumentElement().normalize();
    			NodeList nodeListItem = doc.getElementsByTagName("item");
    			for(int i = 0 ; i < nodeListItem.getLength() ; i++) {
    				Node node = nodeListItem.item(i);
    				Element elem = new Element();
    				NodeList nodeList = node.getChildNodes();
    				for(int y = 0 ; y < nodeList.getLength() ; y++) {
    					Node nodeIn = nodeList.item(y);
    					if(nodeIn.getNodeName().equalsIgnoreCase("title"))
    						elem.setTitle(nodeIn.getTextContent().replace(" - JeuxVideo.com", ""));
    					if(nodeIn.getNodeName().equalsIgnoreCase("itunes:subtitle"))
    						elem.setDescription(nodeIn.getTextContent());
    					if(nodeIn.getNodeName().equalsIgnoreCase("pubDate")) {
    						dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.ENGLISH);
    						try {
								date = dateFormat.parse(nodeIn.getTextContent());
								dateFormat = new SimpleDateFormat("dd MMM yyyy");
	    						elem.setDatePublication(dateFormat.format(date));
							} catch (ParseException e) {
								e.printStackTrace();
							}
    						//elem.setDatePublication(nodeIn.getTextContent());
    					}
    					if(nodeIn.getNodeName().equalsIgnoreCase("itunes:duration"))
    						elem.setDuree(nodeIn.getTextContent().replace("00:", ""));
    					if(nodeIn.getNodeName().equalsIgnoreCase("enclosure"))
    						elem.setUrlMedia(nodeIn.getAttributes().getNamedItem("url").getNodeValue());
    				}
    				elements.add(elem);
    			}
            } catch (DOMException e) {
				e.printStackTrace();
    		} catch (MalformedURLException e) {
    			e.printStackTrace();
    		} catch (IOException e) {
    			e.printStackTrace();
    		} catch (SAXException e) {
    			e.printStackTrace();
    		} catch (ParserConfigurationException e) {
    			e.printStackTrace();
    		}
            return elements;
        }

        @Override
        protected void onPostExecute(List<Element> result) {
            setElements(result);
        }   
    }

}
