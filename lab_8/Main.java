public class Main {
    public static void main(String[] args) {
        coffeeMachine BOSH_A = new coffeeMachine_BOSH();
        coffeeMachine BOSH_B = new coffeeMachine_BOSH();
        coffeeMachine MAKITA_A = new coffeeMachine_MAKITA();
        coffeeMachine MAKITA_B = new coffeeMachine_MAKITA();
        BOSH_A = new Color(BOSH_A, "red");
        BOSH_B = new Color(BOSH_B, "purple");
        BOSH_B = new Chromed(BOSH_B);
        System.out.println(BOSH_A.getInfo() + " --- price: " + BOSH_A.getPrice());
        System.out.println(BOSH_B.getInfo() + " --- price: " + BOSH_B.getPrice());
        MAKITA_A = new Color(MAKITA_A, "black");
        MAKITA_A = new autoCleaning(MAKITA_A);
        MAKITA_B = new autoCleaning(MAKITA_B);
        System.out.println(MAKITA_B.getInfo() + " --- price: " + MAKITA_B.getPrice());
        System.out.println(MAKITA_A.getInfo() + " --- price: " + MAKITA_A.getPrice());
    }
}


interface coffeeMachine
{
    public int getPrice();
    public String getInfo();
}

class coffeeMachine_BOSH implements coffeeMachine
{
    private String info = "BOSH";
    private int price = 1000;
    public int getPrice()
    {
        return this.price;
    }
    public String getInfo()
    {
        return this.info;
    }
}

class coffeeMachine_MAKITA implements coffeeMachine
{
    private String info = "MAKITA";
    private int price = 700;
    public int getPrice()
    {
        return this.price;
    }
    public String getInfo()
    {
        return this.info;
    }
}

class Decorator implements coffeeMachine
{
    private coffeeMachine machine;
    private String info;
    private int price;
    public Decorator(coffeeMachine machine, String info, int price)
    {
        this.machine = machine;
        this.info = info;
        this.price = price;
    }
    public int getPrice()
    {
        return this.price + machine.getPrice();
    }
    public String getInfo()
    {
        return this.info + " " + machine.getInfo();
    }
}

class Color extends Decorator
{
    public Color(coffeeMachine machine, String color)
    {
        super(machine, color, 100);
    }
}

class autoCleaning extends Decorator
{
    public autoCleaning(coffeeMachine coffeeMachine)
    {
        super(coffeeMachine, "auto cleaning", 500);
    }
}

class Chromed extends Decorator
{
    public Chromed(coffeeMachine machine)
    {
        super(machine,  "chromed", 200);
    }
}