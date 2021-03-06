/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensisa.hassenforder.golfinettes.client.ui;

import fr.ensisa.hassenforder.golfinettes.client.model.Event;

/**
 *
 * @author Hassenforder
 */
public class Event_UI extends javax.swing.JPanel {

    private GUIListener listener;
    private Event event;

    /**
     * Creates new form Event_UI
     */
    public Event_UI(GUIListener listener, Event event) {
        initComponents();
        this.event = event;
        this.listener = listener;
        updateView ();
    }

    public Event getEvent() {
		return event;
	}

	public void updateView () {
    	jEventText.setText(event.toString());
    	this.revalidate();
        this.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jEventText = new javax.swing.JLabel();
        jEventDelete = new javax.swing.JButton();

        jEventText.setText("jLabel20");

        jEventDelete.setText("delete");
        jEventDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEventDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jEventDelete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jEventText, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jEventDelete)
                .addComponent(jEventText))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jEventDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEventDeleteActionPerformed
    	if (listener != null) listener.onDeleteEvent(event);
    }//GEN-LAST:event_jEventDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jEventDelete;
    private javax.swing.JLabel jEventText;
    // End of variables declaration//GEN-END:variables
}
