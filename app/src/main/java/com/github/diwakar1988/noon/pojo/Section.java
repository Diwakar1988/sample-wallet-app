package com.github.diwakar1988.noon.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 'Diwakar Mishra' on 16,November,2018
 */
public class Section {
    public static final String TYPE_CAROUSEL="carousel";
    public static final String TYPE_LIST="list";
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("actions")
    @Expose
    private List<Action> actions = new ArrayList<>();

    public boolean isCarousel() {
        return getType().toLowerCase().equals(TYPE_CAROUSEL);
    }
    public boolean isList() {
        return getType().toLowerCase().equals(TYPE_LIST);
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public static class Action {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("icon")
        @Expose
        private String icon;
        @SerializedName("hasOffer")
        @Expose
        private Boolean hasOffer;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public Boolean getHasOffer() {
            return hasOffer;
        }

        public void setHasOffer(Boolean hasOffer) {
            this.hasOffer = hasOffer;
        }

    }
}
