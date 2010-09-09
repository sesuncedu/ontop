/***
 * Copyright (c) 2008, Mariano Rodriguez-Muro. All rights reserved.
 * 
 * The OBDA-API is licensed under the terms of the Lesser General Public License
 * v.3 (see OBDAAPI_LICENSE.txt for details). The components of this work
 * include:
 * 
 * a) The OBDA-API developed by the author and licensed under the LGPL; and, b)
 * third-party components licensed under terms that may be different from those
 * of the LGPL. Information about such licenses can be found in the file named
 * OBDAAPI_3DPARTY-LICENSES.txt.
 */

package inf.unibz.it.obda.gui.swing.dataquery.panel;

import inf.unibz.it.obda.api.controller.QueryController;
import inf.unibz.it.obda.gui.swing.action.GetDefaultSPARQLPrefixAction;
import inf.unibz.it.obda.gui.swing.action.OBDADataQueryAction;
import inf.unibz.it.ucq.swing.IncrementalQueryResultTableModel;
import inf.unibz.it.utils.swing.DialogUtils;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

// import edu.stanford.smi.protege.resource.Icons;
// import edu.stanford.smi.protegex.owl.ui.ProtegeUI;

/**
 * 
 * Creates a new panel to execute queries. Remember to execute the
 * setResultsPanel function to indicate where to display the results.
 * 
 * Remember to execute all setXActions when creating this objects
 * 
 * @author mariano
 */
public class QueryInterfacePanel extends javax.swing.JPanel implements
		SavedQueriesPanelListener, TableModelListener {

	/**
	 * Variable currentGroup is the group's id to which belongs the selected query
	 * Variable currentId  is the query's id that is selected
	 */
	private static final long serialVersionUID = -5902798157183352944L;
	private ResultViewTablePanel viewpanel;
	private SPARQLQueryStyledDocument _styled_doc = null;
	private OBDADataQueryAction executeUCQAction = null;
	private OBDADataQueryAction executeEQLAction = null;
	private OBDADataQueryAction retrieveUCQExpansionAction = null;
	private OBDADataQueryAction retrieveUCQUnfoldingAction = null;
	private OBDADataQueryAction retrieveEQLUnfoldingAction = null;
	private QueryController qc = null;
	private QueryInterfacePanel instance = null;
	private double execTime = 0;
	private String currentGroup = null;
	private String currentId = null;

	/** Creates new form QueryInterfacePanel */
	public QueryInterfacePanel(QueryController qc) {
		this.qc = qc;
		instance = this;
		initComponents();

		StyleContext style = new StyleContext();
		_styled_doc = new SPARQLQueryStyledDocument(style);

		// StyleContext eqlStyleContext = new StyleContext();
		Style eqlStyle = eqlTextPane.getStyledDocument().getStyle(
				StyleContext.DEFAULT_STYLE);
		StyleConstants.setFontFamily(eqlStyle, "Courier New");
		StyleConstants.setFontSize(eqlStyle, 12);
		StyleConstants.setForeground(eqlStyle, Color.black);

		// UIUtils.setAntializaing(this, true);
		queryTextPane.setDocument(_styled_doc);
		queryTextPane.setBackground(Color.WHITE);
		queryTextPane.setCaretColor(Color.BLACK);

		getEQLSQLExpansion.setEnabled(true);

		// eqlTextPane.setFont(new Font("Arial", Font.PLAIN, 12));
		// eqlTextPane.getStyledDocument().getStyle(StyleContext.DEFAULT_STYLE);

		// remove the EQL panel
		// jTabbedPane2.remove(1);
	}

	public void setResultsPanel(ResultViewTablePanel viewpanel) {
		this.viewpanel = viewpanel;
	}

	public void setGetSPARQLDefaultPrefixAction(
			GetDefaultSPARQLPrefixAction action) {
		_styled_doc.setGetDefaultSPARQLPrefixAction(action);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// <editor-fold defaultstate="collapsed" desc="Generated
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {
		java.awt.GridBagConstraints gridBagConstraints;

		eqlPopupMenu = new javax.swing.JPopupMenu();
		getEQLSQLExpansion = new javax.swing.JMenuItem();
		sparqlPopupMenu = new javax.swing.JPopupMenu();
		getSPARQLExpansion = new javax.swing.JMenuItem();
		getSPARQLSQLExpansion = new javax.swing.JMenuItem();
		jTabbedPane2 = new javax.swing.JTabbedPane();
		jPanel11 = new javax.swing.JPanel();
		jLabel18 = new javax.swing.JLabel();
		jScrollPane3 = new javax.swing.JScrollPane();
		queryTextPane = new javax.swing.JTextPane();
		eqlPanel = new javax.swing.JPanel();
		jScrollPane4 = new javax.swing.JScrollPane();
		eqlTextPane = new javax.swing.JTextPane();
		panel_query_buttons = new javax.swing.JPanel();
		jPanel1 = new javax.swing.JPanel();
		jLabelStatus = new javax.swing.JLabel();
		buttonAdvancedProperties = new javax.swing.JButton();
		buttonExecute = new javax.swing.JButton();
		buttonSaveQuery = new javax.swing.JButton();

		getEQLSQLExpansion.setText("Get SQL for EQL query...");
		getEQLSQLExpansion.setEnabled(false);
		getEQLSQLExpansion
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						getEQLSQLExpansionActionPerformed(evt);
					}
				});
		eqlPopupMenu.add(getEQLSQLExpansion);

		sparqlPopupMenu.setComponentPopupMenu(sparqlPopupMenu);

		getSPARQLExpansion.setText("Get expansion this UCQ...");
		getSPARQLExpansion
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						getSPARQLExpansionActionPerformed(evt);
					}
				});
		sparqlPopupMenu.add(getSPARQLExpansion);

		getSPARQLSQLExpansion
				.setText("Get expanded/unfolded query for this UCQ...");
		getSPARQLSQLExpansion
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						getSPARQLSQLExpansionActionPerformed(evt);
					}
				});
		sparqlPopupMenu.add(getSPARQLSQLExpansion);

		setLayout(new java.awt.GridBagLayout());

		jPanel11.setLayout(new java.awt.BorderLayout());

		jLabel18.setFont(new java.awt.Font("Arial", 1, 11));
		jLabel18.setForeground(new java.awt.Color(153, 153, 153));
		jLabel18.setText("  Query (SPARQL):");
		jPanel11.add(jLabel18, java.awt.BorderLayout.NORTH);

		queryTextPane.setFont(new java.awt.Font("Lucida Grande", 0, 14));
		queryTextPane.setComponentPopupMenu(sparqlPopupMenu);
		jScrollPane3.setViewportView(queryTextPane);

		jPanel11.add(jScrollPane3, java.awt.BorderLayout.CENTER);

		jTabbedPane2.addTab("SPARQL", null, jPanel11,
				"For issuing union of conjunctive queries to the reasoner.");

		eqlPanel.setLayout(new java.awt.BorderLayout());

		eqlTextPane.setComponentPopupMenu(eqlPopupMenu);
		jScrollPane4.setViewportView(eqlTextPane);

		eqlPanel.add(jScrollPane4, java.awt.BorderLayout.CENTER);

		jTabbedPane2.addTab("EQL", null, eqlPanel,
				"For issuing EQL epistemic queries to the reasoner");

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridwidth = 3;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 2.0;
		add(jTabbedPane2, gridBagConstraints);

		panel_query_buttons.setLayout(new java.awt.GridBagLayout());

		jPanel1.setLayout(new java.awt.GridBagLayout());
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
		jPanel1.add(jLabelStatus, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		gridBagConstraints.weightx = 1.0;
		panel_query_buttons.add(jPanel1, gridBagConstraints);

		buttonAdvancedProperties.setText("Advanced properties");
		buttonAdvancedProperties.setEnabled(false);
		buttonAdvancedProperties
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						buttonAdvancedPropertiesActionPerformed(evt);
					}
				});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		panel_query_buttons.add(buttonAdvancedProperties, gridBagConstraints);

		buttonExecute.setMnemonic('x');
		buttonExecute.setText("Execute");
		buttonExecute.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonExecuteActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		panel_query_buttons.add(buttonExecute, gridBagConstraints);

		buttonSaveQuery.setText("Save");
		buttonSaveQuery.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonSaveActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
		panel_query_buttons.add(buttonSaveQuery, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridwidth = 3;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		add(panel_query_buttons, gridBagConstraints);
	}// </editor-fold>//GEN-END:initComponents

	private void getEQLSQLExpansionActionPerformed(
			java.awt.event.ActionEvent evt) {// GEN-FIRST:event_getEQLSQLExpansionActionPerformed
		OBDADataQueryAction action = this.getRetrieveEQLUnfoldingAction();
		action.run(eqlTextPane.getText(), null);
	}// GEN-LAST:event_getEQLSQLExpansionActionPerformed

	private void getSPARQLExpansionActionPerformed(
			java.awt.event.ActionEvent evt) {// GEN-FIRST:event_getSPARQLExpansionActionPerformed
		Thread queryRunnerThread = new Thread(new Runnable() {
			public void run() {

				OBDADataQueryAction action = QueryInterfacePanel.this
						.getRetrieveUCQExpansionAction();
				action.run(queryTextPane.getText(), null);
			}
		});
		queryRunnerThread.start();

	}// GEN-LAST:event_getSPARQLExpansionActionPerformed

	private void getSPARQLSQLExpansionActionPerformed(
			java.awt.event.ActionEvent evt) {// GEN-FIRST:event_getSPARQLSQLExpansionActionPerformed
		Thread queryRunnerThread = new Thread(new Runnable() {
			public void run() {
				OBDADataQueryAction action = QueryInterfacePanel.this
						.getRetrieveUCQUnfoldingAction();
				action.run(queryTextPane.getText(), null);
			}
		});
		queryRunnerThread.start();
	}// GEN-LAST:event_getSPARQLSQLExpansionActionPerformed

	private void buttonAdvancedPropertiesActionPerformed(
			java.awt.event.ActionEvent evt) {// GEN-FIRST:event_buttonAdvancedPropertiesActionPerformed
		JFrame protege_main_window = (JFrame) getParent().getParent()
				.getParent().getParent().getParent().getParent().getParent();
		JDialog dialog = new JDialog(protege_main_window);
		AdvancedQueryPropertiesPanel panel = new AdvancedQueryPropertiesPanel(
				dialog);
		dialog.getContentPane().add(panel, java.awt.BorderLayout.CENTER);
		dialog.pack();
		DialogUtils.centerDialogWRTParent(protege_main_window, dialog);
		dialog.setVisible(true);

	}// GEN-LAST:event_buttonAdvancedPropertiesActionPerformed

	private void buttonExecuteActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_buttonExecuteActionPerformed
		int index = jTabbedPane2.getSelectedIndex();
		boolean isSPARQLquery = false;
		boolean isEQLquery = false;
		if (index == 0) {
			isSPARQLquery = true;
		} else if (index == 1) {
			isEQLquery = true;
		}
		if (isSPARQLquery) {
			try {
				// TODO Handle this such that there is a listener checking the
				// progress of the execution

				Thread queryRunnerThread = new Thread(new Runnable() {

					public void run() {
						OBDADataQueryAction action = QueryInterfacePanel.this
								.getExecuteUCQAction();
						action.run(queryTextPane.getText(), instance);

						execTime = action.getExecutionTime();
						int rows = action.getNumberOfRows();
						updateStatus(rows);

					};
				});
				queryRunnerThread.start();

			} catch (Exception e) {
				e.printStackTrace(System.err);
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
		if (isEQLquery) {
			try {
				// TODO Handle this such that there is a listener checking the
				// progress of the execution
				Thread queryRunnerThread = new Thread(new Runnable() {
					public void run() {
						OBDADataQueryAction action = QueryInterfacePanel.this
								.getExecuteEQLAction();
						if (action != null) {
							action.run(eqlTextPane.getText(), instance);
							execTime = action.getExecutionTime();
							int rows = action.getNumberOfRows();
							updateStatus(rows);
						} else {
							throw new NullPointerException(
									"The run EQL query action has not been properlly set. Call QueryInterfacePanel.setEQLAction");
						}

					};
				});
				queryRunnerThread.start();

			} catch (Exception e) {
				e.printStackTrace(System.err);
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
	}// GEN-LAST:event_buttonExecuteActionPerformed

	private void buttonSaveActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_buttonSaveActionPerformed

		// JFrame protege_main_window = (JFrame)
		// ProtegeUI.getTopLevelContainer(OBDAPluginController.getCurrentInstance().getCurrentProject());
		JDialog saveDialog = new JDialog();
		String query = "";
		SaveQueryPanel savePanel;
		int index = jTabbedPane2.getSelectedIndex();
		boolean isSPARQLquery = false;
		boolean isEQLquery = false;
		if (index == 0) {
			isSPARQLquery = true;
		} else if (index == 1) {
			isEQLquery = true;
		}
		if (isSPARQLquery) {
			query = this.queryTextPane.getText();
		} else if (isEQLquery) {
			query = this.eqlTextPane.getText();
		}

		if ((currentGroup != null && currentGroup != "")
				&& (currentId != null && currentId != ""))
			savePanel = new SaveQueryPanel(query, saveDialog, qc, currentGroup,
					currentId);
		else if ((currentGroup != null && currentGroup != "")
				&& (currentId == null || currentId == ""))
			savePanel = new SaveQueryPanel(query, saveDialog, qc, currentGroup,
					currentId);
		else
			savePanel = new SaveQueryPanel(query, saveDialog, qc, currentId);// ?????

		saveDialog.getContentPane()
				.add(savePanel, java.awt.BorderLayout.CENTER);
		saveDialog.pack();
		DialogUtils.centerDialogWRTParent(this, saveDialog);
		saveDialog.setVisible(true);
	}// GEN-LAST:event_buttonSaveActionPerformed

	public void selectedQuerychanged(String new_group, String new_query,
			String new_id) {
		eqlTextPane.setText(new_query);
		queryTextPane.setText(new_query);
		currentGroup = new_group;
		currentId = new_id;
	}

	public void setExecuteUCQAction(OBDADataQueryAction executeUCQAction) {
		this.executeUCQAction = executeUCQAction;
	}

	public OBDADataQueryAction getExecuteUCQAction() {
		return executeUCQAction;
	}

	public void setExecuteEQLAction(OBDADataQueryAction executeEQLAction) {
		this.executeEQLAction = executeEQLAction;
	}

	public OBDADataQueryAction getExecuteEQLAction() {
		return executeEQLAction;
	}

	public void setRetrieveUCQExpansionAction(
			OBDADataQueryAction retrieveUCQExpansionAction) {
		this.retrieveUCQExpansionAction = retrieveUCQExpansionAction;
	}

	public OBDADataQueryAction getRetrieveUCQExpansionAction() {
		return retrieveUCQExpansionAction;
	}

	public void setRetrieveUCQUnfoldingAction(
			OBDADataQueryAction retrieveUCQUnfoldingAction) {
		this.retrieveUCQUnfoldingAction = retrieveUCQUnfoldingAction;
	}

	public OBDADataQueryAction getRetrieveUCQUnfoldingAction() {
		return retrieveUCQUnfoldingAction;
	}

	public void setRetrieveEQLUnfoldingAction(
			OBDADataQueryAction retrieveEQLUnfoldingAction) {
		this.retrieveEQLUnfoldingAction = retrieveEQLUnfoldingAction;
	}

	public OBDADataQueryAction getRetrieveEQLUnfoldingAction() {
		return retrieveEQLUnfoldingAction;
	}

	public void updateStatus(int rows) {

		Double d = Double.valueOf(execTime / 1000);
		String s = "Execution time: " + d
				+ " sec     Number of tuples retrieved: " + rows;
		jLabelStatus.setText(s);
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton buttonAdvancedProperties;
	private javax.swing.JButton buttonExecute;
	private javax.swing.JButton buttonSaveQuery;
	private javax.swing.JPanel eqlPanel;
	private javax.swing.JPopupMenu eqlPopupMenu;
	private javax.swing.JTextPane eqlTextPane;
	private javax.swing.JMenuItem getEQLSQLExpansion;
	private javax.swing.JMenuItem getSPARQLExpansion;
	private javax.swing.JMenuItem getSPARQLSQLExpansion;
	private javax.swing.JLabel jLabel18;
	private javax.swing.JLabel jLabelStatus;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel11;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JScrollPane jScrollPane4;
	private javax.swing.JTabbedPane jTabbedPane2;
	private javax.swing.JPanel panel_query_buttons;
	private javax.swing.JTextPane queryTextPane;
	private javax.swing.JPopupMenu sparqlPopupMenu;

	// End of variables declaration//GEN-END:variables

	// @Override
	public void tableChanged(TableModelEvent e) {
		Double d = Double.valueOf(execTime / 1000);
		int rows = ((IncrementalQueryResultTableModel) e.getSource())
				.getRowCount();
		String s = "Execution time: " + d
				+ " sec     Number of tuples retrieved: " + rows;
		jLabelStatus.setText(s);

	}

	public String getSPARQLQuery() {
		return queryTextPane.getText();
	}

	public String getEQLQuery() {
		return queryTextPane.getText();
	}

	public int getSelecetTab() {
		return jTabbedPane2.getSelectedIndex();
	}
}
