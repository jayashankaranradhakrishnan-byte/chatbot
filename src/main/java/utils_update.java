import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.List;

//public class utils_update {
//    public static List<latestBotdata> readBotData(String filePath) throws Exception {
//        ObjectMapper mapper = new ObjectMapper();
//        return mapper.readValue(
//                new File(filePath),
//                new TypeReference<List<latestBotdata>>() {}
//        );
//    }
//
//    public static List<latestBotdata> readBotdata(String s) {
//        return List.of();
//    }

    public class utils_update {

        public static List<latestBotdata> readBotdata(String filePath) throws Exception {

            ObjectMapper mapper = new ObjectMapper();

            List<latestBotdata> data = mapper.readValue(
                    new File(filePath),
                    new TypeReference<List<latestBotdata>>() {}
            );

            System.out.println("Loaded Records = " + data.size());

            return data;
        }
    }
//}
