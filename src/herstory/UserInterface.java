package herstory;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;

//@author Gabriel Roman
//National College of Ireland
//x23155841

public class UserInterface extends JFrame implements ActionListener{

    Border border = BorderFactory.createLineBorder(Color.black);
    
    JFrame frame;
    
    ImageIcon appIcon;
    ImageIcon logo;
    
    //Landing page
    JLabel title;
    JLabel explanation;
    JButton checkStoriesButton;
    JButton addStoryButton;
    JButton backButton;
    
    //CheckStories Page
    JPanel storyPanel;
    JPanel storyPanel2;
    JPanel storyPanel3;
    JPanel storyPanel4;
    
    JLabel storyTitle;
    JLabel storyDescription;
    JButton readStory;
    JLabel storyTitle2;
    JLabel storyDescription2;
    JButton readStory2;
    JLabel storyTitle3;
    JLabel storyDescription3;
    JButton readStory3;
    JLabel storyTitle4;
    JLabel storyDescription4;
    JButton readStory4;
    
    //Stories
    JLabel story;
    JLabel story2;
    JLabel story3;
    JLabel story4;
    
    //AddStory Page
    JLabel addTitleLabel;
    JTextField addTitleField;
    
    JLabel addStoryLabel;
    JTextField addStoryField;
    
    JLabel addPictureLabel;
    JButton addPictureButton;
    JLabel previewLabel;
    
    JButton submitButton;
    
    UserInterface(){
        // Create and set up the frame
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("herStory");
        frame.setResizable(false);
        frame.setSize(500, 500);

        // Title Image
        logo = new ImageIcon(getClass().getResource("logo.png"));
        title = new JLabel(logo);
        title.setBounds(100,75,300,50);
        
        // Starter explanation text
        explanation = new JLabel();
        explanation.setText("<html>Welcome to HerStory, here you can find inspiring stories about women all around the world.<br>You can also add your own story or tell us about a woman that you think is inspiring.</html>");
        explanation.setBounds(75,150,325,100);
        
        // Check Stories button
        checkStoriesButton = new JButton();
        checkStoriesButton.setBounds(75,300,150,30);
        checkStoriesButton.setFocusable(false);
        checkStoriesButton.setText("Check Stories");
        checkStoriesButton.addActionListener(this);
        
        // Add Story button
        addStoryButton = new JButton();
        addStoryButton.setBounds(250,300,150,30);
        addStoryButton.setFocusable(false);
        addStoryButton.setText("Add a Story");
        addStoryButton.addActionListener(this);
        
        // Create icon for the frame
        appIcon = new ImageIcon(getClass().getResource("icon.png"));
        frame.setIconImage(appIcon.getImage());
        
        // Add back button but hide it
        backButton = new JButton();
        backButton.setBounds(15,420,100,30);
        backButton.setFocusable(false);
        backButton.setText("\u21A9 Back");
        backButton.addActionListener(this);
        backButton.setVisible(false);

        // Add components to frame
        frame.add(title);
        frame.add(explanation);
        frame.add(checkStoriesButton);
        frame.add(addStoryButton);
        frame.add(backButton);

        // Display the frame
        frame.setLayout(null);
        frame.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //Check Stories Button
        if(e.getSource()==checkStoriesButton){
            System.out.println("Check stories button pressed.");
            checkStories();
        }
        if(e.getSource()==addStoryButton){
            System.out.println("Add story button pressed.");
            addStory();
        }
        if(e.getSource()==backButton){
            System.out.println("Back button pressed.");
            goBack();
        }
        if(e.getSource()==readStory){
            System.out.println("Read story 1.");
            goToStory1();
        }
        if(e.getSource()==readStory2){
            System.out.println("Read story 2.");
            goToStory2();
        }
        if(e.getSource()==readStory3){
            System.out.println("Read story 3.");
            goToStory3();
        }
        if(e.getSource()==readStory4){
            System.out.println("Read story 4.");
            goToStory4();
        }
    }
    
    private void checkStories(){
        //Hide the other pages
        explanation.setVisible(false);
        checkStoriesButton.setVisible(false);
        addStoryButton.setVisible(false);
        
        //Show back button
        backButton.setVisible(true);
        
        // Move the title to the top
        title.setBounds(100,20,300,50);
        
        // Add story panels
        storyPanel = new JPanel();
        storyPanel.setBackground(Color.white);
        storyPanel.setBounds(50,100,380,50);
        storyPanel.setBorder(border);
        storyPanel.setLayout(null);
        
        storyPanel2 = new JPanel();
        storyPanel2.setBackground(Color.white);
        storyPanel2.setBounds(50,160,380,50);
        storyPanel2.setBorder(border);
        storyPanel2.setLayout(null);
        
        storyPanel3 = new JPanel();
        storyPanel3.setBackground(Color.white);
        storyPanel3.setBounds(50,220,380,50);
        storyPanel3.setBorder(border);
        storyPanel3.setLayout(null);
        
        storyPanel4 = new JPanel();
        storyPanel4.setBackground(Color.white);
        storyPanel4.setBounds(50,280,380,50);
        storyPanel4.setBorder(border);
        storyPanel4.setLayout(null);
        
        // Add stories within panels
        storyTitle = new JLabel();
        storyTitle.setText("Story 1");
        storyTitle.setBounds(10,10,100,15);
        storyDescription = new JLabel();
        storyDescription.setText("Description");
        storyDescription.setBounds(10,25,100,15);
        readStory = new JButton();
        readStory.setText("Read");
        readStory.setBounds(265,14,100,25);
        readStory.addActionListener(this);
        readStory.setFocusable(false);
        
        storyTitle2 = new JLabel();
        storyTitle2.setText("Story 2");
        storyTitle2.setBounds(10,10,100,15);
        storyDescription2 = new JLabel();
        storyDescription2.setText("Description");
        storyDescription2.setBounds(10,25,100,15);
        readStory2 = new JButton();
        readStory2.setText("Read");
        readStory2.setBounds(265,14,100,25);
        readStory2.addActionListener(this);
        readStory2.setFocusable(false);
        
        storyTitle3 = new JLabel();
        storyTitle3.setText("Story 3");
        storyTitle3.setBounds(10,10,100,15);
        storyDescription3 = new JLabel();
        storyDescription3.setText("Description");
        storyDescription3.setBounds(10,25,100,15);
        readStory3 = new JButton();
        readStory3.setText("Read");
        readStory3.setBounds(265,14,100,25);
        readStory3.addActionListener(this);
        readStory3.setFocusable(false);
        
        storyTitle4 = new JLabel();
        storyTitle4.setText("Story 4");
        storyTitle4.setBounds(10,10,100,15);
        storyDescription4 = new JLabel();
        storyDescription4.setText("Description");
        storyDescription4.setBounds(10,25,100,15);
        readStory4 = new JButton();
        readStory4.setText("Read");
        readStory4.setBounds(265,14,100,25);
        readStory4.addActionListener(this);
        readStory4.setFocusable(false);
        
        // Add stories to story panels
        storyPanel.add(storyTitle);
        storyPanel.add(storyDescription);
        storyPanel.add(readStory);
        
        storyPanel2.add(storyTitle2);
        storyPanel2.add(storyDescription2);
        storyPanel2.add(readStory2);
        
        storyPanel3.add(storyTitle3);
        storyPanel3.add(storyDescription3);
        storyPanel3.add(readStory3);
        
        storyPanel4.add(storyTitle4);
        storyPanel4.add(storyDescription4);
        storyPanel4.add(readStory4);
        
        // Add to frame
        frame.add(storyPanel);
        frame.add(storyPanel2);
        frame.add(storyPanel3);
        frame.add(storyPanel4);
        frame.repaint();
    }
    
    private void addStory(){
        //Hide the other pages
        explanation.setVisible(false);
        checkStoriesButton.setVisible(false);
        addStoryButton.setVisible(false);
        
        //Show back button
        backButton.setVisible(true);
        
        // Move the title to the top
        title.setBounds(100,20,300,50);
        
        // Add label
        addTitleLabel = new JLabel();
        addTitleLabel.setText("Name:");
        addTitleLabel.setBounds(50,100,100,15);
        
        addTitleField = new JTextField();
        addTitleField.setBounds(100,101,200,18);
        
        addStoryLabel = new JLabel();
        addStoryLabel.setText("Story:");
        addStoryLabel.setBounds(50,130,100,15);
        
        addStoryField = new JTextField();
        addStoryField.setBounds(100,131,300,150);
    
        addPictureLabel = new JLabel();
        addPictureLabel.setText("Add a photo:");
        addPictureLabel.setBounds(50,295,100,15);
        addPictureButton = new JButton();
        addPictureButton.setText("Select File");
        addPictureButton.setBounds(50,320,100,45);
        addPictureButton.setFocusable(false);
        
        previewLabel = new JLabel();
        previewLabel.setText("");
        
        submitButton = new JButton();
        submitButton.setText("Submit");
        submitButton.setBounds(370,420,100,30);
        submitButton.addActionListener(this);
        submitButton.setFocusable(false);
        
        // Add to frame
        frame.add(addTitleLabel);
        frame.add(addTitleField);
        frame.add(addStoryLabel);
        frame.add(addStoryField);
        frame.add(addPictureLabel);
        frame.add(addPictureButton);
        frame.add(previewLabel);
        frame.add(submitButton);
        frame.repaint();
    }
    
    private void goBack(){

        if(storyPanel != null && storyPanel.isVisible()) {
            storyPanel.setVisible(false);
            storyPanel2.setVisible(false);
            storyPanel3.setVisible(false);
            storyPanel4.setVisible(false);
            
            // Show landing page
            explanation.setVisible(true);
            checkStoriesButton.setVisible(true);
            addStoryButton.setVisible(true);
        
            // Hide back button
            backButton.setVisible(false);
        
            // Move the title back to landing page positon
            title.setBounds(100,75,300,50);
        }
        if(addTitleLabel != null && addTitleLabel.isVisible()){
            addTitleLabel.setVisible(false);
            addTitleField.setVisible(false);
            addStoryLabel.setVisible(false);
            addStoryField.setVisible(false);
            addPictureLabel.setVisible(false);
            addPictureButton.setVisible(false);
            previewLabel.setVisible(false);
            submitButton.setVisible(false);
            
            // Show landing page
            explanation.setVisible(true);
            checkStoriesButton.setVisible(true);
            addStoryButton.setVisible(true);
        
            // Hide back button
            backButton.setVisible(false);
        
            // Move the title back to landing page positon
            title.setBounds(100,75,300,50);
        }
        if(story != null && story.isVisible()){
            story.setVisible(false);
            storyPanel.add(storyTitle);
            storyTitle.setBounds(10,10,100,15);
            storyPanel.setVisible(true);
            storyPanel2.setVisible(true);
            storyPanel3.setVisible(true);
            storyPanel4.setVisible(true);
        }
        if(story2 != null && story2.isVisible()){
            story2.setVisible(false);
            storyPanel2.add(storyTitle2);
            storyTitle2.setBounds(10,10,100,15);
            storyPanel.setVisible(true);
            storyPanel2.setVisible(true);
            storyPanel3.setVisible(true);
            storyPanel4.setVisible(true);
        }
        if(story3 != null && story3.isVisible()){
            story3.setVisible(false);
            storyPanel3.add(storyTitle3);
            storyTitle3.setBounds(10,10,100,15);
            storyPanel.setVisible(true);
            storyPanel2.setVisible(true);
            storyPanel3.setVisible(true);
            storyPanel4.setVisible(true);
        }
        if(story4 != null && story4.isVisible()){
            story4.setVisible(false);
            storyPanel4.add(storyTitle4);
            storyTitle4.setBounds(10,10,100,15);
            storyPanel.setVisible(true);
            storyPanel2.setVisible(true);
            storyPanel3.setVisible(true);
            storyPanel4.setVisible(true);
        }
    }
    private void goToStory1(){
        // Hide panels
        if(storyPanel != null && storyPanel.isVisible()) {
            storyPanel.setVisible(false);
            storyPanel2.setVisible(false);
            storyPanel3.setVisible(false);
            storyPanel4.setVisible(false);
        }
        
        // Show story information
        frame.add(storyTitle);
        storyTitle.setVisible(true);
        storyTitle.setBounds(50,120,100,15);
        story = new JLabel();
        story.setText("Entire story 1 would show up here including picture.");
        story.setBounds(50,145,300,15);
        frame.add(story);
    }
    private void goToStory2(){
        // Hide panels
        if(storyPanel != null && storyPanel.isVisible()) {
            storyPanel.setVisible(false);
            storyPanel2.setVisible(false);
            storyPanel3.setVisible(false);
            storyPanel4.setVisible(false);
        }
        
        // Show story information
        frame.add(storyTitle2);
        storyTitle2.setVisible(true);
        storyTitle2.setBounds(50,120,100,15);
        story2 = new JLabel();
        story2.setText("Entire story 2 would show up here including picture.");
        story2.setBounds(50,145,300,15);
        frame.add(story2);
    }
    private void goToStory3(){
        // Hide panels
        if(storyPanel != null && storyPanel.isVisible()) {
            storyPanel.setVisible(false);
            storyPanel2.setVisible(false);
            storyPanel3.setVisible(false);
            storyPanel4.setVisible(false);
        }
        
        // Show story information
        frame.add(storyTitle3);
        storyTitle3.setVisible(true);
        storyTitle3.setBounds(50,120,100,15);
        story3 = new JLabel();
        story3.setText("Entire story 3 would show up here including picture.");
        story3.setBounds(50,145,300,15);
        frame.add(story3);
    }
    private void goToStory4(){
        // Hide panels
        if(storyPanel != null && storyPanel.isVisible()) {
            storyPanel.setVisible(false);
            storyPanel2.setVisible(false);
            storyPanel3.setVisible(false);
            storyPanel4.setVisible(false);
        }
        
        // Show story information
        frame.add(storyTitle4);
        storyTitle4.setVisible(true);
        storyTitle4.setBounds(50,120,100,15);
        story4 = new JLabel();
        story4.setText("Entire story 4 would show up here including picture.");
        story4.setBounds(50,145,300,15);
        frame.add(story4);
    }
}
