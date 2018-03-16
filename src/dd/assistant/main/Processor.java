package dd.assistant.main;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;

import dd.assistant.CBaseImplement;
import dd.assistant.dataobject.ElemBase;
import dd.assistant.executable.CmdBase;
import dd.assistant.symbo_cmd.CmdSymbo;

public class Processor extends CBaseImplement{
	private Document doc = null;
	private Map<String,ElemBase> objectContainer = new HashMap<String,ElemBase>();
	
	public Processor() {}
	
	public void Execute(CmdBase cmdBase) {
		if(this.doc == null && cmdBase.getTarget() != CmdSymbo._operate.LOAD_FILE) {
			this.getReplySelector().parameterWrong(this.toString(), "XML_FILE IS NOT LOADED");
			this.writeLog_Err("过早操作", "未载入数据前操作模型");
			return;
		}
		Document a = cmdBase.ExecuteOperate(doc, objectContainer);
		if(a != null)
			doc = a;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
