//import io.restassured.RestAssured;
//import io.restassured.response.Response;
//import org.testng.Assert;
//
//public class BotTest {
//
//    public static void main(String[] args) {
//
//        // Base URL
//        String baseUrl = "http://192.168.0.28:5025/chat";
//
//        // Question to send
//        String question = "I feel ashamed of my background sometimes. What should I do?";
//
//        // Expected answer (plain text, HTML removed)
//        String expectedAnswer = "That shame isn’t yours to carry, it’s been passed down or projected onto you.Your background deserves respect and pride. Start with the guided program Exploring Identity to understand how our sense of self is shaped. Then try the mini podcast Why Do I Feel Bad When Someone Criticizes My Country or Religion for deeper insights.";
//
//        // Send POST request as x-www-form-urlencoded
//        Response response = RestAssured
//                .given()
//                .contentType("application/x-www-form-urlencoded")
//                .formParam("message", question)
//                .when()
//                .post(baseUrl)
//                .then()
//                .statusCode(200)
//                .extract()
//                .response();
//
//        // Extract 'response' field from JSON
//        String htmlResponse = response.jsonPath().getString("response");
//
//        // Strip HTML tags
//        String actualAnswer = htmlResponse.replaceAll("<[^>]*>", "").trim();
//
//        // Print actual answer (optional)
//        System.out.println("Actual: " + actualAnswer);
//
//        // Assertion: exact match
//        Assert.assertEquals(actualAnswer, expectedAnswer, "Response does not match expected answer!");
//
//        System.out.println("Test Passed ✅");
//    }
//}

 //=========================================================================================================
//import io.restassured.RestAssured;
//import io.restassured.response.Response;
//import org.testng.Assert;
//import java.util.List;
//
//public class BotTest {
//
//    public static void main(String[] args) throws Exception {
//
//        String baseUrl = "http://65.0.250.241:5015/chat";
//
//        // 🔹 Read JSON data (NEW)
//        List<BotData> testData = JsonUtils.readBotData(
//                "C:\\Users\\Ram prathees\\IdeaProjects\\chatbot\\src\\main\\java\\pdf1_adult.json"
//        );
//
//        for (BotData data : testData) {
//
//            String question = data.getQuestion();
//            String expectedAnswer = data.getExpected_answer();
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
//            String htmlResponse = response.jsonPath().getString("response");
//            String actualAnswer = htmlResponse.replaceAll("<[^>]*>", "").trim();
//
//            System.out.println("Question: " + question);
//            System.out.println("Actual  : " + actualAnswer);
//            System.out.println("Expected: " + expectedAnswer);
//
//            Assert.assertEquals(
//                    actualAnswer,
//                    expectedAnswer,
//                    "❌ Mismatch for question: " + question
//            );
//
//            System.out.println("✅ Test Passed\n");
//        }
//    }
//}

//====================================================================================================================


//import io.restassured.RestAssured;
//import io.restassured.response.Response;
//import org.testng.Assert;
//import java.util.List;
//
//public class BotTest {
//
//    public static void main(String[] args) throws Exception {
//
//        String baseUrl = "http://65.0.250.241:5015/chat";
//
//        // 🔹 Read JSON data
//        List<BotData> testData = JsonUtils.readBotData(
//                "C:\\Users\\Ram prathees\\IdeaProjects\\chatbot\\src\\main\\java\\pdf1_adult.json"
//        );
//
//        for (BotData data : testData) {
//
//            String question = data.getQuestion();
//            String expectedAnswer = data.getExpected_answer();
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
//            String htmlResponse = response.jsonPath().getString("response");
//            String actualAnswer = htmlResponse.replaceAll("<[^>]*>", "").trim();
//
//            System.out.println("Question: " + question);
//            System.out.println("Actual  : " + actualAnswer);
//            System.out.println("Expected: " + expectedAnswer);
//
//            // ✅ NORMALIZED COMPARISON
//            Assert.assertEquals(
//                    normalize(actualAnswer),
//                    normalize(expectedAnswer),
//                    "❌ Mismatch for question: " + question
//            );
//
//            System.out.println("✅ Test Passed\n");
//        }
//    }
//
//    // 🔧 NORMALIZATION METHOD
//    private static String normalize(String text) {
//        if (text == null) return null;
//
//        return text
//                .replaceAll("[‘’]", "'")        // smart single quotes → '
//                .replaceAll("[“”]", "\"")       // smart double quotes → "
//                .replaceAll("\\s+", " ")        // normalize spaces
//                .replaceAll("\\s+([.,!?])", "$1") // space before punctuation
//                .trim();
//    }
//}

//============================================================================================================================


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;
import java.util.List;

public class BotTest {

    public static void main(String[] args) throws Exception {

        String baseUrl = "http://192.168.0.28:5015/chat";

        List<BotData> testData = JsonUtils.readBotData(
                "C:\\Users\\Ram prathees\\IdeaProjects\\chatbot\\src\\main\\java\\teennewall.json"
        );

        SoftAssert softAssert = new SoftAssert(); // ✅ SoftAssert

        for (BotData data : testData) {

            String question = data.getQuestion();
            String expectedAnswer = data.getExpected_answer();

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

            String htmlResponse = response.jsonPath().getString("response");
            String actualAnswer = htmlResponse.replaceAll("<[^>]*>", "").trim();

            System.out.println("Question: " + question);
            System.out.println("Actual  : " + actualAnswer);
            System.out.println("Expected: " + expectedAnswer);

            // ✅ Soft assertion (won't stop loop)
            softAssert.assertEquals(
                    normalize(actualAnswer),
                    normalize(expectedAnswer),
                    "❌ Mismatch for question: " + question
            );

            System.out.println("➡️ Test Completed (moving to next)\n");
        }

        // 🔴 Reports all failures at the END
        softAssert.assertAll();
    }

    private static String normalize(String text) {
        if (text == null) return null;

        return text
                .replaceAll("[‘’]", "'")
                .replaceAll("[“”]", "\"")
                .replaceAll("\\s+", " ")
                .replaceAll("\\s+([.,!?])", "$1")
                .trim();
    }
}



