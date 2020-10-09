import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import lombok.Data;
import lombok.ToString;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

/**
 * @author ZhangJiaKui
 * @classname SL651
 * @description TODO
 * @date 2020/9/28 13:56
 */
public class SL651 {
    private static Set<String> strings=new HashSet<>();
    private static final String RTD_TRAFFIC = "rtdTraffic";
    private static final String RTD_FLOW_RATE = "rtdFlowRate";
    private static final String COU_TRAFFIC = "couTraffic";
    private static final String RTD_WATER_LEVEL = "rtdWaterLevel";
    private static final String VOLTAGE = "voltage";
    private static final String PRESSURE = "pressure";


    public static Sl651MsgInfo parse_SL651(String message) {
        try {
            Sl651MsgInfo sl651MsgInfo = parseData(message);
            if (sl651MsgInfo != null) {
                sl651MsgInfo.setStationName(getStationName(sl651MsgInfo.getStationAddr()));
            }
            return sl651MsgInfo;
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 解析主函数
     *
     * @param msg 源报文内容
     * @return
     */
    public static Sl651MsgInfo parseData(String msg) {
        int len = msg.length();
        String crcInfo = msg.substring(len - 4);
        String dateInfo = msg.substring(0, len - 4);
        //crc校验
        if (!CRC16Util.getCRC_ModBus(dateInfo).equals(crcInfo)) {
            return null;
        }
        /**
         * 报头:
         * 7E7E: 帧起始符 2
         * 01: 中心站地址 1
         * 0055667712: 遥测站地址 5
         * A000: 通讯密码 2
         * 32: 遥测站定时报 1
         * 0034: 第一个字符0代表上行1代表下行,后三位代表报文字节数34==3*16+4=52个字节长度==104位长度 2
         * 总长度为13*2=16
         */
        String msgHead = msg.substring(0, 26);
        /**
         * 报尾
         * 03: 结束标识符 1
         * 78E5: CRC16校验码 2
         * 总长度 3*2=6
         */
        //剔除报文起始符02
        String mainBody = msg.substring(26 + 2, len - 6);
        Sl651MsgInfo sl651MsgInfo = new Sl651MsgInfo();
        //报文头解析
        parseHead(msgHead, sl651MsgInfo);
        //非定时报文及心跳报文 均做丢弃处理
        if (!"32".equals(sl651MsgInfo.getType()) && !"2F".equals(sl651MsgInfo.getType())) {
            return null;
        }
        //报文正文解析
        parseMainBody(mainBody, sl651MsgInfo);
        //判断该报文是否为心跳报
        sl651MsgInfo.setHeartBeat("2F".equalsIgnoreCase(sl651MsgInfo.getType()));
        return sl651MsgInfo;
    }

    /**
     * 报文头解析函数
     *
     * @param head         报文头
     * @param sl651MsgInfo 解析数据接收
     */
    public static void parseHead(String head, Sl651MsgInfo sl651MsgInfo) {
        int index = 4;
        sl651MsgInfo.setCenterAddr(head.substring(index, index + 2));
        index += 2;
        sl651MsgInfo.setStationAddr(head.substring(index, index + 10));
        strings.add(sl651MsgInfo.getStationAddr());
        index += 10;
        sl651MsgInfo.setPwd(head.substring(index, index + 4));
        index += 4;
        sl651MsgInfo.setType(head.substring(index, index + 2));
        index += 2;
        sl651MsgInfo.setUpload(head.charAt(index + 1) == '0');
        index += 1;
        sl651MsgInfo.setMainBodyLen(new BigInteger(head.substring(index, index + 3), 16).intValue() * 2);
    }

    /**
     * 报文正文解析
     *
     * @param mainBody     报文正文
     * @param sl651MsgInfo 解析数据接收
     */
    public static void parseMainBody(String mainBody, Sl651MsgInfo sl651MsgInfo) {
        int index = 0;
        //流水号
        sl651MsgInfo.setSerNum(mainBody.substring(index, index + 4));
        index += 4;
        //发报时间
        sl651MsgInfo.setDataTime(DateUtil.parse("20" + mainBody.substring(index, index + 12), "yyyyMMddHHmm"));
        index += 12;
        //心跳包到此结束,无其他信息
        if (index == mainBody.length()) {
            return;
        }
        Map<String, Object> mainBodyMap = sl651MsgInfo.getMainBody();
        index += 4;
        mainBodyMap.put("stationAddr", mainBody.substring(index, index + 10));
        index += 10;
        mainBodyMap.put("stationType", mainBody.substring(index, index + 2));
        index += 2;
        while (index < mainBody.length()) {
            String key;
            String tag = mainBody.substring(index, index + 2);
            index += 2;
            //观测时间做特殊处理
            if ("F0".equals(tag.toUpperCase())) {
                index += 2;
                key = "dataTime";
                DateTime dateTime = DateUtil.parse("20" + mainBody.substring(index, index + 10), "yyyyMMddHHmm");
                index += 10;
                mainBodyMap.put(key, dateTime);
                continue;
            }


            String tagTail = mainBody.substring(index, index + 2);
            index += 2;
            String tagTailInfo = hexToBinary(tagTail);
            //前五位代表字节数计算长度 字节数*2 ,后三位代表位数计算小数长度
            int len = Integer.parseInt(tagTailInfo.substring(0, 5), 2) * 2;
            int decLen = Integer.parseInt(tagTailInfo.substring(5), 2);
            //截取标志信息
            String tagMsg = mainBody.substring(index, index + len);
            tagMsg = tagMsg.substring(0, len - decLen) + "." + tagMsg.substring(len - decLen);
            index += len;
            //标识符 参考水温监测数据通信规约SL651-2014
            switch (tag) {
                case "27":
                    key = RTD_TRAFFIC;
                    break;
                case "30":
                    key = COU_TRAFFIC;
                    break;
                case "37":
                    key = RTD_FLOW_RATE;
                    break;
                case "39":
                    key = RTD_WATER_LEVEL;
                    break;
                case "38":
                    key = VOLTAGE;
                    break;
                case "FF":
                    key = PRESSURE;
                    break;
                default:
                    key = "";
            }
            mainBodyMap.put(key, Double.parseDouble(tagMsg));
        }
    }

    /**
     * 16进制转8进制,不满足8位补0
     *
     * @param hex
     * @return
     */
    public static String hexToBinary(String hex) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hex.length(); i++) {
            int v = Integer.parseInt(String.valueOf(hex.charAt(i)), 16);
            String s = Integer.toBinaryString(v);
            while (s.length() < 4) {
                s = String.format("0%s", s);
            }
            String tail4 = s.substring(s.length() - 4);
            sb.append(tail4);
        }
        return sb.toString();
    }

    /**
     * @param addr
     * @return
     */
    private static String getStationName(String addr) {
        Map<String, String> map = new HashMap<>();
        map.put("0055667720", "大洛羊");
        map.put("0055667721", "果林水库");
        map.put("0055667790", "石龙坝");
        map.put("0055667791", "七家村");
        map.put("0055667710", "洛龙河");
        map.put("0055667711", "王家营");
        map.put("0055667712", "连运大沟");
        map.put("0055667713", "铁路机械排水沟");
        map.put("0055667714", "大新册黑龙潭");
        map.put("0055667715", "海子排水沟");
        map.put("0055667716", "铜牛寺水库");
        map.put("0055667717", "海子奶场");
        map.put("0055667781", "倪家营SF1911005220");
        map.put("0055667782", "东鸳鸯沟");
        map.put("0055667783", "清水水库");
        map.put("0055667784", "大冲水库");
        map.put("0055667730", "石夹子泵站");
        map.put("0055667731", "云大西路");
        map.put("0055667760", "老坝闸");
        map.put("0055667761", "倪家营SF1912001805");
        map.put("0055667762", "西邑大沟");
        map.put("0055667763", "昆船排水沟");
        map.put("0055667764", "公家村");


        return map.getOrDefault(addr.trim(), "未知站点" + addr);
    }


    @Data
    @ToString
    static class Sl651MsgInfo {
        /**
         * 站点名称
         */
        String stationName;
        /**
         * 心跳报标志
         */
        Boolean heartBeat;

        /**
         * 中心站地址
         */
        String centerAddr;
        /**
         * 遥测站地址
         */
        String stationAddr;
        /**
         * 通讯密码
         */
        String pwd;
        /**
         * 报文类型
         */
        String type;
        /**
         * 是否上行
         */
        Boolean upload;
        /**
         * 报文正文长度
         */
        int mainBodyLen;

        //------------------以下属于正文内容

        /**
         * 报文流水号
         */
        String serNum;
        /**
         * 发报时间
         */
        Date dataTime;

        /**
         * 报文正文内容
         */
        Map<String, Object> mainBody = new HashMap<>();

    }

    public static void main(String[] args) throws IOException {
        Set<String> nameSet=new HashSet<>();
        Set<String> addrSet=new HashSet<>();
        String path = "C:\\Users\\10158\\Desktop\\nettyServer - 副本\\info\\a.log";
        FileReader fileReader=new FileReader(path);
        BufferedReader bufferedReader=new BufferedReader(fileReader);
        while (true){
            String s = bufferedReader.readLine();
            if (s==null){
                break;
            }
            Sl651MsgInfo sl651MsgInfo = parse_SL651(s);
            if (sl651MsgInfo!=null){
                System.out.println(sl651MsgInfo);
                nameSet.add(sl651MsgInfo.getStationName());
                addrSet.add(sl651MsgInfo.getStationAddr());
            }
        }
        System.out.println("-------------定时报文正常工作设备地址编号---");
        for (String s : addrSet) {
            System.out.println(s+"------"+getStationName(s));
        }
        System.out.println("------------------所有发报设备----------------");
        for (String string : strings) {
            System.out.println(string+"---"+getStationName(string));
        }
    }
}
