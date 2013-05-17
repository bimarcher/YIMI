/**
 * 
 */
package com.yimi.archer.tools

import org.codehaus.groovy.antlr.Main

/**
 * @author chezg
 * ��������*.java�ļ�
 *
 */
public class JavaFileBuilder{
	def static m_class_path = ClassLoader.getSystemResource("").getFile();
	
	/**
	 * ������·��
	 */
	def private static buildPath(String _className){
		
		// 1���������Ʒֽ��[com.yimi.arche.Hello]����ʽ
		def pathList = _className.tokenize(".")
		
		// 2����¼��ǰ����·������ʼֵΪclasspath
		def nowPath = m_class_path;
		
		// 3�����������Ŀ¼ȱʧ�򴴽�
		def levelSum = pathList.size();
		for(level in 0..<(levelSum-1)){
			nowPath = nowPath + pathList.get(level) + "/";
			File dir = new File(nowPath);
			if(dir.exists() && dir.isDirectory()){
				continue;
			}
			dir.mkdir();
		}
		
		// 4�����java�ļ������ڣ��򴴽��յ�java�ļ�
		nowPath = nowPath + pathList.get(levelSum-1) + ".java";
		File javaFile = new File(nowPath);
		if(!javaFile.exists()){
			javaFile.createNewFile();
		}
		
		// 5��������·��
		return nowPath;
	}
	
	def static build(_className,_classContent){
		
		def filePath = buildPath(_className);

		println filePath;
		def file = new File(filePath);
		
		file.write(_classContent);
		return filePath;
	}
	
	def static javac(_className){
		Runtime runtime = Runtime.getRuntime();
		def filePath = m_class_path + _className + ".java";
		Process proc = runtime.exec("javac "+filePath);
		int exitVal = proc.waitFor();
		return exitVal;
		
	}
	static void main(args) {  
		JavaFileBuilder.build("com.yimi.judy.Hello","");
	}
}
