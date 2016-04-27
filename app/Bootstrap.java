import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import controllers.Accounts;
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
        
        String photoName1= "socialNetwork.jpg";
        Blob blob1 = new Blob ();
        blob1.set(new FileInputStream(photoName1), MimeTypes.getContentType(photoName1));
        Accounts.pics=blob1;
        photoName1= "bubbles.jpg";
        blob1 = new Blob ();
        blob1.set(new FileInputStream(photoName1), MimeTypes.getContentType(photoName1));
        Accounts.pics2=blob1;
        photoName1= "blur2.jpg";
        blob1 = new Blob ();
        blob1.set(new FileInputStream(photoName1), MimeTypes.getContentType(photoName1));
        Accounts.pics3=blob1;
        
        String photoName = "homer.gif";
        Blob blob = new Blob ();
        blob.set(new FileInputStream(photoName), MimeTypes.getContentType(photoName));
        String photoName2 = "homerThumb.png";
        Blob blob2 = new Blob ();
        blob2.set(new FileInputStream(photoName2), MimeTypes.getContentType(photoName2));
        User homer = User.findByEmail("homer@simpson.com");
        homer.profilePicture = blob;
        homer.thumbnailPicture= blob2;
        homer.save(); 
        
        photoName = "marge.gif";
        blob = new Blob ();
        blob.set(new FileInputStream(photoName), MimeTypes.getContentType(photoName));
        photoName2 = "margeThumb.gif";
        blob2 = new Blob ();
        blob2.set(new FileInputStream(photoName2), MimeTypes.getContentType(photoName2));
        User marge = User.findByEmail("marge@simpson.com");
        marge.profilePicture = blob;
        marge.thumbnailPicture=blob2;
        marge.save(); 
        
        photoName = "bart.gif";
        blob = new Blob ();
        blob.set(new FileInputStream(photoName), MimeTypes.getContentType(photoName));
        photoName2 = "bartThumb.jpg";
        blob2 = new Blob ();
        blob2.set(new FileInputStream(photoName2), MimeTypes.getContentType(photoName2));
        User bart = User.findByEmail("bart@simpson.com");
        bart.profilePicture = blob;
        bart.thumbnailPicture=blob2;
        bart.save(); 
        
        photoName = "lisa.gif";
        blob = new Blob ();
        blob.set(new FileInputStream(photoName), MimeTypes.getContentType(photoName));
        photoName2 = "lisaThumb.gif";
        blob2 = new Blob ();
        blob2.set(new FileInputStream(photoName2), MimeTypes.getContentType(photoName2));
        User lisa = User.findByEmail("lisa@simpson.com");
        lisa.profilePicture = blob;
        lisa.thumbnailPicture= blob2;
        lisa.save(); 
        
        photoName = "maggie.gif";
       blob = new Blob ();
        blob.set(new FileInputStream(photoName), MimeTypes.getContentType(photoName));
        photoName2 = "maggieThumb.gif";
        blob2 = new Blob ();
        blob2.set(new FileInputStream(photoName2), MimeTypes.getContentType(photoName2));
        User maggie = User.findByEmail("maggie@simpson.com");
        maggie.profilePicture = blob;
        maggie.thumbnailPicture=blob2;
        maggie.save(); 
    }
  }
  
  
}