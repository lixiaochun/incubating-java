package im.wangbo.bj58.ffmpeg.cli.ffmpeg.arg;

import com.google.auto.value.AutoValue;
import im.wangbo.bj58.ffmpeg.common.FrameRate;
import im.wangbo.bj58.ffmpeg.streamspecifier.StreamSpecifier;

import java.util.Optional;

/**
 * Set the audio sampling frequency.
 * <p>
 * For output streams it is set by default to the frequency of the corresponding input stream.
 * For input streams this option only makes sense for audio grabbing devices and raw demuxers and is mapped to the corresponding demuxer options.
 *
 * @author Elvis Wang
 * @see FrameRate
 */
@AutoValue
public abstract class SamplingFrequencyArg implements InOutputArg {
    @Override
    public final String name() {
        final String specifier = streamSpecifier().asString();
        return specifier.isEmpty() ? "-ar" : "-ar:" + specifier;
    }

    @Override
    public final String description() {
        return "Set the audio sampling frequency. " +
            "For input streams this option only makes sense for audio grabbing devices and raw demuxers and " +
            "is mapped to the corresponding demuxer options. " +
            "For output streams it is set by default to the frequency of the corresponding input stream.";
    }

    abstract StreamSpecifier streamSpecifier();

    abstract int samplingFreq();

    @Override
    public final Optional<String> value() {
        return Optional.of(String.valueOf(samplingFreq()));
    }

    private static SamplingFrequencyArg create(StreamSpecifier streamSpecifier, int samplingFreq) {
        return new AutoValue_SamplingFrequencyArg(streamSpecifier, samplingFreq);
    }

    public static InputArg asInput(final StreamSpecifier specifier, final int samplingFreq) {
        return create(specifier, samplingFreq);
    }

    public static OutputArg asOutput(final StreamSpecifier specifier, final int samplingFreq) {
        return create(specifier, samplingFreq);
    }
}
