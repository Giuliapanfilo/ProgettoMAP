
package di.uniba.map.ilsestosenso.database;

/**
 *
 * @author Giulia
 */
public class UserScore {

    private String username;
    private int time;
    private int score;
    private String data;

    public UserScore(String username, String data) {
        this.username = username;
        this.data = data;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
        
        if(time<300) {
            score = 500;
        } else if(time < 600) {
            score = 400;
        } else if (time < 900) {
            score = 200;
        } else {
            score = 100;
        }
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    
}