import de.embl.cba.elastixwrapper.commands.ApplyTransformationUsingTransformix;
import net.imagej.ImageJ;

public class TestApplyTransformationUsingTransformixUI
{
    // Main
    public static void main(final String... args) throws Exception
    {

        final ImageJ ij = new ImageJ();
        ij.ui().showUI();
        ij.command().run( ApplyTransformationUsingTransformix.class, false );

    }

}
