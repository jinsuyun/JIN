
public class Main {
	private float height;
	private float weight;
	private int age;
	private String sex;
	private int choice;
	private float targetWeight;
	private int targetDay;
	int aCareer;
	public Main(float height,float weight,float targetWeight,int age,String sex,int choice,int targetDay,int aCareer) {
		this.height = height;
		this.weight = weight;
		this.targetWeight = targetWeight;
		this.age = age;
		this.sex = sex;
		this.choice = choice;
		this.targetDay = targetDay;
		this.aCareer = aCareer;
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
	public int getaCareer() {
		return aCareer;
	}
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		Main m = new Main(182,83,73,28,"m",2,90,1); // Ű, ü��, ��ǥü��, ����, ����, ��Ȱ����, ��ǥ�Ⱓ, ����
		HarrisBenedict hb = new HarrisBenedict();
		System.out.printf("BMI: %.2f\n",hb.bmiCal(m.getHeight(),m.getWeight()));
		System.out.printf("���ʴ�緮(BMR): %.2fkcal\n",hb.bmrCal(m.getHeight(),m.getWeight(),m.getAge(),m.getSex()));
		System.out.printf("Ȱ����緮: %.2fkcal\n",hb.actCal(m.getHeight(), m.getWeight(), m.getAge(), m.getSex(), m.getChoice()));
		System.out.printf("���ļ�ȭ�������: %.2fkcal\n",hb.tefCal(m.getHeight(), m.getWeight(), m.getAge(), m.getSex(), m.getChoice()));
		System.out.printf("���� �Һ� Į�θ�: %.2fkcal\n",hb.consumeCal(m.getHeight(), m.getWeight(), m.getAge(), m.getSex(), m.getChoice()));
		System.out.printf("�� ��ǥ �Ҹ� Į�θ�: %.2fkcal",hb.targetCal(m.getWeight(),m.getTWeight(),m.getTDay()));
		hb.usingRCal(m.getWeight(), m.getTWeight(), m.getTDay());
		
		bodyAlgorithm ba = new bodyAlgorithm();
		ba.bdAlgorithm(m.getHeight(),m.getWeight(),m.getaCareer(),m.getChoice()); // ���� // ��Ȱ����
	}
}