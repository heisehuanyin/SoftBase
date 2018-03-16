package dd.assistant.executable;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import dd.assistant.dataobject.Branch;
import dd.assistant.dataobject.ElemBase;
import dd.assistant.dataobject.Module;
import dd.assistant.dataobject.Relation;
import dd.assistant.dataobject.Template;
import dd.assistant.symbo_cmd.CmdSymbo;
import dd.assistant.symbo_xml.XmlSymbo;
//TODO:命令参数的分析
//TODO：文件所有权之类的，错误处理等信息
public class AboutSystem extends CmdBase {

	@Override
	public Document ExecuteOperate(Document doc, Map<String, ElemBase> container) {
		if(this.getTarget().equals(CmdSymbo._operate.SAVE_AS)) {
			if(this.getConstraint(CmdSymbo._constraint.FILEPATH.KEY	).
					equals(CmdSymbo._constraint.FILEPATH.V_MANUALLY)) {
				String pp = this.getSupplement(CmdSymbo._supplement.FILEPATH.KEY);
				if(doc == null) {
					this.writeLog_Err(this.toString(), "DOM数据模型为NULL");
					this.getReplySelector().dataModelError(this.toString(),
							"DOM数据模型为NULL");
				}else {
					new SaveAsOperate().SaveAtPath(pp, doc);
					this.getReplySelector().operateSuccess(this.toString(),
							CmdSymbo._supplement.FILEPATH.KEY+ "=" + pp);
					this.writeLog_Operate(this.toString(), "成功执行了SAVA操作");
				}
			}else {
				this.getReplySelector().parameterWrong(this.toString(),
						CmdSymbo._operate.SAVE_AS + "命令使用了错误的约束");
				this.writeLog_Err(this.toString(), CmdSymbo._operate.SAVE_AS +"使用了不能理解的约束条件");
			}
			return null;
		}else {
			NewDataFileOperate hub = new NewDataFileOperate();
			hub.initial();
			
			String kind = this.getConstraint(CmdSymbo._constraint.FILEPATH.KEY);
			if(kind == null) {
				this.getReplySelector().parameterWrong(this.toString(),
						CmdSymbo._operate.LOAD_FILE + "命令格式错误");
				this.writeLog_Err(this.toString(), "新建数据文件使用了不能理解的约束条件");
				return null;
			}else if(kind.equals(CmdSymbo._constraint.FILEPATH.V_MANUALLY)) {
				String path = this.getSupplement(CmdSymbo._supplement.FILEPATH.KEY);
				if(!(new File(path)).exists()) {
					this.getReplySelector().parameterWrong(this.toString(), CmdSymbo._supplement.FILEPATH.KEY+"="+path+";文件不存在");
					this.writeLog_Err(this.toString(), "文件不存在");
					return null;
				}
				this.getReplySelector().operateSuccess(this.toString(), "FILEPATH="+path);
				this.writeLog_Operate(this.toString(), "FILEPATH="+path);
				return hub.loadDataFileForParse(path, container);
			}else if(kind.equals(CmdSymbo._constraint.FILEPATH.V_DEFAULT)) {
				this.getReplySelector().operateSuccess(this.toString(), "创建默认模板文档");
				this.writeLog_Operate(this.toString(), "创建默认模板文档，当前未保存");
				return hub.createDefaultDocument();
			}else {
				this.getReplySelector().parameterWrong(this.toString(), "在新建数据文件时候，使用了不能理解的约束条件；");
				this.writeLog_Err(this.toString(), "新建数据文件使用了不能理解的约束条件");
				return null;
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}



	class SaveAsOperate{
		public void SaveAtPath(String path, Document doc) {
			TransformerFactory factory_t = TransformerFactory.newInstance();
			Transformer former = null;
			try {
				former = factory_t.newTransformer();
				former.setOutputProperty(OutputKeys.INDENT,"yes");  //设置输出时XML换行;
				former.transform(new DOMSource(doc),new StreamResult(new File(path)));
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				AboutSystem.this.flushBuffer();
			}
		}
	}

	class NewDataFileOperate{
		private DocumentBuilderFactory one = DocumentBuilderFactory.newInstance();
		private DocumentBuilder two = null; 
		
		public void initial() {
			try {
				two = one.newDocumentBuilder();
			} catch (ParserConfigurationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				AboutSystem.this.flushBuffer();
			}
		}
		
		public Document createDefaultDocument() {
			Document modify = two.newDocument();
			Element program = modify.createElement(XmlSymbo.RootElementNode.tagName);
			program.setAttribute(XmlSymbo.RootElementNode._Name.tagName,
					XmlSymbo.RootElementNode._Name.defaultVal);
			program.setAttribute(XmlSymbo.RootElementNode._ParserVersion.tagName,
					XmlSymbo.RootElementNode._ParserVersion.defaultVal);
			program.setAttribute(XmlSymbo.RootElementNode._ProgramSymbo.tagName,
					XmlSymbo.RootElementNode._ProgramSymbo.defaultVal);
			modify.appendChild(program);
			
			Element template_c = modify.createElement(XmlSymbo.TemplateCollectionNode.tagName);
			program.appendChild(template_c);
			Element module_c = modify.createElement(XmlSymbo.ModuleCollectionNode.tagName);
			program.appendChild(module_c);
			Element relation_c = modify.createElement(XmlSymbo.RelationCollectionNode.tagName);
			program.appendChild(relation_c);

			AboutSystem.this.writeLog_Operate(this.toString(), "新建空白文件");
			
			return modify;
		}
		
		public Document loadDataFileForParse(String path, Map<String, ElemBase> container) {
			Document doc = null;
			try {
				doc = two.parse(path);
				AboutSystem.this.writeLog_Operate(this.toString(), "打开已有文件，FILEPATH="+path);
			} catch (SAXException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				AboutSystem.this.flushBuffer();
			}
			
			Element template_c = (Element)doc.getElementsByTagName(XmlSymbo.TemplateCollectionNode.tagName).item(0);
			NodeList s1 = template_c.getElementsByTagName(XmlSymbo.TemplateNode.tagName);
			for(int i=s1.getLength();i>0;i--) {
				Element tt = (Element) s1.item(i-1);
				container.put(tt.getAttribute(XmlSymbo.TemplateNode._id.tagName), new Template(tt));
			}
			
			NodeList s2 = template_c.getElementsByTagName(XmlSymbo.BranchNode.tagName);
			for(int i=s2.getLength();i>0;i--) {
				Element tt = (Element) s2.item(i-1);
				container.put(tt.getAttribute(XmlSymbo.BranchNode._id.tagName), new Branch(tt));
			}
			
			Element module_c = (Element)doc.getElementsByTagName(XmlSymbo.ModuleCollectionNode.tagName).item(0);
			NodeList m = module_c.getElementsByTagName(XmlSymbo.ModuleNode.tagName);
			for(int i=m.getLength();i>0;i--) {
				Element tt = (Element) m.item(i-1);
				container.put(tt.getAttribute(XmlSymbo.ModuleNode._id.tagName), new Module(tt));
			}
			
			Element relation_c = (Element)doc.getElementsByTagName(XmlSymbo.RelationCollectionNode.tagName).item(0);
			NodeList r = relation_c.getElementsByTagName(XmlSymbo.RelationNode.tagName);
			for(int i=r.getLength();i>0;i--) {
				Element tt = (Element) r.item(i-1);
				container.put(tt.getAttribute(XmlSymbo.RelationNode._id.tagName), new Relation(tt));
			}
			
			return doc;
		}
	}
}
