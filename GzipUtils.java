import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.zip.GZIPInputStream;

public class GzipUtils {
    public static String decompress(String compressed) throws IOException {
        byte[] compressedBytes = Base64.getDecoder().decode(compressed);
        ByteArrayInputStream bis = new ByteArrayInputStream(compressedBytes);
        GZIPInputStream gis = new GZIPInputStream(bis);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while (true) {
            try {
                if (!((len = gis.read(buffer)) > 0)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            bos.write(buffer, 0, len);
        }
        gis.close();
        bis.close();
        bos.close();
        return new String(bos.toByteArray(), "UTF-8");
    }
}
