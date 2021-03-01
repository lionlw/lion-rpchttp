package com.lion.utility.http.net;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.lion.utility.http.net.EmailLIB;

public class EmailLIBTest {
	@Test
	public void sendEmail() throws Exception {
		List<String> emailToList = new ArrayList<>();
		emailToList.add("liwei@ismartv.cn");

		List<String> attachmentFileList = new ArrayList<>();
		attachmentFileList.add("d:\\work\\code\\temp\\log\\saveText.txt");
		attachmentFileList.add("d:\\work\\code\\temp\\log\\fileHead20200611.log");

		EmailLIB.sendEmail(
				"smtp.qiye.163.com",
				25,
				"noreply@ismartv.cn",
				"ret6wR@#$*(()4&*",
				"noreply@ismartv.cn",
				"狮子",
				emailToList,
				"测试邮件-sendEmail",
				"你好，狮子",
				attachmentFileList,
				"gbk",
				3000,
				6000);
	}

	@Test
	public void sendSSLEmail() throws Exception {
		List<String> emailToList = new ArrayList<>();
		emailToList.add("liwei@ismartv.cn");

		List<String> attachmentFileList = new ArrayList<>();
		attachmentFileList.add("d:\\work\\code\\temp\\log\\saveText.txt");
		attachmentFileList.add("d:\\work\\code\\temp\\log\\fileHead20200611.log");

		EmailLIB.sendSSLEmail(
				"smtp.qiye.163.com",
				25,
				"noreply@ismartv.cn",
				"ret6wR@#$*(()4&*",
				"noreply@ismartv.cn",
				"狮子",
				emailToList,
				"测试邮件-sendSSLEmail",
				"你好，狮子",
				attachmentFileList,
				"gbk",
				3000,
				6000);
	}
}
