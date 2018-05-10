
public class HarrisBenedict {
	public double bmiCal(float height,double weight){
		return weight/((height/100)*(height/100));
	}
	public static float bmrCal(float height,double weight,int age,String sex) { //기초 대사량 계산
		if(sex =="m") {
			return (float) (88.362+(13.397*weight)+(4.799*height)-(5.677*age));
		}
		else if(sex =="w") {
			return (float) (447.593+(9.247*weight)+(3.098*height)-(4.33*age));
		}
		return 0;
	}
	
	public static float actCal(float height,double weight,int age,String sex,int choice) { //활동대사량 계산
		switch(choice){
	        case 1: //활동이 적거나 운동을 안할 경우
	        	return (float) (bmrCal(height, weight, age, sex)*0.2);
			case 2: //가벼운 활동 및 운동(대략 1-3일/주)
	        	return (float) (bmrCal(height, weight, age, sex)*0.375);
	        case 3: // 보통의 활동 및 운동(대략 3-5일/주)
	        	return (float) (bmrCal(height, weight, age, sex)*0.555);
	        case 4: //적극적인 활동 및 운동(대략 6-7일/주)
	        	return (float) (bmrCal(height, weight, age, sex)*0.725);
	        case 5: //아주 적극적인 활동 및 운동
	        	return (float) (bmrCal(height, weight, age, sex)*0.9);	
	        default:    
		}
		return 0;
	}
	public static float tefCal(float height,double weight,int age,String sex,int choice) {
		return (actCal(height,weight,age,sex,choice)+bmrCal(height,weight,age,sex))/9;
	}
}
