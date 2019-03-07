package controllers;

import models.Book;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.Books.create;
import views.html.Books.index;
import views.html.Books.edit;
import views.html.Books.show;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;


public class BooksController extends Controller {

    @Inject
    FormFactory formFactory;

    public Result index(){
//        Set<Book> bookSet = Book.allBooks();
        List<Book> bookList = Book.finder.all();
        return ok(index.render(bookList));
    }

    public Result create(){
        Form<Book> bookForm = formFactory.form(Book.class);
//        bookForm.
        return ok(create.render(bookForm));
    }

    public Result save(){
        Form<Book> bookForm = formFactory.form(Book.class).bindFromRequest();
        Book book = bookForm.get();
        /*DynamicForm bookForm = formFactory.form().bindFromRequest();
        Optional<DynamicForm.Dynamic> book = bookForm.value();
        DynamicForm.Dynamic dynamic =book.get();
        Map<String, Object> data =dynamic.getData();
        for(Map.Entry<String, Object> d : data.entrySet()){
            System.out.println(d.getKey()+" => "+d.getValue());
        }*/

//        Book.add(book);
        book.save();
        return redirect(routes.BooksController.index());
    }

    public Result edit(Integer id){
//        Book bookFound = Book.findById(id);
        Book bookFound = Book.finder.byId(id);
        if(bookFound == null){
            return notFound("Book not found");
        }
        Form<Book> bookForm = formFactory.form(Book.class).fill(bookFound);
//        bookForm.fill(bookFound);
        return ok(edit.render(bookForm));
    }

    public Result update(){
        Form<Book> bookForm = formFactory.form(Book.class).bindFromRequest();
        Book book = bookForm.get();
//        Book bookUpdate = Book.findById(book.id);
        Book bookUpdate = Book.finder.byId(book.id);

        if(bookUpdate == null){
            return notFound("Book not found");
        }
        bookUpdate.price = book.price;
        bookUpdate.author = book.author;
        bookUpdate.title = book.title;

//        bookUpdate.save();
        bookUpdate.update();
        return redirect(routes.BooksController.index());
    }

    public Result destroy(Integer id){
//        Book bookToDelete = Book.findById(id);
        Book bookToDelete = Book.finder.byId(id);
        if(bookToDelete == null){
            return notFound("Book not found");
        }
//        Book.remove(bookToDelete);
        bookToDelete.delete();
        return redirect(routes.BooksController.index());
    }

    public Result show(Integer id){
//        Book book = Book.findById(id);
        Book book = Book.finder.byId(id);

        if(book == null){
            return notFound("Book not found");
        }

        return ok(show.render(book));
    }
}
