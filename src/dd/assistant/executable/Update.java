package dd.assistant.executable;

import java.util.Map;

import org.w3c.dom.Document;

import dd.assistant.dataobject.ElemBase;
import dd.assistant.symbo_cmd.CmdSymbo;
import dd.assistant.symbo_xml.XmlSymbo;

public class Update extends CmdBase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public Document ExecuteOperate(Document doc, Map<String, ElemBase> container) {
		String target = this.getTarget();
		if(target.equals(CmdSymbo._target.MODEL)) {
			String mainbranch = this.getSupplement(CmdSymbo._supplement.MAIN_BRANCH.KEY);
			String name = this.getSupplement(CmdSymbo._supplement.NODENAME.KEY);
			
			if(name != null)
				doc.getDocumentElement().setAttribute(XmlSymbo.RootElementNode._Name.tagName, name);
			
			if(mainbranch != null)
				doc.getDocumentElement().setAttribute(XmlSymbo.RootElementNode._ProgramSymbo.tagName, mainbranch);
			
		}else if(target.equals(CmdSymbo._target.TEMPLATE)) {
			
			this.autoUpdateOperate(CmdSymbo._constraint.TEMPLATE_ID.KEY, container);
			
		}else if(target.equals(CmdSymbo._target.BRANCH)) {
			
			this.autoUpdateOperate(CmdSymbo._constraint.BRANCH_ID.KEY, container);
			
		}else if(target.equals(CmdSymbo._target.MODULE)) {

			this.autoUpdateOperate(CmdSymbo._constraint.MODULE_ID.KEY, container);
			
		}else if(target.equals(CmdSymbo._target.RELATION)) {

			this.autoUpdateOperate(CmdSymbo._constraint.RELATION_ID.KEY, container);
			
		}else if(target.equals(CmdSymbo._target.FUZZY)) {

			this.autoUpdateOperate(CmdSymbo._constraint.FUZZY_ID.KEY, container);
			
		}else {
			this.getReplySelector().operateError(this.toString(),"目标种类未定义");
			this.writeLog_Operate(CmdSymbo._operate.QUERY, "目标种类未定义");
		}
		
		return doc;
	}

	public void autoUpdateOperate(String nodeType, Map<String,ElemBase> container) {

		String id = this.getConstraint(nodeType);
		ElemBase estNode = container.get(id);
		if(estNode != null) {
			estNode.autoUpdate(this);
			this.getReplySelector().operateSuccess(this.toString(), "更新成功");
			this.writeLog_Operate(this.toString(), "更新成功");
		}else {
			this.getReplySelector().operateError(this.toString(), "无此目标实例");
			this.writeLog_Err(this.toString(), "查不到该目标实例");
		}
	}
	
}
