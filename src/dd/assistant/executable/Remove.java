package dd.assistant.executable;

import java.util.Map;

import org.w3c.dom.Document;

import dd.assistant.dataobject.ElemBase;
import dd.assistant.symbo_cmd.CmdSymbo;

public class Remove extends CmdBase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public Document ExecuteOperate(Document doc, Map<String, ElemBase> container) {
		String target = this.getTarget();
		if(target.equals(CmdSymbo._target.MODULE)) {
			
			this.removeOperate(CmdSymbo._constraint.MODULE_ID.KEY, container);
			
		}else if(target.equals(CmdSymbo._target.TEMPLATE)) {
			
			this.removeOperate(CmdSymbo._constraint.TEMPLATE_ID.KEY, container);
			
		}else if(target.equals(CmdSymbo._target.BRANCH)) {
			
			this.removeOperate(CmdSymbo._constraint.BRANCH_ID.KEY, container);
			
		}else if(target.equals(CmdSymbo._target.RELATION)) {
			
			this.removeOperate(CmdSymbo._constraint.RELATION_ID.KEY, container);
			
		}else if(target.equals(CmdSymbo._target.FUZZY)) {
			
			this.removeOperate(CmdSymbo._constraint.FUZZY_ID.KEY, container);
			
		}else {
			this.getReplySelector().operateError(target, "目标种类未定义");
			this.writeLog_Err(CmdSymbo._operate.REMOVE, "目标种类未定义");
		
		}
		return null;
		
	}
	
	private void removeOperate(String id_tagName,Map<String,ElemBase> container) {
		String id = this.getConstraint(id_tagName);
		ElemBase a = container.get(id);
		if(a == null) {
			this.getReplySelector().operateError(this.toString(), "目标实例不存在");
			this.writeLog_Err(CmdSymbo._operate.REMOVE, "目标实例不存在");
		}
		else {
			a.removeItSelfFromDOM();
			container.remove(id);
			this.getReplySelector().operateSuccess(this.toString(), "目标实例移除成功");
			this.writeLog_Operate(CmdSymbo._operate.REMOVE, "目标实例移除成功");
		}
	}

}
