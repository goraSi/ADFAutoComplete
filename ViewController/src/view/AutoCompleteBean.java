package view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import oracle.adf.view.rich.model.AutoSuggestUIHints;

public class AutoCompleteBean {
    private String value;

    public AutoCompleteBean() {
        value = "burek;";
    }

    public List suggestItems(FacesContext facesContext, AutoSuggestUIHints autoSuggestUIHints) {
        List suggestItems = new ArrayList();
        suggestItems.add(new SelectItem("item_1"));
        suggestItems.add(new SelectItem("item_2"));
        suggestItems.add(new SelectItem("item_3"));
        return suggestItems;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
