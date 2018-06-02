package ssm.hel_per;

import java.util.Arrays;
import java.util.List;

public class bodyAlgo {
    //1. 저체중허약 2.저체중강인 3.표준체중허약 4.표준체중비만 5.표준체중강인 6.과체중허약 7.과체중비만 8.과체중강인 9.보디빌더
    public static double bmiCal(double height, double weight){
        return (double) (weight/((height/100)*(height/100)));
    }
    public static double bmrCal(double height,double weight,int age,String sex) { //기초 대사량 계산
        double bmrcal = 0;
        if(sex =="m") {
            bmrcal = (double) (88.362+(13.397*weight)+(4.799*height)-(5.677*age));
        }
        else if(sex =="w") {
            bmrcal = (double) (447.593+(9.247*weight)+(3.098*height)-(4.33*age));
        }
        return bmrcal;
    }

    public static double actCal(double height,double weight,int age,String sex,int choice) { //활동대사량 계산
        double actcal = 0;
        switch(choice){
            case 1: //활동이 적거나 운동을 안할 경우
                actcal = (double) (bmrCal(height, weight, age, sex)*0.2);
            case 2: //가벼운 활동 및 운동(대략 1-2일/주)
                actcal = (double) (bmrCal(height, weight, age, sex)*0.375);
            case 3: // 보통의 활동 및 운동(대략 3-5일/주)
                actcal = (double) (bmrCal(height, weight, age, sex)*0.555);
            case 4: //적극적인 활동 및 운동(대략 6-7일/주)
                actcal = (double) (bmrCal(height, weight, age, sex)*0.725);
            case 5: //아주 적극적인 활동 및 운동
                actcal = (double) (bmrCal(height, weight, age, sex)*0.9);
            default:
        }
        return actcal;
    }
    public static double tefCal(double height,double weight,int age,String sex,int choice) { //음식소화 흡수열량 계산
        return (actCal(height,weight,age,sex,choice)+bmrCal(height,weight,age,sex))/9;
    }
    public static double consumeCal(double height,double weight,int age,String sex,int choice) { //일일 소비칼로리 계산
        return actCal(height,weight,age,sex,choice)+bmrCal(height,weight,age,sex)+tefCal(height,weight,age,sex,choice);
    }
    public static double targetCal(double weight, double targetWeight,int targetDay) { //일 목표소비 칼로리 계산
        return ((weight - targetWeight) * 7200)/targetDay;
    }
    public static List<Double> ratioCal(double weight, double targetWeight, int targetDay){
        return Arrays.asList((double)(targetCal(weight,targetWeight,targetDay)*0.3),(double)(targetCal(weight,targetWeight,targetDay)*0.7));
    }
    public static void usingRCal(double weight,double targetWeight,int targetDay) {
        List<Double> result = ratioCal(weight, targetWeight, targetDay);
        System.out.println("\n운동으로 소모해야할 칼로리:"+result.get(0)+"kcal");
        System.out.println("식사조절로 줄여야할 칼로리:"+result.get(1)+"kcal");
    }
}
