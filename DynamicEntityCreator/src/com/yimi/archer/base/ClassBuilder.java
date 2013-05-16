package com.yimi.archer.base;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;

public class ClassBuilder {
	private static URL m_classpath_url = ClassLoader.getSystemResource("");

	public static Class build(String _className) throws IOException {

		String sourceFilePath = m_classpath_url.getFile() + _className + ".java";
		// File sourceFile = new File(sourceFilePath);

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(sourceFilePath)));
		bw.write("something");

		// Runtime runtime = Runtime.getRuntime();
		// try {
		// runtime.exec("javac e:\\" + _className);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		return null;
	}

	public static void main(String[] args) throws IOException {
		System.out.println(ClassBuilder.m_classpath_url);
		ClassBuilder.build("Hello");
	}
}
