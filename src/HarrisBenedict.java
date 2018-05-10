
public class HarrisBenedict {
	public double bmiCal(float height,double weight){
		return weight/((height/100)*(height/100));
	}
	public static float bmrCal(float height,double weight,int age,String sex) { //���� ��緮 ���
		if(sex =="m") {
			return (float) (88.362+(13.397*weight)+(4.799*height)-(5.677*age));
		}
		else if(sex =="w") {
			return (float) (447.593+(9.247*weight)+(3.098*height)-(4.33*age));
		}
		return 0;
	}
	
	public static float actCal(float height,double weight,int age,String sex,int choice) { //Ȱ����緮 ���
		switch(choice){
	        case 1: //Ȱ���� ���ų� ��� ���� ���
	        	return (float) (bmrCal(height, weight, age, sex)*0.2);
			case 2: //������ Ȱ�� �� �(�뷫 1-3��/��)
	        	return (float) (bmrCal(height, weight, age, sex)*0.375);
	        case 3: // ������ Ȱ�� �� �(�뷫 3-5��/��)
	        	return (float) (bmrCal(height, weight, age, sex)*0.555);
	        case 4: //�������� Ȱ�� �� �(�뷫 6-7��/��)
	        	return (float) (bmrCal(height, weight, age, sex)*0.725);
	        case 5: //���� �������� Ȱ�� �� �
	        	return (float) (bmrCal(height, weight, age, sex)*0.9);	
	        default:    
		}
		return 0;
	}
	public static float tefCal(float height,double weight,int age,String sex,int choice) {
		return (actCal(height,weight,age,sex,choice)+bmrCal(height,weight,age,sex))/9;
	}
}
