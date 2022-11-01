package com.github.smuddgge.engine;

import com.github.smuddgge.Client;
import com.github.smuddgge.connections.ClientConnection;
import com.github.smuddgge.database.data.GameRecord;
import com.github.smuddgge.database.data.PlayerRecord;
import com.github.smuddgge.events.*;
import com.github.smuddgge.game.ChessBoard;
import com.github.smuddgge.game.ChessColour;
import com.github.smuddgge.managers.ClientNetworkManager;
import com.github.smuddgge.requests.GameRoomRequest;

import java.io.IOException;
import java.util.Map;
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
            Console.print(ConsoleColour.WHITE + "[Socket] Unable to connect to the server. This may be " +
                    "because the server is offline or the config contains the wrong host address.");
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

        PlayerRecord playerRecord = new PlayerRecord();
        playerRecord.uuid = clientUUID.toString();
        playerRecord.name = name;
        playerRecord.joinDate = String.valueOf(System.currentTimeMillis());

        clientConnection.getNetworkManager().broadcastEvent(new DatabasePlayerUpdateEvent(playerRecord));
    }

    /**
     * Used to send game data to the server
     * @param winner The winner of the game
     * @param board The instance of the game board
     */
    public static void updateGame(ChessColour winner, ChessBoard board) {

        Object gameRoomObject = MultiplayerManager.get().request(new GameRoomRequest());
        Map<String, Object> gameRoomMap = (Map<String, Object>) gameRoomObject;

        if (gameRoomMap == null) return;

        GameRecord gameRecord = new GameRecord();
        gameRecord.uuid = String.valueOf(UUID.randomUUID());
        gameRecord.player1 = (String) gameRoomMap.get("player1");
        gameRecord.player2 = (String) gameRoomMap.get("player2");
        if (winner == ChessColour.WHITE) gameRecord.winningPlayer = gameRecord.player1;
        if (winner == ChessColour.BLACK) gameRecord.winningPlayer = gameRecord.player2;
        gameRecord.winningColour = winner.toString();
        gameRecord.log = gameRoomMap.get("moves").toString();
        gameRecord.timeStamp = String.valueOf(System.currentTimeMillis());

        MultiplayerManager.get().broadcastEvent(new DatabaseGameUpdateEvent(gameRecord));
        MultiplayerManager.get().broadcastEvent(new PlayerMoveEvent(board.getLog().getLast().convertClassToString()));
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
