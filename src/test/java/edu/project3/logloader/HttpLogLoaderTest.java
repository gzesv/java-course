package edu.project3.logloader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HttpLogLoaderTest {

    @Test
    void httpLogLoader_test(){
        String url =
            "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs";

        HttpLogLoader loader = new HttpLogLoader(url);
        loader.readLogs();
        List<String> records = loader.getRecords();

        assertThat(records).isNotNull();
        assertEquals(records.size(), 51462);
    }

    @Test
    void fileLogLoader_incorrectUrl_test() {
        HttpLogLoader loader = new HttpLogLoader("");;

        assertThrows(
            RuntimeException.class,
            loader::readLogs
        );
    }

}
