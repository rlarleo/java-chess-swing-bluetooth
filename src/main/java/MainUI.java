/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainMenu.java
 *
 * Created on Jan 7, 2010, 9:02:27 PM
 */
/**
 * @author MADHAV
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainUI extends JFrame {

    /** Creates new form MainMenu */
    public MainUI() {
        super("Main Menu");
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainpanel = new JPanel();
        newgame = new JLabel();
        options = new JLabel();
        help = new JLabel();
        about = new JLabel();
        exit = new JLabel();
        logo = new JLabel();
        createdby = new JLabel();
        exitbutton = new JButton();
        subpanel = new JPanel();
        single = new JLabel();
        host = new JLabel();
        join = new JLabel();
        back = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Main MENU");

        newgame.setText("jLabel3");

        options.setText("jLabel4");

        help.setText("jLabel5");

        about.setText("jLabel6");

        exit.setText("jLabel7");

        GroupLayout mainpanelLayout = new GroupLayout(mainpanel);
        mainpanel.setLayout(mainpanelLayout);
        mainpanelLayout.setHorizontalGroup(
                mainpanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainpanelLayout.createSequentialGroup()
                                .addGroup(mainpanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(about, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(exit, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
                        .addGroup(GroupLayout.Alignment.TRAILING, mainpanelLayout.createSequentialGroup()
                                .addGroup(mainpanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(help, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                                        .addComponent(options, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                                        .addComponent(newgame, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                                .addGap(229, 229, 229))
        );
        mainpanelLayout.setVerticalGroup(
                mainpanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainpanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(newgame)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(options)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(help)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(about)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(exit)
                                .addContainerGap(38, Short.MAX_VALUE))
        );

        logo.setText("BlueChess logo");

        createdby.setFont(new Font("Tahoma", Font.BOLD, 14));
        createdby.setText("Created by Biraj, Ganesh & Madhav");

        exitbutton.setFont(new Font("Tahoma", Font.BOLD, 18));
        exitbutton.setForeground(new Color(255, 0, 0));
        exitbutton.setText("EXIT");
        exitbutton.addActionListener(this::exitbuttonActionPerformed);

        single.setText("jLabel8");

        host.setText("jLabel9");

        join.setText("jLabel10");

        back.setText("jLabel1");

        GroupLayout subpanelLayout = new GroupLayout(subpanel);
        subpanel.setLayout(subpanelLayout);
        subpanelLayout.setHorizontalGroup(
                subpanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(subpanelLayout.createSequentialGroup()
                                .addGroup(subpanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(single)
                                        .addComponent(host)
                                        .addComponent(join)
                                        .addComponent(back))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        subpanelLayout.setVerticalGroup(
                subpanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(subpanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(single)
                                .addGap(18, 18, 18)
                                .addComponent(host)
                                .addGap(18, 18, 18)
                                .addComponent(join)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(back)
                                .addContainerGap(35, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(46, 46, 46)
                                                .addComponent(logo))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(subpanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(18, 18, 18)
                                                .addComponent(mainpanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                                .addGap(99, 99, 99)
                                .addComponent(exitbutton, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(174, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(createdby, GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(logo, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(subpanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(mainpanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                                .addComponent(createdby)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(exitbutton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitbuttonActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_exitbuttonActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        EventQueue.invokeLater(() -> new MainMenu().setVisible(true));
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JLabel about;
    private JLabel back;
    private JLabel createdby;
    private JLabel exit;
    private JButton exitbutton;
    private JLabel help;
    private JLabel host;
    private JLabel join;
    private JLabel logo;
    private JPanel mainpanel;
    private JLabel newgame;
    private JLabel options;
    private JLabel single;
    private JPanel subpanel;
    // End of variables declaration//GEN-END:variables
    protected Icon bluechess;
    protected Icon newgame1;
    protected Icon newgame2;
    protected Icon options1;
    protected Icon options2;
    protected Icon help1;
    protected Icon help2;
    protected Icon about1;
    protected Icon about2;
    protected Icon exit1;
    protected Icon exit2;
    protected Icon single1;
    protected Icon single2;
    protected Icon host1;
    protected Icon host2;
    protected Icon join1;
    protected Icon join2;
    protected Icon back1;
    protected Icon back2;

    private class MouseHandler implements MouseListener {

        public void mouseClicked(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet.");
        }

        public void mousePressed(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet.");
        }

        public void mouseReleased(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet.");

            if (e.getSource() == newgame) {
                mainpanel.setVisible(false);
                subpanel.setVisible(true);
            }

            if (e.getSource() == options) {
            }

            if (e.getSource() == help) {
            }

            if (e.getSource() == about) {
            }

            if (e.getSource() == exit) {
                System.exit(0);
            }

            if (e.getSource() == single) {
                JFrame main = new JFrame("Blue Chess Game Window"); //Title
                main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                GUISingle chessWindow = new GUISingle();
                main.add(chessWindow);//creates game board
                chessWindow.createGUI(main);
                main.setSize(680, 580);
                main.setResizable(false);
                main.setVisible(true);
                dispose();
            }

            if (e.getSource() == join) {
                try {
                    JFrame main = new JFrame("Blue Chess Game Window"); //Title
                    main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    GUIBT chessWindow = new GUIBT(false);
                    main.add(chessWindow); //creates game board
                    chessWindow.createGUI(main);
                    main.setSize(680, 580);
                    main.setResizable(false);
                    main.setVisible(true);
                    dispose();
                } catch (Exception ex) {
                    Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (e.getSource() == host) {
                try {
                    JFrame main = new JFrame("Blue Chess Game Window"); //Title
                    main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    GUIBT chessWindow = new GUIBT(true);
                    main.add(chessWindow); //creates game board
                    chessWindow.createGUI(main);
                    main.setSize(680, 580);
                    main.setResizable(false);
                    main.setVisible(true);
                    dispose();
                } catch (Exception ex) {
                    Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (e.getSource() == back) {

                subpanel.setVisible(false);
                mainpanel.setVisible(true);
            }
        }

        public void mouseEntered(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet.");

            if (e.getSource() == newgame) {
                newgame.setIcon(newgame2);
            }

            if (e.getSource() == options) {
                options.setIcon(options2);
            }

            if (e.getSource() == help) {
                help.setIcon(help2);
            }

            if (e.getSource() == about) {
                about.setIcon(about2);
            }

            if (e.getSource() == exit) {
                exit.setIcon(exit2);
            }

            if (e.getSource() == single) {
                single.setIcon(single2);
                subpanel.setVisible(true);
            }

            if (e.getSource() == host) {
                host.setIcon(host2);
                subpanel.setVisible(true);
            }

            if (e.getSource() == join) {
                join.setIcon(join2);
                subpanel.setVisible(true);
            }

            if (e.getSource() == back) {
                back.setIcon(back2);
                subpanel.setVisible(true);
            }

        }

        public void mouseExited(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet.");

            if (e.getSource() == newgame) {
                newgame.setIcon(newgame1);
            }

            if (e.getSource() == options) {
                options.setIcon(options1);
            }

            if (e.getSource() == help) {
                help.setIcon(help1);
            }

            if (e.getSource() == about) {
                about.setIcon(about1);
            }

            if (e.getSource() == exit) {
                exit.setIcon(exit1);
            }

            if (e.getComponent() == subpanel) {
                subpanel.setVisible(false);
            }

            if (e.getSource() == single) {
                single.setIcon(single1);
            }

            if (e.getSource() == host) {
                host.setIcon(host1);
            }

            if (e.getSource() == join) {
                join.setIcon(join1);
            }

            if (e.getSource() == back) {
                back.setIcon(back1);
            }
        }
    }

    public void showMenu() {

        bluechess = new ImageIcon(getClass().getResource("images2/bluechess.png"));

        newgame1 = new ImageIcon(getClass().getResource("images2/newgame1.png"));
        newgame2 = new ImageIcon(getClass().getResource("images2/newgame2.png"));
        options1 = new ImageIcon(getClass().getResource("images2/options1.png"));
        options2 = new ImageIcon(getClass().getResource("images2/options2.png"));
        help1 = new ImageIcon(getClass().getResource("images2/help1.png"));
        help2 = new ImageIcon(getClass().getResource("images2/help2.png"));
        about1 = new ImageIcon(getClass().getResource("images2/about1.png"));
        about2 = new ImageIcon(getClass().getResource("images2/about2.png"));
        exit1 = new ImageIcon(getClass().getResource("images2/exit1.png"));
        exit2 = new ImageIcon(getClass().getResource("images2/exit2.png"));

        single1 = new ImageIcon(getClass().getResource("images2/single1.png"));
        single2 = new ImageIcon(getClass().getResource("images2/single2.png"));
        host1 = new ImageIcon(getClass().getResource("images2/host1.png"));
        host2 = new ImageIcon(getClass().getResource("images2/host2.png"));
        join1 = new ImageIcon(getClass().getResource("images2/join1.png"));
        join2 = new ImageIcon(getClass().getResource("images2/join2.png"));
        back1 = new ImageIcon(getClass().getResource("images2/back1.png"));
        back2 = new ImageIcon(getClass().getResource("images2/back2.png"));


        MouseHandler handler = new MouseHandler();

        mainpanel.setBounds(20, 100, 255, 250);
        add(mainpanel);

        subpanel.setBounds(20, 100, 255, 250);
        add(subpanel);


        logo.setIcon(bluechess);
        logo.setText(null);
        
        setMenu(newgame, newgame1, "New Game");
        newgame.addMouseListener(handler);
        
        setMenu(options, options1, "Options");
        options.addMouseListener(handler);
        
        setMenu(help, help1, "Help");
        help.addMouseListener(handler);

        setMenu(about, about1, "About Game");
        about.addMouseListener(handler);
        
        setMenu(exit, exit1, "Exit From Game");
        exit.addMouseListener(handler);

        setMenu(single, single1, "Play in same device");
        single.addMouseListener(handler);

        setMenu(join, join1, "Join to play through BLUETOOTH");
        join.addMouseListener(handler);

        setMenu(host, host1, "Host to play through BLUETOOTH");
        host.addMouseListener(handler);

        setMenu(back, back1, "Host to play through BLUETOOTH");
        back.addMouseListener(handler);


        Point menuCenter = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        //adjust the bound of frame by a simple logic
        //to center the frame window
        int menuWindowWidth = 300;
        int menuWindowHeight = 450;
        setBounds(menuCenter.x - menuWindowWidth / 2, menuCenter.y - menuWindowHeight / 2, menuWindowWidth, menuWindowHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        mainpanel.setVisible(true);
        subpanel.setVisible(false);
        setVisible(true);

    }


	private void setMenu(JLabel menuName, Icon menuIcon, String tipText) {
		menuName.setIcon(menuIcon);
		menuName.setText(null);
		menuName.setToolTipText(tipText);
	}
}
