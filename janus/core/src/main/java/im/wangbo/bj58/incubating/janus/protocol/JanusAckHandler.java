package im.wangbo.bj58.incubating.janus.protocol;

import im.wangbo.bj58.incubating.janus.transport.JanusAckResponse;

/**
 * TODO add brief description here
 *
 * Copyright © 2016 58ganji Beijing spat team. All rights reserved.
 *
 * @author Elvis Wang [wangbo12 -AT- 58ganji -DOT- com]
 */
interface JanusAckHandler {
    void onAck(final JanusAckResponse response);
}
