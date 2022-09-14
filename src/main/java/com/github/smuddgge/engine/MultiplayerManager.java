package com.github.smuddgge.engine;

import com.github.smuddgge.Client;
import com.github.smuddgge.connections.ClientConnection;
import com.github.smuddgge.events.GameRoomDeleteEvent;
import com.github.smuddgge.events.PlayerConnectionEvent;
import com.github.smuddgge.events.PlayerDisconnectEvent;
import com.github.smuddgge.managers.ClientNetworkManager;

import java.io.IOException;
import java.util.UUID;

/**
 * Represents the manager for connecting to the server
 */
public class MultiplayerManager {

    /**
     * The connection to the client
     */
    private static ClientConnection clientConnection = null;

    /**
     * The clients UUID
     */
    private static final UUID clientUUID = UUID.randomUUID();

    /**
     * The clients name
     */
    private static String name = null;

    /**
     * Used to register the client with the server
     */
    public static void registerClient() {
        try {
            clientConnection = new ClientConnection(Client.getConfig().getHost(), Client.getConfig().getPort());
            clientConnection.setDebugMode(true);

            if (name != null) MultiplayerManager.setClientName(MultiplayerManager.name);
        }
        catch (IOException exception) {
            Console.print(ConsoleColour.WHITE + "[Socket] Unable to connect to the server. This may be because the server is offline or the config contains" +
                    "the wrong host address.");
        }
    }

    /**
     * Used to unregister the client from the server
     */
    public static void unregisterClient() {
        try {
            clientConnection.getNetworkManager().broadcastEvent(new GameRoomDeleteEvent());
        }
        catch (Exception ignored) {}
        try {
            clientConnection.getNetworkManager().broadcastEvent(new PlayerDisconnectEvent());
        }
        catch (Exception ignored) {}
    }

    /**
     * Used to inform the server the clients name
     * and send the player uuid
     * @param name Name to inform the server
     */
    public static void setClientName(String name) {
        MultiplayerManager.name = name;
        clientConnection.getNetworkManager().broadcastEvent(new PlayerConnectionEvent(MultiplayerManager.name, MultiplayerManager.clientUUID));
    }

    /**
     * Used to get the clients network manager
     * @return The network manager
     */
    public static ClientNetworkManager get() {
        return MultiplayerManager.clientConnection.getNetworkManager();
    }

    /**
     * Used to check if we are connected to the server
     * @return False if not connected
     */
    public static boolean isConnected() {
        return clientConnection != null;
    }
}
