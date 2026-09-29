import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.List;
import java.util.regex.Matcher;
import   java.util.regex.Pattern;

public class updated_bot {

    public static void main(String[] args) throws Exception {

//      String baseUrl = "http://13.134.43.106:5015/chat";

        String baseUrl = "https://demo.sustverse.sustally.com/api/chat";

        List<latestBotdata> testData = utils_update.readBotdata(
                "C:\\Users\\Ram prathees\\IdeaProjects\\chatbot\\src\\main\\java\\sustaly.json"
        );

        System.out.println("Total Questions : " + testData.size());

        for (latestBotdata data : testData) {

            String question = data.getQuestion();

            Response response = RestAssured
                    .given()
                    .contentType("application/x-www-form-urlencoded")
                    .formParam("message", question)
                    .when()
                    .post(baseUrl)
                    .then()
                    .statusCode(200)
                    .extract()
                    .response();

            String rawResponse = response.asString();

            String finalResponse = extractTokens(rawResponse);

            System.out.println("\n" + "=".repeat(120));

            System.out.println("Question ID : " + data.getId());
            System.out.println("Question    : " + question);

            System.out.println("-".repeat(120));

            System.out.println("Response    :");
            System.out.println();
            System.out.println(finalResponse);

            System.out.println("=".repeat(120) + "\n");

            Thread.sleep(9000);
        }
    }

    private static String extractTokens(String response) {

        StringBuilder answer = new StringBuilder();

        Pattern pattern = Pattern.compile("\"token\"\\s*:\\s*\"(.*?)\"");
        Matcher matcher = pattern.matcher(response);

        while (matcher.find()) {
            answer.append(matcher.group(1));
        }

        String finalText = answer.toString()
                .replace("\\n", "\n")
                .replace("\\t", "\t")
                .replace("\\u2014", "—")
                .replace("\\\"", "\"")
                .replace("\\/", "/")
                .replaceAll("<[^>]*>", "") // remove HTML tags
                .trim();
        if (finalText.contains("<div")) {
            finalText = finalText.substring(0, finalText.indexOf("<div"));
        }

        return finalText.trim();

    }


}



//=========================================================================================================

// teen
//
//import io.restassured.RestAssured;
//import io.restassured.response.Response;
//import org.json.JSONObject;
//
//import java.util.List;
//
//public class updated_bot {
//
//    public static void main(String[] args) throws Exception {
//
//        String baseUrl = "http://13.134.43.106:5015/chat";
//
//        List<latestBotdata> testData = utils_update.readBotdata(
//                "C:\\Users\\Ram prathees\\IdeaProjects\\chatbot\\src\\main\\java\\consistcheck.json"
//        );
//
//        System.out.println("Total Questions : " + testData.size());
//
//        for (latestBotdata data : testData) {
//
//            String question = data.getQuestion();
//
//            Response response = RestAssured
//                    .given()
//                    .contentType("application/x-www-form-urlencoded")
//                    .formParam("message", question)
//                    .when()
//                    .post(baseUrl)
//                    .then()
//                    .statusCode(200)
//                    .extract()
//                    .response();
//
//            String rawResponse = response.asString();
//
//            String finalResponse = extractResponse(rawResponse);
//
//            System.out.println("\n" + "=".repeat(120));
//            System.out.println("Question ID : " + data.getId());
//            System.out.println("Question    : " + question);
//            System.out.println("-".repeat(120));
//            System.out.println("Response    :\n");
//            System.out.println(finalResponse);
//            System.out.println("=".repeat(120) + "\n");
//
//            Thread.sleep(9000);
//        }
//    }
//
//    private static String extractResponse(String rawResponse) {
//
//        try {
//
//            JSONObject json = new JSONObject(rawResponse);
//
//            String responseText = json.optString("response", "");
//
//            return responseText
//                    .replaceAll("<[^>]*>", "")      // Remove HTML tags
//                    .replace("&nbsp;", " ")
//                    .replace("&amp;", "&")
//                    .replace("&lt;", "<")
//                    .replace("&gt;", ">")
//                    .replace("\\n", "\n")
//                    .replace("\\t", "\t")
//                    .trim();
//
//        } catch (Exception e) {
//
//            System.out.println("Failed to parse JSON response.");
//            System.out.println("Raw Response: " + rawResponse);
//
//            return rawResponse;
//        }
//    }
//}