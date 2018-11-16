package com.github.diwakar1988.noon.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 'Diwakar Mishra' on 16,November,2018
 */
public class ClientConfigurations {
    @SerializedName("sections")
    @Expose
    private List<Section> sections = new ArrayList<>();

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }
}
