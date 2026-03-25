import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

public class link_check {

    public static void main(String[] args) throws Exception {

        String baseUrl = "http://192.168.0.28:5015/chat";

        // Load your test data JSON
        List<BotData> testData = JsonUtils.readBotData(
                "C:\\Users\\Ram prathees\\IdeaProjects\\chatbot\\src\\main\\java\\linkcheck.json"
        );

        SoftAssert softAssert = new SoftAssert(); // SoftAssert allows all checks to run

        for (BotData data : testData) {

            String question = data.getQuestion();
            List<String> expectedLinks = data.getExpectedLinks(); // DB expected links

            // Send request to chatbot
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

            // Get HTML response
            String htmlResponse = response.jsonPath().getString("response");

            // Parse all <a href="..."> links
            Document doc = Jsoup.parse(htmlResponse);
            Elements linkElements = doc.select("a[href]");
            List<String> actualLinks = new ArrayList<>();
            linkElements.forEach(link -> actualLinks.add(link.attr("href").trim()));

            // Compare actual vs expected links
            if (expectedLinks != null) {
                softAssert.assertEquals(
                        actualLinks,
                        expectedLinks,
                        "❌ Link mismatch for question: " + question
                );
            }

            // Logging for debugging
            System.out.println("Question: " + question);
            System.out.println("Actual Links  : " + actualLinks);
            System.out.println("Expected Links: " + expectedLinks);
            System.out.println("➡️ Links Test Completed\n");
        }

        // Report all failures at the END
        softAssert.assertAll();
    }
}
