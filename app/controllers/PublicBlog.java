package controllers;


import play.*;
import play.db.jpa.Blob;
import play.mvc.*;


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
	    Date date = new Date();
	    Post post=Post.findById(postid);
	    String dateString= String.format("%tT%<tp, %<td %<tB %<tY", date );

	    post.postComment(post,user, comment, dateString);
	    post.save();
	    Logger.info ("The post:"+postid+"user: "+user.firstName+"comment: "+comment+ "date: "+ dateString);
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
	  User user = User.findById(id);
	  Post post=Post.findById(postid);
	  render(post,user,currentUser);
  }
  
 
 
}