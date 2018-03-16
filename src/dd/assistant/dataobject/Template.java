package dd.assistant.dataobject;

import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import dd.assistant.executable.CmdBase;
import dd.assistant.symbo_cmd.CmdSymbo;
import dd.assistant.symbo_xml.XmlSymbo;

public class Template extends ElemBase {
	
	//用于新建操作，增加节点，使用前确保ID唯一性
	public Template(Document doc, String id) {
		Element node = doc.createElement(XmlSymbo.TemplateNode.tagName);
		node.setAttribute(XmlSymbo.TemplateNode._id.tagName, id);
		this.init(node);
	}
	//用于载入操作，既有节点
	public Template(Element elm) {
		this.init(elm);
	}
	@Override
	public String getContent(String queryType) {
		// TODO Auto-generated method stub
		if(!queryType.equals(CmdSymbo._supplement.CONTENT.KEY))
			return null;

		String rtnStr = "";
		rtnStr += CmdSymbo._supplement.NODENAME.KEY + "=" + this.getAttribute(XmlSymbo.TemplateNode._name.tagName)+";\n";
		rtnStr += CmdSymbo._supplement.IS_SIMPLE.KEY +"=" + this.getAttribute(XmlSymbo.TemplateNode._is_simple.tagName)+";\n";
		rtnStr += CmdSymbo._supplement.EXTEND.KEY+"="+this.getAttribute(XmlSymbo.TemplateNode._extend.tagName)+";\n";
		rtnStr += "BRANCH_LIST={";
		NodeList branchList = this.getInnerNode().getElementsByTagName(XmlSymbo.BranchNode.tagName);
		for(int n = branchList.getLength();n>0;n--) {
			Node m = branchList.item(n-1);
			rtnStr += ((Element)m).getAttribute(XmlSymbo.BranchNode._id.tagName) + ",";
		}
		rtnStr += "}";
		
		return rtnStr;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.getAttribute(XmlSymbo.TemplateNode._id.tagName);
	}

	@Override
	public void autoUpdate(CmdBase env) {
		// TODO Auto-generated method stub
		String tmp = null;
		
		tmp = env.getSupplement(CmdSymbo._supplement.NODENAME.KEY);
		if(tmp != null)
			this.updateInner(XmlSymbo.TemplateNode._name.tagName, tmp);
		
		tmp = env.getSupplement(CmdSymbo._supplement.IS_SIMPLE.KEY);
		if(tmp!=null)
			this.updateInner(XmlSymbo.TemplateNode._is_simple.tagName,tmp);
		
		tmp = env.getSupplement(CmdSymbo._supplement.EXTEND.KEY);
		if(tmp != null)
			this.updateInner(XmlSymbo.TemplateNode._extend.tagName, tmp);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	@Override
	public Map<String, String> getDefaultValuePair() {
		// TODO Auto-generated method stub
		return null;
	}

}
