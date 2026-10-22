/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package lutzen_jacopo_zoltar.Gui;


import lutzen_jacopo_zoltar.Gui.ControllerMenu;
import lutzen_jacopo_zoltar.Gui.PersonalitaGrafica;
/**
 * Finestra operativa per scrivere e inviare le domande.
 *
 * <p>La logica di rete, validazione, animazione e visualizzazione delle
 * risposte e delegata a {@link ControllerGioco}.</p>
 */
public class Zoltar_Game_Menu extends javax.swing.JFrame {
    
    // Controller della schermata in cui l'utente scrive le domande.
        private ControllerGioco controller;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Zoltar_Game_Menu.class.getName());

    /**
     * Crea la schermata per le domande.
     */
    public Zoltar_Game_Menu(PersonalitaGrafica personalita) {
        // Inizializza il form e collega la personalita scelta.
        initComponents();
        
        controller = new ControllerGioco(this, txt_domanda, btn_invia,
                lbl_personalità, personalita);
    }

    private Zoltar_Game_Menu() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public boolean avvia() {
    // Apre la connessione al server prima di rendere visibile la schermata.
    return controller.connetti();
}
    /**
     * Inizializza i componenti grafici generati dal designer NetBeans.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_schermo = new javax.swing.JPanel();
        pnl_alto = new javax.swing.JPanel();
        lbl_titolo = new javax.swing.JLabel();
        pnl_basso = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_domanda = new javax.swing.JTextPane();
        btn_invia = new javax.swing.JButton();
        pnl_sinistro = new javax.swing.JPanel();
        pnl_destro = new javax.swing.JPanel();
        lbl_guida = new javax.swing.JLabel();
        lbl_personalità = new javax.swing.JLabel();
        btn_logoff = new javax.swing.JButton();
        btn_indietro = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnl_schermo.setBackground(new java.awt.Color(51, 0, 51));
        pnl_schermo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnl_alto.setBackground(new java.awt.Color(51, 0, 0));

        lbl_titolo.setFont(new java.awt.Font("Viner Hand ITC", 1, 80)); // NOI18N
        lbl_titolo.setForeground(new java.awt.Color(153, 153, 0));
        lbl_titolo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_titolo.setText("ZOLTAR");

        javax.swing.GroupLayout pnl_altoLayout = new javax.swing.GroupLayout(pnl_alto);
        pnl_alto.setLayout(pnl_altoLayout);
        pnl_altoLayout.setHorizontalGroup(
            pnl_altoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_altoLayout.createSequentialGroup()
                .addComponent(lbl_titolo, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnl_altoLayout.setVerticalGroup(
            pnl_altoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_altoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_titolo, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnl_schermo.add(pnl_alto, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 670, 120));

        pnl_basso.setBackground(new java.awt.Color(51, 0, 0));

        txt_domanda.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jScrollPane1.setViewportView(txt_domanda);

        btn_invia.setBackground(new java.awt.Color(255, 255, 0));
        btn_invia.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_invia.setText("invia");
        btn_invia.addActionListener(this::btn_inviaActionPerformed);

        javax.swing.GroupLayout pnl_bassoLayout = new javax.swing.GroupLayout(pnl_basso);
        pnl_basso.setLayout(pnl_bassoLayout);
        pnl_bassoLayout.setHorizontalGroup(
            pnl_bassoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_bassoLayout.createSequentialGroup()
                .addGroup(pnl_bassoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_bassoLayout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_bassoLayout.createSequentialGroup()
                        .addGap(274, 274, 274)
                        .addComponent(btn_invia, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        pnl_bassoLayout.setVerticalGroup(
            pnl_bassoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_bassoLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_invia)
                .addContainerGap(120, Short.MAX_VALUE))
        );

        pnl_schermo.add(pnl_basso, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 670, 240));

        pnl_sinistro.setBackground(new java.awt.Color(102, 51, 0));

        javax.swing.GroupLayout pnl_sinistroLayout = new javax.swing.GroupLayout(pnl_sinistro);
        pnl_sinistro.setLayout(pnl_sinistroLayout);
        pnl_sinistroLayout.setHorizontalGroup(
            pnl_sinistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );
        pnl_sinistroLayout.setVerticalGroup(
            pnl_sinistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
        );

        pnl_schermo.add(pnl_sinistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 60, 360));

        pnl_destro.setBackground(new java.awt.Color(102, 51, 0));

        javax.swing.GroupLayout pnl_destroLayout = new javax.swing.GroupLayout(pnl_destro);
        pnl_destro.setLayout(pnl_destroLayout);
        pnl_destroLayout.setHorizontalGroup(
            pnl_destroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );
        pnl_destroLayout.setVerticalGroup(
            pnl_destroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
        );

        pnl_schermo.add(pnl_destro, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 120, 70, -1));

        lbl_guida.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        lbl_guida.setForeground(new java.awt.Color(153, 255, 102));
        lbl_guida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_guida.setText("INVIARE LA DOMANDA PER IL TUO FUTURO");
        pnl_schermo.add(lbl_guida, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 430, 540, 50));

        lbl_personalità.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img_Personalità/Avdoul/Frame_1_T_P.png"))); // NOI18N
        pnl_schermo.add(lbl_personalità, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 310, 290));

        btn_logoff.setBackground(new java.awt.Color(255, 0, 0));
        btn_logoff.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        btn_logoff.setForeground(new java.awt.Color(255, 255, 51));
        btn_logoff.setText("ESCI");
        btn_logoff.addActionListener(this::btn_logoffActionPerformed);
        pnl_schermo.add(btn_logoff, new org.netbeans.lib.awtextra.AbsoluteConstraints(524, 120, 70, 70));

        btn_indietro.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_indietro.setText("<=");
        btn_indietro.addActionListener(this::btn_indietroActionPerformed);
        pnl_schermo.add(btn_indietro, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, 60, 30));

        getContentPane().add(pnl_schermo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 670, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_indietroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_indietroActionPerformed
        // Chiude la connessione e torna al menu principale.
        controller.chiudi();
    new Zoltar_Main_Menu().setVisible(true);
    dispose();
    }

    private void btn_logoffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_logoffActionPerformed
       // Chiede conferma prima di terminare definitivamente il programma.
       if (controller.confermaUscita()) {
        controller.chiudi();
        dispose();
        System.exit(0);
    }
    }

    private void btn_inviaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_inviaActionPerformed
         // Delega al controller il controllo e l'invio della domanda.
         controller.inviaDomanda();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        // Avvia la schermata di gioco nel thread grafico di Swing.
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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Zoltar_Game_Menu().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_indietro;
    private javax.swing.JButton btn_invia;
    private javax.swing.JButton btn_logoff;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_guida;
    private javax.swing.JLabel lbl_personalità;
    private javax.swing.JLabel lbl_titolo;
    private javax.swing.JPanel pnl_alto;
    private javax.swing.JPanel pnl_basso;
    private javax.swing.JPanel pnl_destro;
    private javax.swing.JPanel pnl_schermo;
    private javax.swing.JPanel pnl_sinistro;
    private javax.swing.JTextPane txt_domanda;
    // End of variables declaration//GEN-END:variables
}
