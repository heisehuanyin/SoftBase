package dd.assistant.dataobject;

import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import dd.assistant.executable.CmdBase;
import dd.assistant.symbo_cmd.CmdSymbo;
import dd.assistant.symbo_xml.XmlSymbo;

public class Relation extends ElemBase {
	public Relation(Document doc, String id) {
		Element elm = doc.createElement(XmlSymbo.RelationNode.tagName);
		elm.setAttribute(XmlSymbo.RelationNode._id.tagName, id);
		this.init(elm);
	}
	public Relation(Element elm) {
		this.init(elm);
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.getAttribute(XmlSymbo.RelationNode._id.tagName);
	}

	@Override
	public String getContent(String queryType) {
		// TODO Auto-generated method stub
		if(!queryType.equals(CmdSymbo._supplement.CONTENT.KEY))
			return null;
		
		String rtnStr = "";
		rtnStr += CmdSymbo._supplement.BELONGS.KEY + "=" + this.getAttribute(XmlSymbo.RelationNode._belongs.tagName)+";\n";
		rtnStr += CmdSymbo._supplement.FROM.KEY + "=" + this.getAttribute(XmlSymbo.RelationNode._from.tagName)+";\n";
		rtnStr += CmdSymbo._supplement.TO.KEY + "=" + this.getAttribute(XmlSymbo.RelationNode._to.tagName)+";\n";
		NodeList portList = this.getInnerNode().getElementsByTagName(XmlSymbo.RelationNode.PortNode.tagName);
		for(int n=portList.getLength();n>0;n--) {
			rtnStr += CmdSymbo._supplement.PORT.KEY+":"+ ((Element)portList.item(n-1))
					.getAttribute(XmlSymbo.RelationNode.PortNode._to.tagName) + "=" + ((Element)portList.item(n-1))
					.getAttribute(XmlSymbo.RelationNode.PortNode._from.tagName) + ";\n";
		}
		
		return rtnStr;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	@Override
	public void autoUpdate(CmdBase env) {
		// TODO Auto-generated method stub
		String tmp = null;
		
		tmp = env.getSupplement(CmdSymbo._supplement.TO.KEY);
		if(tmp != null)
			this.updateInner(XmlSymbo.RelationNode._to.tagName, tmp);
		
		tmp = env.getSupplement(CmdSymbo._supplement.FROM.KEY);
		if(tmp != null)
			this.updateInner(XmlSymbo.RelationNode._from.tagName, tmp);
		
		tmp = env.getConstraint(CmdSymbo._constraint.BRANCH_ID.KEY);
		if(tmp != null)
			this.updateInner(XmlSymbo.RelationNode._belongs.tagName, tmp);
	}
	@Override
	public Map<String, String> getDefaultValuePair() {
		// TODO Auto-generated method stub
		return null;
	}
}
