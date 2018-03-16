package dd.assistant.main;

import java.util.ArrayList;

import dd.assistant.CBaseImplement;
import dd.assistant.executable.CmdBase;

public class MainFrame extends CBaseImplement{
	private InputPort inPort = new InputPort();
	private Processor processor = new Processor();
	private ArrayList<CmdBase> exelist = new ArrayList<CmdBase>();
	
	public MainFrame() {
		OutputTidy outPort = new OutputTidy();
		outPort.initialOnce(null);
		this.inPort.loadOutputPort(outPort);
		this.processor.loadOutputPort(outPort);
		this.loadOutputPort(outPort);
	}
	
	//相当于死循环，抽取命令执行完所有命令，直到抽不出命令
	public void workLoop() {
		while(true) {
			if (exelist.size() == 0) {
				CmdBase aCmd = this.inPort.getCmd();
				if(aCmd == null)
					break;
				exelist.add(aCmd);
			}else {
				//每执行一次，取出第一个命令
				this.processor.Execute(exelist.get(0));
				exelist.remove(0);
				this.flushBuffer();
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainFrame main = new MainFrame();
		main.workLoop();
	}

}
//LOAD_FILE:<FILEPATH=APPOINT,>FILEPATH=/Volumes/Transcend/Project/cpp/SoftBase/Example.xml;
//SAVE_AS:<FILEPATH=APPOINT,>FILEPATH=/Volumes/Transcend/Project/Example.xml;
