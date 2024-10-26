package Movieticketbooking;

import java.util.*;


public class Movie {
    static int id=1;
    int movieid;
    String moviename;
    double amount;
    int ticketcount=250;
    Queue<String> queue=new LinkedList<>();
    Movie(String moviename, double amount)
    {
        this.movieid=id++;
        this.moviename=moviename;
        this.amount=amount;
        for(int i=1; i<=26; i++)
        {
            char c=(char)('A'+(i-1));
            String z=String.valueOf(c);
            for(int j=1; j<=10; j++)
            {
                
                queue.add(z+String.valueOf(j));
            }
        }
    }

    
}
