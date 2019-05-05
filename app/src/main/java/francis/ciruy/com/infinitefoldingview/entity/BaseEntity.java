package francis.ciruy.com.infinitefoldingview.entity;

import android.support.annotation.Nullable;

public abstract class BaseEntity {
    protected String alias;
    protected String name;
    protected Integer id;

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        if (id != null) {
            return id;
        }
        return super.hashCode();
    }
}
