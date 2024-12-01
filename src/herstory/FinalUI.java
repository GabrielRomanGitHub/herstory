package herstory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;
import javax.swing.border.Border;

//@author Gabriel Roman
//National College of Ireland
//x23155841

public class FinalUI extends JFrame implements ActionListener{
    //Border to check placement issues
    private Border border = BorderFactory.createLineBorder(Color.black);
    
    //Get the storyManager
    private StoryManager storyManager;
    
    //Check current Panel
    private String currentPanel;
    
    //Constructor
    private JFrame frame;
    
    //Create All Panels
    private JPanel landingPanel;
    private JPanel storiesPanel;
    private JPanel addStoryPanel;
    private JPanel storyUploadedPanel;
    private ImageIcon logo;
    private JLabel title;
    private JButton storiesButton;
    private JButton addStoryButton;
    private JButton backButton;
    
    //AddStoryPanel
    private JLabel addTitleLabel;
    private JTextField addTitleField;
    private JLabel addStoryLabel;
    private JTextArea addStoryTextArea;
    private JScrollPane addStoryScrollPane;
    private JLabel addPictureLabel;
    private JButton addPictureButton;
    private JLabel previewLabel;
    private JButton submitButton;
    private File selectedImageFile;
    
    //StoryUploadedPanel
    private JLabel uploadMessage;
    
    //StoriesPanel
    private JScrollPane storiesScrollPane;
    private JPanel mainStoriesPanel;
    private JPanel individualStoryPanel;
    private int newHeight;
    private JButton readStoryButton;
    private JLabel nameLabel;
    private JLabel storyLabel;
    
    //ShowStoryPanel
    private JPanel showStoryPanel;
    private JLabel showStoryNameLabel;
    private JLabel showStoryTextLabel;
    private ImageIcon showImageIcon;
    private JLabel showImageLabel;
    
    FinalUI(StoryManager storyManager){
        //Get the storyManager
        this.storyManager = storyManager;
        storyManager.readAndUpdateStories();
        
        //Create main JFrame
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("herStory");
        frame.setResizable(false);
        frame.setSize(500, 500);
        
        //Create back button
        backButton = new JButton();
        backButton.setBounds(15,420,100,30);
        backButton.setFocusable(false);
        backButton.setText("\u21A9 Back");
        backButton.addActionListener(this);
        
        // Create icon for the frame
        ImageIcon appIcon = new ImageIcon(getClass().getResource("icon.png"));
        frame.setIconImage(appIcon.getImage());
        
        //Display frame
        frame.setLayout(null);
        frame.setVisible(true);
        
        createAllPanels();
        goToLandingPanel();
    }
    
    private void createAllPanels(){
        //---------- Create landing JPanel ----------
        landingPanel = new JPanel();
        landingPanel.setBounds(0,0,500,500);
        landingPanel.setLayout(null);
        
        //Title Image
        logo = new ImageIcon(getClass().getResource("logo.png"));
        title = new JLabel(logo);
        title.setBounds(100,75,300,50);
        landingPanel.add(title);
        
        JLabel explanation = new JLabel();
        explanation.setText("<html>Welcome to HerStory, here you can find inspiring stories about women all around the world.<br>You can also add your own story or tell us about a woman that you think is inspiring.</html>");
        explanation.setBounds(75,150,325,100);
        landingPanel.add(explanation);
        
        storiesButton = new JButton();
        storiesButton.setBounds(75,300,150,30);
        storiesButton.setFocusable(false);
        storiesButton.setText("Check Stories");
        storiesButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                goToStoriesPanel();
            }
        });
        landingPanel.add(storiesButton);
        
        addStoryButton = new JButton();
        addStoryButton.setBounds(250,300,150,30);
        addStoryButton.setFocusable(false);
        addStoryButton.setText("Add a Story");
        addStoryButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                goToAddStoryPanel();
            }
        });
        landingPanel.add(addStoryButton);
        
        //---------- Create add story JPanel ----------
        addStoryPanel = new JPanel();
        addStoryPanel.setBounds(0,0,500,500);
        addStoryPanel.setLayout(null);
        
        addTitleLabel = new JLabel();
        addTitleLabel.setText("Name:");
        addTitleLabel.setBounds(50,100,100,15);
        addStoryPanel.add(addTitleLabel);
        
        addTitleField = new JTextField();
        addTitleField.setBounds(100,101,200,18);
        addStoryPanel.add(addTitleField);
        
        addStoryLabel = new JLabel();
        addStoryLabel.setText("Story:");
        addStoryLabel.setBounds(50,130,100,15);
        addStoryPanel.add(addStoryLabel);
        
        addStoryTextArea = new JTextArea();
        addStoryTextArea.setLineWrap(true);
        addStoryTextArea.setWrapStyleWord(true);
        addStoryScrollPane = new JScrollPane(addStoryTextArea);
        addStoryScrollPane.setBounds(100,131,300,150); 
        addStoryPanel.add(addStoryScrollPane);
        
        addPictureLabel = new JLabel();
        addPictureLabel.setText("Add a photo:");
        addPictureLabel.setBounds(50,295,100,15);
        addStoryPanel.add(addPictureLabel);
        
        addPictureButton = new JButton();
        addPictureButton.setText("Select File");
        addPictureButton.setBounds(50,320,100,45);
        addPictureButton.setFocusable(false);
        addPictureButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Select Image");
                fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image files","jpg","png"));
            
                int result = fileChooser.showOpenDialog(null);
                
                if (result == JFileChooser.APPROVE_OPTION){
                    selectedImageFile = fileChooser.getSelectedFile();
                    previewLabel.setText("Selected Image: "+selectedImageFile.getName());
                }
            }
        });
        
        addStoryPanel.add(addPictureButton);
        
        previewLabel = new JLabel();
        previewLabel.setText("Selected Image: None.");
        previewLabel.setBounds(50,360,150,35);
        addStoryPanel.add(previewLabel);
        
        submitButton = new JButton();
        submitButton.setText("Submit");
        submitButton.setBounds(370,420,100,30);
        submitButton.setFocusable(false);
        submitButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String name = addTitleField.getText();
                String story = addStoryTextArea.getText();
            
                if (name.isEmpty()||story.isEmpty()||selectedImageFile==null){
                    JOptionPane.showMessageDialog(null,"Fill all fields and then try to submit again.");
                    return;
                }
            
                storyManager.copyImageToFolder(selectedImageFile);
                storyManager.addStory(name, story);

                goToStoryUploadedPanel();
                
                addTitleField.setText("");
                addStoryTextArea.setText("");
                selectedImageFile = null;
                previewLabel.setText("Selected Image: None.");
            }
        });
        addStoryPanel.add(submitButton);
        
        //---------- Create story uploaded JPanel ----------
        storyUploadedPanel = new JPanel();
        storyUploadedPanel.setBounds(0,0,500,500);
        storyUploadedPanel.setLayout(null);
        
        uploadMessage = new JLabel();
        uploadMessage.setText("Your story was succesfully uploaded.");
        uploadMessage.setBounds(140,200,250,35);
        storyUploadedPanel.add(uploadMessage);
        
        //---------- Create stories JPanel ----------
        storiesPanel = new JPanel();
        storiesPanel.setBounds(0,0,500,500);
        storiesPanel.setLayout(null);
        
        mainStoriesPanel = new JPanel();
        mainStoriesPanel.setLayout(null);
        storiesScrollPane = new JScrollPane(mainStoriesPanel);
        storiesScrollPane.setBounds(45,50,400,300);
        storiesScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        storiesPanel.add(storiesScrollPane);
        
        //---------- Create show story JPanel ----------
        showStoryPanel = new JPanel();
        showStoryPanel.setBounds(0,0,500,500);
        showStoryPanel.setLayout(null);
        
        showStoryNameLabel = new JLabel();
        showStoryNameLabel.setBounds(45,50,100,15);
        showStoryPanel.add(showStoryNameLabel);
        
        showStoryTextLabel = new JLabel();
        showStoryTextLabel.setVerticalAlignment(SwingConstants.TOP);
        showStoryTextLabel.setBounds(45,85,250,250);
        showStoryPanel.add(showStoryTextLabel);
        
        showImageIcon = new ImageIcon();
        
        showImageLabel = new JLabel(showImageIcon);
        showImageLabel.setBounds(325,85,100,125);
        showStoryPanel.add(showImageLabel);
    }
    
    private void goToLandingPanel(){
        title.setBounds(100,75,300,50);
        
        landingPanel.add(title);
        frame.add(landingPanel);
        frame.repaint();
        currentPanel = "landingPanel";
    }
    
    private void goToStoriesPanel(){
        frame.remove(landingPanel);
        
        newHeight = 0;
        
        for(int i = 0; i <= storyManager.getNumOfStories()-1; i++){
            
            Story currentStory = storyManager.getStoryFrom(i);
            
            if(currentStory != null){
                individualStoryPanel = new JPanel();
                individualStoryPanel.setBounds(0,i*50,400,50);
                
                individualStoryPanel.setBorder(border);
                individualStoryPanel.setLayout(null);
                
                nameLabel = new JLabel(currentStory.getName());
                nameLabel.setBounds(15,10,260,15);
                
                storyLabel = new JLabel(currentStory.getStory());
                storyLabel.setBounds(15,25,260,15);
                
                readStoryButton = new JButton("Read");
                readStoryButton.setBounds(300,14,75,25);
                readStoryButton.addActionListener(e -> goToShowStoryPanel(currentStory));
                System.out.println("Story added.");
                
                individualStoryPanel.add(nameLabel);
                individualStoryPanel.add(storyLabel);
                individualStoryPanel.add(readStoryButton);
                
                mainStoriesPanel.add(individualStoryPanel);
            }  
        }
        
        newHeight = (storyManager.getNumOfStories())*50;
        mainStoriesPanel.setPreferredSize(new Dimension (380, newHeight));
        storiesScrollPane.getViewport().setViewSize(new Dimension(380, newHeight));
        
        mainStoriesPanel.revalidate();
        mainStoriesPanel.repaint();
        
        storiesScrollPane.revalidate();
        storiesScrollPane.repaint();
        
        storiesPanel.revalidate();
        storiesPanel.repaint();
        
        storiesPanel.add(backButton);
        frame.add(storiesPanel);
        frame.repaint();
        
        currentPanel = "storiesPanel";
    }
    
    private void goToShowStoryPanel(Story story){
        frame.remove(storiesPanel);
        
        System.out.println("showing story.");
        
        currentPanel = "showStory";
        
        showStoryNameLabel.setText(story.getName());
        
        showStoryTextLabel.setText("<html><body>"+story.getStory()+"</body></html>");

        showImageIcon.setImage(new ImageIcon(story.getImagePath()).getImage());
        Image resizedImage = showImageIcon.getImage();
        
        showImageIcon.setImage(resizedImage.getScaledInstance(100, 125, Image.SCALE_SMOOTH));
        
        showStoryPanel.add(backButton);
        frame.add(showStoryPanel);
        frame.repaint();
        
        currentPanel = "showStoryPanel";
    }
    
    private void goToAddStoryPanel(){
        frame.remove(landingPanel);
        
        // Move the title to the top
        title.setBounds(100,20,300,50);
        
        addStoryPanel.add(backButton);
        addStoryPanel.add(title);
        
        frame.add(addStoryPanel);
        frame.repaint();
        
        addStoryTextArea.revalidate();
        addStoryTextArea.repaint();
        
        currentPanel = "addStoryPanel";
    }
    
    private void goToStoryUploadedPanel(){
        frame.remove(addStoryPanel);
        
        title.setBounds(100,20,300,50);
        
        storyUploadedPanel.add(backButton);
        storyUploadedPanel.add(title);
        
        frame.add(storyUploadedPanel);
        frame.repaint();
        
        currentPanel = "storyUploadedPanel";
    }
    
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==backButton){
            if(currentPanel=="storiesPanel"){
                frame.remove(storiesPanel);
                individualStoryPanel = null;
                
                goToLandingPanel();
                currentPanel = "landingPanel";
            }
            if(currentPanel=="showStoryPanel"){
                frame.remove(showStoryPanel);
                
                goToStoriesPanel();
                currentPanel = "storiesPanel";
            }
            if(currentPanel=="addStoryPanel"){
                frame.remove(addStoryPanel);
                
                goToLandingPanel();
                currentPanel = "landingPanel";
            }
            if(currentPanel=="storyUploadedPanel"){
                frame.remove(storyUploadedPanel);
                
                goToAddStoryPanel();
                currentPanel = "addStoryPanel";
            }
        }
    }
}
