package net.engineeringdigest.journalApp.entity;

import com.mongodb.lang.NonNull;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Setter
    @Getter
    @Id
    private ObjectId id;

    @Indexed(unique = true)
    @NonNull
    private String userName;
    @NonNull
    private String password;
    //getters, setters
    @Setter
    @DBRef
    List<JournalEntry> journalEntries = new ArrayList<JournalEntry>();
    @Setter
    @Getter
    private List<String> roles;
    private String email;
    private boolean sentimentAnalyis;

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    @NonNull
    public String getUserName() {
        return userName;
    }

    public void setUserName(@NonNull String userName) {
        this.userName = userName;
    }
    public List<JournalEntry> getJournalEntries() {
        if (journalEntries == null) {
            journalEntries = new ArrayList<>();
        }
        return journalEntries;
    }

}
