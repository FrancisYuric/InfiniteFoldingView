package francis.ciruy.com.infinitefoldingview.controller.contentController.impl;

import java.util.ArrayList;
import java.util.List;

import francis.ciruy.com.infinitefoldingview.controller.contentController.BaseContentController;
import francis.ciruy.com.infinitefoldingview.entity.BaseContactEntity;

public class TitleContentController extends BaseContentController {
    private List<BaseContactEntity> contactEntities = new ArrayList<>();

    public TitleContentController(BaseContactEntity contactEntity) {
        this.contactEntities.clear();
        this.contactEntities.add(contactEntity);
    }

    public void clicked(BaseContactEntity baseContactEntity) {
        int clickedIndex = contactEntities.indexOf(baseContactEntity);
        if (clickedIndex != -1) {
            contactEntities = contactEntities.subList(0, clickedIndex + 1);
        } else {
            contactEntities.add(baseContactEntity);
        }
    }

    public void push(BaseContactEntity baseContactEntity) {
        contactEntities.add(baseContactEntity);
    }

    public void pop() {
        contactEntities.remove(contactEntities.size() - 1);
    }

    public BaseContactEntity getLast() {
        return contactEntities.get(contactEntities.size() - 1);
    }

    public List<BaseContactEntity> getTitleList(){
        return contactEntities;
    }
}
