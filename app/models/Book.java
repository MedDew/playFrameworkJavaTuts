package models;


import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Book extends Model {

    @Id
    public Integer id;
    public String title;
    public Integer price;
    public String author;

    public static Finder<Integer, Book> finder = new Finder<Integer, Book>(Book.class);



}
