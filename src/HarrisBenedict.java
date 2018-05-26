import java.util.Arrays;
import java.util.List;

public class HarrisBenedict {
	public float bmiCal(float height,float weight){
		return (float)(weight/((height/100)*(height/100)));
	}
	public static float bmrCal(float height,float weight,int age,String sex) { //���� ��緮 ���
		float bmrcal = 0;
		if(sex =="m") {
			bmrcal = (int) (88.362+(13.397*weight)+(4.799*height)-(5.677*age));
		}
		else if(sex =="w") {
			bmrcal = (int) (447.593+(9.247*weight)+(3.098*height)-(4.33*age));
		}
		return bmrcal;
	}
	
	public static float actCal(float height,float weight,int age,String sex,int choice) { //Ȱ����緮 ���
		float actcal = 0;
		switch(choice){
	        case 1: //Ȱ���� ���ų� ��� ���� ���
	        	actcal = (float) (bmrCal(height, weight, age, sex)*0.2);
			case 2: //������ Ȱ�� �� �(�뷫 1-2��/��)
	        	actcal = (float) (bmrCal(height, weight, age, sex)*0.375);
	        case 3: // ������ Ȱ�� �� �(�뷫 3-5��/��)
	        	actcal =  (float) (bmrCal(height, weight, age, sex)*0.555);
	        case 4: //�������� Ȱ�� �� �(�뷫 6-7��/��)
	        	actcal = (float) (bmrCal(height, weight, age, sex)*0.725);
	        case 5: //���� �������� Ȱ�� �� �
	        	actcal = (float) (bmrCal(height, weight, age, sex)*0.9);	
	        default:    
		}
		return actcal;
	}
	public static float tefCal(float height,float weight,int age,String sex,int choice) { //���ļ�ȭ ������� ���
		return (actCal(height,weight,age,sex,choice)+bmrCal(height,weight,age,sex))/9;
	}
	public static float consumeCal(float height,float weight,int age,String sex,int choice) { //���� �Һ�Į�θ� ���
		return actCal(height,weight,age,sex,choice)+bmrCal(height,weight,age,sex)+tefCal(height,weight,age,sex,choice);
	}
	public static float targetCal(float weight, float targetWeight,int targetDay) { //�� ��ǥ�Һ� Į�θ� ���
		return ((weight - targetWeight) * 7200)/targetDay;
	}
	/*public static void exerCal(float weight, float targetWeight,int targetDay) {
		targetCal(weight,targetWeight,targetDay)*0.3;
	}
	public static void foodCal(float weight, float targetWeight,int targetDay) {
		targetCal(weight,targetWeight,targetDay)*0.7;
	}*/
	public static List<Float> ratioCal(float weight, float targetWeight,int targetDay){
		return Arrays.asList((float)(targetCal(weight,targetWeight,targetDay)*0.3),(float)(targetCal(weight,targetWeight,targetDay)*0.7));
	}
	public static void usingRCal(float weight,float targetWeight,int targetDay) {
		List<Float> result = ratioCal(weight, targetWeight, targetDay);
		System.out.println("\n����� �Ҹ��ؾ��� Į�θ�:"+result.get(0)+"kcal");
		System.out.println("�Ļ������� �ٿ����� Į�θ�:"+result.get(1)+"kcal");
	}
}
