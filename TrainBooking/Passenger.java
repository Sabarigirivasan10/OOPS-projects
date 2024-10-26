public class Passenger {

    static int id=1000;

    int passengerId;
    String name;
    int age;
    String gender;
    String preference;
    String ticket;

    Passenger(String name, int age, String gender, String preference, String ticket)
    {
        this.passengerId=id++;
        this.name=name;
        this.age=age;
        this.gender=gender;
        this.preference=preference;
        this.ticket=ticket;
    }
}
