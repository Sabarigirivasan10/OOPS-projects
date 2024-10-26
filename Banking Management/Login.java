public class Login {
    String id;
    String password;
    Giftcard giftcard;
    

    Login(String id, String pass)
    {
        this.id=id;
        this.password=pass;
    }

    public  Giftcard getgiftcard(String id)
    {
        return giftcard;
    }
    

}
