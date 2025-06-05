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
        Array<Map<String, Object>> dataList = new Array<>();
        for (Player p : players) {
            Map<String, Object> data = new HashMap<>();
            data.put("username", p.getUserName());
            data.put("password", p.getPassword());
            data.put("securityQuestion", p.getSecurityQuest());
            data.put("securityAnswer", p.getSecurityAns());
            data.put("Kill", p.getKill());
            data.put("TimeAlive", p.getTimeAlive());
            dataList.add(data);
        }
        Json json = new Json();
        FileHandle file = Gdx.files.local(FILE_PATH);
        file.writeString(json.toJson(dataList), false);
    }


    private static String getString(JsonValue entry, String key) {
        JsonValue val = entry.get(key);
        return val != null ? val.getString("value", "") : "";
    }


    private static int getInt(JsonValue entry, String key) {
        JsonValue val = entry.get(key);
        return val != null ? Integer.parseInt(val.getString("value", "0")) : 0;
    }

    private static float getFloat(JsonValue entry, String key) {
        JsonValue val = entry.get(key);
        return val != null ? Float.parseFloat(val.getString("value", "0")) : 0f;
    }




    public static Array<Player> loadPlayers() {
        FileHandle file = Gdx.files.local(FILE_PATH);
        if (!file.exists()) return new Array<>();

        JsonValue jsonValue = new JsonReader().parse(file.readString());
        Array<Player> players = new Array<>();

        for (JsonValue entry : jsonValue) {

            String userName = getString(entry, "username");
            String password = getString(entry, "password");
            String securityQuest = getString(entry, "securityQuestion");
            String securityAns = getString(entry, "securityAnswer");
            float timeAlive = getFloat(entry, "TimeAlive");
            int kill = getInt(entry, "Kill");

            Player player = new Player(userName, password, 100f, 5f, securityQuest, securityAns,
                GameAssetManager.getGameAssetManager().getAbbyPortrait());
            player.setKill(kill);
            player.setTimeAlive(timeAlive);
            players.add(player);
        }

        return players;
    }

}

