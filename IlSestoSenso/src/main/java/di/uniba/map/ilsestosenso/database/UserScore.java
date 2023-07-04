
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
    
    @Override
    public String toString() {
        String user = username + "                     ";
        
        if (time / 60 < 10)
        {
            user = user + "0" + time / 60 + ":";
        } else
        {
            user = user + (time / 60) + ":";
        }
        if (time % 60 < 10)
        {
            user = user + "0" + time % 60;
        } else
        {
            user = user + (time % 60);
        }

        user = user +"                "+ score +"             "+ data;
        
        return user;
    }
    
}
