package Movieticketbooking;

import java.util.*;

public class Main {
    static Map<Integer, Movie> movielist = new HashMap<>();
    static Map<Integer, Customer> bookings = new HashMap<>();
    static String defaultusername = "KIT";
    static String defaultpassword = "KIT";

    public static void bookticket() {
        Scanner sc = new Scanner(System.in);
        if (movielist.size() == 0) {
            System.out.println("There is no Available movies");
            return;
        }
        System.out.print("Enter your name:");
        String name = sc.nextLine();
        System.out.print("Enter your age:");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter your Gender('M'/'F'):");
        String gender = sc.nextLine();
        System.out.println("-----------------------------------------------------------");
        for (Map.Entry<Integer, Movie> movies : movielist.entrySet()) {
            Movie movie = movies.getValue();
            System.out.println(movie.movieid + "   " + movie.moviename + "  " + movie.amount);
        }
        System.out.println("-----------------------------------------------------------");
        System.out.print("Enter the movie Id:");
        int movieid = sc.nextInt();
        Movie movie = null;
        if (movielist.containsKey(movieid)) {
            movie = movielist.get(movieid);
        } else {
            System.out.print("Enter the valid movieId:");
            while (!movielist.containsKey(movieid)) {
                movieid = sc.nextInt();
                movie = movielist.get(movieid);
            }
        }
        if (movie.ticketcount <= 0) {
            System.out.print("Sorry, No Tickets Available for " + movie.moviename + " Choose Different Movie:");
            while (movie.ticketcount <= 0) {
                movieid = sc.nextInt();
                movie = movielist.get(movieid);
            }
        }
        System.out.print("Enter Howmany Tickets you have required:");
        int ticketcount = sc.nextInt();
        if (ticketcount > movie.ticketcount) {
            System.out
                    .println("There is only availability of " + movie.ticketcount + " tickets for " + movie.moviename);
            System.out.print("Enter the valid amount of Tickets");
            while (ticketcount > movie.ticketcount) {
                ticketcount = sc.nextInt();
            }
        }
        movie.ticketcount -= ticketcount;
        sc.nextLine();
        Customer customer = new Customer(name, age, gender, movie, ticketcount);
        bookings.put(customer.customerid, customer);
        for (int i = 1; i <= customer.count; i++) {
            customer.tickets.add(movie.queue.remove());
        }
        System.out.println("------------------------PVR CINEMAS------------------------");
        System.out.println("Name              :" + customer.name);
        System.out.println("movie             :" + movie.moviename);
        System.out.println("no of tickets     :" + customer.count);
        System.out.println("price per ticket  :" + movie.amount);
        System.out.println("Total Amount      :" + customer.amount);
        Queue<String> tickets = new LinkedList<>(customer.tickets);
        while (tickets.size() != 0) {
            System.out.print(tickets.remove() + " ");
        }
        System.out.println();
        System.out.println("-----------------------------------------------------------");

    }

    public static void cancelticket() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your id:");
        int id = sc.nextInt();
        if (!bookings.containsKey(id)) {
            System.out.println("Enter valid Id");
            return;
        }
        Customer customer = bookings.get(id);
        Movie movie = customer.movie;
        Queue<String> bookedtickets = customer.tickets;
        movie.ticketcount += bookedtickets.size();
        while (bookedtickets.size() > 0) {
            movie.queue.add(bookedtickets.remove());
        }
        System.out.println("-----------TICKET CANCELLED SUCCESSFULLY--------------");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("-----------------------------------------------------------");
            System.out.println("Enter the operation to perform:");
            System.out.println(
                    "    1.Book Movie Ticket \n    2.Cancel Movie Ticket \n    3.view Available tickets  \n    4.Add movie \n    5.Delete movie");
            System.out.println("-----------------------------------------------------------");

            int option = sc.nextInt();
            switch (option) {
                case 1: {
                    bookticket();
                    break;
                }
                case 2: {
                    cancelticket();
                    break;
                }
                case 3: {
                    System.out.println("-----------------------------------------------------------");
                    for (Map.Entry<Integer, Movie> movies : movielist.entrySet()) {
                        Movie movie = movies.getValue();
                        System.out.println(movie.movieid + "   " + movie.moviename + "  " + movie.amount);
                    }
                    System.out.println("-----------------------------------------------------------");
                    System.out.print("Enter the movie Id:");
                    int movieid = sc.nextInt();
                    Movie movie = null;
                    if (movielist.containsKey(movieid)) {
                        movie = movielist.get(movieid);
                    } else {
                        System.out.print("Enter the valid movieId:");
                        while (!movielist.containsKey(movieid)) {
                            movieid = sc.nextInt();
                            movie = movielist.get(movieid);
                        }
                    }
                    System.out.print("AVAILABLE TICKETS:" + movie.ticketcount);

                    break;
                }
                case 4: {
                    sc.nextLine();
                    System.out.print("Enter the Username:");
                    String username = sc.nextLine();
                    System.out.print("Enter the password:");
                    String password = sc.nextLine();
                    if (username.equals(defaultusername) && password.equals(defaultpassword)) {
                        System.out.print("Enter the Movie Name:");
                        String moviename = sc.nextLine();
                        System.out.print("Enter the Amount per ticket for " + moviename + ":");
                        double amount = sc.nextDouble();
                        Movie movie = new Movie(moviename, amount);
                        movielist.put(movie.movieid, movie);
                    } else {
                        System.out.println("Incorrect Login credentials");
                    }
                    break;
                }
                case 5: {
                    System.out.println("Enter the movie Id:");
                    int movieid = sc.nextInt();
                    if (movielist.containsKey(movieid)) {
                        movielist.remove(movieid);
                        System.out.println("Movie Deleted Successfully");
                    } else {
                        System.out.println("Invalid Movie Id");
                    }
                }
                default:
                    break;
            }
        }

    }
}
