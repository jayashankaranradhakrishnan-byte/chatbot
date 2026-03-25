import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.List;

public class Zerosbot {

    private static final String BASE_URL = "http://192.168.0.28:5000/ask";

    public static void main(String[] args) throws Exception {

        List<BotData> testData = JsonUtils.readBotData(
                "C:\\Users\\Ram prathees\\IdeaProjects\\chatbot\\src\\main\\java\\3.15_general.json"
        );

        System.out.println("🚀 Chatbot Execution Started...\n");

        for (BotData data : testData) {

            String question = data.getQuestion();

            try {
                // ✅ Correct Request Body
                String requestBody = "{ \"question\": \"" + question + "\", \"conversation_id\": null }";

                Response response = RestAssured
                        .given()
                        .contentType("application/json")
                        .body(requestBody)
                        .log().ifValidationFails()
                        .when()
                        .post(BASE_URL)
                        .then()
                        .extract()
                        .response();

                int statusCode = response.getStatusCode();

                System.out.println("Status: " + statusCode);

                if (statusCode != 200) {
                    System.out.println("❌ API FAILED for: " + question);
                    System.out.println("Response: " + response.asString());
                    continue;
                }

                // ✅ Print full response once (for debugging)
                String fullResponse = response.asString();
                System.out.println("RAW RESPONSE: " + fullResponse);

                // ⚠️ Adjust this field if needed
                String answer = response.jsonPath().getString("response");

                // fallback if null
                if (answer == null) {
                    answer = response.jsonPath().getString("answer");
                }

                answer = cleanHtml(answer);

                // ✅ FINAL OUTPUT
                System.out.println("=====================================");
                System.out.println("Q: " + question);
                System.out.println("A: " + answer);
                System.out.println("=====================================\n");

            } catch (Exception e) {
                System.out.println("❌ Error for question: " + question);
                e.printStackTrace();
            }
        }

        System.out.println("✅ Execution Completed");
    }

    private static String cleanHtml(String input) {
        if (input == null) return "";
        return input.replaceAll("<[^>]*>", "").trim();
    }
}


//check the question asked and responce is correct as per the qa point of view if any thing is mostly more irrelevant list that type question