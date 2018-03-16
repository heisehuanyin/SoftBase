package dd.assistant.executable;

import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import dd.assistant.dataobject.Branch;
import dd.assistant.dataobject.ElemBase;
import dd.assistant.dataobject.Module;
import dd.assistant.dataobject.Relation;
import dd.assistant.dataobject.Template;
import dd.assistant.symbo_cmd.CmdSymbo;
import dd.assistant.symbo_xml.XmlSymbo;

public class Add extends CmdBase{
	
	public ElemBase uniqueEnsure(Map<String,ElemBase> container, String id) {
		ElemBase one = container.get(id);
		return one;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public Document ExecuteOperate(Document doc, Map<String, ElemBase> container) {
		// TODO Auto-generated method stub
		String target = this.getTarget();
		
		if(target.equals(CmdSymbo._target.TEMPLATE)) {
			String id = this.getSupplement(CmdSymbo._supplement.TEMPLATE_ID.KEY);
			
			if(id != null && this.uniqueEnsure(container, id) == null) {
				ElemBase newNode = new Template(doc,id);
				newNode.autoUpdate(this);
				Element f = (Element)doc.getElementsByTagName(XmlSymbo.TemplateCollectionNode.tagName).item(0);
				newNode.mountUnder(f);
				
				container.put(id, newNode);
				
				this.getReplySelector().operateSuccess(this.toString(), "添加TEMPLATE操作成功");
				this.writeLog_Operate(CmdSymbo._operate.ADD, "成功添加");
			}else {
				this.getReplySelector().operateError(this.toString(), CmdSymbo._supplement.TEMPLATE_ID.KEY
					+"="+id+"已存在或未指定ID");
				this.writeLog_Err(CmdSymbo._operate.ADD, CmdSymbo._supplement.TEMPLATE_ID.KEY+"值重复或未指定");
			}
			
		}else if(target.equals(CmdSymbo._target.BRANCH)) {
			String id = this.getSupplement(CmdSymbo._supplement.BRANCH_ID.KEY);
			
			if(id != null && this.uniqueEnsure(container, id) == null) {
				ElemBase newNode = new Branch(doc,id);
				newNode.autoUpdate(this);
				String t_id = this.getConstraint(CmdSymbo._constraint.TEMPLATE_ID.KEY);
				ElemBase ttttmp = container.get(t_id);
				if(ttttmp == null) {
					this.getReplySelector().operateError(this.toString(), CmdSymbo._target.TEMPLATE + "不存在");
					this.writeLog_Err(CmdSymbo._operate.ADD, CmdSymbo._target.TEMPLATE + "不存在");
					return null;
				}
				Element f = ttttmp.getInnerNode();
				newNode.mountUnder(f);
				
				container.put(id, newNode);
				
				this.getReplySelector().operateSuccess(this.toString(),"Branch添加操作成功");
				this.writeLog_Operate(CmdSymbo._operate.ADD, "Branch添加成功");
				
			}else {
				this.getReplySelector().operateError(this.toString(), CmdSymbo._supplement.BRANCH_ID.KEY
					+"="+id+"已存在或未指定ID");
				this.writeLog_Err(CmdSymbo._operate.ADD, CmdSymbo._supplement.BRANCH_ID.KEY+"值重复或未指定");
			}
			
			
		}else if(target.equals(CmdSymbo._target.MODULE)) {
			String id = this.getSupplement(CmdSymbo._supplement.MODULE_ID.KEY);
			
			if(id != null && this.uniqueEnsure(container, id) == null) {
				ElemBase newNode = new Module(doc, id);
				Element f = (Element)doc.getElementsByTagName(XmlSymbo.ModuleCollectionNode.tagName).item(0);
				newNode.mountUnder(f);
				newNode.autoUpdate(this);
				
				container.put(id, newNode);

				this.getReplySelector().operateSuccess(this.toString(), CmdSymbo._target.MODULE +"添加操作成功");
				this.writeLog_Operate(CmdSymbo._operate.ADD, "Module添加成功");
			}else {
				this.getReplySelector().operateError(this.toString(), CmdSymbo._supplement.MODULE_ID.KEY
					+"="+id+"已存在或未指定ID");
				this.writeLog_Err(CmdSymbo._operate.ADD, CmdSymbo._supplement.MODULE_ID.KEY+"值重复或未指定");
			}
			
		}else if(target.equals(CmdSymbo._target.RELATION)) {
			String id = this.getSupplement(CmdSymbo._supplement.RELATION_ID.KEY);
			
			if(id != null && this.uniqueEnsure(container, id) == null) {
				ElemBase _t = new Relation(doc,id);
				Element f = (Element)doc.getElementsByTagName(XmlSymbo.RelationCollectionNode.tagName).item(0);
				_t.mountUnder(f);
				_t.autoUpdate(this);

				container.put(id, _t);
				
				this.getReplySelector().operateSuccess(this.toString(),
						CmdSymbo._target.RELATION+"添加操作成功");
				this.writeLog_Operate(CmdSymbo._operate.ADD, CmdSymbo._target.RELATION+"添加成功");
			}else {
				this.getReplySelector().operateError(this.toString(), CmdSymbo._supplement.RELATION_ID.KEY
					+"="+id+"已存在或未指定ID");
				this.writeLog_Err(CmdSymbo._operate.ADD, CmdSymbo._supplement.RELATION_ID.KEY+"值重复或未指定");
			}
			
		}
		

		
		return doc;
	}

}
