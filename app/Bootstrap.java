import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import play.*;
import play.jobs.*;
import play.test.*;
import play.db.jpa.Blob;
import play.libs.MimeTypes;
 
import models.*;
 
@OnApplicationStart
public class Bootstrap extends Job 
{ 
  public void doJob() throws FileNotFoundException
  {
    if (User.count() == 0)
    {
    	Fixtures.deleteDatabase();
        Fixtures.loadModels("data.yml");
        
       String photoName = "homer.gif";
        Blob blob = new Blob ();
        blob.set(new FileInputStream(photoName), MimeTypes.getContentType(photoName));
        User homer = User.findByEmail("homer@simpson.com");
        homer.profilePicture = blob;
        homer.save(); 
        
        photoName = "marge.gif";
        blob = new Blob ();
        blob.set(new FileInputStream(photoName), MimeTypes.getContentType(photoName));
        User marge = User.findByEmail("marge@simpson.com");
        marge.profilePicture = blob;
        marge.save(); 
        
        photoName = "bart.gif";
        blob = new Blob ();
        blob.set(new FileInputStream(photoName), MimeTypes.getContentType(photoName));
        User bart = User.findByEmail("bart@simpson.com");
        bart.profilePicture = blob;
        bart.save(); 
        
        photoName = "lisa.gif";
        blob = new Blob ();
        blob.set(new FileInputStream(photoName), MimeTypes.getContentType(photoName));
        User lisa = User.findByEmail("lisa@simpson.com");
        lisa.profilePicture = blob;
        lisa.save(); 
        
        photoName = "maggie.gif";
       blob = new Blob ();
        blob.set(new FileInputStream(photoName), MimeTypes.getContentType(photoName));
        User maggie = User.findByEmail("maggie@simpson.com");
        maggie.profilePicture = blob;
        maggie.save(); 
    }
  }
}