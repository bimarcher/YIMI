package com.yimi.archer.tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class JavaClassBuilder {
	private static String m_class_path = ClassLoader.getSystemResource("")
			.getFile();

	/**
	 * 创建类路径
	 * 
	 * @throws IOException
	 */
	private static HashMap<String, Object> buildPath(String _className)
			throws IOException {

		// 1、将类名称分解成[com.yimi.arche.Hello]的形式
		String[] pathList = _className.split("\\.");

		// 2、记录当前的类路径，初始值为classpath
		String nowPath = m_class_path;

		// 3、遍历，如果目录缺失则创建
		int levelSum = pathList.length;
		for (int level = 0; level < levelSum - 1; level++) {
			nowPath = nowPath + pathList[level] + "/";
			File dir = new File(nowPath);
			if (dir.exists() && dir.isDirectory()) {
				continue;
			}
			dir.mkdir();
		}

		// 4、如果java文件不存在，则创建空的java文件
		String fileName = pathList[levelSum - 1] + ".java";
		nowPath = nowPath + fileName;
		File javaFile = new File(nowPath);
		if (!javaFile.exists()) {
			javaFile.createNewFile();
		}

		// 5、返回类路径

		String javaPackage = _className.substring(0,
				_className.lastIndexOf("."));

		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("PathList", pathList);
		hm.put("JavaPackage", javaPackage);
		hm.put("fileName", fileName);
		hm.put("filePath", nowPath);

		return hm;
	}

	/**
	 * 创建java文件并且编译
	 * 
	 * @param _className
	 * @param _classContent
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void build(String _className, String _classContent)
			throws IOException, InterruptedException {

		HashMap<String, Object> hm = buildPath(_className);
		String filePath = (String) hm.get("filePath");

		FileWriter fw = new FileWriter(filePath);
		fw.write(_classContent);
		fw.close();

		javac(_className);

		File file = new File(filePath);
		file.delete();
	}

	private static int javac(String _filePath) throws IOException,
			InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		Process proc = runtime.exec("javac " + _filePath);
		int exitVal = proc.waitFor();
		return exitVal;

	}

	static void main(String args[]) {
		StringBuffer sb = new StringBuffer();
		JavaFileBuilder.build("com.yimi.judy.Hello", sb.toString());
	}
}
