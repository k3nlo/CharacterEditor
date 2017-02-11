package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import player.Fighter;
import player.Player;
import player.Inventory;
import player.WornItems;

import java.awt.CardLayout;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * GUI for SOEN6441 course project - simple version of D&D
 * <P>
 * There are basically 2 panels inside the main window: menu on the left and main panel on the right
 * The main panel has a card layout and contains multiple panels corresponding to menu items.
 */
public class GUI extends JFrame
{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    static String savedCharsPath = "C:\\Users\\qinyu\\workspace\\CharacterEditor\\SavedChar\\";

    private JPanel content_pane;
    private JPanel main_panel; // panel on the right
    private JPanel menu_panel;

    private JPanel new_char_panel;
    private JPanel existing_char_panel;
    private JPanel new_map_panel;
    private JPanel existing_map_panel;
    private JPanel player_inventory_pane;

    private JTextField  txt_char_name;

    private JList existing_chars_listbox;
    private JLabel existing_char_name;
    private JLabel existing_char_level;
    private JLabel existing_char_strength;
    private JLabel existing_char_intelligence;
    private JLabel existing_char_wisdom;
    private JLabel existing_char_dexterity;
    private JLabel existing_char_constitution;
    private JLabel existing_char_charisma;

    private JLabel new_char_strength;
    private JLabel new_char_intelligence;
    private JLabel new_char_wisdom;
    private JLabel new_char_dexterity;
    private JLabel new_char_constitution;
    private JLabel new_char_charisma;


    public Player curPlayer;


    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GUI main_window = new GUI();
                    main_window.setVisible(true);
                    main_window.setSize(1000,600);
                    main_window.setResizable(false);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * This method is called when the user creates a new character.
     * It creates and serializes the character.
     */
    public void createAndSavePlayer(){
        System.out.println("Creating new character...");
        Player myPlayer = new Player();
        myPlayer.setName(txt_char_name.getText());
        myPlayer.setLevel(1);

        serializer myserializer = new serializer();
        myserializer.saveToFile(savedCharsPath + myPlayer.getName() + ".ser" ,myPlayer);

        System.out.println("Character saved to file.");

        System.out.println("Displaying randomly generated ability scores...");
        new_char_strength.setText(Integer.toString(myPlayer.getStat(Fighter.Stat.STRENGHT)));
        new_char_intelligence.setText(Integer.toString(myPlayer.getStat(Fighter.Stat.INTELLIGENCE)));
        new_char_wisdom.setText(Integer.toString(myPlayer.getStat(Fighter.Stat.WISDOM)));
        new_char_dexterity.setText(Integer.toString(myPlayer.getStat(Fighter.Stat.DEXTERITY)));
        new_char_constitution.setText(Integer.toString(myPlayer.getStat(Fighter.Stat.CONSTITUTION)));
        new_char_charisma.setText(Integer.toString(myPlayer.getStat(Fighter.Stat.CHARISMA)));

        //Ready!
    }

    /**
     * This method serializes the input player.
     * @param playerToSave
     */
    public void savePlayer(Player playerToSave){

        serializer myserializer = new serializer();
        myserializer.saveToFile(savedCharsPath + playerToSave.getName() + ".ser" ,playerToSave);

    }

    /**
     * This method loads the selected existing character from file into memory and displays its characteristics on the screen.
     */
    public void loadPlayer(){
        if (existing_chars_listbox.getSelectedIndices().length == 1){

            System.out.println("Deserializing character from file...");
            serializer myserializer = new serializer();
            curPlayer = myserializer.loadPlayerFromFile(savedCharsPath + existing_chars_listbox.getSelectedValue().toString() +".ser");

            System.out.println("Displaying the characteristics of the loaded character...");
            existing_char_name.setText(curPlayer.getName());
            existing_char_level.setText(Integer.toString(curPlayer.getLevel()));
            existing_char_strength.setText(Integer.toString(curPlayer.getStat(Fighter.Stat.STRENGHT)));
            existing_char_intelligence.setText(Integer.toString(curPlayer.getStat(Fighter.Stat.INTELLIGENCE)));
            existing_char_wisdom.setText(Integer.toString(curPlayer.getStat(Fighter.Stat.WISDOM)));
            existing_char_dexterity.setText(Integer.toString(curPlayer.getStat(Fighter.Stat.DEXTERITY)));
            existing_char_constitution.setText(Integer.toString(curPlayer.getStat(Fighter.Stat.CONSTITUTION)));
            existing_char_charisma.setText(Integer.toString(curPlayer.getStat(Fighter.Stat.CHARISMA)));
        }
        else{
            //message: only one player must be selected
        }

        System.out.println("Ready!");
    }

    /**
     * This method is written just for test purposes!
     */
    /*public void testCreatePlayer(){
        Player myPlayer = new Player();
        myPlayer.setName("looloo");
        myPlayer.setLevel(1);
               
        serializer myserializer = new serializer();        
        myserializer.saveToFile("C:\\Concordia courses\\APP\\Project\\testFiles\\Looloo.ser",myPlayer);
                
        Player myPlayer2 ;//= new Player();
        myPlayer2 = myserializer.loadPlayerFromFile("C:\\Concordia courses\\APP\\Project\\testFiles\\Looloo.ser");                                            
    }*/

    /**
     * This method shows the list of existing characters by getting the list of files in the directory where characters are saved.
     */
    public void showExistingPlayers(){
        System.out.println("Preparing the list of existing characters...");

        File folder = new File(savedCharsPath);
        File[] listOfFiles = folder.listFiles();

        String  existingCharListData[] = new String[listOfFiles.length];

        for (int i = 0; i < listOfFiles.length; i++) {
            existingCharListData[i] = listOfFiles[i].getName().substring(0, listOfFiles[i].getName().length()-4);
        }


        existing_chars_listbox.setListData(existingCharListData);

        System.out.println("Ready!");
    }

    /**
     * This method initializes the user screen.
     */
    public GUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(50, 50, 1000, 600);

        setupNewCharPanel();
        setupExistingCharPanel();
        setupNewMapPanel();
        setupExistingMapPanel();
        setUpInventoryPane();

        setupMenuPanel();
        setupMainPanel();
        setupWindow();

//        testCreatePlayer();
        showExistingPlayers();

    }

    /**
     * This method initializes the main window.
     */
    private void setupWindow()
    {
        content_pane = new JPanel();
        content_pane.setBackground(new Color(51, 102, 102));
        content_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
        content_pane.setLayout(new BorderLayout(20, 20));
        setContentPane(content_pane);

        content_pane.add(menu_panel, BorderLayout.WEST);
        content_pane.add(main_panel, BorderLayout.CENTER);
    }

    /**
     * This method initializes the panel on the right.
     */
    private void setupMainPanel()
    {
        main_panel = new JPanel();
        main_panel.setLayout(new CardLayout(0, 0));
        main_panel.add(existing_char_panel,"");
        main_panel.add(new_char_panel,"");
        main_panel.add(existing_map_panel,"");
        main_panel.add(new_map_panel,"");
        main_panel.add(player_inventory_pane,"");
    }

    /**
     * This method builds the menu.
     */
    private void setupMenuPanel()
    {
        menu_panel = new JPanel();
        menu_panel.setBackground(new Color(102, 0, 0));
        //menu_panel.setLayout(new BoxLayout(menu_panel, BoxLayout.Y_AXIS));
        menu_panel.setLayout(new GridLayout(15,1,0,5));
        menu_panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        //Add Menu buttons              
        JButton btn_char_selection = new JButton("Character Selection");
        btn_char_selection.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new_char_panel.setVisible(false);
                existing_char_panel.setVisible(true);
                player_inventory_pane.setVisible(false);
                showExistingPlayers();
            }
        });
        menu_panel.add(btn_char_selection);
        //menu_panel.add(Box.createVerticalStrut(5));

        JButton btn_char_creation = new JButton("Character Creation");
        btn_char_creation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new_char_panel.setVisible(true);
                existing_char_panel.setVisible(false);
                player_inventory_pane.setVisible(false);
            }
        });
        menu_panel.add(btn_char_creation);
        //menu_panel.add(Box.createVerticalStrut(5));

        JButton btn_map_selection = new JButton("Map Selection");
        btn_map_selection.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new_char_panel.setVisible(false);
                existing_char_panel.setVisible(false);
                existing_map_panel.setVisible(true);
                player_inventory_pane.setVisible(false);
            }
        });
        menu_panel.add(btn_map_selection);
        btn_map_selection.setVisible(false);

        JButton btn_map_creation = new JButton("Map Creation");
        btn_map_creation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new_char_panel.setVisible(false);
                existing_char_panel.setVisible(false);
                existing_map_panel.setVisible(false);
                new_map_panel.setVisible(true);
                player_inventory_pane.setVisible(false);
            }
        });
        menu_panel.add(btn_map_creation);
        btn_map_creation.setVisible(false);

        JButton btn_inventory =  new JButton("Inventory");
        btn_inventory.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new_char_panel.setVisible(false);
                existing_char_panel.setVisible(false);
                existing_map_panel.setVisible(false);
                new_map_panel.setVisible(false);
                player_inventory_pane.setVisible(true);
            }
        });
        menu_panel.add(btn_inventory);

        JButton btn_start = new JButton("Play");        
        /*btn_start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new_char_panel.setVisible(false);
                existing_char_panel.setVisible(false);              
                existing_map_panel.setVisible(false);
                new_map_panel.setVisible(false);
            }
        });*/
        menu_panel.add(btn_start);
        btn_start.setVisible(false);


        JButton btn_exit = new JButton("Exit");
        btn_exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (curPlayer != null){
                    System.out.println("Saving the current player...");
                    savePlayer(curPlayer);
                }
                System.out.println("Closed!");
                dispose();
                System.exit(0);
            }
        });
        menu_panel.add(btn_exit);

    }

    /**
     * This method initializes the screen for creating a new character.
     */

    private void setupNewCharPanel()
    {
        new_char_panel = new JPanel();
        new_char_panel.setBackground(new Color(0, 51, 51));
        new_char_panel.setVisible(false);
        new_char_panel.setLayout(null);//todo: change layout

        //Todo: Add new_char_panel controls                
        JLabel lbl_style = new JLabel("Style:");
        lbl_style.setFont(new Font("Tahoma", Font.BOLD, 12));
        lbl_style.setForeground(new Color(255,255,255));
        lbl_style.setBounds(10, 11, 46, 14);
        new_char_panel.add(lbl_style);

        JRadioButton rdbtn_melee = new JRadioButton("Melee");
        rdbtn_melee.setSelected(true);
        rdbtn_melee.setBackground(new Color(0, 51, 51));
        rdbtn_melee.setForeground(new Color(255,255,255));
        rdbtn_melee.setBounds(10, 29, 109, 23);
        new_char_panel.add(rdbtn_melee);
        rdbtn_melee.setEnabled(false);

        JLabel lbl_class = new JLabel("Class:");
        lbl_class.setFont(new Font("Tahoma", Font.BOLD, 12));
        lbl_class.setForeground(new Color(255,255,255));
        lbl_class.setBounds(10, 84, 46, 14);
        new_char_panel.add(lbl_class);

        JRadioButton rdbtn_fighter = new JRadioButton("Fighter");
        rdbtn_fighter.setBackground(new Color(0, 51, 51));
        rdbtn_fighter.setForeground(new Color(255,255,255));
        rdbtn_fighter.setSelected(true);
        rdbtn_fighter.setBounds(10, 105, 109, 23);
        new_char_panel.add(rdbtn_fighter);
        rdbtn_fighter.setEnabled(false);

        JLabel lbl_name = new JLabel("Name:");
        lbl_name.setFont(new Font("Tahoma", Font.BOLD, 12));
        lbl_name.setForeground(new Color(255,255,255));
        lbl_name.setBounds(10, 155, 46, 14);
        new_char_panel.add(lbl_name);

        txt_char_name = new JTextField ();
        txt_char_name.setBounds(10, 180, 109, 23);
        new_char_panel.add(txt_char_name);
        //txt_char_name.setInputVerifier(new textInputVerifier());

        txt_char_name.setInputVerifier(new InputVerifier() {
            public boolean verify(JComponent input) {
                String text = ((JTextField) input).getText();
                //if (text.matches("[a-zA-Z0-9]*") && text.length() < 20)
                if (text.matches("[a-zA-Z][a-zA-Z0-9]*") && text.length() < 20)
                {
                    input.setBackground( Color.white );
                    //btn_saveChar.setEnabled(true);
                    return true;
                }

                else
                {
                    input.setBackground( Color.red );
                    //btn_saveChar.setEnabled(false);
                    return false;
                }
            }
        });


        JLabel char_strength = new JLabel("Strength:");
        char_strength.setFont(new Font("Tahoma", Font.BOLD, 12));
        char_strength.setForeground(new Color(255,255,255));
        char_strength.setBounds(10, 290, 100, 23);
        new_char_panel.add(char_strength);

        new_char_strength = new JLabel();
        new_char_strength.setFont(new Font("Tahoma", Font.BOLD, 12));
        new_char_strength.setForeground(Color.red);
        new_char_strength.setBounds(75, 290, 109, 23);
        new_char_panel.add(new_char_strength);

        JLabel char_Intelligence = new JLabel("Intelligence:");
        char_Intelligence.setFont(new Font("Tahoma", Font.BOLD, 12));
        char_Intelligence.setForeground(new Color(255,255,255));
        char_Intelligence.setBounds(10, 320, 100, 23);
        new_char_panel.add(char_Intelligence);

        new_char_intelligence = new JLabel();
        new_char_intelligence.setFont(new Font("Tahoma", Font.BOLD, 12));
        new_char_intelligence.setForeground(Color.red);
        new_char_intelligence.setBounds(90, 320, 109, 23);
        new_char_panel.add(new_char_intelligence);

        JLabel char_wisdom = new JLabel("Wisdom:");
        char_wisdom.setFont(new Font("Tahoma", Font.BOLD, 12));
        char_wisdom.setForeground(new Color(255,255,255));
        char_wisdom.setBounds(10, 350, 60, 23);
        new_char_panel.add(char_wisdom);

        new_char_wisdom = new JLabel();
        new_char_wisdom.setFont(new Font("Tahoma", Font.BOLD, 12));
        new_char_wisdom.setForeground(Color.red);
        new_char_wisdom.setBounds(70, 350, 109, 23);
        new_char_panel.add(new_char_wisdom);

        JLabel char_dexterity = new JLabel("Dexterity:");
        char_dexterity.setFont(new Font("Tahoma", Font.BOLD, 12));
        char_dexterity.setForeground(new Color(255,255,255));
        char_dexterity.setBounds(10, 380, 70, 23);
        new_char_panel.add(char_dexterity);

        new_char_dexterity = new JLabel();
        new_char_dexterity.setFont(new Font("Tahoma", Font.BOLD, 12));
        new_char_dexterity.setForeground(Color.red);
        new_char_dexterity.setBounds(80, 380, 109, 23);
        new_char_panel.add(new_char_dexterity);

        JLabel char_constitution = new JLabel("Constitution:");
        char_constitution.setFont(new Font("Tahoma", Font.BOLD, 12));
        char_constitution.setForeground(new Color(255,255,255));
        char_constitution.setBounds(10, 410, 90, 23);
        new_char_panel.add(char_constitution);

        new_char_constitution = new JLabel();
        new_char_constitution.setFont(new Font("Tahoma", Font.BOLD, 12));
        new_char_constitution.setForeground(Color.red);
        new_char_constitution.setBounds(95, 410, 109, 23);
        new_char_panel.add(new_char_constitution);

        JLabel char_charisma = new JLabel("Charisma:");
        char_charisma.setFont(new Font("Tahoma", Font.BOLD, 12));
        char_charisma.setForeground(new Color(255,255,255));
        char_charisma.setBounds(10, 440, 60, 23);
        new_char_panel.add(char_charisma);

        new_char_charisma = new JLabel();
        new_char_charisma.setFont(new Font("Tahoma", Font.BOLD, 12));
        new_char_charisma.setForeground(Color.red);
        new_char_charisma.setBounds(75, 440, 109, 23);
        new_char_panel.add(new_char_charisma);

        JButton btn_saveChar = new JButton("Create");
        btn_saveChar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txt_char_name.getText().matches("[a-zA-Z][a-zA-Z0-9]*") && txt_char_name.getText().length() < 20)
                    createAndSavePlayer();
                else
                    JOptionPane.showMessageDialog(null, "Name cannot start with a number, contain special characters or be longer than 20 letters!", "Name Not Acceptable", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        btn_saveChar.setBounds(650, 500, 109, 23);
        new_char_panel.add(btn_saveChar);
    }

    /**
     * This method initializes the screen that lets user select an existing character.
     */

    private void setupExistingCharPanel()
    {
        existing_char_panel = new JPanel();
        existing_char_panel.setBackground(new Color(0, 51, 51));
        existing_char_panel.setVisible(false);
        existing_char_panel.setLayout(null);//todo: change layout

        //Todo: Add existing_char_panel controls
        JLabel lbl_existing_chars = new JLabel("Available Characters:");
        lbl_existing_chars.setFont(new Font("Tahoma", Font.BOLD, 12));
        lbl_existing_chars.setForeground(new Color(255,255,255));
        lbl_existing_chars.setBounds(10, 11, 246, 14);
        existing_char_panel.add(lbl_existing_chars);

        existing_chars_listbox = new JList();
        existing_chars_listbox.setBounds(10,40,120,150);
        //existing_char_panel.add(existing_chars_listbox);        

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10,40,120,150);
        scrollPane.setViewportView(existing_chars_listbox);
        existing_char_panel.add(scrollPane);

        JLabel char_name = new JLabel("Name:");
        char_name.setFont(new Font("Tahoma", Font.BOLD, 12));
        char_name.setForeground(new Color(255,255,255));
        char_name.setBounds(10, 220, 50, 23);
        existing_char_panel.add(char_name);

        existing_char_name = new JLabel();
        existing_char_name.setFont(new Font("Tahoma", Font.BOLD, 12));
        existing_char_name.setForeground(Color.red);
        existing_char_name.setBounds(50, 220, 109, 23);
        existing_char_panel.add(existing_char_name);

        JLabel char_level = new JLabel("Level:");
        char_level.setFont(new Font("Tahoma", Font.BOLD, 12));
        char_level.setForeground(new Color(255,255,255));
        char_level.setBounds(10, 260, 50, 23);
        existing_char_panel.add(char_level);

        existing_char_level = new JLabel();
        existing_char_level.setFont(new Font("Tahoma", Font.BOLD, 12));
        existing_char_level.setForeground(Color.red);
        existing_char_level.setBounds(50, 260, 109, 23);
        existing_char_panel.add(existing_char_level);

        JLabel char_style = new JLabel("Style:");
        char_style.setFont(new Font("Tahoma", Font.BOLD, 12));
        char_style.setForeground(new Color(255,255,255));
        char_style.setBounds(120, 260, 50, 23);
        existing_char_panel.add(char_style);

        JLabel char_style_value = new JLabel("Melee");
        char_style_value.setFont(new Font("Tahoma", Font.BOLD, 12));
        char_style_value.setForeground(Color.red);
        char_style_value.setBounds(160, 260, 50, 23);
        existing_char_panel.add(char_style_value);

        JLabel char_class = new JLabel("Class:");
        char_class.setFont(new Font("Tahoma", Font.BOLD, 12));
        char_class.setForeground(new Color(255,255,255));
        char_class.setBounds(250, 260, 50, 23);
        existing_char_panel.add(char_class);

        JLabel char_class_value = new JLabel("Fighter");
        char_class_value.setFont(new Font("Tahoma", Font.BOLD, 12));
        char_class_value.setForeground(Color.red);
        char_class_value.setBounds(290, 260, 50, 23);
        existing_char_panel.add(char_class_value);

        JLabel char_strength = new JLabel("Strength:");
        char_strength.setFont(new Font("Tahoma", Font.BOLD, 12));
        char_strength.setForeground(new Color(255,255,255));
        char_strength.setBounds(10, 290, 100, 23);
        existing_char_panel.add(char_strength);

        existing_char_strength = new JLabel();
        existing_char_strength.setFont(new Font("Tahoma", Font.BOLD, 12));
        existing_char_strength.setForeground(Color.red);
        existing_char_strength.setBounds(75, 290, 109, 23);
        existing_char_panel.add(existing_char_strength);

        JLabel char_Intelligence = new JLabel("Intelligence:");
        char_Intelligence.setFont(new Font("Tahoma", Font.BOLD, 12));
        char_Intelligence.setForeground(new Color(255,255,255));
        char_Intelligence.setBounds(10, 320, 100, 23);
        existing_char_panel.add(char_Intelligence);

        existing_char_intelligence = new JLabel();
        existing_char_intelligence.setFont(new Font("Tahoma", Font.BOLD, 12));
        existing_char_intelligence.setForeground(Color.red);
        existing_char_intelligence.setBounds(90, 320, 109, 23);
        existing_char_panel.add(existing_char_intelligence);

        JLabel char_wisdom = new JLabel("Wisdom:");
        char_wisdom.setFont(new Font("Tahoma", Font.BOLD, 12));
        char_wisdom.setForeground(new Color(255,255,255));
        char_wisdom.setBounds(10, 350, 60, 23);
        existing_char_panel.add(char_wisdom);

        existing_char_wisdom = new JLabel();
        existing_char_wisdom.setFont(new Font("Tahoma", Font.BOLD, 12));
        existing_char_wisdom.setForeground(Color.red);
        existing_char_wisdom.setBounds(70, 350, 109, 23);
        existing_char_panel.add(existing_char_wisdom);

        JLabel char_dexterity = new JLabel("Dexterity:");
        char_dexterity.setFont(new Font("Tahoma", Font.BOLD, 12));
        char_dexterity.setForeground(new Color(255,255,255));
        char_dexterity.setBounds(10, 380, 70, 23);
        existing_char_panel.add(char_dexterity);

        existing_char_dexterity = new JLabel();
        existing_char_dexterity.setFont(new Font("Tahoma", Font.BOLD, 12));
        existing_char_dexterity.setForeground(Color.red);
        existing_char_dexterity.setBounds(80, 380, 109, 23);
        existing_char_panel.add(existing_char_dexterity);

        JLabel char_constitution = new JLabel("Constitution:");
        char_constitution.setFont(new Font("Tahoma", Font.BOLD, 12));
        char_constitution.setForeground(new Color(255,255,255));
        char_constitution.setBounds(10, 410, 90, 23);
        existing_char_panel.add(char_constitution);

        existing_char_constitution = new JLabel();
        existing_char_constitution.setFont(new Font("Tahoma", Font.BOLD, 12));
        existing_char_constitution.setForeground(Color.red);
        existing_char_constitution.setBounds(95, 410, 109, 23);
        existing_char_panel.add(existing_char_constitution);

        JLabel char_charisma = new JLabel("Charisma:");
        char_charisma.setFont(new Font("Tahoma", Font.BOLD, 12));
        char_charisma.setForeground(new Color(255,255,255));
        char_charisma.setBounds(10, 440, 60, 23);
        existing_char_panel.add(char_charisma);

        existing_char_charisma = new JLabel();
        existing_char_charisma.setFont(new Font("Tahoma", Font.BOLD, 12));
        existing_char_charisma.setForeground(Color.red);
        existing_char_charisma.setBounds(75, 440, 109, 23);
        existing_char_panel.add(existing_char_charisma);

        JButton btn_loadChar = new JButton("Load");
        btn_loadChar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadPlayer();
            }
        });
        btn_loadChar.setBounds(650, 500, 109, 23);
        existing_char_panel.add(btn_loadChar);
    }

    /**
     * This method initializes the screen for creating a new map.
     */
    private void setupNewMapPanel()
    {
        new_map_panel = new JPanel();
        new_map_panel.setBackground(new Color(0, 51, 51));
        new_map_panel.setVisible(false);
        new_map_panel.setLayout(null);//todo: change layout   
    }

    /**
     * This method initializes the screen that lets user select an existing map.
     */
    private void setupExistingMapPanel()
    {
        existing_map_panel = new JPanel();
        existing_map_panel.setBackground(new Color(0, 51, 51));
        existing_map_panel.setVisible(false);
        existing_map_panel.setLayout(null);//todo: change layout

        //Todo: Add existing_map_panel controls
        JLabel lbl_existing_maps = new JLabel("Available Maps:");
        lbl_existing_maps.setFont(new Font("Tahoma", Font.BOLD, 12));
        lbl_existing_maps.setForeground(new Color(255,255,255));
        lbl_existing_maps.setBounds(10, 11, 246, 14);
        existing_map_panel.add(lbl_existing_maps);
    }

    private void setUpInventoryPane()
    {
        {


            player_inventory_pane = new JPanel();
            player_inventory_pane.setBackground(new Color(244,142,2));
            player_inventory_pane.setVisible(true);
            player_inventory_pane.setLayout(null);

            // JButton            invButton = new JButton("Inventory");
            ListSelectionModel listSelectionModel;
            JButton load = new JButton("Equip");
            JButton unEquip = new JButton("UnEquip");

            load.setBounds(11, 50, 80, 50);
            unEquip.setBounds(11,300,80,50);
            Inventory i = new Inventory();
            WornItems wi = new WornItems();
            final  JList wornItemList = new JList(wi.wornList.toArray());
            wornItemList.setBounds(500, 50, 50, 200);
             /* if(wornItemList==null);
              {
                  wornItemList.add();
              }*/
            final JList inventoryList = new JList(i.ls.toArray());
            inventoryList.setBounds(100, 50, 100, 300);

            inventoryList.setVisibleRowCount(10);
            inventoryList
                    .setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            wornItemList.setVisibleRowCount(10);
            //wornItemList.setBounds(100, 100, 10, 250);
            wornItemList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            //panel.add(wornItemList);

            player_inventory_pane.add(unEquip);
            player_inventory_pane.add(inventoryList);
            player_inventory_pane.add(wornItemList);
            player_inventory_pane.add(load);


            load.addActionListener(new ActionListener() {


                @Override
                public void actionPerformed(ActionEvent arg0)
                {
                    @SuppressWarnings("deprecation")
                    List s = inventoryList.getSelectedValuesList();
                    WornItems wi = new WornItems();
                    wi.addItems(s);
                    System.out.println("Item added");


                }

            });
            unEquip.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent arg0)
                {
                    List s = wornItemList.getSelectedValuesList();
                    WornItems wi = new WornItems();
                    wi.removeItem(s);
                    System.out.println("Item removed");

                }
            });
        }
    }
}


