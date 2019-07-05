package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.Optional;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.model.AutoSuggestUIHints;

public class AutoCompleteBean {
    private String value;
    private List<SelectItem> items;

    public AutoCompleteBean() {
        value = "1;2";

        items = new ArrayList<SelectItem>();
        items.add(new SelectItem(1, "Oseba 1"));
        items.add(new SelectItem(2, "Oseba 2"));
        items.add(new SelectItem(3, "Oseba 3"));
    }

    public List suggestItems(FacesContext facesContext, AutoSuggestUIHints autoSuggestUIHints) {
        // TODO enable proper filtering
        return items;
    }

    public void valueChangeListener(ValueChangeEvent valueChangeEvent) {
        System.out.println("valueChangeEvent: " + valueChangeEvent.getNewValue());
        RichInputText comp = (RichInputText) valueChangeEvent.getComponent();
        String newValue = valueChangeEvent.getNewValue() != null ? valueChangeEvent.getNewValue().toString() : null;
        String oldValue = valueChangeEvent.getOldValue() != null ? valueChangeEvent.getOldValue().toString() : null;
        System.out.println("newValue: " + newValue);
        System.out.println("oldValue: " + oldValue);
        if (newValue != null) {
            if (newValue.contains(";")) {
                if (newValue.lastIndexOf(";") == newValue.length()) {
                    newValue = newValue;
                } else {
                    newValue = newValue.substring(0, newValue.lastIndexOf(";")+1);
                }
            } else {
                String item = findItemByValue(newValue);
                if (item != null) {
                    newValue = (oldValue != null ? oldValue : "") + item + ";";
                }
                newValue = newValue.contains(";") ? newValue : null;
            }            
            comp.setValue(newValue);
            AdfFacesContext.getCurrentInstance().addPartialTarget(comp);
        }
    }

    private String findItemByValue(String value) {
        Optional<SelectItem> selectItem = items.stream().filter(item -> item.getValue().toString().equals(value)).findFirst();
        if (selectItem.isPresent()) {
            return selectItem.get().getLabel();
        }
        return null;
    }

    private String findItemByLabel(String label) {
        Optional<SelectItem> selectItem = items.stream().filter(item -> item.getLabel().toString().equals(label)).findFirst();
        if (selectItem.isPresent()) {
            return selectItem.get().getValue().toString();
        }
        return null;
    }

    public void setValue(String value) {
        System.out.println("setValue: " + value);
        if (value != null) {
            StringBuffer sb = new StringBuffer();
            List<String> labels = Arrays.asList(value.split(";"));
            for (String label : labels) {
                String item = findItemByLabel(label);
                if (item != null) {
                    sb.append(item).append(";");                    
                }
            }
            value = sb.toString();
            
        }
        this.value = value;
    }

    public String getValue() {
        System.out.println("getValue: " + value);
        if (value != null) {
            StringBuffer sb = new StringBuffer();
            List<String> values = Arrays.asList(value.split(";"));
            for (String value : values) {
                String item = findItemByValue(value);
                if (item != null) {
                    sb.append(item).append(";");                    
                }
            }
            return sb.toString();
        }
        return value;
    }
}
