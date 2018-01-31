package com.nuage.javagiac.ressource;

import java.io.InputStream;

public class DllProvider {

	public static final String FOLDERPAH = "libNuage/";

	public static InputStream getJavaJiac() {
		return DllProvider.class.getResourceAsStream(getJavaGiacFileName());
	}

	public static String getJavaGiacFileName() {
		return "javagiac.dll";
	}

	public static InputStream getLibGCC() {
		return DllProvider.class.getResourceAsStream(getLibGccFileName());
	}

	public static String getLibGccFileName() {
		return "libgcc_s_dw2-1.dll";
	}

	public static InputStream getLibStdCpp() {
		return DllProvider.class.getResourceAsStream(getLibStdCppFileName());
	}

	public static String getLibStdCppFileName() {
		return "libstdc++-6.dll";
	}
}
