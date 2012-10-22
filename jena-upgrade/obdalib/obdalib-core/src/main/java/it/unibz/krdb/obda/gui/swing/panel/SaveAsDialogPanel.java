/***
 * Copyright (c) 2008, Mariano Rodriguez-Muro.
 * All rights reserved.
 *
 * The OBDA-API is licensed under the terms of the Lesser General Public
 * License v.3 (see OBDAAPI_LICENSE.txt for details). The components of this
 * work include:
 * 
 * a) The OBDA-API developed by the author and licensed under the LGPL; and, 
 * b) third-party components licensed under terms that may be different from 
 *   those of the LGPL.  Information about such licenses can be found in the 
 *   file named OBDAAPI_3DPARTY-LICENSES.txt.
 */

package it.unibz.krdb.obda.gui.swing.panel;

import it.unibz.krdb.obda.gui.swing.utils.DialogUtils;

import javax.swing.JDialog;

/**
 *
 * @author  mariano
 */
public class SaveAsDialogPanel extends javax.swing.JPanel {
    
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 6272564404142174931L;
	JDialog parentDialog = null;
    /** Creates new form SaveAsDialogPanel */
    public SaveAsDialogPanel(JDialog parentDialog) {
    	this.parentDialog = parentDialog;
        initComponents();
        DialogUtils.setAntializaing(this, true);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        optionsGroup = new javax.swing.ButtonGroup();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        buttonAccept = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jLabel7.setBackground(new java.awt.Color(153, 0, 0));
        jLabel7.setFont(new java.awt.Font("Arial", 1, 11));
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("  SAVE RESULTS AS");
        jLabel7.setAlignmentX(10.0F);
        jLabel7.setFocusable(false);
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel7.setIconTextGap(10);
        jLabel7.setMinimumSize(new java.awt.Dimension(25, 25));
        jLabel7.setOpaque(true);
        jLabel7.setPreferredSize(new java.awt.Dimension(121, 20));
        add(jLabel7, java.awt.BorderLayout.NORTH);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        optionsGroup.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Arial", 0, 11));
        jRadioButton1.setText("CSV Text file");
        jRadioButton1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jRadioButton1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jRadioButton1.setMinimumSize(new java.awt.Dimension(250, 20));
        jRadioButton1.setPreferredSize(new java.awt.Dimension(200, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 10);
        jPanel1.add(jRadioButton1, gridBagConstraints);

        optionsGroup.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Arial", 0, 11));
        jRadioButton2.setText("Excel file");
        jRadioButton2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jRadioButton2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jRadioButton2.setMinimumSize(new java.awt.Dimension(250, 20));
        jRadioButton2.setPreferredSize(new java.awt.Dimension(200, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 10);
        jPanel1.add(jRadioButton2, gridBagConstraints);

        optionsGroup.add(jRadioButton3);
        jRadioButton3.setFont(new java.awt.Font("Arial", 0, 11));
        jRadioButton3.setText("RDF Triples");
        jRadioButton3.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jRadioButton3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jRadioButton3.setMinimumSize(new java.awt.Dimension(250, 20));
        jRadioButton3.setPreferredSize(new java.awt.Dimension(200, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 10);
        jPanel1.add(jRadioButton3, gridBagConstraints);

        optionsGroup.add(jRadioButton4);
        jRadioButton4.setFont(new java.awt.Font("Arial", 0, 11));
        jRadioButton4.setText("OWL Ontology file (only ABox)");
        jRadioButton4.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jRadioButton4.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jRadioButton4.setMinimumSize(new java.awt.Dimension(250, 20));
        jRadioButton4.setPreferredSize(new java.awt.Dimension(200, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 10);
        jPanel1.add(jRadioButton4, gridBagConstraints);

        optionsGroup.add(jRadioButton5);
        jRadioButton5.setFont(new java.awt.Font("Arial", 0, 11));
        jRadioButton5.setText("OWL Ontology (including TBox)");
        jRadioButton5.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jRadioButton5.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jRadioButton5.setMinimumSize(new java.awt.Dimension(250, 20));
        jRadioButton5.setPreferredSize(new java.awt.Dimension(200, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 10);
        jPanel1.add(jRadioButton5, gridBagConstraints);

        add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        buttonAccept.setText("Accept");
        buttonAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAcceptActionPerformed(evt);
            }
        });

        jPanel2.add(buttonAccept);

        buttonCancel.setText("Cancel");
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelActionPerformed(evt);
            }
        });

        jPanel2.add(buttonCancel);

        add(jPanel2, java.awt.BorderLayout.SOUTH);

    }// </editor-fold>//GEN-END:initComponents

    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
    	parentDialog.setVisible(false);
    }//GEN-LAST:event_buttonCancelActionPerformed

    private void buttonAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAcceptActionPerformed

    	parentDialog.setVisible(false);
    }//GEN-LAST:event_buttonAcceptActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAccept;
    private javax.swing.JButton buttonCancel;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.ButtonGroup optionsGroup;
    // End of variables declaration//GEN-END:variables
    
}