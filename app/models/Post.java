package models;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Lob;

import play.Logger;
import play.db.jpa.GenericModel;
import play.db.jpa.Model;


@Entity
public class Post extends Model 
{
  @OneToMany (cascade = {CascadeType.ALL})
  public List<BlogComment> comments = new ArrayList<BlogComment>();
	
  public String title;
  @Lob
  public String content;

  public Post(String title, String content)
  {
    this.title = title;
    this.content = content;
  }
  
  public void postComment(User user ,String comment,String date){
	   BlogComment blogComm= new BlogComment(user,comment,date);
	   comments.add(blogComm);
	   blogComm.save();
	  
  }

  public String toString()
  {
    return title;
  } 
  
 
}