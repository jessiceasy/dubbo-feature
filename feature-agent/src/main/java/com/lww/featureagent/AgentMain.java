package com.lww.featureagent;


import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2017-03-15
 */
public class AgentMain {
	/**
	 * 加载前修改
	 * @param agentOps
	 * @param inst
	 */
	public static void premain(String agentOps, Instrumentation inst) {
		instrument(agentOps, inst);
	}

	/**
	 * 运行中修改先注释掉
	 * @param agentOps
	 * @param inst
	 */
//	public static void agentmain(String agentOps, Instrumentation inst) {
//		instrument(agentOps, inst);
//	}

	private static void instrument(String agentOps, Instrumentation inst) {
		inst.addTransformer(new AbstractClusterInvokerTransformer());
	}

}

