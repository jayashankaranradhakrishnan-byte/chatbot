import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.List;

public class JsonUtils {

    public static List<BotData> readBotData(String filePath) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(
                new File(filePath),
                new TypeReference<List<BotData>>() {}
        );
    }
}
