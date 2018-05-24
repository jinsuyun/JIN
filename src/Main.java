import java.util.List;

public class Main {
	private float height;
	private float weight;
	private int age;
	private String sex;
	private int choice;
	private float targetWeight;
	private int targetDay;
	public Main(float height,float weight,float targetWeight,int age,String sex,int choice,int targetDay) {
		this.height = height;
		this.weight = weight;
		this.targetWeight = targetWeight;
		this.age = age;
		this.sex = sex;
		this.choice = choice;
		this.targetDay = targetDay;
	}
	public float getHeight() {
		return height;
	}
	public float getWeight() {
		return weight;
	}
	public float getTWeight() {
		return targetWeight;
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
	public int getTDay() {
		return targetDay;
	}
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		Main m = new Main(181,82,79,27,"m",4,90); // 키, 체중, 목표체중, 나이, 성별, 일활동량, 목표기간
		HarrisBenedict hb = new HarrisBenedict();
		System.out.printf("BMI: %d\n",hb.bmiCal(m.getHeight(),m.getWeight()));
		System.out.printf("기초대사량(BMR): %.2fkcal\n",hb.bmrCal(m.getHeight(),m.getWeight(),m.getAge(),m.getSex()));
		System.out.printf("활동대사량: %.2fkcal\n",hb.actCal(m.getHeight(), m.getWeight(), m.getAge(), m.getSex(), m.getChoice()));
		System.out.printf("음식소화흡수열량: %.2fkcal\n",hb.tefCal(m.getHeight(), m.getWeight(), m.getAge(), m.getSex(), m.getChoice()));
		System.out.printf("일일 소비 칼로리: %.2fkcal\n",hb.consumeCal(m.getHeight(), m.getWeight(), m.getAge(), m.getSex(), m.getChoice()));
		System.out.printf("일 목표 소모 칼로리: %.2fkcal",hb.targetCal(m.getWeight(),m.getTWeight(),m.getTDay()));
		hb.usingRCal(m.getWeight(), m.getTWeight(), m.getTDay());
	}
}