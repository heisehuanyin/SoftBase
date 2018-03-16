package dd.assistant.dataobject;

import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import dd.assistant.executable.CmdBase;
import dd.assistant.symbo_cmd.CmdSymbo;
import dd.assistant.symbo_xml.XmlSymbo;

public class Module extends ElemBase {
	public Module(Document doc, String id) {
		Element elm = doc.createElement(XmlSymbo.ModuleNode.tagName);
		elm.setAttribute(XmlSymbo.ModuleNode._id.tagName, id);
		this.init(elm);
	}
	public Module(Element elm) {
		this.init(elm);
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.getAttribute(XmlSymbo.ModuleNode._id.tagName);
	}

	@Override
	public String getContent(String queryType) {
		// TODO Auto-generated method stub
		if(!queryType.equals(CmdSymbo._supplement.CONTENT.KEY))
			return null;
		
		String str = "";
		str += CmdSymbo._supplement.NODENAME.KEY + "=" + 
				this.getAttribute(XmlSymbo.ModuleNode._name.tagName)+";\n";
		str += CmdSymbo._supplement.TYPE.KEY + "=" +
				this.getAttribute(XmlSymbo.ModuleNode._type.tagName);
		
		return str;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	@Override
	public void autoUpdate(CmdBase env) {
		// TODO Auto-generated method stub
		String tmp = null;
		
		tmp = env.getSupplement(CmdSymbo._supplement.NODENAME.KEY);
		if(tmp != null)
			this.updateInner(XmlSymbo.ModuleNode._name.tagName, tmp);
		
		tmp = env.getSupplement(CmdSymbo._supplement.TYPE.KEY);
		if(tmp != null)
			this.updateInner(XmlSymbo.ModuleNode._type.tagName, tmp);
		
	}
	@Override
	public Map<String, String> getDefaultValuePair() {
		// TODO Auto-generated method stub
		return null;
	}

}
