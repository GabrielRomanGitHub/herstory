package herstory;

import java.util.List;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

//@author Gabriel Roman
//National College of Ireland
//x23155841

public class StoryManager {
    
    private File storiesFile;
    private File storyImagesFolder;
    private String imagePath;
    private List<Story> storyList;
    
    StoryManager(){
        
        storiesFile = new File("storiesFile.txt");
        storyList = new ArrayList<>();
        
        try{
            if(storiesFile.createNewFile()){
                System.out.println("File created: " + storiesFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e){
            System.out.println("An error happened while trying to create the file.");
            e.printStackTrace();
        }
        
        storyImagesFolder = new File("storyImagesFolder");
        
        if (!storyImagesFolder.exists()){
            storyImagesFolder.mkdir();
            System.out.println("Images folder created.");
        } else if (storyImagesFolder.exists()){
            System.out.println("Images folder already exists.");
        }
        
        
    }
    
    public void copyImageToFolder(File selectedFile){
        File destinationFile = new File(storyImagesFolder, selectedFile.getName());
        
        try{
            Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Image: "+selectedFile.getAbsolutePath()+"\nWas copied to: "+destinationFile.getAbsolutePath());
            imagePath = "storyImagesFolder/"+selectedFile.getName();
        } catch (IOException e){
            System.out.println("An error occurred while trying to copy the image to storyImagesFolder");
            e.printStackTrace();
        }
    
    }
    
    public void addStory(String name, String story){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(storiesFile,true))){
            String storyEntry = name + ".%!@" + story + ".%!@" + imagePath + ".%!@END";
            writer.write(storyEntry);
            writer.newLine();
            System.out.println("Story from "+name+" was added succesfully.");
        } catch (IOException e){
            System.out.println("An error ocurred while trying to add the story.");
            e.printStackTrace();
        }
        storyList.add(new Story(name, story, imagePath));
    }
    
    public void readAndUpdateStories(){
        storyList.clear();
        try(BufferedReader reader = new BufferedReader(new FileReader(storiesFile))){
            
            StringBuilder fileContent = new StringBuilder();
            String currentLine;
            
            while((currentLine = reader.readLine()) != null){
                fileContent.append(currentLine).append("\n");
            }
            
            String fullText = fileContent.toString();
            
            String[] stories = fullText.split("\\.%!@END\n");
            System.out.println("Number of stories: "+stories.length);
            for (String story : stories){
                System.out.println("Story section: "+ story);
            }
            
            for (String story : stories){   

                story = story.trim();

                if (story.length() > 0){
                    String[] section = story.split("\\.%!@", 3);

                    if (section.length == 3){
                        String readName = section[0].trim();
                        String readStory = section[1].trim();
                        String readImagePath = section[2].trim();

                        storyList.add(new Story(readName, readStory, readImagePath));
                    } else {
                        System.out.println("Unable to get story from storiesFile.txt");
                    }
                }
            }
        } catch (IOException e){
            System.out.println("An error occurred while trying to read stories from the storiesFile.txt");
            e.printStackTrace();
        }
    }
    
    public List<Story> getStories(){
        return storyList;
    }
    
    public int getNumOfStories(){
        return storyList.size();
    }
    
    public Story getStoryFrom(int index){
        if (index >= 0 && index < storyList.size()){
            return storyList.get(index);
        } else {
            System.out.println("No stories contain this index.");
            return null;
        }
    }
    
}
