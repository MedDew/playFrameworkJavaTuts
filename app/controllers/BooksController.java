package controllers;

import models.Book;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
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

        if(bookForm.hasErrors()){
            flash("danger", "Please fill the form below");
            return badRequest(create.render(bookForm));
        }
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
        flash("success", "Book saved successfully");
        return redirect(routes.BooksController.index());
    }

    public Result edit(Integer id){
//        Book bookFound = Book.findById(id);
        Book bookFound = Book.finder.byId(id);
        if(bookFound == null){
            flash("warning", "Book not found");
            return notFound("Book not found");
        }
        Form<Book> bookForm = formFactory.form(Book.class).fill(bookFound);
//        bookForm.fill(bookFound);
        return ok(edit.render(bookForm));
    }

    public Result update(){
        Form<Book> bookForm = formFactory.form(Book.class).bindFromRequest();
        System.out.println("Req : "+Http.Context.current().request().body());//Http.Context.current().request().body().asJson()

        if(bookForm.hasErrors()){
            flash("danger", "Please correct the form below");
//            return badRequest(edit.render(bookForm));
//            return internalServerError();
            return ok();
        }

        Book book = bookForm.get();
//        Book bookUpdate = Book.findById(book.id);
        Book bookUpdate = Book.finder.byId(book.id);

        if(bookUpdate == null){
            flash("warning", "Book not found");
            return notFound();
        }
        bookUpdate.price = book.price;
        bookUpdate.author = book.author;
        bookUpdate.title = book.title;

//        bookUpdate.save();
        bookUpdate.update();
        flash("success", "Book updated successfully");
//        return redirect(routes.BooksController.index());
        return ok();
    }

    public Result destroy(Integer id){
//        Book bookToDelete = Book.findById(id);
        Book bookToDelete = Book.finder.byId(id);
        if(bookToDelete == null){
            flash("warning", "Book not found");
            return notFound("Book not found");
        }
//        Book.remove(bookToDelete);
        bookToDelete.delete();
        flash("success", "Book deleted successfully");
//        return redirect(routes.BooksController.index());
        return ok();
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
