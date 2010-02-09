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
package inf.unibz.it.obda.codec.xml.query;


import inf.unibz.it.obda.gui.swing.querycontroller.tree.QueryGroupTreeElement;
import inf.unibz.it.obda.gui.swing.querycontroller.tree.QueryTreeElement;
import inf.unibz.it.obda.gui.swing.querycontroller.tree.TreeElement;

import java.util.Vector;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

//TODO: refactor as a codec
public class XMLRenderer {
	public Element render(Element parent, QueryTreeElement query) {
		Document doc = parent.getOwnerDocument();
		Element query_element = doc.createElement("Query");
		query_element.setAttribute("id", query.getID());
		query_element.setAttribute("text", query.getQuery());
		return query_element;
	}
	
	
	public Element render(Element parent, QueryGroupTreeElement group) {
		Document doc = parent.getOwnerDocument();
		Element group_element = doc.createElement("QueryGroup");
		group_element.setAttribute("id", group.getID());
		Vector<QueryTreeElement> queries = group.getQueries();
		for (QueryTreeElement query : queries) {
			Element query_element = render(group_element, query);
			group_element.appendChild(query_element);
		}
		return group_element;
	}
	
	public Element render(Element parent, TreeElement element) {
		if (element instanceof QueryTreeElement) {
			return render(parent, (QueryTreeElement)element);
		} else if (element instanceof QueryGroupTreeElement) {
			return render(parent, (QueryGroupTreeElement)element);
		}
		return null;
	}
}
