import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class test2 {
    public static void main(String[] args) throws IOException {
        String data = "Hello, world!";
        byte[] compressedData = compress(data.getBytes());

        Base64.Encoder encoder = Base64.getEncoder();
        String encoded = encoder.encodeToString(compressedData);
        System.out.println(new String(compressedData));
        System.out.println(new String(encoded));

        Scanner ins =new Scanner(System.in);
        System.out.println("请输入:");
        String name = ins.nextLine();
//        Base64.Decoder decoder = Base64.getDecoder();
//        String decoded = new String(decoder.decode(name));
//        System.out.println(decoded);
        String decompressed = GzipUtils.decompress(name);
        System.out.println(decompressed);
//        byte[] decompressedData = decompress(decoded.getBytes(StandardCharsets.UTF_8));
//        System.out.println(new String(decompressedData));
    }

    private static byte[] compress(byte[] data) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GZIPOutputStream gzipOut = new GZIPOutputStream(baos);
        gzipOut.write(data);
        gzipOut.close();
        return baos.toByteArray();
    }

    private static byte[] decompress(byte[] compressedData) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(compressedData);
        GZIPInputStream gzipIn = new GZIPInputStream(bais);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = gzipIn.read(buffer)) > 0) {
            baos.write(buffer, 0, len);
        }
        gzipIn.close();
        baos.close();
        return baos.toByteArray();
    }
}
