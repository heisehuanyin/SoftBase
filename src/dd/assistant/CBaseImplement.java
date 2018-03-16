package dd.assistant;

import dd.assistant.main.OutputTidy;

public abstract class CBaseImplement implements CBaseInterface {
	private OutputTidy output = null;

	@Override
	public void loadOutputPort(OutputTidy outPort) {
		// TODO Auto-generated method stub
		if(this.output != null) {
			this.output.basicLogWrite("ERROR:<OutputPort多次赋值> 程序中存在设计问题;"+this.getClass()+";");
			return;
		}
		this.output = outPort;
	}
	@Override
	public void flushBuffer() {
		this.output.basicBufferFlush();
	}
	
	@Override
	public void writeLog_Err(String className, String msg) {
		this.output.basicLogWrite("ERROR:<"+className+">"+msg+";");
	}
	@Override
	public void writeLog_Warning(String className, String msg) {
		this.output.basicLogWrite("WARNING:<"+className+">"+msg+";");
		
	}
	@Override
	public void writeLog_Operate(String className,String supplement) {
		this.output.basicLogWrite("OPERATE:<"+className+">"+supplement+";");
	}
	
	@Override
	public OutputTidy getReplySelector() {
		return this.output;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
