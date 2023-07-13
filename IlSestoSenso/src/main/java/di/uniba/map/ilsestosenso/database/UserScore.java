package di.uniba.map.ilsestosenso.database;


/**
 * @author IlSestoSenso
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

        score = 100 - (time / 10);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    @Override
    public String toString() {
        StringBuilder newUser = new StringBuilder();
        newUser.append(username);
        for(int i = username.length(); i<15; i++)
            newUser.append(" ");
        String newTime = String.format("%02d:%02d", time/60, time%60);
        StringBuilder newScore = new StringBuilder();
        newScore.append(score);
        for(int i= newScore.length(); i<7; i++)
            newScore.append(" ");
        
        return (newUser.toString() + " " + newTime + " " + newScore.toString() + " " + data);
    }


}
