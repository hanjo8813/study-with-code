import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class test {

    @Test
    void test(){
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
//        String format = simpleDateFormat.format(now);
//        System.out.println(now);
    }
}
