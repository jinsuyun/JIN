package ssm.hel_per;

public class CustomFoodContent {

    private String name;
    private int num;
    private String amount;
    private int consumeCal;

    public CustomFoodContent (String name, int num, String amount, int consumeCal) {
        this.name = name;
        this.num = num;
        this.amount = amount;
        this.consumeCal = consumeCal;
    }

    public String getFoodName() { return this.name; }

    public int getNum() { return this.num; }

    public String getAmount() { return this.amount; }

    public int getConsumeCal() { return this.consumeCal; }
}
