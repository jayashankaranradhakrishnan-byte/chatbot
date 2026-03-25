//public class BotData {
//    private String question;
//    private String expected_answer;
//
//    public String getQuestion() {
//        return question;
//    }
//
//    public String getExpected_answer() {
//        return expected_answer;
//    }
//}




import java.util.List;

public class BotData {
    private String question;
    private String expected_answer;
    private int id;


    public int getId() { return id; }


    // NEW: add this field for links
    private List<String> expectedLinks;

    // getters and setters

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getExpected_answer() {
        return expected_answer;
    }

    public void setExpected_answer(String expected_answer) {
        this.expected_answer = expected_answer;
    }

    // ✅ Getter for links
    public List<String> getExpectedLinks() {
        return expectedLinks;
    }

    // ✅ Setter for links
    public void setExpectedLinks(List<String> expectedLinks) {
        this.expectedLinks = expectedLinks;
    }
}
