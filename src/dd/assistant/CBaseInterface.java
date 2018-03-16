package dd.assistant;

import dd.assistant.main.OutputTidy;

public interface CBaseInterface {
	void loadOutputPort(OutputTidy outPort);
	OutputTidy getReplySelector();
	void writeLog_Err(String className, String msg);
	void writeLog_Warning(String className, String msg);
	void writeLog_Operate(String className,String supplement);
	void flushBuffer();
}
