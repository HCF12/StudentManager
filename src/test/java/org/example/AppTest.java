package org.example;

import static org.junit.Assert.assertTrue;

import cn.com.pojo.SwTestOnlineCustomerNo;
import cn.com.utils.TraditionalChineseCalenderUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.ibatis.javassist.expr.NewArray;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Autowired
    private RestTemplate restTemplate;
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }


    @Test
    public void test1() throws ParseException {
        String startTime = "";
        String endTime = "";
        int timeInterval= 4;
        switch (timeInterval) {
            case 1: //上月
                Map<String, String> lastMonthMap = getLastStartOrEndDayOfMonth();
                startTime = lastMonthMap.get("beginTime");
                endTime = lastMonthMap.get("endTime");

                break;
            case 2: //上季度
                Map<String, String> lastQuarterMap = getLastStartOrEndDayOfQuarter();
                startTime = lastQuarterMap.get("beginTime");
                endTime = lastQuarterMap.get("endTime");
                break;
            case 3: //上年度
                Map<String, String> lastAnnualMap = getLastStartOrEndDayOfYear();
                startTime = lastAnnualMap.get("beginTime");
                endTime = lastAnnualMap.get("endTime");
                break;
            case 4: //本月
                Map<String, String> lastYearMap = getStartOrEndDayOfMonth();
                startTime = lastYearMap.get("beginTime");
                endTime = lastYearMap.get("endTime");
                break;
        }
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
        String startTime2 = sdf3.format(sdf2.parse(startTime));
        String endTime2 = sdf3.format(sdf2.parse(endTime));
        String startTime3 = sdf2.format(sdf2.parse(startTime));
        String endTime3 = sdf2.format(sdf2.parse(endTime));
        System.out.println("startTime2:" + startTime2 + "endTime2:" + endTime2);
        System.out.println("startTime3:" + startTime3 + "endTime3:" + endTime3);
    }
    /**
     * 功能描述: TODO 获取上个季度开始和结束时间
     */
    public static Map<String, String> getLastStartOrEndDayOfQuarter() {
        Map<String, String> dayMap = new HashMap<>(16);
        // 获取当前日期
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);

        // 计算上个季度的起始日期和结束日期
        calendar.add(Calendar.MONTH, -3);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date startDate = calendar.getTime();

        calendar.add(Calendar.MONTH, 3);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date endDate = calendar.getTime();

        // 格式化日期
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String startDateStr = dateFormat.format(startDate);
        String endDateStr = dateFormat.format(endDate);
        dayMap.put("beginTime", startDateStr);
        dayMap.put("endTime", endDateStr);
        return dayMap;
    }

    /**
     * @Description TODO 获取去年的第一天或最后一天
     * @Param: [today, isFirst: true 表示开始时间，false表示结束时间]
     * @return: java.lang.String
     */
    public static Map<String, String> getLastStartOrEndDayOfYear() {
        Map<String, String> dayMap = new HashMap<>(16);
        // 获取当前日期
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        calendar.add(Calendar.YEAR, -1);
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        // 计算上个年度的起始日期和结束日期
        Date startDate = calendar.getTime();
        calendar.add(Calendar.YEAR, -1);
        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        calendar.set(Calendar.DATE, 31);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date endDate = calendar.getTime();

        // 格式化日期
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String startDateStr = dateFormat.format(startDate);
        String endDateStr = dateFormat.format(endDate);
        dayMap.put("beginTime", startDateStr);
        dayMap.put("endTime", endDateStr);
        return dayMap;
    }

    /**
     * 功能描述: 获取上月开始日期和结束日期
     */
    public static Map<String, String> getLastStartOrEndDayOfMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Map<String, String> dayMap = new HashMap<>(16);
        //获取当前日期
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        //设置为1号,当前日期既为本月第一天
        cal.set(Calendar.DAY_OF_MONTH, 1);
        String firstDay = sdf.format(cal.getTime());
        //获取前月的最后一天
        Calendar cale = Calendar.getInstance();
        //设置为1号,当前日期既为本月第一天
        cale.set(Calendar.DAY_OF_MONTH, 0);
        String lastDay = sdf.format(cale.getTime());
        dayMap.put("beginTime", firstDay);
        dayMap.put("endTime", lastDay);
        return dayMap;
    }

    /**
     * 功能描述: 获取本月的第一天或最后一天
     * @Param: [today, isFirst: true 表示开始时间，false表示结束时间]
     * @return: java.lang.String
     */
    public static Map<String, String> getStartOrEndDayOfMonth() {
        Map<String, String> dayMap = new HashMap<>(16);
        // 获取当前日期
        Date currentDate = new Date();
        // 创建Calendar实例
        Calendar calendar = Calendar.getInstance();
        // 设置日期为当前日期
        calendar.setTime(currentDate);
        // 将日期设置为该月的第一天
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        // 获取本月的开始时间
        Date startTime = calendar.getTime();
        // 将日期设置为该月的最后一天
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        // 获取本月的结束时间
        Date endTime = calendar.getTime();

        // 格式化日期
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String startDateStr = dateFormat.format(startTime);
        String endDateStr = dateFormat.format(endTime);
        dayMap.put("beginTime", startDateStr);
        dayMap.put("endTime", endDateStr);
        return dayMap;
    }

    @Test
    public void testChineseCalender(){
        Calendar calendar = Calendar.getInstance();
        String s = "1999-01-12";
        String str[] = s.split("-");
        Integer year = calendar.get(Calendar.YEAR);
        System.out.println(year);
        Integer month = Integer.parseInt(str[1]);
        Integer day = Integer.parseInt(str[2]);
        System.out.println(year + "-" + month);
        int birthday[] = TraditionalChineseCalenderUtils.lunarToSolar(year+1,month,day,true);
        for(int birth : birthday){
            System.out.println(birth);
        }
    }
    public static String getCommaNum(String str) {
        String a = "";
        if (str.contains(String.valueOf('.'))) {
            int index = str.indexOf('.');
            a = str.substring(index, str.length());
            str = str.substring(0, index);
        }
        StringBuffer stringBuffer = new StringBuffer(str);
        for (int i = stringBuffer.length() - 3; i > 0; i -= 3) {

            stringBuffer.insert(i, ",");
        }
        if (a != "") {
            stringBuffer.append(a);
        }
        return stringBuffer.toString();
    }
}
