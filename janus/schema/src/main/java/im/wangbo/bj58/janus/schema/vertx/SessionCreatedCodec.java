package im.wangbo.bj58.janus.schema.vertx;

import im.wangbo.bj58.janus.schema.eventbus.SessionCreated;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

/**
 * TODO add brief description here
 *
 * @author Elvis Wang
 */
class SessionCreatedCodec implements MessageCodec<SessionCreated, SessionCreated> {
    @Override
    public void encodeToWire(Buffer buffer, SessionCreated sessionCreated) {
        buffer.appendLong(sessionCreated.sessionId());
    }

    @Override
    public SessionCreated decodeFromWire(int pos, Buffer buffer) {
        return SessionCreated.of(buffer.getLong(pos));
    }

    @Override
    public SessionCreated transform(SessionCreated sessionCreated) {
        return sessionCreated;
    }

    @Override
    public String name() {
        return new EventTypeMeta<SessionCreated>() {
        }.codecName();
    }

    @Override
    public byte systemCodecID() {
        return -1;
    }
}
