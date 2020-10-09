import cn.hutool.core.util.HexUtil;

import java.nio.charset.Charset;

/**
 * description: CRC16Util（环境212标准的crc16算法） <br>
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
    public static String h212CalcCrc16(String text) {
        byte[] data = text.getBytes();
        int crc = 0xffff;
        int dxs = 0xa001;
        int hibyte;
        int sbit;
        for (byte datum : data) {
            hibyte = crc >> 8;
            crc = hibyte ^ datum;
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
                code = String.format("0%s", code);
            }
        }
        return code;
    }

    /**
     * CRC-16/MODBUS 算法
     * @param str
     * @return
     */
    public static String getCRC_ModBus(String str) {
        //    CRC算法名称	多项式公式	        宽度	多项式	初始值	结果异或值	输入反转	输出反转
        //    CRC-16/MODBUS	x16 + x15 + x2 + 1	16	8005	FFFF	0000	    true	true
        /*ModBus 通信协议的 CRC ( 冗余循环校验码含2个字节, 即 16 位二进制数。
        CRC 码由发送设备计算, 放置于所发送信息帧的尾部。
        接收信息设备再重新计算所接收信息 (除 CRC 之外的部分）的 CRC,
        比较计算得到的 CRC 是否与接收到CRC相符, 如果两者不相符, 则认为数据出错。

        1) 预置 1 个 16 位的寄存器为十六进制FFFF(即全为 1) , 称此寄存器为 CRC寄存器。
        2) 把第一个 8 位二进制数据 (通信信息帧的第一个字节) 与 16 位的 CRC寄存器的低 8 位相异或, 把结果放于 CRC寄存器。
        3) 把 CRC 寄存器的内容右移一位( 朝低位)用 0 填补最高位, 并检查右移后的移出位。
        4) 如果移出位为 0, 重复第 3 步 ( 再次右移一位); 如果移出位为 1, CRC 寄存器与多项式A001 ( 1010 0000 0000 0001) 进行异或。
        5) 重复步骤 3 和步骤 4, 直到右移 8 次,这样整个8位数据全部进行了处理。
        6) 重复步骤 2 到步骤 5, 进行通信信息帧下一个字节的处理。
        8) 最后得到的 CRC寄存器内容即为 CRC码。*/
        char[] chars=new char[str.length()/2];
        str = str.toUpperCase();
        int index = 0;
        for (int i = 0; i < str.length(); i += 2) {
            String a = str.substring(i, i + 2);
            chars[index++] = (char) Integer.parseInt(a, 16);
        }
        int CRC = 0x0000ffff;
        int POLYNOMIAL = 0x0000a001;
        for (int anInt : chars) {
            CRC ^= anInt;
            for (int j = 0; j < 8; j++) {
                int t = CRC & 1;
                CRC >>= 1;
                if (t == 1) {
                    CRC ^= POLYNOMIAL;
                }
            }
        }
        return Integer.toHexString(CRC).toUpperCase();
    }


    public static void main(String[] args) {
        String a = "ST=32;CN=2011;PW=123456;MN=0871KMJKWSLH11;CP=&&DataTime=20201122155600;011-Rtd=05.000,011-Flag=N;060-Rtd=0.071,060-Flag=N;001-Rtd=7.081,001-Flag=N;B01-Rtd=775.833,B01-Flag=N;101-Rtd=0.023,101-Flag=N;065-Rtd=4.778,065-Flag=N&&";
//        String a = "ST=32;CN=2011;PW=123456;MN=0871KMJKWSLH08;CP=&&DataTime=20200931155600;011-Rtd=99.000,011-Flag=N;060-Rtd=9.071,060-Flag=N;001-Rtd=7.081,001-Flag=N;B01-Rtd=775.833,B01-Flag=N;101-Rtd=0.023,101-Flag=N;065-Rtd=4.778,065-Flag=N&&";
        String s = CRC16Util.h212CalcCrc16(a);
        System.out.println("##0225" + a + s);
//        String str = "7E7E010055667712A000310074020EE6200925020552F1F1005566771248F0F0200925010504180000052219392200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000003";
//        System.out.println(getCRC_ModBus(str));
    }


}
