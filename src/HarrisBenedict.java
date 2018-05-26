import java.util.Arrays;
import java.util.List;

public class HarrisBenedict {
	public float bmiCal(float height,float weight){
		return (float)(weight/((height/100)*(height/100)));
	}
	public static float bmrCal(float height,float weight,int age,String sex) { //기초 대사량 계산
		float bmrcal = 0;
		if(sex =="m") {
			bmrcal = (int) (88.362+(13.397*weight)+(4.799*height)-(5.677*age));
		}
		else if(sex =="w") {
			bmrcal = (int) (447.593+(9.247*weight)+(3.098*height)-(4.33*age));
		}
		return bmrcal;
	}
	
	public static float actCal(float height,float weight,int age,String sex,int choice) { //활동대사량 계산
		float actcal = 0;
		switch(choice){
	        case 1: //활동이 적거나 운동을 안할 경우
	        	actcal = (float) (bmrCal(height, weight, age, sex)*0.2);
			case 2: //가벼운 활동 및 운동(대략 1-2일/주)
	        	actcal = (float) (bmrCal(height, weight, age, sex)*0.375);
	        case 3: // 보통의 활동 및 운동(대략 3-5일/주)
	        	actcal =  (float) (bmrCal(height, weight, age, sex)*0.555);
	        case 4: //적극적인 활동 및 운동(대략 6-7일/주)
	        	actcal = (float) (bmrCal(height, weight, age, sex)*0.725);
	        case 5: //아주 적극적인 활동 및 운동
	        	actcal = (float) (bmrCal(height, weight, age, sex)*0.9);	
	        default:    
		}
		return actcal;
	}
	public static float tefCal(float height,float weight,int age,String sex,int choice) { //음식소화 흡수열량 계산
		return (actCal(height,weight,age,sex,choice)+bmrCal(height,weight,age,sex))/9;
	}
	public static float consumeCal(float height,float weight,int age,String sex,int choice) { //일일 소비칼로리 계산
		return actCal(height,weight,age,sex,choice)+bmrCal(height,weight,age,sex)+tefCal(height,weight,age,sex,choice);
	}
	public static float targetCal(float weight, float targetWeight,int targetDay) { //일 목표소비 칼로리 계산
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
		System.out.println("\n운동으로 소모해야할 칼로리:"+result.get(0)+"kcal");
		System.out.println("식사조절로 줄여야할 칼로리:"+result.get(1)+"kcal");
	}
}
