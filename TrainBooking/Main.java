import java.util.*;

public class Main {
    static int totalrac = 1;
    static int totalwaitinglist = 1;

    static int lowerticketcount = 1;
    static int middleticketcount = 1;
    static int upperticketcount = 1;
    static int racticketcount = 1;
    static int waitingticketcount = 1;
    static Queue<Passenger> lower = new LinkedList<>();
    static Queue<Passenger> middle = new LinkedList<>();
    static Queue<Passenger> upper = new LinkedList<>();
    static Queue<Passenger> rac = new LinkedList<>();
    static Queue<Passenger> waiting = new LinkedList<>();
    static Map<String, Passenger> passengermap = new HashMap<>();

    public static void BookTicket(String name, int age, String gender, String preference) {
        String ticket = null;
        Passenger p = null;
        if (check(preference)) {

            if (preference.equals("l") || preference.equals("L")) {
                ticket = String.valueOf(lowerticketcount++) + preference;
                p = new Passenger(name, age, gender, preference, ticket);
                System.out.println("Ticket Booked Successfully, Ticket no:" + ticket);
                lower.add(p);
            }
            if (preference.equals("m") || preference.equals("M")) {
                ticket = String.valueOf(middleticketcount++) + preference;
                p = new Passenger(name, age, gender, preference, ticket);
                System.out.println("Ticket Booked Successfully, Ticket no:" + ticket);
                middle.add(p);
            }
            if (preference.equals("u") || preference.equals("U")) {
                ticket = String.valueOf(upperticketcount++) + preference;
                p = new Passenger(name, age, gender, preference, ticket);
                System.out.println("Ticket Booked Successfully, Ticket no:" + ticket);
                upper.add(p);
            }
        } else {
            System.out.println("Your preference berth is not available");
            if (check("L")) {
                ticket = String.valueOf(lowerticketcount++) + "L";
                p = new Passenger(name, age, gender, preference, ticket);
                lower.add(p);
                System.out.println("Ticket Booked Successfully, Ticket no:" + ticket);

            } else if (check("M")) {
                ticket = String.valueOf(middleticketcount++) + "M";
                p = new Passenger(name, age, gender, preference, ticket);
                middle.add(p);
                System.out.println("Ticket Booked Successfully, Ticket no:" + ticket);
            } else if (check("U")) {
                ticket = String.valueOf(upperticketcount++) + "U";
                p = new Passenger(name, age, gender, preference, ticket);
                upper.add(p);
                System.out.println("Ticket Booked Successfully, Ticket no:" + ticket);
            } else if (check("rac")) {
                ticket = String.valueOf(racticketcount++) + "RAC";
                p = new Passenger(name, age, gender, preference, ticket);
                rac.add(p);
                System.out.println("Ticket Booked Successfully, Ticket no:" + ticket);
            } else if (check("wl")) {
                ticket = String.valueOf(waitingticketcount++) + "WL";
                p = new Passenger(name, age, gender, preference, ticket);
                waiting.add(p);
                System.out.println("Ticket Booked Successfully, Ticket no:" + ticket);
            } else {
                System.out.println("SORRY,NO TICKETS AVAILABLE AT A MOMENT");
                return;
            }

        }
        passengermap.put(p.ticket, p);

    }

    public static boolean check(String s) {
        if (s.equals("l") || s.equals("L")) {
            if (Tickets.lower > 0) {
                Tickets.lower -= 1;
                return true;
            }
            return false;
        }
        if (s.equals("m") || s.equals("M")) {
            if (Tickets.middle > 0) {
                Tickets.middle -= 1;
                return true;
            }
            return false;
        }
        if (s.equals("U") || s.equals("u")) {
            if (Tickets.upper > 0) {
                Tickets.upper -= 1;
                return true;
            }
            return false;
        }
        if (s.equals("RAC") || s.equals("rac")) {
            if (Tickets.rac > 0) {
                Tickets.rac -= 1;
                return true;
            }
            return false;
        }
        if (s.equals("wl") || s.equals("WL")) {
            if (Tickets.waitinglist > 0) {
                Tickets.waitinglist -= 1;
                return true;
            }
            return false;
        }
        return false;
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.println("1.Book Ticket \n2.Cancel Ticket \n3.print Bokked Tickets \n4.print Available Tickets");
                System.out.print("Enter the operation to perform:");
                int n = sc.nextInt();
                switch (n) {
                    case 1: {
                        System.out.println("Welcome to Railway Reservation System");
                        System.out.print("Enter your Name:");
                        sc.nextLine();
                        String name = sc.nextLine();
                        System.out.print("Enter your Age:");
                        int age = sc.nextInt();
                        sc.nextLine();
                        if (age < 5) {
                            System.out.println("No Ticket for people less than age 5");
                            break;
                        }
                        System.out.print("Enter your Gender('M'/'F'):");
                        String gender = sc.nextLine();
                        System.out.print("Enter your berth preference('U'/'L'/'M'):");
                        String preference = sc.nextLine();
                        if (age > 60 || gender.equals("F")) {
                            preference = "L";
                        }
                        BookTicket(name, age, gender, preference);
                        break;

                    }
                    case 2: {
                        System.out.println("Enter your ticket number:");
                        sc.nextLine();
                        String ticket = sc.nextLine();
                       // Passenger p = passengermap.get(ticket);
                        passengermap.remove(ticket);
                        String berth = "";
                        for (int i = 0; i < ticket.length(); i++) {
                            if (!Character.isDigit(ticket.charAt(i))) {
                                berth += ticket.charAt(i);
                            }
                        }
                        if (berth.equals("l") || berth.equals("L")) {
                            Tickets.lower += 1;
                            if (Tickets.rac < totalrac) {
                                Passenger r = rac.remove();
                                String rticket = r.ticket;
                                r.ticket = ticket;
                                lower.add(r);
                                Tickets.rac += 1;
                                Tickets.lower -= 1;
                                if (Tickets.waitinglist < totalwaitinglist) {
                                    Passenger w = waiting.remove();
                                    w.ticket = rticket;
                                    rac.add(w);
                                    Tickets.waitinglist += 1;
                                    Tickets.rac -= 1;
                                }
                            }

                        }
                        if (berth.equals("m") || berth.equals("M")) {
                            Tickets.middle += 1;
                            if (Tickets.rac < totalrac) {
                                Passenger r = rac.remove();
                                String rticket = r.ticket;
                                r.ticket = ticket;
                                middle.add(r);
                                Tickets.rac += 1;
                                Tickets.middle -= 1;
                                if (Tickets.waitinglist < totalwaitinglist) {
                                    Passenger w = waiting.remove();
                                    w.ticket = rticket;
                                    rac.add(w);
                                    Tickets.waitinglist += 1;
                                    Tickets.rac -= 1;
                                }
                            }

                        }
                        if (berth.equals("u") || berth.equals("U")) {
                            Tickets.upper -= 1;
                            if (Tickets.rac < totalrac) {
                                Passenger r = rac.remove();
                                String rticket = r.ticket;
                                r.ticket = ticket;
                                upper.add(r);
                                Tickets.rac += 1;
                                Tickets.upper += 1;
                                if (Tickets.waitinglist < totalwaitinglist) {
                                    Passenger w = waiting.remove();
                                    w.ticket = rticket;
                                    rac.add(w);
                                    Tickets.waitinglist += 1;
                                    Tickets.rac -= 1;
                                }
                            }

                        }
                        if (berth.equals("rac") || berth.equals("RAC")) {
                            Tickets.rac += 1;
                            if (Tickets.waitinglist < totalwaitinglist) {
                                Passenger w = waiting.remove();
                                w.ticket = ticket;
                                rac.add(w);
                                Tickets.waitinglist += 1;
                                Tickets.rac -= 1;
                            }

                        }
                        if (berth.equals("wl") || berth.equals("WL")) {
                            Tickets.waitinglist += 1;
                        }
                        break;
                    }
                    case 3: {
                        System.out.println("-----------------------------------------");
                        for (Map.Entry<String, Passenger> m : passengermap.entrySet()) {
                            Passenger p = m.getValue();
                            System.out.println(p.name + "  " + p.age + "  " + p.ticket);

                        }
                        System.out.println("-----------------------------------------");
                        break;
                    }
                    case 4: {
                        System.out.println("Available Tickets-------------------------");
                        System.out.println("Lower Tickets:" + Tickets.lower);
                        System.out.println("Middle Tickets:" + Tickets.middle);
                        System.out.println("Upper Tickets:" + Tickets.upper);
                        System.out.println("RAC Tickets:" + Tickets.rac);
                        System.out.println("Waiting List Tickets:" + Tickets.waitinglist);
                        System.out.println("------------------------------------------");
                        break;
                    }
                }
            }
        }

    }
}