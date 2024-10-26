public class Booking {
    static int bookid = 1;
    static int cusid = 1;
    int bookingId;
    int customerId;
    char from;
    char to;
    int pickuptime;
    int amount;
    int droptime;
    Taxi taxi;

    Booking(char from, char to, int pickuptime, Taxi taxi) {
        this.bookingId = bookid++;
        this.customerId = cusid++;
        this.from = from;
        this.to = to;
        this.pickuptime = pickuptime;
        this.droptime = calculateDropTime(pickuptime, from, to);
        this.amount += calculateAmount(from, to);
        this.taxi = taxi;
    }

    public int calculateDropTime(int pickuptime, char from, char to) {
        int distance = (Math.abs(from - to));
        return distance + pickuptime;
    }

    public int calculateAmount(char from, char to) {
        int distance = ((Math.abs(from - to))) * 15;
        int amount = 100 + ((distance - 5) * 10);
        return amount;
    }
}
