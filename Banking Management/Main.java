import java.util.*;

class Main {
    static Map<String, Giftcard> giftcardmap = new HashMap<>();

    public static void creategiftcard(Login user) {
        int giftid = (int) (Math.random() * (99999 - 10000 + 1) + 10000);
        int pin = (int) (Math.random() * (9999 - 1000 + 1) + 1000);
        System.out.println("giftID--------> " + giftid);
        System.out.println("giftcardpin---->" + pin);
        Giftcard giftcard = new Giftcard(giftid, pin);
        user.giftcard = giftcard;
        giftcardmap.put(user.id, giftcard);
        System.out.print(giftcardmap);
    }

    public static void Topup(double amount, Giftcard giftcard) {
        if (giftcard.accountbalance < giftcard.amount) {
            System.out.println("Insufficient Main Account balance");
        }
        giftcard.amount += amount;
        giftcard.accountbalance -= amount;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Enter the option to perform:");
            System.out.println("1.Login \n2.Block  \n3.Topup \n4.Purchase");
            int n = sc.nextInt();
            switch (n) {
                case 1: {
                    System.out.print("Enter your User ID:");
                    String id = sc.next();
                    System.out.print("Enter your password:");
                    String password = sc.next();
                    Login user = new Login(id, password);
                    creategiftcard(user);
                    break;
                }
                case 2: {
                    System.out.print("Enter your User ID:");
                    String id = sc.next();
                    Giftcard giftcard = giftcardmap.get(id);
                    giftcard.status = false;
                    giftcard.accountbalance += giftcard.amount;
                    giftcard.amount = 0;
                    System.out
                            .print("The Account is Blocked and your giftcard amount transferred to your Main Account");
                    break;

                }
                case 3: {
                    System.out.print("Enter your User ID:");
                    String id = sc.next();
                    System.out.print("Enter the amount to transfer:");
                    int amount = sc.nextInt();
                    Giftcard giftcard = giftcardmap.get(id);
                    if (giftcard.status == false) {
                        System.out.println("YOUR ACCOUNT IS BLOCKED");
                    } else {
                        Topup(amount, giftcard);
                        System.out.println(giftcard.accountbalance);
                    }
                    break;

                }
                case 4: {
                    System.out.print("Enter your Giftcard Id:");
                    int giftid = sc.nextInt();
                    System.out.print("Enter your Giftcard password:");
                    int giftpass = sc.nextInt();
                    Giftcard giftcard = null;
                    for (Map.Entry<String, Giftcard> m : giftcardmap.entrySet()) {
                        Giftcard g = m.getValue();
                        if (g.id == giftid && g.pin == giftpass) {
                            giftcard = g;
                            break;
                        }
                    }
                    if (giftcard == null) {
                        System.out.println("Enter the Valid id and pin");
                    } else if (giftcard.status == false) {
                        System.out.print("Your Account is BLOCKED");
                    } else {
                        System.out.print("Enter the purchase amount:");
                        double amount = sc.nextDouble();
                        if (giftcard.amount < amount) {
                            System.out.println("Your current balance is:" + giftcard.amount
                                    + "Insufficient Balance, Please Topup");
                        } else {
                            giftcard.amount -= amount;
                            giftcard.reward += (amount / 100);
                            if (giftcard.reward >= 10) {
                                giftcard.accountbalance += giftcard.reward;
                                giftcard.reward = 0;

                            }
                            System.out.println("Amount in Giftcard:" + giftcard.amount);
                            System.out.println("Amount in Reward:" + giftcard.reward);
                            System.out.println("Account Balance:" + giftcard.accountbalance);
                        }
                    }
                }
                default:
                    sc.close();
                    break;
            }

        }

    }
}