package com.example.demo.classfile.type;

/**
 * @Author XZQ
 * @Date 2021/9/26 22:42
 **/
public class MethodInfo {
    private U2 access_flag;
    private U2 name_index;
    private U2 descriptor_index;
    private U2 attributes_count;
    private Attributeinfo[] attributes;

    public U2 getAccess_flag() {
        return access_flag;
    }

    public void setAccess_flag(U2 access_flag) {
        this.access_flag = access_flag;
    }

    public U2 getName_index() {
        return name_index;
    }

    public void setName_index(U2 name_index) {
        this.name_index = name_index;
    }

    public U2 getDescriptor_index() {
        return descriptor_index;
    }

    public void setDescriptor_index(U2 descriptor_index) {
        this.descriptor_index = descriptor_index;
    }

    public U2 getAttributes_count() {
        return attributes_count;
    }

    public void setAttributes_count(U2 attributes_count) {
        this.attributes_count = attributes_count;
    }

    public Attributeinfo[] getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributeinfo[] attributes) {
        this.attributes = attributes;
    }
}
