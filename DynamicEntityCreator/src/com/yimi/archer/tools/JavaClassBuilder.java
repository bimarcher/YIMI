package com.yimi.archer.tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class JavaClassBuilder {
	private static String m_class_path = ClassLoader.getSystemResource("")
			.getFile();

	/**
	 * ������·��
	 * 
	 * @throws IOException
	 */
	private static HashMap<String, Object> buildPath(String _className)
			throws IOException {

		// 1���������Ʒֽ��[com.yimi.arche.Hello]����ʽ
		String[] pathList = _className.split("\\.");

		// 2����¼��ǰ����·������ʼֵΪclasspath
		String nowPath = m_class_path;

		// 3�����������Ŀ¼ȱʧ�򴴽�
		int levelSum = pathList.length;
		for (int level = 0; level < levelSum - 1; level++) {
			nowPath = nowPath + pathList[level] + "/";
			File dir = new File(nowPath);
			if (dir.exists() && dir.isDirectory()) {
				continue;
			}
			dir.mkdir();
		}

		// 4�����java�ļ������ڣ��򴴽��յ�java�ļ�
		String fileName = pathList[levelSum - 1] + ".java";
		nowPath = nowPath + fileName;
		File javaFile = new File(nowPath);
		if (!javaFile.exists()) {
			javaFile.createNewFile();
		}

		// 5��������·��

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
	 * ����java�ļ����ұ���
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
