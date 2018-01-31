package pidr.mag.sample;

import java.io.File;
import java.io.IOException;

import javax.swing.JMenuItem;

import pidr.mag.sample.implementation.FileItemRenderer;
import pidr.mag.sample.implementation.FileItemRendererConstraint;

/**
 * 
 * test de class for the formatter on {@link FileItemRenderer}
 * 
 * @author Arnault Le Prévost-Corvellec
 *
 */
public class TestFile {
	private static String format(String s) {
		FileItemRenderer render = new FileItemRenderer();
		JMenuItem item = (JMenuItem) render.getComponent(s, new FileItemRendererConstraint(100));
		return item.getText();
	}

	public static void main(String[] args) throws IOException {
		File f = new File("C:\\Users\\arnau\\workspace\\MaG\\src\\pidr\\mag\\sample\\api\\DefaultItemRenderer.java");
		String s = f.getAbsolutePath();
		System.out.println(s);
		s = format(s);
		System.out.println("postformatage : \n" + s + "\n\n");
		f = new File("/home/thisUser/Document/workspace/MaG/src/pidr/mag/sample/api/DefaultItemRenderer.java");
		s = f.getAbsolutePath();
		s = s.substring(s.indexOf(File.separatorChar));// on simule un path UNIX
		System.out.println(s);
		s = format(s);
		System.out.println("postformatage : \n" + s);

	}
}
