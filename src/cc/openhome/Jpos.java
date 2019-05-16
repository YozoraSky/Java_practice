package cc.openhome;

import java.io.FileInputStream;
import java.io.InputStream;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.packager.GenericPackager;

public class Jpos {
    public static void main(String[] args) {
//    	Jpos iso = new Jpos();
//        try {
//            String message = iso.buildISOMessage();
//            System.out.printf("Message = %s", message);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        String s = "333";
        byte[] b = s.getBytes();
        for(int i=0;i<b.length;i++)
        	System.out.println(b[i]);
    }
	
    private String buildISOMessage() throws Exception {
        try {
            // Load package from resources directory.
            InputStream is = new FileInputStream("src/fields.xml");
            GenericPackager packager = new GenericPackager(is);
            ISOMsg isoMsg = new ISOMsg();
            String header = "ISO026000000";
            isoMsg.setPackager(packager);
            isoMsg.setHeader(header.getBytes());
            isoMsg.setMTI("0200");
            isoMsg.set(3, "010030");
            isoMsg.set(4, "000000100000");
            isoMsg.set(11, "003580");
            isoMsg.set(12, "103609");
            isoMsg.set(13, "1120");
            isoMsg.set(17, "1120");
            isoMsg.set(22, "010");
            isoMsg.set(32, "00000429339");
            isoMsg.set(35, "4563198301526508=2706                ");
            isoMsg.set(37, "103609003580");
            isoMsg.set(41, "88888889        ");
            isoMsg.set(48, "8220199700010      00000000");
            isoMsg.set(49, "901");
            isoMsg.set(52, "                ");
            isoMsg.set(61, "        031        ");
            isoMsg.set(63, "&X0000200048!XC000026X                   0 0    ");
            printISOMessage(isoMsg);
            byte[] result = isoMsg.pack();
            return new String(result);
        } catch (ISOException e) {
            throw new Exception(e);
        }
    }

    private void printISOMessage(ISOMsg isoMsg) {
        try {
            System.out.printf("MTI = %s%n", isoMsg.getMTI());
            for (int i = 1; i <= isoMsg.getMaxField(); i++) {
                if (isoMsg.hasField(i)) {
                    System.out.printf("Field (%s) = %s%n", i, isoMsg.getString(i));
                }
            }
        } catch (ISOException e) {
            e.printStackTrace();
        }
    }
}
