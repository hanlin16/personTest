package com.writeBack;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017/6/8.
 */
public class GenPayOrderPushJson {
    /**
     * 打印输入到控制台
     *
     * @param jsonStr
     * @author lizhgb
     * @Date 2015-10-14 下午1:17:22
     */
    public static void printJson(String jsonStr) {
        System.out.println(formatJson(jsonStr));
    }

    /**
     * 格式化
     *
     * @param jsonStr
     * @return
     * @author lizhgb
     * @Date 2015-10-14 下午1:17:35
     * @Modified 2017-04-28 下午8:55:35
     */
    public static String formatJson(String jsonStr) {
        if (null == jsonStr || "".equals(jsonStr))
            return "";
        StringBuilder sb = new StringBuilder();
        char last = '\0';
        char current = '\0';
        int indent = 0;
        boolean isInQuotationMarks = false;
        for (int i = 0; i < jsonStr.length(); i++) {
            last = current;
            current = jsonStr.charAt(i);
            switch (current) {
                case '"':
                    if (last != '\\'){
                        isInQuotationMarks = !isInQuotationMarks;
                    }
                    sb.append(current);
                    break;
                case '{':
                case '[':
                    sb.append(current);
                    if (!isInQuotationMarks) {
                        sb.append('\n');
                        indent++;
                        addIndentBlank(sb, indent);
                    }
                    break;
                case '}':
                case ']':
                    if (!isInQuotationMarks) {
                        sb.append('\n');
                        indent--;
                        addIndentBlank(sb, indent);
                    }
                    sb.append(current);
                    break;
                case ',':
                    sb.append(current);
                    if (last != '\\' && !isInQuotationMarks) {
                        sb.append('\n');
                        addIndentBlank(sb, indent);
                    }
                    break;
                default:
                    sb.append(current);
            }
        }

        return sb.toString();
    }

    /**
     * 添加space
     *
     * @param sb
     * @param indent
     * @author lizhgb
     * @Date 2015-10-14 上午10:38:04
     */
    private static void addIndentBlank(StringBuilder sb, int indent) {
        for (int i = 0; i < indent; i++) {
            sb.append('\t');
        }
    }

    static ResultSet ret = null;


    public static Connection conn = null;
    public static PreparedStatement pst = null;

    public static PreparedStatement getPreparedStatement(String sql) {
        try {
         	String url = "jdbc:mysql://192.168.250.91:3306/ddyunf?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8";
            String name = "com.mysql.jdbc.Driver";
            String user = "ddyunf";
            String password = "rzhtddyunf321";
//            String url = "jdbc:mysql://192.168.251.61:3306/ddyunf?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8";
//            String name = "com.mysql.jdbc.Driver";
//            String user = "root";
//            String password = "P@ssw0rd";
            Class.forName(name);//指定连接类型
            conn = DriverManager.getConnection(url, user, password);//获取连接
            pst = conn.prepareStatement(sql);//准备执行语句
            return pst;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close() {
        try {
            conn.close();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String showId="201709270919420100";
        String sql = "SELECT o.bizId, o.id, o.showId, o.cityCode, o.cityName, o.ciStartDate, o.biStartDate, p.insureComCode, p.channelCode, p.biPremium, p.ciPremium, p.vehicleTaxPremium, p.totalPremium, p.zkPushCredits, c.licenseNo, c.frameNo, c.engineNo, c.registerDate, c.modelCode, c.modelName, c.newCarPrice FROM b_order_detail o INNER JOIN b_car c ON o.carId = c.id INNER JOIN b_precision_price p ON o.precisionPriceId = p.id WHERE o.showId = '"+showId+"'";//SQL语句

        try {
            ret = getPreparedStatement(sql).executeQuery();//执行语句，得到结果集
            SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = sf.format(new Date());
            while (ret.next()) {
                Map<String,Object> data=new LinkedHashMap<>();
                data.put("bizId", ret.getString("bizId"));
                data.put("thpBizId", ret.getString("showId"));
                data.put("totalCredits", Double.valueOf(ret.getString("zkPushCredits"))/100);
                data.put("biPremium", ret.getString("biPremium"));
                data.put("ciPremium", ret.getString("ciPremium"));
                data.put("vehicleTaxPremium", ret.getString("vehicleTaxPremium"));
                data.put("sumPremium", ret.getString("totalPremium"));
                data.put("policyEmail", "117114243@qq.com");
                data.put("biStartDate", ret.getString("biStartDate"));
                data.put("ciStartDate", ret.getString("ciStartDate"));
                data.put("cityCode", ret.getString("cityCode"));
                data.put("cityName", ret.getString("cityName"));
                data.put("agentCode", "CLOUD_SERVICE");
                data.put("accountNo", ret.getString("showId"));
                data.put("productType", "3");
                data.put("serialNumber", null);
                data.put("payTime", date);
                data.put("payType", "TM");
                data.put("payMoney", ret.getString("totalPremium"));
                data.put("payState", 1);
                data.put("cooperateComCode", ret.getString("insureComCode"));
                data.put("channelCode", ret.getString("channelCode"));
                Map<String,Object> personInfo=new LinkedHashMap<>();
                personInfo.put("ownerName", "刘鑫璐");
                personInfo.put("ownerIdNo", "210304198710191612");
                personInfo.put("ownerMobile", "18501992563");
                personInfo.put("applicantName", "刘鑫璐");
                personInfo.put("applicantIdNo", "210304198710191612");
                personInfo.put("applicantMobile", "18501992563");
                personInfo.put("insuredName", "刘鑫璐");
                personInfo.put("insuredIdNo", "210304198710191612");
                personInfo.put("insuredMobile", "18501992563");
                personInfo.put("insuredAddress", null);
                personInfo.put("addresseeName", "刘鑫璐");
                personInfo.put("addresseeMobile", "13758169221");
                personInfo.put("addresseeDetails", "萧山区");
                data.put("personInfo", personInfo);
                Map<String,Object> vehicleInfo=new LinkedHashMap<>();
                vehicleInfo.put("licenseNo", ret.getString("licenseNo"));
                vehicleInfo.put("frameNo", ret.getString("frameNo"));
                vehicleInfo.put("engineNo", ret.getString("engineNo"));
                vehicleInfo.put("registerDate", ret.getString("registerDate"));
                vehicleInfo.put("vehicleModelCode", ret.getString("modelCode"));
                vehicleInfo.put("vehicleBrand", "TYBAED0023");
                vehicleInfo.put("vehicleModelName", ret.getString("modelName"));
                vehicleInfo.put("newCarPrice", ret.getString("newCarPrice"));
                vehicleInfo.put("seatCount", "5");
                vehicleInfo.put("transferFlag", "1");
                vehicleInfo.put("transferDate", null);
                vehicleInfo.put("loanFlag", "0");
                data.put("vehicleInfo", vehicleInfo);
                List<Map<String,Object>> kinds=new ArrayList<>();
                Map<String,Object> kind=new LinkedHashMap<>();
                kind.put("kindCode", "A");
                kind.put("kindName", "机动车损失险");
                kind.put("amount", "52107.60");
                kind.put("premium", "1307.82");
                kind.put("riskCode", "0528");
                kind.put("deductibleFlag", "0");
                kind.put("flag", null);
                kinds.add(kind);
                Map<String,Object> kindb=new LinkedHashMap<>();
                kindb.put("kindCode", "B");
                kindb.put("kindName", "第三者责任险");
                kindb.put("amount", "500000.00");
                kindb.put("premium", "1001.64");
                kindb.put("riskCode", "0528");
                kindb.put("deductibleFlag", "0");
                kindb.put("flag", null);
                kinds.add(kindb);
                Map<String,Object> kindD3=new LinkedHashMap<>();
                kindD3.put("kindCode", "D3");
                kindD3.put("kindName", "司机责任险");
                kindD3.put("amount", "10000.00");
                kindD3.put("premium", "25.18");
                kindD3.put("riskCode", "0528");
                kindD3.put("deductibleFlag", "0");
                kindD3.put("flag", null);
                kinds.add(kindD3);
                Map<String,Object> kindD4=new LinkedHashMap<>();
                kindD4.put("kindCode", "D4");
                kindD4.put("kindName", "乘客责任险");
                kindD4.put("amount", "10000.00");
                kindD4.put("premium", "63.87");
                kindD4.put("riskCode", "0528");
                kindD4.put("deductibleFlag", "0");
                kindD4.put("flag", null);
                kinds.add(kindD4);
                Map<String,Object> kindMA=new LinkedHashMap<>();
                kindMA.put("kindCode", "MA");
                kindMA.put("kindName", "不计免赔险-车损");
                kindMA.put("amount", "1");
                kindMA.put("premium", "196.17");
                kindMA.put("riskCode", "0528");
                kindMA.put("deductibleFlag", "1");
                kindMA.put("flag", null);
                kinds.add(kindMA);
                Map<String,Object> kindMB=new LinkedHashMap<>();
                kindMB.put("kindCode", "MB");
                kindMB.put("kindName", "不计免赔险-三责");
                kindMB.put("amount", "1");
                kindMB.put("premium", "150.25");
                kindMB.put("riskCode", "0528");
                kindMB.put("deductibleFlag", "1");
                kindMB.put("flag", null);
                kinds.add(kindMB);
                Map<String,Object> kindMD3=new LinkedHashMap<>();
                kindMD3.put("kindCode", "MD3");
                kindMD3.put("kindName", "不计免赔险-司机");
                kindMD3.put("amount", "1");
                kindMD3.put("premium", "3.78");
                kindMD3.put("riskCode", "0528");
                kindMD3.put("deductibleFlag", "1");
                kindMD3.put("flag", null);
                kinds.add(kindMD3);
                Map<String,Object> kindMD4=new LinkedHashMap<>();
                kindMD4.put("kindCode", "MD4");
                kindMD4.put("kindName", "不计免赔险-乘客");
                kindMD4.put("amount", "1");
                kindMD4.put("premium", "9.58");
                kindMD4.put("riskCode", "0528");
                kindMD4.put("deductibleFlag", "1");
                kindMD4.put("flag", null);
                kinds.add(kindMD4);
                Map<String,Object> kindFOR=new LinkedHashMap<>();
                kindFOR.put("kindCode", "FORCEPREMIUM");
                kindFOR.put("kindName", "机动车交通事故责任强制险");
                kindFOR.put("amount", null);
                kindFOR.put("premium", "850.0");
                kindFOR.put("riskCode", "0507");
                kindFOR.put("deductibleFlag", "0");
                kindFOR.put("flag", null);
                kinds.add(kindFOR);
                data.put("kinds", kinds);
                List<Map<String,Object>> proposals=new ArrayList<>();
                Map<String,Object> biproposal=new LinkedHashMap<>();
                biproposal.put("proposalNo", "T026105282016047691");
                biproposal.put("policyNo", "1195105282016012691");
                biproposal.put("riskCode", "0528");
                biproposal.put("lastPolicyNo", null);
                biproposal.put("insurancePremium", ret.getString("biPremium"));
                biproposal.put("vehicleTaxPremium", null);
                proposals.add(biproposal);
                Map<String,Object> ciproposal=new LinkedHashMap<>();
                ciproposal.put("proposalNo", "T195105072016043176");
                ciproposal.put("policyNo", "1195105072016024036");
                ciproposal.put("riskCode", "0507");
                ciproposal.put("lastPolicyNo", null);
                ciproposal.put("insurancePremium", ret.getString("ciPremium"));
                ciproposal.put("vehicleTaxPremium", ret.getString("vehicleTaxPremium"));
                proposals.add(ciproposal);
                data.put("proposals", proposals);
                List<Map<String,Object>> agreements=new ArrayList<>();
                data.put("agreements", agreements);
                Map<String,Object> allMap=new LinkedHashMap<>();
                allMap.put("msg", null);
                allMap.put("sendTime", date);
                allMap.put("sign", null);
                allMap.put("data", data);

                String json = "";
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    json = objectMapper.writeValueAsString(allMap);
//                    System.out.println(json);
                    printJson(json);
                } catch (JsonProcessingException e1) {
                    System.out.println("json转换异常");
                }
            }//显示数据
            ret.close();
            close();//关闭连接
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
