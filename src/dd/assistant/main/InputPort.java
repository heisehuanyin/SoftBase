package dd.assistant.main;

import java.util.Scanner;

import dd.assistant.CBaseImplement;
import dd.assistant.executable.AboutSystem;
import dd.assistant.executable.Add;
import dd.assistant.executable.CmdBase;
import dd.assistant.executable.EmptyTask;
import dd.assistant.executable.Query;
import dd.assistant.executable.Remove;
import dd.assistant.executable.Update;
import dd.assistant.symbo_cmd.CmdSymbo;

public class InputPort extends CBaseImplement{
	private Scanner source = new Scanner(System.in);
	
	public CmdBase getCmd() {
		CmdBase one = null;
		String cmdString = source.nextLine();
		this.getReplySelector().basicLogWrite("\n//输入命令/////////////////////////////////////////////////");
		this.getReplySelector().basicLogWrite(cmdString);
		this.getReplySelector().basicLogWrite("==执行结果=================================================");
		
		String head = cmdString.split(":")[0];
		
		//？操作命令？
		if(head.indexOf(CmdSymbo._operate.ADD)!=-1)
			one = new Add();
		else if(head.indexOf(CmdSymbo._operate.REMOVE)!= -1)
			one = new Remove();
		else if(head.indexOf(CmdSymbo._operate.QUERY)!= -1)
			one = new Query();
		else if(head.indexOf(CmdSymbo._operate.UPDATE)!= -1)
			one = new Update();
		
		//？系统命令？
		else if(head.indexOf(CmdSymbo._operate.LOAD_FILE)!= -1 ||
				head.indexOf(CmdSymbo._operate.SAVE_AS)!= -1)
			one = new AboutSystem();
		
		else {
			this.getReplySelector().operateError(this.toString(), "命令解析查无此命令");
			this.writeLog_Err(this.toString(), "无此命令");
			one = new EmptyTask();
		}
		
		
		one.FeedCmd(cmdString);
		one.loadOutputPort(this.getReplySelector());
		return one;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputPort ab = new InputPort();
		ab.getCmd();
	}

}
