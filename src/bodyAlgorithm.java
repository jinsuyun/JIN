
public class bodyAlgorithm {
	static HarrisBenedict hb = new HarrisBenedict();
	public static void bdAlgorithm(float height,float weight,int aCareer,int choice) {
		String bodyType = "";
		//1.활동이 적거나 운동을 안할 경우  
		//2.가벼운 활동 및 운동(대략 1-2일/주)
		//3.보통의 활동 및 운동(대략 3-5일/주)
		//4.적극적인 활동 및 운동(대략 6-7일/주)
		//5.아주 적극적인 활동 및 운동
		if(hb.bmiCal(height, weight)<18.5) { //저체중
			bodyType += "저체중 ";
			switch(aCareer){
		        case 1: //운동경력 ~1
		        	bodyType += "허약형"; break;
		        case 2: //운동경력  1~2
		        	if(choice == 1 || choice == 2) bodyType += "허약형";
		        	else bodyType += "강인형"; break;
		        case 3: // 운동경력 2~
		        	bodyType += "강인형"; break;
			}
		}

		else if (hb.bmiCal(height, weight)>=18.5 && hb.bmiCal(height, weight)<26) {//표준체중
			bodyType += "표준체중 ";
			switch(aCareer){
		        case 1: //운동경력 ~1
		        	if(hb.bmiCal(height, weight)>24.5) bodyType += "비만형"; 
		        	else bodyType += "허약형";break;
		        case 2: //운동경력  1~2
		        	if(choice == 1 || choice == 2) {
		        		if(hb.bmiCal(height, weight)>24.5) bodyType += "비만형"; 
		        		else bodyType += "허약형";
		        	} 
		        	else bodyType +="강인형"; break;
		        case 3: // 운동경력 2~
		        	bodyType += "강인형"; break;
			}
		}
		
		else { //과체중
			bodyType += "과체중 ";
			switch(aCareer){
		        case 1: //운동경력 ~1
		        	if(hb.bmiCal(height, weight)>30) bodyType += "비만형"; 
		        	else bodyType += "허약형"; break;
		        case 2: //운동경력  1~2
		        	if(choice == 1 || choice == 2) {
			        	if(hb.bmiCal(height, weight)>30) bodyType += "비만형"; 
		        		else bodyType += "허약형";
		        	} 
		        	else bodyType += "강인형"; break;
				case 3: // 운동경력 2~
		        	if(hb.bmiCal(height, weight)>30 && choice == 5) bodyType ="보디빌더형";
		        	else bodyType += "강인형"; break;
			}
		}
		System.out.println(bodyType);
	}
}
