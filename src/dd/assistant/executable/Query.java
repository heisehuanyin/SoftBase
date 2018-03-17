package dd.assistant.executable;

import java.util.Map;

import org.w3c.dom.Document;

import dd.assistant.dataobject.ElemBase;
import dd.assistant.symbo_cmd.CmdSymbo;
import dd.assistant.symbo_xml.XmlSymbo;

public class Query extends CmdBase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public Document ExecuteOperate(Document doc, Map<String, ElemBase> container) {
		String target = this.getTarget();
		if(target.equals(CmdSymbo._target.MODEL)) {
			String t = this.getSupplement(CmdSymbo._supplement.QUERY.KEY);
			if(t.equals(CmdSymbo._supplement.MAIN_BRANCH.KEY)) {
				String val = doc.getDocumentElement().getAttribute(XmlSymbo.RootElementNode._ProgramSymbo.tagName);
				
				this.getReplySelector().operateSuccess(this.toString(), CmdSymbo._supplement.MAIN_BRANCH.KEY + "=" + val);
				this.writeLog_Operate(CmdSymbo._operate.QUERY, target+"查询成功");
			}
			
		}else if(target.equals(CmdSymbo._target.TEMPLATE)) {
			String queryType = this.getSupplement(CmdSymbo._supplement.QUERY.KEY);
			this.queryOperate(CmdSymbo._constraint.TEMPLATE_ID.KEY, container, queryType);
			
		}else if(target.equals(CmdSymbo._target.BRANCH)) {
			String queryType = this.getSupplement(CmdSymbo._supplement.QUERY.KEY);
			this.queryOperate(CmdSymbo._constraint.BRANCH_ID.KEY, container, queryType);
			
		}else if(target.equals(CmdSymbo._target.MODULE)) {
			String queryType = this.getSupplement(CmdSymbo._supplement.QUERY.KEY);
			this.queryOperate(CmdSymbo._constraint.MODULE_ID.KEY, container, queryType);
			
		}else if(target.equals(CmdSymbo._target.RELATION)) {
			String queryType = this.getSupplement(CmdSymbo._supplement.QUERY.KEY);
			this.queryOperate(CmdSymbo._constraint.RELATION_ID.KEY, container, queryType);
			
		}else if(target.equals(CmdSymbo._target.FUZZY)) {
			String queryType = this.getSupplement(CmdSymbo._supplement.QUERY.KEY);
			this.queryOperate(CmdSymbo._constraint.FUZZY_ID.KEY, container, queryType);
			
		}else {
			this.getReplySelector().operateError(this.toString(),"目标种类未定义");
			this.writeLog_Operate(CmdSymbo._operate.QUERY, "目标种类未定义");
		}
		
		
		return null;
	}
	
	private void queryOperate(String id_tagName, Map<String,ElemBase> container, String query_type) {
		String id = this.getConstraint(id_tagName);
		ElemBase estNode = container.get(id);
		if(estNode == null) {
			this.getReplySelector().operateError(this.toString(), "无此实例对象");
			this.writeLog_Err(CmdSymbo._operate.QUERY, "无此实例对象");
			return;
		}
		String content = estNode.getContent(query_type);
		if(content == null) {
			this.getReplySelector().operateError(this.toString(), "查询目标不存在，或存在其他错误");
			this.writeLog_Err(this.toString(), "查询目标不存在或存在去他错误");
			return;
		}
		this.getReplySelector().operateSuccess(this.toString(), content);
		this.writeLog_Operate(CmdSymbo._operate.QUERY, "查询成功");
	}

}
