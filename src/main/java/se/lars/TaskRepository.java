package se.lars;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.google.common.collect.ImmutableMap.of;
import static java.util.Optional.ofNullable;

@Repository
public class TaskRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final static String FIND_ALL = "SELECT * from tasks";
    private final static String FIND_ONE = "SELECT * from tasks where id=:id";
    private final static String DELETE = "DELETE from tasks where id=:id";
    private final static String SAVE = "INSERT INTO tasks (id, title, completed) " +
            "VALUES (:id, :title, :completed) " +
            "ON CONFLICT (id) " +
            "DO UPDATE SET title = :title, completed=:completed";

    @Autowired
    public TaskRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Task> findAll() {
        return jdbcTemplate.query(FIND_ALL, getRowMapper());
    }

    public Optional<Task> findOne(long id) {

        return null;
    }

    public Optional<Task> save(Task task) {
        jdbcTemplate.update(SAVE, of("id", task.id(),
                                     "title", task.title().orElse(""),
                                     "completed", task.completed()));
        return Optional.of(task);
    }

    public void delete(long id) {
        jdbcTemplate.update(DELETE, of("id", id));
    }

    private RowMapper<Task> getRowMapper() {
        return (rs, rowNum) -> ImmutableTask.builder()
                                            .id(rs.getLong("id"))
                                            .title(ofNullable(rs.getString("title")))
                                            .completed(rs.getBoolean("completed"))
                                            .build();
    }

}
