package de.embl.cba.elastixwrapper;

import java.util.ArrayList;
import java.util.List;

public abstract class ElastixTransformationParameters
{
    public static List<String> getParametersHenningNo5( ElastixSettings settings )
    {
        List<String> parameters = new ArrayList<>();

        parameters.add("(CheckNumberOfSamples \"false\")");

        parameters.add("(Transform \"" + settings.transformationType + "Transform\")");
        parameters.add("(NumberOfResolutions " + settings.resolutionPyramid.split(";").length+")");
        parameters.add("(MaximumNumberOfIterations " + settings.iterations +")");
        parameters.add("(ImagePyramidSchedule " + settings.resolutionPyramid.replace(";"," ")+")");
        parameters.add("(FinalGridSpacingInVoxels " + settings.bSplineGridSpacing+" )");

        // Spatial Samples
        parameters.add("(NumberOfSpatialSamples " +
                settings.spatialSamples.
                        replace(";"," ").
                        replace("full","0")
                +")");

        // ImageSampler
        String imageSampler = "(ImageSampler ";
        for ( String s : settings.spatialSamples.split(";") )
        {
            imageSampler += s.equals("full") ? " \"Full\" " : " \"Random\" ";
        }
        imageSampler += ")";
        parameters.add(imageSampler);

        if ( settings.bitDepth == 8 )
            parameters.add("(ResultImagePixelType \"unsigned char\")");
        else if ( settings.bitDepth == 16 )
            parameters.add("(ResultImagePixelType \"unsigned short\")");
        else
        {
            settings.logService.error("Bit depth " + settings.bitDepth + " not supported.");
            return null;
        }

        parameters.add("(DefaultPixelValue 0)");
        parameters.add("(Optimizer \"AdaptiveStochasticGradientDescent\")");

        parameters.add("(Registration \"MultiResolutionRegistration\")");
        parameters.add("(WriteTransformParametersEachIteration \"false\")");
        parameters.add("(WriteTransformParametersEachResolution \"false\")");
        parameters.add("(WriteResultImageAfterEachResolution \"false\")");
        parameters.add("(WritePyramidImagesAfterEachResolution \"false\")");
        parameters.add("(FixedInternalImagePixelType \"float\")");
        parameters.add("(MovingInternalImagePixelType \"float\")");
        parameters.add("(UseDirectionCosines \"false\")");
        parameters.add("(Interpolator \"LinearInterpolator\")");
        parameters.add("(ResampleInterpolator \"FinalLinearInterpolator\")");
        parameters.add("(FixedImagePyramid \"FixedRecursiveImagePyramid\")");
        parameters.add("(MovingImagePyramid \"MovingRecursiveImagePyramid\")");
        parameters.add("(AutomaticParameterEstimation \"true\")");
        parameters.add("(AutomaticScalesEstimation \"true\")");
        parameters.add("(Metric \"AdvancedMeanSquares\")");
        parameters.add("(AutomaticTransformInitialization \"false\")");
        parameters.add("(HowToCombineTransforms \"Compose\")");
        parameters.add("(ErodeMask \"false\")");
        parameters.add("(NewSamplesEveryIteration \"true\")");

        parameters.add("(BSplineInterpolationOrder 1)");
        parameters.add("(FinalBSplineInterpolationOrder 3)");
        parameters.add("(WriteResultImage \"true\")");
        parameters.add("(ResultImageFormat \"" + settings.resultImageFileType + "\")");

        return( parameters );
    }

}
