package dd.assistant.dataobject;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import dd.assistant.executable.CmdBase;
import dd.assistant.symbo_cmd.CmdSymbo;
import dd.assistant.symbo_xml.XmlSymbo;

public class Branch extends ElemBase {
	public Branch(Document doc, String id) {
		Element elm = doc.createElement(XmlSymbo.BranchNode.tagName);
		elm.setAttribute(XmlSymbo.BranchNode._id.tagName, id);
		this.init(elm);
	}
	public Branch(Element elm) {
		this.init(elm);
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.getAttribute(XmlSymbo.BranchNode._id.tagName);
	}
	@Override
	public String getContent(String queryType) {
		String Str="";
		
		if(queryType.equals(CmdSymbo._supplement.CONTENT.KEY)) {
			Str += CmdSymbo._supplement.NODENAME.KEY + "=" + this.getAttribute(XmlSymbo.BranchNode._name.tagName)+";\n";
			
			Str += CmdSymbo._supplement.INPUT.KEY+"={";
			NodeList portList = this.getInnerNode().getElementsByTagName(XmlSymbo.BranchNode.InputNode.tagName);
			for(int n=portList.getLength();n>0;n--) {
				Str += ((Element)portList.item(n-1)).getAttribute(XmlSymbo.BranchNode.InputNode._name.tagName) + 
						"[" +((Element)portList.item(n-1)).getAttribute(XmlSymbo.BranchNode.InputNode._type.tagName) + "],";
			}
			Str += "};\n";
			
			Str += CmdSymbo._supplement.OUTPUT.KEY+"={";
			portList = this.getInnerNode().getElementsByTagName(XmlSymbo.BranchNode.OutputNode.tagName);
			for(int n=portList.getLength();n>0;n--) {
				Str += ((Element)portList.item(n-1)).getAttribute(XmlSymbo.BranchNode.OutputNode._name.tagName) + 
						"[" +((Element)portList.item(n-1)).getAttribute(XmlSymbo.BranchNode.OutputNode._type.tagName) + "],";
			}
			Str += "}";
		}else if(queryType.equals(CmdSymbo._supplement.INTERCONTENT.KEY)){
			Str += CmdSymbo._supplement.NODENAME.KEY + "=" +this.getAttribute(XmlSymbo.BranchNode._name.tagName) + ";\n";
			
			Element program = (Element) this.getInnerNode().getParentNode().getParentNode().getParentNode();
			Element relationC = (Element) program.getElementsByTagName(XmlSymbo.RelationCollectionNode.tagName).item(0);
			NodeList relations = relationC.getElementsByTagName(XmlSymbo.RelationNode.tagName);
			for(int s = relations.getLength();s>0;s--) {
				String belongs = ((Element)relations.item(s-1)).getAttribute(XmlSymbo.RelationNode._belongs.tagName);
				if(belongs.equals(this.getId())) {
					String toOrigin = ((Element)relations.item(s-1)).getAttribute(XmlSymbo.RelationNode._to.tagName);
					Str += CmdSymbo._target.BRANCH + "=" + toOrigin + ",";
					Str += CmdSymbo._target.RELATION + "=" + ((Element)relations.item(s-1)).getAttribute(XmlSymbo.RelationNode._to.tagName)+";\n";
				}
			}
		}
		
		return Str;
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
			this.updateInner(XmlSymbo.BranchNode._name.tagName, tmp);
		
		tmp = env.getSupplement(CmdSymbo._supplement.IS_SIMPLE.KEY);
		if(tmp != null)
			this.updateInner(XmlSymbo.BranchNode._is_simple.tagName, tmp);
		
		tmp = env.getSupplement(CmdSymbo._supplement.IS_STATIC.KEY);
		if(tmp != null)
			this.updateInner(XmlSymbo.BranchNode._is_static.tagName, tmp);
		
		
		tmp= env.getSupplement(CmdSymbo._supplement.INPUT.KEY);
		if(tmp != null){
			tmp = tmp.replace("{", "").replace("}", "");
			String nodes[] = tmp.split(",");
			for(String n:nodes) {
				String name = n.split("\\[")[0];
				String type = n.split("\\[")[1].replace("\\]", "");
				Element input = this.getInnerNode().getOwnerDocument().createElement(XmlSymbo.BranchNode.InputNode.tagName);
				input.setAttribute(XmlSymbo.BranchNode.InputNode._name.tagName, name);
				input.setAttribute(XmlSymbo.BranchNode.InputNode._type.tagName, type);
				this.getInnerNode().appendChild(input);
			}
		}
		
		tmp= env.getSupplement(CmdSymbo._supplement.OUTPUT.KEY);
		if(tmp != null){
			tmp = tmp.replace("{", "").replace("}", "");
			String nodes[] = tmp.split(",");
			for(String n:nodes) {
				String name = n.split("\\[")[0];
				String type = n.split("\\[")[1].replace("\\]", "");
				Element output = this.getInnerNode().getOwnerDocument().createElement(XmlSymbo.BranchNode.OutputNode.tagName);
				output.setAttribute(XmlSymbo.BranchNode.OutputNode._name.tagName, name);
				output.setAttribute(XmlSymbo.BranchNode.OutputNode._type.tagName, type);
				this.getInnerNode().appendChild(output);
			}
		}
		
		
	}
	@Override
	public Map<String, String> getDefaultValuePair() {
		// TODO Auto-generated method stub
		HashMap<String, String> vp = new HashMap<String,String>();
		vp.put(CmdSymbo._supplement.NODENAME.KEY, "NO_NAME");
		vp.put(CmdSymbo._supplement.IS_SIMPLE.KEY, "NO");
		vp.put(CmdSymbo._supplement.IS_STATIC.KEY, "NO");
		
		return vp;
	}

}
