package se.lars;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.Optional;

@Value.Immutable
@JsonSerialize(as = ImmutableTask.class)
@JsonDeserialize(as = ImmutableTask.class)
public interface Task {
    @Value.Parameter
    long id();
    @Value.Parameter
    Optional<String> title();
    @Value.Parameter
    boolean completed();
}
