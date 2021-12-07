package set.up.model.task;

import lombok.*;
import set.up.enums.Status;
import set.up.model.BaseId;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"title", "description", "status"})
public class Task extends BaseId {
    private String title;
    private String description;
    private Status status;
}
