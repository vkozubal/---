package org.pti.poster.assembler;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class AbstractAssembler {

	protected static Object getNewInstanceFor(String className) throws Exception {
		Object result;
		Class<?> clazz;

		clazz = Class.forName(className);
		Constructor<?> ctor = clazz.getConstructor(getConstructorArgumentClasses());
		result = ctor.newInstance(getConstructorArguments());

		return result;
	}


	protected static Object[] getConstructorArguments() {
		List<Object> args = new ArrayList<>();

		return args.toArray();
	}

	protected static Class[] getConstructorArgumentClasses() {
		List<Class> args = new ArrayList<>();

		return args.toArray(new Class[args.size()]);
	}

}
