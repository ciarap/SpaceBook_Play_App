package controllers;


import play.*;
import play.db.jpa.Blob;
import play.mvc.*;

import java.time.ZonedDateTime;
import java.util.*;

import models.*;

public class PublicBlog extends Controller 
{
	  static BlogComment blogComm ;

  public static void visit(Long id)
  {
	  User currentUser=null;
	if (session.contains("logged_in_userid")){
	  currentUser= Accounts.getLoggedInUser();
    }
    User user = User.findById(id);
    Logger.info("Just visiting the blog for " + user.firstName + ' ' + user.lastName);
    render(user,currentUser);
  }
  
  public static void newBlogComment(Long postid,Long id,String comment){
	    User user = Accounts.getLoggedInUser();
	    ZonedDateTime date = ZonedDateTime.now();
	    Post post=Post.findById(postid);
	    post.postComment(post,user, comment, date.getHour()+":"+date.getMinute()+", "+date.getDayOfMonth()+" "+date.getMonth()+" "+date.getYear());
	    post.save();
	    Logger.info ("The post:"+postid+"user: "+user.firstName+"comment: "+comment+ "date: "+ date.getDayOfMonth()+"/"+date.getMonth()+"/"+date.getYear());
	    blogPostPage(postid,id);
  }

  public static void getPicture(Long id) 
  {
	
    User user = User.findById(id);
    Blob picture = user.profilePicture;
    if (picture.exists())
    {
      response.setContentTypeIfNotSet(picture.type());
      renderBinary(picture.get());
    }
  }
  
  public static void blogPostPage(Long postid,Long id){
	  User currentUser=null;
	  if (session.contains("logged_in_userid")){
	  currentUser= Accounts.getLoggedInUser();
	  }
	  Logger.info("ssss"+postid);
	  User user = User.findById(id);
	  Post post=Post.findById(postid);
	  render(post,user,currentUser);
  }
  
 
 
}