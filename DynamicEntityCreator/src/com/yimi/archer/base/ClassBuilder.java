package com.yimi.archer.base;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.yimi.archer.tools.JavaFileBuilder;

public class ClassBuilder {
	private static String m_classpath_url = ClassLoader.getSystemResource("")
			.getFile();

	public static Class<?> build(String _className) throws IOException {

		String sourceFilePath = m_classpath_url + _className + ".java";
		FileWriter fw = new FileWriter(sourceFilePath);
		fw.write("something");

		fw.close();

		Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec("javac e:\\" + _className);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void main(String[] args) throws IOException,
			ClassNotFoundException, InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, SecurityException, NoSuchMethodException {

		JavaFileBuilder.build("Hello","");
		JavaFileBuilder.javac("Hello");

		Class Hello = Class.forName("Hello");
		Object hello = Hello.newInstance();
		Method sayHi = Hello.getMethod("sayHi", new Class[0]);
		sayHi.invoke(hello, new Object[0]);

	}
}
