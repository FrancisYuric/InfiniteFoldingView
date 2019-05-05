package francis.ciruy.com.infinitefoldingview.entity;

import java.util.ArrayList;
import java.util.List;

public class BaseContactEntity extends BaseEntity {
    public Boolean isSelected;
    public Boolean isLast;
    public List<BaseContactEntity> subDepartment = new ArrayList<>();
}
