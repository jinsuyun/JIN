package ssm.hel_per;

public class CustomFoodContent {

    private String name;
    private String amount;
    private int consumeCal;

    public CustomFoodContent (String name, String amount, int consumeCal) {
        this.name = name;
        this.amount = amount;
        this.consumeCal = consumeCal;
    }

    public String getFoodName() {  return this.name; }

    public String getAmount() {  return this.amount; }

    public int getConsumeCal() {  return this.consumeCal; }
}
