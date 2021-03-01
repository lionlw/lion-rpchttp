package com.lion.utility.http.net;

import org.junit.Test;

import com.lion.utility.http.net.FTPLIB;

public class FTPLIBTest {
	@Test
	public void uploadFile() throws Exception {
		FTPLIB.uploadFile(
				"192.168.2.131",
				21,
				"user1",
				"123",
				"d:\\work\\code\\temp\\log\\fileHead20200611.log",
				"/2020/lion/",
				true);

		FTPLIB.uploadFile(
				"192.168.2.131",
				21,
				"user2",
				"456",
				"d:\\work\\code\\temp\\log\\1test.txt",
				"/2020/lion/",
				true);
	}
}
