package it.unibz.krdb.obda.owlrefplatform.core.reformulation;

/*
 * #%L
 * ontop-reformulation-core
 * %%
 * Copyright (C) 2009 - 2014 Free University of Bozen-Bolzano
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import it.unibz.krdb.obda.model.Function;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MinimalCQProducer {
	private final TreeWitnessReasonerLite reasoner;
	private List<Function> atoms = new LinkedList<Function>();
	private List<Function> noCheckAtoms = new LinkedList<Function>();
	
	public MinimalCQProducer(TreeWitnessReasonerLite reasoner) {
		this.reasoner = reasoner;
	}

	public MinimalCQProducer(MinimalCQProducer cqp) {
		this.reasoner = cqp.reasoner;
		this.atoms.addAll(cqp.atoms);
		this.noCheckAtoms.addAll(cqp.noCheckAtoms);
	}


	public boolean subsumes(Function atom) {
		for (Function a : atoms) 
			if (reasoner.isMoreSpecific(a, atom))
				return true;
		return false;
	}
	
	public void add(Function atom) {
		if (subsumes(atom))
			return;
		
		// removed all atoms that are subsumed
		Iterator<Function> i = atoms.iterator();
		while (i.hasNext()) {
			Function a = i.next();
			if (reasoner.isMoreSpecific(atom, a))
				i.remove();
		}
		atoms.add(atom);
	}
	
	public void addAll(Collection<Function> aa) {
		for (Function a : aa) 
			add(a);
	}

	public List<Function> getAtoms() {
		return atoms;
	}
	
	public void addNoCheck(Function atom) {
		noCheckAtoms.add(atom);
	}
	
	public void addAllNoCheck(Collection<Function> aa) {
		noCheckAtoms.addAll(aa);
	}

	public List<Function> getNoCheckAtoms() {
		return noCheckAtoms;
	}
}
