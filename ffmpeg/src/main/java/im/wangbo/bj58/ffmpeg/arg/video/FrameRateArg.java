package im.wangbo.bj58.ffmpeg.arg.video;

import com.google.auto.value.AutoValue;

import java.util.Optional;

import im.wangbo.bj58.ffmpeg.arg.FrameRate;
import im.wangbo.bj58.ffmpeg.arg.InOutputArg;
import im.wangbo.bj58.ffmpeg.arg.InputArg;
import im.wangbo.bj58.ffmpeg.arg.OutputArg;
import im.wangbo.bj58.ffmpeg.ffmpeg.StreamSpecifier;

/**
 * Specify the frame rate of a video, expressed as the number of frames generated per second.
 * It has to be a string in the format frame_rate_num/frame_rate_den,
 * an integer number, a float number or a valid video frame rate abbreviation.
 *
 * @author Elvis Wang
 * @see im.wangbo.bj58.ffmpeg.arg.FrameRate
 */
@AutoValue
public abstract class FrameRateArg implements InOutputArg {
    @Override
    public final String argName() {
        final String specifier = streamSpecifier().asString();
        return specifier.isEmpty() ? "-r" : "-r:" + specifier;
    }

    @Override
    public final String description() {
        return "Set frame rate (Hz value, fraction or abbreviation). " +
                "As an input option, ignore any timestamps stored in the file and " +
                "instead generate timestamps assuming constant frame rate fps." +
                "As an output option, duplicate or drop input frames to achieve constant output frame rate fps.";
    }

    abstract StreamSpecifier streamSpecifier();

    abstract FrameRate frameRate();

    @Override
    public final Optional<String> argValue() {
        return Optional.of(frameRate().asString());
    }

    private static FrameRateArg create(final StreamSpecifier specifier, final FrameRate fps) {
        return new AutoValue_FrameRateArg(specifier, fps);
    }

    public static InputArg asInput(final StreamSpecifier specifier, final FrameRate fps) {
        return create(specifier, fps);
    }

    public static OutputArg asOutput(final StreamSpecifier specifier, final FrameRate fps) {
        return create(specifier, fps);
    }
}