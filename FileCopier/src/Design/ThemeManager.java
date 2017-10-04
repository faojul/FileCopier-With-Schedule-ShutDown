package Design;


import java.io.*;
import java.util.*;
import java.util.Formatter;
import java.util.logging.*;

import javax.swing.*;

import copier.Main;
import static copier.Main.*;

public class ThemeManager extends javax.swing.JDialog {
	private JButton applyThemeButton;
    private JLabel promptLabel;
    private JLabel themeNotificationLabel;
    private JComboBox themesComboBox;
    
    public ThemeManager(java.awt.Frame parent, boolean modal) {
    	super(parent, modal);
        initComponents();
        themesComboBox.setSelectedItem(javax.swing.UIManager.getLookAndFeel().getName());

    }
    

    public static void populateThemeNamesMap() {
        themenamesMap = new HashMap<>();
        themenamesMap.put("Acryl", "com.jtattoo.plaf.acryl.AcrylLookAndFeel");
        themenamesMap.put("Aluminium", "com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
        themenamesMap.put("Bernstein", "com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
        themenamesMap.put("Graphite", "com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
        themenamesMap.put("HiFi", "com.jtattoo.plaf.hifi.HiFiLookAndFeel");
        themenamesMap.put("McWin", "com.jtattoo.plaf.mcwin.McWinLookAndFeel");
        themenamesMap.put("Mint", "com.jtattoo.plaf.mint.MintLookAndFeel");
        themenamesMap.put("Nimbus", "javax.swing.plaf.nimbus.NimbusLookAndFeel");
        themenamesMap.put("Noire", "com.jtattoo.plaf.noire.NoireLookAndFeel");
        themenamesMap.put("Texture", "com.jtattoo.plaf.texture.TextureLookAndFeel");
        themenamesMap.put("Windows", "com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    }

    private void applyLookAndFeel(String LookAndFeelName) {
        try {
            
            UIManager.setLookAndFeel(themenamesMap.get(LookAndFeelName));
           SwingUtilities.updateComponentTreeUI(app);
           app.pack();
            saveCurrentTheme(LookAndFeelName);
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void saveCurrentTheme(String currentLookAndFeelName) {
        try {
            File CopyDir = new File((new File(System.getProperty("java.io.tmpdir"))).getParentFile().getPath().concat("\\FileCopier"));
            if (CopyDir.exists()) {
                (new File(CopyDir.getPath().concat("theme.ini"))).delete();
            }
            else {
                CopyDir.mkdir();
            }
            CopyDir = new File(CopyDir.getPath().concat("\\theme.ini"));
            try (Formatter formatter = new Formatter(CopyDir)) {
                formatter.format("%s", currentLookAndFeelName);
            }
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String getLastTheme() {
        File CopyDir = new File((new File(System.getProperty("java.io.tmpdir"))).getParentFile().getPath().concat("\\FileCopier\\theme.ini"));
        if (CopyDir.exists()) {
            try {
                java.util.Scanner reader = new java.util.Scanner(CopyDir);
                if (reader.hasNext()) {
                    return themenamesMap.get(reader.nextLine());
                }
            }
            catch (FileNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return themenamesMap.get("Nimbus");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        promptLabel = new javax.swing.JLabel();
        themesComboBox = new javax.swing.JComboBox();
        applyThemeButton = new javax.swing.JButton();
        themeNotificationLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Themes");
        setName("ThemeManager"); // NOI18N
        setResizable(false);

        promptLabel.setText("Select a Theme from the list");
        

        
        themesComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Acryl", "Aluminium", "Bernstein", "Graphite", "HiFi", "McWin", "Mint", "Nimbus", "Noire", "Texture", "Windows" }));
        

        applyThemeButton.setText("Apply");
        applyThemeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyThemeButtonActionPerformed(evt);
            }
        });

        themeNotificationLabel.setText("* You may need to restart FileCopier");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(promptLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(applyThemeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(themesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(themeNotificationLabel)))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(promptLabel)
                .addGap(18, 18, 18)
                .addComponent(themesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(applyThemeButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(themeNotificationLabel)
                .addGap(9, 9, 9))
        );

        themeNotificationLabel.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void applyThemeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyThemeButtonActionPerformed
        applyLookAndFeel(themesComboBox.getSelectedItem().toString());
        dispose();
        app.setSize(660, 500);
    }//GEN-LAST:event_applyThemeButtonActionPerformed




}
