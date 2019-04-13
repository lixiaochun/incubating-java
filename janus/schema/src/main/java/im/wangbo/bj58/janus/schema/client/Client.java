package im.wangbo.bj58.janus.schema.client;

import java.net.URI;
import java.util.concurrent.CompletableFuture;

import im.wangbo.bj58.janus.schema.GlobalRequest;
import im.wangbo.bj58.janus.schema.PluginHandleId;
import im.wangbo.bj58.janus.schema.PluginHandleRequest;
import im.wangbo.bj58.janus.schema.ServerInfo;
import im.wangbo.bj58.janus.schema.SessionId;
import im.wangbo.bj58.janus.schema.SessionRequest;
import im.wangbo.bj58.janus.schema.transport.Transport;

/**
 * TODO add brief description here
 *
 * @author Elvis Wang
 */
public interface Client {
    static Client create(final URI uri, final Transport transport) {
        return new ClientImpl(uri, transport);
    }

    CompletableFuture<Void> connect();
    CompletableFuture<Void> close();

    CompletableFuture<ServerInfo> info();

    CompletableFuture<SessionId> createSession();
    CompletableFuture<Void> destroySession(final SessionId session);

    CompletableFuture<Void> keepAlive(final SessionId session);
    CompletableFuture<PluginHandleId> attachPlugin(final SessionId session, final String plugin);
    CompletableFuture<Void> detachPlugin(final SessionId session, final PluginHandleId handle);

    CompletableFuture<Void> request(final GlobalRequest request);
    CompletableFuture<Void> request(final SessionRequest request);
    CompletableFuture<Void> request(final PluginHandleRequest request);
}
