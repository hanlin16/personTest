package com.rpc;

import java.io.IOException;

/**
 * Created by Liuxd on 2018-09-15.
 */
public interface RPCServer {
    public void startService() throws IOException;

    public boolean isRunning();

    public int getPort();

    public void register(Class serviceInterface, Class impl);

    public void stopService();
}