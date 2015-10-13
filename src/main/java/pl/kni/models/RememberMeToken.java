package pl.kni.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Created by Maciej on 13.10.2015.
 */
@Entity
public class RememberMeToken {

    @Id
    private String series;
    private String tokenValue;
    private String username;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String tokenUsername) {
        this.username = tokenUsername;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
