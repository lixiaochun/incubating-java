package im.wangbo.bj58.ffmpeg.cli.ffmpeg;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import im.wangbo.bj58.ffmpeg.cli.ffmpeg.arg.*;
import im.wangbo.bj58.ffmpeg.cli.ffmpeg.filter.FilterGraph;
import im.wangbo.bj58.ffmpeg.codec.MediaCodec;
import im.wangbo.bj58.ffmpeg.common.FrameRate;
import im.wangbo.bj58.ffmpeg.common.SizeInByte;
import im.wangbo.bj58.ffmpeg.format.MediaFormat;
import im.wangbo.bj58.ffmpeg.streamspecifier.StreamSpecifier;

import java.net.URI;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

/**
 * TODO add brief description here
 *
 * @author Elvis Wang
 */
public interface OutputSink {

    List<OutputArg> asArgs();

    static Builder builder(final URI uri) {
        return new Builder(uri);
    }

    static Builder builder(final Path path) {
        return new Builder(path);
    }

    class Builder {

        private final OutputUriArg outputUri;

        private final List<OutputArg> args = Lists.newArrayList();

        private List<OutputArg> seekingArgs = Collections.emptyList();

        private Builder(final URI uri) {
            this.outputUri = OutputUriArg.of(uri);
        }

        private Builder(final Path path) {
            this.outputUri = OutputUriArg.of(path);
        }

        public Builder addArg(final OutputArg arg) {
            args.add(arg);
            return this;
        }

        /**
         * "-f" option
         *
         * @param format muxer
         * @return this
         */
        public Builder mediaFormat(final MediaFormat format) {
            if (format.muxer().isPresent())
                addArg(MediaFormatArg.asOutput(format.muxer().get()));
            return this;
        }

        /**
         * "-c" option
         *
         * @param codec encoder
         * @return this
         */
        public Builder mediaEncoder(final MediaCodec codec) {
            return mediaEncoder(StreamSpecifier.all(), codec);
        }

        /**
         * "-c:v" - like option
         *
         * @param specifier stream specifier
         * @param codec encoder
         * @return this
         */
        public Builder mediaEncoder(final StreamSpecifier specifier, final MediaCodec codec) {
            if (codec.encoder().isPresent())
                addArg(MediaCodecArg.asOutput(specifier, codec.encoder().get()));
            return this;
        }

        /**
         * Seeking from {@code beg} to {@code end}.
         *
         * @param beg beg position offset from beginning
         * @param end end position offset from beginning
         * @return this
         */
        public Builder seeking(final SeekingOffsetArg beg, final SeekingEndArg end) {
            seekingArgs = ImmutableList.of(beg, end);
            return this;
        }

        /**
         * Seeking from {@code beg} with {@code duration}.
         *
         * @param beg      beg position offset from beginning
         * @param duration duration
         * @return this
         */
        public Builder seeking(final SeekingOffsetArg beg, final SeekingDurationArg duration) {
            seekingArgs = ImmutableList.of(beg, duration);
            return this;
        }

        /**
         * Seeking from {@code beg} to ending.
         *
         * @param beg beg position offset from beginning
         * @return this
         */
        public Builder seeking(final SeekingOffsetArg beg) {
            seekingArgs = ImmutableList.of(beg);
            return this;
        }

        /**
         * Seeking from beginning to {@code end}.
         *
         * @param end end position offset from beginning
         * @return this
         */
        public Builder seeking(final SeekingEndArg end) {
            seekingArgs = ImmutableList.of(end);
            return this;
        }

        /**
         * Seeking from begninning with {@code duration}.
         *
         * @param duration duration
         * @return this
         */
        public Builder seeking(final SeekingDurationArg duration) {
            seekingArgs = ImmutableList.of(duration);
            return this;
        }

        /**
         * "-r" option
         *
         * @param fps frame rate
         * @return this
         */
        public Builder frameRate(final FrameRate fps) {
            return frameRate(StreamSpecifier.all(), fps);
        }

        /**
         * "-r:v" - like option
         *
         * @param specifier stream specifier
         * @param fps frame rate
         * @return this
         */
        public Builder frameRate(final StreamSpecifier specifier, final FrameRate fps) {
            return addArg(FrameRateArg.asOutput(specifier, fps));
        }

        /**
         * "-fs" option
         *
         * @param size number of bytes
         * @return this
         */
        public Builder limitOutputSize(final SizeInByte size) {
            return addArg(OutputFileSizeLimitArg.of(size));
        }

        /**
         * "-frames" option
         *
         * @param n number of frames
         * @return this
         */
        public Builder limitOutputFrames(final int n) {
            return limitOutputFrames(StreamSpecifier.all(), n);
        }

        /**
         * "-frames:v" - like option
         *
         * @param specifier stream specifier
         * @param n number of frames
         * @return this
         */
        public Builder limitOutputFrames(final StreamSpecifier specifier, final int n) {
            return addArg(OutputFramesLimitArg.of(specifier, n));
        }

        /**
         * "-q" option
         *
         * @param n output quality
         * @return this
         */
        public Builder limitOutputQuality(final int n) {
            return addArg(OutputQualityLimitArg.of(StreamSpecifier.all(), n));
        }

        /**
         * "-q:v" - like option
         *
         * @param specifier stream specifier
         * @param n output quality
         * @return this
         */
        public Builder limitOutputQuality(final StreamSpecifier specifier, final int n) {
            return addArg(OutputQualityLimitArg.of(specifier, n));
        }

        /**
         * "-metadata" option
         *
         * @param key metadata key
         * @param val metadata value
         * @return this
         */
        public Builder addMetadata(final String key, final String val) {
            return addArg(MetadataArg.of(key, val));
        }

        /**
         * "-filter" - like option
         *
         * @param graph filter graph
         * @return this
         */
        public Builder filter(final FilterGraph graph) {
            return addArg(SimpleFilterArg.of(StreamSpecifier.all(), graph));
        }

        /**
         * "-filter:v" - like option
         *
         * @param specifier stream specifier
         * @param graph filter graph
         * @return this
         */
        public Builder filter(final StreamSpecifier specifier, final FilterGraph graph) {
            return addArg(SimpleFilterArg.of(specifier, graph));
        }

        public OutputSink build() {
            final ImmutableList<OutputArg> outputArgs = ImmutableList.<OutputArg>builder()
                .addAll(args)
                .addAll(seekingArgs)
                .add(outputUri)
                .build();
            return () -> outputArgs;
        }
    }
}
