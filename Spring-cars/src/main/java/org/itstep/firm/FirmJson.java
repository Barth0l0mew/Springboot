package org.itstep.firm;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.itstep.model.ModelJson;
import org.springframework.ui.Model;

import java.util.ArrayList;

@Data
public class FirmJson {
    @JsonProperty("id")
    public String idSymbol;
    public String name;
    @JsonProperty("cyrillic-name")
    public String cyrillicName;
    public boolean popular;
    public String country;
    public ArrayList<ModelJson> models;
}
