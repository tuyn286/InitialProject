package com.fpt.api;

import com.fpt.model.Book;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class RestAssuredEx {
    @Test
    public void getAllBooks() {
        RestAssured
                .given()
                .baseUri("https://demoqa.com")
                .when()
                .get("/BookStore/v1/Books")
                .then()
                .statusCode(200)
                .body("books.size()", Matchers.equalTo(8));

//        List<Book> books = response.jsonPath().getList("books", Book.class);
//        for (Book book : books){
//            Assert.assertNotNull(book.getIsbn());
//            System.out.println(book.getDescription());
//        }
    }

}
