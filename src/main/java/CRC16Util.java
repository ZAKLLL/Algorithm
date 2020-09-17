/**
 * description: CRC16Util（环境212标准的crc16算法） <br>
 * date: 2020/7/18 8:46 <br>
 * author: licheng <br>
 * version: 1.0 <br>
 */
public class CRC16Util {

    /**
     * description: 环境212标准的crc16算法 <br>
     * version: 1.0 <br>
     * date: 2020/7/18 8:47 <br>
     * author: objcat <br>
     *
     * @param text 协议报文内的data部分
     * @return java.lang.String
     */
    public static String calcCrc16(String text) {
        byte[] data = text.getBytes();
        int crc = 0xffff;
        int dxs = 0xa001;
        int hibyte;
        int sbit;
        for (int i = 0; i < data.length; i++) {
            hibyte = crc >> 8;
            crc = hibyte ^ data[i];

            for (int j = 0; j < 8; j++) {
                sbit = crc & 0x0001;
                crc = crc >> 1;
                if (sbit == 1)
                    crc ^= dxs;
            }
        }
        String code = Integer.toHexString(crc & 0xffff);
        //不够4位，则在前面补0
        if (code.length() < 4) {
            int lenDiff = 4 - code.length();
            for (int i = 0; i < lenDiff; i++) {
                code = "0" + code;
            }
        }
        return code;
    }

    public static void main(String[] args) {
        String a = "ST=32;CN=2011;PW=123456;MN=0871KMJKWSLH11;CP=&&DataTime=20201122155600;011-Rtd=05.000,011-Flag=N;060-Rtd=0.071,060-Flag=N;001-Rtd=7.081,001-Flag=N;B01-Rtd=775.833,B01-Flag=N;101-Rtd=0.023,101-Flag=N;065-Rtd=4.778,065-Flag=N&&";
//        String a = "ST=32;CN=2011;PW=123456;MN=0871KMJKWSLH08;CP=&&DataTime=20200931155600;011-Rtd=99.000,011-Flag=N;060-Rtd=9.071,060-Flag=N;001-Rtd=7.081,001-Flag=N;B01-Rtd=775.833,B01-Flag=N;101-Rtd=0.023,101-Flag=N;065-Rtd=4.778,065-Flag=N&&";
        String s = CRC16Util.calcCrc16(a);
        System.out.println("##0225"+a + s);

    }
}
