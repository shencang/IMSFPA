package Other;
//获得时间
import Res.Values.GetString;

import java.util.Calendar;


public class GetTime {
     Calendar calendar=Calendar.getInstance();

    public  int getHour(){
        return  calendar.get(Calendar.HOUR_OF_DAY);

    }
    public  int getYear(){
        return  calendar.get(Calendar.YEAR);
    }

    public  int getMmonth(){
        return  calendar.get(Calendar.MONTH);
    }
    public  int getDate(){
        return  calendar.get(Calendar.DATE);
    }
    public  int getMinute(){
        return  calendar.get(Calendar.MINUTE);
    }
    public  int getSecond(){
        return  calendar.get(Calendar.SECOND);
    }
    public static String gethourString(){
        String output =null;
        GetTime get=new GetTime();

        int hour = get.getHour();
        if (hour<=6){
            output= GetString.Time_bd+GetString.welcome;
        }
        if (hour>6&&hour<=8){
            output= GetString.Time_mor+GetString.welcome;
        }
        if(hour>8&&hour<12){
            output= GetString.Time_am+GetString.welcome;

        }
        if (hour>=12&&hour<=13){
            output =GetString.Time_aft+GetString.welcome;

        }
        if (hour>13&&hour<19){
            output =GetString.Time_pm+GetString.welcome;
        }
        if (hour>=19&&hour<24){
            output =GetString.Time_eve+GetString.welcome;
        }
        return output;
    }
}
