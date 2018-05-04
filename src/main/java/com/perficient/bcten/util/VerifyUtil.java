package com.perficient.bcten.util;

import java.io.IOException;
import java.util.UUID;

import com.perficient.bcten.util.exception.CException;

@SuppressWarnings("restriction")
public final class VerifyUtil {
	private VerifyUtil() {

	}

	private static VerifyUtil instanceUtil;
	private String randomString;

	public static synchronized VerifyUtil getInstance() {
		if (instanceUtil == null) {
			instanceUtil = new VerifyUtil();
		}
		return instanceUtil;
	}

	public String getCode(int userId) {
		String code;
		if (randomString == null) {
			UUID uuid = UUID.randomUUID();
			code = uuid.toString() + "-" + userId;
		} else {
			code = randomString + "-" + userId;
		}
		sun.misc.BASE64Encoder baseEncode = new sun.misc.BASE64Encoder();
		return baseEncode.encode(code.getBytes());

	}

	public String getID(String code64) {
		sun.misc.BASE64Decoder baseDecode = new sun.misc.BASE64Decoder();
		try {
			byte[] dist = baseDecode.decodeBuffer(code64);
			String code = new String(dist);
			int index = code.lastIndexOf('-') + 1;
			return code.substring(index);
		} catch (IOException e) {
			throw new CException(e);
		}
	}

	public void setRandomString(String randomString) {
		this.randomString = randomString;
	}

}
