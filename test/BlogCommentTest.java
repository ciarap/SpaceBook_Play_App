
import java.util.ArrayList;
import java.util.List;

import models.BlogComment;
import models.Message;
import models.Post;
import models.User;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class BlogCommentTest extends UnitTest
{
  private User bob,john;
  private Post post1;
  private BlogComment comment1, comment2;

  @BeforeClass
  public static void loadDB()
  {
    Fixtures.deleteAllModels();
  }

  @Before
  public void setup()
  {
    bob   = new User("bob", "jones", "bob@jones.com", "secret", 20, "irish");
    john = new User("john", "sullivan", "john@sullivan.com", "secret", 30, "irish");
    post1 = new Post("Post Title 1", "This is the first post content");
    comment1=new BlogComment(john,"This is the first comment on post","25/4/16");
    comment2=new BlogComment(john,"This is the second comment on post","23/4/16");
    bob.save();
    john.save();
    post1.save();
    comment1.save();
 
  }

  @After
  public void teardown()
  {
    bob.delete();
    post1.comments.clear();
    post1.delete();
    comment1.delete();
    comment2.delete();
    john.delete();
    
  }

  @Test
  public void testCreateComment()
  {
    bob.posts.add(post1);
    User user = User.findByEmail("bob@jones.com");
    List<Post> posts = user.posts;
    Post post= posts.get(0);
    post.comments.add(comment1);
    bob.save();
    post.save();
    BlogComment comment= post.comments.get(0);
    assertEquals(1, post.comments.size());
    assertEquals(comment.date, "25/4/16");
    assertEquals(comment.from, john);
    assertEquals(comment.comment, "This is the first comment on post");
  
  }

 @Test
  public void testCreateMultipleComments()
  {
    bob.posts.add(post1);
    List<Post> posts = bob.posts;
    Post post= posts.get(0);
    post.comments.add(comment1);
    post.comments.add(comment2);
    bob.save();
    post.save();

    assertEquals(2, post.comments.size());
    BlogComment commenta = post.comments.get(0);
    assertEquals(commenta.date, "25/4/16");
    assertEquals(commenta.from, john);
    assertEquals(commenta.comment, "This is the first comment on post");

    BlogComment commentb = post.comments.get(1);
    assertEquals(commentb.date, "23/4/16");
    assertEquals(commentb.from, john);
    assertEquals(commentb.comment, "This is the second comment on post");
  }
  @Test
  public void testDeleteComment()
  {
    Post post3 = new Post("Post Title 3", "This is the third post content");
    post3.save();
    bob.posts.add(post3);
    bob.save();
    BlogComment comment3= new BlogComment(john,"This is the third comment on post","20/4/16");
    comment3.save();
    post3.comments.add(comment3);
    post3.save();
    
    User user = User.findByEmail("bob@jones.com");  
    Post post = user.posts.get(0);
    assertEquals(1, post.comments.size()); 
    BlogComment comment= post.comments.get(0);
    post.comments.remove(comment);
    user.save();
    comment3.delete();
    post.save();

    User anotherUser = User.findByEmail("bob@jones.com");
    Post postAnother = anotherUser.posts.get(0);
    assertEquals(0, postAnother.comments.size());   
    
   }
}