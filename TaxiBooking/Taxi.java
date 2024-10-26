public class Taxi {
    static int id=1;
    int freeTime;
    int taxiId;
    char currentLocation;
    int totalEarnings;

    Taxi()
    {
        this.taxiId=id++;
        this.freeTime=6;
        this.currentLocation='A';
        this.totalEarnings=0;
    }

    


}
