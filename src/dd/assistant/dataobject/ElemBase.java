package dd.assistant.dataobject;

import java.util.Map;

import org.w3c.dom.Element;

import dd.assistant.CBaseImplement;
import dd.assistant.executable.CmdBase;

public abstract class ElemBase extends CBaseImplement {
	private Element node = null;
	
	public ElemBase() {
		
	}
	protected void init(Element node) {
		this.node = node;
	}
	protected String getAttribute(String name) {
		return this.node.getAttribute(name);
	}
	public Element getInnerNode() {
		return this.node;
	}
	
	public abstract String getId();
	
	public void removeItSelfFromDOM() {
		this.node.getParentNode().removeChild(this.node);
	}
	public abstract String getContent(String queryType);
	protected void updateInner(String name, String val) {
		this.node.setAttribute(name, val);
	}
	public abstract void autoUpdate(CmdBase env) ;
	public void mountUnder(Element nf) {
		nf.appendChild(node);
	}
	public abstract Map<String,String> getDefaultValuePair();
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
