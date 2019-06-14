package im.wangbo.bj58.ffmpeg.ffmpeg;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.net.URI;
import java.util.List;

import im.wangbo.bj58.ffmpeg.arg.OutputArg;
import im.wangbo.bj58.ffmpeg.arg.SizeInByte;
import im.wangbo.bj58.ffmpeg.arg.main.MediaCodecArg;
import im.wangbo.bj58.ffmpeg.arg.main.MediaFormatArg;
import im.wangbo.bj58.ffmpeg.arg.main.MetadataArg;
import im.wangbo.bj58.ffmpeg.arg.main.OutputFileSizeLimitArg;
import im.wangbo.bj58.ffmpeg.arg.main.OutputFramesLimitArg;
import im.wangbo.bj58.ffmpeg.arg.main.OutputQualityLimitArg;
import im.wangbo.bj58.ffmpeg.arg.main.OutputUriArg;
import im.wangbo.bj58.ffmpeg.arg.main.SimpleFilterArg;
import im.wangbo.bj58.ffmpeg.ffmpeg.codec.MediaCodec;
import im.wangbo.bj58.ffmpeg.ffmpeg.filter.FilterChain;
import im.wangbo.bj58.ffmpeg.ffmpeg.format.MediaFormat;

/**
 * TODO add brief description here
 *
 * @author Elvis Wang
 */
public interface OutputSink {
    List<OutputArg> asArgs();

    static Builder builder(final String path) {
        return new Builder(path);
    }

    class Builder {
        private final String pathToOutput;

        private final List<OutputArg> args = Lists.newArrayList();

        private Builder(final String outputPath) {
            this.pathToOutput = outputPath;
        }

        public Builder addArg(final OutputArg arg) {
            args.add(arg);
            return this;
        }

        public Builder mediaFormat(final MediaFormat f) {
            return addArg(MediaFormatArg.asOutput(f));
        }

        public Builder mediaEncoder(final MediaCodec codec) {
            return mediaEncoder(StreamSpecifier.all(), codec);
        }

        public Builder mediaEncoder(final StreamSpecifier specifier, final MediaCodec codec) {
            return addArg(MediaCodecArg.asOutput(specifier, codec));
        }

        public Builder seeking(final ImmutableList<OutputArg> seeking) {
            seeking.forEach(this::addArg);
            return this;
        }

        public Builder limitOutputSize(final SizeInByte size) {
            return addArg(OutputFileSizeLimitArg.of(size));
        }

        public Builder limitOutputFrames(final int n) {
            return limitOutputFrames(StreamSpecifier.all(), n);
        }

        public Builder limitOutputFrames(final StreamSpecifier specifier, final int n) {
            return addArg(OutputFramesLimitArg.of(specifier, n));
        }

        public Builder limitOutputQuality(final int n) {
            return addArg(OutputQualityLimitArg.of(StreamSpecifier.all(), n));
        }

        public Builder limitOutputQuality(final StreamSpecifier specifier, final int n) {
            return addArg(OutputQualityLimitArg.of(specifier, n));
        }

        public Builder addMetadata(final String key, final String val) {
            return addArg(MetadataArg.of(key, val));
        }

        public Builder filter(final FilterChain chain) {
            return addArg(SimpleFilterArg.of(StreamSpecifier.all(), chain));
        }

        public Builder filter(final StreamSpecifier specifier, final FilterChain chain) {
            return addArg(SimpleFilterArg.of(specifier, chain));
        }

        public OutputSink build() {
            final ImmutableList<OutputArg> outputArgs = ImmutableList.<OutputArg>builder()
                    .addAll(args)
                    .add(OutputUriArg.of(URI.create(pathToOutput)))
                    .build();
            return () -> outputArgs;
        }
    }
}