package com.lion.utility.http.rpchttp.tool;

import java.io.File;

import com.lion.utility.tool.common.Tool;
import com.lion.utility.tool.file.FileLIB;

/**
 * 工具类
 * 
 * @author lion
 *
 */
public class CommonLIB {
	private CommonLIB() {
	}

	/**
	 * 保存字节码文件
	 * 
	 * @param fileName
	 *            文件名
	 * @param bytecode
	 *            字节码
	 * @throws Exception
	 *             异常
	 */
	public static void saveBytecodeFile(String fileName, byte[] bytecode) throws Exception {
		String filePath = Tool.getTmpDir() + File.separator + fileName + ".class";
		FileLIB.creatParentDir(filePath);
		FileLIB.writeBinaryFile(bytecode, filePath);
	}
}
