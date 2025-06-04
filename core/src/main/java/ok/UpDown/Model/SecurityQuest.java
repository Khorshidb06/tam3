package ok.UpDown.Model;

public enum SecurityQuest {


    MOTHER_BIRTH_CITY("What city was your mother born in?"),
    CHILDHOOD_DREAM_JOB("What was your dream job as a child?"),
    CHILDHOOD_CITY("What city did you grow up on?"),
    FIRST_BEST_FRIEND("What was the name of your first best friend?"),
    FAVORITE_CARTOON_CHARACTER("Who was your favorite cartoon character?"),
    FIRST_BOOK_READ("What was the first book you ever read?"),
    FIRST_VIDEO_GAME("What was the first video game you played?");
    ;

    private final String quest;

    SecurityQuest(String quest) {
        this.quest = quest;
    }

    public String getQuest() {
        return quest;
    }
}
