package Movieticketbooking;
import java.util.*;
public class Customer {
    static int id=1000;
    int customerid;
    String name;
    int age;
    String gender;
    Movie movie;
    double amount;
    int count;
    Queue<String>tickets=new LinkedList<>();


    Customer(String name, int age, String gender, Movie movie, int count)
    {
        this.customerid=id++;
        this.name=name;
        this.age=age;
        this.gender=gender;
        this.movie=movie;
        this.count=count;
        this.amount=count*movie.amount;
        
    }
}
