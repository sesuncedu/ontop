package inf.unibz.it.obda.protege4.abox.materialization;

import inf.unibz.it.obda.api.controller.APIController;
import inf.unibz.it.obda.owlapi.abox.materialization.AboxMaterializer;
import inf.unibz.it.obda.protege4.core.OBDAPluginController;
import inf.unibz.it.utils.swing.OBDAProgessMonitor;
import inf.unibz.it.utils.swing.OBDAProgressListener;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.util.concurrent.CountDownLatch;

import javax.swing.JOptionPane;

import org.protege.editor.core.ui.action.ProtegeAction;
import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.model.OWLModelManager;
import org.semanticweb.owl.model.OWLOntology;
import org.semanticweb.owl.model.OWLOntologyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AboxMaterializationAction extends ProtegeAction {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -1211395039869926309L;

	private Logger log = LoggerFactory.getLogger(AboxMaterializationAction.class);
	
	@Override
	public void initialise() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (!(getEditorKit() instanceof OWLEditorKit) || !(getEditorKit().getModelManager() instanceof OWLModelManager))
			return;

		int response = JOptionPane
				.showConfirmDialog(
						this.getEditorKit().getWorkspace(),
						"This will use the mappings of the OWL-OBDA model \n to create a set of 'individual' assertions as specified \n by the mappings. \n\n This operation can take a long time and can require a lot of memory \n if the volume data retrieved by the mappings is high.",
						"Confirm", JOptionPane.OK_CANCEL_OPTION);
		if (response == JOptionPane.CANCEL_OPTION || response == JOptionPane.CLOSED_OPTION || response == JOptionPane.NO_OPTION)
			return;

		OWLEditorKit kit = (OWLEditorKit) this.getEditorKit();
		OWLModelManager mm = kit.getOWLModelManager();
		Container cont = this.getWorkspace().getRootPane().getParent();
		APIController obdaapi = ((OBDAPluginController)kit.get(APIController.class.getName())).getOBDAManager();
		
		
		
		try {
			OWLOntologyManager owlOntManager = mm.getOWLOntologyManager();
			OWLOntology owl_ont = mm.getActiveOntology();
			AboxMaterializer mat = new AboxMaterializer();

			OBDAProgessMonitor monitor = new OBDAProgessMonitor();
			CountDownLatch latch = new CountDownLatch(1);
			MaterializeAction action = new MaterializeAction(mat, obdaapi, owl_ont, owlOntManager, latch);
			monitor.addProgressListener(action);
			monitor.start();
			action.run();
			latch.await();
			monitor.stop();


		} catch (Exception e) {
			JOptionPane.showMessageDialog(cont, "ERROR: could not materialize abox.");
			log.error(e.getMessage(), e);
		}

	}

	private class MaterializeAction implements OBDAProgressListener {

		private Thread				thread	= null;
		
		private CountDownLatch		latch	= null;
		
		private AboxMaterializer	mat		= null;
		private OWLOntology			owl_ont	= null;
		private OWLOntologyManager	man		= null;
		APIController				obdapi	= null;

		public MaterializeAction(AboxMaterializer mat, APIController obdaapi, OWLOntology owl_ont, OWLOntologyManager man,
				CountDownLatch latch) {
			this.obdapi = obdaapi;
			this.mat = mat;
			this.owl_ont = owl_ont;
			this.man = man;
			this.latch = latch;
		}


		public void run() {
			thread = new Thread() {
				public void run() {
					try {
						mat.materializeAbox(obdapi, man, owl_ont);
						latch.countDown();
					} catch (Exception e) {
						latch.countDown();
						JOptionPane.showMessageDialog(null, "ERROR: could not materialize abox.");
					}
				}
			};
			thread.start();
		}

		@Override
		public void actionCanceled() {
			if (thread != null) {
				latch.countDown();
				thread.interrupt();
			}
		}

	}

}
