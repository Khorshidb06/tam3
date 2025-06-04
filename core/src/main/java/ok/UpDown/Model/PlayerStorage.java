package ok.UpDown.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.util.HashMap;
import java.util.Map;

public class PlayerStorage {
    private static final String FILE_PATH = "players.json";

    public static void savePlayers(Array<Player> players) {
        Array<Map<String, String>> dataList = new Array<>();
        for (Player p : players) {
            Map<String, String> data = new HashMap<>();
            data.put("username", p.getUserName());
            data.put("password", p.getPassword());
            data.put("securityQuestion", p.getSecurityQuest());
            data.put("securityAnswer", p.getSecurityAns());
            dataList.add(data);
        }
        Json json = new Json();
        FileHandle file = Gdx.files.local(FILE_PATH);
        file.writeString(json.toJson(dataList), false);
    }


    private static String getStringValue(JsonValue entry, String key) {
        JsonValue val = entry.get(key);
        if (val == null) return "";
        return val.getString("value", "");
    }

    public static Array<Player> loadPlayers() {
        FileHandle file = Gdx.files.local(FILE_PATH);
        if (!file.exists()) return new Array<>();

        JsonValue jsonValue = new JsonReader().parse(file.readString());
        Array<Player> players = new Array<>();

        for (JsonValue entry : jsonValue) {
            String userName = getStringValue(entry, "userName");
            String password = getStringValue(entry, "password");
            String securityQuest = getStringValue(entry, "securityQuest");
            String securityAns = getStringValue(entry, "securityAns");

            Player player = new Player(userName, password, 100f, 5f, securityQuest, securityAns, GameAssetManager.getGameAssetManager().getAbbyPortrait());
            players.add(player);
        }

        return players;
    }


}

