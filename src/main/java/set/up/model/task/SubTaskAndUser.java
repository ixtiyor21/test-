package set.up.model.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import set.up.model.BaseId;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubTaskAndUser extends BaseId {
    private String userId;
    private String subTaskId;
}