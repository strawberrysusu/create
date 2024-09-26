public class BookPRACTICE {
    String title;
    String author;

    public BookPRACTICE(String t){
        title = t;
        author = "작자미상";
    }

    public BookPRACTICE(String t, String a){
        title = t;
        author = a;
    }

    public static void main(String[] args) {
        BookPRACTICE littlePrince = new BookPRACTICE("어린왕자", "생택쥐페리");

        BookPRACTICE loveStory = new BookPRACTICE("춘향전");
        System.out.println(littlePrince.title + " " + littlePrince.author);
        System.out.println(loveStory.title + " " + loveStory.author);
    
    }
}
