package herstory;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;
import javax.swing.border.Border;

//@author Gabriel Roman
//National College of Ireland
//x23155841

public class UpdatedUI extends JFrame implements ActionListener{
    //Border to check placement issues
    private Border border = BorderFactory.createLineBorder(Color.black);
    
    //Get the storyManager
    private StoryManager storyManager; 
    
    //Check current Panel
    
    private String currentPanel;
    
    //UpdateUI
    private JFrame frame;
    private JPanel landingPanel;
    private JPanel storiesPanel;
    private JPanel viewStoryPanel;
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
    
    
    UpdatedUI(StoryManager storyManager){
        //Get the storyManager
        this.storyManager = storyManager;
        
        //Create main JFrame
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("herStory");
        frame.setResizable(false);
        frame.setSize(500, 500);
        
        //Create landing JPanel
        landingPanel = new JPanel();
        landingPanel.setBounds(0,0,500,500);
        landingPanel.setLayout(null);
        frame.add(landingPanel);
        
        //Create stories JPanel
        storiesPanel = new JPanel();
        storiesPanel.setBounds(0,0,500,500);
        storiesPanel.setLayout(null);
        frame.add(storiesPanel);
        
        //Create viewStories JPanel
        viewStoryPanel = new JPanel();
        viewStoryPanel.setBounds(0,0,500,500);
        viewStoryPanel.setLayout(null);
        frame.add(viewStoryPanel);
        
        //Create addStory JPanel
        addStoryPanel = new JPanel();
        addStoryPanel.setBounds(0,0,500,500);
        addStoryPanel.setLayout(null);
        frame.add(addStoryPanel);
        
        //Create storyUploaded JPanel
        storyUploadedPanel = new JPanel();
        storyUploadedPanel.setBounds(0,0,500,500);
        storyUploadedPanel.setLayout(null);
        frame.add(storyUploadedPanel);
        
        //Display only landingPanel first
        storiesPanel.setVisible(false);
        viewStoryPanel.setVisible(false);
        addStoryPanel.setVisible(false);
        storyUploadedPanel.setVisible(false);
        
        //-------------------- Set up landingPanel --------------------
        
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
        storiesButton.addActionListener(this);
        landingPanel.add(storiesButton);
        
        addStoryButton = new JButton();
        addStoryButton.setBounds(250,300,150,30);
        addStoryButton.setFocusable(false);
        addStoryButton.setText("Add a Story");
        addStoryButton.addActionListener(this);
        landingPanel.add(addStoryButton);
        
        //-------------------- Set up AddStoryPanel --------------------
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

                currentPanel = "storyUploadedPanel";
                System.out.println("Moving to StoryUploadedPanel.");
                updatePanels();
                
                addTitleField.setText("");
                addStoryTextArea.setText("");
                selectedImageFile = null;
                previewLabel.setText("Selected Image: None.");
            }
        });
        addStoryPanel.add(submitButton);
        
        //-------------------- Set up StoryUploadedPanel --------------------
        uploadMessage = new JLabel();
        uploadMessage.setText("Your story was succesfully uploaded.");
        uploadMessage.setBounds(140,200,250,35);
        storyUploadedPanel.add(uploadMessage);
        
        // Add back button to necessary panels
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
    }
    
    private void updateLandingPanel(){
        title.setBounds(100,75,300,50);
        landingPanel.add(title);
    }
    
    private void updateStoriesPanel(){
        storiesPanel.add(backButton);
    }
    
    private void updateAddStoryPanel(){
        addStoryPanel.add(backButton);
        
        // Move the title to the top
        title.setBounds(100,20,300,50);
        addStoryPanel.add(title);
    }
    
    private void updateStoryUploadedPanel(){
        storyUploadedPanel.add(backButton);
    }
    
    private void updatePanels(){
        //Set all panels to not visible and show the required panel only
        landingPanel.setVisible(false);
        storiesPanel.setVisible(false);
        viewStoryPanel.setVisible(false);
        addStoryPanel.setVisible(false);
        storyUploadedPanel.setVisible(false);
        
        if(currentPanel.equals("landingPanel")){
            updateLandingPanel();
            landingPanel.setVisible(true);
            System.out.println("landingPanel has been set to visible.");
        }
        if(currentPanel.equals("storiesPanel")){
            updateStoriesPanel();
            storiesPanel.setVisible(true);
            System.out.println("storiesPanel has been set to visible.");
        }
        if(currentPanel.equals("viewStoryPanel")){
            viewStoryPanel.setVisible(true);
        }
        if(currentPanel.equals("addStoryPanel")){
            updateAddStoryPanel();
            addStoryPanel.setVisible(true);
        }
        if(currentPanel.equals("storyUploadedPanel")){
            updateStoryUploadedPanel();
            storyUploadedPanel.setVisible(true);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==storiesButton){
            currentPanel = "storiesPanel";
            System.out.println("Moving to storiesPanel.");
            updatePanels();
        }
        if(e.getSource()==addStoryButton){
            currentPanel = "addStoryPanel";
            System.out.println("Moving to addStoryPanel.");
            updatePanels();
        }
        if(e.getSource()==backButton){
            if(currentPanel.equals("storiesPanel")||currentPanel.equals("addStoryPanel")){
                currentPanel = "landingPanel";
                System.out.println("Moving to landingPanel.");
                updatePanels();
            }
            if(currentPanel.equals("viewStoryPanel")){
                currentPanel = "storiesPanel";
                System.out.println("Moving to storiesPanel.");
                updatePanels();
            }
            if(currentPanel.equals("storyUploadedPanel")){
                currentPanel = "addStoryPanel";
                System.out.println("Moving to addStoryPanel.");
                updatePanels();
            }
        }
    }
}
