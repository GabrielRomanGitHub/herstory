package herstory;

//@author Gabriel Roman
//National College of Ireland
//x23155841

public class Story {
    private String name;
    private String story;
    private String imagePath;
    
    public Story(String name, String story, String imagePath){
        this.name = name;
        this.story = story;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public String getStory() {
        return story;
    }

    public String getImagePath() {
        return imagePath;
    }
}
