package dd.assistant.executable;

import java.util.Map;

import org.w3c.dom.Document;

import dd.assistant.dataobject.ElemBase;

public class EmptyTask extends CmdBase{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public Document ExecuteOperate(Document doc, Map<String, ElemBase> container) {
		// TODO Auto-generated method stub
		this.getReplySelector().operateError(this.toString(), "无此命令");
		this.writeLog_Err("EmptyTask", "无此命令");
		return null;
	}
	
	@Override
	public void FeedCmd(String str) {}

}
