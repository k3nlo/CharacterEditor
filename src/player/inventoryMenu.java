package player;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class inventoryMenu extends JFrame
{
    private JPanel     j         = new JPanel();
    JButton            invButton = new JButton("Inventory");
    ListSelectionModel listSelectionModel;

    public void inventoryMenu()
    {
       action();
    }

    public void action()
    {
        JFrame frame = new JFrame();
        frame.setSize(400, 400);
        frame.setTitle("Inventory");
        frame.setResizable(false);
        frame.setVisible(true);
        // setBackground(Color.black);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(invButton);
        frame.add(j);
        invButton.setBounds(20, 40, 250, 25);
        j.setVisible(true);
        j.setLayout(null);
        // j.setLayout(new GridLayout(15,1,0,5));
        j.add(invButton);
        invButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                final JFrame frame1 = new JFrame();
                frame1.setSize(600, 600);
                frame1.setTitle("Inventory");
                frame1.setResizable(false);
                frame1.setVisible(true);
                // setBackground(Color.black);
                frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                final JPanel panel = new JPanel();
                JButton load = new JButton("Equip");
                JButton unEquip = new JButton("UnEquip");
                panel.add(load);
                panel.add(unEquip);
                load.setBounds(300, 300, 30, 30);
                unEquip.setBounds(400,300,30,30);

                frame1.add(panel);
                Inventory i = new Inventory();
                WornItems wi = new WornItems();
                
                final  JList wornItemList = new JList(wi.wornList.toArray());
               /* if(wornItemList==null);
                {
                    wornItemList.add();
                }*/
                 final JList inventoryList = new JList(i.ls.toArray());

                inventoryList.setVisibleRowCount(10);
                inventoryList
                        .setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                wornItemList.setVisibleRowCount(10);
                //wornItemList.setBounds(100, 100, 10, 250);
                wornItemList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                //panel.add(wornItemList);
                panel.add(inventoryList);
                panel.add(wornItemList);
                load.addActionListener(new ActionListener() {
                   
                    
                    @Override
                    public void actionPerformed(ActionEvent arg0)
                    {
                        @SuppressWarnings("deprecation")
                        List s = inventoryList.getSelectedValuesList();
                        WornItems wi = new WornItems();
                            wi.addItems(s);            
                       frame1.setSize(600,600);
                       panel.setVisible(false);
                       JPanel jp1 = new JPanel();
                       frame1.setTitle("Inventory");
                       frame1.setResizable(false);
                       frame1.setVisible(true);
                       // setBackground(Color.black);
                       
                       frame1.setDefaultCloseOperation(frame1.EXIT_ON_CLOSE);
                       JList wornItemList = new JList(wi.wornList.toArray());
                      // panel.add(inventoryList);
                       jp1.add(wornItemList);
                       jp1.add(inventoryList);
                       JButton ok = new JButton("OK");
                       jp1.add(ok);
                       ok.setBounds(200, 200, 30, 30);
                       ok.addActionListener(new ActionListener(){

                        @Override
                        public void actionPerformed(ActionEvent arg0)
                        {
                           ok();
                            
                        }
 });
                       
                      frame1.add(jp1);
                    }

                });
                
                unEquip.addActionListener(new ActionListener()
                {

                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        List s = inventoryList.getSelectedValuesList(); 
                         
                        WornItems wi = new WornItems();
                        wi.removeItem(s);
                     JList wornItemList= new  JList(wi.wornList.toArray());
                        frame1.setSize(600,600);
                       frame1.setTitle("Inventory");
                       frame1.setResizable(false);
                       frame1.setVisible(true);
                       // setBackground(Color.black);
                       frame1.setDefaultCloseOperation(frame1.EXIT_ON_CLOSE);
                       JPanel jp2 = new JPanel();
                       jp2.add(inventoryList);
                       jp2.add(wornItemList);
                       
                     // panel.add(wornItemList);
                       
                    }
                    
                }
                        
                        );
            }

        });
    }
    
    public void ok()
    {
    System.exit(0);
    // Go Back to Game menu
    }
    
    
    public static void main(String args[])
    {
        inventoryMenu i = new inventoryMenu();
        i.inventoryMenu();
    }

}
