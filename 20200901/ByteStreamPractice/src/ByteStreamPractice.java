import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteStreamPractice {
    File file0;
    File file1;
    FileInputStream fileInputStream;
    FileOutputStream fileOutputStream;

    ByteStreamPractice() throws IOException {
        file0 = new File("Test.txt");
        if (file0.createNewFile()) {
            FileOutputStream fis = new FileOutputStream(file0);
            String s = file0.getName() + "\r\n" + file0.getAbsolutePath() + "\r\n" + file0.lastModified();
            fis.write(s.getBytes());
            fis.close();
            System.out.println(s);
            fileInputStream = new FileInputStream(file0);
        }
        file1 = new File("TestCopy.txt");
        if (file1.createNewFile()) {
            fileOutputStream = new FileOutputStream(file1);
        }
        byte[] b = new byte[1024];
        int length;
        while ((length = fileInputStream.read(b)) != -1) {
            fileOutputStream.write(b,0,length);
        }
        fileOutputStream.close();
        fileInputStream.close();
    }

    public static void main(String [] args){
        try {
            new ByteStreamPractice();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
