
public class bodyAlgorithm {
	static HarrisBenedict hb = new HarrisBenedict();
	public static void bdAlgorithm(float height,float weight,int aCareer,int choice) {
		String bodyType = "";
		//1.Ȱ���� ���ų� ��� ���� ���  
		//2.������ Ȱ�� �� �(�뷫 1-2��/��)
		//3.������ Ȱ�� �� �(�뷫 3-5��/��)
		//4.�������� Ȱ�� �� �(�뷫 6-7��/��)
		//5.���� �������� Ȱ�� �� �
		if(hb.bmiCal(height, weight)<18.5) { //��ü��
			bodyType += "��ü�� ";
			switch(aCareer){
		        case 1: //���� ~1
		        	bodyType += "�����"; break;
		        case 2: //����  1~2
		        	if(choice == 1 || choice == 2) bodyType += "�����";
		        	else bodyType += "������"; break;
		        case 3: // ���� 2~
		        	bodyType += "������"; break;
			}
		}

		else if (hb.bmiCal(height, weight)>=18.5 && hb.bmiCal(height, weight)<26) {//ǥ��ü��
			bodyType += "ǥ��ü�� ";
			switch(aCareer){
		        case 1: //���� ~1
		        	if(hb.bmiCal(height, weight)>24.5) bodyType += "����"; 
		        	else bodyType += "�����";break;
		        case 2: //����  1~2
		        	if(choice == 1 || choice == 2) {
		        		if(hb.bmiCal(height, weight)>24.5) bodyType += "����"; 
		        		else bodyType += "�����";
		        	} 
		        	else bodyType +="������"; break;
		        case 3: // ���� 2~
		        	bodyType += "������"; break;
			}
		}
		
		else { //��ü��
			bodyType += "��ü�� ";
			switch(aCareer){
		        case 1: //���� ~1
		        	if(hb.bmiCal(height, weight)>30) bodyType += "����"; 
		        	else bodyType += "�����"; break;
		        case 2: //����  1~2
		        	if(choice == 1 || choice == 2) {
			        	if(hb.bmiCal(height, weight)>30) bodyType += "����"; 
		        		else bodyType += "�����";
		        	} 
		        	else bodyType += "������"; break;
				case 3: // ���� 2~
		        	if(hb.bmiCal(height, weight)>30 && choice == 5) bodyType ="���������";
		        	else bodyType += "������"; break;
			}
		}
		System.out.println(bodyType);
	}
}
