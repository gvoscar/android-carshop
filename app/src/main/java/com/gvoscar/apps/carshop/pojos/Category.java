package com.gvoscar.apps.carshop.pojos;

import java.util.HashMap;
import java.util.Map;

public class Category {
    public static final String key_id = "id";
    public static final String key_name = "name";
    public static final String key_readPermission = "readPermission";
    public static final String key_writePermission = "writePermission";
    public static final String key_editPermission = "editPermission";

    private String id;
    private String name;
    private boolean readPermission;
    private boolean writePermission;
    private boolean editPermission;

    public Category() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isReadPermission() {
        return readPermission;
    }

    public void setReadPermission(boolean readPermission) {
        this.readPermission = readPermission;
    }

    public boolean isWritePermission() {
        return writePermission;
    }

    public void setWritePermission(boolean writePermission) {
        this.writePermission = writePermission;
    }

    public boolean isEditPermission() {
        return editPermission;
    }

    public void setEditPermission(boolean editPermission) {
        this.editPermission = editPermission;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", readPermission=" + readPermission +
                ", writePermission=" + writePermission +
                ", editPermission=" + editPermission +
                '}';
    }

    public Map<String, Object> toMap() {
        return new HashMap<String, Object>() {{
            put("id", id);
            put("name", name);
            put("readPermission", readPermission);
            put("writePermission", writePermission);
            put("editPermission", editPermission);
        }};
    }
}
