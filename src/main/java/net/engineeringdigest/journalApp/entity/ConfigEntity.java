package net.engineeringdigest.journalApp.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "configurations")
@Data
public class ConfigEntity {
    @Getter
    private ObjectId id;
    @Getter
    @Setter
    private String key;
    @Getter
    @Setter
    private String value;
}
