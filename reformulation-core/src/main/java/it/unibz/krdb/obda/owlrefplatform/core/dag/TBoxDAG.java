package it.unibz.krdb.obda.owlrefplatform.core.dag;

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

import it.unibz.krdb.obda.ontology.Description;

import java.util.Set;

/***
 * A class that provides TBox entailment computation based on a DAG
 * 
 * @author mariano
 * 
 */
public interface TBoxDAG {

	public Set<Set<Description>> getDirectChildren(Description desc);

	public Set<Set<Description>> getDirectParents(Description desc);

	public Set<Set<Description>> getDescendants(Description desc);

	public Set<Set<Description>> getAncestors(Description desc);

	public Set<Description> getEquiavlences(Description description);

	public void getNode();

}
