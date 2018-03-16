package dd.assistant.executable;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;

import dd.assistant.CBaseImplement;
import dd.assistant.dataobject.ElemBase;
import dd.assistant.symbo_cmd.CmdSymbo;

public abstract class CmdBase extends CBaseImplement{
	private String target = "";
	private Map<String,String> contraint = new HashMap<String,String>();
	private Map<String,String> supplement = new HashMap<String,String>();
	
	public CmdBase() {
		this.contraint.put("nop", "nop");
		this.supplement.put("nop", "nop");
	}

	public void FeedCmd(String cmdString) {
		String head = cmdString.split(":")[0];
		if(head.indexOf(CmdSymbo._target.MODEL)!=-1)
			this.setTarget(CmdSymbo._target.MODEL);
		
		else if (head.indexOf(CmdSymbo._target.TEMPLATE) != -1) 
			this.setTarget(CmdSymbo._target.TEMPLATE);
		
		else if (head.indexOf(CmdSymbo._target.MODULE)!= -1)
			this.setTarget(CmdSymbo._target.MODULE);
		
		else if(head.indexOf(CmdSymbo._target.BRANCH)!= -1)
			this.setTarget(CmdSymbo._target.BRANCH);
		
		else if(head.indexOf(CmdSymbo._target.RELATION)!= -1)
			this.setTarget(CmdSymbo._target.RELATION);
		
		else if(head.indexOf(CmdSymbo._target.FUZZY)!= -1) 
			this.setTarget(CmdSymbo._target.FUZZY);
		
		else if(head.indexOf(CmdSymbo._operate.LOAD_FILE)!= -1)
			this.setTarget(CmdSymbo._operate.LOAD_FILE);
		else 
			this.setTarget(CmdSymbo._operate.SAVE_AS);
		
		
		String cString = cmdString.split("<")[1].split(">")[0];
		if(cString.indexOf("=")!=-1) {
			String coString[] = cString.split(",");
			for(String a:coString){
				if(a.indexOf("=")!=-1) {
					this.setContraint(a.split("=")[0], a.split("=")[1]);
				}
			}
		}
		
		
		String sString = cmdString.split(">")[1];
		if(sString.indexOf("=")!=-1) {
			String supplementString[] = sString.split(";");
			for(String a:supplementString){
				if(a.indexOf("=")!=-1) {
					this.setSupplement(a.split("=")[0], a.split("=")[1]);
				}
			}
		}
	}
	
	abstract public Document ExecuteOperate(Document doc, Map<String, ElemBase> container);
	
	private void setTarget(String target) {
		this.target = target;
	}
	public String getTarget() {
		return this.target;
	}
	
	private void setContraint(String tag, String val) {
		this.contraint.put(tag, val);
	}
	public String getConstraint(String tag) {
		return this.contraint.get(tag);
	}
	
	private void setSupplement(String tag,String val) {
		this.supplement.put(tag, val);
	}
	public String getSupplement(String tag) {
		return this.supplement.get(tag);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
