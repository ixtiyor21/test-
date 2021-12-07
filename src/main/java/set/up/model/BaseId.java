package set.up.model;

import lombok.Getter;

import java.util.UUID;

@Getter
public class BaseId {
    private final String id = UUID.randomUUID().toString().replace("-", "");
}
