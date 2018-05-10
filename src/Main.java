
public class Main {
	private float height;
	private double weight;
	private int age;
	private String sex;
	private int choice;
	public Main(float height,double weight,int age,String sex,int choice) {
		this.height = height;
		this.weight = weight;
		this.age = age;
		this.sex = sex;
		this.choice = choice;
	}
	public float getHeight() {
		return height;
	}
	public double getWeight() {
		return weight;
	}
	public int getAge() {
		return age;
	}
	public String getSex() {
		return sex;
	}
	public int getChoice() {
		return choice;
	}
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		Main m = new Main(182,91,28,"m",2);
		HarrisBenedict hb = new HarrisBenedict();
		System.out.printf("BMI: %.2fkcal\n",hb.bmiCal(m.getHeight(),m.getWeight()));
		System.out.printf("기초대사량(BMR): %.2fkcal\n",hb.bmrCal(m.getHeight(),m.getWeight(),m.getAge(),m.getSex()));
		System.out.printf("활동대사량: %.2fkcal\n",hb.actCal(m.getHeight(), m.getWeight(), m.getAge(), m.getSex(), m.getChoice()));
		System.out.printf("음식소화흡수열량: %.2fkcal",hb.tefCal(m.getHeight(), m.getWeight(), m.getAge(), m.getSex(), m.getChoice()));
	}
}