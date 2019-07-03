package view;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import oracle.adf.view.rich.component.rich.input.RichInputText;

@FacesConverter(value = "view.StringConverter", forClass = String.class)
public class StringConverter implements Converter {
    public StringConverter() {
        super();
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent comp, String string) {
        RichInputText input = (RichInputText) comp;
        System.out.println("getAsObject: " + string);
        System.out.println("getValue: " + input.getValue());
        if (string != null && !string.isEmpty()) {
            if (string.contains(";")) {
                if (string.lastIndexOf(";") == string.length()) {
                    string = string;
                } else {
                    string = string.substring(0, string.lastIndexOf(";")+1);
                }
            } else {
                string = (input.getValue() != null ? input.getValue() : "") + string + ";";
            }
        } else {
            string = null;
        }
        System.out.println("getAsObject-2: " + string);
        return string;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent comp, Object object) {
        System.out.println("getAsString: " + object);
        return (String) object;
    }
}
