package dialga.shiny.api.players;

import dialga.shiny.api.teams.Team;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by ShinyDialga45 on 3/31/2015.
 */
public class PlayerUtils {

    public static Team getTeam(Player player) {
        try {
            String urlString = "https://oc.tc/";
            if (player.getName() == null) {
                if (player.getUUID() == null) {
                    return null;
                } else {
                    urlString = urlString + player.getUUID().toString();
                }
            } else {
                urlString = urlString + player.getName().toString();
            }
            URL url = new URL(urlString);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(url.openStream()));

            String inputLine;
            int i = 0;
            int isTeam = 0;
            while ((inputLine = in.readLine()) != null) {
                i++;
                if (inputLine.equals("<h6>team</h6>")) {
                    isTeam = isTeam + 2;
                }
                if (i == isTeam) {
                    new Team(inputLine.replaceAll("\\<[^>]*>",""));
                }
            }
        } catch (Exception e) {

        }
        return null;
    }

}
