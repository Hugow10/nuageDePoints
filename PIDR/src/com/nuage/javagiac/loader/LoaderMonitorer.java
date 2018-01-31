package com.nuage.javagiac.loader;

public class LoaderMonitorer {
	private static ILoaderMonitorer instance;

	static {
		if ("true".equals(System.getProperty("ebug"))) {
			instance = new EffectiveLoaderMonitorer();
		} else {
			instance = new PassiveLoaderMonitorer();
		}
	}

	public static void monitor(Object o) {
		instance.monitor(o);
	}

	private static class EffectiveLoaderMonitorer implements ILoaderMonitorer {

		@Override
		public void monitor(Object o) {
			System.out.println(o);
			System.out.flush();
		}
	}

	private static class PassiveLoaderMonitorer implements ILoaderMonitorer {

		@Override
		public void monitor(Object o) {
			// nothing to do here
		}
	}

	private interface ILoaderMonitorer {
		void monitor(Object o);
	}
}
