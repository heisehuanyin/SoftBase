package dd.assistant.main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class OutputTidy {
	private PrintWriter logPort = null;
	private String defaultPath = "./log.txt";
	
	public void initialOnce(String newPath) {
		if(newPath != null)
			this.defaultPath = newPath;
		try {
			this.logPort = new PrintWriter(new BufferedWriter(new FileWriter(this.defaultPath)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void basicBufferFlush() {
		this.logPort.flush();
	}
	public void basicLogWrite(String msg) {
		this.logPort.println(msg);
	}

	
	//reply list
	public void parameterWrong(String className, String explain) {
		this.operateError(className, explain);
		System.out.println("ERROR:<PARAMETER_WRONG> " + explain + ";");
	}
	public void dataModelError(String constrain, String explain) {
		this.operateError(constrain, explain);
		System.out.println("ERROR:<DATAMODEL_ERROR> " + explain + ";");
	}
	public void operateSuccess(String className, String othermsg) {
		System.out.println("SUCCESS:<"+className+">"+"DONE;");
		System.out.println(othermsg+";");
	}
	public void operateError(String className, String othermsg) {
		System.out.println("ERROR:<"+className+">"+"BADRESULT;");
		System.out.println(othermsg+";");
	}
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OutputTidy one = new OutputTidy();
		one.parameterWrong(null, "fuck msg");
	}

}
