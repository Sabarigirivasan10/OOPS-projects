import java.util.*;

class Main {
    static ArrayList<Taxi> taxilist = new ArrayList<>();
    static Map<Integer, List<Booking>> Bookings = new HashMap<>();

    public static void createTaxi() {
        Taxi taxi = new Taxi();
        taxilist.add(taxi);
        System.out.println("Taxi Created Successfully");
    }

    public static void viewTaxilist() {
        for (int i = 0; i < taxilist.size(); i++) {
            System.out.println("-----------------------");
            System.out.println("Taxi ID:" + taxilist.get(i).taxiId);
            System.out.println("Earnings:" + taxilist.get(i).totalEarnings);
            System.out.println("freetime:" + taxilist.get(i).freeTime);
            System.out.println("-----------------------");
        }
    }

    public static void bookTaxi(char pickup, char drop, int time) {
        ArrayList<Taxi> availavleTaxi = new ArrayList<>();
        for (int i = 0; i < taxilist.size(); i++) {
            Taxi taxi = taxilist.get(i);
            if (taxi.freeTime <= time && ((Math.abs(taxi.currentLocation - pickup))) + taxi.freeTime >= time) {
                availavleTaxi.add(taxi);
            }
        }
        if (availavleTaxi.size() == 0) {
            System.out.print("No Taxis Available");
        } else {
            Collections.sort(availavleTaxi, (t1, t2) -> {
                int distance1 = Math.abs(t1.currentLocation - pickup);
                int distance2 = Math.abs(t2.currentLocation - pickup);
                return Integer.compare(distance1, distance2);
            });
            Taxi taxi = availavleTaxi.get(0);
            Booking booking = new Booking(pickup, drop, time, taxi);
            taxi.freeTime = time + (Math.abs(drop - pickup));
            taxi.currentLocation = drop;

            if (Bookings.containsKey(taxi.taxiId)) {
                List<Booking> list = (Bookings.get(taxi.taxiId));
                list.add(booking);
                Bookings.put(taxi.taxiId, list);
            } else {
                List<Booking> list = new ArrayList<>();
                list.add(booking);
                Bookings.put(taxi.taxiId, list);
            }

            taxi.totalEarnings += booking.amount;
            System.out.println("Taxi" + taxi.taxiId + "Booked Successfully");
        }

    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.println("Enter the option to perform:");
                System.out.println("1.Create Taxi \n2.view TaxiList \n3.Book Taxi \n4.view Transactions");
                int n = sc.nextInt();
                switch (n) {
                    case 1: {
                        createTaxi();
                        break;
                    }
                    case 2: {
                        viewTaxilist();
                        break;
                    }
                    case 3: {
                        System.out.print("Enter the Pickup point:");
                        char pickup = sc.next().charAt(0);
                        System.out.print("Enter the Drop point:");
                        char drop = sc.next().charAt(0);
                        System.out.print("Enter the pickup time:");
                        int time = sc.nextInt();
                        bookTaxi(pickup, drop, time);
                        break;
                    }
                    case 4: {
                        for (Map.Entry<Integer, List<Booking>> m : Bookings.entrySet()) {
                            int id = m.getKey();
                            List<Booking> bookings = m.getValue();
                            System.out.println("Taxi: " + id);
                            System.out.println("-----------------------");
                            for (int i = 0; i < bookings.size(); i++) {
                                Booking b = bookings.get(i);
                                System.out.println(b.bookingId + "  " + b.customerId + " " + b.from + " " + b.to + " "
                                        + b.pickuptime + " " + b.droptime + " " + b.amount);
                            }
                        }
                        break;
                    }

                    default:
                        break;
                }
            }
        }
    }
}