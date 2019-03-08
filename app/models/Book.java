package models;


import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Book extends Model {

    @Id
    @Constraints.Required
    public Integer id;
    @Constraints.Required
    @Constraints.MaxLength(50)
    @Constraints.MinLength(5)
    public String title;
    @Constraints.Required
    @Constraints.Min(5)
    @Constraints.Max(100)
    public Integer price;
    @Constraints.Required
    public String author;

    public static Finder<Integer, Book> finder = new Finder<Integer, Book>(Book.class);



}
