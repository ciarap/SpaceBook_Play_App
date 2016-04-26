package models;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import play.Logger;
import play.db.jpa.GenericModel;
import play.db.jpa.Model;

@Entity
public class BlogComment extends Model 
{

  public String date;
  @Lob
  public String comment;
  @ManyToOne
  public User from;
  @ManyToOne
  public Post post;
  
  public BlogComment(Post post,User from,String comment,String date)
  {
	this.post=post;
	this.date=date;
    this.from=from;
    this.comment=comment;
  }

  public String toString()
  {
    return from.firstName+" "+comment+ " "+ date;
  } 
}