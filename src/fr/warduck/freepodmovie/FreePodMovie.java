package fr.warduck.freepodmovie;

import org.orman.dbms.Database;
import org.orman.dbms.sqliteandroid.SQLiteAndroid;
import org.orman.mapper.MappingSession;
import org.orman.mapper.SchemaCreationPolicy;

import fr.warduck.freepodmovie.metier.Categorie;
import fr.warduck.freepodmovie.metier.Flux;

import android.app.Application;

public class FreePodMovie extends Application {
	
	@Override
    public void onCreate() {
		//MappingSession.getConfiguration().setCreationPolicy(SchemaCreationPolicy.CREATE);
		MappingSession.registerEntity(Categorie.class);
		MappingSession.registerEntity(Flux.class);
        Database db = new SQLiteAndroid(this, "dbfile.db",1);
        MappingSession.registerDatabase(db);
        MappingSession.start();
        /*
        Categorie jvc = new Categorie();
        jvc.setTitle("Jeuxvideo.com");
        jvc.insert();
        
        Flux gl = new Flux();
        gl.setTitle("Gaming live");
        gl.setCategorie(jvc);
        gl.setLink("http://www.jeuxvideo.com/rss/itunes-hd.xml");
        gl.insert();
        
        Flux chr = new Flux();
        chr.setTitle("Chronique");
        chr.setCategorie(jvc);
        chr.setLink("http://www.jeuxvideo.com/rss/itunes-chroniques-hd.xml");
        chr.insert();
        
        Flux lc = new Flux();
        lc.setTitle("Le CLIQ");
        lc.setCategorie(jvc);
        lc.setLink("http://www.jeuxvideo.com/rss/itunes-le-cliq-hd.xml");
        lc.insert();
        
        Categorie allo = new Categorie();
        allo.setTitle("Allociné");
        allo.insert();
        
        Flux ba = new Flux();
        ba.setTitle("Bandes-annonces");
        ba.setCategorie(allo);
        ba.setLink("http://rss.allocine.fr/bandesannonces/ipod");
        ba.insert();
        
        Flux fr = new Flux();
        fr.setTitle("Faux-raccords");
        fr.setCategorie(allo);
        fr.setLink("http://rss.allocine.fr/fauxraccord/ipod");
        fr.insert();
        
	    Flux tes = new Flux();
	    tes.setTitle("Tueur en série");
	    tes.setCategorie(allo);
	    tes.setLink("http://rss.allocine.fr/tueursenseries/ipod");
	    tes.insert();*/
    }
	
	
	
}
