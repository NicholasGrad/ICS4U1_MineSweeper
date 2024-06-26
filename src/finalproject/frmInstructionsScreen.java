package finalproject;

/**
 * This class creates a JFrame displaying information about how to play
 * Minesweeper
 */
public class frmInstructionsScreen extends javax.swing.JFrame {

    /**
     * Creates new form frameInstruction
     */
    public frmInstructionsScreen() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBackground = new javax.swing.JPanel();
        lblInstructionsTitle = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaInstructions = new javax.swing.JTextArea();
        lblBorder = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlBackground.setBackground(new java.awt.Color(51, 51, 255));
        pnlBackground.setLayout(null);

        lblInstructionsTitle.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
        lblInstructionsTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblInstructionsTitle.setText("Instructions");
        pnlBackground.add(lblInstructionsTitle);
        lblInstructionsTitle.setBounds(10, 20, 183, 33);

        btnBack.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        pnlBackground.add(btnBack);
        btnBack.setBounds(350, 20, 80, 30);

        jScrollPane1.setBorder(null);

        txaInstructions.setEditable(false);
        txaInstructions.setBackground(new java.awt.Color(51, 51, 255));
        txaInstructions.setColumns(20);
        txaInstructions.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        txaInstructions.setForeground(new java.awt.Color(255, 255, 255));
        txaInstructions.setRows(5);
        txaInstructions.setText("Objective: Discover all the tile while avoiding randomly \nplaced bombs.\n\nGameplay: \nUncover tiles: Left click to dig up/uncover the tile you\nchoose.\n- If you uncover a mine it is a automatic loss\n- Uncovering a number will show how many bombs there are \naround that tile (Including diagonals).\n\nFlagging Tiles: Right click to put down a flag where you\nthink the mines are.\n- Right click again to pick up the flag and beable to\nuncover that tile.");
        jScrollPane1.setViewportView(txaInstructions);

        pnlBackground.add(jScrollPane1);
        jScrollPane1.setBounds(10, 60, 430, 268);

        getContentPane().add(pnlBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 450, 340));

        lblBorder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/finalproject/background1Retro.PNG"))); // NOI18N
        getContentPane().add(lblBorder, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, 380));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * This closes the instructions screen and returns to the title screen
     *
     * @param evt
     */
    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // close this screen
        this.dispose();
        // create title screen
        new frmTitleScreen().setVisible(true);
    }//GEN-LAST:event_btnBackActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmInstructionsScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmInstructionsScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmInstructionsScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmInstructionsScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // make this screen visible
                new frmInstructionsScreen().setVisible(false);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBorder;
    private javax.swing.JLabel lblInstructionsTitle;
    private javax.swing.JPanel pnlBackground;
    private javax.swing.JTextArea txaInstructions;
    // End of variables declaration//GEN-END:variables
}
