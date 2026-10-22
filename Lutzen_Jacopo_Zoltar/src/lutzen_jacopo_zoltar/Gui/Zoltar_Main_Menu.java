/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package lutzen_jacopo_zoltar.Gui;


import lutzen_jacopo_zoltar.Gui.ControllerMenu;
import lutzen_jacopo_zoltar.Gui.PersonalitaGrafica;
/**
 * Finestra iniziale dell'applicazione.
 *
 * <p>Il form contiene i componenti grafici, mentre {@link ControllerMenu}
 * gestisce lo stato del menu e l'avvio della schermata successiva.</p>
 */
public class Zoltar_Main_Menu extends javax.swing.JFrame {
    
    // Controller che gestisce scelta della personalita e stato del menu.
    private ControllerMenu controller;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Zoltar_Main_Menu.class.getName());

    /**
     * Crea il menu principale di Zoltar.
     */
    public Zoltar_Main_Menu() {
        // Crea i componenti grafici generati da NetBeans.
        initComponents();
        // Collega i componenti del form alla logica del menu.
        controller = new ControllerMenu(cmb_personalità, lbl_anteprima,
                lbl_pulsante, lbl_inserisci1, lbl_inserisci2);
    }
    /**
     * Inizializza componenti, layout, immagini e listener del form.
     * La sezione e generata dal designer NetBeans.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_schermo = new javax.swing.JPanel();
        pnl_alto = new javax.swing.JPanel();
        lbl_titolo = new javax.swing.JLabel();
        pnl_basso = new javax.swing.JPanel();
        lbl_moneta = new javax.swing.JLabel();
        lbl_pulsante = new javax.swing.JLabel();
        pnl_sinistro = new javax.swing.JPanel();
        pnl_destro = new javax.swing.JPanel();
        lbl_inserisci1 = new javax.swing.JLabel();
        lbl_inserisci2 = new javax.swing.JLabel();
        cmb_personalità = new javax.swing.JComboBox<>();
        lbl_guida = new javax.swing.JLabel();
        lbl_anteprima = new javax.swing.JLabel();

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

        lbl_moneta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img_Personalità/Slot/vintage-coin-slot-machine-panel-with-button-front-allan-swart.png"))); // NOI18N
        lbl_moneta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_monetaMouseClicked(evt);
            }
        });

        lbl_pulsante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img_Personalità/Slot/images (4).png"))); // NOI18N
        lbl_pulsante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_pulsanteMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnl_bassoLayout = new javax.swing.GroupLayout(pnl_basso);
        pnl_basso.setLayout(pnl_bassoLayout);
        pnl_bassoLayout.setHorizontalGroup(
            pnl_bassoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_bassoLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(lbl_pulsante, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 379, Short.MAX_VALUE)
                .addComponent(lbl_moneta, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );
        pnl_bassoLayout.setVerticalGroup(
            pnl_bassoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_bassoLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(pnl_bassoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_pulsante)
                    .addComponent(lbl_moneta, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(114, Short.MAX_VALUE))
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

        lbl_inserisci1.setFont(new java.awt.Font("Arial Narrow", 1, 60)); // NOI18N
        lbl_inserisci1.setForeground(new java.awt.Color(153, 0, 0));
        lbl_inserisci1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_inserisci1.setText("INSERISCI MONETA");
        pnl_schermo.add(lbl_inserisci1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, 530, 80));

        lbl_inserisci2.setFont(new java.awt.Font("Arial Narrow", 1, 60)); // NOI18N
        lbl_inserisci2.setForeground(new java.awt.Color(255, 0, 0));
        lbl_inserisci2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_inserisci2.setText("INSERISCI MONETA");
        pnl_schermo.add(lbl_inserisci2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, 520, 80));

        cmb_personalità.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nessuno ", "Zoltar", "Avdoul", "Hakari" }));
        cmb_personalità.addActionListener(this::cmb_personalitàActionPerformed);
        pnl_schermo.add(cmb_personalità, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 130, 140, 30));

        lbl_guida.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        lbl_guida.setForeground(new java.awt.Color(153, 255, 102));
        lbl_guida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_guida.setText("PREMERE IL TASTO VERDE PER INIZIARE");
        pnl_schermo.add(lbl_guida, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 430, 540, 50));

        lbl_anteprima.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img_Personalità/Avdoul/Frame_1_T_P.png"))); // NOI18N
        pnl_schermo.add(lbl_anteprima, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 170, 260, 250));

        getContentPane().add(pnl_schermo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 670, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_pulsanteMouseClicked(java.awt.event.MouseEvent evt) {                                          
        // Recupera la personalita selezionata e avvia la schermata di gioco.
         PersonalitaGrafica scelta = controller.pulsanteCliccato();
        if (scelta == null) {
            return; // pulsante disabilitato
        }

        Zoltar_Game_Menu gioco = new Zoltar_Game_Menu(scelta);
        if (gioco.avvia()) {        // se il server non risponde mostra un alert
            gioco.setVisible(true);
            dispose();
        }

    }

    private void lbl_monetaMouseClicked(java.awt.event.MouseEvent evt) {                                        
        // Mostra il selettore delle personalita dopo l'inserimento della moneta.
        controller.inserisciMoneta();
    }

    private void cmb_personalitàActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // Aggiorna anteprima e pulsante quando cambia la selezione.
        controller.personalitaSelezionata();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        // Avvia la GUI nel thread grafico di Swing.
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
        java.awt.EventQueue.invokeLater(() -> new Zoltar_Main_Menu().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmb_personalità;
    private javax.swing.JLabel lbl_anteprima;
    private javax.swing.JLabel lbl_guida;
    private javax.swing.JLabel lbl_inserisci1;
    private javax.swing.JLabel lbl_inserisci2;
    private javax.swing.JLabel lbl_moneta;
    private javax.swing.JLabel lbl_pulsante;
    private javax.swing.JLabel lbl_titolo;
    private javax.swing.JPanel pnl_alto;
    private javax.swing.JPanel pnl_basso;
    private javax.swing.JPanel pnl_destro;
    private javax.swing.JPanel pnl_schermo;
    private javax.swing.JPanel pnl_sinistro;
    // End of variables declaration//GEN-END:variables
}
