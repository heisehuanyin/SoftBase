package dd.assistant.symbo_xml;

public class XmlSymbo {
	public static final String parserVersion = "1.0";
	public class RootElementNode{
		public static final String tagName = "program";
		public class _Name{
			public static final String tagName = "name";
			public static final String defaultVal = "default";
		}
		public class _ParserVersion{
			public static final String tagName = "parser_v";
			public static final String defaultVal = XmlSymbo.parserVersion;
		}
		public class _ProgramSymbo{
			public static final String tagName = "program_s";
			public static final String defaultVal = "no";
		}
	}
	public class TemplateCollectionNode{
		public static final String tagName = "template_c";
	}
	public class TemplateNode{
		public static final String tagName = "module";
		public class _id{
			public static final String tagName = "id";
		}
		public class _name{
			public static final String tagName = "name";
		}
		public class _is_simple{
			public static final String tagName = "is_simple";
			public static final String defaultVal = "no";
			public static final String disableVal = "no";
			public static final String enableVal = "yes";
		}
		public class _extend{
			public static final String tagName = "extend";
			public static final String defaultVal = "none";
		}
	}
	public class BranchNode{
		public static final String tagName = "branch";
		public class _id{
			public static final String tagName = "id";
		}
		public class _name{
			public static final String tagName = "name";
		}
		public class _is_simple{
			public static final String tagName = "is_simple";
			public static final String defaultVal = "no";
			public static final String disableVal = "no";
			public static final String enableVal = "yes";
		}
		public class _is_static{
			public static final String tagName = "is_static";
			public static final String defaultVal = "no";
			public static final String disableVal = "no";
			public static final String enableVal = "yes";
		}
		public class InputNode{
			public static final String tagName = "input";
			public class _name{
				public static final String tagName = "name";
			}
			public class _type{
				public static final String tagName = "type";
			}
			public class _buffered{
				public static final String tagName = "buffered";
				public static final String defaultVal = "no";
				public static final String disableVal = "no";
				public static final String enableVal = "yes";
			}
		}
		public class OutputNode{
			public static final String tagName = "output";
			public class _name{
				public static final String tagName = "name";
			}
			public class _type{
				public static final String tagName = "type";
			}
			public class _buffered{
				public static final String tagName = "buffered";
				public static final String defaultVal = "no";
				public static final String disableVal = "no";
				public static final String enableVal = "yes";
			}
		}
	}
	
	
	public class ModuleCollectionNode{
		public static final String tagName = "module_c";
	}
	public class ModuleNode{
		public static final String tagName = "module";
		public class _id{
			public static final String tagName = "id";
		}
		public class _name{
			public static final String tagName = "name";
		}
		public class _type{
			public static final String tagName = "type";
		}
	}
	
	
	
	public class RelationCollectionNode{
		public static final String tagName = "relation_c";
	}
	public class RelationNode{
		public static final String tagName = "relation";
		public class _id{
			public static final String tagName = "id";
		}
		public class _belongs{
			public static final String tagName = "belongs";
		}
		public class _from{
			public static final String tagName = "from";
		}
		public class _to{
			public static final String tagName = "to";
		}
		public class PortNode{
			public static final String tagName = "port";
			public class _to{
				public static final String tagName = "to";
				public static final String branchOfThat = "_";
			}
			public class _from{
				public static final String tagName = "from";
				public static final String branchOfThat = "_";
			}
		}
	}
	
	
	
	
	
	public static void main(String arg[]) {
		System.out.println(XmlSymbo.RelationNode.PortNode._from.tagName);
	}
}