package controllers;

import play.*;
import play.db.jpa.Blob;
import play.mvc.*;
import java.util.*;
import models.*;

public class Accounts extends Controller
{
	
  public static Blob pics;
  public static Blob pics2;
  public static Blob pics3;
	 
  public static void signup()
  {
	  List<User> users = User.findAll();
	    render(users);
  }

  public static void login()
  {
	  List<User> users = User.findAll();
	    render(users);
  }

  public static void logout()
  {
	String userId = session.get("logged_in_userid");
    User user = User.findById(Long.parseLong(userId));
	user.online=false;
	user.save();
    session.clear();
    index();
  }

  public static void index()
  {
	  List<User> users = User.findAll();
    render(users);
  }

  public static User getLoggedInUser()
  {
    User user = null;
    if (session.contains("logged_in_userid"))
    {
      String userId = session.get("logged_in_userid");
      user = User.findById(Long.parseLong(userId));
     
    }
    else
    {
      login();
    }
    return user;
  }
  
  public static void register(User user)
  {
    Logger.info(user.firstName + " " + user.lastName + " " + user.email + " " + user.password+" "+user.colour);
   
    user.save();
    index();
  }

  public static void authenticate(String email, String password)
  {
    Logger.info("Attempting to authenticate with " + email + ":" + password);

    User user = User.findByEmail(email);
    if ((user != null) && (user.checkPassword(password) == true))
    {
      Logger.info("Authentication successful");
      session.put("logged_in_userid", user.id);
      user.online=true;
      user.save();
      Home.index();
    }
    else
    {
      Logger.info("Authentication failed");
      login();
    }
  }
  
  public static void getPicture() 
  {
    Blob picture = pics;
    if (picture.exists())
    {
      response.setContentTypeIfNotSet(picture.type());
      renderBinary(picture.get());
    }
  }
  public static void getPicture2() 
  {
    Blob picture = pics2;
    if (picture.exists())
    {
      response.setContentTypeIfNotSet(picture.type());
      renderBinary(picture.get());
    }
  }
  public static void getPicture3() 
  {
    Blob picture = pics3;
    if (picture.exists())
    {
      response.setContentTypeIfNotSet(picture.type());
      renderBinary(picture.get());
    }
  }
  
  
}