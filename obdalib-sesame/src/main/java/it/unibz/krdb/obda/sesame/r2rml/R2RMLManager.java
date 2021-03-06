package it.unibz.krdb.obda.sesame.r2rml;

/*
 * #%L
 * ontop-obdalib-sesame
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

/**
 * @author timea bagosi
 * Class responsible of parsing R2RML mappings from file or from an RDF graph
 */

import it.unibz.krdb.obda.model.CQIE;
import it.unibz.krdb.obda.model.Function;
import it.unibz.krdb.obda.model.OBDADataFactory;
import it.unibz.krdb.obda.model.OBDALibConstants;
import it.unibz.krdb.obda.model.OBDAMappingAxiom;
import it.unibz.krdb.obda.model.Predicate;
import it.unibz.krdb.obda.model.Term;
import it.unibz.krdb.obda.model.Variable;
import it.unibz.krdb.obda.model.impl.OBDADataFactoryImpl;
import it.unibz.krdb.obda.model.impl.OBDAVocabulary;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openrdf.model.Graph;
import org.openrdf.model.Resource;
import org.openrdf.model.Statement;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFParser;
import org.openrdf.rio.Rio;
import org.openrdf.rio.helpers.StatementCollector;

public class R2RMLManager {
	
	private OBDADataFactory fac = OBDADataFactoryImpl.getInstance();
	
	private org.openrdf.model.Graph myGraph;
	
	private R2RMLParser r2rmlParser;
	
	/**
	 * Constructor to start parsing R2RML mappings from file.
	 * @param file - the full path of the file
	 */
	public R2RMLManager(String file) {
		this(new File(file));
	}
	
	/**
	 * Constructor to start parsing R2RML mappings from file.
	 * @param file - the File object
	 */
	public R2RMLManager(File file) {
		try {
			r2rmlParser = new R2RMLParser();
			RDFParser parser = Rio.createParser(RDFFormat.TURTLE);
			InputStream in = new FileInputStream(file);
			URL documentUrl = new URL("file://" + file);
			myGraph = new org.openrdf.model.impl.GraphImpl();
			StatementCollector collector = new StatementCollector(myGraph);
			parser.setRDFHandler(collector);
			parser.parse(in, documentUrl.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Constructor to start the parser from an RDF graph
	 * @param graph - the sesame graph containing mappings
	 */
	public R2RMLManager(Graph graph){
		myGraph = graph;
		r2rmlParser = new R2RMLParser();
		
	}
	
	/**
	 * Get the graph of mappings
	 * @return the Graph object containing the mappings
	 */
	public Graph getGraph() {
		Iterator<Statement> it = myGraph.iterator();
		while (it.hasNext())
			System.out.println(it.next());
		return myGraph;
	}
	
	/**
	 * This method return the list of mappings from the Graph main method to be
	 * called, assembles everything
	 * @param myGraph - the graph structure containing mappings
	 * @return ArrayList<OBDAMappingAxiom> - list of mapping axioms read from the graph
	 */
	public ArrayList<OBDAMappingAxiom> getMappings(Graph myGraph) {

		ArrayList<OBDAMappingAxiom> mappings = new ArrayList<OBDAMappingAxiom>();

		// retrieve the TriplesMap nodes
		Set<Resource> tripleMaps = r2rmlParser.getMappingNodes(myGraph);

		for (Resource tripleMap : tripleMaps) {

			// for each node get a mapping
			OBDAMappingAxiom mapping;

			try {
				mapping = getMapping(myGraph, tripleMap);

				// add it to the list of mappings
				mappings.add(mapping);

				// pass 2 - check for join conditions, add to list
				List<OBDAMappingAxiom> joinMappings = getJoinMappings(myGraph, tripleMap);
				if (joinMappings != null) {
					mappings.addAll(joinMappings);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mappings;
	}
	
	/**
	 * Method to get an OBDAMappingAxiom from a Resource node in the given Graph
	 * @param myGraph - the graph containing mappings
	 * @param subj - the Resource node of one mapping
	 * @return OBDAMappingAxiom - the mapping axiom read from the graph
	 */
	private OBDAMappingAxiom getMapping(Graph myGraph, Resource subj) throws Exception {
		String sourceQuery = r2rmlParser.getSQLQuery(myGraph, subj);
		List<Function> body = getMappingTripleAtoms(myGraph, subj);
		Function head = getHeadAtom(body);
		CQIE targetQuery = fac.getCQIE(head, body);
		OBDAMappingAxiom mapping = fac.getRDBMSMappingAxiom("mapping-"+subj.stringValue(), sourceQuery, targetQuery);
		return mapping;
	}
	
	/**
	 * Method to get the join OBDAMappingAxioms from a Resource node in the given Graph
	 * @param myGraph - the Graph structure containing mappings
	 * @param tripleMap - the Resource node of a mapping
	 * @return List<OBDAMappingAxiom> - the list of join mappings read from the given node
	 */
	private List<OBDAMappingAxiom> getJoinMappings(Graph myGraph, Resource tripleMap) throws Exception {
		String sourceQuery = "";
		
		//get all predicateobject nodes that contain joins for the subj triplemap given
		List<Resource> joinNodes = r2rmlParser.getJoinNodes(myGraph, tripleMap);
		if (!joinNodes.isEmpty()) {
			List<OBDAMappingAxiom> joinMappings = new ArrayList<OBDAMappingAxiom>(joinNodes.size());
			
			//get subject sql string and newliteral of given node
			String sourceQuery1 = r2rmlParser.getSQLQuery(myGraph, tripleMap);
			Term joinSubject1 = r2rmlParser.getSubjectAtom(myGraph, tripleMap);
			Term joinSubject1Child = r2rmlParser.getSubjectAtom(myGraph, tripleMap, "CHILD_");
			
			int idx = 1;
			//for each predicateobject map that contains a join
			for (Resource joinPredObjNode : joinNodes) {
				//get the predicates
				List<Predicate> joinPredicates = r2rmlParser.getBodyPredicates(myGraph, joinPredObjNode);
				
				//get the referenced triple map node
				Resource referencedTripleMap = r2rmlParser.getReferencedTripleMap(myGraph, joinPredObjNode);
				
				//get the referenced triple map sql query and subject atom
				String sourceQuery2 = r2rmlParser.getSQLQuery(myGraph, referencedTripleMap);
				Term joinSubject2 = r2rmlParser.getSubjectAtom(myGraph, referencedTripleMap);
				Term joinSubject2Parent = r2rmlParser.getSubjectAtom(myGraph, referencedTripleMap, "PARENT_");
				
				//get join condition
				String childCol = r2rmlParser.getChildColumn(myGraph, joinPredObjNode);
				String parentCol = r2rmlParser.getParentColumn(myGraph, joinPredObjNode);
				
				
				List<Function> body = new ArrayList<Function>();
				// construct the atom from subject 1 and 2
				List<Term> terms = new ArrayList<Term>();
				
				
				//if join condition is empty, the two sql queries are the same
				if (childCol == null || parentCol == null) {
					sourceQuery = sourceQuery1;
					terms.add(joinSubject1);
					terms.add(joinSubject2);
				} else {
					//TODO replace this workaround
					//have to introduce alias as Child and as Parent 
					sourceQuery = "SELECT * FROM ("+sourceQuery1 + ") as CHILD, (" + sourceQuery2 + ") as PARENT " +
							"WHERE CHILD." + childCol + " = PARENT." + parentCol;
					terms.add(joinSubject1Child);
					terms.add(joinSubject2Parent);
				}
				
				//for each predicate construct an atom and add to body
				for (Predicate pred : joinPredicates) {
					Function bodyAtom = fac.getFunction(pred, terms);
					body.add(bodyAtom);
				}
				//get head and construct cqie
				Function head = getHeadAtom(body);
				CQIE targetQuery = fac.getCQIE(head, body);
				
				if (sourceQuery.isEmpty()) {
					throw new Exception("Could not create source query for join in "+tripleMap.stringValue());
				}
				//finally, create mapping and add it to the list
				OBDAMappingAxiom mapping = fac.getRDBMSMappingAxiom("mapping-join"+idx+"-"+tripleMap.stringValue(), sourceQuery, targetQuery);
				idx++;
				System.out.println("WARNING joinMapping introduced : "+mapping.toString());
				System.out.println("Renaming of variables as CHILD_ and PARENT_ introduced!");
				
				joinMappings.add(mapping);
			}
			return joinMappings;
		}
		return null;
	}
		
	/**
	 * construct head of mapping q(variables) from the body
	 * @param body - the body of the mapping
	 * @return Function - the head of the mapping
	 */
	private Function getHeadAtom(List<Function> body) {
		Set<Variable> vars = new HashSet<Variable>();
		for (Function bodyAtom : body) {
			 vars.addAll(bodyAtom.getReferencedVariables());
		}
		int arity = vars.size();
		List<Term> dvars = new ArrayList<Term>(vars);
		Function head = fac.getFunction(fac.getPredicate(OBDALibConstants.QUERY_HEAD, arity, null), dvars);
		return head;
	}
	
	/**
	 * method to get the body atoms of the mapping from a given Resource node in the Graph
	 * @param myGraph - the rdf graph containing mappings
	 * @param subj - the Resource node of the specific mapping
	 * @return List<Function> - the body of the mapping constructed from the graph
	 */
	private List<Function> getMappingTripleAtoms(Graph myGraph, Resource subj) throws Exception {
		//the body to return
		List<Function> body = new ArrayList<Function>();
				
		//get subject
		Term subjectAtom = r2rmlParser.getSubjectAtom(myGraph, subj);		
		
		//get any class predicates, construct atom Class(subject), add to body
		List<Predicate> classPredicates = r2rmlParser.getClassPredicates();
		for (Predicate classPred : classPredicates) {
			body.add(fac.getFunction(classPred, subjectAtom));
		}		
		//get predicate-object nodes
		Set<Resource> predicateObjectNodes = r2rmlParser.getPredicateObjects(myGraph, subj);	
		
		for (Resource predobj : predicateObjectNodes) {
			//for each predicate object map
			
			//get body predicate
			List<Predicate> bodyPredicates = r2rmlParser.getBodyPredicates(myGraph, predobj);
			//predicates that contain a variable are separately treated
			List<Function> bodyURIPredicates = r2rmlParser.getBodyURIPredicates(myGraph, predobj);
			
			//get object atom
			Term objectAtom = r2rmlParser.getObjectAtom(myGraph, predobj);
			if (objectAtom == null) {
				// skip, object is a join
				continue;
			}
			
			// construct the atom, add it to the body
			List<Term> terms = new ArrayList<Term>();
			terms.add(subjectAtom);
			
			
			for (Predicate bodyPred : bodyPredicates) {
				//for each predicate if there are more in the same node
				
				//check if predicate = rdf:type
				if (bodyPred.toString().equals(OBDAVocabulary.RDF_TYPE)) {
					//create term triple(subjAtom, URI("...rdf_type"), objAtom)
						Predicate newpred = OBDAVocabulary.QUEST_TRIPLE_PRED;
						Predicate uriPred = fac.getUriTemplatePredicate(1);
						Function rdftype = fac.getFunction(uriPred, fac.getConstantLiteral(OBDAVocabulary.RDF_TYPE));
						terms.add(rdftype);
						terms.add(objectAtom);
						body.add(fac.getFunction(newpred, terms));
				} else {
					// create predicate(subject, object) and add it to the body
					terms.add(objectAtom);
					Function bodyAtom = fac.getFunction(bodyPred, terms);
					body.add(bodyAtom);
				}
			}
			
			//treat predicates that contain a variable (column or template declarations)
			for (Function predFunction : bodyURIPredicates) {
				//create triple(subj, predURIFunction, objAtom) terms
				Predicate newpred = OBDAVocabulary.QUEST_TRIPLE_PRED;
				terms.add(predFunction);
				terms.add(objectAtom);
				body.add(fac.getFunction(newpred, terms));
			}
		}
		return body;
	}
}
