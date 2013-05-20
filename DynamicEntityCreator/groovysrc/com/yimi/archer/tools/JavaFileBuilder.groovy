/**
 * 
 */
package com.yimi.archer.tools

import org.codehaus.groovy.antlr.Main


/**
 * @author chezg
 * 用来创建*.java文件
 *
 */
public class JavaFileBuilder{
	def static m_class_path = ClassLoader.getSystemResource("").getFile();
	
	/**
	 * 创建类路径
	 */
	def private static buildPath(String _className){
		
		// 1、将类名称分解成[com.yimi.arche.Hello]的形式
		def pathList = _className.tokenize(".")
		
		// 2、记录当前的类路径，初始值为classpath
		def nowPath = m_class_path;
		
		// 3、遍历，如果目录缺失则创建
		def levelSum = pathList.size();
		for(level in 0..<(levelSum-1)){
			nowPath = nowPath + pathList.get(level) + "/";
			File dir = new File(nowPath);
			if(dir.exists() && dir.isDirectory()){
				continue;
			}
			dir.mkdir();
		}
		
		// 4、如果java文件不存在，则创建空的java文件
		nowPath = nowPath + pathList.get(levelSum-1) + ".java";
		File javaFile = new File(nowPath);
		if(!javaFile.exists()){
			javaFile.createNewFile();
		}
		
		// 5、返回类路径
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