package images;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import coreBuild.Team;

public class ImageMaker {
	
	private static Path path;
	
	static {
		try {
			URI location = ImageMaker.class.getProtectionDomain().getCodeSource().getLocation().toURI();
			path = Paths.get(location).resolve("../Chess Core/images");
		}catch (URISyntaxException e) {
			System.out.println("this does not work");
		}
	}

	public static Image getImage(String pieceName, Team side) {
		String imageName = (side == Team.white ? "white " : "black ") + pieceName + ".png";
		return new Image(Display.getDefault(), path.resolve(imageName).toString());
	}
	
	public static Image placeholderImage() {
		return new Image(Display.getDefault(), path.resolve("blank.png").toString());
	}
}
