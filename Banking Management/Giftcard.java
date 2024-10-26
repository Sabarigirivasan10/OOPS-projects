

public class Giftcard {
     int id;
     int pin;
      double amount=0;
      boolean status=true;
      double accountbalance=10000;
      int reward=0;
    Giftcard(int id, int pin)
    {
        this.id=id;
        this.pin=pin;
    }

    public  double getbalance(String id)
    {
        return accountbalance;
    }
}
