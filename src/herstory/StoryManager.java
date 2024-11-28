package herstory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
    
    StoryManager(){
        
        storiesFile = new File("storiesFile.txt");
        
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
    }
    
}
