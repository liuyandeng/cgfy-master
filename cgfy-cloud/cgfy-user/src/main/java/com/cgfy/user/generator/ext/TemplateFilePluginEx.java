package com.cgfy.user.generator.ext;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import tk.mybatis.mapper.generator.TemplateFilePlugin;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.util.List;
import java.util.Properties;

public class TemplateFilePluginEx extends TemplateFilePlugin {
	private String charsetName;
	private String beanKey;

	protected String read(InputStream inputStream) throws IOException {
//		try {
//			byte[] inputBytes = IOUtils.toByteArray(inputStream);
//			String year = DateFormatUtils.format(Calendar.getInstance(), "yyyy");
//			byte[] bytes2 = decrypt(inputBytes, (year + year + year).getBytes());
//			inputStream = new ByteArrayInputStream(bytes2);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, this.charsetName));
		StringBuffer stringBuffer = new StringBuffer();
		String line = reader.readLine();
		while (line != null) {
			stringBuffer.append(line).append("\n");
			line = reader.readLine();
		}
		return stringBuffer.toString();
	}

	private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
		SecureRandom sr = new SecureRandom();

		DESKeySpec dks = new DESKeySpec(key);

		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey securekey = keyFactory.generateSecret(dks);

		Cipher cipher = Cipher.getInstance("DES");

		cipher.init(2, securekey, sr);

		return cipher.doFinal(data);
	}

	public void setProperties(Properties properties) {
		super.setProperties(properties);
		this.charsetName = properties.getProperty("charsetName", "UTF-8");
		this.beanKey = properties.getProperty("beanKey");
	}

	public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
		String targetPackage = getProperties().getProperty("targetPackage");
		String fileName = getProperties().getProperty("fileName");
		fileName = StringUtils.replace(fileName, "${tableClass.shortClassName}", introspectedTable.getBaseRecordType());

		String className = StringUtils.removeEndIgnoreCase(fileName, ".java");

		String classFullName = StringUtils
				.join(new String[] { targetPackage, new FullyQualifiedJavaType(className).getShortName() }, ".");

		introspectedTable.setAttribute(this.beanKey, classFullName);
		introspectedTable.setAttribute(this.beanKey + "_SHORT",
				new FullyQualifiedJavaType(classFullName).getShortName());

		return super.contextGenerateAdditionalJavaFiles(introspectedTable);
	}
}
